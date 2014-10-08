<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>  


<div class="content clearfix pr">
	<!--ad01 E -->
	
	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">上传图片</h3>
	</div>
  
    <div class="ask_and_sea found hei-400 l">
		<form method="post" action="<%=path%>/user/school_photo_add.action" enctype="multipart/form-data">
<div class="red"><s:fielderror/></div>		
<ul class="link_gray ul-li-s">
			<s:action var="listAlbum" name="album_list" namespace="/user" executeResult="false" ignoreContextParams="false">
					<s:param name="pageSize" value="10"></s:param>
			</s:action>
			
			<li><span>选择相册:</span> <a onclick="createAlbum()" class="r" style="left:95px;display:inline"><input type="button" class="btn2 r mt5" value="创建相册" /></a>
				<select name="photo.albumId">
					<s:iterator value="#listAlbum.albums.list" var="albums">
						<option value='<s:property value="#albums.id"/>'><s:property value="#albums.title" /></option>
            		</s:iterator>
				</select>
			</li>
			
			<li><span>上传图片:</span>
				<div class="up_photo clearfix pr">
					<div class="a-img1 l" id="localImag"><img alt="" src="<%=path%>/images/head_120_120.jpg" id="preview"></div> 
					<textarea class="area l" name="photo.text">相片简介</textarea>
				</div>
			
				<input name="file" type="file" class="inp_txt" id="doc" onchange="javascript:setImagePreview();"/>
			
			</li>	
				
			<li><input name="Submit1" type="submit" value="提 交" class="btn2"/></li>	
				
		</ul>
		</form>  
    </div>
    
  </div>
    

<!--content E -->
<link href="http://static01.babytreeimg.com/img/css/boxy2.css?ver=20110707" rel="stylesheet" type="text/css" />
<script src="http://static01.babytreeimg.com/img/js/boxy2.js?ver=1315188206" type="text/javascript"></script>

<!--创建相册-->
<div id="create_album_dialog" style="display:none;">
<div class="alert alert-620">
	<form action="<%=path%>/user/school_album_saveOrUpdate.action" method="post" id="form_create_album" name="form_create_album" >
  <div class="body">
    <div class="cont">
      <ul class="padding-25">
        <li class="margin-10">
          <label class="label font-14">相册标题：</label>
          <input type="text" class="txt input-265" name="album.title" id="album.title" validation="required" value="2012-08的相册" maxlength="20"/><span  style="display:none;color:red;">相册标题不能为空</span>
        </li>
        <li class="margin-10" onclick="document.getElementById('album-placeholder').style.display='none';">
          <label class="label_top font-14">相册描述：</label>
          <textarea rows="5" class="area" name="album.text"  id="album_description"></textarea>
          <span style="position: absolute; top: 70px; color: rgb(153, 153, 153); left: 134px;" class="placeholder" id="album-placeholder">都有谁？在什么地方？什么活动？</span>
          <span  style="display:none;color:red;"></span>
        </li>
        
        <input type="hidden" name="album.id" id="modifyAlbumId"></input>
       
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
<script>
jQuery(document).ready(function(){
	 boxy2_init(true);    //里面的参数表示是否带标题栏
	});
function createAlbum(){
	boxy2.pop(jQuery('#create_album_dialog').html(),620,220); 
	boxy2.title('创建相册');
}
function setImagePreview() {
    var docObj=document.getElementById("doc");

    var imgObjPreview=document.getElementById("preview");
            if(docObj.files &&    docObj.files[0]){
                    //火狐下，直接设img属性
                    imgObjPreview.style.display = 'block';
                    imgObjPreview.style.width = '300px';
                    imgObjPreview.style.height = '120px';                    
                    //imgObjPreview.src = docObj.files[0].getAsDataURL();

				  //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式  
				  imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

            }else{
                    //IE下，使用滤镜
                    docObj.select();
                    var imgSrc = document.selection.createRange().text;
                    var localImagId = document.getElementById("localImag");
                    //必须设置初始大小
                    localImagId.style.width = "300px";
                    localImagId.style.height = "120px";
                    //图片异常的捕捉，防止用户修改后缀来伪造图片
			try{
                            localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                            localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
                    }catch(e){
                            alert("您上传的图片格式不正确，请重新选择!");
                            return false;
                    }
                    imgObjPreview.style.display = 'none';
                    document.selection.empty();
            }
            return true;
    }
</script>
</script>

<%@ include file="../inc/templateFoot.jsp"%>