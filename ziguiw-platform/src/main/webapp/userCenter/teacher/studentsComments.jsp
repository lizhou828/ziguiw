
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学生们评语——子贵网用户中心</title>
    <link href="../css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="../css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" defer="defer"></script>
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
            <form action="/userCenter/studentCommentList.action"  id="studentsCommentsList" method="post">

                <input type="hidden" name="typeid" id="msgtype" value="3"/>

                <div class="bobti font-5">
                    学生评语
                </div>
                <div class="xunc_1 font-2">
                    <div class="xunc_1-1">
                        <table width="697" border="0" cellspacing="0" cellpadding="0">
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
                                <td width="40%"><input  id="QueryBtn"  type="submit"   class="cx" value="查  询"/></td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="title_5 font-3">(&nbsp;共&nbsp;${rectotal}&nbsp; 封&nbsp;)</div>
                <div class="ly-tab font-2">
                    <table border="0" cellpadding="0" cellspacing="0" class="sideright_tab1">
                        <tr bgcolor="#f2f4f6" >
                            <td width="608" class="td_5">信息内容</td>
                        </tr>
                        <c:choose>
                            <c:when test="${map['data'] ==null || fn:length(map['data'])==0}">
                                没有相关信息！
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${map['data']}" var="data">
                                    <tr style="cursor:pointer" >
                                    <td class="td_6">
                                    ${data['MSM_CONTENT']}&nbsp;
                                    <p>${data['SENDOBJECT']}&nbsp;</p>
                                    <p>${data['SENDTIME']}&nbsp;</p>
                                    </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>

                        </c:choose>

                    </table>
                </div>
                <div class="pageNav">
                   <%--分页开始--%>
                       <jsp:include page="/userCenter/pageQuery.jsp"/>
                   <%--分页结束--%>
               </div>
            </form>
        </div>


        <div class="clear"></div>
    </div>


    <!--插入数字化校园的区域 结束-->
</div>
<div class="clear"></div>

<div class="uc_noti">
    <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
</div>

</div>

<jsp:include page="/inc/templateFoot.jsp"/>

</body>
</html>
