<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="head">
<p class="r a-r t-tips">
<a href="http://www.ziguiw.com" target="_blank">网站首页</a>|[<a href="/admin/administrator_logout.action">退出管理中心</a>]
</p>
	<ul id="nav">
        <s:action var="adminMenus" name="authority_getMenu" namespace="/admin/auth" executeResult="false" ignoreContextParams="false"></s:action>
        <s:iterator value="#adminMenus.topMenuList" var="adminMenu">
            <li>
            <li>
                <a href="${adminMenu.url}?menuId=${adminMenu.id}" target="_self"><span>${adminMenu.value}</span></a>|
            </li>
        </s:iterator>
	</ul>
</div>