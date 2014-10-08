
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/comm/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="/userCenterWap/js/pageQuery.js"></script>

<title>学生考勤查询</title>
    <link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("input[name='studentId']").change(function(){
                window.location="/userCenterWap/showAttendance.action?studentId="+$(this).val();
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
        考勤查询<span style="color:#2d2d2d;font-family:Arial, Helvetica, sans-serif;font-weight:normal;font-size:12px;">（以下考勤信息仅供参考）</span>
    </div>
    <div class="xunc_1-1">
		<font color="red"> <p>&nbsp;近一个月内的信息</p></font>
    </div>
<div>
    <form action="/userCenterWap/showAttendance.action" method="post" id="attendanceForm">
        <div class="kaoqin_con">

            <jsp:include page="../chooseChild.jsp"/>
            </br></br>
         <table width="100%" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <th width="25%">日期</th>
                <th width="25%">读卡时间</th>
                <th width="25%">考勤结果</th>
                <th width="25%">状态</th>
            </tr>
            <c:choose>
                <c:when test="${map['data'] ==null || fn:length(map['data'])==0}">
                    <tr>
                        没有相关信息！
                    </tr>

                </c:when>
                <c:otherwise>
                    <c:forEach items="${map['data']}" var="data">
                        <tr>
                            <td width="25%" align="center" class="kaoq-td-1">
                                <jsp:useBean id="dataTime" class="java.util.Date" />
                                <jsp:setProperty name="dataTime" property="time" value="${data['READCARD_TIME']}" />
                                <fmt:formatDate value="${dataTime}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td width="25%">
                                <jsp:useBean id="readcardTime" class="java.util.Date" />
                                <jsp:setProperty name="readcardTime" property="time" value="${data['READCARD_TIME']}" />
                                <fmt:formatDate value="${readcardTime}" pattern="HH:mm:ss"/>
                            </td>
                            <td width="25%">${data['RESULT']}</td>

                            <td width="25%">
                                <c:if test="${data['IN_OUT_STATE'] eq '0'}">
                                    <font color="red">离校</font>
                                </c:if>
                                <c:if test="${data['IN_OUT_STATE'] eq '1'}">
                                    <font color="green">进校</font>
                                </c:if>
                            </td>

                        </tr>
                    </c:forEach>

                </c:otherwise>
             </c:choose>

          </table>
        </div>


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
