package com.xavier.vectorlink.vectorlinksysapi.repository.helper.mobilization.totals;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.MobilizationTotalsFilter;

public interface MobilizationTotalsRepositoryQueries {
	
	public Page<MobilizationTotals> filter(MobilizationTotalsFilter mobilizationFilter, Pageable pageable);

}
