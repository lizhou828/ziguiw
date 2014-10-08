<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">新闻：新增新闻</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/auth/role_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>角色名称</span>
			<input name="role.name" type="text" class="inp_txt" value="${role.name}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>角色的名字</td>
	</tr>
	
	<input type="hidden" name="role.id" value="${role.id}">

</table>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>

<%@ include file="../inc/foot.jsp"%>