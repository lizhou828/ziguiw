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
@Table(name = "forum")
@SequenceGenerator(name = "FORUM_SEQ", sequenceName = "forum_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Forum implements Serializable {
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * 
	 */
	private static final long serialVersionUID = -1869484453661821056L;
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORUM_SEQ")
	   public long id;
	   
//	   @Column(name = "parentid")
//	   public long parentid;
	   
	   @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	   @JoinColumn(name="parentid", insertable=true, updatable=true)
	   @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
		private Forum parentForum;
	   
//	   @Column(name = "boardid")
//	   public long boardid;
	   
	   @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	   @JoinColumn(name="boardid", insertable=true, updatable=true)
	   @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	   private Board board;
	   
	   @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
		@JoinColumn(name="recommend_region_id", insertable=true, updatable=true)
	   @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
		private KnowledgeRecommendRegion knowledgeRecommendRegion;
	   
	   @Formula("(select count(*) from forum f where f.parentid = id)")
	   public long renum = 0;
	   
	   @Column(name = "creator_id")
	   public long creatorId;
	   
	   @Column(name = "creator_nick")
	   public String creatorNick;

	   @Column(name = "title")
	   public String title;

	   @Column(name = "content")
	   public String content;

	   @Column(name = "click_count")
	   public long clickCount;

	   @Column(name = "create_time")
	   public Date createTime = new Date();

	   @Column(name = "last_post_id")
	   public long lastPostId;

	   @Column(name = "last_post_nick")
	   public String lastPostNick;

	   @Column(name = "last_post_time")
	   public Date lastPostTime = new Date();

	   @Column(name = "ipaddress")
	   public String ipaddress;

	   @Column(name = "isnew")
	   public int isnew;

	   @Column(name = "elite")
	   public int elite = 0;

	   @Column(name = "istop")
	   public int istop = 0;

	   @Column(name = "state")
	   public int state = 1;

	   @Column(name = "tag")
	   public String tag;

	   @Column(name = "first_image")
	   public String firstImage;

	   @Column(name = "auto_description")
	   public String autoDescription;

	   @Transient
		private String formatedStartTime = getFormatedStartTime();

		@Transient
		private String formatedLastPostTime = getFormatedLastPostTime();

		@Formula("(select u.portrait from user_base u where u.id = creator_id)")
		private String creatorPortrait;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Forum getParentForum() {
		return parentForum;
	}

	public void setParentForum(Forum parentForum) {
		this.parentForum = parentForum;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public long getRenum() {
		return renum;
	}

	public void setRenum(long renum) {
		this.renum = renum;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public KnowledgeRecommendRegion getKnowledgeRecommendRegion() {
		return knowledgeRecommendRegion;
	}

	public void setKnowledgeRecommendRegion(
			KnowledgeRecommendRegion knowledgeRecommendRegion) {
		this.knowledgeRecommendRegion = knowledgeRecommendRegion;
	}

	public long getClickCount() {
		return clickCount;
	}

	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getLastPostId() {
		return lastPostId;
	}

	public void setLastPostId(long lastPostId) {
		this.lastPostId = lastPostId;
	}

	public String getLastPostNick() {
		return lastPostNick;
	}

	public void setLastPostNick(String lastPostNick) {
		this.lastPostNick = lastPostNick;
	}

	public Date getLastPostTime() {
		return lastPostTime;
	}

	public void setLastPostTime(Date lastPostTime) {
		this.lastPostTime = lastPostTime;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public int getIsnew() {
		return isnew;
	}

	public void setIsnew(int isnew) {
		this.isnew = isnew;
	}

	public int getElite() {
		return elite;
	}

	public void setElite(int elite) {
		this.elite = elite;
	}

	public int getIstop() {
		return istop;
	}

	public void setIstop(int istop) {
		this.istop = istop;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	   
	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	
	public String getAutoDescription() {
		return autoDescription;
	}

	public void setAutoDescription(String autoDescription) {
		this.autoDescription = autoDescription;
	}

	
	public String getCreatorPortrait() {
		return creatorPortrait;
	}

	public void setCreatorPortrait(String creatorPortrait) {
		this.creatorPortrait = creatorPortrait;
	}

	public String getFormatedStartTime() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}

	public String getFormatedLastPostTime() {
		if(lastPostTime != null){
			return dateFormat.format(lastPostTime);
		}
		return "";
	}


}
