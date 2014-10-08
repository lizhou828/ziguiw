package com.zigui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.NewsSourceDao;
import com.zigui.domain.NewsSource;

public class NewsSourceServiceImpl {
	
	@Autowired
	private NewsSourceDao newsSourceDao;
	
	public long saveOrUpdate(NewsSource newsSource){
		newsSourceDao.saveOrUpdate(newsSource);
		
		return newsSource.getId();
	}

}
