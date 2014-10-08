package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.*;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基础数据，主要是从1.0获取的那些数据
 */
public class BaseDataAction extends BaseAction {
	
	private static Log log = LogFactory.getLog("BUSINESS_DATA");
	
	@Autowired
	private transient CommonServiceImpl commonService;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String type;
	
	private String queryType;
	
	private String queryId;
	
	private Page<Map> datas;
	
	private UserBase user;
	
	private Page<Object> pageObj;
	
	private School school;
	
	private TeacherClasz clasz;
	
	private int teacherId;
	
	private String schoolId;
	
	private String[] sidArray;
	
	private long Xx_id;
	
	public String getTeachersBySchool(){
		
		long xx_id = user.getForeignKey();
		School school = commonService.get(School.class, xx_id);
		
		pageObj = commonService.getByHql("from Teacher where XxID = ?", new Integer(currentPage).intValue(), 
				new Integer(countPerPage).intValue(), new Object[]{school.getXxID()});
		
		return Action.SUCCESS;
	}
	
	public String getClaszByTeacher(){
		
		long teacherId = user.getForeignKey();
		
		if(log.isDebugEnabled()){
			log.debug("getClaszByTeacher|teacherId:" + teacherId);
		}
		
		pageObj = commonService.getByHql("from TeacherClasz where teacherID = ?", new Integer(currentPage).intValue(), 
				new Integer(countPerPage).intValue(), new Object[]{(int)teacherId});
		
		if(log.isDebugEnabled()){
			log.debug("getClaszByTeacher|pageObj:" + pageObj);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String getStudentByClasz(){
		pageObj = commonService.getByHql("from Student where Bj_ID = ?", new Integer(currentPage).intValue(), 
				new Integer(countPerPage).intValue(), new Object[]{"" + clasz.getClasz().getBj_id()});
		
		return Action.SUCCESS;
	}
	
	/**
	 * 通过班级ID获取学生
	 * 
	 * @return
	 */
	public String ajaxGetStudentByClasz(){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		pageObj = commonService.getByHql("from Student where Bj_ID = ?", new Integer(currentPage).intValue(), 
				100, new Object[]{"" + clasz.getClasz().getBj_id()});
		
		StringBuilder sb = new StringBuilder();
		
		for(Object obj : pageObj.getList()){
			Student student = (Student)obj;
			
			UserBase ub = CommonUtils.getUserByXsID(student.getXs_id());
			
			sb.append("<option value=" + ub.getId() + ">" + student.getXs_xming() + "</option>");
		}
		
		print(response, sb.toString());
		
		return Action.NONE;
	}
	
	/**
	 * 通过班级ID获取学生
	 * 
	 * @return
	 */
	public String ajaxGetStudentByClasz1(){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		pageObj = commonService.getByHql("from Student where Bj_ID = ?", new Integer(currentPage).intValue(), 
				100, new Object[]{"" + clasz.getClasz().getBj_id()});
		
		StringBuilder sb = new StringBuilder();
		
		for(Object obj : pageObj.getList()){
			Student student = (Student)obj;
			
			UserBase ub = CommonUtils.getUserByXsID(student.getXs_id());
			
			sb.append("<option value=" + student.getXs_id() + ">" + student.getXs_xming() + "</option>");
		}
		
		print(response, sb.toString());
		
		return Action.NONE;
	}
	
	/**
	 * 通过教师ID获取班级列表
	 * 
	 * @return
	 */
	public String ajaxGetClaszByTeacher(){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		pageObj = commonService.getByHql("from TeacherClasz where teacherID = ?", 1, 
				100, new Object[]{teacherId});
		
		StringBuilder sb = new StringBuilder();
		
		if(pageObj != null && CollectionUtils.isNotEmpty(pageObj.getList())){
			for(Object obj : pageObj.getList()){
				TeacherClasz teacherClasz = (TeacherClasz)obj;
				
				sb.append("<option value=" + teacherClasz.getClasz().getBj_id() + ">" + teacherClasz.getClasz().getBj_mcheng() + "</option>");
			}
		}
		
		print(response, sb.toString());
		
		return Action.NONE;
	}
	
	
	/**
	 * 通过教师ID获取未选班级列表
	 * 
	 * @return
	 */
	public String ajaxGetNoClaszByTeacher(){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		
		pageObj = commonService.getByHql("from Clasz where XxID = ?", 1, 
				100, new Object[]{NumberUtils.toInt(schoolId)});
		
		System.out.println("school.getXxID():" + schoolId);
		System.out.println("teacherID:" + teacherId);
		
		Page<Object> pageObj1 = commonService.getByHql("from TeacherClasz where teacherID = ?", 1,
				100, new Object[]{teacherId});
		
//		List<Clasz> list1 = CommonUtils.transform(Clasz.class, pageObj).getList();
//		List<Clasz> list2 = CommonUtils.transform(Clasz.class, pageObj1).getList();
		
		StringBuilder sb = new StringBuilder();
		
		List<Clasz> claszs = new ArrayList<Clasz>(0);
		if(pageObj1 != null && CollectionUtils.isNotEmpty(pageObj1.getList())){
			claszs = new ArrayList<Clasz>(pageObj1.getList().size());
			for(Object obj1 : pageObj1.getList()){
				TeacherClasz teacherClasz1 = (TeacherClasz)obj1;
				
				claszs.add(teacherClasz1.getClasz());
			}
			
		}
		
		if(pageObj != null && CollectionUtils.isNotEmpty(pageObj.getList())){
			for(Object obj : pageObj.getList()){
				Clasz clasz = (Clasz)obj;
				
				System.out.println("clasz" + clasz);
				
				
				if(!claszs.contains(obj)){
					sb.append("<option value=" + clasz.getBj_id() + ">" + clasz.getBj_mcheng() + "</option>");
				}
				
			}
		}
		
		print(response, sb.toString());
		
		return Action.NONE;
	}
	
	/**
	 * 通过学校ID获取班级列表
	 * 
	 * @return
	 */
	public String ajaxGetClaszBySchoolId(){
		HttpServletResponse response = ServletActionContext.getResponse();
		
		school = commonService.get(School.class, Xx_id);
		
		pageObj = commonService.getByHql("from Clasz where XxID = ?", 1, 
				100, new Object[]{NumberUtils.toInt(school.getXxID())});
		
		StringBuilder sb = new StringBuilder();
		
		if(pageObj != null && CollectionUtils.isNotEmpty(pageObj.getList())){
			for(Object obj : pageObj.getList()){
				Clasz clasz = (Clasz)obj;
				
				sb.append("<option value=" + clasz.getBj_id() + ">" + clasz.getBj_mcheng() + "</option>");
			}
		}
		
		print(response, sb.toString());
		
		return Action.NONE;
	}
	
	public String getSubjectByTeacher(){
		long teacherId = user.getForeignKey();
		
		if(log.isDebugEnabled()){
			log.debug("getClaszByTeacher|teacherId:" + teacherId);
		}
		
		Teacher teacher = commonService.get(Teacher.class, teacherId);
		
		pageObj = commonService.getByHql("from Subject where XxID = ?", 1, 100, new Object[]{"" + teacher.getSchool().getXxID()});
		
		return Action.SUCCESS;
	}
	
	public String saveOrUpdateTeacherClasz(){
		if(ArrayUtils.isNotEmpty(sidArray)){
			commonService.update("delete TeacherClasz where teacherID = " + teacherId);
			for(String sid : sidArray){
				Clasz clasz = commonService.get(Clasz.class, NumberUtils.toLong(sid));
				
				TeacherClasz teacherClasz = new TeacherClasz();
				teacherClasz.setClasz(clasz);
				teacherClasz.setTeacherID(teacherId);
				
				commonService.save(teacherClasz);
			}
		}
		
		return Action.SUCCESS;
		
	}
	
	protected void print(HttpServletResponse response, String info) {
        try {
        	  response.setHeader("Cache-Control", "no-cache");
        	  response.setContentType("text/json;charset=utf-8");
            response.getWriter().print(info);
        } catch (Exception e) {
                e.printStackTrace();
        }
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


	public UserBase getUser() {
		return user;
	}


	public void setUser(UserBase user) {
		this.user = user;
	}


	public Page<Object> getPageObj() {
		return pageObj;
	}


	public void setPageObj(Page<Object> pageObj) {
		this.pageObj = pageObj;
	}

	public TeacherClasz getClasz() {
		return clasz;
	}

	public void setClasz(TeacherClasz clasz) {
		this.clasz = clasz;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String[] getSidArray() {
		return sidArray;
	}

	public void setSidArray(String[] sidArray) {
		this.sidArray = sidArray;
	}

	public long getXx_id() {
		return Xx_id;
	}

	public void setXx_id(long xx_id) {
		Xx_id = xx_id;
	}

}
