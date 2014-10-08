<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>
<script src="<%=path%>/js/addClickCount.js.jsp"></script> 
<div class="content clearfix  pr">
<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">新闻详情</h3>
	</div>

<div class="my_art_info bordb dygs mb10 ">
    		<h1 style="text-align:center"><s:property value="diary.title" escape="false"/></h1>
            
            <p><s:property value="diary.text" escape="false"/></p>
    	</div>
		
	</div>

<!--content E -->

<input type="hidden" name="resourceType" id="resourceType" value="diary">
<input type="hidden" name="resourceId" id="resourceId" value="<s:property value="diary.id"/>">



<%@ include file="../inc/templateFoot.jsp"%>

<script>
</script>