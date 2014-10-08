<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.DateFormat,java.util.Date,java.text.SimpleDateFormat,com.opensymphony.xwork2.util.ValueStack" %>
<%
DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%@ include file="/admin/inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

<p class="map">知识：查询知识</p>


<s:if test='#parameters.opType[0] == null || #parameters.opType[0] < 1'>
<form action="<%=path%>/admin/knowledge/knowledge_listKnowledgesByCondition.action">
<ul class="col-ul ul_li_sp m-t10">

<s:action var="queryAllChannle" name="knowledgeChannle_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>频道</span><select name="query.channleName" id="channleId" class="inp_select">
	<option value="0">所有频道</option>
	<s:iterator value="#queryAllChannle.knowledgeChannles" var="channle">
		<option value="<s:property value="#channle.id"/>"><s:property value="#channle.name"/></option>
	</s:iterator>
</select>
</li>		
<li><span>知识状态</span>
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
<li><span>知识标题</span><input type="text" class="inp_txt" name="query.title" value="${query.title}"></li>

<input type="hidden" name="opType" value="<s:property value="#parameters.opType" />">

<input type="submit" value="查询"  class="inp_btn"/>
</ul>
</form>
<input type="hidden" id="oldChannleId" value="${query.channleName}">
<input type="hidden" id="oldProvince" value="${query.province}">
<input type="hidden" id="oldState" value="${query.state}">
</s:if>

<!--推荐位查询start -->
<s:if test='#parameters.opType[0] == 3'>
<form action="<%=path%>/admin/knowledge/knowledge_listKnowledgesByCondition.action">
<ul class="col-ul ul_li_sp m-t10">

<s:action var="queryAllRecommendRegion" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>推荐位</span><select name="query.regionId" id="regionId" class="inp_select">
	<option value="0">所有推荐位</option>
	<s:iterator value="#queryAllRecommendRegion.knowledgeRecommendRegions" var="recommendRegion">
		<s:if test='#recommendRegion.type==1'><option <s:if test='#recommendRegion.id==query.regionId'> selected="selected"</s:if> value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>：<s:property value="#recommendRegion.name"/></option></s:if>
	</s:iterator>
</select>
</li>

<s:action var="queryAllChannle" name="knowledgeChannle_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>频道</span><select name="query.channleName" id="channleId" class="inp_select">
	<option value="0">所有频道</option>
	<s:iterator value="#queryAllChannle.knowledgeChannles" var="channle">
		<option <s:if test='#channle.id==query.channleName'> selected="selected"</s:if> value="<s:property value="#channle.id"/>"><s:property value="#channle.name"/></option>
	</s:iterator>
</select>
</li>

<li><span>知识标题</span><input type="text" class="inp_txt" name="query.title" value="${query.title}"></li>

<input type="hidden" name="opType" value="<s:property value="#parameters.opType" />">
<input type="hidden" name="query.state" value="2">
<input type="submit" value="查询"  class="inp_btn"/>
</ul>
</form>

</s:if>
<!-- 推荐位查询end -->

<s:if test="pagedKnowledges != null && pagedKnowledges.list != null">

<s:fielderror/>
<s:if test='#parameters.opType[0] == 0'>
<form name="myform" id="myform" action="<%=path%>/admin/knowledge/knowledge_fakeDelete.action">
</s:if><s:elseif test='#parameters.opType[0] == 1'>
<form name="myform" id="myform" action="<%=path%>/admin/knowledge/knowledge_approve.action">
</s:elseif><s:elseif test='#parameters.opType[0] == 2'>
<form name="myform" id="myform" action="<%=path%>/admin/knowledge/knowledge_restore.action">
</s:elseif><s:elseif test='#parameters.opType[0] == 3'>
<form name="myform" id="myform" action="<%=path%>/admin/knowledge/knowledge_recommend.action">
</s:elseif>

