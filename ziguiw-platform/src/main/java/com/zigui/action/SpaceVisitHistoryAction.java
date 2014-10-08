package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.SpaceVisitHistory;
import com.zigui.domain.UserBase;
import com.zigui.service.impl.SpaceVisitHistoryServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class SpaceVisitHistoryAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private SpaceVisitHistoryServiceImpl spaceVisitHistoryService;
	
	@Autowired
	private UserServiceImpl userService;
	
	private SpaceVisitHistory spaceVisitHistory;
	private long hostUserId;
	private List<SpaceVisitHistory> spaceVisitHistorys;
	
	private String hostUserName;
	
	
	public String saveOrUpdate(){
		spaceVisitHistoryService.saveOrUpdate(spaceVisitHistory);
		
		return Action.SUCCESS;
	}
	
	public String getVisitHistory(){
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		UserBase toUser = userService.getUserBaseByNickName(hostUserName);
		spaceVisitHistorys = spaceVisitHistoryService.getVisitHistory(toUser);
		
		return Action.SUCCESS;
	}
	
	public SpaceVisitHistory getSpaceVisitHistory() {
		return spaceVisitHistory;
	}

	public void setSpaceVisitHistory(SpaceVisitHistory spaceVisitHistory) {
		this.spaceVisitHistory = spaceVisitHistory;
	}

	public List<SpaceVisitHistory> getSpaceVisitHistorys() {
		return spaceVisitHistorys;
	}

	public void setSpaceVisitHistorys(List<SpaceVisitHistory> spaceVisitHistorys) {
		this.spaceVisitHistorys = spaceVisitHistorys;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}
	
	

}
