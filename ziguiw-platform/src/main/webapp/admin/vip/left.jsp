<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String subUrl = request.getRequestURL().toString();
String opType = request.getParameter("opType");
%>
<div class="left">
<a href="" target="_self" class="logo"><img src="<%=path%>/images/logo.gif" alt="ZIGUICMD管理后台" width="155" /></a>
<div class="sidebar">
	<ul>
	<li><a href="<%=path%>/admin/vip/vip_welcome.jsp" target="_self" <%if(subUrl.indexOf("vip_welcome") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/vip/school_vip.jsp" target="_self" <%if(subUrl.indexOf("school_vip") > 0) {%>class="on" <%} %>>学校用户列表</a></li>
	<li><a href="<%=path%>/admin/vip/teacher_vip.jsp" target="_self"  <%if(subUrl.indexOf("teacher_vip") > 0) {%>class="on" <%} %>>教师用户列表</a></li>
	<li><a href="<%=path%>/admin/vip/student_vip.jsp" target="_self"  <%if(subUrl.indexOf("student_vip") > 0) {%>class="on" <%} %>>学生用户列表</a></li>
	<li><a href="<%=path%>/admin/vip/parent_vip.jsp" target="_self"  <%if(subUrl.indexOf("parent_vip") > 0) {%>class="on" <%} %>>家长用户列表</a></li>
	
	<li><a href="<%=path%>/admin/vip/school_audit.jsp" target="_self" <%if(subUrl.indexOf("school_audit") > 0) {%>class="on" <%} %>>学校用户审批</a></li>
	<li><a href="<%=path%>/admin/vip/teacher_audit.jsp" target="_self" <%if(subUrl.indexOf("teacher_audit") > 0) {%>class="on" <%} %>>教师用户审批</a></li>
	<li><a href="<%=path%>/admin/vip/student_audit.jsp" target="_self"  <%if(subUrl.indexOf("student_audit") > 0) {%>class="on" <%} %>>学生用户审批</a></li>
	<li><a href="<%=path%>/admin/vip/parent_audit.jsp" target="_self"  <%if(subUrl.indexOf("parent_audit") > 0) {%>class="on" <%} %>>家长用户审批</a></li>
	
	</ul>
</div>
</div>