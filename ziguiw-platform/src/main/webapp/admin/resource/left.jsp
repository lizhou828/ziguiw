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
	<li>
		<a href="${pageContext.request.contextPath}/admin/resource/resourcesAudit_list.action" target="_self" >资源审核</a>
	</li>
	</ul>
</div>
</div>