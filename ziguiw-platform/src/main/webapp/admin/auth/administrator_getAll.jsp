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
			管理员ID
		</td>
		<td>
			管理员昵称
		</td>
		<td>
			姓名
		</td>
		<td>
			角色
		</td>
		<td>
			修改
		</td>
	</thead>
	<s:action var="getAllAdministrators" name="administrator_getAll" namespace="/admin/auth" executeResult="false" ignoreContextParams="false">
	</s:action>
	
	<s:iterator value="#getAllAdministrators.administrators.list" var="administrator">
	<tr>
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#role.id"/> onclick='unselectall()'>
		</td>
		<td>
			<s:property value="#administrator.id"/>
		</td>
		<td>
			<s:property value="#administrator.nickName"/>
		</td>
		<td>
			<s:property value="#administrator.realName"/>
		</td>
		<td>
			<s:property value="#administrator.role.name"/>
		</td>
		<td>
			<a href="<%=path%>/admin/auth/update_role.jsp?roleId=<s:property value="#administrator.id"/>">修改</a>
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