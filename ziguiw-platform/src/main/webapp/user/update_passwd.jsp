<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>

<div class="w_home_content">
  <div class="w_home_content_box clearfix">
<div class="content clearfix   pr">
<%@ include file="inc/right.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400  r">
       	<h2 class="col-h3">修改密码</h2>

		<form method="post" action="<%=path%>/user/updatePassword.action">
<div class="red"><s:fielderror/></div>
		<ul class="link_gray ul-li-s">
			<li><span>现有密码:</span><input name="oldPassword" type="password" class="inp_txt" /></li>
				<li><span>新密码:</span><input name="user.password" type="password" class="inp_txt" /></li>
				<li><span>再输入一次:</span><input name="secondPassword" type="password" class="inp_txt" /></li>				
				<li><input name="Submit1" type="submit" value="确认修改" class="btn2"/></li>	
				
		</ul>
		</form>
    </div>
  
</div>    
</div>
</div>
<!--content E -->


<%@ include file="../inc/templateFoot.jsp"%>