package com.xavier.vectorlink.vectorlinksysapi.model;

public enum PhysicalVerification {
	YES("SIM"),
	NO("Nao");
	
	private String description;
	
	PhysicalVerification(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	

}
