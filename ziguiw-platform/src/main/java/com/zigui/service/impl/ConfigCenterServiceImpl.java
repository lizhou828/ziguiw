package com.zigui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.ConfigCenterDao;
import com.zigui.domain.ConfigCenter;

public class ConfigCenterServiceImpl {
	
	@Autowired
	private ConfigCenterDao configCenterDao;
	
	public ConfigCenter getConfig(){
		return configCenterDao.get(ConfigCenter.class, 1L);
	}
	
	public long saveOrUpdate(ConfigCenter configCenter){
		configCenterDao.saveOrUpdate(configCenter);
		
		return configCenter.getId();
	}

}
