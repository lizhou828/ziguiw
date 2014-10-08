package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.*;
import com.zigui.service.impl.*;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3542540651847825973L;
	
	private long resourceId;
	
	private String resourceType;
	
	private int pageNo=1;
	
	private int pageSize=10;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	@Autowired
	private transient KnowledgeServiceImpl knowledgeService;
	
	@Autowired
	private transient NewsServiceImpl newsService;
	
	@Autowired
	private transient DebateServiceImpl debateService;
	
	@Autowired
	private transient QuestionServiceImpl questionService;
	
	@Autowired
	private transient ForumServiceImpl forumService;
	
	@Autowired
	private transient UserDiaryServiceImpl userDiaryService;
	
	@Autowired
	private transient CommonServiceImpl commonService;
	
	private Object[] queryObj = new Object[0];
	
	private String parameter;
	
	private Object obj;
	
	private String hql;
	
	public String addClickCount() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		if(resourceType!=null&&resourceType!=""){
			
			if("knowledge".equals(resourceType)){
				Knowledge knowledge = knowledgeService.getKnowledgeById(resourceId);
				if(knowledge!=null&&knowledge.getId()>0){
					if(knowledge.getClickCount()==0){
						knowledge.setClickCount(1);
					}else{
						knowledge.setClickCount(knowledge.getClickCount()+1);
					}
					knowledgeService.saveOrUpdate(knowledge);	
				}
				
			}else if("news".equals(resourceType)){
				
				News news = newsService.getNewsById(resourceId);
				if(news!=null&&news.getId()>0){
					if(news.getClickCount()==0){
						news.setClickCount(1);
					}else{
						news.setClickCount(news.getClickCount()+1);
					}
					newsService.saveOrUpdate(news);	
				}
				
				
			}else if("debate".equals(resourceType)){
				
				Debate debate = debateService.getById(resourceId);
				if(debate!=null&&debate.getId()>0){
					if(debate.getClickCount()==0){
						debate.setClickCount(1);
					}else{
						debate.setClickCount(debate.getClickCount()+1);
					}
					debateService.saveOrUpdate(debate);	
				}
				
				
			}else if("question".equals(resourceType)){
				
				Question question = questionService.getById(resourceId);
				if(question!=null&&question.getId()>0){
					if(question.getClickCount()==0){
						question.setClickCount(1);
					}else{
						question.setClickCount(question.getClickCount()+1);
					}
					questionService.saveOrUpdate(question);	
				}
				
				
			}else if("forum".equals(resourceType)){
				
				Forum forum = forumService.getById(resourceId);
				if(forum!=null&&forum.getId()>0){
					if(forum.getClickCount()==0){
						forum.setClickCount(1);
					}else{
						forum.setClickCount(forum.getClickCount()+1);
					}
					forumService.saveOrUpdate(forum);	
				}
				
				
			}else if("diary".equals(resourceType)){
				
				UserDiary userDiary = userDiaryService.getById(resourceId);
				if(userDiary!=null&&userDiary.getId()>0){
					if(userDiary.getClickCount()==null || userDiary.getClickCount() == 0L){
						userDiary.setClickCount(1L);
					}else{
						userDiary.setClickCount(userDiary.getClickCount()+1);
					}
					userDiaryService.saveOrUpdate(userDiary);	
				}
				
				
			}
			
			
		}
		
		print(response,"success");
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
	  
	public String commonQuery(){
        System.out.println("CommonAction's commonQuery method is running.....the hql sentence you want to query is= "+hql);
		obj = commonService.getByHql(hql, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), queryString, queryObj);
		return Action.SUCCESS;
	}
	
	
	public String commonQuery1(){
		String[] parameterArray = null;
		
		if(StringUtils.isNotBlank(parameter)){
			parameterArray = parameter.split("&");
		}

		Object[] objArray = new Object[parameterArray.length];
		
		for(int i = 0; i < parameterArray.length; i ++){
			if(parameterArray[i].startsWith("*L_")){
				objArray[i] = NumberUtils.toLong(parameterArray[i].substring(3));
			}else if(parameterArray[i].startsWith("*I_")){
				objArray[i] = NumberUtils.toInt(parameterArray[i].substring(3));
			}else{
				objArray[i] = parameterArray[i];
			}
		}
		
		obj = commonService.getByHql(hql, pageNo, pageSize, objArray);
		
		return Action.SUCCESS;
	}
	
	public String deleteContentNotify(){
		commonService.deleteById(ContentNotify.class, resourceId);
		
		return Action.SUCCESS;
	}

	public long getResourceId() {
		return resourceId;
	}

	public void setResourceId(long resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Object[] getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(Object[] queryObj) {
		this.queryObj = queryObj;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}  
	
	

}
