package com.xavier.vectorlink.vectorlinksysapi.model;

public enum SprayStatus {
	
	SPRAYED("Sprayed"),
	NOT_SPRAYED("Not Sprayed");
	
	private String description;
	
	SprayStatus(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
}
