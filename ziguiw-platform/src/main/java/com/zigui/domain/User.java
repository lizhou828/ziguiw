package com.zigui.domain;

import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user")
@SequenceGenerator(name="USER_SEQ", sequenceName="user_seq", allocationSize=1)
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4282064179310924930L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	private long id;

	@Column(name = "user_name")
	private String userName;
	
	private String email;

	private String password;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "user_type")
	private int userType;
	
	private int sex;
	
	private Date birthday;
	
	private int rank;
	
	private String province;
	
	private String city;
	
	private String phone;

	@Column(name = "create_time")
	private Date createTime = new Date();

	@Column(name = "create_ip")
	private String createIp;

	@Column(name = "portrait_url")
	private String portraitUrl;
	
	@Column(name = "last_reg_time")
	private Date lastRegTime = new Date();

	@Column(name = "last_reg_ip")
	private String lastRegIp;

	@Column(name = "reg_count")
	private int regCount;
	
	private String signature;
	
	private int status;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public Date getLastRegTime() {
		return lastRegTime;
	}

	public void setLastRegTime(Date lastRegTime) {
		this.lastRegTime = lastRegTime;
	}

	public String getLastRegIp() {
		return lastRegIp;
	}

	public void setLastRegIp(String lastRegIp) {
		this.lastRegIp = lastRegIp;
	}

	public int getRegCount() {
		return regCount;
	}

	public void setRegCount(int regCount) {
		this.regCount = regCount;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignature() {
		return signature;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public String getPortraitUrl() {
		return portraitUrl;
	}
}
