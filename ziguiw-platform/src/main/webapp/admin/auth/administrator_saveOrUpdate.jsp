<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">新闻：新增新闻</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/auth/administrator_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>用户名</span>
			<input name="administrator.nickName" type="text" class="inp_txt" value="" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>权限的名字</td>
	</tr>
	<tr>
		
		<td><span>用户密码</span>
			<input name="administrator.password" type="text" class="inp_txt" value="" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>权限对应的url</td>
	</tr>
	<tr>
		
		<td><span>真实姓名</span>
			<input name="administrator.realName" type="text" class="inp_txt" value="" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>权限对应的url</td>
	</tr>
	<tr>
		<td><span>角色选择</span>
			<s:action var="role_getAll" name="role_getAll" namespace="/admin/auth" executeResult="false" ignoreContextParams="false">
			</s:action>
			<select name='administrator.role.id' id="roleId" class="inp_select">
			    <option selected="selected" value="0">请选择角色</option>
			    <s:iterator value="#role_getAll.roles" var="role">
			    <s:if test='#role.id == administrator.role.id'>
			       <option value="<s:property value="#role.id"/>" selected="selected"><s:property value="#role.name"/></option>
			    </s:if>
			    <s:else>
			       <option value="<s:property value="#role.id"/>"><s:property value="#role.name"/></option>
			    </s:else>
					
				</s:iterator>
			</select>
		</td>
		<td class="v-b" ><span class="red_star">*</span>用户的角色</td>
	</tr>
	

</table>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>

<%@ include file="../inc/foot.jsp"%>