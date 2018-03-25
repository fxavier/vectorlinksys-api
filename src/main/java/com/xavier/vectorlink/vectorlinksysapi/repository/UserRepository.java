package com.xavier.vectorlink.vectorlinksysapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavier.vectorlink.vectorlinksysapi.model.User;
import com.xavier.vectorlink.vectorlinksysapi.repository.helper.user.UserRepositoryQueries;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQueries{

	public Optional<User> findByEmail(String email);
}
