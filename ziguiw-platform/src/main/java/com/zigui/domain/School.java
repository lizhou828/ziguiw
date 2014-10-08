package com.zigui.domain;

import com.zigui.dao.UserBaseDao;
import com.zigui.utils.BeanFactoryUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * create table T_SCHOOLINFO(
 * xx_ID INTEGER,
 * XxID varchar2(50),
 * sch_name varchar2(100)
 * linkman varchar2(50),
 * contactphone varchar2(50),
 * AGENT_ID INTEGER,
 * dbname varchar2(50),
 * Connstr varchar2(200),
 * ProId INTEGER,
 * CityId INTEGER,
 * reamrks varchar2(200)
 * );
 * 
 * @author bertyang
 */
@Entity
@Table(name="T_SCHOOLINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class School implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="xx_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long xx_ID;

    @Column(name="XXID")
    private String XxID;
	
	@Column(name="sch_name")
	private String sch_name;
	
	@Column(name="linkman")
	private String linkman;
	
	@Column(name="contactphone")
	private String contactphone;
	
	@Column(name="ProId")
	private Integer ProId;
	
	@Column(name="CityId")
	private Integer CityId;
	
	private String reamrks;
	
	@Transient
	private UserBase user;

	public long getXx_ID() {
		return xx_ID;
	}

	public void setXx_ID(long xx_ID) {
		this.xx_ID = xx_ID;
		
		if(StringUtils.isEmpty(this.getXxID())){
			this.XxID = "" + xx_ID;
		}
	}

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		if(StringUtils.isEmpty(xxID)){
			this.XxID = xxID;
		}
	}

	public String getSch_name() {
		return sch_name;
	}

	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getContactphone() {
		return contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public Integer getProId() {
		return ProId;
	}

	public void setProId(Integer proId) {
		ProId = proId;
	}

	public Integer getCityId() {
		return CityId;
	}

	public void setCityId(Integer cityId) {
		CityId = cityId;
	}

	public String getReamrks() {
		return reamrks;
	}

	public void setReamrks(String reamrks) {
		this.reamrks = reamrks;
	}
	
	@PostLoad
	public void postLoad() { 
		
	}

	public UserBase getUser() {
		UserBaseDao userBaseDao = (UserBaseDao) BeanFactoryUtils.getBean("userBaseDao");
		System.out.println(userBaseDao);
		user = (UserBase)userBaseDao.find("from UserBase where type = 1 and foreignKey = " + xx_ID, new Object[0]).get(0); // or however you load the Bar
		return user;
	}

	public void setUser(UserBase user) {
		this.user = user;
	} 
	
	
	
}
