<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.zigui.domain.UserBase"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext,com.zigui.utils.CommonUtils,org.apache.commons.collections.CollectionUtils"%>
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
UserBase user = (UserBase)session.getValue("VALID_USER");

UserServiceImpl userService = (UserServiceImpl)BeanFactoryUtils.getBean("userService");

if(user != null){
user = userService.getUserBaseById(user.getId());
}

UserBase hostUser = user;

boolean isHost = false;

if(user != null && hostUser != null && user.getId() == hostUser.getId()){
	isHost = true;
	user = hostUser;
}

if(hostUser == null || hostUser.getId() < 1){
	response.sendRedirect(path + "/user/login.jsp");
	return;
}

//如果用户状态小于5，表示用户还没有被认证通过
if(hostUser.getState() < 5){
	response.sendRedirect(path + "/user/info_jump.jsp?kind=8");
	return;
}

if(hostUser.getType() == 2){
	CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
	Teacher teacher = null;
	if(user.getType() == 2 && user.getForeignKey() != null  && user.getForeignKey() > 0){
		teacher = commonService.get(Teacher.class, user.getForeignKey());
	}
	
	if(teacher.getSchool() == null || teacher.getSchool().getXx_ID() < 1){
		response.sendRedirect(path + "/user/info_jump.jsp?kind=1");
		return;
	}
	
	if(CommonUtils.getClaszByTeacher(user) == null || CollectionUtils.isEmpty(CommonUtils.getClaszByTeacher(user).getList())){
		response.sendRedirect(path + "/user/info_jump.jsp?kind=2");
		return;
	}
}

if(hostUser.getType() == 4){
	CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
	Student student = null;
	if(user.getType() == 4 && user.getForeignKey() != null  && user.getForeignKey() > 0){
		student = commonService.get(Student.class, (int)user.getForeignKey().longValue());
	}
	
	if(student.getSchool() == null || student.getSchool().getXx_ID() < 1){
		response.sendRedirect(path + "/user/info_jump.jsp?kind=3");
		return;
	}
	
	if(student.getClasz() == null || student.getClasz().getBj_id() < 1){
		response.sendRedirect(path + "/user/info_jump.jsp?kind=4");
		return;
	}
}

if(hostUser.getType() == 3){
	CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
	Parent parent = null;
	if(user.getType() == 3 && user.getForeignKey() != null  && user.getForeignKey() > 0){
		parent = commonService.get(Parent.class, (int)user.getForeignKey().longValue());
	}
	
	Student student = parent.getStudent();
	
	if(student == null){
		response.sendRedirect(path + "/user/info_jump.jsp?kind=5");
		return;
	}
	
	if(student.getSchool() == null || student.getSchool().getXx_ID() < 1){
		response.sendRedirect(path + "/user/info_jump.jsp?kind=6");
		return;
	}
	
	if(student.getClasz() == null || student.getClasz().getBj_id() < 1){
		response.sendRedirect(path + "/user/info_jump.jsp?kind=7");
		return;
	}
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
	<style type="text/css"> 
	　　.jz-i{
	    background: none repeat scroll 0 0 #F8F8F8;
	    border: 1px solid #E0E0E0;
	    height: auto;
	    padding: 8px;
	    width: 528px;
		}
	</style> 
	<script src="<%=path%>/js/jquery-1.7.2.min.js"></script>
	<script src="<%=path%>/js/basic.js.jsp"></script>
	<script src="<%=path%>/DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="<%=path%>/js/image.js"></script>
</head>
<body>
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


<div class="header"  style=" height:160px;>
<div class="treenav">
	<table style="font-size:14px; margin-left:50px;" border="0">
	<tr>
	<td width="160px" height="130" style=" overflow:hidden">
					<div class="userAvatar" style="  height:135px;">
            			<div class="avatar-120" style=" margin:0px"><img alt=" " src="<%=ImageUtils.getSizedImage(hostUser.getPortrait(), path + "/images/head.jpg", 120)%>" />
                    </div>
	</td>
	<td width="300px" style=" line-height:30px">
		<div>姓名：<%=hostUser.getRealName()%></div>

		<div>帐号：<%=hostUser.getNickName()%></div>
		
		<div>身份类别：<%if(hostUser.getType() == 1){%>学校<%}%><%if(hostUser.getType() == 2){%>教师<%}%><%if(hostUser.getType() == 3){%>家长<%}%><%if(hostUser.getType() == 4){%>学生<%}%></div>
        <a href="<%=path%>/user/select_role.jsp"><input type="button" class="actionBtn7" onclick="window.location.href='<%=path%>/user/select_role.jsp';" style=" line-height:normal" value="完善资料"></input></a><a href="<%=path%>/user/update_passwd.jsp"><input type="button" class="actionBtn7" onclick="window.location.href='<%=path%>/user/update_passwd.jsp';" style=" line-height:normal" value="修改密码"></input></a>
	</td>
	<td class="bj9 font-2">
		<div style="line-height:250%">我的积分：<span style=" font-size:150%; color:#fd6e1f; font-weight:bold;"><%=hostUser.getPoints()%></span></div>
        <div style="font-size:12px; color:gray">上次登录时间：	<%=hostUser.getFormatedLastLoginTime()%></div>
	</td>
	</tr>
	</table>


	</div>


</div>
<div class="header" style="height:50px">
	<ul class="nav_sub_tit mt5">
	    <li class="active new"><a href="<%=path%>/user/portal.jsp?hostUserName=<%=hostUser.getNickName()%>"><span>我的空间</span></a></li>
<li class="active new"><a href="#"><span>我的资源</span></a></li>			
<li class="active new"><a href="<%=path%>/user/point_history.jsp"><span>我的积分</span></a></li>	
<li class="active new"><a href="<%=path%>/study/yuer/list/3"><span>我的问答</span></a></li>		
<li class="active new"><a href="#"><span>网站公告</span></a></li>	
<li><a href="#"><span>智学宝</span></a></li>
	    </ul>
	</div>
