<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zigui.domain.News" %>
<%@ page import="com.zigui.dao.NewsDao" %>
<%@ page import="com.zigui.utils.BeanFactoryUtils" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站公告-子贵网用户中心</title>
<link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/userCenter/ucPublic/pageQuery.js"></script>

</head>
<body>
<jsp:include page="ucPublic/head.jsp"></jsp:include>
    <div class="uc_midd">

    	<div class="not_con">
        	<div class="not_t">
            	网站公告
            </div>
            <div class="not_spe">
                <ul>
                    <c:forEach items="${noticePage.list}" var="news">
                        <li>
                            <div class="noti_content">
                                <a href="/news/detail/${news.id}">${news.title}</a>
                            </div>
                            <div class="noti_time">${news.createTime}</div>
                        </li>
                    </c:forEach>

                    </li>
                </ul>


            </div>
            <form action="/userCenter/webNotice.action" id="queryWebNotice" method="post">
            <div class="points_p">
                <input type="hidden" id="gotoPage" name="gotoPage">
            	<p>
                    <c:forEach items="${pageList}" var="page">
                        <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${page}');queryWebNotice.submit();">${page}</a>
                    </c:forEach>
                    <input id=maxPage type=hidden  value="${totalPage}">
                    转到 <input id ="inputPage" type="text"  size="2"/> 页
                    <input onclick="changePage();" type="button" id="page" value="Go"/>
                </p>
            </div>
            </form>
        </div>
     <div class="clear"></div>
    <div class="uc_noti">
    	<p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>

 </div>
<jsp:include page="/inc/templateFoot.jsp"/>