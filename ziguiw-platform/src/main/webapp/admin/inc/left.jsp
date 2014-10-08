<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="left">
    <a href="" target="_self" class="logo"><img src="/images/logo.gif" alt="ZIGUICMD管理后台" width="155"/></a>
    <div class="sidebar">
        <ul>
            <s:action var="adminMenus" name="authority_getMenu" namespace="/admin/auth" executeResult="false" ignoreContextParams="false"></s:action>
            <s:iterator value="#adminMenus.leftMenuList" var="adminMenu">
                <li><a href="${adminMenu.url}" target="_self">${adminMenu.value}</a></li>
            </s:iterator>
        </ul>
    </div>
</div>