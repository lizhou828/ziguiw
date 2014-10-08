package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "school_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SchoolInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="school_id")
	private long schoolId;
	
	private String summary;
	
	private String contact;
	
	@Column(name="master_contact")
	private String masterContact;
	
	private String speech;
	
	private String job;


	public long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMasterContact() {
		return masterContact;
	}

	public void setMasterContact(String masterContact) {
		this.masterContact = masterContact;
	}

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	

}
