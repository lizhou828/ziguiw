<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String nickName="请输入用户名";
    String password="请输入密码";

    Cookie cookies[]=request.getCookies();
    int cookiesLength = cookies.length;
    for(int i=0;i<cookiesLength;i++){
        if(cookies[i].getName().equals("loginMemberUsername")){
                String cookieValue[]=cookies[i].getValue().split("-");
                if(cookieValue.length > 1){
                    nickName=cookieValue[0];
                    password=cookieValue[1];
                }
        }
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        function  change1(){
            document.getElementById("username").value="";
        }
        function  change2(){
            document.getElementById("password").value="";
        }
    </script>

<title>子贵网_手机版_登录 </title>
</head>

<body>

<div class="header">
	<img src="/userCenterWap/images/login.png"/>
</div>
<div class="margin_height"></div>
<div class="middle">
	<form id="formLogin" action="/userCenterWap/wapAjaxLogin.action" method="post" >
				<ul class="login">
					<li>
						用户名：
					</li>
					<li>
						<input type="text" id="username" name="user.nickName" class="xg font-2" value="<%=nickName%>" onfocus="change1()" >
					</li>
				</ul>
				<ul class="login">
					<li>
						密码：
					</li>
					<li>
						<input type="password" id="password" name="user.password" class="xg font-2" value="<%=password%>" onfocus="change2()">
					</li>
				</ul>
				<ul class="login">
					<li>
						<input type="checkbox" class="xz"  id="saveUser" name="saveUserInCookie">
						记住我
					</li>
                            <li>
                                <div id="errorMsg" style="color: #ff0000;">
                                         ${errorMsg}
                                </div>
                            </li>
				</ul>
				<ul class="login">
					<li>
						<input type="submit" class="an" value="登录" name="input2" id="wapLogin">
						<span><a href="#">忘记密码？</a></span>
					</li>
				</ul>
			</form>
</div>
<div class="margin_height"></div>
<div class="footer">
	<img src="/userCenterWap/images/footer_note.png" />
</div>

</body>
</html>
