<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.DateFormat,java.util.Date,java.text.SimpleDateFormat,com.opensymphony.xwork2.util.ValueStack" %>
<%
DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">全局：站点信息</p>


<form action="<%=path%>/admin/user/user_listByCondition.action">
<ul class="col-ul ul_li_sp m-t10">

<li><span>地域</span>
<select name="query.province" id="province" class="inp_select"/>
				<option value="">请选择所在省份</option>
			    <option value="北京" >北京</option>
			    <option value="天津" >天津</option>
			    <option value="上海" >上海</option>
			    <option value="重庆" >重庆</option>
			    <option value="香港" >香港</option>
			    <option value="澳门" >澳门</option>
			    <option value="河北" >河北</option>
			    <option value="山西" >山西</option>
			    <option value="内蒙古" >内蒙古</option>
			    <option value="辽宁" >辽宁</option>
			    <option value="吉林" >吉林</option>
			    <option value="黑龙江" >黑龙江</option>
			    <option value="江苏" >江苏</option>
			    <option value="浙江" >浙江</option>
			    <option value="安徽" >安徽 </option>
			    <option value="福建" >福建 </option>
			    <option value="江西" >江西</option>
			    <option value="山东" >山东</option>
			    <option value="河南" >河南</option>
			    <option value="湖北" >湖北</option>
			    <option value="湖南" >湖南</option>
			    <option value="广东" >广东</option>
			    <option value="广西" >广西</option>
			    <option value="海南" >海南</option>
			    <option value="四川" >四川</option>
			    <option value="贵州" >贵州</option>
			    <option value="云南" >云南</option>
			    <option value="西藏" >西藏</option>
			    <option value="陕西" >陕西 </option>
			    <option value="甘肃" >甘肃 </option>
			    <option value="青海" >青海</option>
			    <option value="宁夏" >宁夏</option>
			    <option value="新疆" >新疆</option>
			    <option value="台湾" >台湾</option>
			</select>
</li>
		
<li><span>新闻状态</span>
<select name="query.state" id="state" class="inp_select">
	<option value="0" >所有状态</option>
	<option value="1" >正常</option>
    <option value="-1" >冻结用户</option>
</select>
</li>

<li><span>注册时间</span>
<input type="text" class="inp_txt" name="query.startCreateTime" value="${query.startTime}" onclick="SelectDate(this)" readonly="readonly" /> - 
<input type="text" class="inp_txt" name="query.endCreateTime" value="${query.endTime}" onclick="SelectDate(this)" readonly="readonly" />
</li>
<!--  
<li><span>最后登录时间</span>
<input type="text" class="inp_txt" name="query.startLoginTime" value="${query.startLoginTime}" onclick="SelectDate(this)" readonly="readonly" /> - 
<input type="text" class="inp_txt" name="query.endLoginTime" value="${query.endLoginTime}" onclick="SelectDate(this)" readonly="readonly" />
</li>
-->
<li><span>用户昵称</span><input type="text" class="inp_txt" name="query.nickName" value="${query.nickName}"></li>

<s:action var="queryAllRecommendRegion" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>推荐位</span><select name="query.regionId" id="regionId" class="inp_select">
	<option value="0">所有推荐位</option>
	<s:iterator value="#queryAllRecommendRegion.knowledgeRecommendRegions" var="recommendRegion">
		<s:if test='#recommendRegion.type==5'><option <s:if test='#recommendRegion.id==query.regionId'> selected="selected"</s:if> value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
	    <s:if test='#recommendRegion.id==99'><option <s:if test='#recommendRegion.id==query.regionId'> selected="selected"</s:if> value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
	</s:iterator>
</select>
</li>

<input type="hidden" name="opType" value="<s:property value="#parameters.opType" />">
<input type="hidden" name="orderBy" value="createTime">
<input type="submit" value="查询"  class="inp_btn"/>
</ul>
</form>
<input type="hidden" id="oldChannleId" value="${query.channleName}">
<input type="hidden" id="oldProvince" value="${query.province}">
<input type="hidden" id="oldState" value="${query.state}">


