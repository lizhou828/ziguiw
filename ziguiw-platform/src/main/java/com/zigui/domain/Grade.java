package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

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
public class Grade implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="Nj_id")
	private Integer Nj_id;
	
	@Column(name="Nj_mcheng")
	private String Nj_mcheng;
	
	@Column(name="Nj_bma")
	private String Nj_bma;
	
	@Column(name="Nj_ztai")
	private Integer Nj_ztai;
	
	@Column(name="sffban")
	private Integer sffban;
	
	@Column(name="Nj_xsming")
	private Integer Nj_xsming;
	
	@Column(name="XXID")
	private String XXID;
	
	@Column(name="kqRule")
	private  Integer kqRule;

    public Integer getNj_id() {
        return Nj_id;
    }

    public void setNj_id(Integer nj_id) {
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

    public Integer getNj_ztai() {
        return Nj_ztai;
    }

    public void setNj_ztai(Integer nj_ztai) {
        Nj_ztai = nj_ztai;
    }

    public Integer getSffban() {
        return sffban;
    }

    public void setSffban(Integer sffban) {
        this.sffban = sffban;
    }

    public Integer getNj_xsming() {
        return Nj_xsming;
    }

    public void setNj_xsming(Integer nj_xsming) {
        Nj_xsming = nj_xsming;
    }

    public String getXXID() {
        return XXID;
    }

    public void setXXID(String XXID) {
        this.XXID = XXID;
    }

    public Integer getKqRule() {
        return kqRule;
    }

    public void setKqRule(Integer kqRule) {
        this.kqRule = kqRule;
    }

    @Override
    public String toString() {
        return "Grade[Nj_mcheng="+Nj_mcheng+
                ",Nj_id="+Nj_id+",Nj_bma="+Nj_bma+
                ",Nj_xsming="+Nj_xsming+",Nj_xsming="+Nj_xsming+
                ",XXID="+XXID+",kqRule="+kqRule+",sffban="+sffban;
    }
}
