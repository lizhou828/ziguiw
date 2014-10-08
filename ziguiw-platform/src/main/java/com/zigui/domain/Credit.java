package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * create table T_fzszhi( Fz_id INTEGER, Fz_mcheng INTEGER, Km_id INTEGER, ZxFen
 * NUMBER, ZdFen NUMBER, NjStr varchar2(100), XXID varchar2(50) );
 * 
 * @author bertyang
 * 
 */
@Entity
@Table(name="T_fzszhi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Credit implements Serializable {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Fz_id")
	private int Fz_id;
	
	@Column(name="Fz_mcheng")
	private int Fz_mcheng;
	
	@Column(name="Km_id")
	private int Km_id;
	
	@Column(name="ZxFen")
	private int ZxFen;
	
	@Column(name="ZdFen")
	private int ZdFen;
	
	@Column(name="XXID")
	private String XXID;

	public int getFz_id() {
		return Fz_id;
	}

	public void setFz_id(int fz_id) {
		Fz_id = fz_id;
	}

	public int getFz_mcheng() {
		return Fz_mcheng;
	}

	public void setFz_mcheng(int fz_mcheng) {
		Fz_mcheng = fz_mcheng;
	}

	public int getKm_id() {
		return Km_id;
	}

	public void setKm_id(int km_id) {
		Km_id = km_id;
	}

	public int getZxFen() {
		return ZxFen;
	}

	public void setZxFen(int zxFen) {
		ZxFen = zxFen;
	}

	public int getZdFen() {
		return ZdFen;
	}

	public void setZdFen(int zdFen) {
		ZdFen = zdFen;
	}

	public String getXXID() {
		return XXID;
	}

	public void setXXID(String xXID) {
		XXID = xXID;
	}

	
}
