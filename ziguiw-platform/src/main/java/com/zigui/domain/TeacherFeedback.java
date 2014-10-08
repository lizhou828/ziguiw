package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * create table TEACHER_SMS_FEEDBACK(
 * FEEDBACK_ID		NUMBER(9),
 * ACCOUNT_ID		NUMBER(9),
 * MOBILEPHONE			varchar2(20),
 * FEEDBACK_CONTTENT			varchar2(200),
 * FEEDBACK_DATE			DATE,
 * XXID		varchar2(50)
);
 * @author bertyang
 *
 */
@Entity
@Table(name="TEACHER_SMS_FEEDBACK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TeacherFeedback implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int FEEDBACK_ID;
	private int ACCOUNT_ID;
	private String MOBILEPHONE;
	private String FEEDBACK_CONTTENT;
	private Date FEEDBACK_DATE;
	private String XXID;
	public int getFEEDBACK_ID() {
		return FEEDBACK_ID;
	}
	public void setFEEDBACK_ID(int fEEDBACK_ID) {
		FEEDBACK_ID = fEEDBACK_ID;
	}
	public int getACCOUNT_ID() {
		return ACCOUNT_ID;
	}
	public void setACCOUNT_ID(int aCCOUNT_ID) {
		ACCOUNT_ID = aCCOUNT_ID;
	}
	public String getMOBILEPHONE() {
		return MOBILEPHONE;
	}
	public void setMOBILEPHONE(String mOBILEPHONE) {
		MOBILEPHONE = mOBILEPHONE;
	}
	public String getFEEDBACK_CONTTENT() {
		return FEEDBACK_CONTTENT;
	}
	public void setFEEDBACK_CONTTENT(String fEEDBACK_CONTTENT) {
		FEEDBACK_CONTTENT = fEEDBACK_CONTTENT;
	}
	public Date getFEEDBACK_DATE() {
		return FEEDBACK_DATE;
	}
	public void setFEEDBACK_DATE(Date fEEDBACK_DATE) {
		FEEDBACK_DATE = fEEDBACK_DATE;
	}
	public String getXXID() {
		return XXID;
	}
	public void setXXID(String xXID) {
		XXID = xXID;
	}
	
	
}
