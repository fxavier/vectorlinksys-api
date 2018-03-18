package com.xavier.vectorlink.vectorlinksysapi.repository.filter;

import java.time.LocalDate;

public class SprayDetailsFilter {
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String villageName;
	
	private String houseHoldId;
	
	private Long sopCode;

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getHouseHoldId() {
		return houseHoldId;
	}

	public void setHouseHoldId(String houseHoldId) {
		this.houseHoldId = houseHoldId;
	}

	public Long getSopCode() {
		return sopCode;
	}

	public void setSopCode(Long sopCode) {
		this.sopCode = sopCode;
	}
	
	

}
