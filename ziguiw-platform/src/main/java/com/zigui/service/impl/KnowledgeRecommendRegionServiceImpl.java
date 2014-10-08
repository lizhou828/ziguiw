package com.zigui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.KnowledgeRecommendRegionDao;
import com.zigui.domain.KnowledgeRecommendRegion;

public class KnowledgeRecommendRegionServiceImpl {
	@Autowired
	private KnowledgeRecommendRegionDao knowledgeRecommendRegionDao;
	
	public long saveOrUpdate(KnowledgeRecommendRegion knowledgeRecommendRegion){
		knowledgeRecommendRegionDao.saveOrUpdate(knowledgeRecommendRegion);
		return knowledgeRecommendRegion.getId();
	}
	
public List<KnowledgeRecommendRegion> getRegionByName(String name){
		
		List<KnowledgeRecommendRegion> knowledgeRecommendRegions = knowledgeRecommendRegionDao.findBy(KnowledgeRecommendRegion.class, "name", name);
		
		return knowledgeRecommendRegions;
	}
	
	public KnowledgeRecommendRegion getRegionById(long id){
		return knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, id);
	}
	
	public List<KnowledgeRecommendRegion> getAllRegions(){
		return knowledgeRecommendRegionDao.getAll(KnowledgeRecommendRegion.class);
	}
	
	public List<KnowledgeRecommendRegion> getAllRegionsByType(long type){
		return knowledgeRecommendRegionDao.findBy(KnowledgeRecommendRegion.class, "type", type);
	}
	
	/**
	 * 
	 * @return
	 * 当返回0时候成功
	 * 返回1时是这个频道下面还有新闻
	 */
	public int deleteRegion(Long[] ids){
		for(Long id : ids){
			knowledgeRecommendRegionDao.removeById(KnowledgeRecommendRegion.class, id);
		}
		
		return 0;
	}

}
