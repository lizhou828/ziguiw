<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">全局：站点信息</p>
<form name="myform" id="myform" action="<%=path%>/admin/auth/deleteRole.action">
<table class="table">
	<thead>
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >
		</td>
		<td>
			权限名称
		</td>
		<td>
			权限URL
		</td>
		<td>
			权限code
		</td>
		<td>
			修改
		</td>
	</thead>
	<s:action var="getAllAuthority" name="authority_getAll" namespace="/admin/auth" executeResult="false" ignoreContextParams="false">
	</s:action>
	
	<s:iterator value="#getAllAuthority.authoritys" var="authority">
	<tr>
		<td>
			<input type="checkbox" name="opIds" value="${authority.id}" onclick='unselectall()'>
		</td>
		<td>
			<s:property value="#authority.name"/>
		</td>
		<td>
			<s:property value="#authority.url"/>
		</td>
		<td>
			<s:property value="#authority.code"/>
		</td>
		<td>
			<a href="<%=path%>/admin/auth/update_role.jsp?roleId=<s:property value="#role.ID"/>">修改</a>
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