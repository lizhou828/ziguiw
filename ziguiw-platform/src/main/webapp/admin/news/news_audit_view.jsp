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

    <p class="map">新闻：新闻审核失败</p>
    <s:fielderror/>

    <form method="post" action="<%=path%>/admin/news/newsAudit_recordAuditInfo.action">
        <textarea id="content"
                  name="newsAudit.rejectReason"
                  tabindex="0"
                  style="width:540px;height:160px;margin:0 auto;">
        </textarea>

        <input type="hidden" name='newsAudit.newsId' value="${newsId}"/>
        <input type="hidden" name='newsAudit.classNewsId' value="${classNewsId}"/>
        <input type="hidden" name='newsAudit.schoolNewsId' value="${schoolNewsId}"/>

        <p class="submit">
            <input name="Submit1" id="Submit1" type="submit" value="提 交"  class="inp_btn" onclick="getEditorContent()"/>
        </p>
    </form>
</div>
<%@ include file="../inc/foot.jsp"%>