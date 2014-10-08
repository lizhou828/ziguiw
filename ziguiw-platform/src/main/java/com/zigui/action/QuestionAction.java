package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.Answer;
import com.zigui.domain.Question;
import com.zigui.domain.UserBase;
import com.zigui.service.impl.AnswerServiceImpl;
import com.zigui.service.impl.QuestionServiceImpl;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class QuestionAction extends BaseAction {

	private static final long serialVersionUID = 7754846528884186081L;
	
	private Question question;
	private Page<Question> questions;
	private Answer bestAnswer;
	
	private Map<String, String> query = new HashMap<String, String>();
	
	private int pageNo = 1;
	private int pageSize = 10;
	private String orderBy = "createTime";
	private boolean isAsc = false;
	private long questionId;
	private long creatorId;
	private int queryType;
	private Long[] opIds;
	private int approveFlag = 1;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String orderField = "createTime";
	
	@Autowired
	private QuestionServiceImpl questionService;
	
	@Autowired
	private AnswerServiceImpl answerService;
	
	public void validateSave(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		UserBase user = (UserBase)session.get("VALID_USER");
		
		if(user == null || user.getId() == 0L){
			this.addFieldError("user", "您好，必须要登录之后才可以提问");
			return;
		}else if(question == null || StringUtils.isEmpty(question.getTitle())){
			this.addFieldError("question.title", "标题不能为空");
			return;
		}else if(question.getPendingTime() < 1 || question.getPendingTime() > 90){
			this.addFieldError("question.pendingTime", "问题的开放时间必须要1~90天");
			return;
		}else if(StringUtils.isBlank(question.getContent()) || question.getContent().length() > 800){
			this.addFieldError("question.content", "问题内容不符合规定");
			return;
		}
	}
	
	public String save(){
		
		question.setCreatorId(user.getId());
		question.setCreatorNick(user.getNickName());
		
		//每成功提问一次	5分
		if(question.getId() < 1){
			changePoints(user.getId(), Constant.QUESTION, Constant.QUESTION_POINT);
		}
		
		questionService.saveOrUpdate(question);
		
		creatorId = user.getId();
		
		return Action.SUCCESS;
	}
	
	public String listByCondition(){
		if(queryType == 4){
			ActionContext context = ActionContext.getContext();
			Map<String, Object> session = context.getSession();
			UserBase user = (UserBase)session.get("VALID_USER");
			
			if(user != null && user.getId() != 0L){
				query.put("creatorId", String.valueOf(user.getId()));
			}
		}
		
		questions = questionService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc);
		
		return Action.SUCCESS;
	}
	
	//选择最佳答案
	public String satisfied(){
		Answer bestAnswer = answerService.getById(question.getBestAnswerId());
		
		question = questionService.getById(question.getId());
		
		question.setBestAnswerId(bestAnswer.getId());
		question.setBestAnswerAuthor(bestAnswer.getCreatorId());
		question.setStatus(1);
		
		questionService.saveOrUpdate(question);
		
		questionId = question.getId();
		
		//减提问人，加回答人
		changePoints(bestAnswer.getCreatorId(), Constant.QUESTION_SUB,-1 * question.getPoint());
		changePoints(user.getId(), Constant.QUESTION_ADD,question.getPoint());
		return Action.SUCCESS;
		
	}
	
	
	public String getById(){
		question = questionService.getById(questionId);
		
		if(question.getBestAnswerId() != 0L){
			bestAnswer = answerService.getById(question.getBestAnswerId());
		}
		
		return Action.SUCCESS;
	}

	public String restore(){
		questionService.restore(opIds);
		
		if(question.getBestAnswerId() != 0L){
			bestAnswer = answerService.getById(question.getBestAnswerId());
		}
		
		return Action.SUCCESS;
	}
	
	public String fakeDelete(){
		questionService.fakeDeleteQuestions(opIds);
		return Action.SUCCESS;
	}
	
	public String approve(){
		questionService.approveQuestions(opIds, approveFlag);
		return Action.SUCCESS;
	}
	
	public String restoreQuestion(){
		questionService.restore(opIds);	
		return Action.SUCCESS;
	}
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Page<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Page<Question> questions) {
		this.questions = questions;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public QuestionServiceImpl getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionServiceImpl questionService) {
		this.questionService = questionService;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public Answer getBestAnswer() {
		return bestAnswer;
	}

	public void setBestAnswer(Answer bestAnswer) {
		this.bestAnswer = bestAnswer;
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
