package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.NewsChannleDao;
import com.zigui.dao.NewsDao;
import com.zigui.domain.NewsChannle;

public class NewsChannleServiceImpl{
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private NewsChannleDao newsChannleDao;
	
	@Autowired
	private NewsDao newsDao;
	
	public long saveOrUpdate(NewsChannle newsChannle){
		newsChannleDao.saveOrUpdate(newsChannle);
		return newsChannle.getId();
	}
	
	public List<NewsChannle> getChannleByName(String name){
		
		List<NewsChannle> newsChannles = newsChannleDao.findBy(NewsChannle.class, "name", name);
		
		return newsChannles;
	}
	
	public NewsChannle getChannleById(long id){
		return newsChannleDao.get(NewsChannle.class, id);
	}
	
	public List<NewsChannle> getAllChannles(){
		return newsChannleDao.getAll(NewsChannle.class);
	}
	
	public void move(long fromChannle, long toChannle, String startCreateTime, String endCreateTime){
		String hql = "update News set newsChannle.id = " + toChannle + " where newsChannle.id = " + fromChannle;
		
		if(StringUtils.isNotEmpty(startCreateTime)){
			hql += " and to_char(createTime,'yyyy-MM-dd') > '" + startCreateTime + "'";
			
		}
		if(StringUtils.isNotEmpty(endCreateTime)){
			hql += " and to_char(createTime,'yyyy-MM-dd') < '" + endCreateTime + "'";
			
		}
		
		newsChannleDao.update(hql);
	}
	
	/**
	 * 
	 * @return
	 * 当返回0时候成功
	 * 返回1时是这个频道下面还有新闻
	 */
	public int deleteNewsChannle(Long[] ids){
		for(Long id : ids){
			newsChannleDao.removeById(NewsChannle.class, id);
		}
		
		return 0;
	}

}
