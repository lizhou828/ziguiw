<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>
<script src="<%=path%>/js/addClickCount.js.jsp"></script> 
<div class="content clearfix  pr">
<%@ include file="inc/right.jsp"%>
	<div class=" mypos1 fsong r w-770 mb10"></div>
<div class="r w-770   hei-400">
<div class="my_art_info">
    		<h1 style="text-align:center"><s:property value="diary.title" escape="false"/></h1>
            
            <p><s:property value="diary.text" escape="false"/></p>
    	</div>
		
	</div>

</div>
<!--content E -->

<input type="hidden" name="resourceType" id="resourceType" value="diary">
<input type="hidden" name="resourceId" id="resourceId" value="<s:property value="diary.id"/>">



<%@ include file="../inc/templateFoot.jsp"%>

<script>
</script>