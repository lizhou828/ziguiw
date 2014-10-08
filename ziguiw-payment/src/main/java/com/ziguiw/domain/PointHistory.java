package com.ziguiw.domain;

import java.util.Date;


public class PointHistory {
	
	private Integer userId;
	private Integer point_chang;
	private int type;
	private int state;
	private int flag;
	private Date chang_date;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPoint_chang() {
		return point_chang;
	}
	public void setPoint_chang(Integer point_chang) {
		this.point_chang = point_chang;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Date getChang_date() {
		return chang_date;
	}
	public void setChang_date(Date chang_date) {
		this.chang_date = chang_date;
	}
	
	
	
}
