package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.*;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.service.impl.HomeWorkServiceImpl;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class HomeWorkAction  extends BaseAction {
	
	private static final long serialVersionUID = 669963608029364302L;
	
	private static Log log = LogFactory.getLog("BUSINESS_DATA");
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@Autowired
	private HomeWorkServiceImpl homeWorkService;
	
	private HomeWork homeWork;
	
	private Map<String, String> query;
	
	private Page<HomeWork> homeWorks;


	private boolean isAsc = false;
	private long homeWorkId;
	private int queryType;
	private Long[] opIds;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String orderField = "createTime";
	
	private String userId;
	
	public String saveOrUpdate(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		String sid = "" + ub.getId();
		
		Teacher teacher = null;
		if(ub.getType() != null && ub.getType().intValue() == Constant.TEACHER_TYPE && ub.getForeignKey() != 0L){
			teacher = commonService.get(Teacher.class, ub.getForeignKey().longValue());
		}
		
		if(teacher == null){
			return "business_no_auth";
		}
		
		homeWork.setTeacher(teacher);
		homeWork.setCreatorId(user.getId());
		homeWork.setCreatorNick(user.getNickName());
		homeWorkService.saveOrUpdate(homeWork);
		return Action.SUCCESS;
	}
	
	//时间格式，主要用来处理用户输入的查询开始和结束时间
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
	
	public String listByCondition(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		String sid = "" + ub.getId();
		String bjId = "";
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			System.out.println("parent:" + parent);
			System.out.println("student:" + student);
			
			bjId = student.getBj_ID();
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
			
			query.put("opIds", bjId);
			
			//仅仅查询有效的作业
			query.put("state", "1");
			
			homeWorks = homeWorkService.listByCondition(query, new Integer(currentPage).intValue(),
					new Integer(countPerPage).intValue(), orderField, isAsc);	
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.STUDENT_TYPE && ub.getForeignKey() != 0L){
			Student student = commonService.get(Student.class, (int)ub.getForeignKey().longValue());
			
			bjId = student.getBj_ID();
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
			
			query.put("opIds", bjId);
			
			//仅仅查询有效的作业
			query.put("state", "1");
			
			homeWorks = homeWorkService.listByCondition(query, new Integer(currentPage).intValue(),
					new Integer(countPerPage).intValue(), orderField, isAsc);	
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.TEACHER_TYPE && ub.getForeignKey() != 0L){
			Teacher teacher = commonService.get(Teacher.class, ub.getForeignKey().longValue());
			
			Object[] objs = new Object[]{teacher};
			
			String hql = "from HomeWork where teacher = ? and state = 1 order by createTime desc";
			if(StringUtils.isNotEmpty(query.get("startTime")) && !StringUtils.equals(query.get("startTime"), "null")){
				hql = hql + " createTime > ? ";
			}
			
			if(StringUtils.isNotEmpty(query.get("endTime")) && !StringUtils.equals(query.get("endTime"), "null")){
				hql = hql + " createTime > ? ";
			}
			
			Page<Object> temp = commonService.getByHql("from HomeWork where teacher = ? and state = 1 order by createTime desc", new Integer(currentPage).intValue(),
					new Integer(countPerPage).intValue(), new Object[]{teacher});
			
			homeWorks = CommonUtils.transform(HomeWork.class, temp);
		}
		
			
		return Action.SUCCESS;
	}
	
	public String getById(){		
		homeWork = homeWorkService.getById(homeWorkId);
		return Action.SUCCESS;
	}

	public String fakeDelete(){
		homeWorkService.delete(opIds);
		return Action.SUCCESS;
	}

	public HomeWork getHomeWork() {
		return homeWork;
	}

	public void setHomeWork(HomeWork homeWork) {
		this.homeWork = homeWork;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public Page<HomeWork> getHomeWorks() {
		return homeWorks;
	}

	public void setHomeWorks(Page<HomeWork> homeWorks) {
		this.homeWorks = homeWorks;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public long getHomeWorkId() {
		return homeWorkId;
	}

	public void setHomeWorkId(long homeWorkId) {
		this.homeWorkId = homeWorkId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
