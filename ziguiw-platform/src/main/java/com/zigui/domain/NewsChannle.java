package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "news_channle")
@SequenceGenerator(name = "NEWS_CHANNLE_SEQ", sequenceName = "news_channle_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsChannle implements Serializable {

	private static final long serialVersionUID = 1651722137657832794L;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_CHANNLE_SEQ")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
	private int state = 1;

	@Column(name = "creator_id")
	private long creatorId;

	@Column(name = "creator_nick")
	private String creatorNick;

	@Column(name = "create_time")
	private Date createTime = new Date();

	@Column(name = "last_modify_id")
	private Long lastModifyId;

	@Column(name = "last_modify_nick")
	private String lastModifyNick;

	@Column(name = "last_modify_time")
	private Date lastModifyTime = new Date();
	
	@Transient
	private String formatedStartTime = getFormatedStartTime();
	
	@Transient
	private String formatedLastModifyTime = getFormatedLastModifyTime();
	
	@Formula("(select count(*) from news ne where ne.channle_id = id)")
	private int newsCount;

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

	public Long getLastModifyId() {
		return lastModifyId;
	}

	public void setLastModifyId(Long lastModifyId) {
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
	
	public String getFormatedStartTime() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}

	public String getFormatedLastModifyTime() {
		if(lastModifyTime != null){
			return dateFormat.format(lastModifyTime);
		}
		return "";
	}

	public int getNewsCount() {
		return newsCount;
	}

	public void setNewsCount(int newsCount) {
		this.newsCount = newsCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(super.toString()).append(", ").append(id)
				.append(", ").append(name).append(", ").append(state)
				.append(", ").append(creatorId).append(", ")
				.append(creatorNick).append(", ").append(createTime)
				.append(", ").append(lastModifyId).append(", ")
				.append(lastModifyNick).append(", ").append(lastModifyTime)
				.append("]");
		return builder.toString();
	}

}
