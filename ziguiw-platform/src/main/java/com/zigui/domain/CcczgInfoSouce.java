/**
 * 
 */
package com.zigui.domain;

import com.zigui.utils.HtmlUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;





/**
 * @author ljy
 * 资源实体
 */
@Entity
@Table(name="ccczInfoSouce")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CcczgInfoSouce {
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	private String subjectId;//主键




   /* 
	@ManyToOne
	@JoinColumn(name="NJ_ID")
	//private Grade souceStugrade;//年级
	
	@ManyToOne
	@JoinColumn(name="T_S_SUBJECT_ID")
	//private Subject souceSubject;//科目
*/
	
	@ManyToOne
	@JoinColumn(name="NJ_ID")
	private SouceStugrade souceStugrade;//年级
	
	@ManyToOne
	@JoinColumn(name="T_S_SUBJECT_ID")
	private SouceSubject souceSubject;//科目
	
	@ManyToOne
	@JoinColumn(name="RES_ID")
	private SourceType sourceType;//类型
	
	@ManyToOne
	@JoinColumn(name="MEMBERID")
	private UserBase member;//会员
	
	@ManyToOne
	@JoinColumn(name="VERSION_ID")
	private SouceVersion souceVersion;//版本
	
	
	@OneToMany(mappedBy="ccczgInfoSouce",fetch=FetchType.EAGER)
	private Set<CcczgInfoSousown> ccczgInfoSousown = new HashSet<CcczgInfoSousown>();
	

	//外部资源文件标识
	private String ftpFileLocal;
	
	//是否是外部资源
	private Integer sourceTypes;
	private Date createDate;
	private Date modifyDate;
	private String title;
	private String resourcePath;
	private Long resourceDownnum;
	private String resourceSize;
	private String resourceImg;
	private Long orderList;
	private String resourceSwfpath;
	private String resourceNotice;
	private String uploadip;
	private Long delsign=Long.valueOf(0);
	private Long checkSign=Long.valueOf(0);
	private String checkDate;
	private Long needpoint;
	private Long recommend=Long.valueOf(0);
	private String keys;
	private String sourcefileType;
	private int flag;
	private String resourcePdfpath;
	private String netPath;
	
	

	
	// Constructors
	
	public int getFlag() {
		return flag;
	}



	public CcczgInfoSouce(String subjectId, SouceStugrade souceStugrade,
			SouceSubject souceSubject, SourceType sourceType, UserBase member,
			SouceVersion souceVersion, Set<CcczgInfoSousown> ccczgInfoSousown,
			String ftpFileLocal, Integer sourceTypes, Date createDate,
			Date modifyDate, String title, String resourcePath,
			Long resourceDownnum, String resourceSize, String resourceImg,
			Long orderList, String resourceSwfpath, String resourceNotice,
			String uploadip, Long delsign, Long checkSign, String checkDate,
			Long needpoint, Long recommend, String keys, String sourcefileType,
			int flag, String resourcePdfpath, String netPath) {
		super();
		this.subjectId = subjectId;
		this.souceStugrade = souceStugrade;
		this.souceSubject = souceSubject;
		this.sourceType = sourceType;
		this.member = member;
		this.souceVersion = souceVersion;
		this.ccczgInfoSousown = ccczgInfoSousown;
		this.ftpFileLocal = ftpFileLocal;
		this.sourceTypes = sourceTypes;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.title = title;
		this.resourcePath = resourcePath;
		this.resourceDownnum = resourceDownnum;
		this.resourceSize = resourceSize;
		this.resourceImg = resourceImg;
		this.orderList = orderList;
		this.resourceSwfpath = resourceSwfpath;
		this.resourceNotice = resourceNotice;
		this.uploadip = uploadip;
		this.delsign = delsign;
		this.checkSign = checkSign;
		this.checkDate = checkDate;
		this.needpoint = needpoint;
		this.recommend = recommend;
		this.keys = keys;
		this.sourcefileType = sourcefileType;
		this.flag = flag;
		this.resourcePdfpath = resourcePdfpath;
		this.netPath = netPath;
	}

	public SouceStugrade getSouceStugrade() {
		return souceStugrade;
	}

	public void setSouceStugrade(SouceStugrade souceStugrade) {
		this.souceStugrade = souceStugrade;
	}

	public SouceSubject getSouceSubject() {
		return souceSubject;
	}

	public void setSouceSubject(SouceSubject souceSubject) {
		this.souceSubject = souceSubject;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Set getCcczgInfoSousown() {
		return ccczgInfoSousown;
	}

	public void setCcczgInfoSousown(Set ccczgInfoSousown) {
		this.ccczgInfoSousown = ccczgInfoSousown;
	}

	public String getSourcefileType() {
		return sourcefileType;
	}

	public void setSourcefileType(String sourcefileType) {
		this.sourcefileType = sourcefileType;
	}

	/** default constructor */
	public CcczgInfoSouce() {
	}

	// Property accessors

	
    public Integer getSourceTypes() {
        return sourceTypes;
    }

    public void setSourceTypes(Integer sourceTypes) {
        this.sourceTypes = sourceTypes;
    }
	

	public String getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}



	public UserBase getMember() {
		return member;
	}

	public void setMember(UserBase member) {
		this.member = member;
	}



	public SourceType getSourceType() {
		return sourceType;
	}

	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}

	public SouceVersion getSouceVersion() {
		return souceVersion;
	}

	public void setSouceVersion(SouceVersion souceVersion) {
		this.souceVersion = souceVersion;
	}

	

	

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getTitle() {
		return HtmlUtil.splitAndFilterString(this.title,-1);
	}

	public void setTitle(String title) {
		this.title = HtmlUtil.splitAndFilterString(title,-1);
	}

	public String getResourcePath() {
		return this.resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public Long getResourceDownnum() {
		return this.resourceDownnum;
	}

	public void setResourceDownnum(Long resourceDownnum) {
		this.resourceDownnum = resourceDownnum;
	}

	public String getResourceSize() {
		return this.resourceSize;
	}

	public void setResourceSize(String resourceSize) {
		this.resourceSize = resourceSize;
	}

	public String getResourceImg() {
		return this.resourceImg;
	}

	public void setResourceImg(String resourceImg) {
		this.resourceImg = resourceImg;
	}

	public Long getOrderList() {
		return this.orderList;
	}

	public void setOrderList(Long orderList) {
		this.orderList = orderList;
	}

	public String getResourceSwfpath() {
		return this.resourceSwfpath;
	}

	public void setResourceSwfpath(String resourceSwfpath) {
		this.resourceSwfpath = resourceSwfpath;
	}

	public String getResourceNotice() {
		return HtmlUtil.splitAndFilterString(this.resourceNotice,-1);
	}

	public void setResourceNotice(String resourceNotice) {
		this.resourceNotice = HtmlUtil.splitAndFilterString(resourceNotice,-1);
	}

	public String getUploadip() {
		return this.uploadip;
	}

	public void setUploadip(String uploadip) {
		this.uploadip = uploadip;
	}

	public Long getDelsign() {
		return this.delsign;
	}

	public void setDelsign(Long delsign) {
		this.delsign = delsign;
	}

	public Long getCheckSign() {
		return this.checkSign;


	}

	public void setCheckSign(Long checkSign) {
		this.checkSign = checkSign;
	}



	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public Long getNeedpoint() {
		return this.needpoint;
	}

	public void setNeedpoint(Long needpoint) {
		this.needpoint = needpoint;
	}

	public Long getRecommend() {
		return this.recommend;
	}

	public void setRecommend(Long recommend) {
		this.recommend = recommend;
	}

	public String getKeys() {
		return this.keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getResourcePdfpath() {
		return resourcePdfpath;
	}

	public void setResourcePdfpath(String resourcePdfpath) {
		this.resourcePdfpath = resourcePdfpath;
	}

	public String getNetPath() {
		return netPath;
	}

	public void setNetPath(String netPath) {
		this.netPath = netPath;
	}
    public String getFtpFileLocal() {
        return ftpFileLocal;
    }

    public void setFtpFileLocal(String ftpFileLocal) {
        this.ftpFileLocal = ftpFileLocal;
    }

	
	
	
}
