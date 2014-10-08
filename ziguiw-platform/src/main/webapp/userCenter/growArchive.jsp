<%@ page import="com.zigui.domain.Clasz" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.util.List" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>成长档案-子贵网用户中心</title>
    <link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="/css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script src="/js/basic.js.jsp"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/examQuery.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("input[name='studentId']").change(function(){
                window.location="/userCenter/growArchive.action?studentId="+$(this).val();
            });
        });
    </script>


</head>
<body >

<!--onload="ajaxShowHeadMsg()"-->
<jsp:include page="/userCenter/ucPublic/head.jsp"></jsp:include>
<div class="uc_midd">
    <div class="uc_l">


        <jsp:include page="dsisQueryNavigator.jsp"/>

        <jsp:include page="commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>


    </div>


    <div class="uc_right">
    <form action="/userCenter/growArchive.action" method="post" id="growArchiveForm">
            <div class="bobti">成长档案</div>
            <c:choose>
                <c:when test="${ userType==3 && fn:length(studentList) > 1}">
                    <div class="jz-i-8 font-3" style="margin-left: 20px">
                        <table width="100%" cellspacing="0" cellpadding="0" border="0">
                            <tbody>
                            <tr>
                                <td width="26%">
                                    <b>请选择你的孩子：</b>
                                </td>

                                <td>
                                    <c:forEach items="${studentList}" var="student">
                                        <input type="radio"  value="${student.xs_id}" name="studentId">
                                        ${student.xs_xming}
                                    </c:forEach>
                                </td>

                            </tr>
                            </tbody>
                        </table>
                    </div>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${userType==2}">
                     <div class="xunc_1 font-2">
                    <div class="xunc_1-1">
                        <table width="697" cellspacing="0" cellpadding="0" border="0">
                            <tbody>
                            <tr>

                                <td>
                                    班级：
                                    <%UserBase ub=(UserBase)session.getAttribute("VALID_USER");%>
                                    <s:action var="clazzList"  name="showClasses" namespace="/userCenter" executeResult="false" ignoreContextParams="false">
                                        <s:param name="teacherId"><%=ub.getForeignKey()%></s:param>
                                    </s:action>
                                    <select name="bjId" id="classId" onchange="queryStudentsByClassId(this.value);">
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
                                        <option value="${student.xs_id}">${student.xs_xming}</option>
                                    </select>
                                </td>
                                <td>
                                    <input type="submit" value="查询" class="cx" id="teaQuery"
                                           name="teaQuery">
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                </c:when>
            </c:choose>

            <div class="zzll">
                <table width="729" cellspacing="0" cellpadding="0" border="0" class="tab-xx-4 font-2">

                    <tbody>

                        <c:choose>
                            <c:when test="${growArchivess.list==null || fn:length(growArchivess.list)==0}">
                                没有相关信息！
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${growArchivess.list}" var="archive" >
                                    <tr>
                                        <td width="114" class="bod"><strong>基本信息</strong></td>
                                        <td width="615" align="right" class="bod">

                                            &nbsp;
                                        </td>
                                    </tr>

                                    <tr>
                                        <td width="114" class="xgr">学生姓名：</td>
                                        <td width="615" class="xgr">
                                                ${archive.student.xs_xming}

                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="xgr">血型：</td>
                                        <td class="xgr">
                                            <c:choose>
                                                <c:when test="${archive.bloodType==null && ''==archive.bloodType && 'null'==archive.bloodType} ">
                                                    ${archive.bloodType}
                                                </c:when>
                                                <c:otherwise>
                                                    未填写
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="xgr">爱好：</td>
                                        <td class="xgr">
                                            <c:choose>
                                                <c:when test="${archive.student.hobby==null &&  ''==archive.student.hobby && 'null'==archive.student.hobby} ">
                                                    ${archive.student.hobby}
                                                </c:when>
                                                <c:otherwise>
                                                    未填写
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="xgr">健康状态：</td>
                                        <td class="xgr">
                                            <c:choose>
                                                <c:when test="${archive.student.healthstate==null &&  ''==archive.student.healthstate &&  'null'==archive.student.healthstate} ">
                                                    ${archive.student.healthstate}
                                                </c:when>
                                                <c:otherwise>
                                                    未填写
                                                </c:otherwise>
                                            </c:choose>


                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="xgr">医保卡号：</td>
                                        <td class="xgr">
                                            <c:choose>
                                                <c:when test="${archive.student.ybHAO==null &&  ''==archive.student.ybHAO && 'null'==archive.student.ybHAO} ">
                                                    ${archive.student.ybHAO}
                                                </c:when>
                                                <c:otherwise>
                                                    未填写
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="xgr">既往史：</td>
                                        <td class="xgr">
                                            <c:choose>
                                                <c:when test="${archive.pastMedicalHistory==null &&  ''==archive.pastMedicalHistory &&  'null'==archive.pastMedicalHistory} ">
                                                    ${archive.pastMedicalHistory}
                                                </c:when>
                                                <c:otherwise>
                                                    无
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="xgr">过敏史：</td>
                                        <td class="xgr">
                                            <c:choose>
                                                <c:when test="${archive.allergicHistory==null && ''==archive.allergicHistory && 'null'==archive.allergicHistory} ">
                                                    ${archive.allergicHistory}
                                                </c:when>
                                                <c:otherwise>
                                                    无
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                    </tbody>
                </table>

            </div>
        <div class="clear"></div>


        <div class="pageNav">
            <input type="hidden" id="gotoPage" value="1"  name="gotoPage" />

            <a href="javascript:document.getElementById('gotoPage').setAttribute('value','1');growArchiveForm.submit();">首页</a>
             <c:forEach items="${pageList}" var="p">
                <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${p}');growArchiveForm.submit();">${p}</a>
             </c:forEach>
            <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${totalPage}');growArchiveForm.submit();">尾页</a>

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
    var _bjId = <%=StringUtils.trimToEmpty(request.getParameter("bjId"))%>;
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
