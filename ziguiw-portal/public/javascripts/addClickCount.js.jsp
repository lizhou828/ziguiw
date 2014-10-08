<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.zigui.domain.UserBase,java.util.Map,org.apache.struts2.ServletActionContext,com.opensymphony.xwork2.ActionContext"%>
<% 
String path = request.getContextPath();
%>
function addClickCount() {
    var resourceType = document.getElementById("resourceType").getAttribute('value');
    var resourceId = document.getElementById("resourceId").getAttribute('value');
     if(resourceType!=null&&resourceType!=""){
         $.ajax({
		type : "post",
		url : "<%=path%>/admin/ajax/common_addClickCount.action",
		dataType : 'text',
		data : '&resourceId=' + resourceId + '&resourceType='+ resourceType,
		success : function(text) {
		}
	   });
     }
   
}

window.onload=addClickCount;


