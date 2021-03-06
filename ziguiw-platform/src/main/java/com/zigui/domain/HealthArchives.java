package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "health_archives")
@SequenceGenerator(name = "HEALTH_ARCHIVES_SEQ", sequenceName = "health_archives_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HealthArchives implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6096325009855071915L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HEALTH_ARCHIVES_SEQ")
	public long id;
	
	@Column(name = "user_id")
	public long userId;
	
	@Column(name = "blood_type")
	public String bloodType;

	@Column(name = "drug_allergy_history")
	public String drugAllergyHistory;

	@Column(name = "vision")
	public String vision;

	@Column(name = "listening_comprehension")
	public String listeningComprehension;

	@Column(name = "height")
	public String height;

	@Column(name = "weight")
	public String weight;

	@Column(name = "local_history")
	public String localHistory;

	@Column(name = "medical_history")
	public String medicalHistory;

	@Column(name = "genetic_history")
	public String geneticHistory;

	@Column(name = "reserve1")
	public String reserve1;

	@Column(name = "reserve2")
	public String reserve2;

	@Column(name = "state")
	public int state = 1;

	@Column(name = "creator_id")
	public long creatorId;

	@Column(name = "creator_nick")
	public String creatorNick;

	@Column(name = "create_time")
	public Date createTime = new Date();

	@Column(name = "last_modify_id")
	public long lastModifyId;

	@Column(name = "last_modify_nick")
	public String lastModifyNick;

	@Column(name = "last_modify_time")
	public Date lastModifyTime = new Date();;

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Transient
	private String formatedCreateDate = getFormatedStartTime();

	@Transient
	private String formatedStartTime = getFormatedStartTime();

	@Transient
	private String formatedLastModifyTime = getFormatedLastModifyTime();

	public String getFormatedStartTime() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}

	public String getFormatedLastModifyTime() {
		if(lastModifyTime != null){
			return dateFormat.format(lastModifyTime);
		}
		return "";
	}



	public String getFormatedCreateDate() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getDrugAllergyHistory() {
		return drugAllergyHistory;
	}

	public void setDrugAllergyHistory(String drugAllergyHistory) {
		this.drugAllergyHistory = drugAllergyHistory;
	}

	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getListeningComprehension() {
		return listeningComprehension;
	}

	public void setListeningComprehension(String listeningComprehension) {
		this.listeningComprehension = listeningComprehension;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getLocalHistory() {
		return localHistory;
	}

	public void setLocalHistory(String localHistory) {
		this.localHistory = localHistory;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getGeneticHistory() {
		return geneticHistory;
	}

	public void setGeneticHistory(String geneticHistory) {
		this.geneticHistory = geneticHistory;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorNick() {
		return creatorNick;
	}

	public void setCreatorNick(String creatorNick) {
		this.creatorNick = creatorNick;
	}

//	public java.util.Date getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(java.util.Date createTime) {
//		this.createTime = createTime;
//	}

	public long getLastModifyId() {
		return lastModifyId;
	}

	public void setLastModifyId(long lastModifyId) {
		this.lastModifyId = lastModifyId;
	}

	public String getLastModifyNick() {
		return lastModifyNick;
	}

	public void setLastModifyNick(String lastModifyNick) {
		this.lastModifyNick = lastModifyNick;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	

}
