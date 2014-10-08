<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../comm/common.jsp"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>子贵网_校园站内站</title>
    <link href="../css/class.css" rel="stylesheet" type="text/css" />

</head>
<body>
<!--子贵网头部!-->
<jsp:include page="inc/classHead.jsp"></jsp:include>
<!--导航结束!-->


<div class="main">

    <!--内页外边框!-->
    <div class="outside">

        <!--班级图片标题!-->
        <div class="t_title">
            <img src="../images/title_img.gif" />班级论坛</div>


        <!--新闻开始!-->



        <!--新闻结束!-->


        <div class="clear"></div>

        <!--论坛开始!-->
        <div class="c_send">
            <div class="s_sind">
                <input class="send_btn" type="button" value="发表新帖" onclick="javascript:window.location='<%=path%>/school/classPosted.jsp?class_id=${class_id}'"/>
            </div>
        </div>
        <div class="c_says">
            <table cellpadding="0" cellspacing="0" width="925" border="0">
                <tr>
                    <td height="30" style=" background-image:url(../images/list_title_bg.gif)">&nbsp;</td>
                    <td height="30" style=" background-image:url(../images/list_title_bg.gif)" align="center"><b>帖子主题</b></td>
                    <td style=" background-image:url(../images/list_title_bg.gif)" align="center">&nbsp;</td>
                    <td height="30" style=" background-image:url(../images/list_title_bg.gif)" align="center"><b>作者</b></td>
                    <td height="30" style=" background-image:url(../images/list_title_bg.gif)" align="center"><b>回复/查看</b></td>
                    <td height="30" style=" background-image:url(../images/list_title_bg.gif)" align="center"><b>最后发表</b></td>
                </tr>
                <c:forEach items="${classForumPage.list}" var="classForum">
                <tr>
                    <td width="38" height="41" style="padding-left:10px;background-color:#f4fafd"> <img src="../images/new_post.gif" width="18" height="21" /></td>
                    <td width="587" colspan="2" style="padding-left:10px"><a href="<%=path%>/user/classForumInfo.action?class_id=${classForum.class_id}&classForumId=${classForum.id}"> ${classForum.title}</a></td>
                    <td width="100" bgcolor="#F4FAFD" align="center"><p>${classForum.user.nickName}</p>
                    <p><fmt:formatDate value="${classForum.createTime}" type="both" pattern="yyyy-MM-dd"></fmt:formatDate></p>
                    <td width="100" align="center">${classForum.replyCount}/${classForum.visitCount}</td>
                    <td width="100" bgcolor="#F4FAFD" align="center"><p><fmt:formatDate value="${classForum.lastReplyTime}" type="both" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></p>
                        </td>
                </tr>
                </c:forEach>
            </table>


        </div>

        <div class="pagenum">
            ${pageString}
        </div>
    </div>
    <!--论坛开始!-->

    <!--内页外边框结束!-->

</div>

<!--子贵网底部开始-->
<jsp:include page="inc/templateFoot.jsp"></jsp:include>
<!--子贵网底部结束-->
</body>
</html>
