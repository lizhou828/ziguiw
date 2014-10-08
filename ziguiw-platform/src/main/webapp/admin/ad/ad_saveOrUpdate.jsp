<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">广告：新增广告</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/ad/ad_saveOrUpdate.action" enctype="multipart/form-data">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>广告位名字</span>
			<input name="ad.name" type="text" class="inp_txt" value="${ad.name}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>广告位名字,主要是便于记忆</td>
	</tr>
	<tr>
		
		<td><span>广告位宽度</span>
			<input name="ad.width" type="text" class="inp_txt" value="${ad.width}" />
		</td>
		<td class="v-b" >广告位宽度</td>
	</tr>
	<tr>
		
		<td><span>广告位高度</span>
			<input name="ad.height" type="text" class="inp_txt" value="${ad.height}" />
		</td>
		<td class="v-b" >广告位高度</td>
	</tr>
	<tr>
		
		<td><span>广告位连接</span>
			<input name="ad.link" type="text" class="inp_txt" value="${ad.link}" />
		</td>
		<td class="v-b" >广告位连接</td>
	</tr>
	<tr>
		
		<td><span>广告位描述</span>
			<input name="ad.description" type="text" class="inp_txt" value="${ad.description}" />
		</td>
		<td class="v-b" >广告位描述</td>
	</tr>
	<tr>
		
		<td><span>广告位图片</span>
			<input name="uploadFile" type="file" />
		</td>
		<td class="v-b" >广告位图片</td>
	</tr>
	

</table>

<input type="hidden" name="ad.id" value="${ad.id}">

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>

<%@ include file="../inc/foot.jsp"%>