package com.zigui.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.CcczgInfoSouce;
import com.zigui.service.IResourcesAuditService;


/*
 * 后台管理-- 教学资源审核
 * @author Liujinayu
 * Date:12-11-20
 * 
 * */

@Controller
@Scope("prototype")
public class ResourcesAuditAction extends PageSourceAction {
	
	public final static int PAGENUM_EXAM = 15;
	
	@Autowired
	private IResourcesAuditService resourcesAuditService;

	private String subjectId;//资源id
	private Long checkSign;//审核状态
	private String resname;//资源名
	private String queryType;//条件类型
	private CcczgInfoSouce ccczgsouce;//资源信息对象
	
	
	/**
	 * 未审核资源列表(带分页)
	 * @return
	 */
	public String list(){
		try {
			//page分页信息
			// 设置每页显示的条数
			page.setEveryPage(PAGENUM_EXAM);
			// 根据statePage进行Page对象设置，并查询
			if (gotoPage == null || gotoPage.length() == 0) {
				gotoPage = "1";
			}
			page.setCurrentPage(Integer.parseInt(gotoPage));
			result=resourcesAuditService.getResouce(page,queryType);
			ActionContext.getContext().put("queryType", queryType);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return SUCCESS;
	}
	
	/**
	 * 按条件获取资源列表
	 * @return
	 */
	public String listBykey(){
		try {
			StringBuffer paramsql=new StringBuffer(" and 1=1 ");
			//page分页信息
			// 设置每页显示的条数
			page.setKeyword(StringUtils.trimToNull(resname));
			page.setEveryPage(PAGENUM_EXAM);
			// 根据statePage进行Page对象设置，并查询
			if (gotoPage == null || gotoPage.length() == 0) {
				gotoPage = "1";
			}
			page.setCurrentPage(Integer.parseInt(gotoPage));
			result=resourcesAuditService.getResouce(page, queryType);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return SUCCESS;
	}

	/**
	 * 审核页面初始化
	 * @return
	 * 
	 * http://localhost:8080/ccczgPro/login!login.action
	 */
	@SuppressWarnings("unused")
	public String open(){
		String id = StringUtils.trimToNull(getParameter("subjectId"));
		if(id == null){
			this.addActionMessage("资源id不存在！");
			return ERROR;
		}
		ccczgsouce = resourcesAuditService.get(id);
		return SUCCESS;
	}
	
	/**
	 * 审核
	 * @return
	 */
	public String updAuditResources(){
		String id = StringUtils.trimToNull(getParameter("subjectId"));
		String check = StringUtils.trimToNull(getParameter("checkSign"));
		checkSign=Long.valueOf(check);
		if(id == null){
			this.addActionMessage("资源id不存在！");
			return ERROR;
		}
		resourcesAuditService.updateResources(id, checkSign);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public IResourcesAuditService getResourcesAuditService() {
		return resourcesAuditService;
	}
	public void setResourcesAuditService(
			IResourcesAuditService resourcesAuditService) {
		this.resourcesAuditService = resourcesAuditService;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public Long getCheckSign() {
		return checkSign;
	}
	public void setCheckSign(Long checkSign) {
		this.checkSign = checkSign;
	}
	public String getResname() {
		return resname;
	}
	public void setResname(String resname) {
		this.resname = resname;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public CcczgInfoSouce getCcczgsouce() {
		return ccczgsouce;
	}
	public void setCcczgsouce(CcczgInfoSouce ccczgsouce) {
		this.ccczgsouce = ccczgsouce;
	}
	
	
	
	
}
