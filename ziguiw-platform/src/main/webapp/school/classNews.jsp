<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../comm/common.jsp"%>
<%
   String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>子贵网_校园站内站</title>
    <link href="../css/class.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/news.js"></script>
</head>
<body>
<!--子贵网头部!-->
<jsp:include page="inc/classHead.jsp"></jsp:include>
<!--导航结束!-->


<div class="main">

    <!--内页外边框!-->
    <div class="outside">

        <!--班级图片标题!-->
        <c:if test="${type == 0}">
        <div class="t_title">
            <img src="../images/title_img.gif" />班级动态</div>
        </c:if>
        <c:if test="${type == 1}">
            <div class="t_title">
                <img src="../images/title_img.gif" />班级公告</div>
        </c:if>
        <c:if test="${type == 2}">
            <div class="t_title">
                <img src="../images/title_img.gif" />班级荣誉</div>
        </c:if>
        <c:if test="${type == 3}">
            <div class="t_title">
                <img src="../images/title_img.gif" />学习天地</div>
        </c:if>

        <!--新闻开始!-->



        <!--新闻结束!-->


        <div class="clear">
            <div class="h_menu">
                <div id="masterdiv">
                    <c:forEach items="${classNewsPage.list}" var="classNews" varStatus="status">
                    <div class="menutitle">
                        <img src="../images/n_img.png" />
                        <a href="<%=path%>/user/classNewsInfo.action?id=${classNews.id}&type=${classNews.type}&class_id=${classNews.class_id}">${classNews.title}</a>
                        <div class="n_time">
                            <fmt:formatDate value="${classNews.create_time}" type = "both" pattern="yyyy-MM-dd"></fmt:formatDate>
                        </div>
                    </div>
                    <div class="submenu" id="${status.index}" style="text-indent:32px">
                        <div><%--${classNews.autoDescription}--%></div>
                        <div class="xx"><a href="<%=path%>/user/classNewsInfo.action?id=${classNews.id}&type=${classNews.type}&class_id=${classNews.class_id}">详细内容</a></div>
                    </div>
                    </c:forEach>
                </div>
                <div style="text-align: center">${pageString}</div>
            </div>
        </div>


    </div>




    <!--内页外边框结束!-->

</div>

<!--子贵网底部开始-->
<jsp:include page="inc/templateFoot.jsp"></jsp:include>
<!--子贵网底部结束-->
</body>
</html>
