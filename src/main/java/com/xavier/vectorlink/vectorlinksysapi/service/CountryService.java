package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.Country;
import com.xavier.vectorlink.vectorlinksysapi.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public Country update(Long id, Country country) {
		Country savedCountry = countryRepository.getOne(id);
		if(savedCountry == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(country, savedCountry, "id");
		return countryRepository.save(savedCountry);
	}
	

}
