package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "health_history")
@SequenceGenerator(name = "HEALTH_HISTORY_SEQ", sequenceName = "health_history_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HealthHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3898884299214125031L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HEALTH_HISTORY_SEQ")
	public long id;

	@Column(name = "user_id")
	public long userId;

	@Column(name = "info")
	public String info;

	@Column(name = "reserve1")
	public String reserve1;

	@Column(name = "reserve2")
	public String reserve2;

	@Column(name = "state")
	public int state = 1;

	@Column(name = "type")
	public String type;

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
	public Date lastModifyTime;

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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

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
