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
@Table(name = "board")
@SequenceGenerator(name = "BOARD_SEQ", sequenceName = "board_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Board implements Serializable {
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**
	 * 
	 */
	private static final long serialVersionUID = -5972126602338484864L;
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ")
	   public long id;
	   
//	   @Column(name = "parentid")
//	   public long parentid;
	   
		@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
		@JoinColumn(name="parentid", insertable=true, updatable=true)
		@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
		private Board parentBoard;
	   
	   @Column(name = "boardname")
	   public String boardname;

	   @Column(name = "explains")
	   public String explains;

	   @Column(name = "bulletin")
	   public String bulletin;

	   @Column(name = "state")
	   public int state = 1;

	   @Column(name = "needpasswd")
	   public int needpasswd;

	   @Column(name = "passwd")
	   public String passwd;

		@Column(name = "creator_id")
		private long creatorId;

		@Column(name = "creator_nick")
		private String creatorNick;

	   @Column(name = "create_time")
		private Date createTime = new Date();

	   @Column(name = "boardtype")
	   public double boardtype;

	   @Formula("(select count(*) from forum f where f.boardid = id and f.isnew = 1)")
	   public long mainpostnum = 0;

	   @Formula("(select count(*) from forum f where f.boardid = id)")
	   public long postnum = 0;

	   @Transient
	   private String formatedStartTime = getFormatedStartTime();

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Board getParentBoard() {
			return parentBoard;
		}

		public void setParentBoard(Board parentBoard) {
			this.parentBoard = parentBoard;
		}

		public String getBoardname() {
			return boardname;
		}

		public void setBoardname(String boardname) {
			this.boardname = boardname;
		}

		public String getExplains() {
			return explains;
		}

		public void setExplains(String explains) {
			this.explains = explains;
		}

		public String getBulletin() {
			return bulletin;
		}

		public void setBulletin(String bulletin) {
			this.bulletin = bulletin;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public int getNeedpasswd() {
			return needpasswd;
		}

		public void setNeedpasswd(int needpasswd) {
			this.needpasswd = needpasswd;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
	
		public double getBoardtype() {
			return boardtype;
		}
	
		public void setBoardtype(double boardtype) {
			this.boardtype = boardtype;
		}
	
		public long getMainpostnum() {
			return mainpostnum;
		}
	
		public void setMainpostnum(long mainpostnum) {
			this.mainpostnum = mainpostnum;
		}
	
		public long getPostnum() {
			return postnum;
		}
	
		public void setPostnum(long postnum) {
			this.postnum = postnum;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
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

		public String getFormatedStartTime() {
			if(createTime != null){
				return dateFormat.format(createTime);
			}
			return "";
		}
		
}
