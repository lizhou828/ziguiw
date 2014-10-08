<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>  

<div class="w_home_content">
  <div class="w_home_content_box clearfix">
  
  
<div class="content clearfix  pr">
	<%@ include file="inc/right.jsp"%>
	
<div class="r w-770   hei-400">

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">我的好友</h3>
	</div>
	<div class="bordb dygs wdxc p_b10 mb10">
		<ul class="img_f">
		<s:iterator value="userFriends.list" var="userFriend">
			<li><a class="a-img1" href="<%=path%>/myhome/<s:property value='#userFriend.friendUser.nickName'/>"  target="blank"><img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#userFriend.friendUser.portrait"), path + "/images/head.jpg", 70)%>" alt=""></a>
			
			<div style="text-align:center">
			<a style="display:inline;text-align:center" href="<%=path%>/myhome/<s:property value='#userFriend.friendUser.nickName'/>"><s:property value='#userFriend.friendUser.nickName'/></a>
			</div>
			</li>
		</s:iterator>      
		</ul>
	</div>
	</div>
	</div>
  </div>
</div>
</div>
<!--content E -->

<%@ include file="../inc/templateFoot.jsp"%>