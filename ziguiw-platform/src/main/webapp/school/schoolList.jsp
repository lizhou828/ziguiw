<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../comm/common.jsp"%>
<%
   String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8" />
    <title>站内站首页_子贵网</title>
    <meta name="generator" content="editplus" />
    <meta name="author" content="" />
    <meta name="keywords" content="教育新闻资讯,升学考试政策,校园动态,校园新闻" />
    <meta name="description" content="教育在线栏目致力于打造最大的教育新闻资讯平台。面向全国高中小院校及家长、教师等各类用户提供及时、权威的教育新闻，发布升学考试政策，关注校园动态，追踪新闻热点。频道下设：焦点话题报道、校园新闻、升学考试指南及择校等教育行业资讯栏目。" />
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
    <META HTTP-EQUIV="expires" CONTENT="0">
    <link href="../css/basc.css" rel="stylesheet" type="text/css">
    <link href="../css/schoolList.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery-1.7.2.min.js"></script>
    <script src="/js/basic.js.jsp"></script>
    <script src="/js/focus.js"></script>
</head>

<body>

<div class="toolbar">

    <div class="tit">
        欢迎来到子贵网！
    </div>

    <div class="subNav">


        <a title="用户登录" href="/user/login.jsp" target="_blank">用户登录</a>

        <a title="新用户注册" href="/user/register.jsp" target="_blank">新用户注册</a>

    </div>

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




<!--toolbar E -->


<div class="header">

    <a class="logo" href="/" title=""><img src="/images/logo.jpg" alt=""/></a>

    <form action="/search.action">

        <div class="search">

            <input type="text" class="text" name="keyWords" value="请输入搜索关键词..."/>

            <input type="submit" class="btn" name="" value="搜索"/>

        </div>
    </form>



    <div class="nav">

        <ul class="nav_tit clearfix">

            <li id='index_tab1'><a href="${ctx}/index.jsp" title="首   页" >首   页</a></li>

            <li id='index_tab2'><a href="${ctx}/news/index" title="教育在线" >教育在线</a></li>

            <li id='index_tab3'><a href="${ctx}/study/index" title="教育知识" >教育知识</a></li>

            <li id='index_tab4'><a href="http://dsis.ziguiw.com/source/sourceInfo.action" title="教育资源" >教育资源</a></li>

            <li id='index_tab5'><a href="${ctx}/community/index" title="互动社区" >互动社区 <img src="/images/new.png" alt=""/></a></li>

            <li id='index_tab6'><a href="${ctx}/user/home_index.jsp" title="我的小家" >我的小家</a></li>

        </ul>
        <script language='javascript'>
            function aaa() {
                var url = self.location.href;

                if(url.indexOf('news')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').setAttribute('class','cur');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').removeAttribute('class');
                }else if(url.indexOf('study')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').setAttribute('class','cur');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').removeAttribute('class');
                }else if(url.indexOf('source')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').setAttribute('class','cur');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').removeAttribute('class');
                }else if(url.indexOf('community')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').setAttribute('class','cur');
                    document.getElementById('index_tab6').removeAttribute('class');
                }else if(url.indexOf('home')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').setAttribute('class','cur');
                }else{
                    document.getElementById('index_tab1').setAttribute('class','cur');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').removeAttribute('class');
                }
            }
            aaa();
        </script>
        <ul class="nav_sub_tit">

            <li>

                <a href="${ctx}/news/list/1" title="焦点话题" target="_blank">焦点话题</a>

                <a href="${ctx}/news/list/2" title="校园新闻" target="_blank">校园新闻</a>

                <a href="${ctx}/news/list/3" title="考试升学" target="_blank">考试升学</a>

            </li>

            <li>

                <a href="${ctx}/study/youer/list" title="幼儿期" target="_blank">幼儿期</a>

                <a href="${ctx}/study/shaoer/list" title="少儿期" target="_blank">少儿期</a>

                <a href="${ctx}/study/qingnian/list" title="青年期" target="_blank">青年期</a>

                <a href="${ctx}/study/psychology/list" title="心理咨询" target="_blank">心理咨询</a>

                <a href="${ctx}/study/yuer/list/0" title="育儿问答" target="_blank">育儿问答</a>

                <a href="${ctx}/study/bianlun/list" title="辩论" target="_blank">辩论</a>

            </li>

            <li>

                <a href="${ctx}/community/age/list" title="同龄圈" target="_blank">同龄圈</a>

                <a href="${ctx}/community/city/list" title="同城圈" target="_blank">同城圈</a>

                <a href="${ctx}/community/jiaoliu/list" title="学习交流" target="_blank">学习交流</a>

                <a href="${ctx}/community/zhuanjia/list" title="专家问答" target="_blank">专家问答</a>

            </li>


        </ul>

    </div>

    <!--nav E -->

</div>

<!--header E --><div class="content clearfix">

<!--<div class="ad01">

        <a href="#" title=""><img src="/images/adv01.jpg" alt=""/></a>

    </div>-->

<!--ad01 E -->

<div class="mypos fsong"><a href="/index">子贵首页</a> > <a href="">学校站内站</a></div>

