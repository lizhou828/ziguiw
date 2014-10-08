<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
   String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../comm/common.jsp"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>子贵网_校园站内站</title>
    <link href="/css/class.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        body {background:url(../images/body_bg.jpg);
            background-repeat:repeat-x;
        }

    </style>
    <script src="../js/jquery-1.4a2.min.js" type="text/javascript"></script>
    <script src="../js/jquery.KinSlideshow-1.2.1.min.js"type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
        $("#KinSlideshow").KinSlideshow({
        moveStyle:"right",
        titleBar:{titleBar_height:30,titleBar_bgColor:"#08355c",titleBar_alpha:0.5},
        titleFont:{TitleFont_size:12,TitleFont_color:"#FFFFFF",TitleFont_weight:"normal"},
        btn:{btn_bgColor:"#FFFFFF",btn_bgHoverColor:"#1072aa",btn_fontColor:"#000000",btn_fontHoverColor:"#FFFFFF",btn_borderColor:"#cccccc",btn_borderHoverColor:"#1188c0",btn_borderWidth:1}
        });
        })
    </script>
</head>

<body>

<jsp:include page="inc/classHead.jsp"></jsp:include>


<!--导航结束!-->
<div class="main">

<!--滚动图片开始!-->
<div class="c_img">
    <div id="KinSlideshow" style="visibility:hidden;">
        <c:if test="${pageClassPhotoTj.list == null || fn:length(pageClassPhotoTj.list) == 0}">
            <a href="/user/classPhoto.action?class_id=${class_id}" target="_blank"><img src="/images/class_one.jpg" width="283" height="230" /></a>
        </c:if>

        <c:if test="${pageClassPhotoTj.list != null && fn:length(pageClassPhotoTj.list) != 0}">
        <c:forEach items="${pageClassPhotoTj.list}" var="classPhoto" varStatus="status">
        <c:if test="${status.index < 5}">
        <a href="/user/classPhoto.action?class_id=${class_id}" target="_blank"><img src="${ctx}/${classPhoto.url}" alt="${classPhoto.title}" width="283" height="230" /></a>
        </c:if>
        </c:forEach>
        </c:if>
    </div>

</div>

<!--滚动图片结束!-->

<!--新闻开始!-->
<div class="c_news">
    <div class="n_top">
        <div class="n_name"><img src="/images/top_img.gif" /><span>班级动态</span>
        </div>
        <a class="more" href="<%=path%>/user/classNews.action?class_id=${class_id}&currentPage=1&pageSize=10&type=0">更多</a>
    </div>

    <ul class="n_ul">
        <c:forEach items="${pageClassNews.list}" var="classNews">
        <li class="n_li"><a href="<%=path%>/user/classNewsInfo.action?id=${classNews.id}&type=${classNews.type}&class_id=${classNews.class_id}" class="n_a">${classNews.title}
        </a>
        </li>
        </c:forEach>
    </ul>
</div>
<!--新闻结束!-->

<!--公告开始!-->
<div class="c_talk">
    <div class="t_top">
        <div class="t_name"><img src="/images/top_img.gif" />班级公告</div>
        <a class="more" href="<%=path%>/user/classNews.action?class_id=${class_id}&currentPage=1&pageSize=10&type=1">更多</a>
    </div>
    <ul class="n_ul">
        <c:forEach items="${pageClassGg.list}" var="classGg">
        <li class="n_li"><a href="<%=path%>/user/classNewsInfo.action?id=${classGg.id}&type=${classGg.type}&class_id=${classGg.class_id}" class="n_a">${classGg.title}</a>
        </li>
        </c:forEach>
    </ul>
</div>
<!--公告结束!-->

<!--图片开始!-->
    <div class="c_photo">
        <div class="p_top">
            <div class="p_name"><img src="/images/top_img.gif" />班级图片</div>
            <a class="more" href="<%=path%>/user/classPhoto.action?class_id=${class_id}">更多</a>
        </div>
        <style type="text/css">
            <!--
            #demo {
                background: #FFF;
                overflow:hidden;
                width: 955px;
                margin-left:5px;
                margin-top:4px;
            }
            #demo img {
                border: 3px solid #F2F2F2;
            }
            #indemo {
                float: left;
                width: 800%;
            }
            #demo1 {
                float: left;
            }
            #demo2 {
                float: left;
            }
            -->
        </style>
        <div id="demo">
            <div id="indemo">
                <div id="demo1">
                    <c:forEach items="${pageClassPhoto.list}" var="classPhoto1">
                    <a href="/user/classPhoto.action?class_id=${class_id}&photoId=${classPhoto1.id}"><img src="${ctx}/${classPhoto1.url}" border="0" height="240" width="180"/></a>
                    </c:forEach>
                </div>
                <div id="demo2"></div>
            </div>
        </div>
        <script>
            <!--
            var speed=30;
            var tab=document.getElementById("demo");
            var tab1=document.getElementById("demo1");
            var tab2=document.getElementById("demo2");
            tab2.innerHTML=tab1.innerHTML;
            function Marquee(){
            if(tab2.offsetWidth-tab.scrollLeft<=0)
            tab.scrollLeft-=tab1.offsetWidth
            else{
            tab.scrollLeft++;
            }
            }
            var MyMar=setInterval(Marquee,speed);
            tab.onmouseover=function() {clearInterval(MyMar)};
            tab.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
            -->
        </script>

    </div>
<!--图片结束!-->

<!--荣誉开始!-->
<div class="c_honor">
    <div class="h_top">
        <div class="h_name"><img src="../images/top_img.gif" />班级荣誉</div>
        <a class="more" href="<%=path%>/user/classNews.action?class_id=${class_id}&currentPage=1&pageSize=10&type=2">更多</a>
    </div>
    <ul class="n_ul">
        <c:forEach items="${pageClassRy.list}" var="classRy">
        <li class="n_li"><a href="<%=path%>/user/classNewsInfo.action?id=${classRy.id}&type=${classRy.type}&class_id=${classRy.class_id}" class="n_a">${classRy.title}</a>
        </li>
        </c:forEach>
    </ul>
</div>
<!--荣誉结束!-->

<!--学校天地开始!-->
<div class="c_book">
    <div class="b_top">
        <div class="b_name"><img src="../images/top_img.gif" /><span>学习天地</span>
        </div>
        <a class="more" href="<%=path%>/user/classNews.action?class_id=${class_id}&currentPage=1&pageSize=10&type=3">更多</a>
    </div>

    <ul class="n_ul">
        <c:forEach items="${pageClassXxtd.list}" var="classXxtd">
        <li class="n_li"><a href="<%=path%>/user/classNewsInfo.action?id=${classXxtd.id}&type=${classXxtd.type}&class_id=${classXxtd.class_id}" class="n_a">${classXxtd.title}
        </a>
        </li>
        </c:forEach>
    </ul>
</div>
<!--学习天地束!-->

<!--视频片开始!-->
    <div class="c_talk_vedio">
        <div class="t_top">
            <div class="t_name"><img src="/images/top_img.gif" />班级视频</div>
            <a class="more" href="<%=path%>/user/classMovie.action?class_id=${class_id}&type=1&pageSize=5">更多</a>
        </div>
        <ul class="n_ul">
            <c:forEach items="${pageClassSp.list}" var="classSp">
                <li class="n_li"><a href="<%=path%>/user/classMovieInfo.action?class_id=${classSp.class_id}&classMovieId=${classSp.id}" class="n_a">${classSp.title}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

<!--视频片结束!-->


</div>
<%@include file="inc/templateFoot.jsp"%>
</body>
</html>