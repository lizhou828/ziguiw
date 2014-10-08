<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String subUrl = request.getRequestURL().toString();
String type = request.getParameter("type");
%>
<div class="left">
<a href="" target="_self" class="logo"><img src="<%=path%>/images/logo.gif" alt="ZIGUICMD管理后台" width="155" /></a>
<div class="sidebar">
	<ul>
	<li><a href="<%=path%>/admin/notify/notify_welcome.jsp" target="_self" <%if(subUrl.indexOf("notify_welcome") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/notify/notify_getAll_success.jsp" target="_self" <%if(subUrl.indexOf("notify_getAll") > 0) {%>class="on" <%} %>>内容提醒</a></li>
	
	</ul>
</div>
</div>