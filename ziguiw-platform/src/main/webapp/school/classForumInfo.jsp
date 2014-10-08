<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../comm/common.jsp"%>
<%
    String path = request.getContextPath();
    ActionContext context = ActionContext.getContext();
    Map<String, Object> session1 = context.getSession();
    UserBase user = (UserBase)session1.get("VALID_USER");
    long uid = 0;
    if(user!=null){
        uid = user.getId();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>子贵网_校园站内站</title>
    <link href="../css/class.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
            function userLogin(){
                var uid = <%=uid %>;
                if(uid==0){
                    alert('您好，必须要登录之后才可以发帖!');
                    return false;
                }else{
                    return true;
                }
            }

    </script>
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


        <div class="clear"></div>

        <!--论坛开始!-->
        <div class="c_send">
            <div class="s_sind">
                <div class="bbs_ntn">
                    <input class="send_btn" type="button" value="发表新帖" onclick="javascript:window.location='<%=path%>/school/classPosted.jsp?class_id=${class_id}'"/>
                    <input class="send_btn" type="button" value="回复"/>
                    <span style="margin-left:530px"><a href="<%=path%>/user/classMovie.action?class_id=${class_id}"><b><< 返回帖子列表</b></a></span></div>
            </div>
            <div class="bbs_lou">
                <table width="925" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="300px" rowspan="2" class="bt_2"><div class="bt_user"><img src="/${ctx}${classForum.user.portrait}" /></div>
                            <div><strong>${classForum.user.nickName}</strong></div>
                        </td>
                        <td width="824" class="bt_title">
                            <p>${classForum.title}</p>
                        </td>
                    </tr>
                    <tr>
                        <td height="250" class="bt_right">
                            <div class="b_time">
                                <div class="tt_1">发表于：<b><fmt:formatDate value="${classForum.createTime}" type="both" pattern="yyyy-MM-dd hh:mm"></fmt:formatDate></b></div>
                                <div class="tt_2"> 浏览：<b style="margin-right:15px">${classForum.visitCount}</b> 回复：<b>${classForum.replyCount}</b></div></div>
                            <div class="bt_wenzi">
                                <p>${classForum.content}
                                </p>
                            </div>
                        </td>
                    </tr>
                </table>

            </div>

            <c:forEach items="${classReplyPage.list}" var="classReply" varStatus="status">
            <div class="bbs_lou">
                <table width="925" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="300px" rowspan="2" class="bt_2"><div class="bt_user"><img src="../images/head.jpg" /></div>
                            <div><strong>${classReply.user.nickName}</strong></div>
                        </td>
                        <td width="824" class="bt_title">
                            <span class="bbs_floor">${status.index + 1}楼</span>
                        </td>
                    </tr>
                    <tr>
                        <td height="250" class="bt_right">
                            <div class="b_time">
                                <div class="tt_1">发表于：<b><fmt:formatDate value="${classReply.createTime}" type="both" pattern="yyyy-MM-dd hh:ss"></fmt:formatDate></b></div></div>
                            <div class="bt_wenzi">
                                <p>${classReply.content}
                                </p>
                            </div>
                        </td>
                    </tr>
                </table>

            </div>
            </c:forEach>



            <div class="pagenum">
                ${pageString}
            </div>
            <br />

            <div class="s_sind">
                <div class="bbs_ntn">
                    <input class="send_btn" type="button" value="发表新帖"/>
                    <span style="margin-left:530px"><a href="<%=path%>/user/classMovie.action?class_id=${class_id}"><b><< 返回帖子列表</b></a></span></div>
            </div>
            <form action="<%=path%>/user/saveClassReply.action" type="post" onsubmit="return userLogin();">
            <div class="bbs_lou">
                <div class="bbs_edit">
                    <textarea  name="classReply.content" cols="132" height="270" width="913" rows="17"></textarea>
                </div>
                <div class="bre_btn">
                    <div class="bbs_btn">
                        <input class="send_btn" type="submit" value="回复"/>
                    </div>
                </div>
            </div>
            <input type="hidden" name="class_id" value="${class_id}">
            <input type="hidden" name="classForumId" value="${classForum.id}">

            </form>
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
