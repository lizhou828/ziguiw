package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * create table T_SCHOOLINFO(
 * xx_ID INTEGER,
 * XxID varchar2(50),
 * sch_name varchar2(100)
 * linkman varchar2(50),
 * contactphone varchar2(50),
 * AGENT_ID INTEGER,
 * dbname varchar2(50),
 * Connstr varchar2(200),
 * ProId INTEGER,
 * CityId INTEGER,
 * reamrks varchar2(200)
 * );
 * 
 * @author bertyang
 */
@Entity
@Table(name="T_SCHOOLINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CopySchool implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="xx_ID")
	private long xx_ID;
	
	private String XxID;
	
	@Column(name="sch_name")
	private String sch_name;
	
	@Column(name="linkman")
	private String linkman;
	
	@Column(name="contactphone")
	private String contactphone;
	
	@Column(name="ProId")
	private int ProId;
	
	@Column(name="CityId")
	private int CityId;
	
	private String reamrks;

	public long getXx_ID() {
		return xx_ID;
	}

	public void setXx_ID(long xx_ID) {
		this.xx_ID = xx_ID;
	}

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		XxID = xxID;
	}

	public String getSch_name() {
		return sch_name;
	}

	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getContactphone() {
		return contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public int getProId() {
		return ProId;
	}

	public void setProId(int proId) {
		ProId = proId;
	}

	public int getCityId() {
		return CityId;
	}

	public void setCityId(int cityId) {
		CityId = cityId;
	}

	public String getReamrks() {
		return reamrks;
	}

	public void setReamrks(String reamrks) {
		this.reamrks = reamrks;
	}
	
}
