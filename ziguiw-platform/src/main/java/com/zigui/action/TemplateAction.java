package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.Template;
import com.zigui.service.impl.TemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TemplateAction extends ActionSupport {

	private static final long serialVersionUID = -8363698134943199366L;
	
	@Autowired
	private TemplateServiceImpl templateService;
	private Template template;
	private long templateId;
	private List<Template> templates;
	private Long[] opIds;
	
	public String saveOrUpdate(){

		template.setContent(template.getContent().replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
		templateService.saveOrUpdate(template);
		
		return Action.SUCCESS;
	}
	
	public String getAll(){
		templates = templateService.getAllTemplates();
		
		return Action.SUCCESS;
	}
	
	public String getById(){
		template = templateService.getTemplateById(templateId);
		
		return Action.SUCCESS;
	}
	
	public String remove(){
		templateService.removeTemplates(opIds);
		
		return Action.SUCCESS;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}
	
	
}
