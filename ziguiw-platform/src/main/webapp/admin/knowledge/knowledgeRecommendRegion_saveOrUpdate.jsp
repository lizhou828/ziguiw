<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/admin/inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

<p class="map">知识：新增/修改知识推荐区</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/knowledge/knowledgeRecommendRegion_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>推荐位名称</span>
			<input name="knowledgeRecommendRegion.name" type="text" class="inp_txt" value="${knowledgeRecommendRegion.name}" maxlength="30"/>
		</td>
		<td class="v-b" ><span class="red_star">*</span>推荐位名称</td>						
		<input type="hidden" name="knowledgeRecommendRegion.id" value="${knowledgeRecommendRegion.id}" />
	</tr>
	<tr>
	    <td><span>推荐位类型</span>
		<select name="knowledgeRecommendRegion.type" id="knowledgeRecommendRegion.type" class="inp_select">
		   <option  value="1" <s:if test="knowledgeRecommendRegion.type == 1">selected="selected"</s:if>>知识</option>
		   <option  value="2" <s:if test="knowledgeRecommendRegion.type == 2">selected="selected"</s:if>>论坛</option>
		   <option  value="3" <s:if test="knowledgeRecommendRegion.type == 3">selected="selected"</s:if>>问答</option>
		   <option  value="4" <s:if test="knowledgeRecommendRegion.type == 4">selected="selected"</s:if>>辩论</option>
		   <option  value="5" <s:if test="knowledgeRecommendRegion.type == 5">selected="selected"</s:if>>用户</option>
		   <option  value="6" <s:if test="knowledgeRecommendRegion.type == 6">selected="selected"</s:if>>日志</option>
		   <option  value="7" <s:if test="knowledgeRecommendRegion.type == 7">selected="selected"</s:if>>照片</option>
		   <option  value="8" <s:if test="knowledgeRecommendRegion.type == 8">selected="selected"</s:if>>心情</option>
		</select>
		</td>
		<td class="v-b" ><span class="red_star">*</span>推荐位类型</td>
	</tr>

</table>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>

<%@ include file="/admin/inc/foot.jsp"%>