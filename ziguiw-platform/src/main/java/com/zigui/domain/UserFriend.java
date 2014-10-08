package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "user_friend")
@SequenceGenerator(name = "USER_FRIEND_SEQ", sequenceName = "user_friend_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserFriend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6902169523567565577L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_FRIEND_SEQ")
	private long id;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase user;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="friend_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase friendUser;
	
	@Column(name = "create_time")
	private Date createTime = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public UserBase getUser() {
		return user;
	}

	public void setUser(UserBase user) {
		this.user = user;
	}

	public UserBase getFriendUser() {
		return friendUser;
	}

	public void setFriendUser(UserBase friendUser) {
		this.friendUser = friendUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
