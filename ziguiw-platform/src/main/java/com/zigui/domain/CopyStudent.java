package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * create table T_student(
 * Xs_id		integer,
 * Xj_bhao			varchar(30),
 * Xs_xming			varchar(30),
 * Birthplace			VARCHAR(50),
 * PoliticalFace			VARCHAR2(20),
 * IDCard			VARCHAR2(18),
 * AccountPlace			VARCHAR2(100),
 * sex			CHAR(2),
 * birthday			DATE,
 * Bj_ID		integer,
 * Accommodation			CHAR(1),
 * DateintegeroSch			DATE,
 * mzhu			varchar2(10),
 * Hobby			varchar2(300),
 * personalphoto			varchar2(50),
 * homephone			varchar2(50),
 * zip			varchar2(6),
 * homeaddress			varchar2(100),
 * otherlinks			varchar2(100),
 * Healthstate			varchar2(2000),
 * XxID		varchar2(50),
 * groupID		integer,
 * banGanBuID			varchar2(10),
 * ybhao			varchar2(50)
 * )
 * @author bertyang
 *
 */

@Entity
@Table(name="T_student")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CopyStudent implements Serializable{
	
	@Id
	private int Xs_id;
	private String Xs_xming;
	private String Xj_bhao;
	private String Birthplace;
	private String PoliticalFace;
	private String IDCard;
	private String AccountPlace;
	private Date birthday;
	private String Bj_ID;
	private String Accommodation;
//	private String DateIntoSch;
	private String mzhu;
	private String Hobby;
	private String homephone;
	private String zip;
	private String homeaddress;
	private String otherlinks;
	private String Healthstate;
	private Long XxID;
	private Long groupID;
	private String banGanBuID;
	
	public int getXs_id() {
		return Xs_id;
	}
	public void setXs_id(int xs_id) {
		Xs_id = xs_id;
	}
	public String getXs_xming() {
		return Xs_xming;
	}
	public void setXs_xming(String xs_xming) {
		Xs_xming = xs_xming;
	}
	public String getXj_bhao() {
		return Xj_bhao;
	}
	public void setXj_bhao(String xj_bhao) {
		Xj_bhao = xj_bhao;
	}
	public String getBirthplace() {
		return Birthplace;
	}
	public void setBirthplace(String birthplace) {
		Birthplace = birthplace;
	}
	public String getPoliticalFace() {
		return PoliticalFace;
	}
	public void setPoliticalFace(String politicalFace) {
		PoliticalFace = politicalFace;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getAccountPlace() {
		return AccountPlace;
	}
	public void setAccountPlace(String accountPlace) {
		AccountPlace = accountPlace;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getBj_ID() {
		return Bj_ID;
	}
	public void setBj_ID(String bj_ID) {
		Bj_ID = bj_ID;
	}
	public String getAccommodation() {
		return Accommodation;
	}
	public void setAccommodation(String accommodation) {
		Accommodation = accommodation;
	}
//	public String getDateIntoSch() {
//		return DateIntoSch;
//	}
//	public void setDateIntoSch(String dateIntoSch) {
//		DateIntoSch = dateIntoSch;
//	}
	public String getMzhu() {
		return mzhu;
	}
	public void setMzhu(String mzhu) {
		this.mzhu = mzhu;
	}
	public String getHobby() {
		return Hobby;
	}
	public void setHobby(String hobby) {
		Hobby = hobby;
	}
	public String getHomephone() {
		return homephone;
	}
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getHomeaddress() {
		return homeaddress;
	}
	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}
	public String getOtherlinks() {
		return otherlinks;
	}
	public void setOtherlinks(String otherlinks) {
		this.otherlinks = otherlinks;
	}
	public String getHealthstate() {
		return Healthstate;
	}
	public void setHealthstate(String healthstate) {
		Healthstate = healthstate;
	}
	
	public Long getXxID() {
		return XxID;
	}
	public void setXxID(Long xxID) {
		XxID = xxID;
	}
	
	public Long getGroupID() {
		return groupID;
	}
	public void setGroupID(Long groupID) {
		this.groupID = groupID;
	}
	public String getBanGanBuID() {
		return banGanBuID;
	}
	public void setBanGanBuID(String banGanBuID) {
		this.banGanBuID = banGanBuID;
	}

}
