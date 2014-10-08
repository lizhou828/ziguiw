<%@ page import="com.zigui.exception.FileNotFoundException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<s:form action="/test.action">
	<%--<s:textfield name="strutsPager.pageSize" value="10" theme="simple"></s:textfield> --%>
	<%--<s:textfield name="strutsPager.currentPage" value="1" theme="simple"></s:textfield> 测试用,且不给力!--%>
	
		
	<%--<s:textfield name="strutsPager.query.like_title" value="${strutsPager.query.like_title}" theme="simple"></s:textfield> --%>
	<%-- 上述方法,报异常According to TLD or attribute directive in tag file, attribute value does not accept any expressions --%>
	<%--<s:textfield name="strutsPager.query.like_title" value="<s:property value="strutsPager.query.like_title"/>" theme="simple"></s:textfield> --%>	 
	<%-- 上述方法,报异常equal symbol expected --%>
	
	
	
	title<input type="text" name="strutsPager.query.like_title" value="${strutsPager.query.like_title}"/>
	create_time<input type="text" name="strutsPager.query.lt_create_time" value="${strutsPager.query.lt_create_time}"/>
	<s:submit theme="simple"/>
</s:form>


<s:iterator id="news" value="strutsPager.list">		
	<p>${news.title}</p>
</s:iterator>

<p>
page:
${strutsPager.pageNavigation}
</p>