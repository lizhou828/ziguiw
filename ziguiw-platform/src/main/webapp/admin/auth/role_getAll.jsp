<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">全局：站点信息</p>
<form name="myform" id="myform" action="<%=path%>/admin/auth/role_deleteRole.action">
<table class="table">
	<thead>
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >
		</td>
		<td>
			角色ID
		</td>
		<td>
			角色名称
		</td>
		<td>
			权限
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:action var="getAllValidRole" name="role_getAll" namespace="/admin/auth" executeResult="false" ignoreContextParams="false">
	</s:action>
	
	<s:iterator value="#getAllValidRole.roles" var="role">
	<tr>
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#role.id"/> onclick='unselectall()'>
		</td>
		<td>
			<s:property value="#role.id"/>
		</td>
		<td>
			<s:property value="#role.name"/>
		</td>
		<td>
			<s:iterator value="#role.authoritys" var="authority">
			<s:if test="#authority.name != null">
				<s:property value="#authority.name"/>&nbsp;&nbsp;
			 </s:if>
			</s:iterator>
		</td>
		<td>
			<a href="<%=path%>/admin/auth/role_getById.action?role.id=<s:property value="#role.id"/>">修改</a>
			|
			<a href="javascript:void(0);" onclick="javascript:showAuth(<s:property value="#role.id"/>);">修改权限</a>
		</td>
	</tr>
</s:iterator>
</table>

</p>
<input type="submit" value="删除" class="inp_btn">
</form>
</div>


<div style="display:block;" id="shortcut" class="tc_bg">
	<div class="tc">
		<form name="shortcutform" method="post" action="role_grantAuth">
		<h2><a class="close" onclick="$('#shortcut').hide();" href="javascript:void(0);"></a>设置权限</h2>
		<ul class="col-ul ul_li_sp">
			<li><span>清空功能</span><a onclick="unSelectAll();" href="javascript:void(0);">清空选项</a></li>
			<li><span>全选功能</span><a onclick="selectAll();" href="javascript:void(0);">全选选项</a></li>
			
			<li id="menubox">
				<div class="l">	
					<s:action var="getNewsAuthority" name="authority_getAll" namespace="/admin/auth" executeResult="false" ignoreContextParams="false">
					</s:action>
					<dl>
						<dt>新闻管理</dt>
						<s:iterator value="#getNewsAuthority.authoritys" var="authority">
						<dd><input type="checkbox" title="<s:property value="#authority.name"/>" value='<s:property value="#authority.id"/>' name="auths"><s:property value="#authority.name"/></dd>
						</s:iterator>
					</dl>
					
				</div>
			</li>
			<li><input type="button" class="inp_btn m-r10" value="设 置" name="menusubmit" id="auth_grant"><a class="m-lr10" onclick="$('#shortcut').hide();" href="javascript:void(0);">取 消</a></li>
		</ul>
		<input type="hidden" name="role.id" value="<s:property value="#role.id"/>" id="roleId">
		</form>
	</div>
	<span class="biaoshi"></span>
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

function showAuth(roleId){
	$("#roleId").val(roleId);
	$("#shortcut").show();
	
	$.post("<%=path%>/admin/ajax/role_getById.action?role.id=" + roleId, function(data){
		$('input[name="auths"]').attr("checked", false);
		$.each(data.role.authoritys,function(idx,item){
			$('input[name="auths"][value="' + item.id + '"]').attr("checked", true);
		});
	});
}

function selectAll(){
	$('input[name="auths"]').attr("checked", true);
}

function unSelectAll(){
	$('input[name="auths"]').attr("checked", false);
}

</script>
<%@ include file="../inc/foot.jsp"%>