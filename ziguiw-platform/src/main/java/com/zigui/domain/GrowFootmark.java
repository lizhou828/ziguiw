package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="grow_footmark")
@SequenceGenerator(
    name="GROW_FOOTMARK_SEQ",
    sequenceName="grow_footmark_seq",
    allocationSize=1
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GrowFootmark implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4930935352136560695L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROW_FOOTMARK_SEQ")
	private long id;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="text")
	private String text;
	
	@Column(name="description")
	private String description;
	
	@Column(name="keyword")
	private String  keyword;
	
	@Column(name="type")
	private int type;
	
	@Column(name="status")
	private int status;
	
	@Column(name="state")
	private int state=1;
	
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
	
	@Column(name="view_count")
	private Integer viewCount;
	
	@Column(name="recommend_region_id")
	private int recommendRegionId;
	
	@Column(name="first_image")
	private String firstImage;
	
	@Column(name="click_count")
	private long clickCount;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public int getRecommendRegionId() {
		return recommendRegionId;
	}

	public void setRecommendRegionId(int recommendRegionId) {
		this.recommendRegionId = recommendRegionId;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public long getClickCount() {
		return clickCount;
	}

	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	

}
