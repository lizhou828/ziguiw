<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/templateHead.jsp"%>
<%@ page import="com.zigui.utils.ConfigUtils"%>
<!--header E -->
<!--register s -->
<div class="content">
<div class="login bor_d m-t10" style="height:630px">



<h3>数字化校园用户，请直接点击 <a href="<%=path%>/user/login.jsp">登录</a>  <a href="#">什么是数字化校园用户？</a></h3>



<h2 class="">请填写您的注册信息</h2>


<form method="post" action="<%=path%>/user/user_register.action">
<ul class="login_ul ul-li-s l">
<li style="text-align:center" class="red">
	<s:fielderror>
	</s:fielderror>
</li>
<li><span>账户名：</span><input type="text" class="inp_txt" name="user.nickName" id="userNickName" value='<s:property value="user.nickName"/>'/><label class="red_b9 m-l4">*</label>


<samp style="width:200px;">4-16个字符</samp>
</li>
<%--<li><span>注册类型：</span><select class="inp_txt" name="regType" id="regType" style="height:30px">--%>
	<%--<option value=1>邮箱注册</option>--%>
	<%--<option value=2>手机注册</option>--%>
<%--</select>--%>
<%--<label class="red_b9 m-l4">*</label></li>--%>
<li id="email"><span>邮箱：</span><input type="text" class="inp_txt" id="" name="user.email" value='<s:property value="user.email"/>'/><label class="red_b9 m-l4">*</label></li>
<%--<li id="mobile" style="display:none"><span>手机：</span><input type="text" class="inp_txt" id="userMobile" name="user.mobile" value='<s:property value="user.mobile"/>'/><label class="red_b9 m-l4">*</label></li>--%>

<li><span>密码：</span><input type="password" class="inp_txt" name="user.password"/><label class="red_b9 m-l4">*</label><samp style="width:200px;">6-16个字符</samp></li>
<li><span>密码确认：</span><input type="password" class="inp_txt" name="secondPassword"/><label class="red_b9 m-l4">*</label><samp style="width:200px;">请重新填写一遍密码</samp></li>
<li class="commonValid"><span>验证码：</span><img src="<%=path%>/kaptcha.jsp" width="200" id="kaptchaImage"/><a id="changeKaptch" href="javascript:return void(0)">看不清，换一张</a></li>
<li class="commonValid"><span>输入验证码：</span><input type="text" class="inp_txt" name="validateCode"/><label class="red_b9 m-l4">*</label></li>

<%--<li class="mobileValid" style="display:none"><span>获取短信验证码：</span><a id="changeMobileKaptch" href="javascript:return void(0)"><input type="button" value="（重新）发送短信验证码"></a></li>--%>
<%--<li class="mobileValid" style="display:none"><span>输入短信验证码：</span><input type="text" class="inp_txt" name="mobileValidateCode"/><label class="red_b9 m-l4">*</label></li>--%>
<style>
.register_btn_no {
	WIDTH: 226px;
	BACKGROUND: url(../images/register_no.png) no-repeat 0px 0px;
	border-width: 0;
    height: 42px;
    line-height: 36px;
    text-align: center;
}
</style>
<li> <input type="checkBox" id="_protocol" checked="true" onclick="allowProtocol();"/>《<a href="/helpCenter/contract.jsp">子贵网使用协议</a>》</li>
<li><input type="submit" id="register_btn" class="register_btn" value="" name="Button1"/></li>
</ul>
<input type="hidden" class="inp_txt" name="state" value=1/>
</form>
<p class="r link_red">还没有帐号？现在<a href="#">注册</a>
<a class="l_ad bor_d" href="#"><img src="<%=path%>/images/238X177.jpg" alt=""/></a>


<span style="clear:both; width:238px; display:block; margin-top:200px;  font-weight:100; font-size:12px; ">
<samp style="font-weight:bold;">温馨提示：</samp><br />
数字校园信息系统用户登录子贵网<br />

家长登录账号：手机号<br />
学生登录账号：校徽卡<br />

老师登录账号：信息员统一分配</span>

</p>




</div>
 <%@ include file="../inc/templateLink.jsp"%>
</div>

<script>
$(function() {  
    $('#changeKaptch').click(function() {  
        $('#kaptchaImage').attr('src', '<%=path%>/kaptcha.jsp?' + Math.floor(Math.random() * 100));
    })
})

	function allowProtocol(){
		var register_btn = document.getElementById("register_btn");
		var _protocol = document.getElementById("_protocol");
		if (_protocol.checked) {
			register_btn.className = "register_btn";
			register_btn.disabled=false;
		} else {
			register_btn.className = "register_btn_no";
			register_btn.disabled=true;
		}
	}
	allowProtocol();
</script>

    
<%@ include file="../inc/templateFoot.jsp"%>