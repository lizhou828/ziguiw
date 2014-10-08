package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "user_album")
@SequenceGenerator(name = "USER_ALBUM_SEQ", sequenceName = "user_album_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAlbum implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8050995130905500314L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ALBUM_SEQ")
	private long id;

	private String title;
	
	private String text;
	
	private String keyword;
	
	private int type;
	
	private int status;
	
	@OneToOne(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name="cover_photo_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserPhoto coverPhoto;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase user;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "last_modify_time")
	private Date lastModifyTime;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public UserPhoto getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(UserPhoto coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

}
