<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>  


<div class="w_home_content">
  <div class="w_home_content_box clearfix">
  
  
<div class="content clearfix  pr">
	<%@ include file="inc/right.jsp"%>

	
<div class="r w-770   hei-400">

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">我发表的帖子</h3>
	</div>
	<div class="bordb dygs mb10 my_art">
		<s:action var="topForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.creatorNick" value="#parameters.hostUserName[0]"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="6"></s:param>
			      <s:param name="query.state" value="1"></s:param>
		        </s:action>
		        <s:iterator value="#topForums.forums.list" var="forum">
			<div class="my_art_dy">
				<h4><a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" target="_blank" class="red_f63"><s:property value="#forum.title"/></a></h4>
		 		<p><s:property value="#forum.content" escape="false"/></p>
				<p class="c_666">
				阅读(<s:property value="#forum.clickCount"/>)
		 		<a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" target="_blank">查看全文</a>
		 		
		 		<%if(isHost){ %>
		 		<a href="<%=path%>/page.action?templateName=createForum.ftl&boardId=<s:property value="#forum.board.id"/>&forumId=<s:property value="#forum.id"/>" target="_blank">编辑帖子</a>
		 		<%} %>
		 		</p>
			</div>
		</s:iterator>	
		

		<s:if test="#topForums.forums.pageString != null && #topForums.forums.pageString != ''">
		<div class="pagenum">
			<s:property value="#topForums.forums.pageString" escape="false"/>
		</div>
		</s:if>
	</div>
</div>	
</div>
</div>
</div>
</div>
<!--content E -->

<%@ include file="../inc/templateFoot.jsp"%>