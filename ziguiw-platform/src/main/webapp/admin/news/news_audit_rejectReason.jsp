<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
    String path = request.getContextPath();
    String currentUrl = request.getRequestURL().toString();
    String queryString = request.getQueryString() == null ? "1=1" : request.getQueryString();
    queryString = queryString.replace("currentPage=", "");
    queryString = queryString.replace("orderField=", "");
    queryString = queryString.replace("orderType=", "");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <link href="<%=path%>/css/base-min.css" rel="stylesheet"/>
    <link href="<%=path%>/css/admin.css" type="text/css" rel="stylesheet" media="all"/>
    <title>子贵网CMS</title>
</head>
<body>

<%@ include file="../inc/left.jsp"%>
<div class="right">

    <%@ include file="../inc/navigation.jsp"%>

    <p class="map">新闻：新闻审核失败原因查看</p>
    <s:fielderror/>

    <table class="table">
        <colgroup>
        </colgroup>
        <thead>
        <td>
            审批失败理由
        </td>
        <td>
            审批人
        </td>
        <td>
            审批时间
        </td>
        </thead>

        <s:iterator value="newsAuditList" var="newsAudit">
            <tr>
                <td>${newsAudit.rejectReason}</td>
                <td>${newsAudit.creatorNick}</td>
                <td>${newsAudit.createTime}</td>
            </tr>
        </s:iterator>
    </table>
</div>
<%@ include file="../inc/foot.jsp"%>