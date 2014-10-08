package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.service.impl.TemplateGenerationServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class PageAction extends ActionSupport {
	
	private static final long serialVersionUID = 5898732054973866305L;
	
	@Autowired
	private TemplateGenerationServiceImpl templateGenerationService;
	private String templateName;
	
	public String execute(){
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		
		Map<String, Object> session = context.getSession();
		
		Map<String, Object> parameter = context.getParameters();
		
		String filePath = null;
		String savePath = ServletActionContext.getServletContext().getRealPath("");
		// 分目录存放用户图片
		savePath = savePath + "/static/";
		
		String html = null;
		
		if(StringUtils.equals("news_index.ftl", templateName)){
			filePath = savePath + "news/index";
		}else if(StringUtils.equals("knowledge_index.ftl", templateName)){
			filePath = savePath + "knowledge/index";
		}
		
		if(StringUtils.isNotEmpty(filePath)){
			File file = new File(filePath);
			
			if(file.exists() && FileUtils.isFileNewer(file, System.currentTimeMillis() - 1000 * 60 * 10)){
				try {
					html = FileUtils.readFileToString(file, "UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				html = templateGenerationService.generate(templateName, parameter, request, session);
				
				try {
					if(!file.getParentFile().exists()){
						FileUtils.forceMkdir(file.getParentFile());
					}
					FileUtils.writeStringToFile(file, html, "UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			html = templateGenerationService.generate(templateName, parameter, request, session);
		}
		

		
		response.setContentType("text/html;charaset=" + "UTF-8");
		Writer out;
		try {
			out = response.getWriter();
			out.write(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Action.NONE;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	 
}
