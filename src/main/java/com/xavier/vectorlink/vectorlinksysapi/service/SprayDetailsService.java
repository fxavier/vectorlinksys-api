package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayDetails;
import com.xavier.vectorlink.vectorlinksysapi.repository.SprayDetailsRepository;

@Service
public class SprayDetailsService {
	
	@Autowired
	private SprayDetailsRepository sprayDetailsRepository;
	
	
	public SprayDetails save(SprayDetails sprayDetails) {
		if(isVariablesNotEmpty(sprayDetails)) {
			sprayDetails.setReference(createReference(sprayDetails));
		}
		return sprayDetailsRepository.save(sprayDetails);
	}
	


	public SprayDetails update(Long id, SprayDetails sprayDetails) {
		SprayDetails savedSprayDetails = sprayDetailsRepository.getOne(id);
		if(savedSprayDetails == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(sprayDetails, savedSprayDetails, "id");
		return sprayDetailsRepository.save(savedSprayDetails);
	}

	private boolean isVariablesNotEmpty(SprayDetails sprayDetails) {
		return sprayDetails.getSprayDate() != null  && sprayDetails.getVillage() != null && sprayDetails.getSprayOperator() != null;
	}
	
	private String createReference(SprayDetails sprayDetails) {
		return sprayDetails.getSprayDate().toString() + sprayDetails.getVillage().getId() + sprayDetails.getSprayOperator().getId();
	}
}
