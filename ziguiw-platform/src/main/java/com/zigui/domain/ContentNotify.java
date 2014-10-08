package com.zigui.domain;

import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.utils.BeanFactoryUtils;
import com.zigui.utils.Constant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="content_notify")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContentNotify implements Serializable{
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int type;
	
	private int flag = 1;
	
	@Column(name="content_id")
	private long contentId;
	
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Transient
	private String formatedCreateTime = getFormatedCreateTime();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public long getContentId() {
		return contentId;
	}

	public void setContentId(long contentId) {
		this.contentId = contentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getFormatedCreateTime(){
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}
	
	public String getContent(){
		CommonServiceImpl commonService = (CommonServiceImpl) BeanFactoryUtils.getBean("commonService");
		if(this.getType() == Constant.REGISTER_NOTIFY){
			UserBase user = commonService.get(UserBase.class, this.getContentId());
			
			return user.getNickName() + "于" + getFormatedCreateTime() + "注册了子贵网";
		}else if(this.getType() == Constant.DIARY_NOTIFY){
			UserDiary diary = commonService.get(UserDiary.class, this.getContentId());
			
			return diary.getUser().getNickName() + "于" + getFormatedCreateTime() + "创建了日志“" + diary.getTitle() + "”";
		}
		
		return "";
	}
	
}
