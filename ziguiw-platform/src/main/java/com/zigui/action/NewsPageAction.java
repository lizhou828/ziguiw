package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.service.impl.TemplateGenerationServiceImpl;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class NewsPageAction extends ActionSupport {
	
	private static final long serialVersionUID = -6893008656084464435L;
	
	@Autowired
	private TemplateGenerationServiceImpl templateGenerationService;
	
	private long newsId;
	
	private long channleId;
	private int currentPage = 1;
	private int pageSize = 10;
	
	public String getNewsIndex(){
		String newsIndex = templateGenerationService.generateNewsIndex();
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		
		response.setContentType("text/html;charaset=" + "UTF-8");
		Writer out;
		try {
			out = response.getWriter();
			out.write(newsIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Action.NONE;
	}
	
	public String getNews(){
		String news = templateGenerationService.generateNews(newsId);
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		
		response.setContentType("text/html;charaset=" + "UTF-8");
		Writer out;
		try {
			out = response.getWriter();
			out.write(news);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Action.NONE;
	}
	
	public String getChannleNewsList(){
		String news = templateGenerationService.generateChannleNewsList(channleId, currentPage, pageSize);
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		
		response.setContentType("text/html;charaset=" + "UTF-8");
		Writer out;
		try {
			out = response.getWriter();
			out.write(news);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Action.NONE;
	}

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getChannleId() {
		return channleId;
	}

	public void setChannleId(long channleId) {
		this.channleId = channleId;
	}

}
