package com.xavier.vectorlink.vectorlinksysapi.model;

public enum ActorType {
	BS("Brigade Supervisor"),
	MOB("Mobilizer"),
	SOP("Spray Operator"),
	TL("Team Leader");
	
	private String description;
	
	ActorType(String description){
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	

}
