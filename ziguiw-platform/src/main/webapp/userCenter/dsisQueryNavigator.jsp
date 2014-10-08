<%@ page import="com.zigui.domain.UserBase" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path=request.getContextPath();
    UserBase userBase=(UserBase)session.getAttribute("VALID_USER");
%>
<script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="/userCenter/ucPublic/checkBind.js"></script>
<div class="uc_num_f">
    <div class="num_f_title">数字化校园平台查询</div>
    <ul>
        <li><div class="note"><img src="ucimages/xueji.gif"/></div>
            <a  href="javascript:checkBind('/userCenter/showXueJi.action')"><span>学生学籍档案</span></a>
        </li>
        <li><div class="note"><img src="ucimages/kaoq.gif"/></div>
            <a  href="javascript:checkBind('/userCenter/showAttendance.action')" target="_self">学生考勤</a>
        </li>
        <li><div class="note"><img src="ucimages/zuoy.gif"/></div>
            <a href="javascript:checkBind('/userCenter/showHomeWork.action')">学生作业</a>
        </li>
        <li><div class="note"><img src="ucimages/score.gif"/></div>
            <a href="javascript:checkBind('/userCenter/examList.action')">学生成绩</a>
        </li>
        <li><div class="note"><img src="ucimages/dang.gif"/></div>
            <a href="javascript:checkBind('/userCenter/growArchive.action')">成长档案</a>
        </li>
        <li><div class="note"><img src="ucimages/zuji.gif"/></div>
            <a href="/userCenter/growFootprint.jsp">成长足迹</a>
        </li>

        <li><div class="note"><img src="ucimages/gongg.gif"/></div>
            <a href="javascript:checkBind('/userCenter/showSchoolNotice.action')">学校公告</a>
        </li>
        <li><div class="note"><img src="ucimages/tongz.gif"/></div>
            <a href="javascript:checkBind('/userCenter/classNoticeList.action')">班级通知</a>
        </li>
        <li><div class="note"><img src="ucimages/pingy.gif"/></div>
            <a href="javascript:checkBind('/userCenter/studentCommentList.action')">学生评语</a>
        </li>
        <li><div class="note"><img src="ucimages/zhann.gif"/></div>
            <a href="javascript:checkBind('/userCenter/innerLetterList.action')">我的站内信</a>
        </li>

        <li><div class="note"><img src="ucimages/xiaof.gif"/></div>
            <a href="javascript:checkBind('/userCenter/studentConsumption.action')">一卡通消费查询</a>
        </li>

    </ul>
</div>