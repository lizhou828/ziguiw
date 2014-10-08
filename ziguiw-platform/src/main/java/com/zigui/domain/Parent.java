package com.zigui.domain;

import com.zigui.dao.UserBaseDao;
import com.zigui.utils.BeanFactoryUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * create table T_PARENTINFO(
 * PARENTS_ID		INTEGER,
 * Xs_id		INTEGER,
 * PARENTS_NAME			varchar2(30),
 * birthday			DATE,
 * officePhone			varchar2(20),
 * mobliephone			varchar2(20),
 * email			varchar2(50),
 * PARENTS_CALLER			varchar2(20),
 * Professional			INTEGER,
 * workaddress			varchar2(120),
 * remarks			varchar2(100)
);
 * @author bertyang
 *
 *]              
 */
@Entity
@Table(name="T_PARENTINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Parent{
	
	private static final long serialVersionUID = 4543898174145500928L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int PARENTS_ID;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
	@JoinColumn(name = "Xs_id")
	private Student student;
	private String PARENTS_NAME;
	private Date birthday;
	private String officePhone;
	private String mobliephone;
	private String PARENTS_CALLER;
	private String Professional;
	private String workaddress;
	private String remarks;
	public int getPARENTS_ID() {
		return PARENTS_ID;
	}
	public void setPARENTS_ID(int pARENTS_ID) {
		PARENTS_ID = pARENTS_ID;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getPARENTS_NAME() {
		return PARENTS_NAME;
	}
	public void setPARENTS_NAME(String pARENTS_NAME) {
		PARENTS_NAME = pARENTS_NAME;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

    public String getMobliephone() {
        return mobliephone;
    }

    public void setMobliephone(String mobliephone) {
        this.mobliephone = mobliephone;
    }

    public String getPARENTS_CALLER() {
		return PARENTS_CALLER;
	}
	public void setPARENTS_CALLER(String pARENTS_CALLER) {
		PARENTS_CALLER = pARENTS_CALLER;
	}
	public String getProfessional() {
		return Professional;
	}
	public void setProfessional(String professional) {
		Professional = professional;
	}
	public String getWorkaddress() {
		return workaddress;
	}
	public void setWorkaddress(String workaddress) {
		this.workaddress = workaddress;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public UserBase getUser() {
		UserBaseDao userBaseDao = (UserBaseDao) BeanFactoryUtils.getBean("userBaseDao");
		System.out.println(userBaseDao);
		return (UserBase)userBaseDao.find("from UserBase where type = 3 and foreignKey = " + PARENTS_ID, new Object[0]).get(0); // or however you load the Bar
//		return user;
	}
	@Override
	public String toString() {
		return "Parent [PARENTS_ID=" + PARENTS_ID + ", student=" + student
				+ ", PARENTS_NAME=" + PARENTS_NAME + ", birthday=" + birthday
				+ ", officePhone=" + officePhone + ", mobliephone="
				+ mobliephone + ", PARENTS_CALLER=" + PARENTS_CALLER
				+ ", Professional=" + Professional + ", workaddress="
				+ workaddress + ", remarks=" + remarks + "]";
	}

	

}
