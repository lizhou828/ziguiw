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
		<h3 class="fyahei">我的相册</h3> <a href="<%=path%>/user/photo_upload.jsp" class="r btn4">上传图片</a>
	</div>
	<div class="bordb dygs wdxc p_b10 mb10">
		<ul class="img_f">
		<s:iterator value="photos" var="photo">
			<li><a class="a-img1" href="<%=path%>/user/getRoundPhoto.action?photoId=<s:property value='#photo.id'/>&albumId=${albumId}&hostUserName=<%=hostUser.getNickName()%>"  target="blank"><img src="/<s:property value='#photo.url'/>" alt=""></a>
			
			<%if(isHost){ %>
			<div style="text-align:center">
			<a style="display:inline;text-align:center" href="<%=path%>/user/delPhoto.action?photoId=<s:property value='#photo.id'/>&albumId=${albumId}">删除</a>
			<a style="display:inline;text-align:center" href="<%=path%>/user/saveAlbumCover.action?album.coverPhoto.id=<s:property value='#photo.id'/>&album.id=${albumId}">置封面</a>
			</div>
			<%} %>
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