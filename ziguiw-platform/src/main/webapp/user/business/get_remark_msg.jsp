<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>


<div class="content clearfix pr">


	<!--左侧菜单-->
	<%@ include file="../inc/info_left.jsp"%>
	<!--左侧菜单-->

	<div class="w-760 r userCenterContent">
		<h2 class="title">信息中心</h2>
		<div class="title-bottom">.</div>
		<!--列表样式1-->
		<div class=" w-520 my_mid" style="margin-left:10px;width:700px">	
	
		<form action="<%=path%>/info/remark_saveOrUpdate.action" method="post">		
			<div class="">
			<textarea name="commonMessage.text" class="area2"  id='content1'></textarea>
			<input type="hidden" name="commonMessage.parentMsg.id" value="<s:property value="commonMessage.id"/>" /> 
			<input type="hidden" name="commonMessage.kind" value="7" />
			<br/>
			<input name="submit" type="submit" value="回复" class="btn2 mt5" />		
			</div>
		</form>
		
		</div>
	
		<div>

		<s:iterator value="commonMessage.aggregationMsgs" var="message">
			<div class="mes_list_r" style="margin-left: 0px">
				<label class="gray">
				 <s:property value="#message.formUser.realName" /> <s:property
						value="#message.formatedCreateDate" /> 评语</label>
				<p class="block">
					<s:property value="#message.text" />
				</p>
				
			</div>
		</s:iterator>

		<p>&nbsp;</p>
		<p>
	</div>

	</div>
</div>
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>