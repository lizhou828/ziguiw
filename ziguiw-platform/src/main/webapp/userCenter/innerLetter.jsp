<%@include file="/comm/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>站内信-子贵网用户中心</title>
<link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/userCenter/ucPublic/pageQuery.js"></script>

</head>
<body>
    <jsp:include page="ucPublic/head.jsp"></jsp:include>
    <div class="uc_midd">
    	<div class="uc_l">
            <jsp:include page="dsisQueryNavigator.jsp"/>
            <jsp:include page="commonQuestion.jsp" >
                <jsp:param name="columnId" value="5"></jsp:param>
                <jsp:param name="columnName" value="常见问题"></jsp:param>
            </jsp:include>
        </div>

        <div class="letter_right">
        	<!--我的站内信开始-->
            <div class="my_lett">
            	<div class="lett_title">
                	我的站内信 [<a href="/userCenter/innerLetterList.action">收件箱</a>][<a href="/userCenter/sendLetterList.action">已发送</a>]
                </div>
                <div >
                    <form action="/userCenter/innerLetterList.action" id="letterListForm" method="post">
                        <c:forEach items="${letters.list}" var="letter">
                            <div class="lett_bl">
                                <p>标题：<a href="../userCenter/innerLetterDetail.action?letterId=${letter.id}">${letter.title}</a></p>
                                <p>内容：<a href="../userCenter/innerLetterDetail.action?letterId=${letter.id}">${letter.text}</a></p>
                                <p class="lett_pa">
                                    <span class="lett_m">发信人：${letter.fromUser.nickName}</span>
                                    <span>发送时间：<fmt:formatDate value="${letter.createTime}" pattern="yyyy-MM-dd HH:mm"/></span>
                                </p>
                            </div>
                        </c:forEach>
                        <input type="hidden" id="gotoPage" name="gotoPage">
                        <div class="points_p">
                        <p>
                            <c:forEach items="${pageList}" var="page">
                                <a href="javascript:document.getElementById('gotoPage').setAttribute('value','${page}');letterListForm.submit();">${page}</a>
                            </c:forEach>
                            <input id=maxPage type=hidden  value="${totalPages}">
                            转到 <input id ="inputPage" type="text"  size="2"/> 页
                            <input onclick="changePage();" type="button" id="page" value="Go"/>
                        </p>
                        </div>
                    </form>
                </div>

            </div>
            <!--我的站内信结束-->
            <!--发起站内信开始-->
            <div class="sent_lett">
                <div class="sent_title">
                    发起站内信
                </div>
                <div class="sent_p">
                    <form action="/userCenter/sendInnerLetter.action" method="post">
                        <table>
                            <tr>
                                <td>收件人：</td>
                                <td><input type="text" name="toUserName" class="per_inp" /></td>
                            </tr>
                            <tr>
                                <td>主题：</td>
                                <td><input type="text" name="title" class="art_inp"/></td>
                            </tr>
                            <tr>
                                <td>内容：</td>
                                <td><textarea class="con_inp" name="text" rows="6"></textarea></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" value="发送" class="sent_inp"/></td>
                            </tr>
                        </table>
                    </form>


                </div>
            </div>
            <!--发起站内信结束-->

            

        </div>

     <div class="clear"></div>
 
 
    <div class="uc_noti">
    	<p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>
           
    </div>


    <jsp:include page="/inc/templateFoot.jsp"/>
