package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;

public class VipAction  extends BaseAction implements ServletContextAware {
	
	private ServletContext context;
	
	private Page<Object> pageObj;
	
	 private String countPerPage = "10";

	private String currentPage = "1";
	
	private String type = "4";
	
	@Autowired
	private CommonServiceImpl commonService;
	
	public String getPendingVipUsers(){
		  if(StringUtils.equals("1", type)){
			  pageObj = commonService.getByHql("from School where state = 3", NumberUtils.toInt(currentPage), NumberUtils.toInt(countPerPage), null);
		  }else if(StringUtils.equals("2", type)){
			  pageObj = commonService.getByHql("from Teacher where state = 3", NumberUtils.toInt(currentPage), NumberUtils.toInt(countPerPage), null);
		  }else if(StringUtils.equals("3", type)){
			  pageObj = commonService.getByHql("from Parent where state = 3", NumberUtils.toInt(currentPage), NumberUtils.toInt(countPerPage), null);
		  }else if(StringUtils.equals("4", type)){
			  pageObj = commonService.getByHql("from Student where state = 3", NumberUtils.toInt(currentPage), NumberUtils.toInt(countPerPage), null);
		  }
		  
		  return Action.SUCCESS;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	

	public Page<Object> getPageObj() {
		return pageObj;
	}

	public void setPageObj(Page<Object> pageObj) {
		this.pageObj = pageObj;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
