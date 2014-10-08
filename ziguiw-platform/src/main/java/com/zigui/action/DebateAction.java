package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.Debate;
import com.zigui.service.impl.DebateServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class DebateAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4433897873557631975L;

	@Autowired
	private DebateServiceImpl debateService;
	
	private Debate debate;
	private Page<Debate> debates;
	private Map<String, String> query;
	private int pageNo = 1;
	private int pageSize = 10;
	
	private boolean isAsc = false;
	private long debateId;
	private int queryType;
	private Long[] opIds;
	private int approveFlag = 1;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String orderField = "createTime";
	
	public String listByCondition(){
		
		debates = debateService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, false);
		
		return Action.SUCCESS;
		
	}
	
	public void validateSaveOrUpdate(){
		if(StringUtils.isEmpty(debate.getTitle())){
			this.addFieldError("debate.title", "新闻标题不能为空");
			return;
		}
		if(StringUtils.isEmpty(debate.getPositiveOpinion())){
			this.addFieldError("debate.positiveOpinion", "正方观点不能为空");
			return;
		}
		
		if(StringUtils.isEmpty(debate.getNegativeOpinion())){
			this.addFieldError("debate.negativeOpinion", "反方观点不能为空");
			return;
		}
		
	}
	
	public String getById(){
		debate = debateService.getById(debateId);
		
		return Action.SUCCESS;
	}

	public String saveOrUpdate() {//后台创建
		debate.setCreatorId(admin.getId());
		debate.setCreatorNick(admin.getNickName());
		debateService.saveOrUpdate(debate);
		return Action.SUCCESS;
	}
	
	public String fakeDelete(){
		debateService.fakeDeleteDebates(opIds);
		return Action.SUCCESS;
	}
	
	public String approve(){
		debateService.approveDebates(opIds, approveFlag);
		return Action.SUCCESS;
	}
	
	public String restoreDebate(){
		debateService.restore(opIds);	
		return Action.SUCCESS;
	}
	
	public Debate getDebate() {
		return debate;
	}

	public void setDebate(Debate debate) {
		this.debate = debate;
	}

	public Page<Debate> getDebates() {
		return debates;
	}

	public void setDebates(Page<Debate> debates) {
		this.debates = debates;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public long getDebateId() {
		return debateId;
	}

	public void setDebateId(long debateId) {
		this.debateId = debateId;
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

	public int getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(int approveFlag) {
		this.approveFlag = approveFlag;
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
