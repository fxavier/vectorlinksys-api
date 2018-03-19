package com.xavier.vectorlink.vectorlinksysapi.repository.filter;

import java.time.LocalDate;

public class MobilizationDetailsFilter {
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private String villageName;
	
	private String mobCode;

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

	public String getMobCode() {
		return mobCode;
	}

	public void setMobCode(String mobCode) {
		this.mobCode = mobCode;
	}
	
	

}
