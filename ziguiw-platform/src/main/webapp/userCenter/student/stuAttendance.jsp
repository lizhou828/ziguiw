<%@ taglib prefix="t" uri="/ttxs-tags" %>
<%@include file="/comm/common.jsp" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>学生考勤——用户中心</title>
    <link href="../css/uccss.css" type="text/css" rel="stylesheet"/>
    <link href="../css/css1.css" type="text/css" rel="stylesheet"/>
    <link href="../css/kaoqinchaxun.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" defer="defer"></script>

</head>

<body>

<jsp:include page="/userCenter/ucPublic/head.jsp"></jsp:include>

<div class="uc_midd">
    <div class="uc_l">
        <jsp:include page="/userCenter/dsisQueryNavigator.jsp"/>

        <jsp:include page="/userCenter/commonQuestion.jsp">
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>


    </div>


    <div class="right">
        <form action="/userCenter/showAttendance.action"  id="attenQueryForm" method="post">
            <div class="bobti">
                考勤查询<span style="color:#2d2d2d;font-family:Arial, Helvetica, sans-serif;font-weight:normal;font-size:12px;">（以下考勤信息仅供参考）</span>
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

                        </tr>
                        <tr>

                            <td width="40%">
                                <input id="attenQueryBtn" type="submit" value="查询" class="cx"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="chax-1 font-2">
                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                       class="kaoqt">
                    <tr>
                        <td class="kaoq-td-1 t1 font-2" width="20%">日期</td>
                        <td width="80%">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                   class="kaoqt-1">
                                <tr>
                                    <td width="29%" class="t1 font-2">读卡时间 </td>
                                    <td width="44%" class="t1 font-2">考勤结果</td>
                                    <td width="27%" class="t1 font-2"> 状态</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>


                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                       class="kaoq">
                        <c:forEach items="${map['data']}" var="data">
                            <tr>
                                <td width="20%" align="center" class="kaoq-td-1">
                                    <jsp:useBean id="dataTime" class="java.util.Date" />
                                    <jsp:setProperty name="dataTime" property="time" value="${data['READCARD_TIME']}" />
                                    <fmt:formatDate value="${dataTime}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td width="80%">
                                    <table width="100%" border="0" cellspacing="0"
                                           cellpadding="0" class="kaoq-1">
                                        <tr>
                                            <td width="29%">
                                                <jsp:useBean id="readcardTime" class="java.util.Date" />
                                                <jsp:setProperty name="readcardTime" property="time" value="${data['READCARD_TIME']}" />
                                                <fmt:formatDate value="${readcardTime}" pattern="HH:mm:ss"/>
                                            </td>
                                            <td width="44%">${data['RESULT']}</td>

                                            <td width="27%">
                                                <c:if test="${data['IN_OUT_STATE'] eq '0'}">
                                                    <font color="red">离校</font>
                                                </c:if>
                                                <c:if test="${data['IN_OUT_STATE'] eq '1'}">
                                                    <font color="green">进校</font>
                                                </c:if>
                                            </td>
                                        </tr>

                                    </table>
                                </td>
                            </tr>
                        </c:forEach>

                </table>

            </div>

            <input type="hidden" id="gotoPage" name="gotoPage" value="1"/>

            <div class="pageNav">
                <%--分页开始--%>

                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','1');attenQueryForm.submit();">首页</a>
                    <c:choose>
                        <c:when test="${gotoPage <= 1}">
                            <span class="na">&lt;上一页</span>
                        </c:when>
                        <c:otherwise>
                            <a class="f12" href="javascript:document.getElementById('gotoPage').setAttribute('value','${gotoPage-1}');attenQueryForm.submit(); ">&lt;上一页</a>
                        </c:otherwise>
                    </c:choose>


                    <c:forEach items="${pageList}"  var="page">
                        <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${page}');attenQueryForm.submit();">${page}</a>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${gotoPage < totalPages}">
                            <a class="f12" href="javascript:document.getElementById('gotoPage').setAttribute('value','${gotoPage+1}');attenQueryForm.submit(); ">下一页&gt;</a>
                        </c:when>
                        <c:otherwise>
                            <span class="na">下一页&gt;</span>

                        </c:otherwise>
                    </c:choose>
                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${totalPages}');attenQueryForm.submit();">尾页</a>

                <%--分页结束--%>
            </div>
        </form>

    </div>
    <div class="clear"></div>
</div>

<div class="clear"></div>

<div class="uc_noti">
    <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
</div>



<jsp:include page="/inc/templateFoot.jsp"/>



