<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">模版列表，注意：模版请谨慎使用删除操作，否则可能导致页面生成失败</p>
<form name="myform" id="myform" action="<%=path%>/template_delete.action">
<table class="table">
	<colgroup>
		
	</colgroup>
	<thead>
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >全部
		</td>
		<td>
			模版ID
		</td>
		<td>
			模版名称
		</td>
		<td>
			模版描述
		</td>
		<td>
			最后更新时间
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:iterator value="templates" var="template">
	<tr>
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#template.id"/> onclick='unselectall()'>
		</td>
		<td>
			<s:property value="#template.id"/>
		</td>
		<td>
			<s:property value="#template.name"/>
		</td>
		<td>
			<s:property value="#template.description"/>
		</td>
		<td>
			<s:property value="#template.formatedLastModifyTime"/>
		</td>
		<td>
			<a href="<%=path%>/admin/template/template_getById.action?templateId=<s:property value="#template.id"/>">修改</a>
			|
			<a href="<%=path%>/admin/template/template_remove.action?opIds=<s:property value="#template.id"/>">删除</a>
		</td>
	</tr>
</s:iterator>
</table>

</p>
<input type="submit" value="删除" class="inp_btn">
</form>
</div>
<script type="text/javascript">
//JavaScript Document


//转载请保留出处 http://www.dwww.cn 
function unselectall(){
if(document.myform.chkAll.checked){
document.myform.chkAll.checked = document.myform.chkAll.checked&0;
}
}
function CheckAll(form){
for (var i=0;i<form.elements.length;i++){
var e = form.elements[i];
if (e.Name != 'chkAll'&&e.disabled==false)
e.checked = form.chkAll.checked;
}
}



</script>
<%@ include file="../inc/foot.jsp"%>