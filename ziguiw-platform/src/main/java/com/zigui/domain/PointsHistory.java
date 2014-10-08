package com.zigui.domain;

import com.zigui.utils.Constant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "point_history")
@SequenceGenerator(name = "POINT_HISTORY_SEQ", sequenceName = "point_history_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PointsHistory implements Serializable{
	
	private static final long serialVersionUID = -3797931007089460956L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POINT_HISTORY_SEQ")
	private long id;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase user;
	
	@Column(name="points_change")
	private int pointsChange;
	
	//1是增加，2是减少
	private int flag = 1;
	
	//类型，例如是回答问题，还是**
	@Column(name="change_type")
	private int type;
	
	@Column(name="change_time")
	private Date changeTime = new Date();
	
	private int state = 1;
	
	@Column(name="audit_opinion")
	private String auditOpinion;
	
	@Column(name="audit_user_id")
	private String auditUserId;
	
	@Column(name="audit_time")
	private Date auditTime;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Transient
	private String formatedChangeTime = getFormatedChangeTime();
	
	public String getFormatedChangeTime() {
		if(changeTime != null){
			return dateFormat.format(changeTime);
		}
		return "";
	}

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

	public int getPointsChange() {
		return pointsChange;
	}

	public void setPointsChange(int pointsChange) {
		this.pointsChange = pointsChange;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	public String getTypeStr() {
		if(this.getType() == Constant.EVERY_LOGON || this.getType() == Constant.EVERY_LOGON_1){
			return "登录";
		}else if(this.getType() == Constant.REGISTER){
			return "注册";
		}else if(this.getType() == Constant.PUBLISH_FORUM){
			return "发表帖子";
		}else if(this.getType() == Constant.REPLY_FORUM){
			return "恢复帖子";
		}else if(this.getType() == Constant.FUFILLMENT_INFO){
			return "完善资料";
		}else if(this.getType() == Constant.QUESTION){
			return "提问";
		}else if(this.getType() == Constant.CONTINUE_LOGON){
			return "连续5天登录";
		}else if(this.getType() == Constant.PAYPOINTS){
			return "购买积分";
		}else if(this.getType() == Constant.DOWN_RESOURCE){
			return ("下载资源");
		}else if(this.getType() == Constant.UPLOAD_RESOURCE){
			return ("上传资源被下载");
		}
		return "  ";
	}

}
