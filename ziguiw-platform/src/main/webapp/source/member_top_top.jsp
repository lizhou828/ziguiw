<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored = "false"%>
<%@  page language="java" import="com.zigui.domain.UserBase" %>
<%@include file="/source/common_tag.jsp"%>


<div class="toolbar">
<div class="tit">
    欢迎来到子贵网！
</div>
<div class="subNav">
<%
    UserBase userBase = (UserBase)session.getAttribute("VALID_USER");
	String nickName = "";
	if(userBase != null){
		nickName = userBase.getNickName();
	}
%>
<c:set value="<%=nickName%>" var="nickName"/>
<s:if test="#session.loginMemberType == null">
	<% 
		if(userBase == null){
	%>		
    <a title="用户登录" href="${pageContext.request.contextPath}/user/login.jsp" target="_blank">用户登录</a>
    <a title="新用户注册" href="${pageContext.request.contextPath}/user/register.jsp" target="_blank">新用户注册</a>
	<% 
		}else{
	%>
		欢迎来到子贵网：<a href="${pageContext.request.contextPath }/user/portal.jsp">${nickName }</a>
		<a href="${pageContext.request.contextPath }/user/user_logout.action">退出</a>
	<%}%>
    <a href="http://dsis.ziguiw.com" title="数字化校园平台" target="_blank"><font color="red"><strong>数字化校园平台</strong></font></a>

    <a href="http://dsis.ziguiw.com/downandroid/mobile.jsp" title="手机版" target="_blank">手机版</a>

    <a href="javascript:setHome(window.location);" target="_self">设为首页</a>

    <a href="javascript:addFavorite(window.location, document.title)" target="_self">加入收藏</a>
</s:if>
<s:else>

尊敬的 <strong>${loginMemberNikename }</strong> 您好
		<!-- 1. 学校用户
		2. 家长
		3. 老师
		4. 学生,5.其他注册用户 -->
		<s:if test="#session.loginMemberType == 1">
		<a href="${ctx}/school/basicinfo/school!getschoolindex.action" class="font-3" target="_blank"><font color="red"><strong>数字化校园平台</strong></font></a>
		</s:if>
		<s:elseif test="#session.loginMemberType == 2">
		<a href="${ctx}/user/parentLogin.action" class="font-3" target="_blank"><font color="red"><strong>数字化校园平台</strong></font></a>
		</s:elseif>
		<s:elseif test="#session.loginMemberType == 3">
		<a href="${ctx}/user/teaIndexAction.action" class="font-3" target="_blank"><font color="red"><strong>数字化校园平台</strong></font></a>
		</s:elseif>
		<s:elseif test="#session.loginMemberType == 4">
		<a href="${ctx}/user/stuLogin.action" class="font-3" target="_blank"><font color="red"><strong>数字化校园平台</strong></font></a>
		</s:elseif>
		<a href="loginout.action?lastUrl=${ctx}/source/sourceInfo.action" class="font-3" target="_self">退出</a>

        <a href="http://dsis.ziguiw.com/downandroid/mobile.jsp" title="手机版" target="_blank">手机版</a>

        <a href="javascript:setHome(window.location);" target="_self">设为首页</a>

        <a href="javascript:addFavorite(window.location, document.title)" target="_self">加入收藏</a>
</s:else>

    <script>
        function addFavorite(sURL, sTitle) {
            try {
                window.external.addFavorite(sURL.toString(), sTitle.toString());
            }
            catch (e) {
                try {
                    window.sidebar.addPanel(sTitle.toString(), sURL.toString(), "");
                }
                catch (e) {
                    alert("加入收藏失败，请使用Ctrl+D进行添加");
                }
            }
        }

        function setHome(vrl) {
            if(document.all){
                document.body.style.behavior='url(#default#homepage)';
                document.body.setHomePage(vrl);
            }
            else if(window.sidebar){
                if(window.netscape){
                    try{
                        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                    }
                    catch(e){
                        alert("您的浏览器未启用[设为首页]功能，开启方法：先在地址栏内输入about:config,然后将项 signed.applets.codebase_principal_support 值该为true即可");
                    }
                }
                var prefs=Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                prefs.setCharPref('browser.startup.homepage',vrl);
            }
        }
    </script>
</div>
<div class="subNav"> </div>
</div>