<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.text.DateFormat,java.util.Date,java.text.SimpleDateFormat,com.opensymphony.xwork2.util.ValueStack,org.apache.commons.lang.StringUtils" %>
<%
DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">内容管理：相册评论</p>
<form action="<%=path%>/admin/content/photo_comment.jsp" method="post">
<ul class="col-ul ul_li_sp m-t10">

<li><span>评论内容</span><input type="text" class="inp_txt" name="text" value="<s:property value="#parameters.text" />"></li>		
<li><span>用户昵称</span><input type="text" class="inp_txt" name="nickName" value="<s:property value="#parameters.nickName" />"></li>

<li><span>评论时间</span>
<input type="text" class="inp_txt" name="startTime" value="<s:property value="#parameters.startTime" />" onclick="SelectDate(this)" readonly="readonly" /> - 
<input type="text" class="inp_txt" name="endTime" value="<s:property value="#parameters.endTime" />" onclick="SelectDate(this)" readonly="readonly" />
</li>

<input type="submit" value="查询"  class="inp_btn"/>
</ul>
</form>
<table class="table">
	<colgroup>
	</colgroup>
	<thead>
 
		<td>
			评论ID
		</td>

		<td>
			评论时间
		</td>
		<td>
			评论人
		</td>
		<td>
			评论内容
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:action var="action1" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
			      <s:param name="hql">from UserComment where type = 1 and kind = 1 
<%if(StringUtils.isNotEmpty(request.getParameter("text"))){ %> and text like '%<s:property value="#parameters.text" />%' <%} %>
		<%if(StringUtils.isNotEmpty(request.getParameter("nickName"))){ %> and formUser.nickName = '<s:property value="#parameters.nickName" />' <%} %>
		<%if(StringUtils.isNotEmpty(request.getParameter("startTime"))){ %> and createTime >  to_date('<s:property value="#parameters.startTime" />','yyyy-MM-dd' ) <%} %>
		<%if(StringUtils.isNotEmpty(request.getParameter("endTime"))){ %> and createTime <  to_date('<s:property value="#parameters.endTime" />','yyyy-MM-dd' ) <%} %>
order by createTime desc</s:param>
<s:param name="queryString"><%if(StringUtils.isNotEmpty(request.getParameter("nickName"))){ %>user.nickName=<s:property value="#parameters.nickName" /><%} %></s:param>
	 </s:action>
	<s:iterator value="#action1.obj.list" var="comment">
	<tr>

		<td>
			<s:property value="#comment.id"/>
		</td>
	
		
		<td>
			<s:property value="#comment.formatedCreateTime"/>
		</td>
		<td>
			<s:property value="#comment.formUser.nickName"/>
		</td>
		<td>
			<s:property value="#comment.text"/>
		</td>
		<td>
			<a href="<%=path%>/admin/content/delPhotoComment.action?message.id=<s:property value="#comment.id"/>">删除</a>
		</td>
		
	</tr>
	</s:iterator>
</table>

<s:if test="#action1.obj.pageString != null && #action1.obj.pageString != ''">
<div class="fenye a-r">
<s:property value="#action1.obj.pageString" escape="false"/>
</div>
</s:if>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>
<script src="<%=path%>/js/admin.js" charset="UTF-8"></script>
<%@ include file="../inc/foot.jsp"%>