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

            <div class="t_title">
                <img src="../images/title_img.gif" />班级视屏</div>

        <!--详细内容!-->

        <div class="new_xx">

            <div class="x_side">



                <h2 align="center"> ${classPhotoInfo.title}</h2>
                <div class="xx_time" align="center">时间：${classPhotoInfo.create_time}</div>
                <div class="tiyao">
                    <p> 内容摘要:</p>
                    <p >${classPhotoInfo.autoDescription}</p>
                    <!-- player begin -->

                    <script src="http://dsis.ziguiw.com/edu_resources/flexpaper/js/flexpaper_flash.js"></script>
                    <script src="http://dsis.ziguiw.com/edu_resources/js/AC_RunActiveContent.js"></script>
                    <script language="javascript">
                        if (AC_FL_RunContent == 0) {
                            alert("This page requires AC_RunActiveContent.js.");
                        } else {
                            AC_FL_RunContent(
                                    'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
                                    'width', '465',
                                    'height', '295',
                                    'src', 'http://dsis.ziguiw.com:80/edu_resources/js/jcplayer',
                                    'quality', 'high',
                                    'pluginspage', 'http://www.macromedia.com/go/getflashplayer',
                                    'align', 'middle',
                                    'play', 'true',
                                    'loop', 'true',
                                    'scale', 'noScale',
                                    'wmode', 'window',
                                    'devicefont', 'false',
                                    'id', 'jcplayer',
                                    'bgcolor', '#ffffff',
                                    'name', 'jcplayer',
                                    'menu', 'true',
                                    'allowFullScreen', 'true',
                                    'allowScriptAccess','sameDomain',
                                    'FlashVars','videoURL=http://www.ziguiw.com/${classPhotoInfo.url}&autoPlay=true&startPhotoSource=http://dsis.ziguiw.com/&backgroundColor1=0x333333&backgroundColor2=0x222222',
                                    'movie', 'http://dsis.ziguiw.com:80/edu_resources/js/jcplayer',
                                    'salign','TL'
                            ); //end AC code
                        }
                        <!--Settable JCPlayer  params :startPhotoSource,videoURL,loop,autoPlay,scaleMode,volume,bufferTime,backgroundColor1,movieBackgroundColor,backgroundColor2,highlightColor,inkColor,playButton,timeBar,seekBar,volumeButton,fullScreenButton,skin,smoothing,autoHide,autoHideFullScreen,offsetY,offsetYFullScreen,margins,marginsFullScreen -->
                    </script>
                    <noscript>
                        <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="465" height="295" id="jcplayer" align="TL">
                            <param name="allowScriptAccess" value="sameDomain" />
                            <param name="FlashVars" value="videoURL=http://www.ziguiw.com/${classPhotoInfo.url}&autoPlay=true&backgroundColor1=0x333333&backgroundColor2=0x222222"/>
                            <param name="allowFullScreen" value="true" />
                            <param name="salign" value="TL" />
                            <param name="scale" value="noScale" />
                            <param name="movie" value="http://dsis.ziguiw.com:80/edu_resources/js/jcplayer.swf" />
                            <param name="quality" value="high" />
                            <param name="bgcolor" value="#ffffff" />
                            <embed src="http://dsis.ziguiw.com/edu_resources/js/jcplayer.swf" FlashVars="videoURL=http://www.ziguiw.com/${classPhotoInfo.url}&autoPlay=true&backgroundColor1=0x333333&backgroundColor2=0x222222" quality="high" bgcolor="#ffffff" width="465" height="295" name="jcplayer" align="middle" salign="TL" scale="noScale" allowScriptAccess="sameDomain" allowFullScreen="true" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
                        </object>
                    </noscript>
                    <!-- player end -->

                </div>

                </div>




            </div>

            <div class="c_honor">
                <div class="h_top">
                    <div class="h_name"><img src="../images/top_img.gif" />最新视频</div>
                    <a class="more" href="<%=path%>/user/classMovie.action?class_id=${class_id}&type=1&pageSize=5">更多</a>
                </div>
                <ul class="n_ul">
                    <c:forEach items="${classMovies1.list}" var="classPhotosNew">
                        <li class="n_li"><a href="<%=path%>/user/classMovieInfo.action?id=${classPhotosNew.id}&type=${classPhotosNew.type}&class_id=${classPhotosNew.class_id}" class="n_a">${classPhotosNew.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>


            <div class="c_honor">
                <div class="h_top">
                    <div class="h_name"><img src="../images/top_img.gif" />最热视频</div>
                    <a class="more" href="<%=path%>/user/classMovie.action?class_id=${class_id}&type=1&pageSize=5">更多</a>
                </div>
                <ul class="n_ul">
                    <c:forEach items="${classMovies2.list}" var="classPhotosDt">
                        <li class="n_li"><a href="<%=path%>/user/classMovieInfo.action?id=${classPhotosDt.id}&type=${classPhotosDt.type}&class_id=${classPhotosDt.class_id}" class="n_a">${classPhotosDt.title}</a>
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
