<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">控制台：修改密码</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/myConsole/administrator_modifyPasswd.action">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>旧密码</span>
			<input name="administrator.password" type="password" class="inp_txt" maxlength="30"/>
		</td>
		<td class="v-b" ><span class="red_star">*</span>原有密码</td>
	</tr>
	<tr>
		<td><span>新密码</span>
			<input name="newPasswd" type="password" class="inp_txt"  maxlength="30"/>
		</td>
		<td class="v-b" ><span class="red_star">*</span>原有密码</td>
	</tr>
	<tr>
		<td><span>重复新密码</span>
			<input name="secondPasswd" type="password" class="inp_txt"  maxlength="30"/>
		</td>
		<td class="v-b" ><span class="red_star">*</span>原有密码</td>
	</tr>
	

</table>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>

<%@ include file="../inc/foot.jsp"%>