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
		知识：查询提问
	</p>

	<div id="newTopics">
	</div>

	<s:if test='#parameters.opType[0] == null || #parameters.opType[0] < 1'>
		<form
			action="<%=path%>/admin/knowledge/question_listByCondition.action">
			<ul class="col-ul ul_li_sp m-t10">
				<li>
					<span>提问人昵称</span>
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
				<li>
					<span>问题标题</span>
					<input type="text" class="inp_txt" name="query.title"
						value="${query.title}">
				</li>

				<input type="hidden" name="opType"
					value="<s:property value="#parameters.opType" />">

				<input type="submit" value="提交" class="inp_btn" />
			</ul>
		</form>
		<input type="hidden" id="oldState" value="${query.state}">

	</s:if>

	<s:if test="questions != null && questions.list != null">

		<s:fielderror />
		<s:if test='#parameters.opType[0] == 0'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/knowledge/question_fakeDelete.action">
		</s:if>
		<s:elseif test='#parameters.opType[0] == 1'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/knowledge/question_approve.action">
		</s:elseif>
		<s:elseif test='#parameters.opType[0] == 2'>
			<form name="myform" id="myform"
				action="<%=path%>/admin/knowledge/question_restoreQuestion.action">
		</s:elseif>

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
					提问标题
				</td>
				<td>
					创建人昵称
				</td>
				<td>
					创建时间
				</td>
				<td>
					提问状态
				</td>
				<td>
					最佳回答者昵称
				</td>
				<td>
					管理
				</td>
			</thead>
			<s:iterator value="questions.list" var="question">
				<tr>
					<td>
						<input type="checkbox" name="opIds"
							value=<s:property value="#question.id"/> onclick='unselectall()'>
					</td>
					<td>
						<s:property value="#question.title" />
					</td>
					<td>
						<s:property value="#question.creatorNick" />
					</td>
					<td>
						<s:property value="#question.formatedStartTime" />
					</td>
					<td>
						<s:property value="#question.bestAnswerAuthor" />
					</td>
					<td>

						<s:if test="#question.status == 1">
				待审批
			</s:if>
						<s:elseif test="#question.status == 2">
				已审批
			</s:elseif>
						<s:elseif test="#question.status == -1">
				已删除
			</s:elseif>
						<s:elseif test="#question.status == -2">
				审批未通过
			</s:elseif>
					</td>
					<td>
						<a
							href="<%=path%>/admin/knowledge/question_fakeDelete.action?opIds=<s:property value="#question.id"/>">删除</a>|
						<a href="javascript:void(0)"
							onclick="javascript:getAnswers(<s:property value="#question.id"/>)">管理答案</a>
					</td>
				</tr>
			</s:iterator>
		</table>


		<s:if test='#parameters.opType[0] == 0'>
		<!-- <input type="submit" value="删除" class="inp_btn"> -->
		<input type="button" id="button" value="删除" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:if>
		<s:elseif test='#parameters.opType[0] == 1'>
		<!--  
			<input type="hidden" name="approveFlag" id="approveFlag" value=1>
			<input type="submit" value="通过" class="inp_btn" onclick="approve(2)" />&nbsp;&nbsp;&nbsp;<input
				type="submit" value="不通过" class="inp_btn" onclick="approve(-2)" />
		-->
		<input  type="button" id="button" value="通过" class="inp_btn" onclick="approve(2)"/>&nbsp;&nbsp;&nbsp;<input  type="button" id="button" value="不通过" class="inp_btn" onclick="approve(-2)"/>
		</s:elseif>
		<s:elseif test='#parameters.opType[0] == 2'>
		<!--  <input type="submit" value="恢复" class="inp_btn">-->
			<input type="button" id="button" value="恢复" class="inp_btn" onclick="javascript:validation(this.form)">
		</s:elseif>
		</form>
		<s:if test="pageString != null && pageString != ''">
			<div class="fenye a-r">
				${pageString}
			</div>
		</s:if>
	</s:if>
	<s:elseif test="#parameters.opType[0] == 1">
暂时没有知识可以审批
</s:elseif>
	<s:elseif test="#parameters.opType[0] == 2">
暂时没有知识可以恢复
</s:elseif>
	</p>
</div>

<script type="text/javascript">

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
alert('请选择要操作的提问！');
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
validation(document.getElementsByName('myform'));
}


</script>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>
<script src="<%=path%>/js/jquery-1.7.2.min.js" charset="UTF-8"></script>
<script src="<%=path%>/js/admin.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="http://a.tbcdn.cn/s/kissy/1.2.0/kissy.js"></script>
<%@ include file="/admin/inc/foot.jsp"%>