<s:if test="users != null && users.list != null">

<s:fielderror/>
<s:if test='#parameters.opType[0] == 0'>
<form name="myform" id="myform"
				action="<%=path%>/admin/user/user_delete.action">
</s:if><s:elseif test='#parameters.opType[0] == 1'>
<form name="myform" id="myform"
				action="<%=path%>/admin/user/user_recommend.action">
</s:elseif><s:elseif test='#parameters.opType[0] == 2'>
<form name="myform" id="myform" action="<%=path%>/news_restore.action">
</s:elseif><s:elseif test='#parameters.opType[0] == 3'>
<form name="myform" id="myform" action="<%=path%>/news_recommend.action"">
</s:elseif>

<table class="table">
	<colgroup>
	</colgroup>
	<thead>
	<s:if test='#parameters.opType[0] == 1'>
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >全部
		</td>
	</s:if>
		<td>
			用户昵称
		</td>
		<td>
			注册邮箱
		</td>
		<td>
			省份 
		</td>
		<td>
			创建时间  
		</td>
		<!--  
		<td>
			最后登录时间
		</td>
		-->
		<td>
			状态
		</td>
		<td>
			角色
		</td>
		<td>
			推荐位
		</td>
		<td>
			管理
		</td>
	</thead>

	<s:iterator value="users.list" var="user">
	<tr>
	    <s:if test='#parameters.opType[0] == 1'>
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#user.id"/> onclick='unselectall()'>
		</td>
		</s:if>
		<td>
			<s:property value="#user.nickName"/>
		</td>
		<td>
			<s:property value="#user.email"/>
		</td>
		<td>
			<s:property value="#user.province"/>
		</td>
		<td>
			<s:property value="#user.formatedStartTime"/>
		</td>
		<!--  
		<td>
			<s:property value="#user.lastLoginTime"/>
		</td>
		-->
		<td>
			
			<s:if test="#user.state == 1">
				正常
			</s:if><s:elseif test="#user.state == -1">
				冻结中
			</s:elseif>
		</td>
		<td>
			<s:property value="#user.role.name" />
		</td>
		<td>
			<s:property value="#user.knowledgeRecommendRegion.name" />
		</td>
		<td>
		<s:if test="#user.state == 1">
			<a href="<%=path%>/admin/user/user_fakeDelete.action?opIds=<s:property value="#user.id"/>">冻结</a>
		</s:if><s:elseif test="#user.state == -1">
		     <a href="<%=path%>/admin/user/user_restore.action?opIds=<s:property value="#user.id"/>">解冻</a>
			</s:elseif>
		
			<!--  
			|
			<s:if test="#user.knowledgeRecommendRegion != null && #user.knowledgeRecommendRegion.id > 0">
				<a href="<%=path%>/admin/user/user_cancleRecommend.action?opIds=<s:property value="#user.id"/>">取消推荐</a>
			</s:if>
			<s:else>
			<a href="<%=path%>/admin/user/user_recommend.action?opIds=<s:property value="#user.id"/>">推荐</a>
			</s:else>
			-->
		</td>
		
	</tr>
	</s:iterator>
</table>


<!--  
      <s:if test='#parameters.opType[0] == 0'>
          <input type="submit" value="冻结" class="inp_btn">&nbsp;&nbsp;&nbsp;<input type="submit" value="解冻" class="inp_btn">
        </s:if>	-->	
		<br>
		<br>
		
	   <s:if test='#parameters.opType[0] == 1'>
			<s:action var="queryAllRecommendRegion" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
			</s:action>
			推荐至<select name="recommendRegionId" class="inp_select">
				<s:iterator value="#queryAllRecommendRegion.knowledgeRecommendRegions" var="recommendRegion">
				<s:if test='#recommendRegion.type==5'><option value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
				</s:iterator>
			</select>
			<input type="button" id="button" value="推荐" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:if>


</form>
<s:if test="users.pageString != null && users.pageString != ''">
<div class="fenye a-r">
${users.pageString}
</div>
</s:if>
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