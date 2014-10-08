<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">新闻：新增新闻</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/auth/authority_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>权限名称</span>
			<input name="authority.name" type="text" class="inp_txt" value="" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>权限的名字</td>
	</tr>
	<tr>
		
		<td><span>url</span>
			<input name="authority.url" type="text" class="inp_txt" value="" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>权限对应的url,如/admin/news/newsChannle_getAll</td>
	</tr>

</table>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>

<%@ include file="../inc/foot.jsp"%>