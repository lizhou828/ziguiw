package com.zigui.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import freemarker.cache.TemplateLoader;

public class StringTemplateLoaderServiceImpl implements TemplateLoader{

	private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";
	private String templateContent;
	private Map templates = new HashMap();
	
	@Autowired
	private TemplateServiceImpl templateService;

	public void setTemplateContent(String content){
		this.templateContent = content;
		templates.put(DEFAULT_TEMPLATE_KEY, templateContent);
	}

	public void AddTemplate(String name, String template) {
		if (name == null || template == null || name.equals("")
				|| template.equals("")) {
			return;
		}
		if (!templates.containsKey(name)) {
			templates.put(name, template);
		}
	}

	public void closeTemplateSource(Object templateSource) throws IOException {

	}

	public Object findTemplateSource(String name) throws IOException {
		name = name.replace("_zh_CN", "");
		if (name == null || name.equals("") || name.equals(DEFAULT_TEMPLATE_KEY)) {
			name = DEFAULT_TEMPLATE_KEY;
			return templates.get(name);
		}else{
			
			return templateService.getTemplateByName(name).get(0).getContent();
		}
		
	}

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		return new StringReader((String) templateSource);
		
	}

}
