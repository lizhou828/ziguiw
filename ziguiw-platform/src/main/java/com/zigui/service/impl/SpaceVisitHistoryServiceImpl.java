package com.zigui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.SpaceVisitHistoryDao;
import com.zigui.dao.UserBaseDao;
import com.zigui.domain.SpaceVisitHistory;
import com.zigui.domain.UserBase;

public class SpaceVisitHistoryServiceImpl {
	
	@Autowired
	private SpaceVisitHistoryDao spaceVisitHistoryDao;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	public long saveOrUpdate(SpaceVisitHistory spaceVisitHistory){
		spaceVisitHistoryDao.update("delete SpaceVisitHistory where formUser.id= " + spaceVisitHistory.getFormUser().getId() + " and toUser.id = " + spaceVisitHistory.getToUser().getId());
		spaceVisitHistoryDao.saveOrUpdate(spaceVisitHistory);
		
		return spaceVisitHistory.getId();
	}
	
	public void addSpacePv(long userId){
		
		UserBase toUser = userBaseDao.get(UserBase.class, userId);
		toUser.setSpacePv(toUser.getSpacePv() == null ? 1 : toUser.getSpacePv() + 1);
		
		userBaseDao.saveOrUpdate(toUser);
		
	}
	
	public List<SpaceVisitHistory> getVisitHistory(UserBase toUser){
		return spaceVisitHistoryDao.findBy(SpaceVisitHistory.class, "toUser", toUser);
	}

}
