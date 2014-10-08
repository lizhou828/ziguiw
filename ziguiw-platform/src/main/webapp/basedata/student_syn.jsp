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
<meta http-equiv="expires" content="Sat,1 Jan 2000 00:00:00 GMT">
<title>学生同步</title>
</head>
<body>

<%
/**
delete from T_student;
delete from user_base where state = 5 and type = 4;
*/
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
GetMethod getMethod = new GetMethod(ConfigUtils.getStringByKey("base_data_syn_url", urlLocation + "/DSIS_zgw_syndata/") + "synchon.htm?table=student&pages=" + currentPage + "&sorttype=desc&pagesize=" + countPerPage);

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
<h>request:</h><br/>
currentPage:<%=currentPage %><br/>
countPerPage:<%=countPerPage %><br/>
<br/>

<h>response:</h><br/>
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
		CopyStudent student = new CopyStudent();
		student.setXs_id(tempJson.getInt("XS_ID".toUpperCase()));
		student.setAccommodation(tempJson.getString("accommodation".toUpperCase()));
		student.setAccountPlace(tempJson.getString("accountplace".toUpperCase()));
		
		student.setBanGanBuID(tempJson.getString("banganbuid".toUpperCase()));
		student.setBirthday((Date)null);
		student.setBirthplace(tempJson.getString("birthplace".toUpperCase()));
		student.setBj_ID(tempJson.getString("bj_Id".toUpperCase()));
		
//		student.setGroupID(tempJson.getString("GROUPID".toUpperCase())==null||tempJson.getString("GROUPID".toUpperCase()).equals("null")?0:new Long(tempJson.getString("GROUPID".toUpperCase())));
		
		student.setHealthstate(tempJson.getString("healthstate".toUpperCase()));
		student.setHobby(tempJson.getString("hobby".toUpperCase()));
		student.setHomeaddress(tempJson.getString("homeaddress".toUpperCase()));
		student.setHomephone(tempJson.getString("homephone".toUpperCase()));
		student.setIDCard(tempJson.getString("CARDID".toUpperCase()));
		student.setMzhu(tempJson.getString("mzhu".toUpperCase()));
		student.setOtherlinks(tempJson.getString("otherlinks".toUpperCase()));
		student.setPoliticalFace(tempJson.getString("politicalface".toUpperCase()));
		student.setXj_bhao(tempJson.getString("XJ_BHAO".toUpperCase()));
		

		student.setXs_xming(tempJson.getString("XS_XMING".toUpperCase()));
	    student.setXxID(tempJson.getLong("xxid".toUpperCase()));
		student.setZip(tempJson.getString("zip".toUpperCase()));
		
		student.setIDCard(tempJson.getString("CARDID"));
		
		if(StringUtils.isNotEmpty(student.getIDCard()) && !StringUtils.equalsIgnoreCase(student.getIDCard(), "null")){
			commonService.saveOrUpdate(student);
			
			UserBase userBase = new UserBase();
			/**从子贵导入数据的特殊标识，state为5*/
			userBase.setState(5);
			userBase.setNickName("" + student.getIDCard());
			userBase.setPassword("e10adc3949ba59abbe56e057f20f883e");
			userBase.setType(4);
			
			userBase.setRealName(student.getXs_xming());
			userBase.setForeignKey((long)student.getXs_id());
			
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
window.location.href="student_syn.jsp?page=" + <%=(Integer.parseInt(currentPage) + 1)%>;
</script>
<%	
}
%>


</body>
</html>