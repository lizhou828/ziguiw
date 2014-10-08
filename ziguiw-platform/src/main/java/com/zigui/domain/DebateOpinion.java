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
@Table(name = "debate_opinion")
@SequenceGenerator(name = "DEBATE_OPINION_SEQ", sequenceName = "debate_opinion_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateOpinion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9098219302667651777L;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBATE_OPINION_SEQ")
	private long id;


	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="topic_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Debate debate;
	
	@Column(name = "status")
	private int status = 1;
	
	@Column(name="opinion")
	private String opinion;
	
	@Column(name = "state")
	private int state = 1;
		
	
	@Column(name="creator_id")
	private long creatorId;
	
	@Column(name="creator_nick")
	private String creatorNick;
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Transient
	private String formatedCreateTime = getFormatedCreateTime();
	
	@Formula("(select u.portrait from user_base u where u.id = creator_id)")
	private String creatorPortrait;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Debate getDebate() {
		return debate;
	}

	public void setDebate(Debate debate) {
		this.debate = debate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
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
	
	
	public String getCreatorPortrait() {
		return creatorPortrait;
	}

	public void setCreatorPortrait(String creatorPortrait) {
		this.creatorPortrait = creatorPortrait;
	}

	public String getFormatedCreateTime() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}
	
}
