package com.zigui.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="user_diary")
@SequenceGenerator(name="USER_DIARY_SEQ", sequenceName="user_diary_seq", allocationSize=1)
public class UserDiaryComment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 50525689862235631L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_DIARY_SEQ")
	private long id;
	
	@Column(name = "diary_id")
	private long diaryId;
	
	@Column(name = "user_id")
	private long userId;
	
	private int status;
	
	@Column(name = "create_time")
	private Date createTime = new Date();

	private String text;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(long diaryId) {
		this.diaryId = diaryId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
