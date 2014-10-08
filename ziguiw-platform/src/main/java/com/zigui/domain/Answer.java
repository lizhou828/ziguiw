package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "answer")
@SequenceGenerator(name = "ANSWER_SEQ", sequenceName = "answer_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Answer implements Serializable{

	private static final long serialVersionUID = -5588423114309551008L;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANSWER_SEQ")
	private long id;

	@Column(name = "state")
	private int state = 1;

	@Column(name = "creator_id")
	private long creatorId;

	@Column(name = "creator_nick")
	private String creatorNick;

	@Column(name = "create_time")
	private Date createTime = new Date();
	
	private String content;
	
	@Column(name = "good_comment_count")
	private long goodCommentCount;
	
	@Transient
	private String formatedCreateTime = getFormatedCreateTime();
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="question_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Question question;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public long getGoodCommentCount() {
		return goodCommentCount;
	}

	public void setGoodCommentCount(long goodCommentCount) {
		this.goodCommentCount = goodCommentCount;
	}
	
	public String getFormatedCreateTime() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}

}
