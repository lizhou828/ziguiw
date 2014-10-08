<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.text.DateFormat,java.util.Date,java.text.SimpleDateFormat,com.opensymphony.xwork2.util.ValueStack"%>
<%
DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%@ include file="/admin/inc/head.jsp"%>
<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

	<p class="map">
		我的小家：查询照片
	</p>

	<div id="newTopics">
	</div>

	<s:if test='#parameters.opType[0] == null || #parameters.opType[0]<= 1'>
		<form
			action="<%=path%>/admin/user/userPhoto_listByCondition.action">
			<ul class="col-ul ul_li_sp m-t10">
				<li>
					<span>照片标题</span>
					<input type="text" class="inp_txt" name="query.title"
						value="${query.title}" />
				</li>
				
				<li>
					<span>创建人昵称</span>
					<input type="text" class="inp_txt" name="query.userNick"
						value="${query.userNick}" />
				</li>

				<li>
					<span>创建时间</span>
					<input type="text" class="inp_txt" name="query.startTime"
						value="${query.startTime}" onclick="SelectDate(this)"
						readonly="readonly" />
					-
					<input type="text" class="inp_txt" name="query.endTime"
						value="${query.endTime}" onclick="SelectDate(this)"
						readonly="readonly" />
				</li>
<s:action var="queryAllRecommendRegion" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>推荐位</span><select name="query.regionId" id="regionId" class="inp_select">
	<option value="0">所有推荐位</option>
	<s:iterator value="#queryAllRecommendRegion.knowledgeRecommendRegions" var="recommendRegion">
		<s:if test='#recommendRegion.type==7'><option <s:if test='#recommendRegion.id==query.regionId'> selected="selected"</s:if> value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
	    <s:if test='#recommendRegion.id==99'><option <s:if test='#recommendRegion.id==query.regionId'> selected="selected"</s:if> value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
	</s:iterator>
</select>
</li>
				<input type="hidden" name="opType"
					value="<s:property value="#parameters.opType" />">

				<input type="submit" value="提交" class="inp_btn" />
			</ul>
		</form>
	</s:if>

	<s:if test="userPhotos != null && userPhotos.list != null">

		<s:fielderror />
		<s:if test='#parameters.opType[0] == 0'>
		<form name="myform" id="myform"
				action="<%=path%>/admin/user/userPhoto_delete.action">
		</s:if>
		 
		<s:elseif test='#parameters.opType[0] == 1'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/user/userPhoto_recommend.action">
		</s:elseif>

		<!--  
		<s:elseif test='#parameters.opType[0] == 1'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/knowledge/debate_approve.action">
		</s:elseif>
		<s:elseif test='#parameters.opType[0] == 2'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/knowledge/debate_restoreDebate.action">
		</s:elseif>
        -->
		<table class="table">
			<colgroup>
			</colgroup>
			<thead> 
				<td>
					<input type="checkbox" name="chkAll" id="chkAll"
						onclick='CheckAll(this.form)'>
					全部
				</td>
			    <td>
					照片ID
				</td>
				<td>
					照片标题
				</td>
				<td>
					存放url
				</td>
				<td>
					照片说明
				</td>
				<td>
					所属相册ID
				</td>
			
				<td>
					推荐位
				</td>
				<td>
					管理
				</td>
			</thead>
			<s:iterator value="userPhotos.list" var="userPhoto">
				<tr>
					<td>
						<input type="checkbox" name="opIds"
							value=<s:property value="#userPhoto.id"/> onclick='unselectall()'>
					</td>
				    <td>
						<s:property value="#userPhoto.id"/>
					</td>
					<td>
						<s:property value="#userPhoto.title"/>
					</td>
					<td>
						<img src="<s:property value="#userPhoto.url"/>" width="50" height="50" onmouseover="this.width=100;this.height=100"
                             onmouseout="this.width=50;this.height=50"/>
					</td>
					<td>
						<s:property value="#userPhoto.text"/>
					</td>
					<td>
						<s:property value="#userPhoto.albumId"/>
					</td>
				
					<td>
						<s:property value="#userPhoto.knowledgeRecommendRegion.name" />
					</td>
					<td>
					<a href="<%=path%>/admin/user/userPhoto_delete.action?opIds=<s:property value="#userPhoto.id"/>">删除</a>
			
					
					</td>
				</tr>
			</s:iterator>
		</table>


		
		
		 <s:if test='#parameters.opType[0] == 0'>
          <input type="button" id="button" value="删除" class="inp_btn" onclick="javascript:validation(this.form)">
        </s:if>		
		<br>
		<br>
		
	   <s:if test='#parameters.opType[0] == 1'>
			<s:action var="queryAllRecommendRegion" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
			</s:action>
			推荐至<select name="recommendRegionId" class="inp_select">
				<s:iterator value="#queryAllRecommendRegion.knowledgeRecommendRegions" var="recommendRegion">
				<s:if test='#recommendRegion.type==7'><option value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
				</s:iterator>
			</select>
			<input type="button" id="button" value="推荐" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:if>
		<!--
		<s:elseif test='#parameters.opType[0] == 1'>
		<input type="hidden" name="approveFlag" id="approveFlag" value=1>
	        <input  type="button" id="button" value="通过" class="inp_btn" onclick="javascript:approve(2)"/>&nbsp;&nbsp;&nbsp;<input  type="button" id="button" value="不通过" class="inp_btn" onclick="javascript:approve(-2)"/>
		</s:elseif>
		<s:elseif test='#parameters.opType[0] == 2'>
			<input type="button" id="button" value="恢复" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:elseif>
		  -->
		</form>
		<s:if test="userPhotos.pageString != null && userPhotos.pageString != ''">
			<div class="fenye a-r">
				${userPhotos.pageString}
			</div>
		</s:if>
	</s:if>
	<s:elseif test="#parameters.opType[0] == 1">
暂时没有照片可以审批
</s:elseif>
	<s:elseif test="#parameters.opType[0] == 2">
暂时没有照片可以恢复
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
alert('请选择要操作的照片！');
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

function approve(approve){
document.getElementById("approveFlag").value= approve;
validation(document.getElementById('myform'));
}


</script>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>
<script src="<%=path%>/js/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script src="<%=path%>/js/admin.js" charset="UTF-8"></script>
<%@ include file="/admin/inc/foot.jsp"%>