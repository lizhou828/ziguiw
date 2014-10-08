package com.zigui.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.UserMessageDao;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserMessage;
import com.zigui.utils.Page;


public class UserMessageServiceImpl {

	@Autowired
	private UserMessageDao userMessageDao;

	public List<UserMessage> getMessageList(int pageNum, int pageSize, long ownerId) {
		List<UserMessage> messages = userMessageDao.getMessageList(pageNum, pageSize, ownerId);
		Iterator<UserMessage> iter = messages.iterator();
		while(iter.hasNext()) {
			UserMessage message = iter.next();
//			message.setReplies(userMessageDao.getReplies(message.getId()));
		}
		return messages;
	}
	
	public Page<UserMessage> getMessagesByNickName(int pageNo, int pageSize, String hostNickName, String queryString){
		return userMessageDao.pagedQuery("from UserMessage where toUser.nickName = ? and kind = 1 order by createTime desc", pageNo, pageSize, queryString, new Object[]{hostNickName});
	}
	
	public Page<UserMessage> getMailByNickName(int pageNo, int pageSize, UserBase user, String mailType, String queryString){
		if(StringUtils.equals("1", mailType)){
			return userMessageDao.pagedQuery("from UserMessage where formUser = ? and kind = 8 order by createTime desc", pageNo, pageSize, queryString, new Object[]{user});
		}else{
			return userMessageDao.pagedQuery("from UserMessage where toUser = ? and kind = 8 order by createTime desc", pageNo, pageSize, queryString, new Object[]{user});
		}
	}
	
	public long save(UserMessage userMessage){
		userMessageDao.save(userMessage);
		
		return userMessage.getId();
	}
	
	public void delete(long msgId){
		userMessageDao.removeById(UserMessage.class, msgId);
	}

	
}
