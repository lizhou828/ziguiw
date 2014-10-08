<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath(); 
String currentUrl = request.getRequestURL().toString();
String queryString = request.getQueryString() == null ? "1=1" : request.getQueryString();
queryString = queryString.replace("currentPage=", "");
queryString = queryString.replace("orderField=", "");
queryString = queryString.replace("orderType=", "");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/base-min.css" rel="stylesheet"/>
<link href="<%=path%>/css/editor-pkg-min-datauri.css" rel="stylesheet"/>
<link href="<%=path%>/css/admin.css" type="text/css" rel="stylesheet" media="all"/>
<title>子贵网CMS</title>
</head>
<body>
<%@ include file="../inc/left.jsp"%>
<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

<p class="map">知识：新增辩论</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/knowledge/debate_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:720px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>辩论标题</span>
			<input name="debate.title" type="text" class="inp_txt" value="${debate.title}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>辩论标题</td>
	</tr>
	
	<tr>
		<td><span>辩论描述</span>
			<textarea name="debate.description" type="text" class="textarea" value="${debate.description}" cols="10" rows="5"></textarea>
		</td>
		<td class="v-b" ><span class="red_star">*</span>辩论描述</td>
	</tr>
	
	<tr>
		<td><span>正方观点</span>
			<textarea name="debate.positiveOpinion" type="text" class="textarea" value="${debate.positiveOpinion}" cols="10" rows="5"></textarea>
		</td>
		<td class="v-b" ><span class="red_star">*</span>正方观点</td>
	</tr>
	
	<tr>
		<td><span>反方观点</span>
			<textarea name="debate.negativeOpinion" type="text" class="textarea" value="${debate.negativeOpinion}" cols="10" rows="5"></textarea>
		</td>
		<td class="v-b" ><span class="red_star">*</span>反方观点</td>
	</tr>
	
	<tr>
		<td><span>开放时间</span>
			<input name="debate.openDays" type="text" class="inp_txt" value="${debate.openDays}" />
		</td>
		<td class="v-b" >开放时间</td>
	</tr>
</table>
<input type="hidden" name="debate.state" value="1"/>
<input type="hidden" name='debateId' value="${debateId}"/>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提 交"  class="inp_btn" onclick="getEditorContent()"/>
</p>
</form>
</div>
<script src="http://a.tbcdn.cn/s/kissy/1.2.0/??kissy-min.js,uibase-min.js,component-min.js,dd-min.js,overlay-min.js,editor-min.js"></script>
<script src="<%=path%>/js/editor-plugin-pkg-min.js"></script>
<script src="<%=path%>/js/connection-min.js"></script> 
<script src="<%=path%>/js/json-min.js"></script>
<%@ include file="/admin/inc/foot.jsp"%>