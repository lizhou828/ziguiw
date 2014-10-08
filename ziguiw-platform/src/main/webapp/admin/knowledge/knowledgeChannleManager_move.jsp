<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/admin/inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

<p class="map">知识：频道迁移</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/knowledge/knowledgeChannleManager_move.action">
		
		<s:action var="knowledgeChannle_getAll" name="knowledgeChannle_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
		</s:action>
		<ul class="col-ul ul_li_sp m-t10">
		<li><span>旧频道名称</span>
			<select name="fromChannle" class="inp_select">
				<s:iterator value="#knowledgeChannle_getAll.knowledgeChannles" var="channle">
					<option value="<s:property value="#channle.id"/>"><s:property value="#channle.name"/></option>
				</s:iterator>
			</select>
		</li>
		<li><span>起始时间</span>
			<input type="text" name="startCreateTime" value="${query.startTime}" onclick="SelectDate(this)" readonly="readonly" class="inp_txt"/>
		</li>
		<li><span>结束时间</span>
			<input type="text" name="endCreateTime" value="${query.endTime}" onclick="SelectDate(this)" readonly="readonly" class="inp_txt"/>
		</li>
		
		<br/>
		<br/>
		迁移至：
		<li><span>新频道</span>
			<select name="toChannle" class="inp_select">
				<s:iterator value="#knowledgeChannle_getAll.knowledgeChannles" var="channle">
					<option value="<s:property value="#channle.id"/>"><s:property value="#channle.name"/></option>
				</s:iterator>
			</select>
		</li>
		</ul>
	

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>
</p>
</form>
</div>
<script src="<%=path%>/js/date.js" charset="UTF-8"></script>
<%@ include file="/admin/inc/foot.jsp"%>