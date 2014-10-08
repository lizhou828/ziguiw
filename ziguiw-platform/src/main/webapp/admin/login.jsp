<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>子贵后台登录</title>
<link href="<%=path%>/css/login.css" rel="stylesheet" type="text/css" />
<script src="<%=path%>/jquery/jquery-1.7.2.min.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript">
$ = jQuery;
function changeAuthCode() {
	var num = 	new Date().getTime();
	var rand = Math.round(Math.random() * 10000);
	num = num + rand;
	$('#ver_code').css('visibility','visible');
	if ($("#vdimgck")[0]) {
		$("#vdimgck")[0].src = "../include/vdimgck.php?tag=" + num;
	}
	return false;	
}
</script>
</head>
<body>

<div class="dl-bg">
<div id="login-box">
   <div class="login-top"><a href="http://www.ziguiw.com" target="_blank" title="返回网站主页">返回网站主页</a></div>
   
   
   
   <div class="login-main">
    <form name="form1" method="post" action="<%=path%>/admin/administrator_login.action">
      <input type="hidden" name="gotopage" value="/dede/" />
      <input type="hidden" name="dopost" value="login" />
      <input name='adminstyle' type='hidden' value='newdedecms' />
      <dl>
	   <dt>用户名：</dt>
	   <dd><input class="pt-bt1" type="text" name="administrator.nickName"/></dd>
	   <dt>密&nbsp;&nbsp;码：</dt>
	   <dd><input class="pt-bt1" type="password" class="alltxt" name="administrator.password"/></dd>
	   		<dt>&nbsp;</dt>
		<dd><input  name="Submit1" id="Submit1" type="submit" value="登录"  class="inp_btn" onclick="getEditorContent()"/></dd>
	 </dl>
	</form>
   </div>
   <div class="login-power">Powered by<a href="http://www.ziguiw.com" title="子贵官网"><strong>ZIGUIW</strong></a>&copy; 2004-2011</div>
</div>
</div>
</body>
</html>
