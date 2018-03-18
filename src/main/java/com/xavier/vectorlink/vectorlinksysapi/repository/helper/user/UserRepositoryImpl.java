package com.xavier.vectorlink.vectorlinksysapi.repository.helper.user;

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

import com.xavier.vectorlink.vectorlinksysapi.model.User;
import com.xavier.vectorlink.vectorlinksysapi.model.User_;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.UserFilter;

public class UserRepositoryImpl implements UserRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<User> filter(UserFilter userFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		
		Predicate[] predicates = createRestrictions(userFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<User> query = manager.createQuery(criteria);
		addPaginationRestrictions(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(userFilter));
	}



	private Predicate[] createRestrictions(UserFilter userFilter, CriteriaBuilder builder, Root<User> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(userFilter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get(User_.name)), "%" + userFilter.getName() + "%"));
		}
		
		if(!StringUtils.isEmpty(userFilter.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get(User_.email)), "%" + userFilter.getEmail() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPaginationRestrictions(TypedQuery<User> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalRecordsPerPage = pageable.getPageSize();
		int firstRecordOfPage = currentPage * totalRecordsPerPage;
		
		query.setFirstResult(firstRecordOfPage);
		
	}
	
	private Long total(UserFilter userFilter) {
	    CriteriaBuilder builder = manager.getCriteriaBuilder();
	    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
	    Root<User> root = criteria.from(User.class);
	    
	    Predicate[] predicates = createRestrictions(userFilter, builder, root);
	    criteria.where(predicates);
	    
	    criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
