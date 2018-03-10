package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.District;
import com.xavier.vectorlink.vectorlinksysapi.model.Province;
import com.xavier.vectorlink.vectorlinksysapi.repository.DistrictRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.ProvinceRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.ProvinceNotExistsOrInactiveException;

@Service
public class DistrictService {
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	public District save(District district) {
		Province province = provinceRepository.getOne(district.getProvince().getId());
		if(province == null || !province.isActive()) {
			throw new ProvinceNotExistsOrInactiveException();
		}
		return districtRepository.save(district);
		
	}
	
	public District update(Long id, District district) {
		District savedDistrict = districtRepository.getOne(id);
		if(savedDistrict == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(district, savedDistrict, "id");
		return districtRepository.save(savedDistrict);
		
	}

}
