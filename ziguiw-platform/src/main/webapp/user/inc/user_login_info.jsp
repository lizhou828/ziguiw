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

UserServiceImpl userService = (UserServiceImpl)BeanFactoryUtils.getBean("userService");
SpaceVisitHistoryServiceImpl spaceVisitHistoryService = (SpaceVisitHistoryServiceImpl)BeanFactoryUtils.getBean("spaceVisitHistoryService");

if(user != null){
user = userService.getUserBaseById(user.getId());
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