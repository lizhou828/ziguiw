package com.zigui.domain;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "user_message")
@SequenceGenerator(name = "USER_MESSAGE_SEQ", sequenceName = "user_message_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserMessage implements Serializable{

	private static final long serialVersionUID = -7178198427155907109L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_MESSAGE_SEQ")
	private long id;
	
	private String title;
	
	private String text;
	
	private int status;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="from_user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase formUser;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="to_user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase toUser;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "parent_msg_id")
	private UserMessage parentMsg;
	
	@OneToMany(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name = "parent_msg_id")
	private Set<UserMessage> childMsgs;
	
	private int kind;
	
	@Column(name = "create_time")
	private Date createTime = new Date();
	
	@Transient
	private List<UserMessage> sortedChildMsgs;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Transient
	private String formatedCreateDate = getFormatedStartTime();
	
	@Transient
	private String formatedStartTime = getFormatedStartTime();
	
	@Transient
	private String formatedLastModifyTime = getFormatedLastModifyTime();
	
	public String getFormatedStartTime() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}

	public String getFormatedLastModifyTime() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}
	
	

	public String getFormatedCreateDate() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserBase getFormUser() {
		return formUser;
	}

	public void setFormUser(UserBase formUser) {
		this.formUser = formUser;
	}

	public UserBase getToUser() {
		return toUser;
	}

	public void setToUser(UserBase toUser) {
		this.toUser = toUser;
	}

	public UserMessage getParentMsg() {
		return parentMsg;
	}

	public void setParentMsg(UserMessage parentMsg) {
		this.parentMsg = parentMsg;
	}

	public Set<UserMessage> getChildMsgs() {
		return childMsgs;
	}

	public void setChildMsgs(Set<UserMessage> childMsgs) {
		this.childMsgs = childMsgs;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<UserMessage> getSortedChildMsgs() {
		sortedChildMsgs = new ArrayList<UserMessage>(childMsgs);
		
		if(CollectionUtils.isNotEmpty(childMsgs)){
			Collections.sort(sortedChildMsgs, (new Comparator<UserMessage>(){
				@Override
				public int compare(UserMessage msg0, UserMessage msg1) {
					if(msg0.getCreateTime().before(msg1.getCreateTime())){
						return 1;
					}else if(msg0.getCreateTime().after(msg1.getCreateTime())){
						return -1;
					}else{
						return 0;
					}
				};
			}));
		}
		
		return sortedChildMsgs;
	}

//	@Override
//	public String toString() {
//		return "UserMessage [id=" + id + ", text=" + text
//				+ ", status=" + status + ", formUser=" + formUser + ", toUser="
//				+ toUser + ", kind=" + kind + ", createTime=" + createTime
//				+ "]";
//	}

}
