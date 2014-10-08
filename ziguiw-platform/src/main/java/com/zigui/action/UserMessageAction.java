package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserMessage;
import com.zigui.service.impl.UserMessageServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class UserMessageAction extends BaseAction {

	private static final long serialVersionUID = 3282813483724955070L;

	private UserMessage message;
	
	private Page<UserMessage> messages;
	
	private int pageSize = 10;
	
	private int currentPage = 1;
	
	private long hostUserId;
	
	private String hostUserName;
	
	@Autowired
	private UserMessageServiceImpl userMessageService;
	
	@Autowired
	private UserServiceImpl userService;
	
	private String encoderdHostUserName;
	
	private String mailType;
	
	/**
	 * 获取留言板消息
	 * 
	 * @return
	 */
	public String getMessageList(){
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		messages = userMessageService.getMessagesByNickName(currentPage, pageSize, hostUserName, queryString);
		return Action.SUCCESS;
	}
	
	/**
	 * 获取站内信消息
	 * 
	 * @return
	 */
	public String getMailList(){
		ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        hostUserName = user.getNickName();
        
        if(user == null || user.getId() < 1){
        	return "USER_NO_LOGIN";
        }
		
		messages = userMessageService.getMailByNickName(currentPage, pageSize, user, mailType, queryString);
		return Action.SUCCESS;
	}
	
	public void validateSave(){
		if(StringUtils.equals(StringUtils.trimToEmpty(message.getText()), "")){
			this.addFieldError("message.text", "留言内容不能为空");
			return;
		}
	}
	
	public String save(){
		ActionContext ctx = ActionContext.getContext();
        Map<String, Object> session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        
        if(user == null || user.getId() < 1){
        	return "USER_NO_LOGIN";
        }
		
        message.setFormUser(user);
        
        UserBase hostUser = new UserBase();
        if(hostUserId == 0){
	        hostUserId = user.getId();
	    }
        
        hostUser = userService.getUserBaseById(hostUserId);
        
        hostUserName = hostUser.getNickName();
        
        message.setToUser(hostUser);
        
        if(message.getParentMsg() == null || message.getParentMsg().getId() == 0L){
        	message.setKind(1);
        }else{
        	message.setKind(2);
        }
                
		userMessageService.save(message);
		
		return Action.SUCCESS;
	}
	
	public String saveMail(){
		ActionContext ctx = ActionContext.getContext();
        Map<String, Object> session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        
        if(user == null || user.getId() < 1){
        	return "USER_NO_LOGIN";
        }
		
      
    	message.setKind(8);
        message.setFormUser(user);
        
		userMessageService.save(message);
		
		return Action.SUCCESS;
	}
	
	public String delMessageById(){
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        if(user != null && user.getId() >= 1){
	        	hostUserName = user.getNickName();
	        }
		}
		
		userMessageService.delete(message.getId());
		
		return Action.SUCCESS;
	}

	public UserMessage getMessage() {
		return message;
	}

	public void setMessage(UserMessage message) {
		this.message = message;
	}

	public Page<UserMessage> getMessages() {
		return messages;
	}

	public void setMessages(Page<UserMessage> messages) {
		this.messages = messages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getHostUserId() {
		return hostUserId;
	}

	public void setHostUserId(long hostUserId) {
		this.hostUserId = hostUserId;
	}

	public void setUserMessageService(UserMessageServiceImpl userMessageService) {
		this.userMessageService = userMessageService;
	}

	public UserMessageServiceImpl getUserMessageService() {
		return userMessageService;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}
	
	public String getEncoderdHostUserName() {
		try {
			return URLEncoder.encode(this.getHostUserName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return this.getHostUserName();
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	
	
	
}
