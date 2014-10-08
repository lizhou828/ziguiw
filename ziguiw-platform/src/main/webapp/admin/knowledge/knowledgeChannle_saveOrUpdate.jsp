<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/admin/inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

<p class="map">知识：新增知识频道</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/knowledge/knowledgeChannle_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>频道名称</span>
			<input name="knowledgeChannle.name" type="text" class="inp_txt" value="${knowledgeChannle.name}" maxlength="30"/>
		</td>
		<input type="hidden" name="knowledgeChannle.id" value=${knowledgeChannle.id}>
		<td class="v-b" ><span class="red_star">*</span>频道名称</td>
	</tr>
	<tr>
	   <td>
	   <p class="submit">
        <input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
       </p>
	   </td>
	</tr>
</table>

</form>
</div>

<%@ include file="/admin/inc/foot.jsp"%>