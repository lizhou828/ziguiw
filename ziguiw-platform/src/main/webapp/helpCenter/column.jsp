<%@ page import="java.util.List" %>
<%@ page import="com.zigui.domain.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.zigui.dao.NewsDao" %>
<%@ page import="com.zigui.utils.BeanFactoryUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<News> newses = new ArrayList<News>();
    String columnId = request.getParameter("columnId");
    String columnName = request.getParameter("columnName");
    if (columnId != null) {
        NewsDao newsDao = (NewsDao) BeanFactoryUtils.getBean("newsDao");
        newses = newsDao.find(String.format("select n from News n where n.newsChannle.id = %s", columnId));
    }
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="p_help_middle">
    <p><strong><%= columnName %></strong></p>
    <hr style="margin:8px;"/>
    <div class="p_help_c">
        <ul>
            <%
                for (News news : newses) {
                    %>
                        <li>
                            <a href="http://www.ziguiw.com/news/detail/<%= news.getId()%>"><%= news.getTitle()%></a>
                        </li>
                    <%
                }
            %>
        </ul>
    </div>
</div>