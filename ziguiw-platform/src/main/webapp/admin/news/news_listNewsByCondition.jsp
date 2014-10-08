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


<s:if test='#parameters.opType[0] == null || #parameters.opType[0] < 1'>
<form action="<%=path%>/admin/news/news_listNewsByCondition.action">
<ul class="col-ul ul_li_sp m-t10">

<s:action var="queryAllChannle" name="newsChannle_getAll" namespace="/admin/news" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>频道</span><select name="query.channleName" id="channleId" class="inp_select">
	<option value="0">所有频道</option>
	<s:iterator value="#queryAllChannle.newsChannles" var="channle">
		<option value="<s:property value="#channle.id"/>"><s:property value="#channle.name"/></option>
	</s:iterator>
</select>
</li>

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
	<option value="1" >未审核</option>
    <option value="2" >审核通过</option>
    <option value="-1" >已经删除</option>
    <option value="-2" >未通过审批</option>
</select>
</li>

<li><span>编辑时间</span>
<input type="text" class="inp_txt" name="query.startTime" value="${query.startTime}" onclick="SelectDate(this)" readonly="readonly" /> - 
<input type="text" class="inp_txt" name="query.endTime" value="${query.endTime}" onclick="SelectDate(this)" readonly="readonly" />
</li>
<li><span>新闻标题</span><input type="text" class="inp_txt" name="query.title" value="${query.title}"></li>


<input type="hidden" name="opType" value="<s:property value="#parameters.opType" />">

<input type="submit" value="查询"  class="inp_btn"/>
</ul>
</form>
<input type="hidden" id="oldChannleId" value="${query.channleName}">
<input type="hidden" id="oldprovince" value="${query.province}">
<input type="hidden" id="oldState" value="${query.state}">

</s:if>

<!-- -tuijianstart -->
<s:if test='#parameters.opType[0] == 3'>
<form action="<%=path%>/admin/news/news_listNewsByCondition.action">
<ul class="col-ul ul_li_sp m-t10">

<s:action var="queryAllRecommendRegion" name="newsRecommendRegion_getAll" namespace="/admin/news" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>推荐位</span><select name="query.regionId" id="query.regionId" class="inp_select">
	<option value="0">所有推荐位</option>
	<s:iterator value="#queryAllRecommendRegion.newsRecommendRegions" var="recommendRegions">
		<option  <s:if test='#recommendRegion.id==query.regionId'> selected="selected"</s:if> value="<s:property value="#recommendRegions.id"/>"><s:property value="#recommendRegions.id"/>:<s:property value="#recommendRegions.name"/></option>
	</s:iterator>
</select>
</li>

<s:action var="queryAllChannle" name="newsChannle_getAll" namespace="/admin/news" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>频道</span><select name="query.channleName" id="channleId" class="inp_select">
	<option value="0">所有频道</option>
	<s:iterator value="#queryAllChannle.newsChannles" var="channle">
		<option <s:if test='#channle.id==query.channleName'> selected="selected"</s:if> value="<s:property value="#channle.id"/>"><s:property value="#channle.name"/></option>
	</s:iterator>
</select>
</li>

<li><span>新闻标题</span><input type="text" class="inp_txt" name="query.title" value="${query.title}"></li>


<input type="hidden" name="opType" value="<s:property value="#parameters.opType" />">
<input type="hidden" name="query.state" value="2">
<input type="submit" value="查询"  class="inp_btn"/>
</ul>
</form>
</s:if>
<!-- tuijianend-->

<s:if test="pagedNews != null && pagedNews.list != null">

<s:fielderror/>
<s:if test='#parameters.opType[0] == 0'>
<form name="myform" id="myform" action="<%=path%>/admin/news/news_fakeDelete.action">
</s:if><s:elseif test='#parameters.opType[0] == 1'>
<form name="myform" id="myform" action="<%=path%>/admin/news/news_approve.action">
</s:elseif><s:elseif test='#parameters.opType[0] == 2'>
<form name="myform" id="myform" action="<%=path%>/admin/news/news_restore.action">
</s:elseif><s:elseif test='#parameters.opType[0] == 3'>
<form name="myform" id="myform" action="<%=path%>/admin/news/news_recommend.action"">
</s:elseif>

