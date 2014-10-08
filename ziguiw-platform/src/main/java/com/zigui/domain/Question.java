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
@Table(name = "question")
@SequenceGenerator(name = "QUESTION_SEQ", sequenceName = "question_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Question implements Serializable{
	
private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static final long serialVersionUID = -4920665046121022083L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_SEQ")
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "state")
	private int state = 1;
	
	
	@Column(name = "content")
	private String content;

	@Column(name = "creator_id")
	private long creatorId;

	@Column(name = "creator_nick")
	private String creatorNick;

	@Column(name = "create_time")
	private Date createTime = new Date();


	@Column(name = "last_modify_time")
	private Date lastModifyTime = new Date();
	
	@Column(name = "pending_time")
	private int pendingTime;
	
	private String keywords;
	
	@Column(name = "click_count")
	private int clickCount;
	
	@Formula("(select count(*) from answer a where a.question_id = id)")
	private int answerCount;
	
	private Integer point;
	
	@Column(name = "best_answer_id")
	private long bestAnswerId;
	
	@Column(name = "best_answer_author")
	private long bestAnswerAuthor;
	
	@Formula("(select max(a.create_time) from answer a where a.question_id = id)")
	private Date lTime;
	
	@Transient
	private String formatedStartTime = getFormatedStartTime();
	
	@Transient
	private String formatedLastModifyTime = getFormatedLastModifyTime();
	
	@Transient
	private String formatedLTime = getFormatedLTime();
	
	@Formula("(select u.nick_name from user_base u where u.id = best_answer_author)")
	private String bestAnswerAuthorNick;
	
	private int status;

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getContent() {
		return content;
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

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.DATE)
	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public int getPendingTime() {
		return pendingTime;
	}

	public void setPendingTime(int pendingTime) {
		this.pendingTime = pendingTime;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeyword(String keywords) {
		this.keywords = keywords;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public long getBestAnswerId() {
		return bestAnswerId;
	}

	public void setBestAnswerId(long bestAnswerId) {
		this.bestAnswerId = bestAnswerId;
	}

	public long getBestAnswerAuthor() {
		return bestAnswerAuthor;
	}

	public void setBestAnswerAuthor(long bestAnswerAuthor) {
		this.bestAnswerAuthor = bestAnswerAuthor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getFormatedLTime() {
		if(lTime != null){
			return dateFormat.format(lTime);
		}
		return "";
	}
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public Date getLTime() {
		return lTime;
	}

	public void setLTime(Date time) {
		lTime = time;
	}

	public String getBestAnswerAuthorNick() {
		return bestAnswerAuthorNick;
	}

	public void setBestAnswerAuthorNick(String bestAnswerAuthorNick) {
		this.bestAnswerAuthorNick = bestAnswerAuthorNick;
	}



	
	
	

}
