<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">知识：频道查询</p>
<form name="myform" id="myform" action="<%=path%>/admin/knowledge/knowledgeChannle_batchDelete.action">
<table class="table">
	<colgroup>
	</colgroup>
	<thead>
	<!--  
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >全部
		</td>
	-->
		<td>
			频道ID
		</td>
		<td>
			频道名称
		</td>
		<td>
			创建时间
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:iterator value="knowledgeChannles" var="channle">
	<tr>
	<!--  
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#channle.id"/> onclick='unselectall()'>
		</td>
	-->
		<td>
			<s:property value="#channle.id"/>
		</td>
		<td>
			<s:property value="#channle.name"/>
		</td>
		<td>
			<s:property value="#channle.formatedStartTime"/>
		</td>
		<td>
			<a href="<%=path%>/admin/knowledge/knowledgeChannle_getById.action?channleId=<s:property value="#channle.id"/>">修改</a>
			|<a href="javascript:void(0)" onclick="javascript:deleteResource(<s:property value="#channle.id"/>,'knowledgeChannle','<%=path%>')">删除</a>
		</td>
		
	</tr>
	</s:iterator>
</table>

</p>
<!-- <input type="submit" value="删除" class="inp_btn"> -->

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
<script src="<%=path%>/js/admin.js" charset="UTF-8"></script>
<%@ include file="../inc/foot.jsp"%>