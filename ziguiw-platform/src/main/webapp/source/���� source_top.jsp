<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" isELIgnored = "false"%>

<%@ include file="../comm/common_tag.jsp" %>
<%@ taglib prefix="s" uri= "/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>子贵网--资源下载中心</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->		
  </head>
  
  <body>
  <jsp:include page="member_top_top.jsp"></jsp:include>
   		<div class="t-box">
			<div class="t-logo"><img src="source/images/index_05.jpg" /></div>
			
			<form id="form1" action="source/source!list_qt.action" method="post" class="form">		
			<div class="t-sousuo-box">
			  <div class="t-sousuo-box1">
			  	<div class="t-sousuo-box-shu"><input name="selstr" type="text" class="shurukuan-01" />
 <input type="hidden"  name="seltj" value="10" />			  	  
</div>
			    <input name="Submit" type="submit" class="t-sousuo-box-an" value="提交" />
		      </div>
			  <div class="t-sousuo-box2">
			    <input name="filetype" type="radio" value="" checked="checked" />
			  全部                                                               
			  <input type="radio" name="filetype" value="TXT" />
			  TXT  
			  <input type="radio" name="filetype" value="DOC" />
			  DOC  
			  <input type="radio" name="filetype" value="PDF" />
			  PDF
                <input type="radio" name="filetype" value="PPT" />
			  PPT  
			  <input type="radio" name="filetype" value="RAR" />
			  RAR
                <input type="radio" name="filetype" value="EXE" />
			  EXE  
			  <input type="radio" name="filetype" value="CHM" />
			  CHM
                <input type="radio" name="filetype" value="FLV" />
			  FLV</div>
			</div>
			</form>
		</div>
		<div class="t-link_box">
	<ul>
		<li>
			<a href="source/sourceInfo.action">资源首页</a>
		</li>
		<li>
			<a href="source/schoolInfo.action?leve=1">小学资源</a>
		</li>
		<li>
			<a href="source/schoolInfo.action?leve=2">初中资源</a>
		</li>
		<li>
			<a href="source/schoolInfo.action?leve=3">高中资源</a>
		</li>
	</ul>
</div>
		<div class="t-AD-03">
          <p class="text-1-3"></p>
		  <p><a href="source/addsourceGet!addget.action"><img src="source/images/zaixiananniu.jpg" border="0"/></a></p>
	  </div>
  </body>
</html>
