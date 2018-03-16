package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayOperator;
import com.xavier.vectorlink.vectorlinksysapi.repository.SprayOperatorRepository;

@Service
public class SprayOperatorService {
	
	@Autowired
	private SprayOperatorRepository sprayOperatorRepository;
	
	public SprayOperator save(SprayOperator sprayOperator) {
		return sprayOperatorRepository.save(sprayOperator);
	}
	
	public SprayOperator update(Long id, SprayOperator sprayOperator) {
		SprayOperator savedSprayOperator = sprayOperatorRepository.getOne(id);
		if(savedSprayOperator == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(sprayOperator, savedSprayOperator, "id");
		return savedSprayOperator;
	}

}
