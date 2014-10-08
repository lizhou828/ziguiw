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
    request.setAttribute("class_id", request.getParameter("class_id"));
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
        <form action="<%=path%>/user/classForumSave.action" method="post" onsubmit="return userLogin();">
        <div class="new_edit">
            <div class="newbbs_title">
                <span>标题：</span><input class="bn_text" type="text"  name = "classForum.title"/>
            </div>

            <div class="nbt_edit">
                <textarea class="newbbs_text" name="" cols="" rows=""></textarea>
                </div>
            </div>
            <input type="hidden" name="classForum.class_id" value="${class_id}"/>

            <div class="s_newbtn">
                <input class="send_btn" type="submit" value="提交"/>
            </div>
        </form>
        </div>


    </div>
    <!--论坛开始!-->

    <!--内页外边框结束!-->



<!--子贵网底部开始-->
<jsp:include page="inc/templateFoot.jsp"></jsp:include>
<!--子贵网底部结束-->
</body>
</html>
