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

<p class="map">社区：新增/修改版区</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/community/board_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:720px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>版区名称</span>
			<input name="board.boardname" type="text" class="inp_txt" value="${board.boardname}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>版区名称</td>
	</tr>
	<tr>
		<td><span>父版区名称</span>
			<s:action var="board_listByCondition" name="board_listByCondition" namespace="/admin/community" executeResult="false" ignoreContextParams="false">
			  <s:param name="query.maxboardid" value="5"></s:param>
			  <s:param name="query.state" value="1"></s:param>
			</s:action>			
            <select name='board.parentBoard.id' id="parentid" class="inp_select">
			    <option value="<s:property value="board.id"/>">请选择父版区</option>
			    <s:iterator value="#board_listByCondition.boards.list" var="pboard">
			    <s:if test='#pboard.id == board.parentBoard.id'>
			       <option value="<s:property value="#pboard.id"/>" selected="selected"><s:property value="#pboard.boardname"/></option>
			    </s:if>
			    <s:else>
			       <option value="<s:property value="#pboard.id"/>"><s:property value="#pboard.boardname"/></option>
			    </s:else>					
				</s:iterator>
			</select>
			<!--  
			<select name='board.parentBoard.id' id="parentid" class="inp_select">
			    <option value="<s:property value="board.id"/>">请选择父版区</option>
			    <option value="1" selected="selected">爱心站</option>
			</select>-->
		</td>
	</tr>	
	<tr>
		<td><span>版区说明</span>
			<textarea name="board.explains" type="text" class="textarea" value="${board.explains}" cols="10" rows="5">${board.explains}</textarea>
		</td>
		<td class="v-b" >版区说明</td>
	</tr>
	
	<tr>
		<td><span>版区公告</span>
			<textarea name="board.bulletin" type="text" class="textarea" value="${board.bulletin}" cols="10" rows="5">${board.bulletin}</textarea>
		</td>
		<td class="v-b" >版区公告</td>
	</tr>
</table>
<input type="hidden" name="board.state" value="1"/>
<input type="hidden" name='board.id' value="${boardId}"/>

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