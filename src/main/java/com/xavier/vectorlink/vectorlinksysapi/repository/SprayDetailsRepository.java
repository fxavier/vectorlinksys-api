package com.xavier.vectorlink.vectorlinksysapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayDetails;
import com.xavier.vectorlink.vectorlinksysapi.repository.helper.spraydetails.SprayDetailsRepositoryQueries;

public interface SprayDetailsRepository extends JpaRepository<SprayDetails, Long>, SprayDetailsRepositoryQueries{

}
