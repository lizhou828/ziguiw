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
	<li><a href="<%=path%>/admin/news/news_welcome.jsp" target="_self" <%if(subUrl.indexOf("news_welcome") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/template/template_getAll.action" target="_self" <%if(subUrl.indexOf("template_getAll") > 0) {%>class="on" <%} %>>查询模版</a></li>
	<li><a href="<%=path%>/admin/template/template_saveOrUpdate.jsp" target="_self"  <%if(subUrl.indexOf("template_saveOrUpdate") > 0) {%>class="on" <%} %>>新增模版</a></li>
	<li><a href="<%=path%>/admin/template/dataFunction_saveOrUpdate.jsp" target="_self"  <%if(subUrl.indexOf("dataFunction_saveOrUpdate") > 0) {%>class="on" <%} %>>新增数据函数</a></li>
	<li><a href="<%=path%>/admin/template/dataFunction_getAll.action" target="_self"  <%if(subUrl.indexOf("dataFunction_getAll") > 0) {%>class="on" <%} %>>数据函数管理</a></li>
	</ul>
</div>
</div>