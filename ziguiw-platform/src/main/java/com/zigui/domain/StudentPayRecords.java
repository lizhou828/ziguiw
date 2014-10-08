package com.zigui.domain;

import java.util.Date;

/**
 * ConsumerPayRecords entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class StudentPayRecords implements java.io.Serializable {

    // Fields

    private String payRecoId;    //缴费记录表
    private String recordId;      //唯一流水号 2003102600002
    private String accountId;     //用户帐号
    private String cardNumber;   //卡号
    private Date payTime;         //缴费时间2011-04-12 08:10
    private Date consTime;         //流水发生时间
    private Double payMoney;       //缴费金额
    private String payState;      //缴费状态，0：失败，1：成功
    private String posId;          //Pos机号 01 001 01为站号
    private String xxid;
    private double XMoney;       //剩余金额

    // Constructors

    /** default constructor */
    public StudentPayRecords() {
    }

    /** full constructor */
    public StudentPayRecords(String recordId, String accountId,
                             String cardNumber, Date payTime, Date consTime, Double payMoney,
                             String payState, String posId, String xxid,double xmoney) {
        this.recordId = recordId;
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.payTime = payTime;
        this.consTime = consTime;
        this.payMoney = payMoney;
        this.payState = payState;
        this.posId = posId;
        this.xxid = xxid;
        this.XMoney=xmoney;
    }

    // Property accessors

    public String getPayRecoId() {
        return this.payRecoId;
    }

    public void setPayRecoId(String payRecoId) {
        this.payRecoId = payRecoId;
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

    public Date getPayTime() {
        return this.payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getConsTime() {
        return this.consTime;
    }

    public void setConsTime(Date consTime) {
        this.consTime = consTime;
    }

    public Double getPayMoney() {
        return this.payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayState() {
        return this.payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getPosId() {
        return this.posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getXxid() {
        return this.xxid;
    }

    public void setXxid(String xxid) {
        this.xxid = xxid;
    }

    public double getXMoney() {
        return XMoney;
    }

    public void setXMoney(double XMoney) {
        this.XMoney = XMoney;
    }
}