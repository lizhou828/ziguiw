<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">全局：站点信息</p>
<form name="myform" id="myform" action="<%=path%>/newsRecommendRegion_delete.action">
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
			管理
		</td>
	</thead>
	<s:iterator value="newsRecommendRegions" var="region">
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
			<a href="<%=path%>/admin/news/newsRecommendRegion_getById.action?regionId=<s:property value="#region.id"/>">修改</a>
			|
			<a href="<%=path%>/admin/news/newsRecommendRegion_delete.action?opIds=<s:property value="#region.id"/>">删除</a>
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
<%@ include file="../inc/foot.jsp"%>