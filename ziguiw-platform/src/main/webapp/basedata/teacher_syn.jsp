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
<%@ page import="com.zigui.utils.ConfigUtils"%>
<%@ page import="java.io.*,org.apache.commons.lang.StringUtils,org.apache.commons.lang.exception.ExceptionUtils,org.apache.commons.codec.digest.DigestUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="config.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师信息同步</title>
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
GetMethod getMethod = new GetMethod(ConfigUtils.getStringByKey("base_data_syn_url", urlLocation + "/DSIS_zgw_syndata/") + "synchon.htm?table=teacher&pages=" + currentPage + "&pagesize=" + countPerPage);

HttpClient htpc = new HttpClient();
htpc.executeMethod(getMethod);

byte[] responseBody = new byte[0];
byte[] buffer = new byte[1024];
InputStream is = getMethod.getResponseBodyAsStream();
int length = 0;
while((length = is.read(buffer)) > 0){
	byte[] newResponseBody = new byte[responseBody.length + length];
	
	System.arraycopy(responseBody, 0, newResponseBody, 0, responseBody.length);
	System.arraycopy(buffer, 0, newResponseBody, responseBody.length, length);
	
	responseBody = newResponseBody;
}

String responseStr = new String(responseBody, "UTF-8");

JSONObject jsonObj = JSONObject.fromObject(responseStr);

JSONArray data = jsonObj.getJSONArray("data");
int dataLength = data.size();
%>

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
		CopyTeacher teacher = new CopyTeacher();
		teacher.setAddress(tempJson.getString("ADDRESS"));
		teacher.setBirthday((Date)null);
		teacher.setDuties(tempJson.getString("duties".toUpperCase()));
		teacher.setEducation(tempJson.getString("education".toUpperCase()));
		teacher.setFax(tempJson.getString("fax".toUpperCase()));
		teacher.setGraduatesch(tempJson.getString("graduatesch".toUpperCase()));
		teacher.setHomephone(tempJson.getString("homephone".toUpperCase()));
		teacher.setJobtilte(tempJson.getString("jobtilte".toUpperCase()));
		teacher.setMoblie(tempJson.getString("moblie".toUpperCase()));
		teacher.setName(tempJson.getString("name".toUpperCase()));
		teacher.setOfficephone(tempJson.getString("officephone".toUpperCase()));
		teacher.setResume(tempJson.getString("resume".toUpperCase()));
		teacher.setTeacherID(tempJson.getLong("teacherid".toUpperCase()));
		teacher.setXxID(tempJson.getString("xxid".toUpperCase()));
		teacher.setZipcode(tempJson.getString("zipcode".toUpperCase()));
		
		if(StringUtils.isNotEmpty(tempJson.getString("ACCOUT")) && !StringUtils.equalsIgnoreCase(tempJson.getString("ACCOUT"), "null")){
			commonService.saveOrUpdate(teacher);
			
			UserBase userBase = new UserBase();
			/**从子贵导入数据的特殊标识，state为5*/
			userBase.setState(5);
			userBase.setNickName("" + tempJson.getString("ACCOUT"));
			userBase.setPassword(tempJson.getString("PASS"));
			userBase.setType(2);
			
			userBase.setRealName(teacher.getName());
			userBase.setForeignKey((long)teacher.getTeacherID());
			
			commonService.save(userBase);
		}
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
window.location.href="teacher_syn.jsp?page=" + <%=(Integer.parseInt(currentPage) + 1)%>;
</script>
<%	
}
%>


</body>
</html>