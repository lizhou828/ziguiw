package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "debate")
@SequenceGenerator(name = "DEBATE_SEQ", sequenceName = "debate_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Debate implements Serializable{

	private static final long serialVersionUID = 4832108676041232495L;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBATE_SEQ")
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="positive_topic")
	private String positiveOpinion;
	
	@Column(name="negative_topic")
	private String negativeOpinion;
	
	@Column(name="positive_support_count")
	private int positiveSupportCount = 0;
	
	@Column(name="negative_support_count")
	private int negativeSupportCount = 0;
	
	@Column(name="description")
	private String description;
	
	@Column(name="open_days")
	private int openDays = 1;
	
	private int state = 1;
	
	@Column(name="creator_id")
	private long creatorId;
	
	@Column(name="creator_nick")
	private String creatorNick;
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Column(name = "last_modify_id")
	private long lastModifyId;

	@Column(name = "last_modify_nick")
	private String lastModifyNick;

	@Column(name = "last_modify_time")
	private Date lastModifyTime = new Date();
	
	@Column(name = "click_count")
	private int clickCount=0;
	
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

	public String getPositiveOpinion() {
		return positiveOpinion;
	}

	public void setPositiveOpinion(String positiveOpinion) {
		this.positiveOpinion = positiveOpinion;
	}

	public String getNegativeOpinion() {
		return negativeOpinion;
	}

	public void setNegativeOpinion(String negativeOpinion) {
		this.negativeOpinion = negativeOpinion;
	}

	public int getPositiveSupportCount() {
		return positiveSupportCount;
	}

	public void setPositiveSupportCount(int positiveSupportCount) {
		this.positiveSupportCount = positiveSupportCount;
	}

	public int getNegativeSupportCount() {
		return negativeSupportCount;
	}

	public void setNegativeSupportCount(int negativeSupportCount) {
		this.negativeSupportCount = negativeSupportCount;
	}

	public int getOpenDays() {
		return openDays;
	}

	public void setOpenDays(int openDays) {
		this.openDays = openDays;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
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
	

}
