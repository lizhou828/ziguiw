<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/comm/common.jsp"%>
<c:choose>
    <c:when test="${user.type == 3 && fn:length(students) > 1}">
        <b>请选择你的孩子：</b>
        <c:forEach items="${students}" var="student">
            <input type="radio"  value="${student.xs_id}" name="studentId">
            ${student.xs_xming}
        </c:forEach>
    </c:when>
</c:choose>
