<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="com.zigui.service.impl.ParentServiceImpl" %>
<%@ page import="com.zigui.utils.BeanFactoryUtils" %>
<%@ page import="com.zigui.domain.Student" %>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>



<div class="toolbar">

    <div class="tit">
        欢迎来到子贵网！
    </div>


    <div style="float:right">
        <a href="http://dsis.ziguiw.com" title="数字化校园平台" target="_blank" style="MARGIN-LEFT:20px"><font color="red"><strong>数字化校园平台</strong></font></a>

        <a href="http://dsis.ziguiw.com/downandroid/mobile.jsp" title="手机版" style="MARGIN-LEFT:20px" target="_blank">手机版</a>

        <a href="javascript:setHome(window.location);" style="MARGIN-LEFT:20px" target="_self">设为首页</a>

        <a href="javascript:addFavorite(window.location, document.title)" style="MARGIN-LEFT:20px;MARGIN-RIGHT:10px" target="_self">
            加入收藏</a>
    </div>
    <%if(session.getAttribute("VALID_USER")==null){%>
    <div class="subNav">
        <a title="用户登录" href="/user/login.jsp" target="_blank">用户登录</a>
        <a title="新用户注册" href="/user/register.jsp" target="_blank">新用户注册</a>
    </div>
    <%}else{%>
    <%UserBase user = (UserBase)session.getAttribute("VALID_USER");%>
    <%
        String displayName = user.getNickName();
        if (user.getType() == 3) {
            ParentServiceImpl parentService1 = (ParentServiceImpl) BeanFactoryUtils.getBean("parentService");
            List<Student> students = parentService1.findChildren(user.getNickName());
            if (students != null && students.size() > 0) {
                displayName = students.get(0).getXs_xming() + "家长";
            }
        }
    %>
    <%=displayName%>
    <a href="/user/user_logout.action">退出</a>
    <%}%>




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

<script type="text/javascript">
      function change(){
          document.getElementById("searchKeyWords").value="";
      }
</script>
<div class="uc_header">
    <div class="uc_logo"><img src="/userCenter/ucimages/uc_logo.gif" /></div>
    <div class="uc_search">
        <div class="">
            <form action="/search.action">
                <input class="uc_s_bg" type="text" id="searchKeyWords" value="请输入搜索关键词..." name="keyWords" onfocus="change()" />
                <input class="uc_s_btn" type="submit" value="搜索"/>
            </form>
        </div>
        <div class="uc_keywords">
            <%--<s:action var="getInnerLetter" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">--
%>
                <%--<s:param name="hql">select n from News n where state =2  and rownum<=3 order by n.createTime desc</s:param>--%>
            <%--<s:param name="queryString">1=1</s:param>--%>
            <%--</s:action>--%>
            <p>
                <s:iterator>
                <span>
                    <a href="/news/detail/">关键词</a>
                </span>
                </s:iterator>
            </p>
        </div>
    </div>
</div>

<div class="clear"></div>


<s:action name="showHeadMsg" namespace="/userCenter"  executeResult="true"></s:action>

<div class="clear"></div>

<div class="uc_nav">
    <div class="uc_nav_h"></div>
    <ul>
        <li><a href="/user/home.jsp">我的空间</a></li>
        <li><a href="/userCenter/mySource.action">我的资源</a></li>
        <li><a href="/userCenter/myPoint.action">我的积分</a></li>
        <li><a href="/userCenter/questionAndAnswerList.action">我的问答</a></li>
        <li><a href="/userCenter/webNotice.action">网站公告</a></li>
        <li><a href="http://star.hong-plus.com/index.html">智学宝</a></li>
    </ul>
    <!--当页面离开数字化校园查询平台时加此行代码--><div class="retu"><a href="/userCenter/userCenter.jsp">返回数字化校园查询
</a></div>
    <div class="uc_nav_f"></div>
</div>
<div class="clear"></div>