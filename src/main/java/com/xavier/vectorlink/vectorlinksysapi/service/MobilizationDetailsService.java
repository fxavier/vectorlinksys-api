package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationDetails;
import com.xavier.vectorlink.vectorlinksysapi.repository.MobilizationDetailsRepository;

@Service
public class MobilizationDetailsService {
	
	@Autowired
	private MobilizationDetailsRepository repository;
	
	
	public MobilizationDetails save(MobilizationDetails mobilizationDetails) {
		if(isVariablesNotEmpty(mobilizationDetails)) {
		mobilizationDetails.setReference(createReference(mobilizationDetails));	
		}
		return repository.save(mobilizationDetails);
	}

	
	public MobilizationDetails update(Long id, MobilizationDetails mobilizationDetails) {
		MobilizationDetails savedMobDetails = repository.getOne(id);
		if(savedMobDetails == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(mobilizationDetails, savedMobDetails, "id");
		return repository.save(savedMobDetails);
	}

	private String createReference(MobilizationDetails mobilizationDetails) {
		return mobilizationDetails.getMobDate().toString() + mobilizationDetails.getVillage().getId() + mobilizationDetails.getMobilizer().getId();
	}
	
	private boolean isVariablesNotEmpty(MobilizationDetails mobilizationDetails){
		return mobilizationDetails.getMobDate() != null && mobilizationDetails.getVillage().getId() != null && mobilizationDetails.getMobilizer().getId() != null;
	}
}
