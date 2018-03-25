package com.xavier.vectorlink.vectorlinksysapi.repository.helper.mobilization.details;

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

import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationDetails;
import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationDetails_;
import com.xavier.vectorlink.vectorlinksysapi.model.Village_;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.MobilizationDetailsFilter;

public class MobilizationDetailsRepositoryImpl implements MobilizationDetailsRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<MobilizationDetails> filter(MobilizationDetailsFilter mobilizationdetailsFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<MobilizationDetails> criteria = builder.createQuery(MobilizationDetails.class);
		Root<MobilizationDetails> root = criteria.from(MobilizationDetails.class);
		
		Predicate[] predicates = createRestrictions(mobilizationdetailsFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<MobilizationDetails> query = manager.createQuery(criteria);
		addPaginationRestrictions(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(mobilizationdetailsFilter));
	}

	private Predicate[] createRestrictions(MobilizationDetailsFilter mobilizationdetailsFilter, CriteriaBuilder builder,
			Root<MobilizationDetails> root) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(mobilizationdetailsFilter.getVillageName())) {
			predicates.add(builder.like(builder.lower(root
					.get(MobilizationDetails_.village)
					.get(Village_.name)), "%" + mobilizationdetailsFilter.getVillageName() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPaginationRestrictions(TypedQuery<MobilizationDetails> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstRecordsPerPage = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstRecordsPerPage);
		query.setMaxResults(totalRecordsPerPage);
		
	}

	private Long total(MobilizationDetailsFilter mobilizationdetailsFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<MobilizationDetails> root = criteria.from(MobilizationDetails.class);
		
		Predicate[] predicates = createRestrictions(mobilizationdetailsFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	

}
