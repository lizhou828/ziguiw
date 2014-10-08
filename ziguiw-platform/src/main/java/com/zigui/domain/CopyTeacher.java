package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * create table T_TEACHERINFO(
 * teacherID INTEGER,
 * user_id INTEGER,
 * Bm_ID INTEGER,
 * name varchar2(30),
 * sex INTEGER,
 * birthday varchar2(50),
 * jobtilte INTEGER,
 * Education INTEGER,
 * graduatesch varchar2(100),
 * duties varchar2(100),
 * resume varchar2(3000),
 * photo varchar2(50),
 * officephone varchar2(50),
 * fax varchar2(50),
 * homephone varchar2(50),
 * moblie varchar2(20),
 * email varchar2(50),
 * zipcode varchar2(6),
 * address varchar2(100),
 * otherlinks varchar2(100),
 * XxID varchar2(50)
 * );
 * @author bertyang
 *
 */
@Entity
@Table(name="T_TEACHERINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CopyTeacher implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="teacherID")
	private long teacherID;
	
	private String name;
	
	@Column(name="XxID")
	private String XxID;
	
	private Date birthday;
	
	/**职级*/
	@Column(name="jobtilte")
	private String jobtilte;
	
	/**毕业院校*/
	@Column(name="Education")
	private String Education;
	
	/**毕业院校*/
	@Column(name="graduatesch")
	private String graduatesch;
	
	/**职务*/
	@Column(name="duties")
	private String duties;
	
	/**简历*/
	@Column(name="resume")
	private String resume;
	
	/**办公电话*/
	@Column(name="officephone")
	private String officephone;
	
	/**传真*/
	@Column(name="fax")
	private String fax;
	
	/**家庭电话*/
	@Column(name="homephone")
	private String homephone;
	
	/**手机*/
	@Column(name="moblie")
	private String moblie;
	
	/**邮编*/
	@Column(name="zipcode")
	private String zipcode;
	
	/**地址*/
	@Column(name="address")
	private String address;

	public long getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(long teacherID) {
		this.teacherID = teacherID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		XxID = xxID;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getJobtilte() {
		return jobtilte;
	}

	public void setJobtilte(String jobtilte) {
		this.jobtilte = jobtilte;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	public String getGraduatesch() {
		return graduatesch;
	}

	public void setGraduatesch(String graduatesch) {
		this.graduatesch = graduatesch;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

}
