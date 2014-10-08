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

<style type="text/css">
#newTopics {
	width: 902px;
	position: absolute;
	left: 50%;
	margin-left: -451px;
	background-color: #FFF;
	height: auto;
	overflow: hidden;
	z-index: 100;
	zoom: 1;
	display: none;
}

#newTopics table tr td {
	padding: 5px 0;
}

#newTopics table tr td input {
	_margin-top: -5px;
}

#newTopics table tr td .btn2 {
	width: 60px;
	cursor: pointer;
	height: 22px;
	margin-top: 0;
}
</style>
	<p class="map">
		社区：查询版区
	</p>

	<div id="newTopics">
	</div>

	<s:if test='#parameters.opType[0] == null || #parameters.opType[0] < 1'>
		<form
			action="<%=path%>/admin/community/forum_listByCondition.action">
			<ul class="col-ul ul_li_sp m-t10">
				<li>
					<span>帖子标题</span>
					<input type="text" class="inp_txt" name="query.title"
						value="${query.title}" />
				</li>
				
				<li>
					<span>辩论创建人昵称</span>
					<input type="text" class="inp_txt" name="query.creatorNick"
						value="${query.creatorNick}" />
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

				<input type="hidden" name="opType"
					value="<s:property value="#parameters.opType" />">
				
				<input type="hidden" name="query.isnew"
					value="1">

				<input type="submit" value="提交" class="inp_btn" />
			</ul>
		</form>
	</s:if>
	
	<!--推荐位查询start -->
<s:if test='#parameters.opType[0] == 1'>
<form action="<%=path%>/admin/community/forum_listByCondition.action">
<ul class="col-ul ul_li_sp m-t10">

<s:action var="queryAllRecommendRegion" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
</s:action>
<li><span>推荐位</span><select name="query.regionId" id="regionId" class="inp_select">
	<option value="0">所有推荐位</option>
	<s:iterator value="#queryAllRecommendRegion.knowledgeRecommendRegions" var="recommendRegion">
		<s:if test='#recommendRegion.type==2'><option <s:if test='#recommendRegion.id==query.regionId'> selected="selected"</s:if> value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
	    <s:if test='#recommendRegion.id==99'><option <s:if test='#recommendRegion.id==query.regionId'> selected="selected"</s:if> value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
	</s:iterator>
</select>
</li>

<s:action var="queryAllBoard" name="board_listByCondition" namespace="/admin/community" executeResult="false" ignoreContextParams="false">
<s:param name="countPerPage" value="200"></s:param>
<s:param name="currentPage" value="1"></s:param>
<s:param name="query.state" value="1"></s:param>
</s:action>
<li><span>版区</span><select name="query.boardid" id="boardid" class="inp_select">
	<option value="0">所有版区</option>
	<s:iterator value="#queryAllBoard.boards.list" var="board">
		<option <s:if test='#board.id==query.boardid'> selected="selected"</s:if> value="<s:property value="#board.id"/>" ><s:property value="#board.boardname"/></option>
	</s:iterator>
</select>
</li>

<li><span>知识标题</span><input type="text" class="inp_txt" name="query.title" value="${query.title}"></li>

<input type="hidden" name="opType" value="<s:property value="#parameters.opType" />">
<input type="hidden" name="query.state" value="1">
<input type="hidden" name="query.isnew" value="1">
<input type="submit" value="查询"  class="inp_btn"/>
</ul>
</form>

