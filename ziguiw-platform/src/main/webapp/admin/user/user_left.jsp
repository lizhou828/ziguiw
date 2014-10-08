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
	<li><a href="<%=path%>/admin/user/user_welcome.jsp" target="_self" <%if(subUrl.indexOf("user_welcome") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/user/user_listByCondition_success.jsp?opType=0" target="_self" <%if(subUrl.indexOf("user_listByCondition") > 0&& (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>用户信息管理</a></li>
	<li><a href="<%=path%>/admin/user/user_listByCondition.action?opType=1&query.state=1" target="_self"  <%if(subUrl.indexOf("user_listByCondition") > 0 && (opType == null || "".equals(opType) || "1".equals(opType))) {%>class="on" <%} %>>推荐用户</a></li>
	<li><a href="<%=path%>/admin/user/userMood_listByCondition_success.jsp?opType=0" target="_self" <%if(subUrl.indexOf("userMood_listByCondition") > 0&& (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>说说管理</a></li>
	<li><a href="<%=path%>/admin/user/userMood_listByCondition.action?opType=1&query.state=1" target="_self"  <%if(subUrl.indexOf("userMood_listByCondition") > 0 && (opType == null || "".equals(opType) || "1".equals(opType))) {%>class="on" <%} %>>推荐说说</a></li>
	<li><a href="<%=path%>/admin/user/userDiary_listByCondition_success.jsp?opType=0" target="_self" <%if(subUrl.indexOf("userDiary_listByCondition") > 0&& (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>日志管理</a></li>
	<li><a href="<%=path%>/admin/user/userDiary_listByCondition.action?opType=1&query.state=1" target="_self"  <%if(subUrl.indexOf("userDiary_listByCondition") > 0 && (opType == null || "".equals(opType) || "1".equals(opType))) {%>class="on" <%} %>>推荐日志</a></li>
	<li><a href="<%=path%>/admin/user/userPhoto_listByCondition_success.jsp?opType=0" target="_self" <%if(subUrl.indexOf("userPhoto_listByCondition") > 0&& (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>照片管理</a></li>
	<li><a href="<%=path%>/admin/user/userPhoto_listByCondition.action?opType=1&query.state=1" target="_self"  <%if(subUrl.indexOf("userPhoto_listByCondition") > 0 && (opType == null || "".equals(opType) || "1".equals(opType))) {%>class="on" <%} %>>推荐照片</a></li>
	<li><a href="<%=path%>/admin/user/points_history.jsp" target="_self"  <%if(subUrl.indexOf("points_history") > 0) {%>class="on" <%} %>>积分管理</a></li>
	</ul>
</div>
</div>