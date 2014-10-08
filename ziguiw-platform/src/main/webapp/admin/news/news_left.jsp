<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String subUrl = request.getRequestURL().toString();
    String opType = request.getParameter("opType");
%>
<div class="left">
    <a href="" target="_self" class="logo"><img src="<%=path%>/images/logo.gif" alt="ZIGUICMD管理后台" width="155"/></a>

    <div class="sidebar">
        <ul>
            <li><a href="<%=path%>/admin/news/news_welcome.jsp" target="_self"
                   <%if(subUrl.indexOf("news_welcome") > 0) {%>class="on" <%} %>>欢迎页</a></li>
            <li><a href="<%=path%>/admin/news/newsChannle_getAll.action" target="_self"
                   <%if(subUrl.indexOf("newsChannle_getAll") > 0) {%>class="on" <%} %>>查询频道</a></li>
            <li><a href="<%=path%>/admin/news/newsChannle_saveOrUpdate.jsp" target="_self"
                   <%if(subUrl.indexOf("newsChannle_saveOrUpdate") > 0) {%>class="on" <%} %>>新增频道</a></li>
            <li><a href="<%=path%>/admin/news/newsChannleManager_move.jsp" target="_self"
                   <%if(subUrl.indexOf("newsChannleManager_move.jsp") > 0) {%>class="on" <%} %>>频道迁移</a></li>
            <li><a href="<%=path%>/admin/news/newsRecommendRegion_getAll.action" target="_self"
                   <%if(subUrl.indexOf("newsRecommendRegion_getAll") > 0) {%>class="on" <%} %>>查询推荐位</a></li>
            <li><a href="<%=path%>/admin/news/newsRecommendRegion_saveOrUpdate.jsp" target="_self"
                   <%if(subUrl.indexOf("newsRecommendRegion_saveOrUpdate") > 0) {%>class="on" <%} %>>新增推荐位</a></li>
            <li><a href="<%=path%>/admin/news/news_listNewsByCondition.jsp?opType=0" target="_self"
                   <%if(subUrl.indexOf("news_listNewsByCondition") > 0 && (opType == null || "".equals(opType) || "0".equals(opType))) {%>class="on" <%} %>>查询新闻</a>
            </li>
            <li><a href="<%=path%>/admin/news/news_listNewsByCondition.action?opType=1&query.state=1" target="_self"
                   <%if(subUrl.indexOf("news_listNewsByCondition") > 0 && opType != null && "1".equals(opType)) {%>class="on" <%} %>>审批新闻</a>
            </li>
            <li><a href="<%=path%>/admin/news/news_listNewsByCondition.action?opType=2&query.state=-1" target="_self"
                   <%if(subUrl.indexOf("news_listNewsByCondition") > 0 && opType != null && "2".equals(opType)) {%>class="on" <%} %>>恢复新闻</a>
            </li>
            <li><a href="<%=path%>/admin/news/news_saveOrUpdate.jsp" target="_self"
                   <%if(subUrl.indexOf("news_saveOrUpdate") > 0) {%>class="on" <%} %>>新增新闻</a></li>
            <li><a href="<%=path%>/admin/news/classNews_listByCondition.action" target="_self">查询班级新闻</a>
            </li>
            <li><a href="<%=path%>/admin/news/classNews_view.action" target="_self"
                   <%if(subUrl.indexOf("news_saveOrUpdate") > 0) {%>class="on" <%} %>>新增班级新闻</a></li>
            <li><a href="<%=path%>/admin/news/schoolNews_listByCondition.action" target="_self">查询校园新闻</a>
            </li>
            <li><a href="<%=path%>/admin/news/schoolNews_view.action" target="_self">新增校园新闻</a></li>
            <li><a href="<%=path%>/admin/news/news_listNewsByCondition.action?opType=3&query.state=2" target="_self"
                   <%if(subUrl.indexOf("news_listNewsByCondition") > 0 && opType != null && "3".equals(opType)) {%>class="on" <%} %>>推荐新闻</a>
            </li>
        </ul>
    </div>
</div>