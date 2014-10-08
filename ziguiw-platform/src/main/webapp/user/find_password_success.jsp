<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<!--register s -->

<div class="content">

<div class="login bor_d m-t10">

<h2 class="">找回密码</h2>


<form method="post" action="<%=path%>/user/findPassword.action">

<div class="red">
	<s:fielderror>
		<s:param>user.email</s:param> 
		<s:param>user.password</s:param>
	</s:fielderror>
</div>


<p>
您的密码重置邮件已经发送到你的邮箱
</p>





</form>

<p class="r link_red">还没有帐号？现在<a href="register.jsp">注册</a>

<a class="l_ad bor_d" href="#"><img src="<%=path%>/images/238X177.jpg" alt=""></a>

</p>

</div>

</div>


<script>
$(function() {  
    $('#changeKaptch').click(function() {  
        $('#kaptchaImage').attr('src', '<%=path%>/kaptcha.jpg?' + Math.floor(Math.random() * 100));  
    })  
});  
</script>

<%@ include file="../inc/templateFoot.jsp"%>