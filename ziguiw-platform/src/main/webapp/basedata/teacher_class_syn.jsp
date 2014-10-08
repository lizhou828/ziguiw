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
<%@ page import="com.zigui.utils.ConfigUtils"%>
<%@ page import="java.io.*,org.apache.commons.lang.StringUtils,org.apache.commons.lang.exception.ExceptionUtils,org.apache.commons.codec.digest.DigestUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目同步</title>
</head>
<body>

<%
String path = request.getContextPath();

String currentPage = request.getParameter("page");
String countPerPage = request.getParameter("pagesize");

if(StringUtils.isEmpty(currentPage)){
	currentPage = "1";
}

if(StringUtils.isEmpty(countPerPage)){
	countPerPage = "500";
}

CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
GetMethod getMethod = new GetMethod(ConfigUtils.getStringByKey("base_data_syn_url", urlLocation + "/DSIS_zgw_syndata/") + "general.htm?table=teacherClass&pages=" + currentPage + "&pagesize=" + countPerPage);

HttpClient htpc = new HttpClient();
htpc.executeMethod(getMethod);

byte[] responseBody = getMethod.getResponseBody();
String responseStr = new String(responseBody, "UTF-8");

JSONObject jsonObj = JSONObject.fromObject(responseStr);

JSONArray data = jsonObj.getJSONArray("data");
int dataLength = data.size();
%>
<%=responseStr%><br/>
<%=jsonObj.get("rectotal")%><br/>
<%=dataLength%><br/>

<%
for (int i = 0; i < dataLength; i++)
{
	JSONObject tempJson = JSONObject.fromObject(data.get(i));
	
%>

<%=tempJson%><br/>

<%	
	try{
		CopyTeacherClasz teacherClasz = new CopyTeacherClasz();
		teacherClasz.setBj_id(tempJson.getInt("bjid"));
		teacherClasz.setRtc_id(tempJson.getInt("teacClassId"));
		teacherClasz.setTeacherID(tempJson.getInt("teacherid"));
		
		commonService.saveOrUpdate(teacherClasz);
	}catch(Exception e){
		response.getWriter().write(ExceptionUtils.getFullStackTrace(e));
	}
}

response.getWriter().write(Integer.parseInt(currentPage) * Integer.parseInt(countPerPage) + "<br/>");
response.getWriter().write(jsonObj.getInt("rectotal") + "<br/>");

if(Integer.parseInt(currentPage) * Integer.parseInt(countPerPage) < jsonObj.getInt("rectotal")){
	response.getWriter().write("跳转");
	response.getWriter().flush();
%>
<script>
window.location.href="teacher_class_syn.jsp?page=" + <%=(Integer.parseInt(currentPage) + 1)%>;
</script>
<%	
}
%>

</body>
</html>