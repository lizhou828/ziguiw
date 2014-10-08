package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.ConfigCenter;
import com.zigui.service.impl.ConfigCenterServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class ConfigCenterAction extends ActionSupport {
	private String content;
	
	private String noescapeContent;
	
	@Autowired
	private ConfigCenterServiceImpl configCenterService;
	
	public String getConfig(){
		content = configCenterService.getConfig() != null ? configCenterService.getConfig().getContent() : "";
		
		noescapeContent = StringUtils.replace(content, "<br/>", "\r\n");
		
		return Action.SUCCESS;
	}
	
	public String saveOrUpdate(){
		ConfigCenter cc = new ConfigCenter();
		cc.setId(1L);
		StringReader sr = new StringReader(content);
		BufferedReader br = new BufferedReader(sr);
		
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		try {
			while((line = br.readLine()) != null){
				sb.append(StringUtils.trim(line));
				if(!line.endsWith("<br/>")){
					sb.append("<br/>");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		cc.setContent(sb.toString());
		
		configCenterService.saveOrUpdate(cc);
		
		return Action.SUCCESS;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNoescapeContent() {
		return noescapeContent;
	}

	public void setNoescapeContent(String noescapeContent) {
		this.noescapeContent = noescapeContent;
	}
	
	
}
