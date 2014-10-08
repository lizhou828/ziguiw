package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "template")
@SequenceGenerator(name = "TEMPLATE_SEQ", sequenceName = "template_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Template implements Serializable{
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static final long serialVersionUID = -4920665046121022083L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEMPLATE_SEQ")
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "state")
	private int state = 1;
	
	private String description;
	
	@Lob
	@Basic(fetch= FetchType.EAGER,optional=true)
	@Column(name = "content")
	private String content;

	@Column(name = "creator_id")
	private long creatorId;

	@Column(name = "creator_nick")
	private String creatorNick;

	@Column(name = "create_time")
	private Date createTime = new Date();

	@Column(name = "last_modify_id")
	private long lastModifyId;

	@Column(name = "last_modify_nick")
	private String lastModifyNick;

	@Column(name = "last_modify_time")
	private Date lastModifyTime = new Date();
	
	@Transient
	private String formatedStartTime = getFormatedStartTime();
	
	@Transient
	private String formatedLastModifyTime = getFormatedLastModifyTime();

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

	public String getContent() {
		return content;
	}
	
	public String getDisplayContent() {
		return content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", name=" + name + ", state=" + state
				+ ", content=" + content + ", creatorId=" + creatorId
				+ ", creatorNick=" + creatorNick + ", createTime=" + createTime
				+ ", lastModifyId=" + lastModifyId + ", lastModifyNick="
				+ lastModifyNick + ", lastModifyTime=" + lastModifyTime
				+ ", formatedStartTime=" + formatedStartTime
				+ ", formatedLastModifyTime=" + formatedLastModifyTime + "]";
	}
	
	

}
