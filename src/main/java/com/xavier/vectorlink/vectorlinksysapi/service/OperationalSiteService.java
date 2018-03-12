package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.District;
import com.xavier.vectorlink.vectorlinksysapi.model.OperationalSite;
import com.xavier.vectorlink.vectorlinksysapi.repository.DistrictRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.OperationalSiteRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.DistrictNotExistsOrInactiveException;

@Service
public class OperationalSiteService {
	
	@Autowired
	private OperationalSiteRepository operationalSiteRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	public OperationalSite save(OperationalSite operationalSite) {
		District district = districtRepository.getOne(operationalSite.getDistrict().getId());
		if(district ==null || !district.isActive()) {
			throw new DistrictNotExistsOrInactiveException();
		}
		return operationalSiteRepository.save(operationalSite);
	}
	
	public OperationalSite update(Long id, OperationalSite operationalSite) {
		OperationalSite savedOperationalSite = operationalSiteRepository.getOne(id);
		if(savedOperationalSite == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(operationalSite, savedOperationalSite, "id");
		return operationalSiteRepository.save(savedOperationalSite);
	}

}
