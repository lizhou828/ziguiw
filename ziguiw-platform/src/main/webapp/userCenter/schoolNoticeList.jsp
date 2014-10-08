<%@ page import="com.zigui.domain.UserBase" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/comm/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学校公告-子贵网用户中心</title>
    <link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="../css/css1.css" type="text/css" rel="stylesheet"/>
    <link href="../css/kaoqinchaxun.css" type="text/css" rel="stylesheet"/>
    <script src="/js/basic.js.jsp"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/pageQuery.js"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
</head>
<body >

<jsp:include page="ucPublic/head.jsp"></jsp:include>
<div class="uc_midd">
    <div class="uc_l">
        <jsp:include page="dsisQueryNavigator.jsp"/>
        <jsp:include page="commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>
    </div>

    <div class="right">
        <form id="querySchoolNotice" action="/userCenter/showSchoolNotice.action" method="post">
        <div class="bobti">
            学校公告
        </div>
            <div class="xunc_1 font-2">
                <div class="xunc_1-1">
                    <table width="697" border="0" cellspacing="0" cellpadding="0">
                        <c:choose>
                        <c:when test="${ fn:length(students) > 1}">
                        <tr>
                            <td width="26%">

                                <b>请选择你的孩子：</b>
                                <c:forEach items="${students}" var="student">
                                    <input type="radio"  value="${student.xs_id}" name="studentId">
                                    ${student.xs_xming}
                                </c:forEach>
                            </td>

                            </c:when>
                            </c:choose>
                        <tr>
                            <jsp:useBean id="sTime" class="java.util.Date" />
                            <jsp:setProperty name="sTime" property="time" value="${startTime}" />
                            <jsp:useBean id="eTime" class="java.util.Date" />
                            <jsp:setProperty name="eTime" property="time" value="${endTime}" />

                            <td width="60%">
                                日期：
                                <input name="startTime" type="text" id="startDate"
                                       style="float: none" class="Wdate" onclick="WdatePicker()" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd "/>"/>
                                到
                                <input name="endTime" type="text" id="endDate"
                                       style="float: none" class="Wdate" onclick="WdatePicker()" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd "/>" />
                            </td>
                            <td width="40%">
                                <input id="attenQueryBtn" type="submit" value="查询" class="cx"/>
                            </td>

                        </tr>
                    </table>
                </div>
            </div>

        <div id="schoolNoticeList" class="not_spe">
            <ul>
                <c:forEach items="${map['data']}" var="data">
                    <li>
                        <div class="noti_content">
                            <p>${data['MSG_CONTENT']}</p>
                            <p style="margin-top:8px;">
                                ${data['PUB_DATE']}
                            </p>

                        </div>
                    </li>
                </c:forEach>

            </ul>
        </div>
        <div class="points_p">
            <input type="hidden" id="gotoPage" name="gotoPage">
            <p>
                <c:forEach items="${pageList}" var="page">
                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${page}');querySchoolNotice.submit();">${page}</a>
                </c:forEach>
                <input id=maxPage type=hidden  value="${totalPage}">
                转到 <input id ="inputPage" type="text"  size="2"/> 页
                <input onclick="changePage();" type="button" id="page" value="Go"/>
            </p>
        </div>


        <div class="uc_r">

        </div>
        </form>
    </div>

    <div class="clear"></div>

    <div class="uc_noti">
        <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>

</div>

<%@ include file="/inc/templateFoot.jsp"%>
