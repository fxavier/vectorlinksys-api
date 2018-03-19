package com.xavier.vectorlink.vectorlinksysapi.repository.helper.mobilization.details;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationDetails;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.MobilizationDetailsFilter;

public interface MobilizationDetailsRepositoryQueries {

	Page<MobilizationDetails> filter(MobilizationDetailsFilter mobilizationdetailsFilter, Pageable pageable);
}
