<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="inc/home_head.jsp"%>  
<div class="w_home_content">
  <div class="w_home_content_box clearfix">
<div class="content clearfix  pr">
    <%@ include file="inc/right.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400 r">
       	<h2 class="col-h3">更改头像</h2>

		<form method="post" action="<%=path%>/user/user_portrait.action" name="form" enctype="multipart/form-data">
		<ul class="link_gray ul-li-s">
            <c:choose>
                <c:when test="${not empty user.portrait}">

                    <li><span>我的头像:</span>
                        <a class="a-img1 l" target="_blank" href="#">
                            <img  src="<%=path%><%=user.getPortrait()%>"  alt=" "/>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><span>我的头像:</span><a class="a-img1 l" target="_blank" href="#"><img src="<%=path%>/images/head_120_120.jpg" alt=" "></a></li>
                </c:otherwise>
            </c:choose>




			<li><span>更换头像：</span><input name="uploadFile" type="file" class="inp_txt" /></li>	
				
			<li><input name="Submit1" type="submit" value="提 交" class="btn2"/></li>
		</ul>
		</form> 
    </div>
</div>    
</div>
</div>
<!--content E -->
<%@ include file="../inc/templateFoot.jsp"%>