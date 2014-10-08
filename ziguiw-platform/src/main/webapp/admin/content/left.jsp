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
	<li><a href="<%=path%>/admin/content/content_welcome.jsp" target="_self" <%if(subUrl.indexOf("content_welcome.jsp") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/content/news_comment.jsp" target="_self" <%if(subUrl.indexOf("news_comment") > 0) {%>class="on" <%} %>>新闻评论管理</a></li>
	<li><a href="<%=path%>/admin/content/photo_comment.jsp" target="_self"  <%if(subUrl.indexOf("photo_comment") > 0) {%>class="on" <%} %>>相册评论管理</a></li>
	<li><a href="<%=path%>/admin/content/message_list.jsp" target="_self"  <%if(subUrl.indexOf("message_list") > 0) {%>class="on" <%} %>>留言管理</a></li>
	</ul>
</div>
</div>