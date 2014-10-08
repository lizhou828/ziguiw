<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>  
<div class="content clearfix">
	

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">我的日志<a style="position:absolute;padding-left:35px" class="r" href="<%=path%>/user/publish_diary.jsp">写日志</a></h3>
	</div>
	<div class="bordb dygs mb10 my_art">
		<s:action var="diaryList" name="diary_getByNickName" namespace="/user" executeResult="false" ignoreContextParams="false">
			<s:param name="status" value="1"></s:param>
			<s:param name="pageSize" value="10"></s:param>
		</s:action>
				
		<s:iterator value="#diaryList.pagedDiaries.list" var="diaries">
			<div class="my_art_dy">
				<h4><a href="<%=path%>/user/getDiaryById?diary.id=<s:property value="#diaries.id"/>&hostUserName=<s:property value="#diaries.user.nickName"/>" target="_blank" class="red_f63"><s:property value="#diaries.title"/></a></h4>
		 		<p><s:property value="#diaries.description" escape="false"/>
		 		</p>
				<p class="c_666">
				阅读(<s:property value="#diaries.clickCount"/>)
		 		<a href="<%=path%>/user/getDiaryById?diary.id=<s:property value="#diaries.id"/>&hostUserName=<s:property value="#diaries.user.nickName"/>" target="_blank">查看全文</a>
		 		
		 		
		 		<a href="<%=path%>/user/getDiaryByIdForEdit.action?diary.id=<s:property value="#diaries.id"/>">编辑文章</a>
		 		<a href="<%=path%>/user/delDiaryById.action?diary.id=<s:property value="#diaries.id"/>">删除</a>
		 		
		 		</p>
			</div>
		</s:iterator>	
		

		<s:if test="#diaryList.pagedDiaries.pageString != null && #diaryList.pagedDiaries.pageString != ''">
		<div class="pagenum">
			<s:property value="#diaryList.pagedDiaries.pageString" escape="false"/>
		</div>
		</s:if>
	</div>
</div>	


</div>
<!--content E -->

<%@ include file="../inc/templateFoot.jsp"%>