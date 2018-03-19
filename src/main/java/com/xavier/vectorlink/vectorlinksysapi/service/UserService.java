package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.User;
import com.xavier.vectorlink.vectorlinksysapi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User update(Long id, User user) {
		User savedUser = userRepository.getOne(id);
		if(savedUser == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(user, savedUser, "id");
		return userRepository.save(savedUser);
	}

}
