<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="com.zigui.service.impl.UserServiceImpl" %>
<%@ page import="com.zigui.utils.BeanFactoryUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../comm/common.jsp"%>
<%
   UserServiceImpl userService = (UserServiceImpl) BeanFactoryUtils.getBean("userService");
   request.setAttribute("hostUserName",request.getParameter("hostUserName"));
   request.setAttribute("xxId",request.getParameter("xxId"));
   boolean isHost = false;
   UserBase user = (UserBase) session.getAttribute("VALID_USER");
   String userNickName = request.getParameter("hostUserName");
   UserBase hostUser =  userService.getUserBaseByNickName(userNickName);
   if(user != null && (hostUser.getNickName().equals(user.getNickName()))){
           isHost = true;
   }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>子贵网_校园站内站</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="http://www.ziguiw.com/css/basc.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/changimages.js" type="text/javascript"></script>
    <link href="${ctx}/css/classList.css" rel="stylesheet" type="text/css" />
    <!--<base target="_blank"/>	-->
    <script>
        function dj(i)
        {
            for(j=1;j<100;j++)  //遍历刷新所有门的状态，序号与参数相同的的一种状态，其余一种状态
            {
                if(document.getElementById("a"+j)==null){
                    break;
                }
                document.getElementById("a"+j).className="li1";
                document.getElementById("b"+j).style.display="none";
            }
            document.getElementById("a"+i).className="li2";
            document.getElementById("b"+i).style.display="block";

        }
    </script>
    <style type="text/css">
        table a {
            color:#06F;
        }

    </style>
</head>
<body>

<div class="toolbar">

    <div class="tit">
        欢迎来到子贵网！
    </div>

    <div class="subNav">


        <a title="用户登录" href="${ctx}/user/login.jsp" target="_blank">用户登录</a>

        <a title="新用户注册" href="${ctx}/user/register.jsp" target="_blank">新用户注册</a>

        <a href="http://dsis.ziguiw.com" title="数字化校园平台" target="_blank"><font color="red"><strong>数字化校园平台</strong></font></a>

        <a href="http://dsis.ziguiw.com/downandroid/mobile.jsp" title="手机版" target="_blank">手机版</a>

        <a href="javascript:setHome(window.location);" target="_self">设为首页</a>

        <a href="javascript:addFavorite(window.location, document.title)" target="_self">加入收藏</a>



        <script>
            function addFavorite(sURL, sTitle) {
                try {
                    window.external.addFavorite(sURL.toString(), sTitle.toString());
                }
                catch (e) {
                    try {
                        window.sidebar.addPanel(sTitle.toString(), sURL.toString(), "");
                    }
                    catch (e) {
                        alert("加入收藏失败，请使用Ctrl+D进行添加");
                    }
                }
            }

            function setHome(vrl) {
                if(document.all){
                    document.body.style.behavior='url(#default#homepage)';
                    document.body.setHomePage(vrl);
                }
                else if(window.sidebar){
                    if(window.netscape){
                        try{
                            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                        }
                        catch(e){
                            alert("您的浏览器未启用[设为首页]功能，开启方法：先在地址栏内输入about:config,然后将项 signed.applets.codebase_principal_support 值该为true即可");
                        }
                    }
                    var prefs=Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                    prefs.setCharPref('browser.startup.homepage',vrl);
                }
            }
        </script>
    </div>
    <div class="subNav"> </div>

</div>

<!--toolbar E -->

