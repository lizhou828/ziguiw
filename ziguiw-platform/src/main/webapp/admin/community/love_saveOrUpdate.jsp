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

<p class="map">知识：新增爱心信息</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/community/love_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:720px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>捐赠人ID</span>
			<input name="love.donateUser.id" type="text" class="inp_txt" value="${love.donateUser.id}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>捐赠人ID，并且要求是站内实际用户</td>
	</tr>
	
	<tr>
		<td><span>被捐赠人姓名</span>
			<input name="love.toNick" type="text" class="inp_txt" value="${love.toNick}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>被捐赠人姓名，不一定为站内用户</td>
	</tr>
	
	<tr>
		<td><span>捐赠信息</span>
			<textarea name="love.info" type="text" class="textarea" value="${love.info}" cols="10" rows="5"></textarea>
		</td>
		<td class="v-b" ><span class="red_star">*</span>捐赠多少钱或物等</td>
	</tr>

</table>
<input type="hidden" name="love.state" value="1"/>
<input type="hidden" name='loveId' value="${loveId}"/>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提 交"  class="inp_btn" onclick="getEditorContent()"/>
</p>
</form>
</div>
<%@ include file="/admin/inc/foot.jsp"%>