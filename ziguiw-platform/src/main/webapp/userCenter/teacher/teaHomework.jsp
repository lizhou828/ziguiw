<%@ page import="java.util.List" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="com.zigui.domain.Clasz" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>班级作业查询——用户中心</title>
    <link href="../css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="../css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#homeWordQueryForm").submit(function(){
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
        <div class="right">
            <% UserBase user=(UserBase)session.getAttribute("VALID_USER");%>
            <div class="bobti">作业查询</div>
            <form action="/userCenter/showHomeWork.action"  id="homeWordQueryForm" method="post">
                <div class="xunc_1 font-2">
                    <div class="xunc_1-1">
                        <table width="700" border="0" cellspacing="0" cellpadding="0">
                                <jsp:useBean id="sTime" class="java.util.Date" />
                                <jsp:setProperty name="sTime" property="time" value="${startTime}" />
                                <jsp:useBean id="eTime" class="java.util.Date" />
                                <jsp:setProperty name="eTime" property="time" value="${endTime}" />

                            <tr>
                                <td width="60%">日期：
                                    <input name="startTime" type="text" id="begindate"  style="float: none"
                                           class="Wdate"  onclick="WdatePicker()" value="<fmt:formatDate value="${sTime}" pattern="yyyy-MM-dd "/>"/>
                                    到
                                    <input name="endTime" type="text" id="enddate" style="float: none"
                                           class="Wdate"  onclick="WdatePicker()" value="<fmt:formatDate value="${eTime}" pattern="yyyy-MM-dd "/>"/>
                                </td>

                            </tr>
                            <tr>
                                <td>班级：
                                    <%UserBase ub=(UserBase)session.getAttribute("VALID_USER");%>
                                    <s:action var="clazzList"  name="showClasses" namespace="/userCenter" executeResult="false" ignoreContextParams="false">
                                        <s:param name="teacherId"><%=ub.getForeignKey()%></s:param>
                                    </s:action>
                                    <select name="classId" id="classId">
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

                                <td>
                                 <input  id="hmQueryBtn" type="submit" value="查询" class="cx"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>


                <div class="xunc_2 font-2">
                    <table width="729" border="0" cellspacing="0" cellpadding="0" >
                        <tr>
                            <td class="t1" width="12%">学生姓名</td>
                            <td class="t1" width="61%">信息内容</td>
                            <td class="t1" width="15%">发送时间</td>
                        </tr>
                        <tbody id="tblSms">
                            <c:choose>
                                <c:when test="${map['data'] ==null || fn:length(map['data'])==0}">
                                    没有相关信息！
                                </c:when>
                                <c:otherwise>
                                   <c:forEach items="${map['data']}" var="data">
                                       <tr>
                                           <td>
                                               ${data['XS_XMING']}
                                           </td>
                                           <td style="text-align:left">
                                              ${data['MSM_CONTENT']}
                                           </td>
                                           <td>
                                             ${data['DISPLAY_DATE']}
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
    var classSelectOptions = document.getElementById("classId").options;
    for(var i=0;i<classSelectOptions.length;i++){
        if(_bjId && _bjId != ''&& _bjId ==classSelectOptions[i].value){
            classSelectOptions[i].selected=true;
            _bjId='';
        }
    }
</script>
    <%
    }
    %>

<jsp:include page="/inc/templateFoot.jsp"/>

