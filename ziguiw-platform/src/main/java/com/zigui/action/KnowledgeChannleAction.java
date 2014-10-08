package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.KnowledgeChannle;
import com.zigui.service.impl.KnowledgeChannleServiceImpl;
import com.zigui.service.impl.KnowledgeServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class KnowledgeChannleAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708552334737951117L;
	
	private KnowledgeChannle knowledgeChannle;
	private List<KnowledgeChannle> knowledgeChannles;
	private long channleId;
	private Long[] opIds;
	private int opType = 0;
	
	@Autowired
	private KnowledgeChannleServiceImpl knowledgeChannleService;
	
	@Autowired
	private KnowledgeServiceImpl knowledgeService;
	
	public void validateSaveOrUpdate(){
		if(knowledgeChannle.getId() == 0){
			List<KnowledgeChannle> channles = knowledgeChannleService.getChannleByName(knowledgeChannle.getName());
			
			if(CollectionUtils.isNotEmpty(channles)){
				this.addFieldError("newsChannle", "该频道已经存在");
			}
		}
	}
	
	public String saveOrUpdate(){
		
		knowledgeChannleService.saveOrUpdate(knowledgeChannle);
		
		return Action.SUCCESS;
	}
	
//	public void validateDelete(){
//		for(Long id : opIds){
//			NewsChannle newsChannle = knowledgeChannleService.getChannleById(id);
//			Map map = new HashMap();
//			map.put("channleId", id);
//			
//			Page<News> news = newsQueryService.getNewsByCondition(map, 1, 1, "id", true);
//			
//			if(CollectionUtils.isNotEmpty(news.getList())){
//				this.addFieldError("opIds", "在" + news.getList().get(0).getNewsChannle().getName() + "频道下面还存在新闻，请转义之后再执行删除动作");
//			}
//		}
//	}
	
	public String getAll(){
		knowledgeChannles = knowledgeChannleService.getAllChannles();
		
		return Action.SUCCESS;
	}
	
	public String delete(){
		opIds = new Long[]{channleId};
		knowledgeChannleService.deleteNewsChannle(opIds);
		
		return Action.SUCCESS;
	}
	
	public String batchDelete(){
		knowledgeChannleService.deleteNewsChannle(opIds);
		
		return Action.SUCCESS;
	}
	public String getById(){
		knowledgeChannle = knowledgeChannleService.getChannleById(channleId);
		
		return Action.SUCCESS;
	}

	public String deleteAjax() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		knowledgeChannle = knowledgeChannleService.getChannleById(opIds[0]);
		if(knowledgeChannle.getKnowledgeCount()>0){
			print(response,"fail");
		}else{
			knowledgeChannleService.deleteNewsChannle(opIds);
			print(response,"success");
		}				
		return null;
	}
	
	protected void print(HttpServletResponse response, String info) throws IOException {
	    try {
	    	response.setHeader("Cache-Control", "no-cache");
	    	response.setContentType("text/json;charset=utf-8");
	    	response.getWriter().print(info);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	throw e;
	    }
	}
	  
	public KnowledgeChannle getKnowledgeChannle() {
		return knowledgeChannle;
	}

	public void setKnowledgeChannle(KnowledgeChannle knowledgeChannle) {
		this.knowledgeChannle = knowledgeChannle;
	}

	public List<KnowledgeChannle> getKnowledgeChannles() {
		return knowledgeChannles;
	}

	public void setKnowledgeChannles(List<KnowledgeChannle> knowledgeChannles) {
		this.knowledgeChannles = knowledgeChannles;
	}

	public long getChannleId() {
		return channleId;
	}

	public void setChannleId(long channleId) {
		this.channleId = channleId;
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
	
	
	

}
