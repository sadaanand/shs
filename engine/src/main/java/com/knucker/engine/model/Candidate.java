package com.knucker.engine.model;

import java.util.List;

public class Candidate {

	private String name;
	private String dob;
	private String coreSkills;
	private List<String> subSkills;
	private String resume_Id;
	private String phone_number;
	private String emailId;
	private String source;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCoreSkills() {
		return coreSkills;
	}

	public void setCoreSkills(String coreSkills) {
		this.coreSkills = coreSkills;
	}

	public List<String> getSubSkills() {
		return subSkills;
	}

	public void setSubSkills(List<String> subSkills) {
		this.subSkills = subSkills;
	}

	public String getResume_Id() {
		return resume_Id;
	}

	public void setResume_Id(String resume_Id) {
		this.resume_Id = resume_Id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
