<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>


<div class="content clearfix pr">


	<!--左侧菜单-->
	<%@ include file="../inc/info_left.jsp"%>

	<div class="w-760 r userCenterContent"
		style="margin-left: -110px; width: 1800px; zoom: 1px;">
		<h2 class="title">信息中心</h2>
		<div class="title-bottom">.</div>
		<!--只有老师可以发起消息内容-->
		<%
			if (hostUser.getType() == 2) {
		%>
		<div class="formBtnBar formBtnBar-bottom" style="margin-right: 0px">
			<a
				href="<%=path%>/user/business/commonMessage_saveOrUpdate.jsp?kind=<%=request.getParameter("kind")%>"><input
				type="button" class="cx" onclick="window.location.href='<%=path%>/user/business/commonMessage_saveOrUpdate.jsp?kind=<%=request.getParameter("kind")%>';" value="增加"></input></a>
		</div>
		<%
			}
		%>
		<!--列表样式2-->
		<table class="listStyle2" cellpadding="0" cellspacing="0" border="0">
			<thead>
				<tr>
					<th width="120px">时间</th>
					<th width="100px">主题</th>
					<th width="450px">内容</th>
					<%
						if (hostUser.getType() == 2) {
					%>
					<th width="80px">管理</th>
					<%
						}
					%>
				</tr>
			</thead>
			<tbody>

				<s:action var="commonMessages" name="commonMessage_listByCondition"
					namespace="/info" executeResult="false" ignoreContextParams="false">
					<s:param name="countPerPage" value="20"></s:param>
					<s:param name="query.state" value="1"></s:param>
					<s:param name="query.kind">
						<s:property value="#parameters.kind" />
					</s:param>
				</s:action>
				<s:iterator value="#commonMessages.commonMessages.list"
					var="commonMessage">
					<tr>
						<td><s:property value="#commonMessage.formatedCreateDate" /></td>
						<td><s:property value="#commonMessage.title" /></td>
						<td><s:property value="#commonMessage.text" /></td>
						<%
							if (hostUser.getType() == 2) {
						%>
						<td><a
							href="<%=path%>/info/commonMessage_fakeDelete.action?opIds=<s:property value="#commonMessage.id"/>&kind=<s:property value="#parameters.kind"/>">删除</a>
						</td>
						<%
							}
						%>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<s:if
			test="#commonMessages.commonMessages.pageString != null && #commonMessages.commonMessages.pageString != ''">
			<div class="pagenum">
				<s:property value="#commonMessages.commonMessages.pageString"
					escape="false" />
			</div>
		</s:if>
		<p>&nbsp;</p>
		<p>
	</div>

</div>
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>