<div class="col1 fl">
    <div class="h3titc">

        <h3 class="pr fyahei">学校列表</h3>

    </div>


    <div class="schoollist borda">

        <ul>
            <c:forEach items="${page.list}" var = "list">
            <li>
                <div class="sch_img">
                    <c:if test="${list[0] == null}">
                    <p class="padd2"><a href="<%=path%>/school/index.jsp?hostUserName=${list[2]}"><img src="../images/school.jpg"></a></p>
                    </c:if>
                    <c:if test="${list[0] != null}">
                    <p class="padd2"><a href="<%=path%>/school/index.jsp?hostUserName=${list[2]}"><img src="${ctx}/${list[0]}" width="175" height="131"></a></p>
                    </c:if>
                    <p class="cen mt10"><a href="<%=path%>/school/index.jsp?hostUserName=${list[2]}">${list[1]}</a></p>
                </div>
            </li>
            </c:forEach>
        </ul>
        <div class="pagenum">
            ${pageString}
        </div>

    </div>

</div>

<div class="col2 fl ml10">

    <div class="hottj pr pt30">

        <div class="h3tita pa">

            <h3 class="fyahei">最新校园新闻</h3>

        </div>

        <div class="borda">

            <ul class="hottj_bot bordno">
                <s:action var="schoolNewsAction" name="schoolNewsAction" namespace="/user" executeResult="false" ignoreContextParams="false">
                    <s:param name="pageSize" value="10"></s:param>
                    <s:param name="type" value="5"></s:param>
                </s:action>
                <s:iterator value="#schoolNewsAction.pagedDiaries.list" var="schoolNews">
                <li>
                    <span>
                        <fmt:formatDate value="${schoolNews.createTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                    </span>
                    <a href="<%=path%>/user/getShoolDiaryById.action?diary.id=${schoolNews.id}&hostUserName=${schoolNews.user.nickName}" target="_blank">
                        ${fn:length(schoolNews.title) > 10 ? fn:substring(schoolNews.title, 0, 10) : schoolNews.title}
                    </a>
                </li>
                </s:iterator>
            </ul>
        </div>

    </div>


    <div class="ph mt10 bgwhite">

        <div class="h3titb">

            <h3 class="fyahei">推荐学校</h3>

        </div>

        <div class="borda col555">

            <ul>
                <%--<s:action var="schoolInfo" name="schoolInfoList" namespace="/user" executeResult="false" ignoreContextParams="false">
                <s:param name="pageNum" value="2"></s:param>
                <s:param name="pageSize" value="10"></s:param>
                </s:action>--%>
                <c:forEach items="${page.list}" var="school" varStatus="status">
                    <c:if test="${status.index < 10}">
                        <li><a href="<%=path%>/school/index.jsp?hostUserName=${school[2]}" target="_blank">${school[1]}</a></li>
                    </c:if>
                </c:forEach>
                <%--<s:iterator value="#page.list" var="schoolInfo1">
                <li><a href="<%=path%>/school/index.jsp?hostUserName=${schoolInfo1[1]}" target="_blank">${schoolInfo1[0]}</a></li>
                </s:iterator>--%>

            </ul>

        </div>

    </div>



    <div class="mt10 bgwhite">

        <div class="h3titb">

            <h3 class="fyahei">最新加入学校</h3>

        </div>

        <div class="borda">

            <ul class="hottj_bot bordno">
                <%--<s:action var="schoolInfo" name="schoolInfoList" namespace="/user" executeResult="false" ignoreContextParams="false">
                    <s:param name="pageNum" value="1"></s:param>
                    <s:param name="pageSize" value="10"></s:param>
                </s:action>
                <s:iterator value="#schoolInfo.page.list" var="schoolInfo2">
                    <li><a href="<%=path%>/school/index.jsp?hostUserName=${schoolInfo2[1]}" target="_blank">${schoolInfo2[0]}</a></li>
                </s:iterator>--%>
                <c:forEach items="${page.list}" var="school" varStatus="status">
                    <c:if test="${status.index < 12}">
                    <li><a href="<%=path%>/school/index.jsp?hostUserName=${school[2]}" target="_blank">${school[1]}</a></li>
                    </c:if>
                </c:forEach>

            </ul>

        </div>

    </div>


</div>
<div class="box08">

    <div class="box08_hd">

        <h2 class="friendLinkTit">友情链接</h2>


    </div>

    <!--box01_hd E -->

    <div class="box08_bd">

        <div class="textListFriends">

            <a href="http://www.moe.edu.cn/">教育部门户</a>
            <a href="http://www.eol.cn/">中国教育在线</a>
            <a href="http://www.chinaedu.edu.cn/">中国教育信息网</a>
            <a href="http://edu.sina.com.cn/">新浪教育</a>
            <a href="http://edu.163.com/">网易教育</a>
            <a href="http://edu.qq.com/">腾讯教育</a>
            <a href="http://learning.sohu.com/">搜狐教育</a>
            <a href="http://www.xinhuanet.com/edu/">新华教育</a>

        </div>

        <!--textListFriends E -->

    </div>

    <!--box01_hd E -->



</div></div>
<style type="text/css">
    .p_all {
        width:990px;
        margin:0 auto 5px auto;
        background:#FFF;
        overflow:hidden;
        zoom:1;
    }

    .p_flink {
        width:970px;
        height:35px;
        line-height:35px;
        background:#EEE;
        margin:0 auto 5px auto;
        font-size:12px;
    }
    .p_flink p {
        text-align:center;
    }
    .p_flink a {
        margin:0 5px;
        color:#000;
        text-decoration: none;
        font-size:12px;
    }
    .p_flink a:hover {
        text-decoration: underline;
    }
    .p_logo {
        text-align:center;
    }
    .p_logo p {
        line-height:20px;
        /*height:20px;*/
    }
    .p_logo span {
        padding:0 5px;
    }
</style>
<!--footer B-->
<jsp:include page="inc/templateFoot.jsp"></jsp:include>
</body>

</html>