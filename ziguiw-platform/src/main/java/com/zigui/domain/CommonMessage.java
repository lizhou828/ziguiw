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
@Table(name = "common_message")
@SequenceGenerator(name = "COMMON_MESSAGE_SEQ", sequenceName = "common_message_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
/**
 * 通用消息
 * 
 * @author bertyang
 *
 */
public class CommonMessage implements Serializable {

	private static final long serialVersionUID = 2838500431499793719L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMON_MESSAGE_SEQ")
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="text")
	private String text;
	
	@Column(name="status")
	private int status;
	
	@Column(name="state")
	private int state=1;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="from_user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase fromUser;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="to_user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase toUser;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "parent_msg_id")
	private CommonMessage parentMsg;

	@OneToMany(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name = "parent_msg_id")
	private Set<CommonMessage> childMsgs;
	
	@Transient
	private List<CommonMessage> sortedChildMsgs;
	
	@Column(name="kind")
	private int kind;
	
	@Column(name="class_id")
	private String classId;
	
	@Transient
	private int type;
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Column(name="last_modify_time")
	private Date lastModifyTime = new Date();
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Transient
	private String formatedCreateDate = getFormatedStartTime();
	
	@Transient
	private String formatedStartTime = getFormatedStartTime();
	
	@Transient
	private String formatedLastModifyTime = getFormatedLastModifyTime();

    private String smsReceivers;//访问10.0.1.55上的接口，以老师的id查询学生评语时，对应的sendObject字段
	
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public UserBase getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserBase fromUser) {
		this.fromUser = fromUser;
	}

	public UserBase getToUser() {
		return toUser;
	}

	public void setToUser(UserBase toUser) {
		this.toUser = toUser;
	}

	public CommonMessage getParentMsg() {
		return parentMsg;
	}

	public void setParentMsg(CommonMessage parentMsg) {
		this.parentMsg = parentMsg;
	}

	public Set<CommonMessage> getChildMsgs() {
		return childMsgs;
	}

	public void setChildMsgs(Set<CommonMessage> childMsgs) {
		this.childMsgs = childMsgs;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

//	public CommonType getType() {
//		return type;
//	}
//
//	public void setType(CommonType type) {
//		this.type = type;
//	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public List<CommonMessage> getSortedChildMsgs() {
		sortedChildMsgs = new ArrayList<CommonMessage>(childMsgs);
		
		if(CollectionUtils.isNotEmpty(childMsgs)){
			Collections.sort(sortedChildMsgs, (new Comparator<CommonMessage>(){
				@Override
				public int compare(CommonMessage msg0, CommonMessage msg1) {
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

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	public CommonMessage getLeastMsg(){
		if(CollectionUtils.isNotEmpty(childMsgs)){
			return this.getSortedChildMsgs().get(0);
		}else{
			return this;
		}
	}
	
	public List<CommonMessage> getAggregationMsgs() {
		List<CommonMessage> messages = new ArrayList<CommonMessage>();
		if(CollectionUtils.isNotEmpty(childMsgs)){
			messages.addAll(this.getSortedChildMsgs());
			messages.add(this);
		}else{
			messages.add(this);
		}
		
		return messages;
	}

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setSortedChildMsgs(List<CommonMessage> sortedChildMsgs) {
        this.sortedChildMsgs = sortedChildMsgs;
    }

    public void setFormatedCreateDate(String formatedCreateDate) {
        this.formatedCreateDate = formatedCreateDate;
    }

    public void setFormatedStartTime(String formatedStartTime) {
        this.formatedStartTime = formatedStartTime;
    }

    public void setFormatedLastModifyTime(String formatedLastModifyTime) {
        this.formatedLastModifyTime = formatedLastModifyTime;
    }

    public String getSmsReceivers() {
        return smsReceivers;
    }

    public void setSmsReceivers(String smsReceivers) {
        this.smsReceivers = smsReceivers;
    }
}
