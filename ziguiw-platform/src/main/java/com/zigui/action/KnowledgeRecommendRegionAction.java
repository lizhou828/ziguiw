package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.KnowledgeRecommendRegion;
import com.zigui.service.impl.KnowledgeRecommendRegionServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KnowledgeRecommendRegionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3579164456066455945L;
	
	private KnowledgeRecommendRegion knowledgeRecommendRegion;
	private List<KnowledgeRecommendRegion> knowledgeRecommendRegions;
	private long regionId;
	private Long[] opIds;
	private int opType = 0;
	
	private long recommendType;
	
	@Autowired
	private KnowledgeRecommendRegionServiceImpl knowledgeRecommendRegionService;
	
	public void validateSaveOrUpdate(){
		if(knowledgeRecommendRegion.getId() == 0){
			if(StringUtils.isEmpty(knowledgeRecommendRegion.getName())){
				this.addFieldError("newsChannle", "推荐区名称不能为空");
				return;
			}
			List<KnowledgeRecommendRegion> regions = knowledgeRecommendRegionService.getRegionByName(knowledgeRecommendRegion.getName());
			
			if(CollectionUtils.isNotEmpty(regions)){
				this.addFieldError("newsChannle", "推荐区已经存在");
			}
		}
	}
	
	public String saveOrUpdate(){
		
		knowledgeRecommendRegionService.saveOrUpdate(knowledgeRecommendRegion);
		
		return Action.SUCCESS;
	}
	
//	public void validateDelete(){
//		for(Long id : opIds){
//			KnowledgeRecommendRegion knowledgeRecommendRegion = knowledgeRecommendRegionService.getRegionById(id);
//			Map map = new HashMap();
//			map.put("regionId", id);
//			
//			Page<News> news = newsQueryService.getNewsByCondition(map, 1, 1, "id", true);
//			
////			if(CollectionUtils.isNotEmpty(news.getList())){
////				this.addFieldError("opIds", "鍦� + news.getList().get(0).getNewsRecommendRegion().getName() + "鎺ㄨ崘涓嬮潰杩樺瓨鍦ㄦ柊闂伙紝璇疯浆涔変箣鍚庡啀鎵ц鍒犻櫎鍔ㄤ綔");
////			}
//		}
//	}
	
	public String getAll(){
		knowledgeRecommendRegions = knowledgeRecommendRegionService.getAllRegions();
		
		return Action.SUCCESS;
	}
	
	public String getAllByType(){
		knowledgeRecommendRegions = knowledgeRecommendRegionService.getAllRegionsByType(recommendType);
		
		return Action.SUCCESS;
	}
	
	public String delete(){
		opIds = new Long[]{regionId};
		knowledgeRecommendRegionService.deleteRegion(opIds);
		
		return Action.SUCCESS;
	}
	
	public String batchDelete(){
		knowledgeRecommendRegionService.deleteRegion(opIds);
		
		return Action.SUCCESS;
	}
	
	public String getById(){
		knowledgeRecommendRegion = knowledgeRecommendRegionService.getRegionById(regionId);
		
		return Action.SUCCESS;
	}

	public KnowledgeRecommendRegion getKnowledgeRecommendRegion() {
		return knowledgeRecommendRegion;
	}

	public void setKnowledgeRecommendRegion(
			KnowledgeRecommendRegion knowledgeRecommendRegion) {
		this.knowledgeRecommendRegion = knowledgeRecommendRegion;
	}

	public List<KnowledgeRecommendRegion> getKnowledgeRecommendRegions() {
		return knowledgeRecommendRegions;
	}

	public void setKnowledgeRecommendRegions(
			List<KnowledgeRecommendRegion> knowledgeRecommendRegions) {
		this.knowledgeRecommendRegions = knowledgeRecommendRegions;
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

	public long getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(long recommendType) {
		this.recommendType = recommendType;
	}	


}
