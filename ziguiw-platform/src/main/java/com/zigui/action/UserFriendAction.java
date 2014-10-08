package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserFriend;
import com.zigui.service.impl.UserFriendServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UserFriendAction extends BaseAction implements ServletContextAware {

	private static final long serialVersionUID = 2097031956441512157L;
	
	private Map<String, String> query;
	
	private String orderField = "createTime";

	private String orderType = "desc";
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String hostUserName;
	
	private UserFriend userFriend;

	private Page<UserFriend> userFriends = new Page<UserFriend>();
	
	private ServletContext context;
	
	@Autowired
	private UserFriendServiceImpl userFriendService;
	
	@Autowired
	private UserServiceImpl userService;
	
	public String addFriendAjax(){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        user = (UserBase)session.get("VALID_USER");
        
        UserBase friend = userService.getUserBaseByNickName(hostUserName);
        
        userFriend = userFriendService.getFriendRelation(user, friend);
        if(userFriend != null && userFriend.getId() > 0){
        	return Action.SUCCESS;
        }
        
        UserFriend userFriend = new UserFriend();
        userFriend.setUser(user);
        userFriend.setFriendUser(friend);
        
        userFriendService.saveOrUpdate(userFriend);
        
//        try {
//			print(response,"success");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        
        return Action.SUCCESS;
	}
	
	public String getFriendRelationAjax(){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        user = (UserBase)session.get("VALID_USER");
        
        UserBase friend = userService.getUserBaseByNickName(hostUserName);
        
        userFriend = userFriendService.getFriendRelation(user, friend);
        
//        try {
//			print(response, "" + (userFriend != null && userFriend.getId() > 0));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        
        return Action.SUCCESS;
	}
	
	public String removeFriendAjax(){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        user = (UserBase)session.get("VALID_USER");
        
        UserBase friend = userService.getUserBaseByNickName(hostUserName);
        
        userFriend = userFriendService.getFriendRelation(user, friend);
        
        userFriendService.delete(userFriend);
        
//        try {
//			print(response,"success");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        
        return Action.SUCCESS;
	}
	
	protected void print(HttpServletResponse response, String info) throws IOException {
        try {
        	  response.setHeader("Cache-Control", "no-cache");
        	  response.setContentType("text/json;charset=utf-8");
              response.getWriter().print(info);
        } catch (IOException e) {
                e.printStackTrace();
                throw e;
        }
     }
	
	public String getMyFriend(){
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map<String, Object> session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		UserBase user = userService.getUserBaseByNickName(hostUserName);
		
		userFriends = userFriendService.getMyFriend(NumberUtils.toInt(currentPage), NumberUtils.toInt(countPerPage), user);
		
		return Action.SUCCESS;
	}
	
//	public String getFriend() {
//		if(StringUtils.isEmpty(hostUserName)){
//			ActionContext ctx = ActionContext.getContext();
//	        Map session = ctx.getSession();
//	        UserBase user = (UserBase)session.get("VALID_USER");
//	        
//	        hostUserName = user.getNickName();
//		}
//		
//		user = userService.getUserBaseByNickName(hostUserName);
//		
//		if(user!=null && user.getId()>0){
//			query.put("userId", new Long(user.getId()).toString());
//			userFriends = userFriendService.listByCondition(query, new Integer(currentPage).intValue(),
//					new Integer(countPerPage).intValue(), orderField, false,this.queryString);
//		}
//		return Action.SUCCESS;
//	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public UserFriend getUserFriend() {
		return userFriend;
	}

	public void setUserFriend(UserFriend userFriend) {
		this.userFriend = userFriend;
	}

	public Page<UserFriend> getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(Page<UserFriend> userFriends) {
		this.userFriends = userFriends;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}
	
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	

}
