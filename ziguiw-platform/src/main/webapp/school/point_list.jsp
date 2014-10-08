<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>


<div class="content clearfix pr">
	<%@ include file="inc/school_left.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760  r" style="height:550px">
       	<h2 class="col-h3">学校积分列表</h2>

	
		<ul class="list-1">
        
        <s:action var="getPointHistory" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
       	<s:param name="hql">from PointsHistory where state >= 0 and user.id = <%=hostUser.getId()%> order by changeTime desc</s:param>
    </s:action>
	
	<s:iterator value="#getPointHistory.obj.list" var="pointHistory">
	
		 <li>
			<span><s:property value="#pointHistory.user.nickName"/> 于 <s:property value="#pointHistory.formatedChangeTime"/> 因为 <samp><s:property value="#pointHistory.typeStr"/> </samp>获得<samp><s:property value="#pointHistory.pointsChange"/></samp> 分积分</span>
		</li>	
	
	</s:iterator>
       
			
		</ul>
    </div>
  
 
<!--content E -->


<%@ include file="../inc/templateFoot.jsp"%>