</s:if>
<!-- 推荐位查询end -->

	<!-- 查询跟帖头部查询框 -->
		<s:if test='#parameters.opType[0] == null || #parameters.opType[0] == 10'>
		<form
			action="<%=path%>/admin/community/forum_listByCondition.action">
			<ul class="col-ul ul_li_sp m-t10">
				<li>
					<span>主帖标题</span>
					<input type="text" class="inp_txt" name="query.title"
						value="${query.title}" />
				</li>
				
				<li>
					<span>辩论创建人昵称</span>
					<input type="text" class="inp_txt" name="query.creatorNick"
						value="${query.creatorNick}" />
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

				<input type="hidden" name="opType"
					value="<s:property value="#parameters.opType" />">
				
				<input type="hidden" name="query.isnew"
					value="0">

				<input type="submit" value="提交" class="inp_btn" />
			</ul>
		</form>
	</s:if>
    <!-- 查询跟帖头部查询框结束 -->
    
    <!-- 主贴start -->
    <s:if test='#parameters.opType[0] < 10'>
	<s:if test="forums != null && forums.list != null">

		
		<s:if test='#parameters.opType[0] == 0'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/community/forum_fakeDelete.action">
		</s:if>

		<s:elseif test='#parameters.opType[0] == 1'>
			<form name="myform" id="myform" action="<%=path%>/admin/community/forum_recommend.action">
		</s:elseif>
	
		<s:elseif test='#parameters.opType[0] == 2'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/community/forum_restore.action">
		</s:elseif>
        
		<table class="table">
			<colgroup>
			</colgroup>
			<thead>			 
				<td>
					<input type="checkbox" name="chkAll" id="chkAll"
						onclick='CheckAll(this.form)'>
					全部
				<br><br><br></td>
			
				<td>
					帖子标题
				<br><br><br></td>
				<td>
					所属版区
				<br><br><br></td>
				<!-- 
				<td>
					帖子内容
				<br><br><br></td> -->
				<td>
					创建者昵称
				<br><br><br></td>
				<td>
					创建时间
				<br><br><br></td>
				<td>
					最后回复人昵称
				<br><br><br></td>
				<td>
					最后回复时间
				<br><br><br></td>
				<td>
					状态
				<br><br><br></td>
				<td>
					回贴数
				<br><br><br></td>
				<td>
					推荐位
				<br><br><br></td>
				<td>
					管理
				<br><br><br></td>
			</thead>
			<s:iterator value="forums.list" var="forum">
				<tr>
					<td>
						<input type="checkbox" name="opIds"
							value=<s:property value="#forum.id"/> onclick='unselectall()'>
					<br><br><br></td>
					<td>
						<a href="<%=path%>/page.action?templateName=forum_detail.ftl&forumId=<s:property value="#forum.id"/>" target="_blank" ><s:property value="#forum.title"/></a>
					<br><br><br></td>
					<td>
						<s:property value="#forum.board.boardname"/>
					<br><br><br></td>
					<!--  
					<td>
						<s:property value="#forum.content"/>
					<br><br><br></td>-->
					<td>
						<s:property value="#forum.creatorNick"/>
					<br><br><br></td>
					<td>
						<s:property value="#forum.formatedStartTime"/>
					<br><br><br></td>
					<td>
						<s:property value="#forum.lastPostNick"/>
					<br><br><br></td>
					<td>
						<s:property value="#forum.formatedLastPostTime"/>
					<br><br><br></td>
					<td>
								<s:if test="#forum.state == 1">
									正常
								</s:if>									
								<s:elseif test="#forum.state == -1">
									已删除
								</s:elseif>
					<br><br><br></td>
				    <td>
						<s:property value="#forum.renum" />
					</td>
					<td>
						<s:property value="#forum.knowledgeRecommendRegion.name" />
					</td>						
					<td>
					  <s:if test='#parameters.opType[0] != 2'>
					  <a href="<%=path%>/admin/community/forum_fakeDelete.action?opIds=<s:property value="#forum.id"/>">删除</a>
					  <div id='top<s:property value="#forum.id"/>'>
					  <a href="javascript:void(0)"
							onclick="javascript:topOrElite('<s:property value="#forum.id"/>','top','<%=path%>')">
					  <s:if test='#forum.istop==0'>置顶</s:if><s:else>取消置顶</s:else>
					  </a>
					  </div>
					   <div id='elite<s:property value="#forum.id"/>'>
					  <a href="javascript:void(0)"
							onclick="javascript:topOrElite('<s:property value="#forum.id"/>','elite','<%=path%>')">
					  <s:if test='#forum.elite==0'>加精</s:if><s:else>取消加精</s:else>
					  </a>
					  </div>
					  
					  </s:if>
			<s:else><a href="<%=path%>/admin/community/forum_delete.action?opIds=<s:property value="#forum.id"/>">彻底删除</a></s:else>			
					</td>
				</tr>
			</s:iterator>
		</table>


		
		
		<s:if test='#parameters.opType[0] == 0'>
			<input type="button" id="button" value="删除" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:if>
		<!--
		<s:elseif test='#parameters.opType[0] == 1'>
		<input type="hidden" name="approveFlag" id="approveFlag" value=1>
	        <input  type="button" id="button" value="通过" class="inp_btn" onclick="javascript:approve(2)"/>&nbsp;&nbsp;&nbsp;<input  type="button" id="button" value="不通过" class="inp_btn" onclick="javascript:approve(-2)"/>
		</s:elseif>
		  -->
		  <s:if test='#parameters.opType[0] == 1'>
			<s:action var="queryAllRecommendRegion" name="knowledgeRecommendRegion_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
			</s:action>
			推荐至<select name="recommendRegionId" class="inp_select">
				<s:iterator value="#queryAllRecommendRegion.knowledgeRecommendRegions" var="recommendRegion">
				<s:if test='#recommendRegion.type==2'><option value="<s:property value="#recommendRegion.id"/>"><s:property value="#recommendRegion.id"/>:<s:property value="#recommendRegion.name"/></option></s:if>
				</s:iterator>
			</select>
			<input type="button" id="button" value="推荐" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:if>
		<s:if test='#parameters.opType[0] == 2'>
			<input type="button" id="button" value="恢复" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:if>
		
		</form>
		<s:if test="forums.pageString != null && forums.pageString != ''">
			<div class="fenye a-r">
				${forums.pageString}
			</div>
		</s:if>
	</s:if>
	<s:elseif test="#parameters.opType[0] == 1">
