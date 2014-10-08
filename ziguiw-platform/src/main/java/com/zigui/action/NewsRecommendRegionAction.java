package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.News;
import com.zigui.domain.NewsRecommendRegion;
import com.zigui.service.impl.NewsQueryServiceImpl;
import com.zigui.service.impl.NewsRecommendRegionServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsRecommendRegionAction  extends ActionSupport {

	private static final long serialVersionUID = 448963978961846786L;
	
	private NewsRecommendRegion newsRecommendRegion;
	private List<NewsRecommendRegion> newsRecommendRegions;
	private long regionId;
	private Long[] opIds;
	private int opType = 0;
	
	@Autowired
	private NewsRecommendRegionServiceImpl newsRecommendRegionService;
	
	@Autowired
	private NewsQueryServiceImpl newsQueryService;
	
	public void validateSaveOrUpdate(){
		if(newsRecommendRegion.getId() == 0){
			if(StringUtils.isEmpty(newsRecommendRegion.getName())){
				this.addFieldError("newsChannle", "推荐区名称不能为空");
				return;
			}
			
			List<NewsRecommendRegion> regions = newsRecommendRegionService.getRegionByName(newsRecommendRegion.getName());
			
			if(CollectionUtils.isNotEmpty(regions)){
				this.addFieldError("newsChannle", "推荐区已经存在");
			}
		}
	}
	
	public String saveOrUpdate(){
		
		newsRecommendRegionService.saveOrUpdate(newsRecommendRegion);
		
		return Action.SUCCESS;
	}
	
	public void validateDelete(){
		for(Long id : opIds){
			
			NewsRecommendRegion newsRecommendRegion = newsRecommendRegionService.getRegionById(id);
			if(newsRecommendRegion == null || newsRecommendRegion.getId() == 0L){
				this.addActionError("待删ID没有数据");
			}
			Map map = new HashMap();
			map.put("regionId", String.valueOf(id));
			
			Page<News> news = newsQueryService.getNewsByCondition(map, 1, 1, "id", true,null);
			
			if(CollectionUtils.isNotEmpty(news.getList())){
				this.addFieldError("opIds", "该"+ news.getList().get(0).getNewsRecommendRegion().getName() + "推荐区存在数据，不能删除");
			}
		}
	}
	
	public String getAll(){
		newsRecommendRegions = newsRecommendRegionService.getAllRegions();
		
		return Action.SUCCESS;
	}
	
	public String delete(){
		newsRecommendRegionService.deleteRegion(opIds);
		
		return Action.SUCCESS;
	}
	
	public String getById(){
		newsRecommendRegion = newsRecommendRegionService.getRegionById(regionId);
		
		return Action.SUCCESS;
	}

	public NewsRecommendRegion getNewsRecommendRegion() {
		return newsRecommendRegion;
	}

	public void setNewsRecommendRegion(NewsRecommendRegion newsRecommendRegion) {
		this.newsRecommendRegion = newsRecommendRegion;
	}

	public List<NewsRecommendRegion> getNewsRecommendRegions() {
		return newsRecommendRegions;
	}

	public void setNewsRecommendRegions(
			List<NewsRecommendRegion> newsRecommendRegions) {
		this.newsRecommendRegions = newsRecommendRegions;
	}

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

	public int getOpType() {
		return opType;
	}

	public void setOpType(int opType) {
		this.opType = opType;
	}

	public NewsRecommendRegionServiceImpl getNewsRecommendRegionService() {
		return newsRecommendRegionService;
	}

	public void setNewsRecommendRegionService(
			NewsRecommendRegionServiceImpl newsRecommendRegionService) {
		this.newsRecommendRegionService = newsRecommendRegionService;
	}

	public NewsQueryServiceImpl getNewsQueryService() {
		return newsQueryService;
	}

	public void setNewsQueryService(NewsQueryServiceImpl newsQueryService) {
		this.newsQueryService = newsQueryService;
	}

}
