<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="java.text.DateFormat,java.util.Date,java.text.SimpleDateFormat,com.opensymphony.xwork2.util.ValueStack" %>
<%
DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">广告：新增广告</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/ad/adPlan_saveOrUpdate.action" enctype="multipart/form-data">
<table class="table">
	<colgroup>
		<col  style="width:620px;"></col>
		<col></col>
	</colgroup>
	<s:action var="ad_getAll" name="ad_getAll" namespace="/admin/ad" executeResult="false" ignoreContextParams="false">
	</s:action>
	<tr>
		<td><span>广告位名字</span>
			<select name="adPlan.ad.id" type="text" class="inp_select">
				<s:iterator value="#ad_getAll.ads" var="ad">
					<option value="<s:property value="#ad.id"/>"><s:property value="#ad.name"/></option>
				</s:iterator>
			</select>
		</td>
		<td class="v-b" ><span class="red_star">*</span>广告位名字,主要是便于记忆</td>
	</tr>
	
	<tr>
		
		<td><span>广告位连接</span>
			http://<input name="adPlan.link" type="text" class="inp_txt" value="${ad.link}" />
		</td>
		<td class="v-b" >广告位连接</td>
	</tr>
	
	<tr>
		
		<td><span>广告位图片</span>
			<input name="uploadFile" type="file" />
		</td>
		<td class="v-b" >广告位图片</td>
	</tr>
	
	<tr>
		
		<td><span>生效时间</span>
		<input type="text" class="inp_txt" name="adPlan.startTime" value="${adPlan.startTime}" onclick="SelectDate(this)" readonly="readonly" /> - 
		<input type="text" class="inp_txt" name="adPlan.endTime" value="${adPlan.endTime}" onclick="SelectDate(this)" readonly="readonly" />
	</td>
		<td class="v-b" >广告位生效时间</td>
	</tr>
	

</table>

<input type="hidden" name="adPlan.id" value="${adPlan.id}">

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
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
alert('请选择要操作的广告！');
}
}

function initSelect(){
	var province = document.getElementById("province");
	var channleId = document.getElementById("channleId");
	var state = document.getElementById("state");

	var oldChannleId = document.getElementById("oldChannleId").value;
	var oldprovince = document.getElementById("oldprovince").value;
	var oldState = document.getElementById("oldState").value;
	
	
    for(var i=0;i<channleId.options.length;i++)
    {
        if(channleId.options[i].value == oldChannleId)
        {
        	channleId.options[i].selected = true;
            break;
        }
    }
    
    for(var i=0;i<province.options.length;i++)
    {
        if(province.options[i].value == oldprovince && oldprovince != "")
        {
        	province.options[i].selected = true;
        	break;
        }
    }
    
    for(var i=0;i<state.options.length;i++)
    {
        if(state.options[i].value == oldState)
        {
        	state.options[i].selected = true;
            break;
        }
    }
   
    
    
}

initSelect();


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

function approve(approve){
document.getElementById("approveFlag").value= approve;
validation(document.getElementById('myform'));
}



</script>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>
<%@ include file="../inc/foot.jsp"%>