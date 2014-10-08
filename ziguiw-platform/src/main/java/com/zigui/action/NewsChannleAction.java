package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.NewsChannle;
import com.zigui.service.impl.NewsChannleServiceImpl;
import com.zigui.service.impl.NewsQueryServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NewsChannleAction extends ActionSupport {

	private static final long serialVersionUID = 1476426797752594972L;
	
	private NewsChannle newsChannle;
	private List<NewsChannle> newsChannles;
	private long channleId;
	private Long[] opIds;
	private int opType = 0;
	
	@Autowired
	private NewsChannleServiceImpl newsChannleService;
	
	@Autowired
	private NewsQueryServiceImpl newsQueryService;
	
	public void validateSaveOrUpdate(){
		if(newsChannle.getId() == 0){
			List<NewsChannle> channles = newsChannleService.getChannleByName(newsChannle.getName());
			
			if(CollectionUtils.isNotEmpty(channles)){
				this.addFieldError("newsChannle", "频道名已经存在");
			}
		}
	}
	
	public String saveOrUpdate(){
		
		newsChannleService.saveOrUpdate(newsChannle);
		
		return Action.SUCCESS;
	}
	
	public void validateDelete(){
		if(ArrayUtils.isEmpty(opIds)){
			this.addFieldError("opIds", "至少选中一行记录");
		}
		for(Long id : opIds){
			NewsChannle newsChannle = newsChannleService.getChannleById(id);
			
			if(newsChannle.getNewsCount() > 0){
				this.addFieldError("opIds", "频道还有新闻，请转移之后再进行操作");
			}
		}
	}
	
	public String getAll(){
		newsChannles = newsChannleService.getAllChannles();
		
		return Action.SUCCESS;
	}
	
	public String delete(){
		newsChannleService.deleteNewsChannle(opIds);
		
		return Action.SUCCESS;
	}
	
	public String deleteAjax() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		newsChannle = newsChannleService.getChannleById(opIds[0]);
		if(newsChannle.getNewsCount()>0){
			print(response,"fail");
		}else{
			newsChannleService.deleteNewsChannle(opIds);
			print(response,"success");
		}				
		return null;
	}
	
	public String getById(){
		newsChannle = newsChannleService.getChannleById(channleId);
		
		return Action.SUCCESS;
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

	public NewsChannle getNewsChannle() {
		return newsChannle;
	}

	public void setNewsChannle(NewsChannle newsChannle) {
		this.newsChannle = newsChannle;
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
	
	public List<NewsChannle> getNewsChannles() {
		return newsChannles;
	}

	public void setNewsChannles(List<NewsChannle> newsChannles) {
		this.newsChannles = newsChannles;
	}

	public NewsChannleServiceImpl getNewsChannleService() {
		return newsChannleService;
	}

	public void setNewsChannleService(NewsChannleServiceImpl newsChannleService) {
		this.newsChannleService = newsChannleService;
	}

}
