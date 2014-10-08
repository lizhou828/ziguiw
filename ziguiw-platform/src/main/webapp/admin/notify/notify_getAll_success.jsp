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

<p class="map">提醒：内容提醒</p>
<form action="<%=path%>/admin/notify/notify_getAll_success.jsp" method="post">
<ul class="col-ul ul_li_sp m-t10">

<li><span>时间</span>
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
			详情
		</td>
		
		<td>
			删除
		</td>

	</thead>
	<s:action var="getNotifys" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
     	<s:param name="hql">from ContentNotify where flag=1 
	<%if(StringUtils.isNotEmpty(request.getParameter("startTime"))){ %> and createTime >  to_date('<s:property value="#parameters.startTime" />','yyyy-MM-dd' ) <%} %>
		<%if(StringUtils.isNotEmpty(request.getParameter("endTime"))){ %> and createTime <  to_date('<s:property value="#parameters.endTime" />','yyyy-MM-dd' ) <%}%>
		
order by createTime desc</s:param>
     	<s:param name="queryString">flag=1</s:param>
	</s:action>
	<s:iterator value="#getNotifys.obj.list" var="notify">
	<tr>

		<td>
			<s:property value="#notify.content"/>
		</td>
		
		<td>
			<a href="<%=path%>/admin/notify_deleteById.action?resourceId=<s:property value="#notify.id"/>">删除</a>
		</td>
	
		
	</tr>
	</s:iterator>
</table>

<s:if test="#getNotifys.obj.pageString != null && #getNotifys.obj.pageString != ''">
<div class="fenye a-r">
<s:property value="#getNotifys.obj.pageString" escape="false"/>
</div>
</s:if>

</div>
<script src="<%=path%>/js/date.js" charset="UTF-8"></script>
<script src="<%=path%>/js/admin.js" charset="UTF-8"></script>
<%@ include file="../inc/foot.jsp"%>