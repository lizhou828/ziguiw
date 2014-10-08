package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="user_diary")
@SequenceGenerator(name="USER_DIARY_SEQ", sequenceName="user_diary_seq", allocationSize=1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserDiary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1665745923475434073L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_DIARY_SEQ")
	private long id;
	
	private String title;

	private String keyword;
	
	private int type;
	
	private int status;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase user;
	
	@Column(name = "create_time")
	private Date createTime = new Date();

	@Column(name = "last_modify_id")
	private int lastModifyId;

	@Column(name = "last_modify_time")
	private Date lastModifyTime = new Date();

	@Lob
	@Basic(fetch= FetchType.EAGER,optional=true)
	@Column(name = "text")
	private String text;
	
	private String description;
	
	@Column(name = "view_count")
	private int viewCount;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="recommend_region_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private KnowledgeRecommendRegion knowledgeRecommendRegion;
	
	@Column(name = "first_image")
	private String firstImage;
	
	@Column(name = "click_count")
	private Long clickCount;
	
	@Lob
	@Type(type="org.springframework.orm.hibernate3.support.ClobStringType")
	@Column(length=10000)
	public String getText() {
		return text;
	}
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Transient
	private String formatedCreateDate = getFormatedStartTime();
	
	@Transient
	private String formatedStartTime = getFormatedStartTime();
	
	@Transient
	private String formatedLastModifyTime = getFormatedLastModifyTime();
	
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
	
	

	public String getFormatedCreateDate() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getLastModifyId() {
		return lastModifyId;
	}

	public void setLastModifyId(int lastModifyId) {
		this.lastModifyId = lastModifyId;
	}

	public Date getLast_modify_time() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getViewCount() {
		return viewCount;
	}



	public KnowledgeRecommendRegion getKnowledgeRecommendRegion() {
		return knowledgeRecommendRegion;
	}

	public void setKnowledgeRecommendRegion(
			KnowledgeRecommendRegion knowledgeRecommendRegion) {
		this.knowledgeRecommendRegion = knowledgeRecommendRegion;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public Long getClickCount() {
		return clickCount;
	}

	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}



}
