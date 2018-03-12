package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.Locality;
import com.xavier.vectorlink.vectorlinksysapi.model.Village;
import com.xavier.vectorlink.vectorlinksysapi.repository.LocalityRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.VillageRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.LocalityNotExistsOrInactiveException;

@Service
public class VillageService {
	
	@Autowired
	private VillageRepository villageRepository;
	
	@Autowired
	private LocalityRepository localityRepository;
	
	public Village save(Village village) {
		Locality locality = localityRepository.getOne(village.getLocality().getId());
		if(locality == null || !locality.isActive()) {
			throw new LocalityNotExistsOrInactiveException();
		}
		
		return villageRepository.save(village);
	
	}
	
	public Village update(Long id, Village village) {
		Village savedVillage = villageRepository.getOne(id);
		if(savedVillage == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(village, savedVillage, "id");
		return villageRepository.save(savedVillage);
	}

}
