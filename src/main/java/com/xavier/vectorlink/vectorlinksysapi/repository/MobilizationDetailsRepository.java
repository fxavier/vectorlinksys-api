package com.xavier.vectorlink.vectorlinksysapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationDetails;
import com.xavier.vectorlink.vectorlinksysapi.repository.helper.mobilization.details.MobilizationDetailsRepositoryQueries;

public interface MobilizationDetailsRepository extends JpaRepository<MobilizationDetails, Long>, MobilizationDetailsRepositoryQueries{

}
