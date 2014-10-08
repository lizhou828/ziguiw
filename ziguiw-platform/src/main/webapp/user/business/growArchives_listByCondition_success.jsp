<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>


<div class="content clearfix pr">


	<!--左侧菜单-->
	<%@ include file="../inc/info_left.jsp"%>

	<div class="w-760 r userCenterContent">
		<h2 class="title">信息中心</h2>
		<div class="title-bottom">.</div>
		<!--学生和家长可以增加编辑成长档案-->
		<%
			if (hostUser.getType() == 3 || hostUser.getType() == 4) {
		%>
		<div class="formBtnBar formBtnBar-bottom" style="margin-right: 0px">
			<a href="<%=path%>/info/addGrowArchives.action"><div
				class="cx">修改</div></a>
		</div>
		<%
			}
		%>
		<!--列表样式2-->
		<table class="listStyle2" cellpadding="0" cellspacing="0" border="0">
			<thead>
				<tr>
					<th width="100px">姓名</th>
					<th width="200px">爱好</th>
					<th width="200px">特长</th>
					<th width="200px">名言</th>
					<th width="80px">管理</th>
				</tr>
			</thead>
			<tbody>
				<s:action var="growArchivess" name="growArchives_listByCondition"
					namespace="/info" executeResult="false" ignoreContextParams="false">
					<s:param name="countPerPage" value="20"></s:param>
					<s:param name="query.state" value="1"></s:param>
				</s:action>
				<s:iterator value="#growArchivess.growArchivess.list"
					var="growArchives">
					<tr>
						<td><a
							href="<%=path%>/info/growArchives_getById_view.action?growArchivesId=<s:property value="#growArchives.id"/>"><s:property
									value="#growArchives.student.user.nickName" /></a></td>
						<td><s:property value="#growArchives.hobby" /></td>
						<td><s:property value="#growArchives.specialty" /></td>
						<td><s:property value="#growArchives.famous" /></td>
						<td><a
							href="<%=path%>/info/growArchives_getById.action?growArchivesId=<s:property value="#growArchives.id"/>">修改</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<s:if
			test="#growArchivess.growArchivess.pageString != null && #growArchivess.growArchivess.pageString != ''">
			<div class="pagenum">
				<s:property value="#growArchivess.growArchivess.pageString"
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