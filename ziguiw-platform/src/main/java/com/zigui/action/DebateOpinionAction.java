package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.Debate;
import com.zigui.domain.DebateOpinion;
import com.zigui.domain.UserBase;
import com.zigui.service.impl.DebateOpinionServiceImpl;
import com.zigui.service.impl.DebateServiceImpl;
import com.zigui.utils.Page;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DebateOpinionAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7747947474770419866L;
	
	@Autowired
	private DebateOpinionServiceImpl debateOpinionService;
	
	@Autowired
	private DebateServiceImpl debateService;
	
	private long debateId;
	private int pageNo = 1;
	private int pageSize = 10;
	private DebateOpinion debateOpinion;
	private Page<DebateOpinion> debateOpinions;
	private Long[] opIds;
	
	public void validateSaveOrUpdate(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		UserBase user = (UserBase)session.get("VALID_USER");
		
		if(user == null || user.getId() == 0L){
			this.addFieldError("user", "您好，必须要登录之后才可以提问");
			return;
		}
//		else if(question == null || StringUtils.isEmpty(question.getTitle())){
//			this.addFieldError("question.title", "标题不能为空");
//			return;
//		}else if(question.getPendingTime() < 1 || question.getPendingTime() > 90){
//			this.addFieldError("question.pendingTime", "问题的开放时间必须要1~90天");
//			return;
//		}else if(StringUtils.isBlank(question.getContent()) || question.getContent().length() > 800){
//			this.addFieldError("question.content", "问题内容不符合规定");
//			return;
//		}
	}
	
	public String saveOrUpdate(){//前台
		debateOpinion.setCreatorId(user.getId());
		debateOpinion.setCreatorNick(user.getNickName());
		debateOpinionService.saveOrUpdate(debateOpinion);
		
		debateId = debateOpinion.getDebate().getId();
		Debate debate = debateService.getById(debateId);
		
		if(debateOpinion.getStatus() == 1){
			debate.setPositiveSupportCount(debate.getPositiveSupportCount()+1);
		}else if(debateOpinion.getStatus() == 2){
			debate.setNegativeSupportCount(debate.getNegativeSupportCount() + 1);
		}
		
		debateService.saveOrUpdate(debate);
		
		return Action.SUCCESS;
	}
	
	public String getOpinionsByDebateId(){
		debateOpinions = debateOpinionService.getDebateOpinionsByDebateId(debateId, pageNo, pageSize);
		return Action.SUCCESS;
	}
	
	public String getAjaxDebateOpinionsByDebateId() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		debateOpinions = debateOpinionService.getDebateOpinionsByDebateId(debateId, pageNo, pageSize);
		List<DebateOpinion> list = debateOpinions.getList();
		JSONObject obj = new JSONObject();
		obj.put("result", list);
		print(response,obj.toString());
		return null;
	}
	
	public String fakeDelete() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		debateOpinionService.fakeDeleteDebateOpinions(opIds);
		print(response,"success");
		return null;
	}
	
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
	public long getDebateId() {
		return debateId;
	}
	public void setDebateId(long debateId) {
		this.debateId = debateId;
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
	public DebateOpinion getDebateOpinion() {
		return debateOpinion;
	}
	public void setDebateOpinion(DebateOpinion debateOpinion) {
		this.debateOpinion = debateOpinion;
	}
	public Page<DebateOpinion> getDebateOpinions() {
		return debateOpinions;
	}
	public void setDebateOpinions(Page<DebateOpinion> debateOpinions) {
		this.debateOpinions = debateOpinions;
	}
	public Long[] getOpIds() {
		return opIds;
	}
	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}
	
	

}
