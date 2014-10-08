<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>  

<div class="content clearfix  pr">
	

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">校园风光</h3><a href="<%=path%>/school/photo_upload.jsp" class="r btn4">上传图片</a>
	</div>
	<div class="bordb dygs wdxc p_b10 mb10">
		<ul class="img_f">
		<s:iterator value="photos" var="photo">
			<li><a class="a-img1" href="<%=path%>/user/getSchoolRoundPhoto.action?photoId=<s:property value='#photo.id'/>&albumId=${albumId}&hostUserName=<%=hostUser.getNickName()%>"  target="blank">
                <img src="${ctx}/<%=(String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#photo.url")%>" alt=""></a>
			
			<%if(isHost){ %>
			<div style="text-align:center">
			<a style="display:inline;text-align:center" href="<%=path%>/user/delSchoolPhoto.action?photoId=<s:property value='#photo.id'/>&albumId=${albumId}">删除</a>
			<a style="display:inline;text-align:center" href="<%=path%>/user/saveSchoolAlbumCover.action?album.coverPhoto.id=<s:property value='#photo.id'/>&album.id=${albumId}">置封面</a>
			</div>
			<%} %>
			</li>
		</s:iterator>      
		</ul>
	</div>
	
  </div>


<!--content E -->
<%@ include file="../inc/templateFoot.jsp"%>