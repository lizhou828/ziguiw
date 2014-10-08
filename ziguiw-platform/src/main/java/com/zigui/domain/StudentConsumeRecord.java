package com.zigui.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ConsumeRecordsFlow entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class StudentConsumeRecord implements java.io.Serializable {

	// Fields

	private String consRecoId;    //消费记录流水表
	private String recordId;      //唯一流水号 2003102600001
	private String accountId;     //用户帐号
	private String cardNumber;   //卡号
	private String posId;         //Pos机号 01 001 01为站号
	private String consState;    //消费类型 0－消费，1－充值，2－取款，3－补助, 4－挂失，5－解挂，6－开户，7－撤户，8－纠错，9－换卡，10－补卡，11－变更，12－登陆，13－正常退出，14－非法退出
	private Date consTime;       //流水发生时间
	private Date gathertime;     //采集时间
	private Double oldMoney;     //原余额
	private Double consFare;      //交易发生额
	private Double XMoney;       //现在余额
	private String operId;         //操作员号
	private String stationId;      //工作站号
	private String netId;          //一卡通号
	private Long fktime;          //写卡次数（ic）
	private String cpbh;          //菜品编号
	private Double cpcount;     //菜品金额
	private String xxid;         //学校编号
	private String actionType;  //动作类型 1：消费，0缴费.默认为1
	private Date uploadTime;      //数据上传时间

	// Constructors

	/** default constructor */
	public StudentConsumeRecord() {
	}

	/** full constructor */
	public StudentConsumeRecord(String recordId, String accountId,
                                String cardNumber, String posId, String consState, Date consTime,
                                Date gathertime, Double oldMoney, Double consFare, Double XMoney,
                                String operId, String stationId, String netId, Long fktime,
                                String cpbh, Double cpcount, String xxid, String actionType, Date uploadTime) {
		this.recordId = recordId;
		this.accountId = accountId;
		this.cardNumber = cardNumber;
		this.posId = posId;
		this.consState = consState;
		this.consTime = consTime;
		this.gathertime = gathertime;
		this.oldMoney = oldMoney;
		this.consFare = consFare;
		this.XMoney = XMoney;
		this.operId = operId;
		this.stationId = stationId;
		this.netId = netId;
		this.fktime = fktime;
		this.cpbh = cpbh;
		this.cpcount = cpcount;
		this.xxid = xxid;
		this.actionType = actionType;
		this.uploadTime = uploadTime;
	}

	// Property accessors

	public String getConsRecoId() {
		return this.consRecoId;
	}

	public void setConsRecoId(String consRecoId) {
		this.consRecoId = consRecoId;
	}

	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPosId() {
		return this.posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

    //消费类型 0－消费，1－充值，2－取款，3－补助, 4－挂失，5－解挂，6－开户，7－撤户，8－纠错，9－换卡，
    // 10－补卡，11－变更，12－登陆，13－正常退出，14－非法退出
	public String getConsState() {
        if(this.consState == null && this.consState.equals("")){
           return "";
        }
        if(this.consState.equals("0")){
            return "消费";
        }else if(this.consState.equals("1")){
            return "充值";
        }else if(this.consState.equals("2")){
            return "取款";
        }else if(this.consState.equals("3")){
            return "补助";
        }else if(this.consState.equals("4")){
            return "挂失";
        }else if(this.consState.equals("5")){
            return "解挂";
        }else if(this.consState.equals("6")){
            return "开户";
        }else if(this.consState.equals("7")){
            return "撤户";
        }else if(this.consState.equals("8")){
            return "纠错";
        }else if(this.consState.equals("9")){
            return "换卡";
        }else if(this.consState.equals("10")){
            return "补卡";
        }else if(this.consState.equals("11")){
            return "变更";
        }else if(this.consState.equals("12")){
            return "登录";
        }else if(this.consState.equals("13")){
            return "正常退出";
        }
		return "非法退出";
	}

	public void setConsState(String consState) {
		this.consState = consState;
	}

	public String getConsTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(consTime);
	}

	public void setConsTime(Date consTime) {
		this.consTime = consTime;
	}

	public Date getGathertime() {
		return this.gathertime;
	}

	public void setGathertime(Date gathertime) {
		this.gathertime = gathertime;
	}

	public Double getOldMoney() {
		return this.oldMoney;
	}

	public void setOldMoney(Double oldMoney) {
		this.oldMoney = oldMoney;
	}

	public Double getConsFare() {
		return this.consFare;
	}

	public void setConsFare(Double consFare) {
		this.consFare = consFare;
	}

	public Double getXMoney() {
		return this.XMoney;
	}

	public void setXMoney(Double XMoney) {
		this.XMoney = XMoney;
	}

	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getStationId() {
		return this.stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getNetId() {
		return this.netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public Long getFktime() {
		return this.fktime;
	}

	public void setFktime(Long fktime) {
		this.fktime = fktime;
	}

	public String getCpbh() {
		return this.cpbh;
	}

	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}

	public Double getCpcount() {
		return this.cpcount;
	}

	public void setCpcount(Double cpcount) {
		this.cpcount = cpcount;
	}

	public String getXxid() {
		return this.xxid;
	}

	public void setXxid(String xxid) {
		this.xxid = xxid;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

}