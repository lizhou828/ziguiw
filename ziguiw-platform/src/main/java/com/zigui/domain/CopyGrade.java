package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * create table T_stugrade(
 * Nj_id			INTEGER,
 * Nj_mcheng			varchar2(30),
 * Nj_bma			char(2),
 * Nj_ztai			INTEGER,
 * sffban			INTEGER,
 * Nj_xsming			varchar2(50),
 * XxID			varchar2(50),
 * kqRule			INTEGER
 * );
 * @author bertyang
 *
 */

@Entity
@Table(name="T_stugrade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CopyGrade {
	
	@Id
	@Column(name="Nj_id")
	private int Nj_id;
	
	@Column(name="Nj_mcheng")
	private String Nj_mcheng;
	
	@Column(name="Nj_bma")
	private String Nj_bma;
	
	@Column(name="Nj_ztai")
	private int Nj_ztai;
	
	@Column(name="sffban")
	private int sffban;
	
	@Column(name="Nj_xsming")
	private int Nj_xsming;
	
	@Column(name="XXID")
	private String XXID;
	
	@Column(name="kqRule")
	private int kqRule;

	public int getNj_id() {
		return Nj_id;
	}

	public void setNj_id(int nj_id) {
		Nj_id = nj_id;
	}

	public String getNj_mcheng() {
		return Nj_mcheng;
	}

	public void setNj_mcheng(String nj_mcheng) {
		Nj_mcheng = nj_mcheng;
	}

	public String getNj_bma() {
		return Nj_bma;
	}

	public void setNj_bma(String nj_bma) {
		Nj_bma = nj_bma;
	}

	public int getNj_ztai() {
		return Nj_ztai;
	}

	public void setNj_ztai(int nj_ztai) {
		Nj_ztai = nj_ztai;
	}

	public int getSffban() {
		return sffban;
	}

	public void setSffban(int sffban) {
		this.sffban = sffban;
	}

	public int getNj_xsming() {
		return Nj_xsming;
	}

	public void setNj_xsming(int nj_xsming) {
		Nj_xsming = nj_xsming;
	}

	public String getXXID() {
		return XXID;
	}

	public void setXXID(String xXID) {
		XXID = xXID;
	}

	public int getKqRule() {
		return kqRule;
	}

	public void setKqRule(int kqRule) {
		this.kqRule = kqRule;
	}

	
	
}
