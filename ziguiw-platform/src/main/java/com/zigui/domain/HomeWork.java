package com.zigui.domain;

import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.utils.BeanFactoryUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="homework")
@SequenceGenerator(
    name="HOMEWORK_SEQ",
    sequenceName="homework_seq",
    allocationSize=1
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HomeWork implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3020951456394427741L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOMEWORK_SEQ")
	private long id;
	
//	@Column(name="user_id")
//	private long userId;
	
//	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
//	@JoinColumn(name="subject", insertable=true, updatable=true)
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	private Subject subject;
	
	
	@Column(name="class_id")
	private String classId;
	
//	@ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
//	@JoinColumn(name="homework_type", insertable=true, updatable=true)
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	private CommonType homeworkType;
	
	@Column(name="point")
	private int point;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="teacher", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Teacher teacher;
	
	@Column(name="reserve1")
	private String reserve1;
	
	@Column(name="reserve2")
	private String reserve2;
	
	@Column(name="state")
	private int state=1;
	
	@Column(name="creator_id")
	private long creatorId;
	
	@Column(name="creator_nick")
	private String creatorNick;

    private String studentName;//对应数字化校园接口，homeworkMSG表中的XS_Xming字段
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Column(name="last_modify_id")
	private long lastModifyId;
	
	@Column(name="last_modify_nick")
	private String lastModifyNick;
	
	@Column(name="last_modify_time")
	private Date lastModifyTime = new Date();
	
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


	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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

	public String getCreateTime() {
		return new SimpleDateFormat("yyyy-MM-dd").format(createTime);
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

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	public Clasz getClasz(){
		CommonServiceImpl commonService = (CommonServiceImpl) BeanFactoryUtils.getBean("commonService");
		
		return commonService.get(Clasz.class, NumberUtils.toLong(this.getClassId()));
	}

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
