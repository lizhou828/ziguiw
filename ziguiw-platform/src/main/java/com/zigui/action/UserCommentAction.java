package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.Administrator;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserComment;
import com.zigui.service.impl.UserCommentServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class UserCommentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private UserComment message;
	
	private Page<UserComment> messages;
	
	private int pageSize = 10;
	
	private int currentPage = 1;
	
	private long hostUserId;
	
	private String hostUserName;
	
	private long commentedId;
	
	private long albumId;
	
	private long photoId;
	
	private int type;
	
	private UserBase loginUser;
	
	@Autowired
	private UserCommentServiceImpl userCommentService;
	
	@Autowired
	private UserServiceImpl userService;
	
	public String getMessageList(){
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        if(user != null){
	        	hostUserName = user.getNickName();
	        }
		}
		
		if(type == 1 && commentedId < 1){
			commentedId = photoId;
		}
		
		
		messages = userCommentService.getMessagesByCommendedId(currentPage, pageSize, type, commentedId, queryString);
		return Action.SUCCESS;
	}
	
	public String getMessageListByType(){
		messages = userCommentService.getMessageListByType(currentPage, pageSize, type, commentedId, queryString);
		System.out.println(messages);  
		return Action.NONE;
	}
	
	public String save(){
		ActionContext ctx = ActionContext.getContext();
        Map<String, Object> session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        if(user == null && loginUser != null){
        	UserBase tmpUser = null;
        	
        	if((tmpUser = userService.getUserBaseByNickName(loginUser.getNickName())) == null){
    			this.addFieldError("user.nickName", "该用户名不存在");
    			return "USER_NO_LOGIN";
    		}
    		
    		if(!tmpUser.getPassword().equals(DigestUtils.md5Hex(loginUser.getPassword()))){
    			this.addFieldError("user.password", "密码不正确");
    			return "USER_NO_LOGIN";
    		}
    		
    		user= tmpUser;
    		
    		session.put("VALID_USER", tmpUser);
        }
        
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
        
        if(message.getParentMsg() == null || message.getParentMsg().getId() == 0L){
        	message.setKind(1);
        }else{
        	message.setKind(2);
        }
                
        userCommentService.saveOrUpdate(message);
		
		return Action.SUCCESS;
	}
	
	public String delMessageById(){
		ActionContext ctx = ActionContext.getContext();
        Map<String, Object> session = ctx.getSession();
		if(StringUtils.isEmpty(hostUserName)){
			UserBase user = (UserBase)session.get("VALID_USER");
	        
	        if(user != null && user.getId() >= 1){
	        	hostUserName = user.getNickName();
	        }
		}
		
		Administrator temp = (Administrator)session.get("ADMIN_USER");
		
		if(user == null && temp == null){
			return "USER_NO_LOGIN";
		}
		
		
		this.photoId = message.getCommentedId();
		
		userCommentService.delete(message.getId());
		
		return Action.SUCCESS;
	}

	public UserComment getMessage() {
		return message;
	}

	public void setMessage(UserComment message) {
		this.message = message;
	}

	public Page<UserComment> getMessages() {
		return messages;
	}

	public void setMessages(Page<UserComment> messages) {
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

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}

	public long getCommentedId() {
		return commentedId;
	}

	public void setCommentedId(long commentedId) {
		this.commentedId = commentedId;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}


	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}
	
	public String getEncoderdHostUserName() {
		try {
			return URLEncoder.encode(this.getHostUserName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return this.getHostUserName();
	}

	public UserBase getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(UserBase loginUser) {
		this.loginUser = loginUser;
	}

	
	
	

}
