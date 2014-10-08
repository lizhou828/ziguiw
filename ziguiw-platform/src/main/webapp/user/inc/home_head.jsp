<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.zigui.domain.UserBase"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page import="com.zigui.service.impl.UserServiceImpl"%>
<%@ page import="com.zigui.service.impl.*"%>
<%@ page import="com.zigui.domain.SpaceVisitHistory"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ page import="com.zigui.utils.BeanFactoryUtils"%>
<%@ page import="com.zigui.domain.UserBase"%>
<%@ page import="com.zigui.domain.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath();
UserBase user = (UserBase)session.getAttribute("VALID_USER");
UserServiceImpl userService = (UserServiceImpl)BeanFactoryUtils.getBean("userService");
SpaceVisitHistoryServiceImpl spaceVisitHistoryService = (SpaceVisitHistoryServiceImpl)BeanFactoryUtils.getBean("spaceVisitHistoryService");

if(user != null){
user = userService.getUserBaseById(user.getId());
if(user.getState() < 2){
	response.sendRedirect(path + "/user/info_jump.jsp?kind=12");
}
}

UserBase hostUser = null;
if(request.getParameter("hostUserName") != null && !"0".equals(request.getParameter("hostUserName"))){
	hostUser = userService.getUserBaseByNickName((request.getParameter("hostUserName")));
}else{
	hostUser = user;
}
boolean isHost = false;

if(user != null && hostUser != null && user.getId() != hostUser.getId()){
	SpaceVisitHistory spaceVisitHistory = new SpaceVisitHistory();
	spaceVisitHistory.setFormUser(user);
	spaceVisitHistory.setToUser(hostUser);
	
	spaceVisitHistoryService.saveOrUpdate(spaceVisitHistory);
}


if(user != null && hostUser != null && user.getId() == hostUser.getId()){
	isHost = true;
	user = hostUser;
}

if(hostUser == null || hostUser.getId() < 1){
	response.sendRedirect(path + "/user/login.jsp");
	return;
}else{
	spaceVisitHistoryService.addSpacePv(hostUser.getId());
}

long hostUserId = hostUser.getId();
String hostUserName = hostUser.getNickName();

String head_120 = ImageUtils.getSizedImage(hostUser.getPortrait(), path + "/images/head.jpg", 120);
%>
<!DOCTYPE HTML>
<html>
<head>
<meta content="text/html; charset=utf-8" />
<title><%=com.zigui.utils.CommonUtils.getHeadInfo().get("title")%></title>
	<meta name="generator" content="editplus" />
	<meta name="author" content="" />
	<meta name="keywords" content="<%=com.zigui.utils.CommonUtils.getHeadInfo().get("keywords")%>" />
	<meta name="description" content="<%=com.zigui.utils.CommonUtils.getHeadInfo().get("description")%>" />
	<link href="<%=path%>/css/basc.css" rel="stylesheet" type="text/css">
    <link href="<%=path%>/css/xiaohome.css" rel="stylesheet" type="text/css">
	<script src="<%=path%>/js/jquery-1.7.2.min.js"></script>
	<script src="<%=path%>/js/basic.js.jsp"></script>
	<script src="<%=path%>/js/image.js"></script>
</head>
<body class="w_home_body">
<div class=" w_home_toolbar">
	<div class="tit x_head w_home_toolbar_title">
		<a href="<%=path%>/index.jsp" title="首   页" target="_blank">首   页</a>
		<a href="<%=path%>/news/index" title="教育在线" target="_blank">教育在线</a>
		<a href="<%=path%>/study/index" title="教育知识" target="_blank">教育知识</a>
		<a href="<%=path%>/community/index" title="互动社区" target="_blank">互动社区</a>
		<a href="<%=path%>/user/home_index.jsp" title="我的小家" target="_blank">我的小家</a>
	</div>
	<div class="subNav">
		<a href="<%=path%>/user/login.jsp" title="用户登录" target="_blank">用户登录</a>
		<a href="<%=path%>/user/register.jsp" title="新用户注册" target="_blank">新用户注册</a>
	</div>
</div>


<div class="w_home_header">
    <div class="w_home_treenav">
		<h1><%=hostUser.getNickName()%>的个人空间</h1>
		<div class="bloglink">
		<br>欢迎光临我的小家
	    </div>		
	</div>
	<ul class="w_home_nav">
	<li class="active new"><a href="<%=path%>/user/portal.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>小家主页</span></a></li>
	<li><a href="<%=path%>/user/diary_list.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>日记</span></a></li>
	<li><a href="<%=path%>/user/album_list.action?hostUserName=<%=hostUser.getNickName()%>"><span>相册</span></a></li>
	<li><a href="<%=path%>/user/home.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>心情</span></a></li>
	<li><a href="<%=path%>/user/leave_message.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>留言板</span></a></li>
	<li><a href="<%=path%>/user/forum_list.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>帖子</span></a></li>
	<li><a href="<%=path%>/user/getMyFriend.action?hostUserName=<%=hostUser.getNickName()%>"><span>好友</span></a></li>
	<li><a href="<%=path%>/user/point_history.jsp"><span>积分明细</span></a></li>
	<%if(isHost){ %>
	<li><a href="<%=path%>/user/mail.jsp?kind=1"><span>站内信</span></a></li>

	<%} %>
	<li><a href="<%=path%>/user/play_list.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>小游戏</span></a></li>
	<%if(isHost){ %>
	<li><a href="<%=path%>/userCenter/userCenter.jsp"><span>用户中心</span></a></li>

	<%} %>
	</ul>

	<form action="<%=path%>/search.action">
	<div class="search">

		<input type="text" id=""  name="keyWords" class="text">
		<input type="hidden" id="" value="user"  name="type" class="text">

		<input type="submit" id="" value="搜索" name="" class="btn">

	</div>
	</form>
</div>
