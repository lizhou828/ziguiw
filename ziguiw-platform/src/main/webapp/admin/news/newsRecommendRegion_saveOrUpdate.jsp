<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">新闻：新增/修改推荐区</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/news/newsRecommendRegion_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>推荐位名称</span>
			<input name="newsRecommendRegion.name" type="text" class="inp_txt" value="${newsRecommendRegion.name}" maxlength="30"/>
		</td>
		<td class="v-b" ><span class="red_star">*</span>推荐位名称</td>
		
		<input type="hidden" name="newsRecommendRegion.id" value="${newsRecommendRegion.id}"/>
	</tr>
	

</table>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>

<%@ include file="../inc/foot.jsp"%>