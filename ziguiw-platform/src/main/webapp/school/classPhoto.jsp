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
    <link href="/css/class.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/photo-js.js"></script>
</head>
<body>
<!--子贵网头部!-->
<<jsp:include page="inc/classHead.jsp"></jsp:include>
<!--子贵网头部结束!-->
<div class="main">

    <!--内页外边框!-->
    <div class="outside">

        <!--班级图片标题!-->
        <div class="t_title">
            <img src="../images/title_img.gif" />
            <span>班级图片</span>
        </div>
        <!--标题结束!-->
        <!--标题分割线!-->
        <div class="t_line"></div>
        <!--分割线结束!-->

        <!--相册开始!-->

        <%--<script type="text/javascript">--%>
            <%--$(document).ready(function (){--%>

                <%--//点击小图切换大图--%>
                <%--$("#thumbnail li a").click(function(){--%>
                    <%--$(".zoompic img").hide().attr({ "src": $(this).attr("href"), "title": $("> img", this).attr("title") });--%>
                    <%--$("#thumbnail li.current").removeClass("current");--%>
                    <%--$(this).parents("li").addClass("current");--%>
                    <%--return false;--%>
                <%--});--%>
                <%--$(".zoompic>img").load(function(){--%>
                    <%--$(".zoompic>img:hidden").show();--%>
                <%--});--%>

                <%--//小图片左右滚动--%>
                <%--var $slider = $('.slider ul');--%>
                <%--var $slider_child_l = $('.slider ul li').length;--%>
                <%--var $slider_width = $('.slider ul li').width();--%>
                <%--$slider.width($slider_child_l * $slider_width);--%>

                <%--var slider_count = 0;--%>

                <%--if ($slider_child_l < 5) {--%>
                    <%--$('#btn-right').css({cursor: 'auto'});--%>
                    <%--$('#btn-right').removeClass("dasabled");--%>
                <%--}--%>

                <%--$('#btn-right').click(function() {--%>
                    <%--if ($slider_child_l < 5 || slider_count >= $slider_child_l - 5) {--%>
                        <%--return false;--%>
                    <%--}--%>

                    <%--slider_count++;--%>
                    <%--$slider.animate({left: '-=' + $slider_width + 'px'}, 'fast');--%>
                    <%--slider_pic();--%>
                <%--});--%>

                <%--$('#btn-left').click(function() {--%>
                    <%--if (slider_count <= 0) {--%>
                        <%--return false;--%>
                    <%--}--%>
                    <%--slider_count--;--%>
                    <%--$slider.animate({left: '+=' + $slider_width + 'px'}, 'fast');--%>
                    <%--slider_pic();--%>
                <%--});--%>

                <%--function slider_pic() {--%>
                    <%--if (slider_count >= $slider_child_l - 5) {--%>
                        <%--$('#btn-right').css({cursor: 'auto'});--%>
                        <%--$('#btn-right').addClass("dasabled");--%>
                    <%--}--%>
                    <%--else if (slider_count > 0 && slider_count <= $slider_child_l - 5) {--%>
                        <%--$('#btn-left').css({cursor: 'pointer'});--%>
                        <%--$('#btn-left').removeClass("dasabled");--%>
                        <%--$('#btn-right').css({cursor: 'pointer'});--%>
                        <%--$('#btn-right').removeClass("dasabled");--%>
                    <%--}--%>
                    <%--else if (slider_count <= 0) {--%>
                        <%--$('#btn-left').css({cursor: 'auto'});--%>
                        <%--$('#btn-left').addClass("dasabled");--%>
                    <%--}--%>
                <%--}--%>

            <%--});--%>
        <%--</script>--%>
        <div class="p_out">
            <div class="zoombox">
                <c:forEach items="${classPhotos}" var="classPhotoCover">
                <c:if test="${classPhotoCover.cover == 1}">
                <div class="zoompic"><img src="/${ctx}${classPhotoCover.url}" width="684" height="394" alt="${classPhotoCover.title}" /></div>
                </c:if>
                </c:forEach>
                <div class="sliderbox">
                    <div id="btn-left" class="arrow-btn dasabled"></div>
                    <div class="slider" id="thumbnail">
                        <ul>
                            <c:forEach items="${classPhotos}" var="classPhoto">
                            <c:if test="${classPhoto.cover == 0 || classPhoto.cover == null}">
                            <li class="current"><a href="/user/classPhoto.action?class_id=${class_id}&photoId=${classPhoto.id}" ><img src="${ctx}/${classPhoto.url}" width="115" height="74" alt="${classPhoto.title}" /></a></li>
                            </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                    <div id="btn-right" class="arrow-btn"></div>
                </div>

            </div><!--slider end-->
            <div style="text-align:center;margin-top:20px"  >
                <c:forEach items="${classPhotos}" var="photo">
                    <c:if test="${photo.id == photoId}">
                        <img src="${ctx}/${photo.url}" width="650" heigth="650"/>
                    </c:if>
                </c:forEach>
            </div>
        </div>



        <!--相册结束!-->

    </div>




    <!--内页外边框结束!-->

</div>

<!--子贵网底部开始!-->
<<jsp:include page="inc/templateFoot.jsp"></jsp:include>
<!--子贵网底部结束!-->



</body>
</html>
