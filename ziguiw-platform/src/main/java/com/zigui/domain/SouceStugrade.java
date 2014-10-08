package com.zigui.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractSouceStugrade entity provides the base persistence definition of the
 * SouceStugrade entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sourceStugrade")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SouceStugrade implements java.io.Serializable {

	// Fields
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	private String njId;
	
	private String njMcheng;
	private String njBma;
	private Long njZtai;
	private Long sffban;
	private String njXsming;
	private String xxid;
	private Long kqrule;
	private String sign1;
	private String sign2;
	private Integer seat;
	private Integer leve;//1:小学 2:初中 3:高中
	
	@OneToMany(mappedBy="souceStugrade")
	private Set<CcczgInfoSouce> ccczgInfoSouces = new HashSet<CcczgInfoSouce>();

	// Constructors

	public Integer getLeve() {
		return leve;
	}

	public void setLeve(Integer leve) {
		this.leve = leve;
	}
	

	/** default constructor */
	public SouceStugrade() {
	}

	/** minimal constructor */
	public SouceStugrade(String njMcheng, Long njZtai, Long sffban) {
		this.njMcheng = njMcheng;
		this.njZtai = njZtai;
		this.sffban = sffban;
	}

	/** full constructor */
	public SouceStugrade(String njMcheng, String njBma, Long njZtai,
			Long sffban, String njXsming, String xxid, Long kqrule,
			String sign1, String sign2, Set<CcczgInfoSouce> ccczgInfoSouces) {
		this.njMcheng = njMcheng;
		this.njBma = njBma;
		this.njZtai = njZtai;
		this.sffban = sffban;
		this.njXsming = njXsming;
		this.xxid = xxid;
		this.kqrule = kqrule;
		this.sign1 = sign1;
		this.sign2 = sign2;
		this.ccczgInfoSouces = ccczgInfoSouces;
	}

	// Property accessors

	public String getNjId() {
		return this.njId;
	}

	public void setNjId(String njId) {
		this.njId = njId;
	}

	

	public String getNjMcheng() {
		return njMcheng;
	}

	public void setCcczgInfoSouces(Set<CcczgInfoSouce> ccczgInfoSouces) {
		this.ccczgInfoSouces = ccczgInfoSouces;
	}

	public void setNjMcheng(String njMcheng) {
		this.njMcheng = njMcheng;
	}

	public String getNjBma() {
		return this.njBma;
	}

	public void setNjBma(String njBma) {
		this.njBma = njBma;
	}

	public Long getNjZtai() {
		return this.njZtai;
	}

	public void setNjZtai(Long njZtai) {
		this.njZtai = njZtai;
	}

	public Long getSffban() {
		return this.sffban;
	}

	public void setSffban(Long sffban) {
		this.sffban = sffban;
	}

	public String getNjXsming() {
		return this.njXsming;
	}

	public void setNjXsming(String njXsming) {
		this.njXsming = njXsming;
	}

	public String getXxid() {
		return this.xxid;
	}

	public void setXxid(String xxid) {
		this.xxid = xxid;
	}

	public Long getKqrule() {
		return this.kqrule;
	}

	public void setKqrule(Long kqrule) {
		this.kqrule = kqrule;
	}

	public String getSign1() {
		return this.sign1;
	}

	public void setSign1(String sign1) {
		this.sign1 = sign1;
	}

	public String getSign2() {
		return this.sign2;
	}

	public void setSign2(String sign2) {
		this.sign2 = sign2;
	}

	public Set getCcczgInfoSouces() {
		return this.ccczgInfoSouces;
	}


	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

}