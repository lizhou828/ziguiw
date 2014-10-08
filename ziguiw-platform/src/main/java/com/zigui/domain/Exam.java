package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create table T_examination(
 * EXAM_id		integer,
 * TERM_ID			varchar2(20),
 * EXAM_NAME			varchar2(100),
 * TYPE_ID			integer,
 * CREATOR_ID			integer,
 * CREATOR_ROLE_ID			integer,
 * IdStr			varchar2(2000),
 * EXAM_DATE			varchar2(50),
 * XxID		varchar2(50),
 * IS_RECORD_SCORE			VARCHAR2(1),
 * IS_FINAL_EXAM			VARCHAR2(1),
 * CREATE_EXAM_DATE			DATE
 * );
 * @author bertyang
 *
 */
@Entity
@Table(name="T_examination")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Exam implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int EXAM_id;
	
	private String TERM_ID;
	
	private String EXAM_NAME;
	
	private int type;
	
	private int CREATOR_ID;
	
	private int CREATOR_ROLE_ID;
	
	private String IdStr;
	
	private String EXAM_DATE;
	
	private String XxID;
	
	private String IS_RECORD_SCORE;
	
	private String IS_FINAL_EXAM;
	
	private Date CREATE_EXAM_DATE;

	public int getEXAM_id() {
		return EXAM_id;
	}

	public void setEXAM_id(int eXAM_id) {
		EXAM_id = eXAM_id;
	}

	public String getTERM_ID() {
		return TERM_ID;
	}

	public void setTERM_ID(String tERM_ID) {
		TERM_ID = tERM_ID;
	}

	public String getEXAM_NAME() {
		return EXAM_NAME;
	}

	public void setEXAM_NAME(String eXAM_NAME) {
		EXAM_NAME = eXAM_NAME;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCREATOR_ID() {
		return CREATOR_ID;
	}

	public void setCREATOR_ID(int cREATOR_ID) {
		CREATOR_ID = cREATOR_ID;
	}

	public int getCREATOR_ROLE_ID() {
		return CREATOR_ROLE_ID;
	}

	public void setCREATOR_ROLE_ID(int cREATOR_ROLE_ID) {
		CREATOR_ROLE_ID = cREATOR_ROLE_ID;
	}

	public String getIdStr() {
		return IdStr;
	}

	public void setIdStr(String idStr) {
		IdStr = idStr;
	}

	public String getEXAM_DATE() {
		return EXAM_DATE;
	}

	public void setEXAM_DATE(String eXAM_DATE) {
		EXAM_DATE = eXAM_DATE;
	}

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		XxID = xxID;
	}

	public String getIS_RECORD_SCORE() {
		return IS_RECORD_SCORE;
	}

	public void setIS_RECORD_SCORE(String iS_RECORD_SCORE) {
		IS_RECORD_SCORE = iS_RECORD_SCORE;
	}

	public String getIS_FINAL_EXAM() {
		return IS_FINAL_EXAM;
	}

	public void setIS_FINAL_EXAM(String iS_FINAL_EXAM) {
		IS_FINAL_EXAM = iS_FINAL_EXAM;
	}

	public String getCREATE_EXAM_DATE() {
        if(CREATE_EXAM_DATE == null || "".equals(CREATE_EXAM_DATE )){
            return "";
        }
		return new SimpleDateFormat("yyyy-MM-dd").format(CREATE_EXAM_DATE);
	}

	public void setCREATE_EXAM_DATE(Date cREATE_EXAM_DATE) {
		CREATE_EXAM_DATE = cREATE_EXAM_DATE;
	}
	
	

}