<table class="table">
	<colgroup>
	</colgroup>
	<thead>
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >全部
		</td>
		<td>
			新闻标题 
		</td>
		<td>
			编辑  
		</td>
		<td>
			所属频道 
		</td>
		<td>
			所属地域  
		</td>
		<td>
			PV
		</td>
		<td>
			创建时间 
		</td>
		<td>
			状态
		</td>
		<td>
			推荐位置
		</td>
		<td>
			管理
		</td>
	</thead>
	<s:action var="action1" name="newsChannle_getAll" namespace="/admin/news" executeResult="false" ignoreContextParams="false">
				<s:param name="countPerPage" value="20"></s:param>
				<s:param name="needPaging" value="1"></s:param>
			</s:action>
				
	<s:action var="action2" name="newsRecommendRegion_getAll" namespace="/admin/news" executeResult="false" ignoreContextParams="false">
				<s:param name="countPerPage" value="20"></s:param>
				<s:param name="needPaging" value="1"></s:param>
			</s:action>
	<s:iterator value="pagedNews.list" var="news">
	<tr>
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#news.id"/> onclick='unselectall()'>
		</td>
		<td>
			<a href="<%=path%>/news/detail/<s:property value="#news.id"/>" target="blank"><s:property value="#news.title"/></a>
		</td>
		<td>
			<s:property value="#news.editors"/>
		</td>
		<td>
			
			 <s:iterator value="#action1.newsChannles" var="channle">
			 	<s:if test="#channle.id == #news.newsChannle.id">
			 		<s:property value="#channle.name"/>
			 	</s:if>
			</s:iterator>
		</td>
		<td>
			<s:property value="#news.province"/>
		</td>
		<td>
			<s:property value="#news.clickCount"/>
		</td>
		<td>
			<s:property value="#news.formatedStartTime"/>
		</td>
		<td>
			
			<s:if test="#news.state == 1">
				待审批
			</s:if><s:elseif test="#news.state == 2">
				已审批
			</s:elseif><s:elseif test="#news.state == -1">
				已删除
			</s:elseif><s:elseif test="#news.state == -2">
				审批未通过<a href="newsAudit_viewRejectReason.action?newsId=${news.id}">查看原因</a>
			</s:elseif>
		</td>
		<td>
			<s:iterator value="#action2.newsRecommendRegions" var="recommend">
			 	<s:if test="#recommend.id == #news.newsRecommendRegion.id">
			 		<s:property value="#recommend.name"/>
			 	</s:if>
			</s:iterator>
		</td>
		<td>
			<a href="<%=path%>/admin/news/news_getById.action?newsId=<s:property value="#news.id"/>">修改</a>
			|
			<s:if test='#parameters.opType[0] != 2'><a href="<%=path%>/admin/news/news_fakeDelete.action?opIds=<s:property value="#news.id"/>">删除</a></s:if>
			<s:else><a href="<%=path%>/admin/news/news_delete.action?opIds=<s:property value="#news.id"/>">彻底删除</a></s:else>	
		</td>
		
	</tr>
	</s:iterator>
</table>
<s:if test='#parameters.opType[0] == 0'>
<!-- <input type="submit" value="删除" class="inp_btn"> -->
<input type="button" id="button" value="删除" class="inp_btn" onclick="javascript:validation(this.form)">
</s:if><s:elseif test='#parameters.opType[0] == 1'>
<input type="hidden" name="approveFlag" id="approveFlag" value=1>
<!-- <input type="submit" value="通过" class="inp_btn" onclick="approve(2)"/>&nbsp;&nbsp;&nbsp;<input type="submit" value="不通过" class="inp_btn" onclick="approve(-2)"/> -->
<input  type="button" id="button" value="通过" class="inp_btn" onclick="approve(2)"/>
</s:elseif><s:elseif test='#parameters.opType[0] == 2'>
<!-- <input type="submit" value="恢复" class="inp_btn"> -->
<input type="button" id="button" value="恢复" class="inp_btn" onclick="javascript:validation(this.form)">
</s:elseif>
<s:elseif test='#parameters.opType[0] == 3'>
<s:action var="queryAllRecommendRegion" name="newsRecommendRegion_getAll" namespace="/admin/news" executeResult="false" ignoreContextParams="false">
</s:action>
推荐至<select name="recommendRegionId" class="inp_select">
	<s:iterator value="#queryAllRecommendRegion.newsRecommendRegions" var="recommendRegion">
	<option value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option>
	</s:iterator>
</select>
<input type="button" id="button" value="推荐" class="inp_btn" onclick="javascript:validation(this.form)">
</s:elseif>
</form>
<s:if test="pagedNews.pageString != null && pagedNews.pageString != ''">
<div class="fenye a-r">
${pagedNews.pageString}
</div>
</s:if>
</s:if>
<s:elseif test="#parameters.opType[0] == 1">
暂时没有新闻可以审批
</s:elseif>
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
alert('请选择要操作的新闻！');
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


<script type="text/javascript" src="http://a.tbcdn.cn/s/kissy/1.2.0/kissy.js"></script>
<%@ include file="../inc/foot.jsp"%>