<!--middle  --B-->
<div class="t_middle">

    <div class="t_banner">
        <div class="bann_logo">
            <img src="${ctx}/images/bann_logo.jpg" />
        </div>
        <div class="banner_sc">
            <img src="${ctx}/images/banner_sc.gif" />
        </div>

    </div>

    <div class="clear"></div>

    <div class="t_nav">
        <ul>
            <li style="width:90px"><a href="${ctx}/school/index.jsp?hostUserName=${hostUserName}"><span>学校首页</span></a></li>
            <li style="width:90px"><a href="${ctx}/user/getSchoolSummary.action?hostUserName=${hostUserName}"><span>学校简介</span></a></li>
            <li style="width:90px"><a href="${ctx}/school/school_news.jsp?type=5&hostUserName=${hostUserName}"><span>校园新闻</span></a></li>
            <li style="width:90px"><a href="${ctx}/school/school_news.jsp?type=6&hostUserName=${hostUserName}"><span>学校公告</span></a></li>
            <%--<li style="width:90px"><a href="${ctx}/school/school_news.jsp?type=7&hostUserName=${hostUserName}"><span>招生信息</span></a></li>--%>
            <li style="width:90px"><a href="${ctx}/school/teachers.jsp?hostUserName=${hostUserName}"><span>师资力量</span></a></li>
            <li style="width:90px"><a href="${ctx}/user/school_album_list.action?hostUserName=${hostUserName}"><span>校园风光</span></a></li>
            <li style="width:90px"><a href="${ctx}/school/leave_message.jsp?hostUserName=${hostUserName}"><span>留言评论</span></a></li>
            <%--<li style="width:90px"><a href="${ctx}/user/getSchoolJob.action?hostUserName=${hostUserName}"><span>招聘信息</span></a></li>--%>
            <%--<li style="width:90px"><a href="${ctx}/user/classList.action?xxId=${xxId}"><span>班级列表</span></a></li>--%>
            <%if(isHost) {%><li style="width:90px"><a href="${ctx}/school/school_manager.jsp"><span>管理中心</span></a></li><%} %>
        </ul>
    </div>


    <div class="comm_k">
        <div class="comm_w">
            <p>班级列表</p>
        </div>


        <div class="class_title"><b>初中</b>：</div>
        <table border="0" style="font-size:12px; margin-bottom:40px; margin-left:20px">
                <c:forEach items="${clazzList}" var="classList" varStatus="status">
                    <c:if test="${fn:startsWith(classList.bj_mcheng, 'C') || fn:startsWith(classList.bj_mcheng, 'c')}">
                        <c:if test="${status.index % 10 ==0}">
                            <tr>
                        </c:if>
                        <td width="92" align="center">
                            <a href="${ctx}/user/classInfo.action?pageNum=1&pageSize=7&class_id=${classList.bj_id}">
                                    ${classList.bj_mcheng}
                            </a>
                        </td>
                        <c:if test="${(status.index+1) % 10 ==0}">
                            </tr>
                        </c:if>
                    </c:if>
                </c:forEach>
        </table>

        <div class="class_title"><b>高中</b>：</div>
        <table border="0" style="font-size:12px; margin-bottom:40px; margin-left:20px">
            <c:forEach items="${clazzList}" var="classList" varStatus="status">
                <c:if test="${fn:startsWith(classList.bj_mcheng, 'G') || fn:startsWith(classList.bj_mcheng, 'g')}">
                    <c:if test="${status.index % 10 ==0}">
                        <tr>
                    </c:if>
                    <td width="92" align="center">
                        <a href="${ctx}/user/classInfo.action?pageNum=1&pageSize=7&class_id=${classList.bj_id}">
                                ${classList.bj_mcheng}
                        </a>
                    </td>
                    <c:if test="${(status.index+1) % 10 ==0}">
                        </tr>
                    </c:if>
                </c:if>
            </c:forEach>
        </table>


    </div>


</div>


</div>


<!--middle  --E-->

<div class="clear"></div>
<!--footer B-->
<div class="p_flink">
    <p>
        <a href="http://www.ziguiw.com/helpCenter/about.jsp">关于我们</a>|
        <a href="http://www.ziguiw.com/helpCenter/contract.jsp">服务协议</a>|
        <a href="http://www.ziguiw.com/helpCenter/service.jsp">客服中心</a>|
        <a href="http://dsis.ziguiw.com/feedback/add.jsp">意见反馈</a>|
        <a href="http://www.ziguiw.com/helpCenter/pin.jsp">网站招聘</a>|
        <a href="http://www.ziguiw.com/helpCenter/map.jsp">网站地图</a>|
        <a href="http://www.ziguiw.com/helpCenter/contact.jsp">联系我们</a>|
        <a href="http://dsis.ziguiw.com/topic/cloud/index.jsp">数字校园云服务展会专题</a>|
        <a href="http://dsis.ziguiw.com/topic/cloud/footmark_two.jsp">轨迹查询</a>|
        <a href="http://www.ziguiw.com/helpCenter/copyright.jsp">版权所有</a>|
        <a href="http://www.ziguiw.com/helpCenter/help.jsp">帮助中心</a>
    </p>
</div>



<div class="p_logo">
    <p>客户服务热线：952116　　客服邮箱：kf@ziguiw.com</p>
    <p><span>Copyright©2012</span><span> <a href="http://www.ziguiw.com">ziguiw.com</a> </span><span>All Rights Reserved</span>  <span>湘ICP备09002922号-3</span></p>
    <img src="../images/p_logo.gif" />
</div>



<!--footer E-->


<script language="JavaScript">
    var msg = '';
</script>



<!-- actionerror  -->


<!-- actionmessage  -->


<!-- 打印转换好格式的fielderror信息    -->
<script language="JavaScript">
    if(msg != ''){
        alert(msg);
    }
</script>


<!-- fielderror  -->




<script type="text/javascript">

    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-29729795-1']);
    _gaq.push(['_setDomainName', 'ziguiw.com']);
    _gaq.push(['_trackPageview']);

    (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
    })();
</script>

</body>
</html>
