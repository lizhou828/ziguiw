<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>
<script src="<%=path%>/js/addClickCount.js.jsp"></script> 
<div class="content clearfix  pr">

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">校长致辞</h3>
	</div>

	<div class="hei-400">
	<div class="my_art_info">
    		<p><s:property value="schoolInfo.speech" escape="false"/></p>
    	</div>
		
	</div>
	
	</div>

<!--content E -->

<input type="hidden" name="resourceType" id="resourceType" value="diary">
<input type="hidden" name="resourceId" id="resourceId" value="<s:property value="diary.id"/>">



<%@ include file="../inc/templateFoot.jsp"%>

<script>
</script>