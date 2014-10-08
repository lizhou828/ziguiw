<%@ page import="com.zigui.domain.Clasz" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学籍档案列表查询——用户中心</title>
    <link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="/css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/userCenter/ucPublic/chooseStudent.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#getstudentlist").submit(function(){
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
        <jsp:include page="../dsisQueryNavigator.jsp"/>

        <jsp:include page="../commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>
    </div>


    <div class="uc_right">
        <!--插入数字化校园的区域 开始-->

        <% UserBase user=(UserBase)session.getAttribute("VALID_USER");%>
        <div>

            <div class="right">
                <form  id="getstudentlist" action="/userCenter/queryStudentsXueJiByClassId.action" method="post">
                    <div class="bobti font-5">学生学籍档案列表</div>
                    <div class="xunc_1 font-2">
                        <div class="xunc_1-1">

                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td>
                                        班级：
                                        <%UserBase ub=(UserBase)session.getAttribute("VALID_USER");%>
                                        <s:action var="clazzList"  name="showClasses" namespace="/userCenter" executeResult="false" ignoreContextParams="false">
                                            <s:param name="teacherId"><%=ub.getForeignKey()%></s:param>
                                        </s:action>

                                        <select  name="classId" id="classId">
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
                                        <input id="studentlist" type="submit"  value="查询" class="cx" />
                                    </td>
                                </tr>
                            </table>

                        </div>
                    </div>
                    <%--<div class="dangan_btn" id="archiveChange">--%>
                        <%--<input type="button" value="新增" class="dangan_add"/>--%>
                        <%--<input type="button" value="调班" class="dangan_add"/>--%>
                        <%--<input type="button" value="复学" class="dangan_add"/>--%>
                        <%--<input type="button" value="休学" class="dangan_add"/>--%>
                        <%--<input type="button" id="quitSchool" value="退学" class="dangan_add" />--%>
                        <%--<input type="button" value="转学" class="dangan_add"/>--%>
                        <%--<input type="button" value="删除" class="dangan_add"/>--%>

                    <%--</div>--%>

                    <p class="dangan_class"><b><span id="className">${clasz.bj_mcheng}</span>班所有孩子学籍档案如下：</b></p>
                    <div class="dangan_tab" id="dangan_tab">
                        <table cellspacing="0" cellpadding="0">
                            <tr>
                                <th>姓名</th>
                                <th>照片</th>
                                <th>性别</th>
                                <th>出生日期</th>
                                <th>手机</th>
                                <th>在校状态</th>
                            </tr>
                            <%--<tr>--%>
                                <%--<td height="18" colspan="8" class="dangan_all">--%>
                                    <%--<input type="button" id="chooseAll" class="da_b" value="全选"/>  &nbsp;--%>
                                    <%--<input type="button" id="chooseNone"  class="da_b" value="全不选"/>  &nbsp;--%>
                                    <%--<input type="button" id="switchChoose"  class="da_b" value="反选"/>--%>
                                <%--</td>--%>
                            <%--</tr>--%>

                            <c:forEach items="${studentPage.list}" var="student" >
                                <tr>
                                    <%--<td class="dangan_single">--%>
                                        <%--<input type="checkbox" name="studentArchive" id="student_id_${student.xs_id}" value="${student.xs_id}" />选择</td>--%>
                                    <td class="dangan_single">${student.xs_xming}</td>
                                    <td class="dangan_single">
                                        <div class="da_img  xx-img">
                                            <img src="http://dsis.952116.com/${student.personalPhoto}"/>
                                        </div>
                                    </td>
                                    <td class="dangan_single">${student.sex}</td>
                                    <td class="dangan_single">${student.birthday} </td>
                                    <td class="dangan_single">
                                        <c:choose>
                                            <c:when test="${student.homephone == null || '' == student.homephone ||  'null'==student.homephone}">
                                            </c:when>
                                            <c:otherwise>
                                                ${student.homephone}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="dangan_single" id="studentStatus">${student.status}</td>
                                </tr>
                           </c:forEach>
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

