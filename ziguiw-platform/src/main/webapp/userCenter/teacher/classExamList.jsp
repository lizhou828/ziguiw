<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="com.zigui.domain.Clasz" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>班级成绩查询——用户中心</title>
    <link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="/css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/examQuery.js"></script>
    <script type="text/javascript">
        $(function(){
           $("#teaExamForm").submit(function(){
               var bjid=document.getElementById("classId").value;
               var termId=document.getElementById("termId").value;
               var examId=document.getElementById("examId").value;
               if(bjid == '-1'){
                   alert("必须选定班级！");
                   return false;
               }else if(termId == '-1'){
                   alert("必须选定学期！");
                   return false;
               }else if(examId == '-1'){
                   alert("必须选定考试名称！");
                   return false;
               }else{
                   return true;
               }
           });
        });

    </script>
</head>

<body >
<jsp:include page="/userCenter/ucPublic/head.jsp"></jsp:include>
<div class="clear"></div>

<div class="uc_midd">
    <div class="uc_l">
        <jsp:include page="/userCenter/dsisQueryNavigator.jsp"/>
        <jsp:include page="/userCenter/commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>
    </div>
    <div class="uc_right">
        <% UserBase user=(UserBase)session.getAttribute("VALID_USER");%>
        <div>
            <form action="/userCenter/examList.action" method="post" id="teaExamForm">
                <div class="bobti">成绩查询</div>
                <div class="xunc_1 font-2">
                    <div class="xunc_1-1">
                        <table width="700" border="0" cellspacing="0" cellpadding="0">
                            <tr>

                                <s:action var="clazzList"  name="showClasses" namespace="/userCenter" executeResult="false" ignoreContextParams="false">
                                    <s:param name="teacherId"><%=user.getForeignKey()%></s:param>
                                </s:action>
                                <td>班级：
                                    <select name="bjid" id="classId" onchange="queryTerm(this.value);">
                                        <option value="-1" selected="selected">--请选择--</option>
                                        <%
                                            List<Clasz> claszList = (List<Clasz>) request.getAttribute("clazzList");
                                            for (Clasz clasz : claszList) {
                                        %>
                                        <option value="<%=clasz.getBj_id()%>"><%=clasz.getBj_mcheng()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </td>
                                <td>学期：
                                    <select name="termId" id="termId" onchange="queryExam(this.value);">
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    考试名称：
                                    <select name="examId" id="examId">
                                    </select>
                                </td>
                                <td><input  id="examQueryBtn" type="submit"  value="查询" class="cx"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="xunc_2 font-2">
                    <table width="729" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                        <td class="t1">考试名称</td>
                        <td class="t1">学生姓名</td>
                        <td class="t1">总分</td>
                        </tr>
                         <%--<c:if test="${examResultList.size==0}">--%>
                             <%--<tr><td align="center" colspan="3">没有记录！</td></tr>--%>
                         <%--</c:if>--%>
                         <%--<c:if test="${examResultList.size > 0}">--%>
                             <s:iterator value="#request.examResultList" var="examStringList">

                                     <tr>
                                         <s:iterator value="#examStringList" var="string">
                                         <td >
                                             <s:property value="#string"/>
                                         </td>
                                         </s:iterator>
                                     </tr>

                             </s:iterator>

                         <%--</c:if>--%>


                    </table>
                    <input type="hidden" id="gotoPage"  name="gotoPage" value="1" />
                    <div class="pageNav">
                        <%--分页开始--%>


                            <a href="javascript:document.getElementById('gotoPage').setAttribute('value','1');teaExamForm.submit();">首页</a>
                            <c:choose>
                                <c:when test="${gotoPage <= 1}">
                                    <span class="na">&lt;上一页</span>
                                </c:when>
                                <c:otherwise>
                                    <a class="f12" href="javascript:document.getElementById('gotoPage').setAttribute('value','${gotoPage-1}');teaExamForm.submit(); ">&lt;上一页</a>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${totalPages >= 9}">
                                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','1');teaExamForm.submit();">1</a>
                                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','2');teaExamForm.submit();">2</a>
                                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','3');teaExamForm.submit();">3</a>
                                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','4');teaExamForm.submit();">4</a>
                                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','5');teaExamForm.submit();">5</a>
                                    <span>...</span>
                                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${totalPages-2}');teaExamForm.submit();">${totalPages-2}</a>
                                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${totalPages-1}');teaExamForm.submit();">${totalPages-1}</a>
                                    <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${totalPages}');teaExamForm.submit();">${totalPages}</a>

                                </c:when>
                                <c:otherwise>
                                    <s:iterator value="#request.pageList"  var="page">
                                        <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${page}');teaExamForm.submit();">${page}</a>
                                    </s:iterator>

                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${gotoPage < totalPages}">
                                    <a class="f12" href="javascript:document.getElementById('gotoPage').setAttribute('value','${gotoPage+1}');teaExamForm.submit(); ">下一页&gt;</a>
                                </c:when>
                                <c:otherwise>
                                    <span class="na">下一页&gt;</span>

                                </c:otherwise>
                            </c:choose>
                            <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${totalPages}');teaExamForm.submit();">尾页</a>

                        <%--分页结束--%>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
    <div class="uc_noti">
        <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>
</div>
    <%if (request.getMethod().equalsIgnoreCase("POST")) {
        %>
<script type="text/javascript">
    var _bjId = <%=StringUtils.trimToEmpty(request.getParameter("bjid"))%>;
    var _xqId = <%=StringUtils.trimToEmpty(request.getParameter("termId"))%>;
    var _ksId =  <%=StringUtils.trimToEmpty(request.getParameter("examId"))%>;
    var classSelectOptions = document.getElementById("classId").options;
    var termSelectOptions = document.getElementById("termId").options;
    for(var i=0;i<classSelectOptions.length;i++){
        if(_bjId && _bjId != ''&& _bjId ==classSelectOptions[i].value){
            classSelectOptions[i].selected=true;
            queryTerm(_bjId);
            _bjId='';
        }
    }
    for(var i=0;i<termSelectOptions.length;i++){
        if(_xqId && _xqId != ''&& _xqId ==termSelectOptions[i].value){
            termSelectOptions[i].selected=true;
            queryExam(_xqId);
            _xqId='';
        }
    }


</script>
    <%
    }%>
<jsp:include page="/inc/templateFoot.jsp"/>
