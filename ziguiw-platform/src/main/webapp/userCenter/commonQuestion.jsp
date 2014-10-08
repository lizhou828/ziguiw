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
        newses = newsDao.find(String.format("select n from News n where n.newsChannle.id = %s and rownum<=5 order by n.createTime desc", columnId));
    }
%>


<div class="uc_que">
    <div class="lit_bg"><%=columnName%></div>
    <ul>

        <%
            for (News news : newses) {
        %>

        <li>
            <a href="/news/detail/<%= news.getId()%>"><%= news.getTitle()%></a>
        </li>
        <%
            }
        %>

    </ul>
</div>