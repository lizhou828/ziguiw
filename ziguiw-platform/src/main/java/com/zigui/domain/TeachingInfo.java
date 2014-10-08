package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * create table TEACHER_TEACHING_SITUATION(
 * TEACHING_ID		INTEGER,
 * Bj_id		INTEGER,
 * SUBJECT_id		INTEGER,
 * zh_ID		INTEGER,
 * XXID		varchar(50)
 * );
 * 
 * @author bertyang
 *
 */
@Entity
@Table(name="TEACHER_TEACHING_SITUATION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TeachingInfo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="TEACHING_ID")
	private int TEACHING_ID;
	
	@Column(name="Bj_id")
	private int Bj_id;
	
	@Column(name="SUBJECT_id")
	private int SUBJECT_id;
	
	@Column(name="zh_ID")
	private int zh_ID;
	
	@Column(name="XXID")
	private String XXID;

	

}
