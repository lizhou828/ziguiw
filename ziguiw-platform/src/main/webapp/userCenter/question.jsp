<%@ page import="com.zigui.domain.UserBase" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/comm/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的问答-子贵网用户中心</title>
<link href="../css/uccss.css" type="text/css" rel="stylesheet" />

<script src="../js/jquery-1.7.2.min.js"></script>


</head>

<body>

<jsp:include page="ucPublic/head.jsp"></jsp:include>

    <%
        UserBase user=(UserBase)session.getAttribute("VALID_USER");

    %>

    <div class="uc_midd">
    	<div class="que_con">
        	<div class="que_t">
            	<span class="my_title">我的问答</span><span><img src="ucimages/que_ent.gif" /></span>
            </div>
            
            <div class="que_qu">
            	<span class="que_my_title">我的提问</span> <span class="que_my_ab">| 类似提问</span>
            </div>
            <div class="que_all">
                <ul>

                    <s:iterator value="questions.list" var="question" >
                        <li>
                            <div class="que_left">
                                <p class="que_special">
                                    <a href="/study/yuer/detail/${id}"> ${title}</a>
                                </p>
                                <p><span class="que_time">
                                    ${answerCount}个回答&nbsp;|&nbsp;创建时间：${createTime}
                                    </span>
                                </p>
                            </div>
                            <div class="que_right">

                                <c:choose>
                                    <c:when test="${status == 1}">
                                        <a href="/study/yuer/detail/${id}">
                                            <p><img src="ucimages/que_yes.gif" /></p>
                                            <p class="que_deal">(已处理)</p>
                                        </a>
                                    </c:when>
                                    <c:when test="${status == 0}">
                                    <a href="/study/yuer/detail/${id}"><p><img src="ucimages/que_d_btn.gif" /></p></a>

                                    </c:when>
                                    <c:when test="${status == -1} ">
                                        <a href="/study/yuer/detail/${id}">
                                            <p><img src="ucimages/que_yes.gif" /></p>
                                            <p class="que_deal">(过期)</p>
                                        </a>
                                    </c:when>

                                </c:choose>

                            </div>
                        </li>
                    </s:iterator>
                </ul>
            </div>
            <div class="clear"></div>
            <div class="que_p">
            	<p>
                	<a href="#">1</a><a href="#">2</a><a href="#">3</a><a href="#">…</a> <a href="#">末页</a> 转到 <input type="text" size="2"/> 页<a href="#">GO</a>
                </p>
            </div>
            
            
            
            <div class="que_qu">
            	<span class="que_my_title">我的回答</span>
            </div>
            <div class="que_all">
                <ul>

                    <s:iterator value="obj.list" var="question" >
                        <li>
                        <div class="que_left">
                        <p class="que_special">
                            <a href="/study/yuer/detail/${id}">${title}</a>
                        </p>
                        <p><span class="que_time">
                        ${answerCount}个回答&nbsp;|&nbsp;最后回答时间：${lastModifyTime}
                        </span>
                        </p>
                        </div>
                        <div class="que_right">

                            <c:choose>
                                <c:when test="${status == 1}">
                                    <a href="/study/yuer/detail/${id}">
                                        <p><img src="ucimages/que_yes.gif" /></p>
                                        <p class="que_deal">(已处理)</p>
                                    </a>
                                </c:when>
                                <c:when test="${status == 0}">
                                    <a href="/study/yuer/detail/${id}"><p><img src="ucimages/que_d_btn.gif" /></p></a>

                                </c:when>
                                <c:when test="${status == -1} ">
                                    <a href="/study/yuer/detail/${id}">
                                        <p><img src="ucimages/que_yes.gif" /></p>
                                        <p class="que_deal">(过期)</p>
                                    </a>
                                </c:when>

                            </c:choose>

                        </div>
                        </li>
                    </s:iterator>
                </ul>
            </div>


            <div class="que_p">
            	<p>
                	<a href="#">1</a><a href="#">2</a><a href="#">3</a><a href="#">…</a> <a href="#">末页</a> 转到 <input type="text" size="2"/> 页<a href="#">GO</a>
                </p>
            </div>
        </div>
               

     <div class="clear"></div>
 
 
    <div class="uc_noti">
    	<p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>
           
 </div>


<jsp:include page="/inc/templateFoot.jsp"/>