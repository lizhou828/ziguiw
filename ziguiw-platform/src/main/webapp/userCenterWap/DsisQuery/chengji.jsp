<%@ page import="com.zigui.domain.Clasz" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/comm/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生成绩查询</title>
    <link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/examQuery.js"></script>
    <script type="text/javascript">
        function aa(userType){
            if(userType == 3 || userType == 4 ){
                var bjId=document.getElementById("classId").value;
                if(bjId !=''|| bjId != null){
                    queryTerm(bjId);
                }
            }
        }
    </script>
    <script type="text/javascript">
        $(function(){
            $("#chengjiForm").submit(function(){
                var bjid=document.getElementById("classId").value;
                var xsid=document.getElementById("studentId").value;
                if(bjid=='-1' && xsid=='-1'){
                    alert("必须选定班级！");
                    return false;
                }
                return true;
            });
        });
    </script>

</head>

<body onload="aa(${user.type});">
    <div class="header">
        <p class="nav_title">数字化校园平台查询</p>
    </div>
    <div class="location">
        <jsp:include page="../location.jsp"></jsp:include>
    </div>
    <div class="c_name pl10">
        <jsp:include page="../userInfo.jsp"></jsp:include>
    </div>
    
    
    <div class="bobti">
        成绩查询<span style="color:#2d2d2d;font-family:Arial, Helvetica, sans-serif;font-weight:normal;font-size:12px;"></span>

    </div>
    
    <div class="xunc_1-1">
        <form action="/userCenterWap/examList.action" id="chengjiForm" method="post">
           <jsp:include page="../chooseChild.jsp"/>
          <table width="100%" cellspacing="0" cellpadding="0" border="0">
            <tbody>
             <c:choose>
                 <c:when test="${user.type==2}">
                     <tr>
                         <%UserBase ub=(UserBase)session.getAttribute("VALID_USER");%>
                         <s:action var="clazzList"  name="showClasses" namespace="/userCenter" executeResult="false" ignoreContextParams="false">
                             <s:param name="teacherId"><%=ub.getForeignKey()%></s:param>
                         </s:action>
                         <td >&nbsp;班&nbsp;&nbsp;&nbsp;&nbsp;级：
                             <select id="bjid" name="classId" onchange="queryTerm(this.value);">
                                 <option value="-1">--请选择--</option>
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

                     </tr>
                 </c:when>
             </c:choose>
             <tr>
                 <input type="hidden" id="classId" value="${requestScope.student.bj_ID}"/>
                 <td >&nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;期：
                     <select name="termId" id="termId" onchange="queryExam(this.value);">
                         <c:choose>
                             <c:when test="${termList==null || fn:length(termList) == 0}">
                                 <option value="-1">--无--</option>
                             </c:when>
                             <c:otherwise>
                                 <option value="-1">--请选择--</option>
                                 <c:forEach items="${termList}" var="term">
                                     <option value="${term.termId}">${term.termName}</option>
                                 </c:forEach>
                             </c:otherwise>
                         </c:choose>

                     </select>
                 </td>
             </tr>

             <tr>
                 <td>&nbsp;考试名称：
                     <select name="examId" id="examId">
                         <option value="-1">--请选择--</option>
                     </select>
                 </td>
             </tr>
             <tr>
                  <td>&nbsp;<input type="submit" class="cx" value="查询" id="examQueryBtn" name="examQueryBtn"></td>
                  <td></td>
              </tr>
          </tbody>
          </table>
        </form>
     </div>
    <div class="score_con">
        ${examMap['examName']}
        <c:choose>
            <c:when test="${user.type==2}">

            <c:forEach items="${examResultList}" var="exam">
                <table width="100%" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <th width="50%">学生姓名</th>
                        <td width="50%">${exam.xs_xming}</td>
                    </tr>
                    <c:forEach items="${exam.subjectList}" var="subject">
                        <tr>
                            <th>${subject.subjectName}</th>
                            <td>${subject.subjectScore}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th>总分</th>
                        <td>${exam.totalScore}</td>
                    </tr>

                </table>
                </br>
            </c:forEach>
            </c:when>
            <c:otherwise>
                 <table width="100%" cellpadding="0" cellspacing="0" border="0">
                     <tr>
                         <th width="50%">学生姓名</th>
                         <td width="50%">${student.xs_xming}</td>
                     </tr>
                     <c:forEach items="${studentExamList}" var="subject">
                     <tr>
                         <th>${subject.subjectName}</th>
                         <td>${subject.subjectScore}</td>
                     </tr>
                     </c:forEach>
                     <tr>
                         <th>总分</th>
                         <td>${totalScore}</td>
                     </tr>
            </c:otherwise>
        </c:choose>
    </div>


    <input type="hidden" value="1" name="gotoPage" id="gotoPage">

    <div class="pageNav">

    </div>


</body>
</html>
