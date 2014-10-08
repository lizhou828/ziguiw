package com.zigui.action;

import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.*;
import com.zigui.service.impl.BussnissDataServiceImpl;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 业务数据拉取接口
 * 
 * 主要是拉取学校公告、考勤和一卡通消费。通过http请求子贵1.0的服务
 *
 */
public class BussnissDataAction extends BaseAction {
	
	@Autowired
	private BussnissDataServiceImpl bussnessDataService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String type;
	
	private String queryType;
	
	private String queryId;
	
	private Page<Map> datas;
	
	public String queryData(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		
		if(ub == null || ub.getId() < 1){
			return "USER_NO_LOGIN";
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			if(student == null){
				student = new Student();
			}
			queryType = "student";
			queryId = "" + student.getXs_id();
			
			if(StringUtils.equals(type, "schoolnotice")){
				queryType = "school";
				queryId = "" + student.getSchool().getXxID();
			}
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.STUDENT_TYPE && ub.getForeignKey() != 0L){
			Student student = commonService.get(Student.class, (int)ub.getForeignKey().longValue());
			if(student == null){
				student = new Student();
			}
			
			queryType = "student";
			queryId = "" + student.getXs_id();
			
			if(StringUtils.equals(type, "schoolnotice")){
				queryType = "school";
				queryId = "" + student.getSchool().getXxID();
			}
			
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.TEACHER_TYPE && ub.getForeignKey() != 0L){
			Teacher teacher = commonService.get(Teacher.class, ub.getForeignKey().longValue());
			if(teacher == null){
				teacher = new Teacher();
			}
			queryType = "teacher";
			
			Page<Object> temp = CommonUtils.getClaszByTeacher(ub);
			if(temp != null && CollectionUtils.isNotEmpty(temp.getList())){
				Page<TeacherClasz> claszs = CommonUtils.transform(TeacherClasz.class, temp);
				if(claszs.getList().get(0) != null && claszs.getList().get(0).getClasz() != null){
					queryId = "" + ((TeacherClasz)claszs.getList().get(0)).getClasz().getBj_id();
				}
			}
			
			
			if(StringUtils.equals(type, "schoolnotice")){
				queryType = "school";
				queryId = "" + teacher.getSchool().getXxID();
			}
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.SCHOOL_TYPE && ub.getForeignKey() != 0L){
			
			School school = commonService.get(School.class, ub.getForeignKey().longValue());
			
			if(school == null){
				school = new School();
			}
			queryType = "school";
			queryId = "" + school.getXxID();
			
		}
		
		datas = bussnessDataService.queryBusnissData(type, NumberUtils.toInt(currentPage, 1), NumberUtils.toInt(countPerPage, 10), queryType, queryId, "", false);
		
		return type;
	}

	public BussnissDataServiceImpl getBussnessDataService() {
		return bussnessDataService;
	}

	public void setBussnessDataService(BussnissDataServiceImpl bussnessDataService) {
		this.bussnessDataService = bussnessDataService;
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

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public Page<Map> getDatas() {
		return datas;
	}

	public void setDatas(Page<Map> datas) {
		this.datas = datas;
	}

}
