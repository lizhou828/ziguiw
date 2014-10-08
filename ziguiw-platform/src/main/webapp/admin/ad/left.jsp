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
	<li><a href="<%=path%>/admin/ad/ad_welcome.jsp" target="_self" <%if(subUrl.indexOf("ad_welcome.jsp") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/ad/ad_saveOrUpdate.jsp" target="_self" <%if(subUrl.indexOf("ad_saveOrUpdate") > 0) {%>class="on" <%} %>>新增广告位</a></li>
	<li><a href="<%=path%>/admin/ad/ad_getAll.action" target="_self"  <%if(subUrl.indexOf("ad_getAll") > 0) {%>class="on" <%} %>>查询广告位</a></li>
	<li><a href="<%=path%>/admin/ad/adPlan_saveOrUpdate.jsp" target="_self"  <%if(subUrl.indexOf("adPlan_saveOrUpdate") > 0) {%>class="on" <%} %>>新增广告计划</a></li>
	<li><a href="<%=path%>/admin/ad/adPlan_getPlansByType.action?type=new" target="_self"  <%if(subUrl.indexOf("adPlan_getPlansByType") > 0 && type.equals("new")) {%>class="on" <%} %>>未执行广告计划</a></li>
	<li><a href="<%=path%>/admin/ad/adPlan_getPlansByType.action?type=old" target="_self"  <%if(subUrl.indexOf("adPlan_getPlansByType") > 0 && type.equals("old")) {%>class="on" <%} %>>已执行广告计划</a></li>
	<li><a href="<%=path%>/admin/ad/adPlan_getPlansByType.action?type=cur" target="_self"  <%if(subUrl.indexOf("adPlan_getPlansByType") > 0 && type.equals("cur")) {%>class="on" <%} %>>执行中广告计划</a></li>
	</ul>
</div>
</div>