<table class="table">
	<colgroup>
	</colgroup>
	<thead>
		<td>
			<input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)' >全部
		</td>
		<td>
			知识标题 
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
			首图
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
   <s:action var="action1" name="knowledgeChannle_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
				<s:param name="countPerPage" value="20"></s:param>
				<s:param name="needPaging" value="1"></s:param>
			</s:action>
				
	<s:action var="action2" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
				<s:param name="countPerPage" value="20"></s:param>
				<s:param name="needPaging" value="1"></s:param>
			</s:action>
	<s:iterator value="pagedKnowledges.list" var="knowledge">
	<tr>
		<td>
			<input type="checkbox" name="opIds" value=<s:property value="#knowledge.id"/> onclick='unselectall()'>
		</td>
		<td>
			<s:property value="#knowledge.title"/>
		</td>
		<td>
			<s:property value="#knowledge.editors"/>
		</td>
		<td>
			
			<s:iterator value="#action1.knowledgeChannles" var="channle">
			 	<s:if test="#channle.id == #knowledge.knowledgeChannle.id">
			 		<s:property value="#channle.name"/>
			 	</s:if>
			</s:iterator>
		</td>
		<td>
			<s:property value="#knowledge.province"/>
		</td>
		<td>
			<s:property value="#knowledge.clickCount"/>
		</td>
		<td>
			<s:property value="#knowledge.firstImage"/>
		</td>
		<td>
			<s:property value="#knowledge.formatedStartTime"/>
		</td>
		<td>
			
			<s:if test="#knowledge.state == 1">
				待审批
			</s:if><s:elseif test="#knowledge.state == 2">
				已审批
			</s:elseif><s:elseif test="#knowledge.state == -1">
				已删除
			</s:elseif><s:elseif test="#knowledge.state == -2">
				审批未通过
			</s:elseif>
		</td>
		<td>
			<s:iterator value="#action2.knowledgeRecommendRegions" var="recommend">
			 	<s:if test="#recommend.id == #knowledge.knowledgeRecommendRegion.id">
			 		<s:property value="#recommend.name"/>
			 	</s:if>
			</s:iterator>
		</td>
		<td>
			<a href="<%=path%>/admin/knowledge/knowledge_getById.action?knowledgeId=<s:property value="#knowledge.id"/>">修改</a>
			|
			<s:if test='#parameters.opType[0] != 2'><a href="<%=path%>/admin/knowledge/knowledge_fakeDelete.action?opIds=<s:property value="#knowledge.id"/>">删除</a></s:if>
			<s:else><a href="<%=path%>/admin/knowledge/knowledge_delete.action?opIds=<s:property value="#knowledge.id"/>">彻底删除</a></s:else>			
		</td>
		
	</tr>
	</s:iterator>
</table>
<s:if test='#parameters.opType[0] == 0'>
<!-- <input type="submit" value="删除" class="inp_btn" onclick="javascript:test()"> -->
<input type="button" id="button" value="删除" class="inp_btn" onclick="javascript:validation(this.form)">
</s:if><s:elseif test='#parameters.opType[0] == 1'>
<input type="hidden" name="approveFlag" id="approveFlag" value=1>
<!-- <input type="submit" value="通过" class="inp_btn" onclick="approve(2)"/>&nbsp;&nbsp;&nbsp;<input type="submit" value="不通过" class="inp_btn" onclick="approve(-2)"/> -->
<input  type="button" id="button" value="通过" class="inp_btn" onclick="approve(2)"/>&nbsp;&nbsp;&nbsp;<input  type="button" id="button" value="不通过" class="inp_btn" onclick="approve(-2)"/>
</s:elseif><s:elseif test='#parameters.opType[0] == 2'>
<!-- <input type="submit" value="恢复" class="inp_btn"> -->
<input type="button" id="button" value="恢复" class="inp_btn" onclick="javascript:validation(this.form)">
</s:elseif>
<s:elseif test='#parameters.opType[0] == 3'>
<s:action var="queryAllRecommendRegion" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
</s:action>
推荐至<select name="recommendRegionId" class="inp_select">
	<s:iterator value="#queryAllRecommendRegion.knowledgeRecommendRegions" var="recommendRegion">
	<s:if test='#recommendRegion.type==1'><option value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
	</s:iterator>
</select>
<input type="button" id="button" value="推荐" class="inp_btn" onclick="javascript:validation(this.form)">
</s:elseif>
</form>
<s:if test="pagedKnowledges.pageString != null && pagedKnowledges.pageString != ''">
<div class="fenye a-r">
${pagedKnowledges.pageString}
</div>
</s:if>
</s:if>
<s:elseif test="#parameters.opType[0] == 1">
暂时没有知识可以审批
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
alert('请选择要操作的知识！');
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
validation(document.getElementById('myform'));
}

 function    getArgs()     
    {     
            var    args=new    Object();     
            var    query=location.search.substring(1);//获取查询串     
            var    pairs=query.split(",");//在逗号处断开
            alert(query);     
            for(var    i=0;i<pairs.length;i++)     
            {     
                    var    pos=pairs[i].indexOf('=');//查找name=value     
                    if(pos==-1)    continue;//如果没有找到就跳过     
                    var    argname=pairs[i].substring(0,pos);//提取name     
                    var    value=pairs[i].substring(pos+1);//提取value     
                    args[argname]=unescape(value);//存为属性     
            } 
            //alert(args);    
            return    args;//返回对象     
    } 

</script>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>


<script type="text/javascript" src="http://a.tbcdn.cn/s/kissy/1.2.0/kissy.js"></script>
<%@ include file="/admin/inc/foot.jsp"%>