暂时没有主贴可以审批
</s:elseif>
	<s:elseif test="#parameters.opType[0] == 2">
暂时没有主贴可以恢复
</s:elseif>
</s:if>
<!-- 主贴end -->

<!-- gentie start -->

 <s:if test='#parameters.opType[0] >= 10'>
	<s:if test="forums != null && forums.list != null">

		
		<s:if test='#parameters.opType[0] == 10'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/community/forum_fakeDelete.action">
		</s:if>
		<!--  
		<s:elseif test='#parameters.opType[0] == 1'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/knowledge/debate_approve.action">
		</s:elseif>
		-->
		<s:elseif test='#parameters.opType[0] == 12'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/knowledge/forum_restore.action">
		</s:elseif>
        
		<table class="table">
			<colgroup>
			</colgroup>
			<thead>			 
				<td>
					<input type="checkbox" name="chkAll" id="chkAll"
						onclick='CheckAll(this.form)'>
					全部
				<br><br><br></td>
			
				<td>
					主贴标题
				<br><br><br></td>
				<td>
					所属版区
				<br><br><br></td>
				<!--  
				<td>
					帖子内容
				<br><br><br></td>-->
				<td>
					创建者昵称
				<br><br><br></td>
				<td>
					创建时间
				<br><br><br></td>
				<td>
					状态
				<br><br><br></td>
				<td>
					管理
				<br><br><br></td>
			</thead>
			<s:iterator value="forums.list" var="forum">
				<tr>
					<td>
						<input type="checkbox" name="opIds"
							value=<s:property value="#forum.id"/> onclick='unselectall()'>
					<br><br><br></td>
					<td>
						<s:property value="#forum.parentForum.title"/>
					<br><br><br></td>
					<td>
						<s:property value="#forum.board.boardname"/>
					<br><br><br></td>
					<!--  
					<td>
						<s:property value="#forum.content"/>
					<br><br><br></td>-->
					<td>
						<s:property value="#forum.creatorNick"/>
					<br><br><br></td>
					<td>
						<s:property value="#forum.formatedStartTime"/>
					<br><br><br></td>
					<td>
								<s:if test="#forum.state == 1">
									正常
								</s:if>									
								<s:elseif test="#forum.state == -1">
									已删除
								</s:elseif>
					<br><br><br></td>	
					<td>
					  <s:if test='#parameters.opType[0] != 12'><a href="<%=path%>/admin/community/forum_fakeDelete.action?opIds=<s:property value="#forum.id"/>">删除</a></s:if>
			<s:else><a href="<%=path%>/admin/community/forum_delete.action?opIds=<s:property value="#forum.id"/>">彻底删除</a></s:else>			
					</td>
				</tr>
			</s:iterator>
		</table>


		
		
		<s:if test='#parameters.opType[0] == 0'>
			<input type="button" id="button" value="删除" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:if>
		<!--
		<s:elseif test='#parameters.opType[0] == 1'>
		<input type="hidden" name="approveFlag" id="approveFlag" value=1>
	        <input  type="button" id="button" value="通过" class="inp_btn" onclick="javascript:approve(2)"/>&nbsp;&nbsp;&nbsp;<input  type="button" id="button" value="不通过" class="inp_btn" onclick="javascript:approve(-2)"/>
		</s:elseif>
		  -->
		<s:elseif test='#parameters.opType[0] == 2'>
			<input type="button" id="button" value="恢复" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:elseif>
		
		</form>
		<s:if test="forums.pageString != null && forums.pageString != ''">
			<div class="fenye a-r">
				${forums.pageString}
			</div>
		</s:if>
	</s:if>
	<s:elseif test="#parameters.opType[0] == 1">
暂时没有主贴可以审批
</s:elseif>
	<s:elseif test="#parameters.opType[0] == 2">
暂时没有主贴可以恢复
</s:elseif>
</s:if>

<!-- gentie end -->
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
alert('请选择要操作的辩论！');
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