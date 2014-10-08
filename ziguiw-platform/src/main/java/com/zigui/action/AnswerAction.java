package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.Answer;
import com.zigui.domain.UserBase;
import com.zigui.service.impl.AnswerServiceImpl;
import com.zigui.utils.Page;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 答案相关的操作
 *
 */
public class AnswerAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -967760818798252335L;

	@Autowired
	private AnswerServiceImpl answerService;
	
	private long questionId;
	private int pageNo = 1;
	private int pageSize = 10;
	private Answer answer;
	private Page<Answer> answers;
	private Long[] opIds;
	
	public void validateSave(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		UserBase user = (UserBase)session.get("VALID_USER");
		
		if(user == null || user.getId() == 0L){
			this.addFieldError("user", "您好，必须要登录之后才可以提问");
			return;
		}else if(StringUtils.isBlank(answer.getContent())){
			this.addFieldError("answer.content", "问题答案不能为空");
			return;
		}
	}
	
	public String save(){
//		ActionContext context = ActionContext.getContext(); 
//		Map<String, Object> session = context.getSession();
//		UserBase user = (UserBase)session.get("VALID_USER");
		
		answer.setCreatorId(user.getId());
		answer.setCreatorNick(user.getNickName());
		
		answerService.saveOrUpdate(answer);
		questionId = answer.getQuestion().getId();
		
		//回答获5分
		changePoints(user.getId(),1,5);
		return Action.SUCCESS;
	}
	
	public String getAnswerByQuestionId(){
		answers = answerService.getAnswerByQuestionId(questionId, pageNo, pageSize);
		
		return Action.SUCCESS;
	}
	
	public String getAjaxAnswerByQuestionId() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		answers = answerService.getAnswerByQuestionId(questionId, pageNo, pageSize);
		List<Answer> list = answers.getList();
		JSONObject obj = new JSONObject();
		obj.put("result", list);
		print(response,obj.toString());
		return null;
	}
	
	public String fakeDelete() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		answerService.fakeDeleteAnswers(opIds);
		print(response,"success");
		return null;
	}
	
//	public String batchFakeDelete(){
//		knowledgeService.fakeDeleteKnowledges(opIds);	
//		return Action.SUCCESS;
//	}
	/**
	 * 读取请求参数解析为JSON数据格式
	 * 
	 * @param request
	 * @return json格式的String对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected JSONObject readJson(HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, String[]> parameterMap = request.getParameterMap();
			Iterator<String> paIter = parameterMap.keySet().iterator();
			while (paIter.hasNext()) {
				String key = paIter.next().toString();
				String[] values = (String[])parameterMap.get(key);
				jsonObject.accumulate(key, values[0]);
			}
			//log.debug("从客户端获得json=" + jsonObject.toString());
		} catch (Exception e) {
			//log.error("获取json数据出错，错误信息如下：\n\t" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return jsonObject;
	}
	
    protected void print(HttpServletResponse response, String info) throws IOException {
        try {
        	  response.setHeader("Cache-Control", "no-cache");
        	  response.setContentType("text/json;charset=utf-8");
                response.getWriter().print(info);
        } catch (IOException e) {
                e.printStackTrace();
                throw e;
        }
     }

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
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

	public Page<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Page<Answer> answers) {
		this.answers = answers;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}
	
	

}
