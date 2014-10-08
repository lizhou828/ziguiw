<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">全局：站点信息</p>

<form method="post" action="<%=path%>/admin/config/configCenter_saveOrUpdate.action">
<textarea cols="150" rows="80" name="content"><s:property value="noescapeContent" escape="false"></s:property></textarea>
<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
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