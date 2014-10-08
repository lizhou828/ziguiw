<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils,java.text.SimpleDateFormat,java.text.DateFormat"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>


<div class="content clearfix pr">


	<!--左侧菜单-->
	<%@ include file="../inc/info_left.jsp"%>
	
	<div class="w-760 r userCenterContent">
		<h2 class="title">信息中心</h2>
		<div class="title-bottom">.</div>
		<!--列表样式1-->
		<%
			String xsid = (String) request.getParameter("xsid");
			Student student = commonService.get(Student.class,
					new Long(xsid).intValue());
			String sid = (String) request.getParameter("sid");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		%>
		<table class="listStyle1" cellpadding="2" cellspacing="2" border="0">
			<tr>
				<td class="field1 datetime">孩子姓名</td>
				<td class="light"><%=student.getXs_xming()%></td>
			</tr>
			<tr>
				<td class="field1 datetime">出生日期</td>
				<td><%=student.getBirthday() != null ? dateFormat.format(student.getBirthday()) : ""%></td>
			</tr>
			<tr>
				<td class="field1 datetime">所在学校</td>
				<td class="light"><%=student.getSchool() != null ? student.getSchool().getSch_name() : ""%></td>
			</tr>
			<tr>
				<td class="field1 datetime">所在班级</td>
				<td class="light"><%=student.getClasz() != null ? student.getClasz().getBj_mcheng() : ""%></td>
			</tr>
			<tr>
				<td class="field1 datetime">学生卡号</td>
				<td><%=student.getIDCard() != null ? student.getIDCard() : ""%></td>
			</tr>

			
			<tr>
				<td class="field1 datetime">手机</td>
				<td class="light"><%=student.getHomephone() != null ? student.getHomephone() : ""%></td>
			</tr>

			<tr>
				<td class="field1 datetime">家庭住址</td>
				<td class="light"><%=student.getHomeaddress() != null ? student.getHomeaddress() : ""%></td>
			</tr>
			<tr>
				<td class="field1 datetime">民族</td>
				<td class="light"><%=student.getMzhu() != null ? student.getMzhu() : ""%></td>
			</tr>
			<tr>
				<td class="field1 datetime">政治面貌</td>
				<td class="light"><%=student.getPoliticalFace() != null ? student.getPoliticalFace() : ""%></td>
			</tr>
		</table>
		<br>
		<p>&nbsp;</p>
		<p>
	</div>

</div>
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>