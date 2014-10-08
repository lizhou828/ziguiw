<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/jscript" src="/userCenterWap/js/jquery.js"></script>
    <script type="text/jscript" src="/userCenterWap/js/mouse.js"></script>
    <title>子贵网_手机版 </title>
</head>

<body>
<div class="header">
    <p class="nav_title">数字化校园平台查询</p>
</div>

<div class="location">
    <jsp:include page="location.jsp"></jsp:include>
</div>

<div class="c_name pl10">
    <jsp:include page="userInfo.jsp"></jsp:include>
</div>

<div class="middle">
    <div class="nav">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr onmouseout="out(this)" onmouseover="over(this);">
                <c:forEach items="${fieldNames}" var="fieldName">
                    <td>
                            ${fieldName}
                    </td>
                </c:forEach>
            </tr>
            <c:forEach items="${map['data']}" var="obj">
               <tr onmouseout="out(this)" onmouseover="over(this);">
                   <c:forEach items="${fields}" var="field">
                       <td>
                           ${obj[field]}
                       </td>
                   </c:forEach>
               </tr>
           </c:forEach>
        </table>
    </div>
</div>


</body>
</html>
