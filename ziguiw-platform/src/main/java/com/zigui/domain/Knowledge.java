package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="knowledge")
@SequenceGenerator(
    name="KNOWLEDGE_SEQ",
    sequenceName="knowledge_seq",
    allocationSize=1
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Knowledge implements Serializable {

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static final long serialVersionUID = 5412943401241699119L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="KNOWLEDGE_SEQ")
	private long id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="channle_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private KnowledgeChannle knowledgeChannle;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="recommend_region_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private KnowledgeRecommendRegion knowledgeRecommendRegion;
	
	@Column(name = "state")
	private int state = 1;

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
	
	@Column(name = "subtitle")
	private String subtitle;
	
	@Column(name = "keywords")
	private String keywords;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "auto_description")
	private String autoDescription;
	
	@Column(name = "editors")
	private String editors;
	
	@Column(name = "click_count")
	private int clickCount;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "first_image")
	private String firstImage;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "city")
	private String city;
	
	@Lob
	@Basic(fetch= FetchType.EAGER,optional=true)
	@Column(name="content")
	private String content;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="source_type", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private NewsSource sourceType;
	
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public KnowledgeChannle getKnowledgeChannle() {
		return knowledgeChannle;
	}

	public void setKnowledgeChannle(KnowledgeChannle knowledgeChannle) {
		this.knowledgeChannle = knowledgeChannle;
	}


	public KnowledgeRecommendRegion getKnowledgeRecommendRegion() {
		return knowledgeRecommendRegion;
	}

	public void setKnowledgeRecommendRegion(
			KnowledgeRecommendRegion knowledgeRecommendRegion) {
		this.knowledgeRecommendRegion = knowledgeRecommendRegion;
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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAutoDescription() {
		return autoDescription;
	}

	public void setAutoDescription(String autoDescription) {
		this.autoDescription = autoDescription;
	}

	public String getEditors() {
		return editors;
	}

	public void setEditors(String editors) {
		this.editors = editors;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
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

	public NewsSource getSourceType() {
		return sourceType;
	}

	public void setSourceType(NewsSource sourceType) {
		this.sourceType = sourceType;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", knowledgeChannle="
				+ knowledgeChannle + ", knowledgeRecommendRegion=" + knowledgeRecommendRegion
				+ ", state=" + state + ", creatorId=" + creatorId
				+ ", creatorNick=" + creatorNick + ", createTime=" + createTime
				+ ", lastModifyId=" + lastModifyId + ", lastModifyNick="
				+ lastModifyNick + ", lastModifyTime=" + lastModifyTime
				+ ", subtitle=" + subtitle + ", keywords=" + keywords
				+ ", description=" + description + ", autoDescription="
				+ autoDescription + ", editors=" + editors + ", clickCount="
				+ clickCount + ", url=" + url + ", firstImage=" + firstImage
				+ ", province=" + province + ", city=" + city + ", sourceType="
				+ sourceType + ", formatedStartTime=" + formatedStartTime
				+ ", formatedLastModifyTime=" + formatedLastModifyTime + "]";
	}

}
