<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">模板管理：新增模板</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/template/template_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:820px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>模版名称</span>
			<input name="template.name" type="text" class="inp_txt" value="${template.name}" />
		</td>
		
		<td class="v-b" ><span class="red_star">*</span>频道名称</td>
	</tr>
	
	<tr>
		<td><span>模版描述</span>
			<input name="template.description" type="text" class="inp_txt" value="${template.description}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>频道名称</td>
	</tr>
	
	
	<tr>
		<td><span>模版内容</span>
			<textarea name="template.content" type="text" rows="30" cols="180" id="templateContent">${template.displayContent}</textarea>
		</td>
	</tr>
	<input type="hidden" name="template.id" value=${template.id}>

</table>

<p class="submit">
<input name="testTemplate" id="testTemplate" type="button" value="预览"  class="inp_btn"/>
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>

<%@ include file="../inc/foot.jsp"%>