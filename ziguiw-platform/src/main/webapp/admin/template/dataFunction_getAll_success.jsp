<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">新闻：频道查询</p>
<form name="myform" id="myform" action="<%=path%>/admin/template/dataFunction_delete.action">
<table class="table">
	<colgroup>
	</colgroup>
	<thead>
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >全部
		</td>
		<td>
			函数ID
		</td>
		<td>
			函数名称
		</td>
		<td>
			函数内容
		</td>
		<td>
			参数
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:iterator value="dataFunctions" var="dataFunction">
	<tr>
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#channle.id"/> onclick='unselectall()'>
		</td>
		<td>
			<s:property value="#dataFunction.id"/>
		</td>
		<td>
			<s:property value="#dataFunction.name"/>
		</td>
		<td>
			<s:property value="#dataFunction.parameter"/>
		</td>
		<td>
			<s:property value="#dataFunction.content"/>
		</td>
		<td>
			<a href="<%=path%>/admin/template/dataFunction_getById.action?dataFunctionId=<s:property value="#dataFunction.id"/>">修改</a>
			|
			<a href="<%=path%>/dataFunction_delete.action?opIds=<s:property value="#dataFunction.id"/>">删除</a>
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