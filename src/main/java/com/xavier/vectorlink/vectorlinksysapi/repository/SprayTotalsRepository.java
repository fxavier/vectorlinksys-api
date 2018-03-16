package com.xavier.vectorlink.vectorlinksysapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.helper.sprayTotals.SprayTotalsRepositoryQueries;

public interface SprayTotalsRepository extends JpaRepository<SprayTotals, Long>, SprayTotalsRepositoryQueries{

}
