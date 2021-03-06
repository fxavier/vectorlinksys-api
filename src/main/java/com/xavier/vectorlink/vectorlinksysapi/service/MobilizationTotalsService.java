package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.MobilizationTotalsRepository;

@Service
public class MobilizationTotalsService {
	
	@Autowired
	private MobilizationTotalsRepository mobilizationTotalsRepository;
	
	
	public MobilizationTotals save(MobilizationTotals mobilizationTotals) {
		if(isVariableNotEmpty(mobilizationTotals)) {
			mobilizationTotals.setReference(createReference(mobilizationTotals));
		}
		return mobilizationTotalsRepository.save(mobilizationTotals);
	}

	
	public MobilizationTotals update(Long id, MobilizationTotals mobilizationTotals) {
		MobilizationTotals savedMobTotals = mobilizationTotalsRepository.getOne(id);
		if(savedMobTotals == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(mobilizationTotals, savedMobTotals, "id");
		return mobilizationTotalsRepository.save(savedMobTotals);
	}

	private String createReference(MobilizationTotals mobilizationTotals) {
		return mobilizationTotals.getMobDate().toString() + mobilizationTotals.getVillage().getId() + mobilizationTotals.getMobilizer().getId();
	}
	
	private boolean isVariableNotEmpty(MobilizationTotals mobilizationTotals) {
		return mobilizationTotals.getMobDate() != null && mobilizationTotals.getVillage().getId() != null && mobilizationTotals.getMobilizer() != null;
	}
}
