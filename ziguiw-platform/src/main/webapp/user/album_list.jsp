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
		<h3 class="fyahei">我的相册<%if(isHost){ %><a onclick="createAlbum()" style="position:absolute;right:110px;display:inline" class="r" href="javascript:void(0)">创建相册</a>&nbsp<a style="position:absolute;right:35px;display:inline" class="r" href="<%=path%>/user/photo_upload.jsp">上传图片</a><%} %></h3>
	</div>
	
	<div class="bordb big_dygs wdxc p_b10 mb10 l">
		<ul class="img_f l">
		<s:iterator value="albums.list" var="album">
			<li>
			<a class="big-img1"  href="<%=path%>/user/getPhotoByAlbumId.action?albumId=<s:property value='#album.id'/>&hostUserName=<s:property value="#album.user.nickName"/>">
				<img  onload="javascript:DrawImage(this, 160, 160)" src="/${album.coverPhoto.url}" alt="">
			</a>
			<a href="<%=path%>/user/getPhotoByAlbumId.action?albumId=<s:property value='#album.id'/>&hostUserName=<s:property value="#album.user.nickName"/>">
			<s:if test="#album.title.length() > 12">
                   <s:property value="#album.title.substring(0, 10)"/>...
		    </s:if>
            <s:else>
				<s:property value='#album.title'/>
			</s:else>
			&nbsp;</a>
			<%if(isHost){ %>
			<div style="text-align:center">
			<a style="display:inline;text-align:center" href="<%=path%>/user/delAlbumById.action?id=<s:property value='#album.id'/>">删除</a>
			<a style="display:inline;text-align:center" href="javascript:modifyAlbum(<s:property value='#album.id'/>,'<s:property value='#album.title'/>')">修改</a>
			</div>
			<%} %>
			</li>
		</s:iterator>      
		</ul>       
	
  </div>
  </div>

</div>
</div>
<!--content E -->
<link href="http://static01.babytreeimg.com/img/css/boxy2.css?ver=20110707" rel="stylesheet" type="text/css" />
<script src="http://static01.babytreeimg.com/img/js/boxy2.js?ver=1315188206" type="text/javascript"></script>

<!--创建相册-->
<div id="create_album_dialog" style="display:none;">
<div class="alert alert-620">
	<form action="<%=path%>/user/album_saveOrUpdate.action" method="post" id="form_create_album" name="form_create_album" >
  <div class="body">
    <div class="cont">
      <ul class="padding-25">
        <li class="margin-10">
          <label class="label font-14">相册标题：</label>
          <input type="text" class="txt input-265" name="album.title" id="albumTitle" validation="required" maxlength="20"/><span  style="display:none;color:red;">相册标题不能为空</span>
        </li>
        <li class="margin-10" onclick="document.getElementById('album-placeholder').style.display='none';">
          <label class="label_top font-14">相册描述：</label>
          <textarea rows="5" class="area" name="album.text"  id="album_description"></textarea>
          <span style="position: absolute; top: 70px; color: rgb(153, 153, 153); left: 134px;" class="placeholder" id="album-placeholder">都有谁？在什么地方？什么活动？</span>
          <span  style="display:none;color:red;"></span>
        </li>
        
        <input type="hidden" name="album.id" id="modifyAlbumId">
       
        <li class="nopadding btn-box">
          <input type="submit" value="确定" class="btn2"/>
          <img src="/img/common/loading_pic_s.gif" style="display:none" id="form_create_album_loading"/>
        </li>
      </ul>
    </div>
  </div>
  </form>
  </div>
  </div>
</div>
  </div>
<script>
jQuery(document).ready(function(){
	 boxy2_init(true);    //里面的参数表示是否带标题栏
	});
function createAlbum(){
	boxy2.pop(jQuery('#create_album_dialog').html(),620,220); 
	boxy2.title('创建相册');
}

function modifyAlbum(albumId, title){
	$("#modifyAlbumId").val(albumId);
	$("#albumTitle").attr("value", title);
	
	boxy2.pop(jQuery('#create_album_dialog').html(),620,220); 
	boxy2.title('修改相册');
}
</script>

<%@ include file="../inc/templateFoot.jsp"%>