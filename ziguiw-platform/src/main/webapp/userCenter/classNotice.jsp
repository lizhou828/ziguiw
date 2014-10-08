<%@include file="/comm/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>班级通知-子贵网用户中心</title>
    <link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/userCenter/ucPublic/pageQuery.js"></script>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("input[name='studentId']").change(function(){
                window.location="/userCenter/classNoticeList.action?studentId="+$(this).val();
            });
        });
    </script>

</head>

<body>
<jsp:include page="ucPublic/head.jsp"></jsp:include>
<div class="uc_midd">
    <div class="uc_l">
        <jsp:include page="dsisQueryNavigator.jsp"/>
        <jsp:include page="commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>
    </div>
    <div class="letter_right">
        <!--班级通知开始-->
        <div class="my_lett">
            <form action="/userCenter/classNoticeList.action" id="classNoticeList" method="post">
            <div class="lett_title">
                班级通知
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
                        </table>
                    </div>
                </div>
                <c:forEach items="${classNotices.list}" var="notice">
                    <div class="lett_bl">
                        <p>标题：${notice.title}</p>
                        <p>内容：${notice.text}</p>
                        <p class="lett_pa">
                            <span class="lett_m">发送人：${notice.fromUser.nickName}</span>
                            <span>发送时间：<fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                        </p>
                    </div>
                </c:forEach>
                <div class="points_p">
                    <input type="hidden" id="gotoPage" name="gotoPage">
                    <div class="points_p">
                        <p>
                            <c:forEach items="${pageList}" var="page">
                                <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${page}');classNoticeList.submit();">${page}</a>
                            </c:forEach>
                            <input id=maxPage type=hidden  value="${totalPages}">
                            转到 <input id ="inputPage" type="text"  size="2"/> 页
                            <input onclick="changePage();" type="button" id="page" value="Go"/>
                        </p>
                    </div>
                </div>
            </form>

        </div>
        <!--班级通知结束-->


    </div>

    <div class="clear"></div>


    <div class="uc_noti">
        <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>

</div>


<jsp:include page="/inc/templateFoot.jsp"/>