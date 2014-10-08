package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="knowledge_recommend_region")
@SequenceGenerator(
    name="KNOWLEDGE_RECOMMEND_REGION_SEQ",
    sequenceName="knowledge_recommend_region_seq",
    allocationSize=1
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KnowledgeRecommendRegion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8285505610051273863L;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="KNOWLEDGE_RECOMMEND_REGION_SEQ")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="state")
	private int state = 1;
	
	@Column(name="creator_id")
	private long creatorId;
	
	@Column(name="creator_nick")
	private String creatorNick; 
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Column(name="last_modify_id")
	private long lastModifyId;
	
	@Column(name="last_modify_nick")
	private String lastModifyNick; 

	@Column(name="last_modify_time")
	private Date lastModifyTime = new Date();
	
	@Column(name="type")
	private long type;
	
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
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
