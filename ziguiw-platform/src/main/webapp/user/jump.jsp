<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page  import="java.net.URLEncoder"%>
<%@ include file="../inc/templateHead.jsp"%>


<!--header E -->
<!--register s -->
<div class="content">
<div class="login bor_d m-t10 a-c">
<h2 style="width:600px;margin:100px auto;">恭喜您！您在子贵网上注册的账号 **** 已注册成功<label style="color:#333;font-size:18px;">若有任何疑问，请拔打952116进行咨询</label></h2>
<%  String path = request.getContextPath();%>
<a herf="<%=path%>/user/select_role.jsp">我要完善资料</a>
<a herf="<%=path%>/user/user_center.jsp">进入用户中心</a>
</div>
<!--
<input type="hidden" name="loginPreUrl" id='loginPreUrl' value="<s:property value="loginPreUrl"/>"/>
<script language="JavaScript">
         var loginPreUrl = document.getElementById('loginPreUrl').getAttribute('value');
         window.location.href="<%=path%>/user/home_index.jsp"; 
         //window.location.href=loginPreUrl;
</script>
-->
<%@ include file="../inc/templateLink.jsp"%>
    </div>     
<%@ include file="../inc/templateFoot.jsp"%>