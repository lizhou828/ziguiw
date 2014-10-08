<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">新闻：频道查询</p>
<form name="myform" id="myform" action="<%=path%>/admin/ad/ad_delete.action">
<table class="table">
	<colgroup>
	</colgroup>
	<thead>
 
		<td>
			广告位ID
		</td>

		<td>
			广告位名称
		</td>
		<td>
			图片地址
		</td>
		<td>
			连接地址
		</td>
		<td>
			描述
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:iterator value="ads" var="ad">
	<tr>

		<td>
			<s:property value="#ad.id"/>
		</td>
	
		<td>
			<s:property value="#ad.name"/>
		</td>
		<td>
			<s:property value="#ad.imageUrl"/>
		</td>
		<td>
			<s:property value="#ad.link"/>
		</td>
		<td>
			<s:property value="#ad.description"/>
		</td>
		<td>
			<a href="<%=path%>/admin/ad/ad_getById.action?ad.id=<s:property value="#ad.id"/>">修改</a>
			|<a href="<%=path%>/admin/ad/ad_delete.action?opIds=<s:property value="#ad.id"/>">删除</a>
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