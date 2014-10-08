<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,org.apache.commons.lang.StringUtils,org.apache.commons.lang.exception.ExceptionUtils,org.apache.commons.codec.digest.DigestUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>
<%@ page import="com.zigui.utils.*"%>
<%@ page import="com.zigui.utils.Page" %>
<%@ include file="../comm/common.jsp"%>
<%
String newsType = request.getParameter("type");
%> 
<div class="content clearfix  pr">
	

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">
		<%if(StringUtils.equals(newsType, "5")){ %>
		校园新闻
		<%} %>
		<%if(StringUtils.equals(newsType, "6")){ %>
		学校公告
		<%} %>
		<%if(StringUtils.equals(newsType, "7")){ %>
		招生信息
		<%} %>
		</h3>
		<%if(isHost){ %><a href="<%=path%>/school/publish_diary.jsp?type=<%=request.getParameter("type")%>" class="r" style="position:absolute;right:15px;top:5px"><input type="button" class="btn2 r mt5" value="写新闻"></a>
	    <%} %>
	</div>
	<div class="bordb dygs mb10 my_art">
        <s:action var="diaryList" name="schoolDiary_getByNickName" namespace="/user" executeResult="false" ignoreContextParams="false">
            <s:param name="status" value="1"></s:param>
            <s:param name="pageSize" value="5"></s:param>
        </s:action>

        <s:iterator value="#diaryList.pagedDiaries.list" var="diaries">
			<div class="my_art_dy">
				<h4><a href="<%=path%>/user/getShoolDiaryById.action?diary.id=<s:property value="#diaries.id"/>&hostUserName=<s:property value="#diaries.user.nickName"/>" target="_blank" class="red_f63"><s:property value="#diaries.title"/></a></h4>
		 		<p><s:property value="#diaries.description" escape="false"/></p>
				<p class="c_666">
				阅读(<s:property value="#diaries.clickCount"/>)
		 		<a href="<%=path%>/user/getShoolDiaryById.action?diary.id=<s:property value="#diaries.id"/>&hostUserName=<s:property value="#diaries.user.nickName"/>" target="_blank">查看全文</a>
		 		创建日期:<s:property value="#diaries.createTime"/>
		 		<%if(isHost){ %>
		 		<a href="<%=path%>/user/getSchoolDiaryByIdForEdit.action?diary.id=<s:property value="#diaries.id"/>&type=<%=newsType%>">编辑文章</a>
		 		<a href="<%=path%>/user/delSchoolDiaryById.action?diary.id=<s:property value="#diaries.id"/>&type=<%=newsType%>">删除</a>
		 		<%} %>
		 		</p>
			</div>
		</s:iterator>	
		<s:if test="#diaryList.pageString != null && #diaryList.pageString != ''">
		<div class="pagenum">
			<s:property value="#diaryList.pageString" escape="false"/>
		</div>
		</s:if>
	</div>

</div>

<!--content E -->

<%@ include file="../inc/templateFoot.jsp"%>