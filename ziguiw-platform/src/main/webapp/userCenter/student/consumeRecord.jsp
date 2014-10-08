<%@ page import="com.zigui.domain.StudentConsumeRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="com.zigui.domain.Clasz" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>一卡通消费记录——用户中心</title>
    <link href="../css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="../css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/pageQuery.js"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/examQuery.js"></script>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
       $(function(){
           $("#consRecordsForm").submit(function(){
               var bjid=document.getElementById("classId").value;
               var xsid=document.getElementById("studentId").value;
               if(bjid == '-1'){
                   alert("必须选定班级！");
                   return false;
               }
               return true;
           });
       });
    </script>
</head>

<body>
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
        <!--插入数字化校园的区域 开始-->

        <div class="right">
            <form action="/userCenter/studentConsumption.action" method="post" id="consRecordsForm">
                <div class="bobti">消费记录查询</div>
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

                                <td>日期：
                                    <input name="startTime" type="text" id="startDate"  style="float: none; width: 105px"
                                           class="Wdate" onclick="WdatePicker()" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd "/>"/>
                                    &nbsp;&nbsp;&nbsp;到&nbsp;&nbsp;&nbsp;
                                    <input name="endTime" type="text" id="endDate" style="float: none; width: 105px"
                                           class="Wdate" onclick="WdatePicker()" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd "/>"/>
                                </td>
                            </tr>

                            <tr>
                                <c:if test="${user.type==2}">
                                   <td>
                                       班级：
                                       <%UserBase ub=(UserBase)session.getAttribute("VALID_USER");%>
                                       <s:action var="clazzList"  name="showClasses" namespace="/userCenter" executeResult="false" ignoreContextParams="false">
                                           <s:param name="teacherId"><%=ub.getForeignKey()%></s:param>
                                       </s:action>
                                       <select name="bjid" id="classId" onchange="queryStudentsByClassId(this.value);">
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

                                       &nbsp;&nbsp;&nbsp;

                                        学生：
                                        <select name="studentId" id="studentId">
                                        </select>
                                    </td>
                                </c:if>
                                <td><input  id="consRecordsQueryBtn"  type="submit"  class="cx" value="查  询"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="xunc_2 font-2">
                    <h1 class="font-3">（ -- ）您的消费记录如下</h1>
                    <table width="729" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="t1">流水号</td>
                            <c:if test="${user.type==2}">
                                <td class="t1">学生</td>
                            </c:if>
                            <td class="t1">卡号</td>
                            <td class="t1">类型</td>
                            <td class="t1">消费时间</td>
                            <td class="t1">消费金额(元)</td>
                            <td class="t1">余额(元)</td>
                        </tr>
                        <tbody id="tblAttendance">
                        <c:forEach items="${map['data']}" var="data">
                             <tr>
                                 <td>${data['RECORD_ID']}</td>
                                 <c:if test="${user.type==2 && studentId <= 0 }">
                                     <td >${data['XS_XMING']}</td>
                                 </c:if>
                                 <c:if test="${user.type==2 && studentId > 0 }">
                                     <td >${map['XS_XMING']}</td>
                                 </c:if>
                                 <td >${data['CARD_NUMBER']}</td>
                                 <td >
                                     <c:choose>
                                         <c:when test="${data['CONS_STATE']=='0'}">
                                             消费
                                         </c:when>
                                         <c:when test="${data['CONS_STATE']=='1'}">
                                             充值
                                         </c:when>
                                         <c:when test="${data['CONS_STATE']=='2'}">
                                             取款
                                         </c:when>
                                         <c:when test="${data['CONS_STATE']=='3'}">
                                             补助
                                         </c:when>
                                         <c:otherwise>
                                              <span style="color: red">非法退出</span>
                                         </c:otherwise>
                                     </c:choose>
                                 </td>
                                 <td >
                                   <jsp:useBean id="consTime" class="java.util.Date" />
                                   <jsp:setProperty name="consTime" property="time" value="${data['CONS_TIME']}" />
                                   <fmt:formatDate value="${consTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                 </td>
                                 <td >${data['CONS_FARE']}(元)</td>
                                 <td >${data['X_MONEY']}(元)</td>
                             </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <div class="pageNav">
                    <%--分页开始--%>
                        <jsp:include page="/userCenter/pageQuery.jsp"></jsp:include>
                    <%--分页结束--%>
                </div>
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
    <%if (request.getMethod().equalsIgnoreCase("POST")) {
        %>
<script type="text/javascript">
    var _bjId = <%=StringUtils.trimToEmpty(request.getParameter("bjid"))%>;
    var _xsId = <%=StringUtils.trimToEmpty(request.getParameter("studentId"))%>;
    var classSelectOptions = document.getElementById("classId").options;
    for(var i=0;i<classSelectOptions.length;i++){
        if(_bjId && _bjId != ''&& _bjId ==classSelectOptions[i].value){
            classSelectOptions[i].selected=true;
            queryStudentsByClassId(_bjId);
            _bjId='';
        }
    }

</script>
    <%
        }
    %>
<jsp:include page="/inc/templateFoot.jsp"/>

