<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page import="com.zigui.service.impl.UserServiceImpl"%>
<%@ page import="com.zigui.service.impl.SchoolServiceImpl"%>
<%@ page import="com.zigui.service.impl.SpaceVisitHistoryServiceImpl"%>
<%@ page import="com.zigui.domain.SpaceVisitHistory"%>
<%@ page import="com.zigui.utils.BeanFactoryUtils"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ page import="com.zigui.domain.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath();
UserBase user = (UserBase)session.getValue("VALID_USER");
UserServiceImpl userService = (UserServiceImpl)BeanFactoryUtils.getBean("userService");
SchoolServiceImpl schoolService = (SchoolServiceImpl)BeanFactoryUtils.getBean("schoolService");
SpaceVisitHistoryServiceImpl spaceVisitHistoryService = (SpaceVisitHistoryServiceImpl)BeanFactoryUtils.getBean("spaceVisitHistoryService");


if(user != null){
user = userService.getUserBaseById(user.getId());
}

UserBase hostUser = null;
if(request.getParameter("hostUserName") != null && !"0".equals(request.getParameter("hostUserName"))){
	hostUser = userService.getUserBaseByNickName((request.getParameter("hostUserName")));
    request.setAttribute("hostUserName",request.getParameter("hostUserName"));
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

//if(hostUser == null || hostUser.getId() < 1){
//	response.sendRedirect(path + "/user/login.jsp");
//	return;
//}else{
//	spaceVisitHistoryService.addSpacePv(hostUser.getId());
//}

long hostUserId = hostUser.getId();
String hostUserName = hostUser.getNickName();

String head_120 = ImageUtils.getSizedImage(hostUser.getPortrait(), path + "/images/head.jpg", 120);

SchoolInfo schoolInfo = schoolService.getInfoBySchoolId(hostUserId);
School school = new School();
if(hostUser!=null&&hostUser.getType() == 1 && hostUser.getForeignKey() != null && hostUser.getForeignKey() > 0){
	school = schoolService.getById(hostUser.getForeignKey());
    request.setAttribute("xxId",school.getXxID());
}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta content="text/html; charset=utf-8" />
<title><%=school.getSch_name()%></title>
	<meta name="generator" content="editplus" />
	<meta name="author" content="<%=hostUserName%>" />
	<meta name="keywords" content="<%=hostUserName%>_子贵网" />
	<meta name="description" content="<%=hostUserName%>_子贵网" />
	<link href="<%=path%>/css/basc.css" rel="stylesheet" type="text/css">
	<link href="<%=path%>/css/usercenter.css" rel="stylesheet" type="text/css">
	<script src="<%=path%>/js/jquery-1.7.2.min.js"></script>
	<script src="<%=path%>/js/basic.js.jsp"></script>
	<script src="<%=path%>/js/image.js"></script>
</head>
<body class="home_body">
<div class="toolbar">
	<div class="tit x_head">
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
<div class="w_w990" style="background:#fff; height:170px ">
<img src="<%=path%>/images/w_user_banner.jpg" style="float:right">
<%if("4301113000".equals(school.getXxID())){%>
<img src="<%=path%>/images/dizhilogo.jpg" style="width:240px;height:160px">
<%}else{%>
    <img src="<%=path%>/images/fly.jpg" style="width:240px;height:160px">
<%}%>
    
</div>
<div class="w_w990 w_user_menu" >
	<ul>
    	<li style="width:90px"><a href="<%=path%>/school/index.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>学校首页</span></a></li>
	<li style="width:90px"><a href="<%=path%>/user/getSchoolSummary.action?hostUserName=<%=hostUser.getNickName()%>"><span>学校简介</span></a></li>
	<li style="width:90px"><a href="<%=path%>/school/school_news.jsp?type=5&hostUserName=<%=hostUser.getNickName()%>"><span>校园新闻</span></a></li>
	<li style="width:90px"><a href="<%=path%>/school/school_news.jsp?type=6&hostUserName=<%=hostUser.getNickName()%>"><span>学校公告</span></a></li>
	<%--<li style="width:90px"><a href="<%=path%>/school/school_news.jsp?type=7&hostUserName=<%=hostUser.getNickName()%>"><span>招生信息</span></a></li>--%>
	<li style="width:90px"><a href="<%=path%>/school/teachers.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>师资力量</span></a></li>
	<li style="width:90px"><a href="<%=path%>/user/school_album_list.action?hostUserName=<%=hostUser.getNickName()%>"><span>校园风光</span></a></li>
    <li style="width:90px"><a href="<%=path%>/user/classList.action?xxId=<%=school.getXxID()%>&hostUserName=<%=hostUser.getNickName()%>"><span>班级天地</span></a></li>
	<li style="width:90px"><a href="<%=path%>/school/leave_message.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>留言评论</span></a></li>
	<%--<li style="width:90px"><a href="<%=path%>/user/getSchoolJob.action?hostUserName=<%=hostUser.getNickName()%>"><span>招聘信息</span></a></li>--%>
	<%if(isHost) {%><li style="width:90px"><a href="<%=path%>/school/school_manager.jsp"><span>管理中心</span></a></li><%} %>
    </ul>
</div>
