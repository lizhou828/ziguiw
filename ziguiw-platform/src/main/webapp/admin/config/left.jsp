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
	<li><a href="<%=path%>/admin/config/config_welcome.jsp" target="_self" <%if(subUrl.indexOf("config_welcome.jsp") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/config/configCenter_getConfig.action" target="_self" <%if(subUrl.indexOf("configCenter_getConfig") > 0) {%>class="on" <%} %>>配置查看</a></li>
	<li><a href="<%=path%>/admin/config/config_get4update.action" target="_self" <%if(subUrl.indexOf("config_saveOrUpdate") > 0) {%>class="on" <%} %>>配置修改</a></li>
	</ul>
</div>
</div>