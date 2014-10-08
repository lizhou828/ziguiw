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
	<li><a href="<%=path%>/admin/auth/role_welcome.jsp" target="_self" <%if(subUrl.indexOf("role_welcome.jsp") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/auth/role_getAll.jsp" target="_self" <%if(subUrl.indexOf("role_getAll") > 0) {%>class="on" <%} %>>查询角色</a></li>
	<li><a href="<%=path%>/admin/auth/role_saveOrUpdate.jsp" target="_self"  <%if(subUrl.indexOf("role_saveOrUpdate") > 0) {%>class="on" <%} %>>新增角色</a></li>
	<li><a href="<%=path%>/admin/auth/authority_saveOrUpdate.jsp" target="_self"  <%if(subUrl.indexOf("authority_saveOrUpdate") > 0) {%>class="on" <%} %>>新增权限</a></li>
	<li><a href="<%=path%>/admin/auth/authority_getAll.jsp" target="_self"  <%if(subUrl.indexOf("authority_getAll") > 0) {%>class="on" <%} %>>查询权限</a></li>
	<li><a href="<%=path%>/admin/auth/administrator_saveOrUpdate.jsp" target="_self"  <%if(subUrl.indexOf("administrator_saveOrUpdate") > 0) {%>class="on" <%} %>>新增管理员</a></li>
	<li><a href="<%=path%>/admin/auth/administrator_getAll.jsp" target="_self"  <%if(subUrl.indexOf("administrator_getAll") > 0) {%>class="on" <%} %>>管理员列表</a></li>
	</ul>
</div>
</div>