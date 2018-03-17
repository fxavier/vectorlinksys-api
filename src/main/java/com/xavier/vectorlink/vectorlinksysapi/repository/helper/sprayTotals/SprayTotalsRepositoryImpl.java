package com.xavier.vectorlink.vectorlinksysapi.repository.helper.sprayTotals;

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

import com.xavier.vectorlink.vectorlinksysapi.model.SprayOperator_;
import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals;
import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals_;
import com.xavier.vectorlink.vectorlinksysapi.model.Village_;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.SprayTotalsFilter;

public class SprayTotalsRepositoryImpl implements SprayTotalsRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<SprayTotals> filter(SprayTotalsFilter sprayTotalsFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SprayTotals> criteria = builder.createQuery(SprayTotals.class);
		Root<SprayTotals> root = criteria.from(SprayTotals.class);
		
		Predicate[] predicates = createRestrictions(sprayTotalsFilter , builder, root);
		criteria.where(predicates);
		
		TypedQuery<SprayTotals> query = manager.createQuery(criteria);
		addPaginationRestrictions(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(sprayTotalsFilter))  ;
	}




	private Predicate[] createRestrictions(SprayTotalsFilter sprayTotalsFilter, CriteriaBuilder builder,
			Root<SprayTotals> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		
		
		if(!StringUtils.isEmpty(sprayTotalsFilter.getVillageName())) {
			predicates.add(builder.like(
					builder.lower(root.get(SprayTotals_.village).get(Village_.name)), 
					"%" + sprayTotalsFilter.getVillageName().toLowerCase() + "%"));

		}
		
		if(sprayTotalsFilter.getStartDate() != null){
			predicates.add(builder.greaterThanOrEqualTo(root.get(SprayTotals_.sprayDate), sprayTotalsFilter.getStartDate()));
		}
		
		if(sprayTotalsFilter.getEndDate() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(SprayTotals_.sprayDate), sprayTotalsFilter.getEndDate()));
		}
		
		if(sprayTotalsFilter.getSopCode() != null) {
			predicates.add(builder.equal(root.get(SprayTotals_.sprayOperator)
					.get(SprayOperator_.sprayOperatorCode), sprayTotalsFilter.getSopCode()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPaginationRestrictions(TypedQuery<SprayTotals> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstRecordPerpage = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstRecordPerpage);
		query.setMaxResults(totalRecordsPerPage);
	}
	private Long total(SprayTotalsFilter sprayTotalsFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<SprayTotals> root = criteria.from(SprayTotals.class);
		
		Predicate[] predicates = createRestrictions(sprayTotalsFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
