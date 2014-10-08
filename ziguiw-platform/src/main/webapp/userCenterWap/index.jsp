<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
<script type="text/jscript" src="/userCenterWap/js/jquery.js"></script>
<script type="text/jscript" src="/userCenterWap/js/mouse.js"></script>
<title>子贵网_手机版_首页 </title>
</head>

<body>
<div class="header">
	<p class="nav_title">数字化校园平台查询</p>
</div>

<div class="location">
	<jsp:include page="location.jsp"></jsp:include>
</div>

<div class="c_name pl10">
    <jsp:include page="userInfo.jsp"></jsp:include>
</div>

<div class="middle">
	<div class="nav">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">

            <tr onmouseout="out(this)" onmouseover="over(this);">
                <td width="25%" class="nav_img"><img src="/userCenterWap/images/my-acc.png"/></td>
                <td width="65%">
                    <a target="_self" href="/userCenterWap/showXueJi.action">学生学籍档案</a>
                </td>
                <td width="10%"><img src="/userCenterWap/images/tri.png" /></td>
            </tr>

            <tr onmouseout="out(this)" onmouseover="over(this);">
                <td width="25%" class="nav_img"><img src="/userCenterWap/images/time.png"/></td>
                <td width="65%">
                  <a target="_self" href="/userCenterWap/showAttendance.action" > 学生考勤</a>
                </td>
                <td width="10%"><img src="/userCenterWap/images/tri.png" /></td>
            </tr>
            <tr onmouseout="out(this)" onmouseover="over(this);">
                <td width="25%" class="nav_img"><img src="/userCenterWap/images/h_word.png"/></td>
                <td width="65%">
                  <a target="_self" href="/userCenterWap/showHomeWork.action">学生作业</a>
                </td>
                <td width="10%"><img src="/userCenterWap/images/tri.png" /></td>
            </tr>
            <tr onmouseout="out(this)" onmouseover="over(this);">
                <td width="25%" class="nav_img"><img src="/userCenterWap/images/feed.png"/></td>
                <td width="65%">
                   <a target="_self" href="/userCenterWap/examList.action">学生成绩</a>
                </td>
                <td width="10%"><img src="/userCenterWap/images/tri.png" /></td>
            </tr>
            <tr onmouseout="out(this)" onmouseover="over(this);">
                <td width="25%" class="nav_img"><img src="/userCenterWap/images/c_note.png"/></td>
                <td width="65%">
                   <a target="_self" href="/userCenterWap/showSchoolNotice.action"> 学校公告</a>
                </td>
                <td width="10%"><img src="/userCenterWap/images/tri.png" /></td>
            </tr>
            <tr onmouseout="out(this)" onmouseover="over(this);">
                <td width="25%" class="nav_img"><img src="/userCenterWap/images/consulting.png"/></td>
                <td width="65%">
                  <a target="_self" href="/userCenterWap/classNoticeList.action">班级通知</a>
                </td>
                <td width="10%"><img src="/userCenterWap/images/tri.png" /></td>
            </tr>
            <tr onmouseout="out(this)" onmouseover="over(this);">
                <td width="25%" class="nav_img"><img src="/userCenterWap/images/zoom.png"/></td>
                <td width="65%">
                    <a target="_self" href="/userCenterWap/studentCommentList.action">学生评语</a>
                </td>
                <td width="10%"><img src="/userCenterWap/images/tri.png" /></td>
            </tr>
            <tr onmouseout="out(this)" onmouseover="over(this);">
                <td width="25%" class="nav_img"><img src="/userCenterWap/images/my-acc.png"/></td>
                <td width="65%">
                  <a target="_self" href="/userCenterWap/studentConsumption.action">一卡通消费查询</a>
                </td>
                <td width="10%"><img src="/userCenterWap/images/tri.png" /></td>
            </tr>
    	</table>
    </div>
</div>


</body>
</html>
