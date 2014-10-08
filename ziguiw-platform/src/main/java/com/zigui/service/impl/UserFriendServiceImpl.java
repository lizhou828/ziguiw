package com.zigui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.UserBaseDao;
import com.zigui.dao.UserFriendDao;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserFriend;
import com.zigui.utils.Page;

public class UserFriendServiceImpl {
	@Autowired
	private UserFriendDao userFriendDao;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	public long saveOrUpdate(UserFriend friend){
		userFriendDao.saveOrUpdate(friend);
		
		return friend.getId();
	}
	
	public UserFriend getFriendRelation(UserBase user, UserBase friend){
		List<UserFriend> userFriends = userFriendDao.find("from UserFriend where user = ? and friendUser = ?", new Object[]{user, friend});
		
		if(CollectionUtils.isNotEmpty(userFriends)){
			return userFriends.get(0);
		}
		
		return null;
	}
	
	public void delete(UserFriend friend){
		
		userFriendDao.remove(friend);
		
	}
	
	public Page<UserFriend> getMyFriend(int pageNo, int pageSize, UserBase user){
		return userFriendDao.pagedQuery("from UserFriend where user = ?", pageNo, pageSize, user);
	}
	
	public Page<UserFriend> listByCondition(Map queryCondition, int pageNo, int pageSize, String orderBy, boolean isAsc,String queryString){
			
			List<Criterion> criterionsList = new ArrayList<Criterion>(queryCondition.size());
			
			
			if(queryCondition.get("userId") != null&&!((String)queryCondition.get("userId")).equals("0")){
				criterionsList.add(Restrictions.eq("user.id", queryCondition.get("userId")));
			}
			
			Criterion[] criterions = new Criterion[criterionsList.size()];
			criterionsList.toArray(criterions);
			
			return userFriendDao.pagedQuery(UserFriend.class, pageNo, pageSize, orderBy, isAsc,queryString,criterions);
			
		}
}
