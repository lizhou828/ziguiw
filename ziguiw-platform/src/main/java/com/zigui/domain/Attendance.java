package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create table STUDENT_ATTENDANCE_RECORD(
 * ATTEN_RECO_ID		NUMBER(20),
 * XS_ID			NUMBER(10),
 * SMS_CONTENT			VARCHAR2(200),
 * READCARD_TIME			DATE,
 * IN_OUT_STATE			VARCHAR2(2),
 * RESULT			varchar2(20),
 * XXID		varchar2(50),
 * CARDID			varchar2(30),
 * ATTEN_SET_ID			integer,
 * READCARD_TIMEID			NUMBER(9),
 * UPLOAD_TIME			Date
 * );
 * @author bertyang
 *
 */
@Entity
@Table(name="STUDENT_ATTENDANCE_RECORD")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Attendance implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long ATTEN_RECO_ID;
    private long XS_ID;
    private String STUDENT_NAME;
    private String SMS_CONTENT;
    private Date READCARD_TIME; //读卡时间
    private Date READCARD_DATE;//读卡日期  取READCARD_TIME里面的年月日
    private String IN_OUT_STATE;     //0-出，1-进
    private String RESULT;
    private String XXID;
    private String CARDID;
    private int ATTEN_SET_ID;
    private int READCARD_TIMEID;
    private Date UPLOAD_TIME;
    public long getATTEN_RECO_ID() {
        return ATTEN_RECO_ID;
    }
    public void setATTEN_RECO_ID(long aTTEN_RECO_ID) {
        ATTEN_RECO_ID = aTTEN_RECO_ID;
    }
    public long getXS_ID() {
        return XS_ID;
    }
    public void setXS_ID(long xS_ID) {
        XS_ID = xS_ID;
    }
    public String getSMS_CONTENT() {
        return SMS_CONTENT;
    }
    public void setSMS_CONTENT(String sMS_CONTENT) {
        SMS_CONTENT = sMS_CONTENT;
    }
    public String getREADCARD_TIME() {
        return new SimpleDateFormat("HH:mm:ss").format(READCARD_TIME);
    }
    public void setREADCARD_TIME(Date rEADCARD_TIME) {
        READCARD_TIME = rEADCARD_TIME;
    }
    public String getIN_OUT_STATE() {
        return IN_OUT_STATE;
    }
    public void setIN_OUT_STATE(String iN_OUT_STATE) {
        IN_OUT_STATE = iN_OUT_STATE;
    }
    public String getRESULT() {
        return RESULT;
    }
    public void setRESULT(String rESULT) {
        RESULT = rESULT;
    }
    public String getXXID() {
        return XXID;
    }
    public void setXXID(String xXID) {
        XXID = xXID;
    }
    public String getCARDID() {
        return CARDID;
    }
    public void setCARDID(String cARDID) {
        CARDID = cARDID;
    }
    public int getATTEN_SET_ID() {
        return ATTEN_SET_ID;
    }
    public void setATTEN_SET_ID(int aTTEN_SET_ID) {
        ATTEN_SET_ID = aTTEN_SET_ID;
    }
    public int getREADCARD_TIMEID() {
        return READCARD_TIMEID;
    }
    public void setREADCARD_TIMEID(int rEADCARD_TIMEID) {
        READCARD_TIMEID = rEADCARD_TIMEID;
    }
    public Date getUPLOAD_TIME() {
        return UPLOAD_TIME;
    }
    public void setUPLOAD_TIME(Date uPLOAD_TIME) {
        UPLOAD_TIME = uPLOAD_TIME;
    }

    public String getSTUDENT_NAME() {
        return STUDENT_NAME;
    }

    public void setSTUDENT_NAME(String STUDENT_NAME) {
        this.STUDENT_NAME = STUDENT_NAME;
    }

    public String getREADCARD_DATE() {
        return new SimpleDateFormat("yyyy-MM-dd").format(READCARD_TIME);
    }

    public void setREADCARD_DATE(Date READCARD_DATE) {
        this.READCARD_DATE = READCARD_DATE;
    }
}
