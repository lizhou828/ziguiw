package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_photo")
@SequenceGenerator(name = "USER_PHOTO_SEQ", sequenceName = "user_photo_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserPhoto implements Serializable{

	private static final long serialVersionUID = 3755277012240987686L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PHOTO_SEQ")
	private long id;

	private String title;
	
	private String url;
	
	private String text;
	
	private int type;
	
	private int status;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase user;

	@Column(name = "album_id")
	private long albumId;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "last_modify_time")
	private Date lastModifyTime;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public KnowledgeRecommendRegion getKnowledgeRecommendRegion() {
		return knowledgeRecommendRegion;
	}

	public void setKnowledgeRecommendRegion(
			KnowledgeRecommendRegion knowledgeRecommendRegion) {
		this.knowledgeRecommendRegion = knowledgeRecommendRegion;
	}

	
	

}
