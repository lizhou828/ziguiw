<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.zigui.domain.UserBase"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page import="com.zigui.service.impl.UserServiceImpl"%>
<%@ page import="com.zigui.service.impl.SchoolServiceImpl"%>
<%@ page import="com.zigui.service.impl.CommonServiceImpl"%>
<%@ page import="com.zigui.domain.SpaceVisitHistory"%>
<%@ page import="com.zigui.utils.BeanFactoryUtils"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ page import="com.zigui.domain.*"%>
<%@ page import="com.zigui.domain.SchoolInfo"%>
<%@ page import="org.apache.commons.httpclient.*"%>
<%@ page import="org.apache.commons.httpclient.methods.GetMethod"%>
<%@ page import="org.apache.commons.httpclient.params.HttpMethodParams"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONSerializer"%>
<%@ page import="com.zigui.utils.ConfigUtils,com.zigui.utils.Page"%>
<%@ page import="java.io.*,org.apache.commons.lang.StringUtils,org.apache.commons.lang.exception.ExceptionUtils,org.apache.commons.codec.digest.DigestUtils"%>
<%@ page import="com.zigui.utils.CommonUtils"%>
<%@ page import="java.util.Map"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
if(!isHost){
	response.sendRedirect(path + "/user/login.jsp");
	return;
}

if(hostUser.getType() == 1){
	response.sendRedirect(path + "/school/school_manager.jsp");
}

CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
%>

