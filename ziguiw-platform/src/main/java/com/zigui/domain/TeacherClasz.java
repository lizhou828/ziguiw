package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name="R_teacher_class")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TeacherClasz {
	
	@Id
	private int rtc_id;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name = "Bj_id")
	private Clasz clasz;
	
	private int teacherID;

	public int getRtc_id() {
		return rtc_id;
	}

	public void setRtc_id(int rtc_id) {
		this.rtc_id = rtc_id;
	}


	public Clasz getClasz() {
		return clasz;
	}

	public void setClasz(Clasz clasz) {
		this.clasz = clasz;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	
}
