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

import org.springframework.util.StringUtils;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals;
import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals_;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.SprayTotalsFilter;

public class SprayTotalsRepositoryImpl implements SprayTotalsRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<SprayTotals> filter(SprayTotalsFilter sprayTotalsFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SprayTotals> criteria = builder.createQuery(SprayTotals.class);
		Root<SprayTotals> root = criteria.from(SprayTotals.class);
		
		Predicate[] predicates = createRestrictions(sprayTotalsFilter , builder, root);
		criteria.where(predicates);
		
		TypedQuery<SprayTotals> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] createRestrictions(SprayTotalsFilter sprayTotalsFilter, CriteriaBuilder builder,
			Root<SprayTotals> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(StringUtils.isEmpty(sprayTotalsFilter.getVillageName())) {
			predicates.add(builder.like(
					builder.lower(root.get(SprayTotals_.village.getName())), "%" + sprayTotalsFilter.getVillageName().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
