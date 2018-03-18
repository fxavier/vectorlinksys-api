package com.xavier.vectorlink.vectorlinksysapi.repository.helper.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xavier.vectorlink.vectorlinksysapi.model.User;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.UserFilter;

public interface UserRepositoryQueries {
	
	public Page<User> filter(UserFilter userFilter, Pageable pageable);

}
