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
    <style type="text/css">
        .t_title {
            border:none;
            height:2px;
        }
        .c_honor {
            margin-top:0px;
            margin-left:22px;
            margin-bottom:20px;
        }

    </style>
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
            <img src="../images/title_img.gif" />新闻详细内容</div>
        </c:if>
        <c:if test="${type == 1}">
            <div class="t_title">
                <img src="../images/title_img.gif" />公告详细内容</div>
        </c:if>
        <c:if test="${type == 2}">
            <div class="t_title">
                <img src="../images/title_img.gif" />荣誉详细内容</div>
        </c:if>
        <c:if test="${type == 3}">
            <div class="t_title">
                <img src="../images/title_img.gif" />学习天地详细内容</div>
        </c:if>


        <!--详细内容!-->

        <div class="new_xx">

            <div class="x_side">



                <h2 align="center"> ${classNews.title}</h2>
                <div class="xx_time" align="center">时间：
                 <fmt:formatDate value="${classNews.create_time}" pattern="yyyy-MM-dd"></fmt:formatDate>
                </div>
                <div class="tiyao" style="display: none">
                    <p> 内容摘要:</p>
                    <p >${classNews.autoDescription}</p>
                </div>
                <div class="neirong"><p>${classNews.content}</p>
                </div>




            </div>

            <div class="c_honor">
                <div class="h_top">
                    <div class="h_name"><img src="../images/top_img.gif" />最新班级新闻</div>
                    <a class="more" href="#">更多</a>
                </div>
                <ul class="n_ul">
                    <c:forEach items="${classNewsNew.list}" var="classNewsNew">
                    <li class="n_li">
                        <a href="<%=path%>/user/classNewsInfo.action?id=${classNewsNew.id}&type=${classNewsNew.type}&class_id=${classNewsNew.class_id}" class="n_a">
                            ${fn:length(classNewsNew.title) > 19 ? fn:substring(classNewsNew.title, 0, 18) : classNewsNew.title}...
                        </a>
                    </li>
                    </c:forEach>
                </ul>
            </div>


            <div class="c_honor">
                <div class="h_top">
                    <div class="h_name"><img src="../images/top_img.gif" />最新班级动态</div>
                    <a class="more" href="#">更多</a>
                </div>
                <ul class="n_ul">
                    <c:forEach items="${classNewsDt.list}" var="classNewsDt">
                    <li class="n_li">
                        <a href="<%=path%>/user/classNewsInfo.action?id=${classNewsDt.id}&type=${classNewsDt.type}&class_id=${classNewsDt.class_id}" class="n_a">
                            ${fn:length(classNewsDt.title) > 19 ? fn:substring(classNewsDt.title, 0, 18) : classNewsDt.title}...
                        </a>
                    </li>
                    </c:forEach>
                </ul>
            </div>

        </div>

        <div class="clear"></div>


    </div>




    <!--内页外边框结束!-->

</div>

<!--子贵网底部开始!-->
<jsp:include page="inc/templateFoot.jsp"></jsp:include>
<!--子贵网底部结束!-->



</body>
</html>
