package com.zigui.domain;

/**
 * ConsumeType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
 //消费类别表
public class StudentConsumeType implements java.io.Serializable {

	// Fields

	private Long consid;
	private String consname;      //类别名称
	private String isflag;         //是否禁用 0：禁用，1：启用
	private String remark;        //备注
	private String xxid;          //学校id，默认为 '-1' 公共

	// Constructors

	/** default constructor */
	public StudentConsumeType() {
	}

	/** full constructor */
	public StudentConsumeType(String consname, String isflag, String remark,
                              String xxid) {
		this.consname = consname;
		this.isflag = isflag;
		this.remark = remark;
		this.xxid = xxid;
	}

	// Property accessors

	public Long getConsid() {
		return this.consid;
	}

	public void setConsid(Long consid) {
		this.consid = consid;
	}

	public String getConsname() {
		return this.consname;
	}

	public void setConsname(String consname) {
		this.consname = consname;
	}

	public String getIsflag() {
		return this.isflag;
	}

	public void setIsflag(String isflag) {
		this.isflag = isflag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getXxid() {
		return this.xxid;
	}

	public void setXxid(String xxid) {
		this.xxid = xxid;
	}

}