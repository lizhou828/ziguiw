package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_mood")
@SequenceGenerator(name = "USER_MOOD_SEQ", sequenceName = "user_mood_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserMood implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5057764577934579140L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_MOOD_SEQ")
	private long id;
	
	private String text;
	
	private int type;
	
	private int status;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase user;
	
	@Column(name = "ref_id")
	private int refId;
	
	@Column(name = "create_time")
	private Date createTime = new Date();
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="recommend_region_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private KnowledgeRecommendRegion knowledgeRecommendRegion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public UserBase getUser() {
		return user;
	}

	public void setUser(UserBase user) {
		this.user = user;
	}

	public int getRefId() {
		return refId;
	}

	public void setRefId(int refId) {
		this.refId = refId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public KnowledgeRecommendRegion getKnowledgeRecommendRegion() {
		return knowledgeRecommendRegion;
	}

	public void setKnowledgeRecommendRegion(
			KnowledgeRecommendRegion knowledgeRecommendRegion) {
		this.knowledgeRecommendRegion = knowledgeRecommendRegion;
	}


	
	

}
