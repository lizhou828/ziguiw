<%@ page import="com.zigui.domain.School" %>
<%@ page import="com.zigui.service.impl.ClassServiceImpl" %>
<%@ page import="org.springframework.beans.factory.BeanFactory" %>
<%@ page import="com.zigui.utils.BeanFactoryUtils" %>
<%@ page import="com.zigui.domain.Clasz" %>
<%@ page import="com.zigui.service.impl.SchoolServiceImpl" %>
<%@ page import="com.zigui.service.impl.UserServiceImpl" %>
<%@ page import="com.zigui.domain.UserBase" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    ClassServiceImpl classService = (ClassServiceImpl)BeanFactoryUtils.getBean("clazzService");
    SchoolServiceImpl schoolService = (SchoolServiceImpl)BeanFactoryUtils.getBean("schoolService");
    UserServiceImpl userService = (UserServiceImpl)BeanFactoryUtils.getBean("userService");
    String path = request.getContextPath();
    String class_id = request.getParameter("class_id");
    long bj_id = Long.parseLong(class_id);
    Clasz clasz = classService.findClassById(bj_id);
    School school = schoolService.getInfoByXxID(clasz.getXxID());
    UserBase user = userService.getUserBaseBySchoolForeignkey(school.getXx_ID());
%>
<div class="toolbar">
    <div class="tit">
        欢迎来到子贵网！
    </div>

    <div class="subNav">


        <a title="用户登录" href="/user/login.jsp" target="_blank">用户登录</a>

        <a title="新用户注册" href="/user/register.jsp" target="_blank">新用户注册</a>

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

<!--头部开始!-->

<div class="c_top">
    <div class="t_logo">
<%if("4301113000".equals(school.getXxID())){%>
        <img src="<%=path%>/images/dizhilogo.jpg" />
<%}else{%>
        <img src="<%=path%>/images/classheadlogo.jpg" />
<%}%>
    </div>
    <div class="t_banner">
        <img src="<%=path%>/images/w_user_banner.jpg" style="height:160px;width:780px" />

    </div>

</div>
<!--头部结束!-->

<!--导航开始!-->
<div class="c_menu">
    <ul class="m_ul">
        <li class="m_li"><a href="<%=path%>/user/classInfo.action?pageNum=1&pageSize=7&class_id=${class_id}">班级首页</a></li>
        <li class="m_li"><a href="<%=path%>/user/classNews.action?class_id=${class_id}&pageNum=1&pageSize=10&type=1">班级公告</a></li>
        <li class="m_li"><a href="<%=path%>/user/classNews.action?class_id=${class_id}&pageNum=1&pageSize=10&type=0">班级动态</a></li>
        <li class="m_li"><a href="<%=path%>/user/classPhoto.action?class_id=${class_id}">班级图片</a></li>
        <li class="m_li"><a href="<%=path%>/user/classNews.action?class_id=${class_id}&pageNum=1&pageSize=10&type=3">学习园地</a></li>
        <li class="m_li"><a href="<%=path%>/user/classNews.action?class_id=${class_id}&pageNum=1&pageSize=10&type=2">班级荣誉榜</a></li>
        <li class="m_li"><a href="<%=path%>/user/classMovie.action?class_id=${class_id}&type=1&pageSize=5">班级视频</a></li>
        <li class="m_li"><a href="<%=path%>/user/classForumList.action?class_id=${class_id}">班级论坛</a></li>
        <li class="m_li"><a href="<%=path%>/school/index.jsp?hostUserName=<%=user.getNickName()%>">返回学校首页</a></li>
    </ul>
</div>