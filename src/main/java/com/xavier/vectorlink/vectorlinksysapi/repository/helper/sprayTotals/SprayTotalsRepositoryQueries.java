package com.xavier.vectorlink.vectorlinksysapi.repository.helper.sprayTotals;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.SprayTotalsFilter;

public interface SprayTotalsRepositoryQueries {
	
	public Page<SprayTotals> filter(SprayTotalsFilter sprayTotalsFilter, Pageable pageable);

}
