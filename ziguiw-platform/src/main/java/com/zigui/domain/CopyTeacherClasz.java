package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="R_teacher_class")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CopyTeacherClasz {
	
	@Id
	private int rtc_id;
	
	private int Bj_id;
	
	private int teacherID;

	public int getRtc_id() {
		return rtc_id;
	}

	public void setRtc_id(int rtc_id) {
		this.rtc_id = rtc_id;
	}

	public int getBj_id() {
		return Bj_id;
	}

	public void setBj_id(int bj_id) {
		Bj_id = bj_id;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	
}
