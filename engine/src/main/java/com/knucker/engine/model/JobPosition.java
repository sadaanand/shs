package com.knucker.engine.model;

public class JobPosition {

	private String positionName;
	private String requiredExperince;
	private String coreSkills;
	private String subSkills;
	private String description;
	private String contactPerson;

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setRequiredExperience(String requiredExperince) {
		this.requiredExperince = requiredExperince;
	}

	public void setCoreSkills(String coreSkills) {
		this.coreSkills = coreSkills;
	}

	public void setSubSkills(String subSkills) {
		this.subSkills = subSkills;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	public String getPositionName() {
		return positionName;
	}

	public String getRequiredExperience() {
		return requiredExperince;
	}

	public String getcoreSkills() {
		return coreSkills;
	}
	
	public String getsubSkills() {
		return subSkills;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getContactPerson() {
		return contactPerson;
	}
}