<div class="l w-190 my_left clearfix" style="position:relative" >
		<div class="my_info tt userCenterLeft" style="min-height:500px">
			
			<div class="userCenterMenuBar">

			<ul>
			
			
			<%if(hostUser.getType() == 2){//教师
			   Map map = CommonUtils.getUserInfo(hostUser.getId());
			   String userIds = (String)map.get("operstrs");
			   //long claszId = ((Clasz)map.get("class")).getBj_id();
			   String claszId = (String)map.get("class");
			 %>
			 <li><a href="<%=path%>/userCenter/userCenter.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("user_center") > 0){%>focus<%}%>">中心首页</a></li>
            <li><a href="<%=path%>/user/business/homeWork_listByCondition_success.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("homeWork") > 0){%>focus<%}%>">作业查询</a></li>
            <li><a href="<%=path%>/user/business/growArchives_listByCondition_success.jsp" class="bj21 font-2 <%if(request.getRequestURL().toString().indexOf("growArchives") > 0){%>focus<%}%>">成长档案</a></li>
            <li><a href="<%=path%>/user/business/user_listByCondition_success.jsp" class="bj17 font-2 
            <%if(request.getRequestURL().toString().indexOf("user_listByCondition_success") > 0 || request.getRequestURL().toString().indexOf("studentView") > 0 ){%>focus<%}%>">
            	学籍档案</a></li>
             <li><a href="<%=path%>/info/queryBussniss.action?type=schoolnotice&countPerPage=20" class="bj26 font-2 <%if(StringUtils.equals(request.getParameter("type"), "schoolnotice")){%>focus<%}%>">学校公告</a></li>
            <li><a href="<%=path%>/user/business/commonMessage_listByCondition_success.jsp?kind=4" class="bj18 font-2 <%if(request.getRequestURL().toString().indexOf("commonMessage") > 0 && StringUtils.equals(request.getParameter("kind"), "4")){%>focus<%}%>">班级通知</a></li>
            <li><a href="<%=path%>/user/business/mail.jsp?kind=3" class="bj11 font-2 <%if(request.getRequestURL().toString().indexOf("mail") > 0 || request.getRequestURL().toString().indexOf("ggregation") > 0){%>focus<%}%>">老师留言</a></li>
            <li><a href="<%=path%>/user/business/remark.jsp?kind=7" class="bjb font-2 <%if(request.getRequestURL().toString().indexOf("remark") > 0 && StringUtils.equals(request.getParameter("kind"), "7")){%>focus<%}%>">学生评语</a></li>
            <li><a href="<%=path%>/info/queryBussniss.action?type=attendanceRec&countPerPage=20" class="bj8 font-2 <%if(StringUtils.equals(request.getParameter("type"), "attendanceRec")){%>focus<%}%>">考勤查询</a></li>
			
			
			<%}else if(hostUser.getType() == 3){//家长
			   Map map = CommonUtils.getUserInfo(hostUser.getId());
			   String userIds = (String)map.get("operstrs");
			   long parentId = hostUser.getForeignKey();
			 %>
			 <li><a href="<%=path%>/userCenter/userCenter.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("user_center") > 0){%>focus<%}%>">中心首页</a></li>
            <li><a href="<%=path%>/user/business/homeWork_listByCondition_success.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("homeWork") > 0){%>focus<%}%>">作业查询</a></li>
            <li><a href="<%=path%>/user/business/growArchives_listByCondition_success.jsp" class="bj21 font-2 <%if(request.getRequestURL().toString().indexOf("growArchives") > 0){%>focus<%}%>">成长档案</a></li>
            <li><a href="<%=path%>/user/business/user_listByCondition_success.jsp" class="bj17 font-2 
            <%if(request.getRequestURL().toString().indexOf("user_listByCondition_success") > 0 || request.getRequestURL().toString().indexOf("studentView") > 0 ){%>focus<%}%>">
            	学籍档案</a></li>
            <li><a href="<%=path%>/user/business/growFootmark_listByCondition_success.jsp" class="bj12 font-2 <%if(request.getRequestURL().toString().indexOf("growFootmark") > 0){%>focus<%}%>">成长足迹</a></li>
            <li><a href="<%=path%>/user/business/healthArchives_listByCondition_success.jsp" class="bj12 font-2 <%if(request.getRequestURL().toString().indexOf("healthArchives") > 0){%>focus<%}%>">健康档案</a></li>
             <li><a href="<%=path%>/info/queryBussniss.action?type=schoolnotice&countPerPage=20" class="bj26 font-2 <%if(StringUtils.equals(request.getParameter("type"), "schoolnotice")){%>focus<%}%>">学校公告</a></li>
            <li><a href="<%=path%>/user/business/commonMessage_listByCondition_success.jsp?kind=4" class="bj18 font-2 <%if(request.getRequestURL().toString().indexOf("commonMessage") > 0 && StringUtils.equals(request.getParameter("kind"), "4")){%>focus<%}%>">班级通知</a></li>
            <li><a href="<%=path%>/user/business/mail.jsp?kind=3" class="bj11 font-2 <%if(request.getRequestURL().toString().indexOf("mail") > 0){%>focus<%}%>">老师留言</a></li>
            <li><a href="<%=path%>/user/business/remark.jsp?kind=7" class="bjb font-2 <%if(request.getRequestURL().toString().indexOf("remark") > 0 && StringUtils.equals(request.getParameter("kind"), "7")){%>focus<%}%>">学生评语</a></li>
            <li><a href="<%=path%>/info/queryBussniss.action?type=attendanceRec&countPerPage=20" class="bj8 font-2 <%if(StringUtils.equals(request.getParameter("type"), "attendanceRec")){%>focus<%}%>">考勤查询</a></li>
            <li><a href="<%=path%>/info/queryBussniss.action?type=examresultList&countPerPage=20" class="bj10 font-2 <%if(StringUtils.equals(request.getParameter("type"), "examresultList")){%>focus<%}%>">成绩查询</a></li>
            <li><a href="<%=path%>/info/queryBussniss.action?type=recordflow&countPerPage=20" class="bj14 font-2 <%if(StringUtils.equals(request.getParameter("type"), "recordflow")){%>focus<%}%>">一卡通消费查询</a></li>
            
            
            <%} else if(hostUser.getType() == 4){//学生
//			   Map map = CommonUtils.getUserInfo(hostUser.getId());
//			   String userIds = (String)map.get("operstrs");
//			   long studentId = hostUser.getForeignKey();
			 %>
			 <li><a href="<%=path%>/userCenter/userCenter.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("user_center") > 0){%>focus<%}%>">中心首页</a></li>
			 <li><a href="<%=path%>/user/business/homeWork_listByCondition_success.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("homeWork") > 0){%>focus<%}%>">作业查询</a></li>
			 <li><a href="<%=path%>/user/business/growArchives_listByCondition_success.jsp" class="bj21 font-2 <%if(request.getRequestURL().toString().indexOf("growArchives") > 0){%>focus<%}%>">成长档案</a></li>
			 <li><a href="<%=path%>/user/business/user_listByCondition_success.jsp" class="bj17 font-2 
            <%if(request.getRequestURL().toString().indexOf("user_listByCondition_success") > 0 || request.getRequestURL().toString().indexOf("studentView") > 0 ){%>focus<%}%>">
            	学籍档案</a></li>
			 <li><a href="<%=path%>/user/business/growFootmark_listByCondition_success.jsp" class="bj12 font-2 <%if(request.getRequestURL().toString().indexOf("growFootmark") > 0){%>focus<%}%>">成长足迹</a></li>
			 <li><a href="<%=path%>/user/business/healthArchives_listByCondition_success.jsp" class="bj12 font-2 <%if(request.getRequestURL().toString().indexOf("healthArchives") > 0){%>focus<%}%>">健康档案</a></li>
			 <li><a href="<%=path%>/info/queryBussniss.action?type=schoolnotice&countPerPage=20" class="bj26 font-2 <%if(StringUtils.equals(request.getParameter("type"), "schoolnotice")){%>focus<%}%>">学校公告</a></li>
            <li><a href="<%=path%>/user/business/commonMessage_listByCondition_success.jsp?kind=4" class="bj18 font-2 <%if(request.getRequestURL().toString().indexOf("commonMessage") > 0 && StringUtils.equals(request.getParameter("kind"), "4")){%>focus<%}%>">班级通知</a></li>
            <li><a href="<%=path%>/user/business/mail.jsp?kind=3" class="bj11 font-2 <%if(request.getRequestURL().toString().indexOf("mail") > 0){%>focus<%}%>">老师留言</a></li>
            <li><a href="<%=path%>/user/business/remark.jsp?kind=7" class="bjb font-2 <%if(request.getRequestURL().toString().indexOf("remark") > 0 && StringUtils.equals(request.getParameter("kind"), "7")){%>focus<%}%>">学生评语</a></li>
            <li><a href="<%=path%>/info/queryBussniss.action?type=attendanceRec&queryType=student&countPerPage=20" class="bj8 font-2 <%if(StringUtils.equals(request.getParameter("type"), "attendanceRec")){%>focus<%}%>">考勤查询</a></li>
            <li><a href="<%=path%>/info/queryBussniss.action?type=examresultList&queryType=student&countPerPage=20" class="bj10 font-2 <%if(StringUtils.equals(request.getParameter("type"), "examresultList")){%>focus<%}%>">成绩查询</a></li>
            <li><a href="<%=path%>/info/queryBussniss.action?type=recordflow&queryType=student&countPerPage=20" class="bj14 font-2 <%if(StringUtils.equals(request.getParameter("type"), "recordflow")){%>focus<%}%>">一卡通消费查询</a></li>
			
			<%} else if(hostUser.getType() == 1){//学校
//			   Map map = CommonUtils.getUserInfo(hostUser.getId());
//			   String userIds = (String)map.get("operstrs");
//			   String studentId = "0";
			 %>
			<li><a href="<%=path%>/school/school_manager.jsp" class="bj26 font-2">学校管理</a></li>
			<%} %>

            <li><a href="<%=path%>/user/business/common_question.jsp" class="bj14 font-2 <%if(request.getRequestURL().toString().indexOf("common_question") > 0){%>focus<%}%>">常见问题</a></li>
			</ul>

				
			</div>
            
		</div>
	</div>
