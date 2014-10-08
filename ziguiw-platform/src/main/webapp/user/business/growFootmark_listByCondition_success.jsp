<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>


<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="../inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">信息中心</h2><div class="title-bottom">.</div>
       	
       	<%if(hostUser.getType() == 3 || hostUser.getType() == 4) {%>
       	<div class="formBtnBar formBtnBar-bottom" style="margin-right:0px"><a href="<%=path%>/user/business/growFootmark_saveOrUpdate.jsp"><div class="cx" >增加</div></a></div>
       	
       	<%}%>
		<!--列表样式1-->
		<s:action var="growFootmarks" name="growFootmark_listByCondition" namespace="/info" executeResult="false" ignoreContextParams="false">
	      <s:param name="countPerPage" value="5"></s:param>
	      <s:param name="query.state" value="1"></s:param>
        </s:action>
        <s:iterator value="#growFootmarks.growFootmarks.list" var="growFootmark">
        <ul class="listStyle3">
	           	  <li class="head"><s:property value="#growFootmark.title"/></li>
	           	  <li class="clearfix">
	           	  <a href="<%=path%>/info/growFootmark_getById.action?growFootmarkId=<s:property value="#growFootmark.id"/>">修改</a>
	           	  <%if(hostUser.getType() == 3 || hostUser.getType() == 4) {%>
			        | <a href="<%=path%>/info/growFootmark_fakeDelete.action?opIds=<s:property value="#growFootmark.id"/>">删除</a>
			      <%}%>
	           	  </li>
	              <li class="clearfix"><img src="<s:property value="#growFootmark.firstImage"/>"/><s:property value="#growFootmark.description"/></li>               
	    </ul>
        </s:iterator>        	
    <s:if test="#growFootmarks.growFootmarks.pageString != null && #growFootmarks.growFootmarks.pageString != ''">
			<div class="pagenum">
			<s:property value="#growFootmarks.growFootmarks.pageString" escape="false"/>
			</div>
    </s:if>	
        <p>&nbsp;</p>
        <p>             
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>