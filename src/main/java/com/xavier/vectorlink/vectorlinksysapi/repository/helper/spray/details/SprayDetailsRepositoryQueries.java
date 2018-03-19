package com.xavier.vectorlink.vectorlinksysapi.repository.helper.spray.details;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayDetails;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.SprayDetailsFilter;

public interface SprayDetailsRepositoryQueries {
	
	public Page<SprayDetails> filter(SprayDetailsFilter sprayDetailsFilter, Pageable pageable);

}
