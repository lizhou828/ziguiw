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
@Table(name = "user_comment")
@SequenceGenerator(name = "USER_COMMENT_SEQ", sequenceName = "user_comment_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserComment implements Serializable{
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_COMMENT_SEQ")
	private long id;
	
	
	private String text;
	
	private int status;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="from_user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase formUser;
	
	private int type;

	@Column(name="commented_id")
	private long commentedId;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "parent_msg_id")
	private UserComment parentMsg;
	
	@OneToMany(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name = "parent_msg_id")
	private Set<UserComment> childMsgs;
	
	private int kind;
	
	@Column(name = "create_time")
	private Date createTime = new Date();
	
	@Transient
	private List<UserComment> sortedChildMsgs;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	@Transient
	private String formatedCreateTime = getFormatedCreateTime();
	
	public String getFormatedCreateTime() {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCommentedId() {
		return commentedId;
	}

	public void setCommentedId(long commentedId) {
		this.commentedId = commentedId;
	}

	public UserComment getParentMsg() {
		return parentMsg;
	}

	public void setParentMsg(UserComment parentMsg) {
		this.parentMsg = parentMsg;
	}

	public Set<UserComment> getChildMsgs() {
		return childMsgs;
	}

	public void setChildMsgs(Set<UserComment> childMsgs) {
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

	public List<UserComment> getSortedChildMsgs() {
		sortedChildMsgs = new ArrayList<UserComment>(childMsgs);
		
		if(CollectionUtils.isNotEmpty(childMsgs)){
			Collections.sort(sortedChildMsgs, (new Comparator<UserComment>(){
				@Override
				public int compare(UserComment msg0, UserComment msg1) {
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
	
	

}
