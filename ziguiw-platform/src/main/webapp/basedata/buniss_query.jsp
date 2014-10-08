<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.domain.UserBase"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page import="com.zigui.service.impl.UserServiceImpl"%>
<%@ page import="com.zigui.service.impl.SchoolServiceImpl"%>
<%@ page import="com.zigui.service.impl.CommonServiceImpl"%>
<%@ page import="com.zigui.domain.SpaceVisitHistory"%>
<%@ page import="com.zigui.utils.BeanFactoryUtils"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ page import="com.zigui.domain.*"%>
<%@ page import="com.zigui.domain.SchoolInfo"%>
<%@ page import="org.apache.commons.httpclient.*"%>
<%@ page import="org.apache.commons.httpclient.methods.GetMethod"%>
<%@ page import="org.apache.commons.httpclient.params.HttpMethodParams"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONSerializer"%>
<%@ page import="java.io.*,org.apache.commons.lang.StringUtils,org.apache.commons.lang.exception.ExceptionUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.zigui.utils.ConfigUtils"%>
<%@ include file="config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String currentPage = request.getParameter("page");
String countPerPage = request.getParameter("pagesize");
String tableName = request.getParameter("tablename");
String studentId = request.getParameter("studentid");
String bjId = request.getParameter("bjid");
String parentid = request.getParameter("parentid");

if(StringUtils.isEmpty(currentPage)){
	currentPage = "1";
}

if(StringUtils.isEmpty(countPerPage)){
	countPerPage = "500";
}

CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
String url = ConfigUtils.getStringByKey("base_data_syn_url", urlLocation + "/DSIS_zgw_syndata/") + 
"bussiness.htm?table=" + tableName + "&pages=" + currentPage + "&pagesize=" + countPerPage;

if(StringUtils.isNotEmpty(studentId)){
	url = url + "&studentid=" + studentId;
}
if(StringUtils.isNotEmpty(bjId)){
	url = url + "&bjid=" + bjId;
}
if(StringUtils.isNotEmpty(parentid)){
	url = url + "&parentid=" + parentid;
}
%>
<%=url%>
<%
GetMethod getMethod = new GetMethod(url);

HttpClient htpc = new HttpClient();
htpc.executeMethod(getMethod);

byte[] responseBody = getMethod.getResponseBody();
String responseStr = new String(responseBody, "UTF-8");	



%>

<%=responseStr%><br/>

<%response.getWriter().write("end <br/>"); %>
</body>
</html>