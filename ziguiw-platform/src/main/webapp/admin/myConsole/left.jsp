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
		<li><a href="<%=path%>/admin/myConsole/index.jsp" target="_self" <%if(subUrl.indexOf("myConsole/index") > 0) {%>class="on" <%} %>>我的首页</a></li>
		<li><a href="<%=path%>/admin/myConsole/administrator_modifyPasswd.jsp" target="_self" <%if(subUrl.indexOf("myConsole/administrator_modifyPasswd") > 0) {%>class="on" <%} %>>修改密码</a></li>
	</ul>
</div>
</div>