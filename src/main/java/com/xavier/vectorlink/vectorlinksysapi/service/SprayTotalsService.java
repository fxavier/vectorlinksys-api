package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.SprayTotalsRepository;

@Service
public class SprayTotalsService {
	
	@Autowired
	private SprayTotalsRepository sprayTotalsRepository;
	
	public SprayTotals save(SprayTotals sprayTotals) {
		if(isVariablesNotEmpty(sprayTotals)) {
			sprayTotals.setReference(createReference(sprayTotals));
		}
		
		return sprayTotalsRepository.save(sprayTotals);
	}
	
	public SprayTotals update(Long id, SprayTotals sprayTotals) {
		SprayTotals foundSprayTotals = sprayTotalsRepository.getOne(id);
		if(foundSprayTotals == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(sprayTotals, foundSprayTotals, "id");
		return sprayTotalsRepository.save(foundSprayTotals);
	}

	private boolean isVariablesNotEmpty(SprayTotals sprayTotals) {
		return sprayTotals.getSprayDate() != null && sprayTotals.getVillage().getId() != null && sprayTotals.getSprayOperator().getId() != null;
	}

	private String createReference(SprayTotals sprayTotals) {
		return sprayTotals.getSprayDate().toString() + sprayTotals.getVillage().getId() + sprayTotals.getSprayOperator().getId();
	}

}
