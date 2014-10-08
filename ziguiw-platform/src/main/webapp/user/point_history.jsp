<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>  

<div class="w_home_content">
  
    
<div class="content clearfix  leave_message">
<div class=" mypos1 fsong mb10"> <a href="#">我的小家</a> &gt; 积分明细</div>


	<div class="bordb my_home" style="height:30px;min-height:30px">
		<div class="h3titb">
			<h3 class="fyahei">我的积分明细</h3>
		</div>
	</div>
	
	<s:action var="getPointHistory" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
       	<s:param name="hql">from PointsHistory where state >= 0 and user.id = <%=hostUser.getId()%> order by changeTime desc</s:param>
    </s:action>
	
	<s:iterator value="#getPointHistory.obj.list" var="pointHistory">
	<div class="clearfix message_list pr" style="height:30px;min-height:30px">
		<div class="l mes_list_l">
			<s:if test="#pointHistory.flag == 1">
				<s:property value="#pointHistory.user.nickName"/>于<s:property value="#pointHistory.formatedChangeTime"/>因为<s:property value="#pointHistory.typeStr"/>获得<s:property value="#pointHistory.pointsChange"/>分积分
			</s:if>
			<s:elseif test="#pointHistory.flag == -1">
			
				<s:property value="#pointHistory.user.nickName"/>于<s:property value="#pointHistory.formatedChangeTime"/>因为<s:property value="#pointHistory.typeStr"/>减少<s:property value="#pointHistory.pointsChange"/>分积分			
			</s:elseif>
		</div>		
	</div>
	</s:iterator>
	
	<s:if test="#getPointHistory.obj.pageString != null && #getPointHistory.obj.pageString != ''">
		<div class="pagenum">
			<s:property value="#getPointHistory.obj.pageString" escape="false"/>
		</div>
    </s:if>
	
	
</div>
</div>


<%@ include file="../inc/templateFoot.jsp"%>

<script type="text/javascript" src="<%=path %>/js/user/leave_message.js"></script>
