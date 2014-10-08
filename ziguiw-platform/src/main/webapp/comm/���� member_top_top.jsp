<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored = "false"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="pot">
	<div class="st-1">
			<div id="skin"><p>选择字体大小：</p>
			<ul>
			  <li id="skin_0" class="selected" title="" style="cursor:pointer">小</li>
			  <li id="skin_1" title=""  style="cursor:pointer">中</li>
			  <li id="skin_2" title="" style="cursor:pointer">大</li>
			</ul>
		  </div>
		<div class="pt font-3">
		尊敬的 <strong>${loginMemberNikename }</strong> 您好，欢迎您回到子贵网，如果有任何问题，请随时联系我们，我们将竭诚为您服务。
		
		
		
		<!-- 1. 学校用户
		2. 家长
		3. 老师
		4. 学生,5.其他注册用户 -->
		<s:if test="#session.loginMemberType == 1">
		<a href="school/basicinfo/school!getschoolindex.action" class="font-3">[资料中心]</a>|
		</s:if>
		<s:elseif test="#session.loginMemberType == 2">
		<a href="user/parentLogin.action" class="font-3">[个人中心]</a>|
		</s:elseif>
		<s:elseif test="#session.loginMemberType == 3">
		<a href="user/teaIndexAction.action" class="font-3">[个人中心]</a>|
		</s:elseif>
		<s:elseif test="#session.loginMemberType == 4">
		<a href="user/stuLogin.action" class="font-3">[个人中心]</a>|
		</s:elseif>
		<a href="loginout.action" class="font-3">退出</a>
		</div>
		<div class="clear"></div>
	</div>
</div>
		
