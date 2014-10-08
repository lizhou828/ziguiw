package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.UserAlbum;
import com.zigui.domain.UserBase;
import com.zigui.service.impl.UserAlbumServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

public class UserAlbumAction extends ActionSupport {

	private UserAlbum album;
	
	@Autowired
	private UserAlbumServiceImpl userAlbumService;
	
	@Autowired
	private UserServiceImpl userService;

	private int pageSize = 10;
	
	private int pageNum = 1;

	private Page<UserAlbum> albums;

	private int userId;

	//Album Id
	private long id;
	
	private long hostUserId;
	
	private String hostUserName;
	
	public void validateSave(){
		if(album == null || StringUtils.isEmpty(album.getTitle())){
			this.addFieldError("album.title", "相册标题不能为空");
		}
	}
	
	public String save() {
		ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        if(user == null || user.getId() < 1){
        	return "USER_NO_LOGIN";
        }
        
        hostUserName = user.getNickName();
		
		album.setCreateTime(new Date());
		UserBase tmpUser = new UserBase();
        tmpUser.setId(user.getId());
		
        album.setUser(tmpUser);
        try{
        	userAlbumService.save(album);
        }catch(Exception e){
        	
        }
		
		return Action.SUCCESS;
	}
	
	public String getAlbumList() {
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		UserBase hostUser = userService.getUserBaseByNickName(hostUserName);
		
		albums = userAlbumService.getAlbumList(pageSize,pageNum,hostUser.getId());
		return Action.SUCCESS;
	}
	
	
	public String delById() {
		ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        if(user != null && StringUtils.isNotEmpty(user.getNickName())){
        	hostUserName = user.getNickName();
        }
		
		userAlbumService.delById(id);
		return Action.SUCCESS;
	}
	
	public String saveAlbumCover(){
		ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        if(user != null && StringUtils.isNotEmpty(user.getNickName())){
        	hostUserName = user.getNickName();
        }
        
        userAlbumService.saveAlbumCover(album.getId(), album.getCoverPhoto().getId());
        
        return Action.SUCCESS;
	}

	public void setAlbum(UserAlbum album) {
		this.album = album;
	}

	public UserAlbum getAlbum() {
		return album;
	}

	public void setUserAlbumService(UserAlbumServiceImpl userAlbumService) {
		this.userAlbumService = userAlbumService;
	}

	public UserAlbumServiceImpl getUserAlbumService() {
		return userAlbumService;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Page<UserAlbum> getAlbums() {
		return albums;
	}

	public void setAlbums(Page<UserAlbum> albums) {
		this.albums = albums;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public String getEncoderdHostUserName() {
		try {
			return URLEncoder.encode(this.getHostUserName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return this.getHostUserName();
	}
}
