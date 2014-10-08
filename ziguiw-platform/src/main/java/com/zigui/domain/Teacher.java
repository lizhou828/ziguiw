package com.zigui.domain;

import com.zigui.dao.UserBaseDao;
import com.zigui.utils.BeanFactoryUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
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
public class Teacher implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="teacherID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long teacherID;
	
	private String name;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name = "XxID", referencedColumnName="XxID")
	private School school;
	
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

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
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

	public UserBase getUser() {
		UserBaseDao userBaseDao = (UserBaseDao) BeanFactoryUtils.getBean("userBaseDao");
		System.out.println(userBaseDao);
		return (UserBase)userBaseDao.find("from UserBase where type = 2 and foreignKey = " + teacherID, new Object[0]).get(0); // or however you load the Bar
//		return user;
	}

}
