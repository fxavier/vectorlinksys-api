package com.xavier.vectorlink.vectorlinksysapi.repository.helper.spray.details;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayDetails;
import com.xavier.vectorlink.vectorlinksysapi.model.SprayDetails_;
import com.xavier.vectorlink.vectorlinksysapi.model.SprayOperator_;
import com.xavier.vectorlink.vectorlinksysapi.model.Village_;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.SprayDetailsFilter;

public class SprayDetailsRepositoryImpl implements SprayDetailsRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<SprayDetails> filter(SprayDetailsFilter sprayDetailsFilter, Pageable pageable) {
	    CriteriaBuilder builder = manager.getCriteriaBuilder();
	    CriteriaQuery<SprayDetails> criteria = builder.createQuery(SprayDetails.class);
	    Root<SprayDetails> root = criteria.from(SprayDetails.class);
	    
	    Predicate[] predicates = createRestrictions(sprayDetailsFilter, builder, root);
	    criteria.where(predicates);
	    
	    TypedQuery<SprayDetails> query = manager.createQuery(criteria);
	    addPaginationRestrictions(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(sprayDetailsFilter));
	}

	private Predicate[] createRestrictions(SprayDetailsFilter sprayDetailsFilter, CriteriaBuilder builder,
			Root<SprayDetails> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(sprayDetailsFilter.getSopCode() != null) {
			predicates.add(builder.equal(root.get(SprayDetails_.sprayOperator).get(SprayOperator_.sprayOperatorCode), sprayDetailsFilter.getSopCode()));
		}
		
		if(sprayDetailsFilter.getStartDate() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(SprayDetails_.sprayDate), sprayDetailsFilter.getStartDate()));
		}
		
		if(sprayDetailsFilter.getEndDate() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(SprayDetails_.sprayDate), sprayDetailsFilter.getEndDate()));
		}
		
		if(!StringUtils.isEmpty(sprayDetailsFilter.getHouseHoldId())) {
			predicates.add(builder.like(root.get(SprayDetails_.houseHoldId), "%" + sprayDetailsFilter.getHouseHoldId() + "%"));
		}
		
		if(!StringUtils.isEmpty(sprayDetailsFilter.getVillageName())) {
			predicates.add(builder.like(builder.lower(root.get(SprayDetails_.village).get(Village_.name)), "%" + sprayDetailsFilter.getVillageName() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPaginationRestrictions(TypedQuery<SprayDetails> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstRecordOfPage = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstRecordOfPage);
		query.setMaxResults(totalRecordsPerPage);
		
	}
	
	private Long total(SprayDetailsFilter sprayDetailsFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<SprayDetails> root = criteria.from(SprayDetails.class);
		
		Predicate[] predicates = createRestrictions(sprayDetailsFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
