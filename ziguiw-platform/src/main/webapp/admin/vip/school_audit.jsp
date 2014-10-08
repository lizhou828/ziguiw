<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.DateFormat,java.util.Date,java.text.SimpleDateFormat,com.opensymphony.xwork2.util.ValueStack" %>
<%
DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">认证：学生用户认证</p>


<table class="table">
	<colgroup>
	</colgroup>
	<thead>
		<td>
			用户ID
		</td>
		<td>
			学校ID
		</td>
		<td>
			学校用户ID
		</td>
		<td>
			学校名称
		</td>
		<td>
			联系电话
		</td>
		
		<td>
			联系人
		</td>
	</thead>

	<s:action var="getVipSchools" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
     	<s:param name="hql">select sc from UserBase as ub, School as sc  where ub.type = 1 and ub.foreignKey = sc.xx_ID and ub.state < 2</s:param>
     	<s:param name="queryString">1=1</s:param>
	</s:action>
	<s:iterator value="#getVipSchools.obj.list" var="school">
	<tr>
		<td>
			<s:property value="#school.user.nickName"/>
		</td>
		<td>
			<s:property value="#school.xx_ID"/>
		</td>
		<td>
			<s:property value="#school.XxID"/>
		</td>
		<td>
			<s:property value="#school.sch_name"/>
		</td>
		
		<td>
			<s:property value="#school.contactphone"/>
		</td>
		
		<td>
			<s:property value="#school.linkman"/>
		</td>
		
	</tr>
	</s:iterator>
</table>



</form>
<s:if test="#getVipSchools.obj.pageString != null && #getVipSchools.obj.pageString != ''">
<div class="fenye a-r">
<s:property value="#getVipSchools.obj.pageString" escape="false"/>
</div>
</s:if>
</p>
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
alert('请选择要操作的用户！');
}
}

function initSelect(){
	var province = document.getElementById("province");
	var channleId = document.getElementById("channleId");
	var state = document.getElementById("state");

	var oldChannleId = document.getElementById("oldChannleId").value;
	var oldProvince = document.getElementById("oldProvince").value;
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
        if(province.options[i].value == oldProvince && oldProvince != "")
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
}



</script>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>


<script type="text/javascript" src="http://a.tbcdn.cn/s/kissy/1.2.0/kissy.js"></script>
<%@ include file="../inc/foot.jsp"%>