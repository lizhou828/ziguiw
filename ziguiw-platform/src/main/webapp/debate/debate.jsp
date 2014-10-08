<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.zigui.domain.UserBase"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath();
UserBase user = (UserBase)session.getValue("VALID_USER");
%>

<s:action var="debate" name="page" namespace="/" executeResult="true" ignoreContextParams="true">
	<s:param name="templateName">debate_detail.ftl</s:param>
	<s:param name="debateId">${debateOpinion.debate.id}</s:param>
	<s:param name="errorMsg"><s:fielderror/></s:param>
</s:action>
