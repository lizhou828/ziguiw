<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/admin/inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

<p class="map">知识：查询知识推荐位</p>
<form name="myform" id="myform" action="<%=path%>/admin/knowledge/knowledgeRecommendRegion_batchDelete.action">
<table class="table">
	<colgroup>
		
	</colgroup>
	<thead>
	    <!--  
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >全部
		</td>
		-->
		<td>
			推荐位ID
		</td>
		<td>
			推荐位名称
		</td>
		<td>
			推荐位新增时间
		</td>
		<td>
			推荐位类型
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:iterator value="knowledgeRecommendRegions" var="region">
	<tr>
	<!--  
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#region.id"/> onclick='unselectall()'>
		</td>
	-->
		<td>
			<s:property value="#region.id"/>
		</td>
		<td>
			<s:property value="#region.name"/>
		</td>
		<td>
			<s:property value="#region.formatedStartTime"/>
		</td>
		<td>
		  <s:if test="#region.type == 1">
				知识
			</s:if><s:elseif test="#region.type == 2">
				论坛
			</s:elseif><s:elseif test="#region.type == 3">
				问答
			</s:elseif><s:elseif test="#region.type == 4">
				辩论
			</s:elseif><s:elseif test="#region.type == 5">
				用户
			</s:elseif><s:elseif test="#region.type == 6">
				日记
			</s:elseif><s:elseif test="#region.type == 7">
				照片
			</s:elseif><s:elseif test="#region.type == 8">
				说说
			</s:elseif>
		</td>		
		<td>
			<a href="<%=path%>/admin/knowledge/knowledgeRecommendRegion_getById.action?regionId=<s:property value="#region.id"/>">修改</a>
			|
			<a href="<%=path%>/admin/knowledge/knowledgeRecommendRegion_delete.action?regionId=<s:property value="#region.id"/>">删除</a>
		</td>
	</tr>
</s:iterator>
</table>

</p>
<!--  
<input type="submit" value="删除" class="inp_btn">
-->
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
<%@ include file="/admin/inc/foot.jsp"%>