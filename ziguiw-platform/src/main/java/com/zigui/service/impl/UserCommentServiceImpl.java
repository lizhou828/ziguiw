package com.zigui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.UserCommentDao;
import com.zigui.domain.UserComment;
import com.zigui.utils.Page;

public class UserCommentServiceImpl {
	
	@Autowired
	private UserCommentDao userCommentDao;
	
	public Page<UserComment> getMessagesByCommendedId(int pageNo, int pageSize, int type, long commentedId, String queryString){
		return userCommentDao.pagedQuery("from UserComment where commentedId = ? and type = ? and kind = 1 order by createTime desc", pageNo, pageSize, queryString, new Object[]{commentedId, type});
	}
	
	
	public Page<UserComment> getMessageListByType(int pageNo, int pageSize, int type, long commentedId, String queryString){
		return userCommentDao.pagedQuery("from UserComment where type = ? and kind = 1 order by createTime desc", pageNo, pageSize, queryString, new Object[]{type});
	}
	
	
	
	public long saveOrUpdate(UserComment userComment){
		userCommentDao.saveOrUpdate(userComment);
		
		return userComment.getId();
	}
	
	public void delete(long msgId){
		userCommentDao.removeById(UserComment.class, msgId);
	}

}
