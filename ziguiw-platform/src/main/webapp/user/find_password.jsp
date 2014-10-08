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
<ul class="login_ul ul-li-s l">

<li><span>注册类型：</span><select class="inp_txt" name="regType" id="regType" style="height:30px">
	<option value=1>邮箱注册</option>
	<option value=2>手机注册</option>
</select>

<li id="email"><span>邮箱：</span><input type="text" class="inp_txt" id="" name="user.email" value='<s:property value="user.email"/>'/><label class="red_b9 m-l4">*</label></li>
<li id="mobile" style="display:none"><span>手机：</span><input type="text" class="inp_txt" id="userMobile" name="user.mobile" value='<s:property value="user.mobile"/>'/><label class="red_b9 m-l4">*</label></li>

<li class="commonValid"><span>验证码：</span><img src="<%=path%>/kaptcha.jsp" width="200" id="kaptchaImage"/><a id="changeKaptch" href="javascript:return void;">看不清，换一张</a></li>
<li class="commonValid"><span>输入验证码：</span><input type="text" class="inp_txt" name="validateCode"/><label class="red_b9 m-l4">*</label></li>

<li class="mobileValid" style="display:none"><span>获取短信验证码：</span><a id="changeMobileKaptch" href="javascript:return void;"><input type="button" value="（重新）发送短信验证码" /></a></li>
<li class="mobileValid" style="display:none"><span>输入短信验证码：</span><input type="text" class="inp_txt" name="mobileValidateCode"/><label class="red_b9 m-l4">*</label></li>

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
        $('#kaptchaImage').attr('src', '<%=path%>/kaptcha.jsp?' + Math.floor(Math.random() * 100));  
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