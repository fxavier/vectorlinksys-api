package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.Country;
import com.xavier.vectorlink.vectorlinksysapi.model.Province;
import com.xavier.vectorlink.vectorlinksysapi.repository.CountryRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.ProvinceRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.CountryNotExistsOrInactiveException;

@Service
public class ProvinceService {
	
   @Autowired
   private ProvinceRepository provinceRepository;
   
   @Autowired
   private CountryRepository countryRepository;
   
   public Province save(Province province) {
	 Country country = countryRepository.getOne(province.getCountry().getId());
	 if(country == null || !country.isActive()) {
		 throw new CountryNotExistsOrInactiveException();
	 }
	 return  provinceRepository.save(province); 
   }
   

}
