<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>
<link href="<%=path%>/css/xiaohome.css" rel="stylesheet" type="text/css">

<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="../inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">信息中心</h2><div class="title-bottom">.</div>
        
        <div class="w_home_headerPicList">
               <s:action var="users1" name="user_getStudentDocument" namespace="/info" executeResult="false" ignoreContextParams="false">
			        </s:action>
			        <s:iterator value="#users1.users.list" var="user">
			          <a style="float:left;" href="<%=path%>/user/business/studentView.jsp?xsid=<s:property value="#user.foreignKey"/>&sid=<s:property value="#user.id"/>">
			           	<img src="<%=com.zigui.utils.ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#user.portrait"), path + "/images/head.jpg", 120)%>" width=120px height=120px/>
			               <span><s:property value="#user.realName"/></span>
			           </a>
			        </s:iterator>        
        </div>
    <s:if test="#users1.users.pageString != null && #users1.users.pageString != ''">
			<div class="pagenum">
			<s:property value="#users1.users.pageString" escape="false"/>
			</div>
    </s:if>	
        <p>&nbsp;</p>
        <p>             
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>