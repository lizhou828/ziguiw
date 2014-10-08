<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<p>
    <c:if test="${user != null }">
        <c:choose>
            <c:when test="${userType==2}">${teacher.name}老师,您好！</c:when>
            <c:when test="${userType==3}">${student.xs_xming}家长,您好！</c:when>
            <c:when test="${userType==4}">${student.xs_xming}同学,您好！</c:when>
            <c:otherwise>
                ${use.nickName}游客!
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if test="${user == null}">
        游客，你好！
    </c:if>

</p>