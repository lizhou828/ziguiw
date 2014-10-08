package com.zigui.service.impl;

import com.zigui.dao.AdPlanDao;
import com.zigui.domain.AdPlan;
import com.zigui.utils.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class AdPlanServiceImpl {
	
	@Autowired
	private AdPlanDao adPlanDao;
	
	public long saveOrUpdate(AdPlan adPlan){
		adPlanDao.saveOrUpdate(adPlan);
		
		return adPlan.getId();
	}
	
	public List<AdPlan> getAll(){
		return adPlanDao.getAll(AdPlan.class);
	}
	
	public void delete(long[] opIds){
		for(long id : opIds){
			adPlanDao.removeById(AdPlan.class, id);
		}
		
	}
	
	public AdPlan getById(long id){
		return adPlanDao.get(AdPlan.class, id);
	}
	
	public Page<AdPlan> getOverduedPlan(int pageNo, int pageSize){
		return adPlanDao.pagedQuery("from AdPlan where endTime < ?", pageNo, pageSize, new Object[]{new Date()});
	}
	
	public Page<AdPlan> getPendingPlan(int pageNo, int pageSize){
		return adPlanDao.pagedQuery("from AdPlan where startTime > ?", pageNo, pageSize, new Object[]{new Date()});
	}
	
	public Page<AdPlan> getCurrentPlan(int pageNo, int pageSize){
		return adPlanDao.pagedQuery("from AdPlan where startTime < ? and endTime > ?", pageNo, pageSize, new Object[]{new Date(), new Date()});
	}
	
	public AdPlan getCurrentPlanByAd(long adId){
		List<AdPlan> adPlans = adPlanDao.find("from AdPlan where ad.id = ? and startTime < ? and endTime > ?", new Object[]{adId, new Date(), new Date()});
		if(CollectionUtils.isNotEmpty(adPlans)){
			return adPlans.get(0);
		}else{
			return null;
		}
	}


}
