package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.SprayTotalsRepository;

@Service
public class SprayTotalsService {
	
	@Autowired
	private SprayTotalsRepository sprayTotalsRepository;
	
	public SprayTotals save(SprayTotals sprayTotals) {
		if(sprayTotals.getSprayDate() != null && sprayTotals.getVillage().getId() != null) {
			sprayTotals.setReference(createReference(sprayTotals));
		}
		
		return sprayTotalsRepository.save(sprayTotals);
	}

	private String createReference(SprayTotals sprayTotals) {
		return sprayTotals.getSprayDate().toString() + sprayTotals.getVillage().getId();
	}

}
