package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * create table T_classes(
 * Bj_id			number	,
 * Nj_id			varchar(10)	,
 * Bj_mcheng		integer,
 * Bj_bma			integer,
 * Bj_ztai			integer,
 * XxID			integer
);
 * @author bertyang
 *
 */

@Entity
@Table(name="T_classes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CopyClasz implements Serializable{
	
	@Id
	@Column(name="Bj_id")
	private long Bj_id;
	
	@Column(name="Bj_mcheng")
	private String Bj_mcheng;
	
	@Column(name="Nj_id")
	private String Nj_id;
	
	@Column(name="Bj_bma")
	private int Bj_bma;
	
	@Column(name="Bj_ztai")
	private int Bj_ztai;
	
	@Column(name="XxID")
	private int XxID;

	public long getBj_id() {
		return Bj_id;
	}

	public void setBj_id(long bj_id) {
		Bj_id = bj_id;
	}

	public String getBj_mcheng() {
		return Bj_mcheng;
	}

	public void setBj_mcheng(String bj_mcheng) {
		Bj_mcheng = bj_mcheng;
	}

	public String getNj_id() {
		return Nj_id;
	}

	public void setNj_id(String nj_id) {
		Nj_id = nj_id;
	}

	public int getBj_bma() {
		return Bj_bma;
	}

	public void setBj_bma(int bj_bma) {
		Bj_bma = bj_bma;
	}

	public int getBj_ztai() {
		return Bj_ztai;
	}

	public void setBj_ztai(int bj_ztai) {
		Bj_ztai = bj_ztai;
	}

	public int getXxID() {
		return XxID;
	}

	public void setXxID(int xxID) {
		XxID = xxID;
	}

	
	

}
