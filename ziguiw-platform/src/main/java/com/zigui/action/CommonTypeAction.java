package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.CommonType;
import com.zigui.service.impl.CommonTypeServiceImpl;
import com.zigui.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class CommonTypeAction extends BaseAction {
	
	private static final long serialVersionUID = 669963698029364902L;
	
	@Autowired
	private CommonTypeServiceImpl commonTypeService;
	
	private CommonType commonType;
	
	private Map<String, String> query;
	
	private Page<CommonType> commonTypes;


	private boolean isAsc = false;
	private long commonTypeId;
	private int queryType;
	private Long[] opIds;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String orderField = "createTime";
	
	public String saveOrUpdate(){		
		commonType.setCreatorId(user.getId());
		commonType.setCreatorNick(user.getNickName());
		commonTypeService.saveOrUpdate(commonType);	
		return Action.SUCCESS;
	}
	
	public String listByCondition(){
		commonTypes = commonTypeService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc);		
		return Action.SUCCESS;
	}
	
	public String getById(){		
		commonType = commonTypeService.getById(commonTypeId);
		return Action.SUCCESS;
	}

	public String fakeDelete(){
		commonTypeService.fakeDelete(opIds);
		return Action.SUCCESS;
	}

	public CommonType getCommonType() {
		return commonType;
	}

	public void setCommonType(CommonType commonType) {
		this.commonType = commonType;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public Page<CommonType> getCommonTypes() {
		return commonTypes;
	}

	public void setCommonTypes(Page<CommonType> commonTypes) {
		this.commonTypes = commonTypes;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public long getCommonTypeId() {
		return commonTypeId;
	}

	public void setCommonTypeId(long commonTypeId) {
		this.commonTypeId = commonTypeId;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
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

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	
}
