<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>


<div class="content clearfix">
	<!--ad01 E -->
    <div class="mypos fsong"> <a href="#">我的小家</a> &gt;创建相册</div>
    <div class="col1 ask_and_sea fl found">
       	<h2 class="col-h3">创建相册</h2>
		<form method="post" action="<%=path%>/user/album_add.action">
		<ul class="link_gray ul-li-s">
			
			<s:action var="listAlbum" name="album_list" namespace="/user" executeResult="false" ignoreContextParams="false">
					<s:param name="pageSize" value="10"></s:param>
			</s:action>

			<li>
				<s:iterator value="#listAlbum.albums" var="albums">
            		<label class="m-r10"><a href="#" target="_blank" class="red_f63"><s:property value="#albums.title"/></a>
            		<a href="<%=path%>/user/album_del.action?id='<s:property value="#albums.id" />'" class="m-l4">删除</a><a href="#" target="_blank" class="m-l4">修改</a>
				</s:iterator>
			</li>			
			
			<li><span>新增相册:</span><input name="album.title" type="text" class="inp_txt" /></li>			
				
			<li><input name="Submit1" type="submit" value="提 交" class="btn2"/></li>	
				
		</ul>
		</form>  
    </div>
       
    
  
    
</div>    
<!--content E -->

<%@ include file="../inc/templateFoot.jsp"%>