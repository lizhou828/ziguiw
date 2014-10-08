package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="love")
@SequenceGenerator(
    name="LOVE_SEQ",
    sequenceName="love_seq",
    allocationSize=1
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Love implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1641661521050972938L;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	   @Id
	   @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="LOVE_SEQ")
	   public Long id;

	   @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	   @JoinColumn(name="donate_user_id", insertable=true, updatable=true)
	   @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	   public UserBase donateUser;
	   
	   @Column(name = "to_nick")
	   public String toNick;

	   @Column(name = "info")
	   public String info;

	   @Column(name = "description")
	   public String description;

	   @Column(name = "reserve1")
	   public String reserve1;

	   @Column(name = "reserve2")
	   public String reserve2;

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

		public UserBase getDonateUser() {
			return donateUser;
		}

		public void setDonateUser(UserBase donateUser) {
			this.donateUser = donateUser;
		}

		public String getToNick() {
			return toNick;
		}

		public void setToNick(String toNick) {
			this.toNick = toNick;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getReserve1() {
			return reserve1;
		}

		public void setReserve1(String reserve1) {
			this.reserve1 = reserve1;
		}

		public String getReserve2() {
			return reserve2;
		}

		public void setReserve2(String reserve2) {
			this.reserve2 = reserve2;
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
