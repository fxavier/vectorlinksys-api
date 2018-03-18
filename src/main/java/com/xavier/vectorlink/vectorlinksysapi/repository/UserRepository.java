package com.xavier.vectorlink.vectorlinksysapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavier.vectorlink.vectorlinksysapi.model.User;
import com.xavier.vectorlink.vectorlinksysapi.repository.helper.user.UserRepositoryQueries;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQueries{

}
