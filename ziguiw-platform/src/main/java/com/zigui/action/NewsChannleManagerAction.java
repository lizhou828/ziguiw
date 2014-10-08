package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.service.impl.NewsChannleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class NewsChannleManagerAction extends ActionSupport {
	
	@Autowired
	private NewsChannleServiceImpl newsChannleService;

	private static final long serialVersionUID = 2235076385431296078L;

	private long fromChannle;
	
	private long toChannle;
	
	private String startCreateTime;
	
	private String endCreateTime;
	
	public String move(){
		newsChannleService.move(fromChannle, toChannle, startCreateTime, endCreateTime);
		
		return Action.SUCCESS;
	}

	public long getFromChannle() {
		return fromChannle;
	}

	public void setFromChannle(long fromChannle) {
		this.fromChannle = fromChannle;
	}

	public long getToChannle() {
		return toChannle;
	}

	public void setToChannle(long toChannle) {
		this.toChannle = toChannle;
	}

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
}
