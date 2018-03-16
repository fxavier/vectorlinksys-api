package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.Mobilizer;
import com.xavier.vectorlink.vectorlinksysapi.repository.MobilizerRepository;

@Service
public class MobilizerService {
	
	@Autowired
	private MobilizerRepository mobilizerRepository;
	
	
	
	public Mobilizer save(Mobilizer mobilizer) {
		return mobilizerRepository.save(mobilizer);
	}
	
	public Mobilizer update(Long id, Mobilizer mobilizer) {
		Mobilizer foundMobilizer = mobilizerRepository.getOne(id);
		if(foundMobilizer == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(mobilizer, foundMobilizer, "id");
		return mobilizerRepository.save(foundMobilizer);
	}

}
