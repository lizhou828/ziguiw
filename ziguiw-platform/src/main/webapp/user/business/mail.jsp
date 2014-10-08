<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>


<div class="content clearfix pr">


	<!--左侧菜单-->
	<%@ include file="../inc/info_left.jsp"%>
	<!--左侧菜单-->

	<div class="w-760 r userCenterContent"
		style="margin-left: -110px; width: 1800px; zoom: 1px;">
		<h2 class="title">信息中心</h2>
		<div class="title-bottom">.</div>
		<!--列表样式1-->
		<%
			if (hostUser.getType() == 2) {
		%>
		<div class="formBtnBar formBtnBar-bottom" style="margin-right: 0px">
			<a
				href="<%=path%>/user/business/mail_saveOrUpdate.jsp?kind=<%=request.getParameter("kind")%>"><input
				type="button" onclick="window.location.href='<%=path%>/user/business/mail_saveOrUpdate.jsp?kind=<%=request.getParameter("kind")%>';" class="cx" value="增加"></input></a>
		</div>
		<%
			}
		%>

		<s:action var="commonMessages" name="commonMessage_listByCondition"
			namespace="/info" executeResult="false" ignoreContextParams="false">
			<s:param name="countPerPage" value="10"></s:param>
			<s:param name="query.state" value="1"></s:param>
			<s:param name="query.kind">
				<s:property value="3" />
			</s:param>
		</s:action>
		<s:iterator value="#commonMessages.commonMessages.list" var="message">
			<div class="mes_list_r" style="margin-left: 0px">
				<label class="gray"><span class="r">
						<a
						href="<%=path%>/user/getAggregationMsgs.action?commonMessageId=<s:property value="#message.id"/>"
						class="m-lr4">查看所有（<s:property value="#message.aggregationMsgs.size" />）</a>
						
				</span> <s:property value="#message.leastMsg.formUser.realName" /> <s:property
						value="#message.leastMsg.formatedCreateDate" /> 留言</label>
				<p class="block">
					<s:property value="#message.leastMsg.text" />
				</p>
				
			</div>
		</s:iterator>

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
<%@ include file="../../inc/templateFoot.jsp"%>