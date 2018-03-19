package com.xavier.vectorlink.vectorlinksysapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.helper.mobilization.totals.MobilizationTotalsRepositoryQueries;

public interface MobilizationTotalsRepository extends JpaRepository<MobilizationTotals, Long>, MobilizationTotalsRepositoryQueries{

}
