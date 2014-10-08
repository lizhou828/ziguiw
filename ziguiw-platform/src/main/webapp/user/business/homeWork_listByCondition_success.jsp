<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>


<div class="content clearfix pr">

	<!--左侧菜单-->
	<%@ include file="../inc/info_left.jsp"%>

	<div class="w-760 r userCenterContent"
		style="">
		<h2 class="title">信息中心</h2>
		<div class="title-bottom">.</div>

		<!-- 作业查询，需要吗，不需要
	       	<form action="<%=path%>/user/business/homeWork_listByCondition_success.jsp">
	       	<div class="jz-i" style="background: none repeat scroll 0 0 #EEF6FB;;border: 1px solid #FFFFFF;height: auto;padding: 8px;">
	            <table width="700" border="0" cellspacing="0" cellpadding="0">
	            <tr>
	              <td>日期：
	              <input name="query.startTime"  readonly="readonly" type="text" id="startDate" class="Wdate" style="float: none;width: 100px;" onclick="WdatePicker()" />
	              到
	              <input name="query.endTime"  readonly="readonly" type="text" id="endDate" style="float: none;width: 100px;" class="Wdate" onclick="WdatePicker()"/>
	             </td>
	            
	              <td>班级：
	                 <select name="query.classId" id="classId">
	                 	<s:action var="getClasz" name="baseData_getClaszByTeacher" namespace="/info" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="20"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="user.foreignKey"><%=hostUser.getForeignKey()%></s:param>
				        </s:action>
	           			<option value="-1" >--必选--</option>
	           			<s:iterator value="#getClasz.pageObj.list" var="clasz">
							<option value="<s:property value="#clasz.clasz.Bj_id"/>"><s:property value="#clasz.clasz.Bj_mcheng"/></option>
						</s:iterator>
	           			
	                </select>
	              </td>
	              
	              <td><input name="smsQueryBtn" id="smsQueryBtn" type="submit" value="查询" class="cx"/></td>
	            </tr>
	          </table>
	      </div>
	      </form>
       -->

		<%
			if (hostUser.getType() == 2) {
		%>
		<div class="formBtnBar formBtnBar-bottom" style="margin-right: 0px">
			<a href="<%=path%>/user/business/homeWork_saveOrUpdate.jsp"><input
				type="button" onclick="window.location.href='<%=path%>/user/business/homeWork_saveOrUpdate.jsp';" class="cx" value="增加"></input></a>
		</div>
		<%
			}
		%>
		<!--列表样式2-->
		<table class="listStyle2" cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead>
				<tr>
					<th width="100px">时间</th>
					<th width="100px">班级</th>
					<th width="500px">内容</th>

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

				<s:action var="homeWorks" name="homeWork_listByCondition"
					namespace="/info" executeResult="false" ignoreContextParams="false">
					<s:param name="countPerPage" value="10"></s:param>
					<s:param name="query.state" value="1"></s:param>
				</s:action>
				<s:iterator value="#homeWorks.homeWorks.list" var="homeWork">
					<tr>
						<td><s:property value="#homeWork.formatedCreateDate" /></td>
						<td><s:property value="#homeWork.clasz.Bj_mcheng" /></td>
						<td><s:property value="#homeWork.content" /></td>
						<%
							if (hostUser.getType() == 2) {
						%>
						<td><a
							href="<%=path%>/info/homeWork_getById.action?homeWorkId=<s:property value="#homeWork.id"/>">修改</a>
							| <a
							href="<%=path%>/info/homeWork_fakeDelete.action?opIds=<s:property value="#homeWork.id"/>">删除</a>
						</td>
						<%
							}
						%>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<s:if
			test="#homeWorks.homeWorks.pageString != null && #homeWorks.homeWorks.pageString != ''">
			<div class="pagenum">
				<s:property value="#homeWorks.homeWorks.pageString" escape="false" />
			</div>
		</s:if>
		<p>&nbsp;</p>
		<p>
	</div>

</div>
<!--content E -->
<!--content E -->


<%@ include file="../../inc/templateFoot.jsp"%>