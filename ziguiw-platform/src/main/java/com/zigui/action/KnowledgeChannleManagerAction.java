package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.service.impl.KnowledgeChannleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class KnowledgeChannleManagerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 611155183026450263L;
	
	@Autowired
	private KnowledgeChannleServiceImpl knowledgeChannleService;
	
	private long fromChannle;
	
	private long toChannle;
	
	private String startCreateTime;
	
	private String endCreateTime;
	
	public String move(){
		knowledgeChannleService.move(fromChannle, toChannle, startCreateTime, endCreateTime);
		
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
