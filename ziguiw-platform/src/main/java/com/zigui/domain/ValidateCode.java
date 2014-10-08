package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="validate_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ValidateCode  implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="code")
	private int validateCode;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="invalid_time")
	private Date invalidTime = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(int validateCode) {
		this.validateCode = validateCode;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	
}
