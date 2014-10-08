<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>  
<div class="content clearfix">
	

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">师资力量</h3>
		
	</div>
	<div class="bordb big_dygs wdxc p_b10 mb10 l">
		<ul class="img_f l">
		<s:action var="schoolTeachers" name="baseData_getTeachersBySchool" namespace="/" executeResult="false" ignoreContextParams="true">
			      <s:param name="pageNo" value="1"></s:param>
			      <s:param name="pageSize" value="1000"></s:param>
			      <s:param name="user.foreignKey"><%=hostUser.getForeignKey()%></s:param>
	        </s:action>
              <s:iterator value="#schoolTeachers.pageObj.list" var="teacher">
                  <li><img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#teacher.photo"),  path + "/images/head.jpg", 120)%>" width="120" height="120"/><div class="name"><s:property value="#teacher.name"/></div>
                  </li>
                </s:iterator>
		</ul>
	</div>
</div>	



<!--content E -->
<%@ include file="../inc/templateFoot.jsp"%>
