package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.Locality;
import com.xavier.vectorlink.vectorlinksysapi.model.OperationalSite;
import com.xavier.vectorlink.vectorlinksysapi.repository.LocalityRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.OperationalSiteRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.OperationalSiteNotExistsOrInactiveException;

@Service
public class LocalityService {
	
	@Autowired
	private LocalityRepository localityRepository;
	
	@Autowired
	private OperationalSiteRepository operationalSiteRepository;
	
	public Locality save(Locality locality) {
		OperationalSite operationalSite = operationalSiteRepository.getOne(locality.getOperationalSite().getId());
		if(operationalSite == null || !operationalSite.isActive()) {
			throw new OperationalSiteNotExistsOrInactiveException();
		}
		return localityRepository.save(locality);
	}
	
	public Locality update(Long id, Locality locality) {
		Locality savedLocality = localityRepository.getOne(id);
		if(savedLocality == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(locality, savedLocality, "id");
		return localityRepository.save(savedLocality);
	}

}
