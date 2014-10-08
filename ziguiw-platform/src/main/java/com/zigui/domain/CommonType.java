package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "common_type")
@SequenceGenerator(name = "COMMON_TYPE_SEQ", sequenceName = "common_type_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Deprecated
public class CommonType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964830157527213408L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMON_TYPE_SEQ")
	public long id;
	
	@Column(name="name")
	public String name;


	@Column(name="info")
	public String info;

	@Column(name="type")
	public int type;

	@Column(name="reserve1")
	public String reserve1;

	@Column(name="reserve2")
	public String reserve2;

	@Column(name="state")
	public int state=1;

	@Column(name="creator_id")
	private long creatorId;

	@Column(name="creator_nick")
	private String creatorNick;

	@Column(name="create_time")
	private Date createTime;

	@Column(name="last_modify_id")
	private long lastModifyId;

	@Column(name="last_modify_nick")
	private String lastModifyNick;

	@Column(name="last_modify_time")
	private Date lastModifyTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
