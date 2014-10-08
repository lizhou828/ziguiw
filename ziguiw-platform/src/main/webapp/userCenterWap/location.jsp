<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<span>首页 &gt;
    <%
        String locationName = request.getParameter("navigateName");
        if (locationName == null) {
            locationName = "用户中心";
        }
    %>
    <%= locationName%>
</span>
    <span class="quit_btn">
        <c:choose>
            <c:when test="${user == null}">
                <a target="_self" href="/userCenterWap/login.jsp">登录</a>
            </c:when>
            <c:otherwise>
                <a target="_self" href="/userCenterWap/index.action">[返回首页]</a>
                <a target="_self"  href="/userCenterWap/wap_logout.action">[退出]</a>
            </c:otherwise>
        </c:choose>
    </span>