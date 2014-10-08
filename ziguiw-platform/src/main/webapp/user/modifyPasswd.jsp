<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<!--register s -->

<div class="content">

<div class="login bor_d m-t10">

<h2 class="">找回密码</h2>


<form method="post" action="<%=path%>/user/updatePassword.action">

<div class="red">
	<s:fielderror>
		<s:param>user.email</s:param> 
		<s:param>user.password</s:param>
	</s:fielderror>
</div>
<ul class="login_ul ul-li-s l">

<li><span>密码：</span><input type="password" class="inp_txt" name="user.password"/><label class="red_b9 m-l4">*</label></li>
<li><span>密码确认：</span><input type="password" class="inp_txt" name="secondPassword"/><label class="red_b9 m-l4">*</label></li>

<input type="hidden" name="user.id" value=<%=request.getParameter("user.id")%>>

<li><input type="submit" class="btn2 l mt5" value="找回密码" name="Button1"></input></li>
</ul>

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
    
    $('#changeMobileKaptch').click(function() {  
    	$.post("<%=path%>/user/user_geneMobileValidateCode.action?user.mobile=" + $("#userMobile").attr("value") + "&user.nickName=" + encodeURIComponent($("#userNickName").attr("value")), function(data) {
    		alert("短信已经发送");
    	});
    })
});  

$('#regType').change(function(){
	if($('#regType').val() == 2){
		$('#email').hide();
		$('#mobile').show();
		$('.mobileValid').show();
		$('.commonValid').hide();
	}else{
		$('#email').show();
		$('#mobile').hide();
		$('.commonValid').show();
		$('.mobileValid').hide();
	}
});
</script>

<%@ include file="../inc/templateFoot.jsp"%>