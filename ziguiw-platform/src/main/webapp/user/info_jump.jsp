<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page  import="java.net.URLEncoder,org.apache.commons.lang.StringUtils"%>
<%@ include file="../inc/templateHead.jsp"%>


<!--header E -->
<!--register s -->
<div class="content">
<div class="login bor_d m-t10 a-c">
<h2 style="width:600px;margin:100px auto;"><label style=" color:#FF6E1F;font-size:18px;">

<%if(StringUtils.equals(request.getParameter("kind"), "1")){ %>
您的教师用户未绑定学校
<%}else if(StringUtils.equals(request.getParameter("kind"), "2")){ %>
您的教师用户未绑定任何班级
<%}else if(StringUtils.equals(request.getParameter("kind"), "3")){ %>
您的学生用户未绑定任何学校
<%}else if(StringUtils.equals(request.getParameter("kind"), "4")){ %>
您的学生用户未绑定任何班级
<%}else if(StringUtils.equals(request.getParameter("kind"), "5")){ %>
您的家长用户未绑定任何学生
<%}else if(StringUtils.equals(request.getParameter("kind"), "6")){ %>
您的孩子未绑定任何学校
<%}else if(StringUtils.equals(request.getParameter("kind"), "7")){ %>
您的孩子未绑定任何班级
<%}else if(StringUtils.equals(request.getParameter("kind"), "8")){ %>
您的身份还没有通过管理员认证，请联系管理员尽快认证</br>
若有任何疑问，请拔打952116进行咨询
<%}else if(StringUtils.equals(request.getParameter("kind"), "11")){ %>
激活邮件已经发送到您的邮箱，请点击激活
<%}else if(StringUtils.equals(request.getParameter("kind"), "12")){ %>
您的帐号没有激活
<%}else if(StringUtils.equals(request.getParameter("kind"), "13")){ %>
恭喜您！您在子贵网上注册的账号:<%=request.getParameter("userNickName")%> 已激活成功</br>
若有任何疑问，请拔打952116进行咨询</br>
<div style=" margin:20px;">
<a href="<%=request.getContextPath()%>/user/portal.jsp"><img alt="" src="<%=path%>/images/jrxj.jpg" width="200" height="50" id="preview"></a>
<a href="<%=request.getContextPath()%>/user/select_role.jsp"><img alt="" src="<%=path%>/images/wszl.jpg" width="200" height="50" id="preview"></a></br>
</div>
<span style=" color:#000; font-size:12px;">友情提醒：完善资料经管理员审批，将享有“数字化校园”功能</span>

<%}else if(StringUtils.equals(request.getParameter("kind"), "14")){ %>
您的找回密码邮件已经发送到你的邮箱，请查收
<%}else if(StringUtils.equals(request.getParameter("kind"), "15")){ %>
您输入的邮箱没有在子贵网注册
<%}else if(StringUtils.equals(request.getParameter("kind"), "16")){ %>
您输入的手机号没有在子贵网注册
<%}else{ %>
子贵网正在努力为您跳转！请稍等。。。
<%} %>
</label></h2>
</div>
<input type="hidden" name="loginPreUrl" id='loginPreUrl' value="<s:property value="loginPreUrl"/>"/>
<script language="JavaScript">
         
         //window.location.href=loginPreUrl;
</script>

<%@ include file="../inc/templateLink.jsp"%>
    </div>     
<%@ include file="../inc/templateFoot.jsp"%>