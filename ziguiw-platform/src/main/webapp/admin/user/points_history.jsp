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

<p class="map">全局：站点信息</p>


<form action="<%=path%>/admin/user/points_history.jsp" method="post">
<ul class="col-ul ul_li_sp m-t10">

		
<li><span>用户昵称</span><input type="text" class="inp_txt" name="nickName" value="<s:property value="#parameters.nickName" />"></li>

<li><span>积分获取时间</span>
<input type="text" class="inp_txt" name="startTime" value="<s:property value="#parameters.startTime" />" onclick="SelectDate(this)" readonly="readonly" /> - 
<input type="text" class="inp_txt" name="endTime" value="<s:property value="#parameters.endTime" />" onclick="SelectDate(this)" readonly="readonly" />
</li>

<input type="submit" value="查询"  class="inp_btn"/>
</ul>
</form>
<%try{ %>
<s:action var="getPointHistory" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
     	<s:param name="hql">from PointsHistory where state >= 0 
     	<%if(StringUtils.isNotEmpty(request.getParameter("nickName"))){ %> and user.nickName = '<s:property value="#parameters.nickName" />' <%} %>
		<%if(StringUtils.isNotEmpty(request.getParameter("startTime"))){ %> and changeTime >  to_date('<s:property value="#parameters.startTime" />','yyyy-MM-dd' ) <%} %>
		<%if(StringUtils.isNotEmpty(request.getParameter("endTime"))){ %> and changeTime <  to_date('<s:property value="#parameters.endTime" />','yyyy-MM-dd' ) <%} %>
     	order by changeTime desc</s:param>
     	<s:param name="queryString"><%if(StringUtils.isNotEmpty(request.getParameter("nickName"))){ %>user.nickName=<s:property value="#parameters.nickName" /><%} %></s:param>
</s:action>
<table class="table">
	<thead>
		<td>
			用户昵称
		</td>
		<td>
			类型
		</td>
		<td>
			积分
		</td>
		<td>
			时间
		</td>
		
	</thead>

	<s:iterator value="#getPointHistory.obj.list" var="pointHistory">
	<tr>
		<td>
			<s:property value="#pointHistory.user.nickName"/>
		</td>
		<td>
			<s:property value="#pointHistory.typeStr"/>
		</td>
		<td>
			<s:property value="#pointHistory.pointsChange"/>
		</td>
		<td>
			<s:property value="#pointHistory.formatedChangeTime"/>
		</td>
		
		
	</tr>
	</s:iterator>
</table>

<s:if test="#getPointHistory.obj.pageString != null && #getPointHistory.obj.pageString != ''">
<div class="fenye a-r">
<s:property value="#getPointHistory.obj.pageString" escape="false"/>
</div>
</s:if>


<%}catch(Throwable e) {
	e.printStackTrace();
	System.out.println(e.getMessage());
} %>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>


<script type="text/javascript" src="http://a.tbcdn.cn/s/kissy/1.2.0/kissy.js"></script>
<%@ include file="../inc/foot.jsp"%>