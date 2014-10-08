package com.zigui.domain;

import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.utils.BeanFactoryUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@Table(name = "user_base")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "user_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserBase implements Serializable{
	
private static final long serialVersionUID = -4920665046121022083L;
private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	private long id;

	@Column(name = "nick_name")
	private String nickName;
	
	@Column(name = "email")
	private String email;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Column(name = "passwd")
	private String password;
	
	@Column(name = "display_name")
	private String displayName;
	
	private String birthday;
	
	private String province;
	
	private String city;
	
	private String location;
	
	private Integer sex;
	
	/**
	 * 注册之后是1
	 * 激活之后是2，手机注册直接进入状态2
	 * 子贵导入数据状态是5
	 * 完善信息激活之后状态是6
	 */
	private Integer state = 1;
	
	@Column(name = "create_time")
	private Date createTime = new Date();
	
	@Column(name = "last_modify_time")
	private Date lastModifyTime = new Date();
	
	@Column(name = "last_login_time")
	private Date lastLoginTime;

    @Column(name = "last_login_ip")
    private String lastLoginIp;

    @Column(name = "sessionid")
    private String sessionId;


    @OneToMany(fetch=FetchType.LAZY,mappedBy="member")
    private Set<CcczgInfoSouce> ccczInfoSouces = new HashSet<CcczgInfoSouce>();

    @OneToMany(fetch=FetchType.LAZY,mappedBy="user")
    private Set<CcczgInfoSousown> ccczgInfoSousown = new HashSet<CcczgInfoSousown>();


    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
	@JoinColumn(name="role_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Role role;
	
	private Integer points = 0;
	
	private String portrait;
	
	@Column(name = "space_pv")
	private Integer spacePv = 0;
	
	private String signature;
	
	@ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
	@JoinColumn(name="recommend_region_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private KnowledgeRecommendRegion knowledgeRecommendRegion;
	
	private Integer type = 0;
	
	@Column(name = "foreign_key")
	private Long foreignKey;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "logincount")
	private Integer loginCount;
	
	@Transient
	private String formatedStartTime = getFormatedStartTime();
	
	@Transient
	private String formatedLastLoginTime = getFormatedLastLoginTime();
	
	@Column(name = "real_name")
	private String realName;
	
	@Column(name = "region_time")
	private Date regionTime;
 

	@OneToMany(fetch=FetchType.LAZY)
	private Set<Order> orders = new HashSet<Order>();
	
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}




	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

    public Set<CcczgInfoSouce> getCcczInfoSouces() {
    	return ccczInfoSouces;
    }

    public void setCcczInfoSouces(Set<CcczgInfoSouce> ccczInfoSouces) {
        this.ccczInfoSouces = ccczInfoSouces;
    }

    public Set<CcczgInfoSousown> getCcczgInfoSousown() {
        return ccczgInfoSousown;
    }

    public void setCcczgInfoSousown(Set<CcczgInfoSousown> ccczgInfoSousown) {
        this.ccczgInfoSousown = ccczgInfoSousown;
    }

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public Integer getSpacePv() {
		return spacePv;
	}

	public void setSpacePv(Integer spacePv) {
		this.spacePv = spacePv;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}



	public KnowledgeRecommendRegion getKnowledgeRecommendRegion() {
		return knowledgeRecommendRegion;
	}

	public void setKnowledgeRecommendRegion(
			KnowledgeRecommendRegion knowledgeRecommendRegion) {
		this.knowledgeRecommendRegion = knowledgeRecommendRegion;
	}
	
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(Long foreignKey) {
		this.foreignKey = foreignKey;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getFormatedStartTime() {
		if(createTime != null){
			return dateFormat.format(createTime);
		}
		return "";
	}
	
	
	
	public String getFormatedLastLoginTime() {
		if(lastLoginTime != null){
			return dateFormat.format(lastLoginTime);
		}
		return "";
	}

	public void setFormatedLastLoginTime(String formatedLastLoginTime) {
		this.formatedLastLoginTime = formatedLastLoginTime;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	

	
	public Date getRegionTime() {
		return regionTime;
	}

	public void setRegionTime(Date regionTime) {
		this.regionTime = regionTime;
	}

	
	public String getRealName() {
		if(StringUtils.isNotEmpty(realName)){
			return realName;
		}
		else if(this.getStudent() != null){
			return this.getStudent().getXs_xming();
		}else if(this.getParent() != null){
			return this.getParent().getPARENTS_NAME();
		}else if(this.getTeacher() != null){
			return this.getTeacher().getName();
		}
		
		return this.nickName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	@JSON(serialize=false)
	public Student getStudent(){
		CommonServiceImpl commonService = (CommonServiceImpl) BeanFactoryUtils.getBean("commonService");
		Student student = null;
		if(this.getType() == 4 && this.getForeignKey() != null  && this.getForeignKey() > 0){
			student = commonService.get(Student.class, (int)this.getForeignKey().longValue());
		}
		
		return student;
	}
	
	@JSON(serialize=false)
	public School getSchool(){
		CommonServiceImpl commonService = (CommonServiceImpl) BeanFactoryUtils.getBean("commonService");
		School school = null;
		if(this.getType() == 1 && this.getForeignKey() != null  && this.getForeignKey() > 0){
			school = commonService.get(School.class, this.getForeignKey().longValue());
		}else if(this.getType() == 2){
			school = this.getTeacher().getSchool();
		}else if(this.getType() == 3){
			school = this.getParent().getStudent().getSchool();
		}else if(this.getType() == 4){
			school = this.getStudent().getSchool();
		}
		
		return school;
	}
	
	@JSON(serialize=false)
	public Parent getParent(){
		CommonServiceImpl commonService = (CommonServiceImpl) BeanFactoryUtils.getBean("commonService");
		Parent parent = null;
		if(this.getType() == 3 && this.getForeignKey() != null  && this.getForeignKey() > 0){
			parent = commonService.get(Parent.class, (int)this.getForeignKey().longValue());
		}
		
		return parent;
	}
	
	@JSON(serialize=false)
	public Teacher getTeacher(){
		CommonServiceImpl commonService = (CommonServiceImpl) BeanFactoryUtils.getBean("commonService");
		Teacher teacher = null;
		if(this.getType() == 2 && this.getForeignKey() != null  && this.getForeignKey() > 0){
			teacher = commonService.get(Teacher.class, this.getForeignKey());
		}
		
		return teacher;
	}

	@Override
	public String toString() {
		return "UserBase [id=" + id + ", nickName=" + nickName + ", email="
				+ email + ", password=" + password + ", birthday=" + birthday
				+ ", province=" + province + ", city=" + city + ", location="
				+ location + ", sex=" + sex + ", state=" + state
				+ ", createTime=" + createTime + ", lastModifyTime="
				+ lastModifyTime + ", lastLoginTime=" + lastLoginTime +
                ",portrait="+portrait+"   ]";
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

}
