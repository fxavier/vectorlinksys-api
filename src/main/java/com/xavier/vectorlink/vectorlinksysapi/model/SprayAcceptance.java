package com.xavier.vectorlink.vectorlinksysapi.model;

public enum SprayAcceptance {
	
	YES("Sim"),
	NO("Nao");
	
	private String description;
	
	SprayAcceptance(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	

}
