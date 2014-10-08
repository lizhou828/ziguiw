package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * create table T_subject(
 * SUBJECT_id		INTEGER,
 * SUBJECT_NAME			varchar2(50),
 * SUBJECT_CODE			varchar2(5),
 * XxID		varchar2(50)	
 * );
 * @author bertyang
 *
 */

@Entity
@Table(name="T_subject")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CopySubject implements Serializable{
	
	@Id
	@Column(name="SUBJECT_id")
	private long SUBJECT_id;
	
	@Column(name="SUBJECT_NAME")
	private String SUBJECT_NAME;
	
	@Column(name="SUBJECT_CODE")
	private String SUBJECT_CODE;
	
	@Column(name="XxID")
	private String XxID;

	public long getSUBJECT_id() {
		return SUBJECT_id;
	}

	public void setSUBJECT_id(long sUBJECT_id) {
		SUBJECT_id = sUBJECT_id;
	}

	public String getSUBJECT_NAME() {
		return SUBJECT_NAME;
	}

	public void setSUBJECT_NAME(String sUBJECT_NAME) {
		SUBJECT_NAME = sUBJECT_NAME;
	}

	public String getSUBJECT_CODE() {
		return SUBJECT_CODE;
	}

	public void setSUBJECT_CODE(String sUBJECT_CODE) {
		SUBJECT_CODE = sUBJECT_CODE;
	}

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		XxID = xxID;
	}

	
}
