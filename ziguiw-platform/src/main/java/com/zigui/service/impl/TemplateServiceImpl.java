package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.TemplateDao;
import com.zigui.domain.Template;
import com.zigui.utils.Page;

public class TemplateServiceImpl {
	
	private static final Log logger = LogFactory.getLog(TemplateServiceImpl.class);

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	@Autowired
	private TemplateDao templateDao;

	public long saveOrUpdate(Template template) {
		templateDao.saveOrUpdate(template);
		return template.getId();
	}

	public List<Template> getTemplateByName(String name) {

		List<Template> templates = templateDao.findBy(
				Template.class, "name", name);

		return templates;
	}

	public Template getTemplateById(long id) {
		return templateDao.get(Template.class, id);
	}

	public List<Template> getAllTemplates() {
		return templateDao.getAll(Template.class);
	}
	
	public void removeTemplates(long id) {
		templateDao.removeById(Template.class, id);
	}
	
	public void removeTemplates(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Template> page = templateDao.pagedQuery(Template.class, 1, ids.length,
				inCodition);
		
		if(logger.isDebugEnabled()){
			logger.debug("" + Arrays.toString(ids) + ":" + page);
		}

		for (Template template : page.getList()) {
			if(logger.isDebugEnabled()){
				logger.debug("start delete " + template);
			}
			templateDao.removeById(Template.class, template.getId());
			if(logger.isDebugEnabled()){
				logger.debug("end delete " + template);
			}
		}
	}

}
