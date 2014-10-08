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
</head>
<body>
<!--子贵网头部!-->
<jsp:include page="inc/classHead.jsp"></jsp:include>


<div class="main">

    <!--内页外边框!-->
    <div class="outside">

        <!--班级图片标题!-->
        <div class="t_title">
            <img src="../images/title_img.gif" />班级视频</div>



        <!--视频!-->
        <div class="moive_main">
            <div class="moive_1"><div>最新视频</div></div>
            <div class="moive_1t">
                <ul class="n_ul">
                    <c:forEach items="${classMovies1.list}" var="classMovie1">
                    <li class="n_li"><a href="<%=path%>/user/classMovieInfo.action?class_id=${classMovie1.class_id}&classMovieId=${classMovie1.id}" class="n_a">${classMovie1.title}
                    </a>
                    </li>
                    </c:forEach>
                    </ul>
            </div>
        </div>
        <div class="moive_main">
            <div class="moive_1">
                <div>最热视频</div></div>
            <div class="moive_1t">

                <ul class="n_ul">
                    <c:forEach items="${classMovies2.list}" var="classMovie2">
                    <li class="n_li"><a href="<%=path%>/user/classMovieInfo.action?class_id=${classMovie2.class_id}&classMovieId=${classMovie2.id}" class="n_a">${classMovie2.title}
                    </a>
                    </li>
                    </c:forEach>
                </ul>

            </div>
        </div>
        <div class="moive_main">
            <div class="moive_1">
                <div>教学视频</div></div>
            <div class="moive_1t">

                <ul class="n_ul">
                    <c:forEach items="${classMovies3.list}" var="classMovie3">
                    <li class="n_li"><a href="<%=path%>/user/classMovieInfo.action?class_id=${classMovie3.class_id}&classMovieId=${classMovie3.id}" class="n_a">${classMovie3.title}
                    </a>
                    </li>
                    </c:forEach>
                </ul>

            </div>
        </div>
        <div class="moive_main">
            <div class="moive_1">
                <div>班级趣事</div></div>
            <div class="moive_1t">

                <ul class="n_ul">
                    <c:forEach items="${classMovies4.list}" var="classMovie4">
                    <li class="n_li"><a href="<%=path%>/user/classMovieInfo.action?class_id=${classMovie4.class_id}&classMovieId=${classMovie4.id}" class="n_a">${classMovie4.title}
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
