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
	<li><a href="<%=path%>/admin/community/community_welcome.jsp" target="_self" <%if(subUrl.indexOf("community_welcome") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/community/board_saveOrUpdate.jsp" target="_self" <%if(subUrl.indexOf("board_saveOrUpdate") > 0) {%>class="on" <%} %>>新增版块</a></li>
	<li><a href="<%=path%>/admin/community/board_listByCondition_success.jsp?opType=0" target="_self"  <%if(subUrl.indexOf("board_listByCondition") > 0 && (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>查询版块</a></li>
	<li><a href="<%=path%>/admin/community/forum_listByCondition_success.jsp?opType=0" target="_self"  <%if(subUrl.indexOf("forum_listByCondition") > 0 && (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>查询主贴</a></li>
	<li><a href="<%=path%>/admin/community/forum_listByCondition.action?opType=1&query.state=1&query.isnew=1" target="_self"  <%if(subUrl.indexOf("forum_listByCondition") > 0 && (opType == null || "".equals(opType) || "1".equals(opType))) {%>class="on" <%} %>>推荐主贴</a></li>
	<li><a href="<%=path%>/admin/community/forum_listByCondition.action?opType=2&query.state=-1" target="_self"  <%if(subUrl.indexOf("forum_listByCondition") > 0 && (opType == null || "".equals(opType) || "2".equals(opType))) {%>class="on" <%} %>>恢复主贴</a></li>
	<li><a href="<%=path%>/admin/community/forum_listByCondition_success.jsp?opType=10" target="_self"  <%if(subUrl.indexOf("forum_listByCondition") > 0 && (opType == null || "".equals(opType) || "10".equals(opType))) {%>class="on" <%} %>>查询回贴</a></li>
	<li><a href="<%=path%>/admin/community/forum_listByCondition.action?opType=12&query.state=-1" target="_self"  <%if(subUrl.indexOf("forum_listByCondition") > 0 && (opType == null || "".equals(opType) || "12".equals(opType))) {%>class="on" <%} %>>恢复回贴</a></li>
	<li><a href="<%=path%>/admin/community/love_saveOrUpdate.jsp" target="_self" <%if(subUrl.indexOf("love_saveOrUpdate") > 0) {%>class="on" <%} %>>新增爱心榜信息</a></li>
	<li><a href="<%=path%>/admin/community/love_listByCondition_success.jsp?opType=0" target="_self"  <%if(subUrl.indexOf("love_listByCondition") > 0 && (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>查询爱心榜信息</a></li>
	</ul>
</div>
</div>