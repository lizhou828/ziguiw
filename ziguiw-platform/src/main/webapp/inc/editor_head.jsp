<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.zigui.domain.UserBase"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath();
UserBase user = (UserBase)session.getValue("VALID_USER");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta charset="utf-8" />

<title>子贵网</title>

	<meta name="generator" content="editplus" />

	<meta name="author" content="" />

	<meta name="keywords" content="" />

	<meta name="description" content="" />
	<script src="<%=path%>/js/jquery-1.7.2.min.js"></script>
	<link href="<%=path%>/css/basc-min.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path%>/css/base-min.css" rel="stylesheet"/>
<link href="<%=path%>/css/editor-pkg-min-datauri.css" rel="stylesheet"/>
	

</head>

<body>

<div class="toolbar">

	<div class="tit">
		子贵，就从今天起航
	</div>

	<div class="subNav">

		<a href="<%=path%>/user/login.jsp" title="用户登录">用户登录</a>

		<a href="<%=path%>/user/register.jsp" title="新用户注册">新用户注册</a>

	</div>

</div>

<!--toolbar E -->



<div class="header">


	<a class="logo" href="#" title=""><img src="<%=path%>/images/logo.jpg" alt=""/></a>
	<form action="<%=path%>/search.action">
	<div class="search">

		<input type="text" class="text" name="keyWords" value="请输入搜索关键词..." id=""/>

		<input type="submit" class="btn" name="" value="搜索" id=""/>

	</div>
	</form>

	<div class="nav">

		<ul class="nav_tit clearfix">

			<li  class="cur"><a href="<%=path%>/index.jsp" title="首   页" target="_blank">首   页</a></li>

			<li ><a href="<%=path%>/news/index" title="教育在线" target="_blank">教育在线</a></li>

			<li ><a href="<%=path%>/study/index" title="教育知识" target="_blank">教育知识</a></li>

			<li ><a href="" title="互动社区" target="_blank">互动社区 <img src="<%=path%>/images/new.png" alt=""/></a></li>

			<li><a href="<%=path%>/user/home_index.jsp" title="我的小家" target="_blank">我的小家</a></li>

		</ul>

		<ul class="nav_sub_tit">

			<li>

				<a href="<%=path%>/newsPage_getChannleNewsList.action?channleId=1" title="焦点话题" target="_blank">焦点话题</a>

				<a href="<%=path%>/newsPage_getChannleNewsList.action?channleId=1" title="校园新闻" target="_blank">校园新闻</a>

				<a href="<%=path%>/newsPage_getChannleNewsList.action?channleId=1" title="考试升学" target="_blank">考试升学</a>

			</li>

			<li>

				<a href="<%=path%>/study/youer/list" title="幼儿期" target="_blank">幼儿期</a>

				<a href="<%=path%>/study/shaoer/list" title="" target="_blank">少儿期</a>

				<a href="<%=path%>/study/qingnian/list" title="" target="_blank">青年期</a>
				
				<a href="<%=path%>/study/psychology/list" title="" target="_blank">心理咨询</a>

				<a href="<%=path%>/study/yuer/list/0" title="育儿问答">育儿问答</a>

				<a href="<%=path%>/study/bianlun/list" title="辩论" target="_blank">辩论</a>

			</li>

			<li>

				<a href="#" title="同龄圈" target="_blank">同龄圈</a>

				<a href="#" title="同城圈" target="_blank">同城圈</a>

				<a href="#" title="爱心站" target="_blank">爱心站</a>

				<a href="#" title="专家问答" target="_blank">专家问答</a>

			</li>

			<li>

				<a href="#" title="日记" >日记</a>

				<a href="<%=path%>/user/album.jsp" title="相册" >相册</a>

				<a href="#" title="心情" >心情</a>

				<a href="#" title="交友" >交友</a>

			</li>

		</ul>

	</div>

	<!--nav E -->

</div>

<!--header E -->