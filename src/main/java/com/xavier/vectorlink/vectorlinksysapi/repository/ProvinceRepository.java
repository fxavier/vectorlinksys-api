package com.xavier.vectorlink.vectorlinksysapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xavier.vectorlink.vectorlinksysapi.model.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{

}
