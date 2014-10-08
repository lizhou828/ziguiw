<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/comm/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>班级通知</title>
    <link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/userCenterWap/js/pageQuery.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("input[name='studentId']").change(function(){
                window.location="/userCenterWap/classNoticeList.action?studentId="+$(this).val();
            });
        });
    </script>
</head>

<body>
    <div class="header">
        <p class="nav_title">数字化校园平台查询</p>
    </div>
    <div class="location">
        <jsp:include page="../location.jsp"></jsp:include>
    </div>
    <div class="c_name pl10">
        <jsp:include page="../userInfo.jsp"></jsp:include>
    </div>
    
    
    <div class="bobti">
        班级通知<span style="color:#2d2d2d;font-family:Arial, Helvetica, sans-serif;font-weight:normal;font-size:12px;"></span>
    </div>
    <div class="xunc_1-1">
		<font color="red"> <p>&nbsp;近一个月内的信息</p></font>
    </div>
    
    <div class="title_5">
    	<p>( 共
            <c:choose>
                <c:when test="${rectotal==null || rectotal==''}">
                    0
                </c:when>
                <c:otherwise>
                    ${rectotal}
                </c:otherwise>
            </c:choose>
            封 )</p>
    </div>



    <div class="c_notice_con">
         <form action="/userCenterWap/classNoticeList.action" id="classNoticeListForm" method="post">
             <jsp:include page="../chooseChild.jsp"/>
              </br>
              </br>
              <c:choose>
                  <c:when test="${commonMessages.list == null|| fn:length(commonMessages.list) == 0}">
                          <table width="100%" cellpadding="0" cellspacing="0" border="0">
                              <tr>
                                  <th width="50%">主题</th>
                                  <td width="50%"></td>
                              </tr>
                              <tr>
                                  <th>类型</th>
                                  <td></td>
                              </tr>
                              <tr>
                                  <th>发送人</th>
                                  <td></td>
                              </tr>
                              <tr>
                                  <th>时间</th>
                                  <td></td>
                              </tr>
                          </table>

                  </c:when>
                  <c:otherwise>
                      <c:forEach items="${commonMessages.list}" var="notice">
                          <table width="100%" cellpadding="0" cellspacing="0" border="0">
                              <tr>
                                  <th width="50%">主题</th>
                                  <td width="50%">${notice.title} </td>
                              </tr>
                              <tr>
                                  <th>内容</th>
                                  <td>${notice.text}</td>
                              </tr>
                              <tr>
                                  <th>发送人</th>
                                  <td>${notice.fromUser.nickName}</td>
                              </tr>
                              <tr>
                                  <th>时间</th>
                                  <td><fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/></td>
                              </tr>
                          </table>
                          </br>
                      </c:forEach>
                  </c:otherwise>
              </c:choose>

             <input type="hidden" value="1" name="gotoPage" id="gotoPage">

             <div class="pageNav">
                <input id=maxPage type=hidden  value="${totalPages}">
                第${gotoPage}/${totalPages}页，跳转到<input id ="inputPage" type="text"  size="1"/> 页
                <input onclick="changePage();" type="button" id="page" value="Go"/>
             </div>
         </form>
    </div>



</body>
</html>
