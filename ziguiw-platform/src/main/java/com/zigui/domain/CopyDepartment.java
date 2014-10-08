package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * create table T_SCHOOLDEPT(
 * Bm_ID NUMBER(9),
 * Bm_mcheng varchar2(50),
 * PBm_id NUMBER(9),
 * XxID varchar2(50) default '-1'
 * );
 * 
 * @author bertyang
 *
 */

@Entity
@Table(name="T_SCHOOLDEPT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CopyDepartment implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Bm_ID")
	private int Bm_ID;
	
	@Column(name="Bm_mcheng")
	private String Bm_mcheng;
	
	@Column(name="PBm_id")
	private int PBm_id;
	
	@Column(name="XxID")
	private String XxID;

	public int getBm_ID() {
		return Bm_ID;
	}

	public void setBm_ID(int bm_ID) {
		Bm_ID = bm_ID;
	}

	public String getBm_mcheng() {
		return Bm_mcheng;
	}

	public void setBm_mcheng(String bm_mcheng) {
		Bm_mcheng = bm_mcheng;
	}

	public int getPBm_id() {
		return PBm_id;
	}

	public void setPBm_id(int pBm_id) {
		PBm_id = pBm_id;
	}

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		XxID = xxID;
	}

	
}
