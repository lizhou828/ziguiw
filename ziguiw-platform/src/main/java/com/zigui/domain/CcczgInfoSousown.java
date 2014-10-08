package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ccczInfoSousown")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CcczgInfoSousown {
	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid" )
	private String tid;
	
	@ManyToOne
	@JoinColumn(name="SUBJECT_ID")
	private CcczgInfoSouce ccczgInfoSouce;
	
	@ManyToOne
	@JoinColumn(name="MEMBERID")
	private UserBase user;
	
	@Column(name="CREATE_DATE")
	private Date createDate;

	// Constructors

	/** default constructor */
	public CcczgInfoSousown() {
	}

	/** full constructor */

	// Property accessors

	

	public CcczgInfoSouce getCcczgInfoSouce() {
		return ccczgInfoSouce;
	}

	public void setCcczgInfoSouce(CcczgInfoSouce ccczgInfoSouce) {
		this.ccczgInfoSouce = ccczgInfoSouce;
	}

	public Date  getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public UserBase getUser() {
		return user;
	}

	public void setUser(UserBase user) {
		this.user = user;
	}

	public CcczgInfoSousown(String tid, CcczgInfoSouce ccczgInfoSouce,
			UserBase user, Date createDate) {
		super();
		this.tid = tid;
		this.ccczgInfoSouce = ccczgInfoSouce;
		this.user = user;
		this.createDate = createDate;
	}

	

	

}
