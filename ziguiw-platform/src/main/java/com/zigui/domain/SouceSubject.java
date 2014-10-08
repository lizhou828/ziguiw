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
 * AbstractSouceSubject entity provides the base persistence definition of the
 * SouceSubject entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="sourceSubject")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SouceSubject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	private String subjectId;
	private String subjectName;
	private String subjectCode;
	private String xxid;
	private String isCommonUse;
	private String sign1;
	private String sign2;
	private Integer seat;
	
	@OneToMany(mappedBy="souceSubject")
	private Set<CcczgInfoSouce> ccczgInfoSouces = new HashSet<CcczgInfoSouce>(0);

	// Constructors

	/** default constructor */
	public SouceSubject() {
	}

	/** minimal constructor */
	public SouceSubject(String subjectName) {
		this.subjectName = subjectName;
	}

	/** full constructor */
	
	
	// Property accessors

	public SouceSubject(String subjectId, String subjectName,
			String subjectCode, String xxid, String isCommonUse, String sign1,
			String sign2, Integer seat, Set ccczgInfoSouces) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
		this.xxid = xxid;
		this.isCommonUse = isCommonUse;
		this.sign1 = sign1;
		this.sign2 = sign2;
		this.seat = seat;
		this.ccczgInfoSouces = ccczgInfoSouces;
	}
	public String getSubjectId() {
		return this.subjectId;
	}


	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getXxid() {
		return this.xxid;
	}

	public void setXxid(String xxid) {
		this.xxid = xxid;
	}

	public String getIsCommonUse() {
		return this.isCommonUse;
	}

	public void setIsCommonUse(String isCommonUse) {
		this.isCommonUse = isCommonUse;
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

	public void setCcczgInfoSouces(Set ccczgInfoSouces) {
		this.ccczgInfoSouces = ccczgInfoSouces;
	}

	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}

}