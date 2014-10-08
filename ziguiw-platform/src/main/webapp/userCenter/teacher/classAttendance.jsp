<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="java.util.*" %>
<%@ page import="com.zigui.domain.Clasz" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.zigui.service.impl.StudentServiceImpl" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>班级考勤查询——用户中心</title>
    <link href="../css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="../css/css1.css" type="text/css" rel="stylesheet" />
    <link href="../css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/examQuery.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#teaAttenForm").submit(function(){
                var bjid=document.getElementById("classId").value;
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

<div class="uc_midd">
    <div class="uc_l">

        <jsp:include page="/userCenter/dsisQueryNavigator.jsp"/>

        <jsp:include page="/userCenter/commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>
    </div>


    <div class="right">
        <form action="/userCenter/showAttendance.action"  id="teaAttenForm" method="post">
            <div class="bobti">


                考勤查询<span style="color:#2d2d2d;font-family:Arial, Helvetica, sans-serif;font-weight:normal;font-size:12px;">（以下考勤信息仅供参考）</span>

            </div>
            <%
                UserBase user=(UserBase)session.getAttribute("VALID_USER");

            %>

            <div class="xunc_1 font-2">
                <div class="xunc_1-1">
                    <table width="697" border="0" cellspacing="0" cellpadding="0">
                        <jsp:useBean id="sTime" class="java.util.Date" />
                        <jsp:setProperty name="sTime" property="time" value="${startTime}" />
                        <jsp:useBean id="eTime" class="java.util.Date" />
                        <jsp:setProperty name="eTime" property="time" value="${endTime}" />
                        <tr>
                            <td colspan="3">
                                日期：
                                <input name="startTime" type="text" id="startTime"  style="float: none"
                                       class="Wdate"  onclick="WdatePicker()" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd "/>"/>
                                到
                                <input name="endTime" type="text"   id="endTime" style="float: none"
                                       class="Wdate"  onclick="WdatePicker()"value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd "/>"/>

                            </td>

                            <td></td>
                        </tr>
                        <tr>

                            <td>
                                班级：
                                <%UserBase ub=(UserBase)session.getAttribute("VALID_USER");%>
                                <s:action var="clazzList"  name="showClasses" namespace="/userCenter" executeResult="false" ignoreContextParams="false">
                                    <s:param name="teacherId"><%=ub.getForeignKey()%></s:param>
                                </s:action>
                                <select name="classId" id="classId"  onchange="queryStudentsByClassId(this.value);">
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
                                学生：
                                <select name="studentId" id="studentId">
                                </select>
                            </td>

                            <td>
                                <input  id="attenQueryBtn" type="submit" value="查询" class="cx" />
                            </td>
                        </tr>

                    </table>
                </div>
            </div>



            <div class="chax-1 font-2">
                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                       class="kaoqt">
                    <tr>
                        <td class="kaoq-td-1 t1 font-2" width="20%">日期
                        </td>
                        <td width="80%">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0"
                                   class="kaoqt-1">
                                <tr>
                                    <td width="20%" class="t1 font-2">
                                        姓名
                                    </td>
                                    <td width="30%" class="t1 font-2">
                                        读卡时间
                                    </td>
                                    <td width="30%" class="t1 font-2">
                                        考勤结果
                                    </td>
                                    <td width="20%" class="t1 font-2">
                                        状态
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>


                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                       class="kaoq">
                         <c:forEach items="${map['data']}" var="data">
                             <tr>
                                 <td  width="20%" align="center" class="kaoq-td-1">
                                     <jsp:useBean id="dataTime" class="java.util.Date" />
                                     <jsp:setProperty name="dataTime" property="time" value="${data['READCARD_TIME']}" />
                                     <fmt:formatDate value="${dataTime}" pattern="yyyy-MM-dd"/>
                                 </td>
                                 <td width="80%">
                                     <table width="100%" border="0" cellspacing="0"cellpadding="0" class="kaoq-1">
                                         <tr>
                                             <td width="20%">
                                                 <c:if test="${user.type==2 && studentId <= 0 }">
                                               ${data['XS_XMING']}
                                             </c:if>
                                             <c:if test="${user.type==2 && studentId > 0 }">
                                                ${map['XS_XMING']}
                                             </c:if>
                                             </td>
                                             <td width="30%">
                                                 <jsp:useBean id="readcardTime" class="java.util.Date" />
                                                 <jsp:setProperty name="readcardTime" property="time" value="${data['READCARD_TIME']}" />
                                                 <fmt:formatDate value="${readcardTime}" pattern="HH:mm:ss"/>
                                             </td>
                                             <td width="30%">${data['RESULT']}</td>
                                             <td width="20%">
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

            <div class="pageNav">
               <%--分页开始 --%>
                 <jsp:include page="/userCenter/pageQuery.jsp"></jsp:include>
                <%--分页结束--%>
            </div>
        </form>
    </div>

    <div class="clear"></div>

    <div class="uc_noti">
        <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>

</div>

    <%if (request.getMethod().equalsIgnoreCase("POST")) {
        %>
<script type="text/javascript">
    var _bjId = <%=StringUtils.trimToEmpty(request.getParameter("classId"))%>;
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

