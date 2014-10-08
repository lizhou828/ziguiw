package com.zigui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.NewsRecommendRegionDao;
import com.zigui.domain.NewsChannle;
import com.zigui.domain.NewsRecommendRegion;

public class NewsRecommendRegionServiceImpl {
	
	@Autowired
	private NewsRecommendRegionDao newsRecommendRegionDao;
	
	public long saveOrUpdate(NewsRecommendRegion newsRecommendRegion){
		newsRecommendRegionDao.saveOrUpdate(newsRecommendRegion);
		return newsRecommendRegion.getId();
	}
	
	public List<NewsRecommendRegion> getRegionByName(String name){
		
		List<NewsRecommendRegion> newsRecommendRegions = newsRecommendRegionDao.findBy(NewsRecommendRegion.class, "name", name);
		
		return newsRecommendRegions;
	}
	
	public NewsRecommendRegion getRegionById(long id){
		return newsRecommendRegionDao.get(NewsRecommendRegion.class, id);
	}
	
	public List<NewsRecommendRegion> getAllRegions(){
		return newsRecommendRegionDao.getAll(NewsRecommendRegion.class);
	}
	
	/**
	 * 
	 * @return
	 * 当返回0时候成功
	 * 返回1时是这个频道下面还有新闻
	 */
	public int deleteRegion(Long[] ids){
		for(Long id : ids){
			newsRecommendRegionDao.removeById(NewsRecommendRegion.class, id);
		}
		
		return 0;
	}

}
