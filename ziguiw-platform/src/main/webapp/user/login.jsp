<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/templateHead.jsp"%>
<%@ page import="com.zigui.utils.ConfigUtils"%>

<!--register s -->

<div class="content">

<div class="login bor_d m-t10">

<h2 class="">用户登录</h2>


<form method="post" action="<%=path%>/user/user_login.action">

<div class="red">
	<s:fielderror>
		<s:param>user.nickName</s:param> 
		<s:param>user.password</s:param>
	</s:fielderror>
</div>
<ul class="login_ul ul-li-s l">

<li><span>账户名：</span><input type="text" class="inp_txt" name="user.nickName" id="accountId"/>
	<label class="red_b9 m-l4">*</label>
</li>

<li><span>密码：</span><input type="password" class="inp_txt" name="user.password" id="passwd"/>
	<label class="red_b9 m-l4">*</label>
</li>

<li><span>验证码：</span><img src="<%=path%>/kaptcha.jsp" width="200" id="kaptchaImage" /><a id="changeKaptch" href="javascript:return void(0)">看不清，换一张</a></li>
<li><span>输入验证码：</span><input type="text" class="inp_txt" name="validateCode" id="validateCode"/><label class="red_b9 m-l4">*</label></li>

<li><input type="button" class="login_btn" value="" name="Button1">&nbsp;&nbsp; <%if(ConfigUtils.getBoolByKey("find_password", true)) {%><a href="<%=path%>/user/find_password.jsp">忘记密码？</a><%} %></li>
</ul>

</form>

<p class="r link_red">还没有帐号？现在<a href="register.jsp">注册</a>

<a class="l_ad bor_d" href="#"><img src="<%=path%>/images/238X177.jpg" alt=""></a>

</p>

</div>
 <%@ include file="../inc/templateLink.jsp"%>
</div>

<script src="<%=path%>/js/loginAf.js"></script>
<script>
$(function() {  
    $('#changeKaptch').click(function() {  
        $('#kaptchaImage').attr('src', '<%=path%>/kaptcha.jsp?' + Math.floor(Math.random() * 100));
    })  
});

$(function(){
    var userRepeatLogin = '<%=session.getAttribute("userRepeatLogin")%>'
    if(userRepeatLogin == "xxx"){
        alert("您的账号已在别处登录，请重新登录!");
    }
});


</script>


<%@ include file="../inc/templateFoot.jsp"%>