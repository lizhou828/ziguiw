<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学生作业——子贵网用户中心</title>
    <link href="../css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="../css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>

</head>

<body>

<jsp:include page="/userCenter/ucPublic/head.jsp"></jsp:include>

<div class="uc_midd">
    <div class="uc_l">

        <jsp:include page="/userCenter/dsisQueryNavigator.jsp"/>

        <jsp:include page="/userCenter/commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>
    </div>


    <div class="uc_right">
        <!--插入数字化校园的区域 开始-->

        <div class="right">
            <form action="/userCenter/showHomeWork.action"  id="showHomeWork" method="post">
                <div class="bobti">作业查询</div>
                <div class="xunc_1 font-2">
                    <div class="xunc_1-1">
                        <table width="700" border="0" cellspacing="0" cellpadding="0">
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

                                <td colspan="2">
                                    <%--onclick="WdatePicker()"--%>
                                    日期：<input name="startTime"   type="text" id="startDate"  style="float: none"
                                              class="Wdate" onclick="WdatePicker()" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd "/>" />
                                    到 &nbsp;&nbsp;&nbsp;
                                        <input   name="endTime" type="text"id="endDate" style="float: none"
                                                 class="Wdate" onclick="WdatePicker()" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd "/>"/>
                                </td>

                            </tr>
                            <tr>

                                <td><input  id="smsQueryBtn" value="查询" type="submit"  class="cx"/></td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="xunc_2 font-2">
                    <table width="729" border="0" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td class="t1" width="80%">作业内容</td>
                            <td class="t1" width="20%">时间</td>
                        </tr>
                        <tbody id="tblSms">
                        <c:choose>
                        <c:when test="${map['data'] ==null || fn:length(map['data'])==0}">
                            没有相关信息！
                        </c:when>
                        <c:otherwise>
                        <c:forEach items="${map['data']}" var="homeWork">
                            <tr>
                                <td style="text-align:left">
                                    ${homeWork['MSM_CONTENT']}
                                </td>
                                <td align="left">
                                ${homeWork['DISPLAY_DATE']}
                                </td>
                            </tr>
                        </c:forEach>
                        </c:otherwise>
                        </c:choose>

                        </tbody>
                    </table>

                </div>



                <div class="pageNav">
                    <%--分页开始--%>
                    <jsp:include page="/userCenter/pageQuery.jsp"/>
                    <%--分页结束--%>
                </div>
            </form>
        </div>


        <!--插入数字化校园的区域 结束-->
    </div>
    <div class="clear"></div>

    <div class="uc_noti">
        <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>

</div>

<jsp:include page="/inc/templateFoot.jsp"/>

