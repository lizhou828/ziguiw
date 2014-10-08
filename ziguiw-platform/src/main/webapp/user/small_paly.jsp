<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>  

<div class="content clearfix  pr">
	<%@ include file="inc/right.jsp"%>
	
<div class="r w-770   hei-400">

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">小游戏</h3>
	</div>
	<div class="bordb dygs wdxc p_b10 mb10">
		<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" 
   codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/
swflash.cab#version=7,0,0,0" 
   width="550" height="400" id="Untitled-1" align="middle">
<param name="allowScriptAccess" value="sameDomain" />
<param name="movie" value="<%=path%>/flash/<%=request.getParameter("play")%>" />
<param name="quality" value="high" />
<param name="bgcolor" value="#ffffff" />
<embed src="<%=path%>/flash/<%=request.getParameter("play")%>" quality="high" bgcolor="#ffffff" width="550" 
   height="400" name="mymovie" align="middle" allowScriptAccess="sameDomain" 
   type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
</object>
	</div>
	</div>
	
  </div>

</div>
<!--content E -->

<%@ include file="../inc/templateFoot.jsp"%>