package com.zigui.service.impl;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.zigui.domain.News;
import com.zigui.domain.NewsChannle;
import com.zigui.utils.DataFunctionDirective;
import com.zigui.utils.ImageUtils;
import com.zigui.utils.Page;

import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModelException;

public class TemplateGenerationServiceImpl {
	
	private TemplateLoader templateLoaderService;
	
	@Autowired
	private NewsChannleServiceImpl newsChannleService;
	
	@Autowired
	private NewsQueryServiceImpl newsQueryService;
	
	@Autowired
	private NewsServiceImpl newsService;
	
	@Autowired
	private DataFunctionDirective dataFunctionDirective;
	
	
	public String generateNewsIndex(){
		    
		  Configuration cfg = new Configuration();
		  cfg.setTemplateLoader(templateLoaderService);		    
		  //设计异常处理器  
		  cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		  cfg.setSharedVariable("dataFunction", dataFunctionDirective);
		    
		  
		  List<NewsChannle> newsChannles = newsChannleService.getAllChannles();
		  
		  
		  long focusId = 0;
		  for(NewsChannle channle : newsChannles){
			  if(channle.getName().equals("焦点新闻")){
				  focusId = channle.getId();
			  }
		  }
		  
		  Map<String, Object> queryCondition = new HashMap<String, Object>();
		  
		  queryCondition.put("channleId", focusId);
		  News firstFocusnews = (News)newsQueryService.getNewsByCondition(queryCondition, 1, 1, "createTime", false,null).getList().get(0);
		  
		  List<News> recommendFocusNews = newsQueryService.getNewsByCondition(queryCondition, 1, 10, "createTime", false,null).getList();
		  
		//定义并设置数据  
		  Map<String, Object> data = new HashMap<String, Object>();
		  data.put("newsChannles", newsChannles);
		  data.put("firstFocusnews", firstFocusnews);
		  data.put("recommendFocusNews", recommendFocusNews);
		  
		  //获取指定模板文件  
		  Template template = null;
		  Writer out = new StringWriter();  
		  try {
			 template = cfg.getTemplate("news_index.ftl");
			 template.process(data, out);
		  } catch (Exception e1) {
			 e1.printStackTrace();
		  }
		    
		  System.out.println(out.toString());
		  
		  return out.toString();
	}
	
	public String generateNews(long newsId){
	    
		  Configuration cfg = new Configuration();
		  cfg.setTemplateLoader(templateLoaderService);		    
		  //设计异常处理器  
		  cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		    
		  
		  News news = newsService.getNewsById(newsId);
		  
		//定义并设置数据  
		  Map<String, Object> data = new HashMap<String, Object>();
		  data.put("news", news);
		  
		  //获取指定模板文件  
		  Template template = null;
		  Writer out = new StringWriter();  
		  try {
			 template = cfg.getTemplate("news.ftl");
			 template.process(data, out);
		  } catch (Exception e1) {
			 e1.printStackTrace();
		  }
		    
		  System.out.println(out.toString());
		  
		  return out.toString();
	}
	
	
	public String generateChannleNewsList(long channleId, int currentPage, int pageSize){
	    
		  Configuration cfg = new Configuration();
		  cfg.setTemplateLoader(templateLoaderService);		    
		  //设计异常处理器  
		  cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		    
		  Map<String, Object> queryCondition = new HashMap<String, Object>();
		  
		  queryCondition.put("channleId", channleId);
		  Page<News> newsPage = newsQueryService.getNewsByCondition(queryCondition, currentPage, pageSize, "createTime", false,null);
		  
		//定义并设置数据  
		  Map<String, Object> data = new HashMap<String, Object>();
		  data.put("newsPage", newsPage);
		  
		  //获取指定模板文件  
		  Template template = null;
		  Writer out = new StringWriter();  
		  try {
			 template = cfg.getTemplate("news_list.ftl");
			 template.process(data, out);
		  } catch (Exception e1) {
			 e1.printStackTrace();
		  }
		    
		  System.out.println(out.toString());
		  
		  return out.toString();
	}
	
	public String generate(String templateName){
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(templateLoaderService);		    
		//设计异常处理器  
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		cfg.setSharedVariable("dataFunction", dataFunctionDirective);

		
		//获取指定模板文件  
		Template template = null;
		Writer out = new StringWriter();  
		try {
			template = cfg.getTemplate(templateName);
			template.process(new HashMap(), out);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    
		System.out.println(out.toString());
	  
		return out.toString();
	}
	
	public String generate(String templateName, Map<String, Object> parameter,  HttpServletRequest request, Map<String, Object> session){
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(templateLoaderService);		    
		//设计异常处理器  
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		cfg.setSharedVariable("dataFunction", dataFunctionDirective);
		
		
		//获取指定模板文件  
		Template template = null;
		Writer out = new StringWriter();
		Map map = new HashMap();
		map.put("request", request);
		map.put("parameter", parameter);
		map.put("session", session);
		map.put("statics", BeansWrapper.getDefaultInstance().getStaticModels());
		try {
			template = cfg.getTemplate(templateName);
			template.process(map, out);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    
//		System.out.println(out.toString());
	  
		return out.toString();
	}


	public TemplateLoader getTemplateLoaderService() {
		return templateLoaderService;
	}

	public void setTemplateLoaderService(TemplateLoader templateLoaderService) {
		this.templateLoaderService = templateLoaderService;
	}
	

}
