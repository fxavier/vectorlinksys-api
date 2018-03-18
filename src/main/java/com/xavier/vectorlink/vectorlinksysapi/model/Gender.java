package com.xavier.vectorlink.vectorlinksysapi.model;

public enum Gender {
	FEMALE("Female"),
	MALE("Male");
	
	private String description;

	Gender(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	

}
