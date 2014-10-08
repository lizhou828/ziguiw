<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">广告：广告计划列表</p>
<form name="myform" id="myform" action="<%=path%>/admin/news/newsChannle_delete.action">
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
			广告计划ID
		</td>
		<td>
			广告未名称
		</td>
		<td>
			广告连接
		</td>
		<td>
			广告图片
		</td>
		<td>
			广告开始时间
		</td>
		<td>
			广告结束时间
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:iterator value="adPlans.list" var="adPlan">
	<tr>
	<!--  
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#channle.id"/> onclick='unselectall()'>
		</td>
	-->
		<td>
			<s:property value="#adPlan.id"/>
		</td>
		<td>
			<s:property value="#adPlan.ad.name"/>
		</td>
		<td>
			<s:property value="#adPlan.link"/>
		</td>
		<td>
			<s:property value="#adPlan.imageUrl"/>
		</td>
		<td>
			<s:property value="#adPlan.startTime"/>
		</td>
		<td>
			<s:property value="#adPlan.endTime"/>
		</td>
		<td>
			<a href="<%=path%>/admin/ad/adPlan_getById.action?adPlan.id=<s:property value="#adPlan.id"/>">修改</a>
			|<a href="<%=path%>/admin/ad/adPlan_delete.action?opIds=<s:property value="#adPlan.id"/>">删除</a>
		</td>
		
	</tr>
	</s:iterator>
</table>

</p>
<!-- <input type="submit" value="删除" class="inp_btn"> -->
<!--  input type="button" id="button" value="删除" class="inp_btn" onclick="javascript:validation(this.form)"-->
</form>
</div>
<script type="text/javascript">
//JavaScript Document
function validation(form){
if(form.chkAll.checked){
form.submit();
}
var isSelect = false;
for (var i=0;i<form.elements.length;i++){
var e = form.elements[i];
if (e.Name != 'chkAll'&&e.disabled==false&&e.checked==true)
  isSelect = true;
}
if(isSelect){
form.submit();
}else{
alert('请选择要操作的新闻频道！');
}
}

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
<script src="<%=path%>/js/admin.js" charset="UTF-8"></script>
<%@ include file="../inc/foot.jsp"%>