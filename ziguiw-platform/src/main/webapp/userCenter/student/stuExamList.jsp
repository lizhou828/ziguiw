<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>成绩查询——用户中心</title>
    <link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="/css/css1.css" type="text/css" rel="stylesheet" />
    <link href="/css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/examQuery.js"></script>
    <script type="text/javascript">
               function aa(){
                   var bjId=document.getElementById("classId").value;
                   if(bjId !=''|| bjId != null){
                       queryTerm(bjId);
                   }
               }

    </script>
</head>

<body onload="aa();">
<jsp:include page="/userCenter/ucPublic/head.jsp"></jsp:include>


<div class="uc_midd">
    <div class="uc_l">
        <jsp:include page="/userCenter/dsisQueryNavigator.jsp"/>

        <jsp:include page="/userCenter/commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>
    </div>



    <div class="right">
        <form action="/userCenter/examList.action"  id="stuQueryForm" method="post">
            <input type="hidden" id="displayType"value="1" />
            <div class="bobti">成绩查询</div>
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

                            <input type="hidden" id="classId" value="${requestScope.student.bj_ID}"/>
                            <td>学期：
                                <select name="termId" id="termId" onchange="queryExam(this.value);">
                                        <option value=" "></option>
                                </select>
                            </td>
                            <td>考试名称：
                                <select name="examId" id="examId">
                                </select>
                            </td>
                            <td><input   id="parQueryBtn" type="submit"  value="查询" class="cx"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
        <div class="xunc_2 font-2 stuscore">
            <table width="729" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th>考试名称</th>
                    <th>时间</th>
                     <c:forEach items="${subjectList}" var="subject">
                        <th>${subject.SUBJECT_NAME}</th>
                     </c:forEach>
                    <th>总分</th>


                </tr>

                <tr>
                    <td>${exam.EXAM_NAME}</td>
                    <td>${exam.CREATE_EXAM_DATE}</td>
                    <c:forEach items="${examList}" var="score" >
                    <td>${score}</td>
                    </c:forEach>
                    <td>${totalScore}</td>

                </tr>

            </table>
        </div>

        <!--分数曲线图开始-->
        <div class="score_img">
            <img src="/DisplayChart.jsp?filename=${graphURL}" width=500 height=300 border=0/>
        </div>
        <!--分数曲线图结束-->

    </div>

    <div class="clear"></div>

    <div class="uc_noti">
        <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>

</div>

<%if (request.getMethod().equalsIgnoreCase("POST")) {
%>
<script type="text/javascript">

    var _xqId = <%=StringUtils.trimToEmpty(request.getParameter("termId"))%>;
    var _ksId =  <%=StringUtils.trimToEmpty(request.getParameter("examId"))%>;
    var _bjId=document.getElementById("classId").value;
    queryTerm(_bjId);
</script>
<%
    }%>
<jsp:include page="/inc/templateFoot.jsp"/>

