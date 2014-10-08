<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored = "false"%>

<%@ include file="/comm/common_tag.jsp" %>
<div class="pot">
	<div class="st-1">
			<div id="skin"> <a href="${ctx}/source/sourceInfo.action">教育资源</a> |
			  
			  <a href="http://new.ziguiw.com/ziguiw/news/index">教育在线</a> |

			  <a href="http://new.ziguiw.com/ziguiw/study/index">教育知识</a>
		  </div>
		<div class="pt font-3">
		尊敬的 <strong>${loginMemberNikename }</strong> 您好，欢迎您回到子贵网。
		
		
		<!-- 1. 学校用户
		2. 家长
		3. 老师
		4. 学生,5.其他注册用户 -->
		<s:if test="#session.loginMemberType == 1">
		<a href="${ctx}/school/basicinfo/school!getschoolindex.action" class="font-3">[资料中心]</a>|
		</s:if>
		<s:elseif test="#session.loginMemberType == 2">
		<a href="${ctx}/user/parentLogin.action" class="font-3">[个人中心]</a>|
		</s:elseif>
		<s:elseif test="#session.loginMemberType == 3">
		<a href="${ctx}/user/teaIndexAction.action" class="font-3">[个人中心]</a>|
		</s:elseif>
		<s:elseif test="#session.loginMemberType == 4">
		<a href="${ctx}/user/stuLogin.action" class="font-3">[个人中心]</a>|
		</s:elseif>
		<a href="${ctx}/loginout.action" class="font-3">退出</a>
		</div>
		<div class="clear"></div>
	</div>
</div>
		
