<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
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
<%@ page import="com.zigui.utils.ConfigUtils,org.apache.commons.codec.digest.DigestUtils,java.util.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
UserServiceImpl userService = (UserServiceImpl)BeanFactoryUtils.getBean("userService");
List<UserBase> userBases = userService.getAll();

for (UserBase userBase : userBases)
{
	userBase.setPassword(DigestUtils.md5Hex(userBase.getPassword()));
	
	userService.saveOrUpdate(userBase);
}
%>


</body>
</html>