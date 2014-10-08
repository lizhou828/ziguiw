package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.service.impl.StringTemplateLoaderServiceImpl;
import com.zigui.utils.DataFunctionDirective;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class TemplatePreviewAction extends ActionSupport {
	
	private static final long serialVersionUID = -4016497428741916187L;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	private String parameter;
	private String testParameter;
	private String hql;
	private Object functionData;
	private String templateContent;
	private String templateResult;
	private TemplateLoader templateLoaderService;
	@Autowired
	private DataFunctionDirective dataFunctionDirective;
	
	public String previewFunction(){
		Map<String, String> params = new HashMap<String, String>();
		if(StringUtils.isNotBlank(testParameter)){
			String[] testParameterArray = testParameter.split("&");
			for(int i = 0; i < testParameterArray.length; i ++){
				String[] pair = testParameterArray[i].split("=");
				
				params.put(pair[0], pair[1]);
			}
		}
		
		String[] parameterArray = null;
		if(StringUtils.isNotBlank(parameter)){
			parameterArray = parameter.split("&");
		}
		
		Object[] objArray = new Object[parameterArray.length];
		
		for(int i = 0; i < parameterArray.length; i ++){
			if(parameterArray[i].startsWith("*L_")){
				objArray[i] = NumberUtils.toLong(MapUtils.getString(params, parameterArray[i].substring(3)));
			}else{
				objArray[i] = MapUtils.getObject(params, parameterArray[i]);
			}
		}
		
		functionData = commonService.getByHql(hql, 1, 10, objArray);
		
		return Action.SUCCESS;
	}
	
	public String previewTemplate(){
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(templateLoaderService);		    
		//设计异常处理器  
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		cfg.setSharedVariable("dataFunction", dataFunctionDirective);
		
		try {
			((StringTemplateLoaderServiceImpl)templateLoaderService).setTemplateContent(URLDecoder.decode(templateContent, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取指定模板文件  
		Template template = null;
		Writer out = new StringWriter();  
		try {
			template = cfg.getTemplate("");
			template.process(new HashMap(), out);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		templateResult = out.toString();
		
		return Action.SUCCESS;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getTestParameter() {
		return testParameter;
	}

	public void setTestParameter(String testParameter) {
		this.testParameter = testParameter;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public Object getFunctionData() {
		return functionData;
	}

	public void setFunctionData(Object functionData) {
		this.functionData = functionData;
	}

	public TemplateLoader getTemplateLoaderService() {
		return templateLoaderService;
	}

	public void setTemplateLoaderService(TemplateLoader templateLoaderService) {
		this.templateLoaderService = templateLoaderService;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getTemplateResult() {
		return templateResult;
	}

	public void setTemplateResult(String templateResult) {
		this.templateResult = templateResult;
	}
	
	

}
