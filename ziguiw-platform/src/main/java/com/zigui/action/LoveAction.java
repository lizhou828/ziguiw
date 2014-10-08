package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.Love;
import com.zigui.service.impl.LoveServiceImpl;
import com.zigui.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class LoveAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3362485748832412031L;

	private Map<String, String> query;

	private String orderField = "createTime";

	private String orderType = "desc";

	private String countPerPage = "10";

	private String currentPage = "1";

	private Love love;

	private Page<Love> pagedLoves;

	private long loveId;
	
	private Long[] opIds;
	
	private int opType = 0;
	
	private int approveFlag = 1;
	
	@Autowired
	private LoveServiceImpl loveService;
	
	public String saveOrUpdate() {//后台创建
		love.setCreatorId(admin.getId());
		love.setCreatorNick(admin.getCreatorNick());
		loveService.saveOrUpdate(love);
		return Action.SUCCESS;
	}
	
	public String delete(){
		loveService.delete(opIds);
		return Action.SUCCESS;
	}
	
	public String listByCondition() {
		boolean isAsc = false;
		if (orderType !=null && orderType.equals("ASC")) {
			isAsc = true;
		}
		pagedLoves = loveService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc,this.queryString);

		return Action.SUCCESS;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	public Love getLove() {
		return love;
	}

	public void setLove(Love love) {
		this.love = love;
	}

	public Page<Love> getPagedLoves() {
		return pagedLoves;
	}

	public void setPagedLoves(Page<Love> pagedLoves) {
		this.pagedLoves = pagedLoves;
	}

	public long getLoveId() {
		return loveId;
	}

	public void setLoveId(long loveId) {
		this.loveId = loveId;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

	public int getOpType() {
		return opType;
	}

	public void setOpType(int opType) {
		this.opType = opType;
	}

	public int getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(int approveFlag) {
		this.approveFlag = approveFlag;
	}
	
	
	
}
