package com.xavier.vectorlink.vectorlinksysapi.repository.helper.sprayTotals;

import java.util.List;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.SprayTotalsFilter;

public interface SprayTotalsRepositoryQueries {
	
	public List<SprayTotals> filter(SprayTotalsFilter sprayTotalsFilter);

}
