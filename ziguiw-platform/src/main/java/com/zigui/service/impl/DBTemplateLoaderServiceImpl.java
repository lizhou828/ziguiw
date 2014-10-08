package com.zigui.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;

import freemarker.cache.TemplateLoader;

public class DBTemplateLoaderServiceImpl implements TemplateLoader{
	
	@Autowired
	private TemplateServiceImpl templateService;
	

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		System.out.println("closeTemplateSource");
	}

	@Override
	public Object findTemplateSource(String templateName) throws IOException {
		templateName = templateName.replace("_zh_CN", "");
		return templateService.getTemplateByName(templateName).get(0);
	}

	@Override
	public long getLastModified(Object templateSource) {
		System.out.println("getLastModified");
		return ((com.zigui.domain.Template)templateSource).getLastModifyTime().getTime();
	}

	@Override
	public Reader getReader(Object templateSource, String encodeType) throws IOException {
		String templateContent = ((com.zigui.domain.Template)templateSource).getContent();
		return new StringReader(templateContent);
	}

}
