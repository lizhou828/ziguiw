<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String subUrl = request.getRequestURL().toString();
String opType = request.getParameter("opType");
%>
<div class="left">
<a href="" target="_self" class="logo"><img src="<%=path%>/images/logo.gif" alt="ZIGUICMD管理后台" width="155" /></a>
<div class="sidebar">
	<ul>
	<li><a href="<%=path%>/admin/knowledge/knowledge_welcome.jsp" target="_self" <%if(subUrl.indexOf("knowledge_welcome") > 0) {%>class="on" <%} %>>欢迎页</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledgeChannle_getAll.action" target="_self" <%if(subUrl.indexOf("knowledgeChannle_getAll") > 0) {%>class="on" <%} %>>查询频道</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledgeChannle_saveOrUpdate.jsp" target="_self"  <%if(subUrl.indexOf("knowledgeChannle_saveOrUpdate") > 0) {%>class="on" <%} %>>新增频道</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledgeChannleManager_move.jsp" target="_self"  <%if(subUrl.indexOf("knowledgeChannleManager_move") > 0) {%>class="on" <%} %>>频道迁移</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledgeRecommendRegion_getAll.action" target="_self" <%if(subUrl.indexOf("knowledgeRecommendRegion_getAll") > 0) {%>class="on" <%} %>>查询推荐位</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledgeRecommendRegion_saveOrUpdate.jsp" target="_self"  <%if(subUrl.indexOf("knowledgeRecommendRegion_saveOrUpdate") > 0) {%>class="on" <%} %>>新增推荐位</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledge_listKnowledgesByCondition.jsp?opType=0" target="_self" <%if(subUrl.indexOf("knowledge_listKnowledgesByCondition") > 0 && (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>查询知识</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledge_listKnowledgesByCondition.action?opType=1&query.state=1" target="_self" <%if(subUrl.indexOf("knowledge_listKnowledgesByCondition") > 0 && opType != null && "1".equals(opType)) {%>class="on" <%} %>>审批知识</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledge_listKnowledgesByCondition.action?opType=2&query.state=-1" target="_self" <%if(subUrl.indexOf("knowledge_listKnowledgesByCondition") > 0 && opType != null && "2".equals(opType)) {%>class="on" <%} %>>恢复知识</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledge_saveOrUpdate.jsp" target="_self" <%if(subUrl.indexOf("knowledge_saveOrUpdate") > 0) {%>class="on" <%} %>>新增知识</a></li>
	<li><a href="<%=path%>/admin/knowledge/knowledge_listKnowledgesByCondition.action?opType=3&query.state=2" target="_self" <%if(subUrl.indexOf("knowledge_listKnowledgesByCondition") > 0 && opType != null && "3".equals(opType)) {%>class="on" <%} %>>推荐知识</a></li>
	<li><a href="<%=path%>/admin/knowledge/question_listByCondition.jsp?opType=0" target="_self" <%if(subUrl.indexOf("question_listByCondition") > 0 && (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>查询提问</a></li>
	<li><a href="<%=path%>/admin/knowledge/question_listByCondition.action?opType=1&query.state=1" target="_self" <%if(subUrl.indexOf("question_listByCondition") > 0 && opType != null && "1".equals(opType)) {%>class="on" <%} %>>审批提问</a></li>
	<li><a href="<%=path%>/admin/knowledge/question_listByCondition.action?opType=2&query.state=-1" target="_self" <%if(subUrl.indexOf("question_listByCondition") > 0 && opType != null && "2".equals(opType)) {%>class="on" <%} %>>恢复提问</a></li>
	<li><a href="<%=path%>/admin/knowledge/debate_saveOrUpdate.jsp" target="_self" <%if(subUrl.indexOf("debate_saveOrUpdate") > 0) {%>class="on" <%} %>>新增辩论</a></li>
	<li><a href="<%=path%>/admin/knowledge/debate_listByCondition.jsp?opType=0" target="_self" <%if(subUrl.indexOf("debate_listByCondition") > 0 && (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>查询辩论</a></li>
	<li><a href="<%=path%>/admin/knowledge/debate_listByCondition.action?opType=1&query.state=1" target="_self" <%if(subUrl.indexOf("debate_listByCondition") > 0 && opType != null && "1".equals(opType)) {%>class="on" <%} %>>审批辩论</a></li>
	<li><a href="<%=path%>/admin/knowledge/debate_listByCondition.action?opType=2&query.state=-1" target="_self" <%if(subUrl.indexOf("debate_listByCondition") > 0 && opType != null && "2".equals(opType)) {%>class="on" <%} %>>恢复辩论</a></li>
	</ul>
</div>
</div>