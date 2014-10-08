package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * create table SCHOOL_NOTICE_TYPE(
 * Type_ID NUMBER(9),
 * Type_Name varchar2(50),
 * XxID varchar2(20)
 * );
 * @author bertyang
 *
 */

@Entity
@Table(name="SCHOOL_NOTICE_TYPE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NoticeType {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Type_ID")
	private int Type_ID;
	
	@Column(name="Type_Name")
	private String Type_Name;
	
	@Column(name="XxID")
	private String XxID;

	public int getType_ID() {
		return Type_ID;
	}

	public void setType_ID(int type_ID) {
		Type_ID = type_ID;
	}

	public String getType_Name() {
		return Type_Name;
	}

	public void setType_Name(String type_Name) {
		Type_Name = type_Name;
	}

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		XxID = xxID;
	}

	
}
