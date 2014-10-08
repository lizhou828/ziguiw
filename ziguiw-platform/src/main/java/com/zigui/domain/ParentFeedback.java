package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * create table T_M_pFeedback(
 * FEEDBACK_ID		NUMBER(9),
 * FEEDBACK_CONTENT			varchar2(400),
 * Xs_id		NUMBER(9),
 * MOBILEPHONE			varchar2(20),
 * FEEDBACK_DATE			DATE,
 * REPLY_SMS_ID			NUMBER,
 * XXID		varchar2(50),
 * isAuditing			varchar2(1),
 * PARENT_REPLY_SOURCE			varchar2(1)
 * );
 * @author bertyang
 *
 */
@Entity
@Table(name="T_M_pFeedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ParentFeedback implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int FEEDBACK_ID;
	private String FEEDBACK_CONTENT;
	private int Xs_id;
	private String MOBILEPHONE;
	private Date FEEDBACK_DATE;
	private int REPLY_SMS_ID;
	private String XXID;
	private String isAuditing;
	private String PARENT_REPLY_SOURCE;
	
	

}
