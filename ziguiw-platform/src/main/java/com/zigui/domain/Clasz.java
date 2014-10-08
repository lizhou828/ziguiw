package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
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
public class Clasz implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Bj_id")
	private Long Bj_id;
	
	@Column(name="Bj_mcheng")
	private String Bj_mcheng;
	
	@Column(name="Nj_id")
	private Long Nj_id;
	
	@Column(name="Bj_bma")
	private Integer Bj_bma;
	
	@Column(name="Bj_ztai")
	private int Bj_ztai;
	
	@Column(name="XxID")
	private String XxID;

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

	public Integer getNj_id() {
		if (Nj_id == null) return 0;
        return Nj_id.intValue();
	}

	public void setNj_id(Long nj_id) {
		Nj_id = nj_id;
	}

	public int getBj_bma() {
        if(Bj_bma == null ){
            return 0;
        }
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

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		XxID = xxID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clasz other = (Clasz) obj;
		if (Bj_id != other.Bj_id)
			return false;
		if (XxID != other.XxID)
			return false;
		return true;
	}

	
	

}
