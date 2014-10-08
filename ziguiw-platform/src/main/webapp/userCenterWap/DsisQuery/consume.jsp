
<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/comm/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>一卡通消费查询</title>
    <link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/userCenterWap/js/pageQuery.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("input[name='studentId']").change(function(){
                window.location="/userCenterWap/studentConsumption.action?studentId="+$(this).val();
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
        消费纪录查询<span style="color:#2d2d2d;font-family:Arial, Helvetica, sans-serif;font-weight:normal;font-size:12px;">（以下消费信息仅供参考）</span>
    </div>
    <div class="xunc_1-1">
		<font color="red"> <p>&nbsp;近一个月内的信息</p></font>
    </div>
    
    <div class="title_5">
    	<font color="red"><strong>您的消费纪录如下</strong></font>
    </div>
 <div>
      <form action="/userCenterWap/studentConsumption.action" id="studentConsumptionForm" method="post">
        <div class="score_con">
            <jsp:include page="../chooseChild.jsp"/>
             </br></br>
            <c:choose>
                <c:when test="${map['data']==null || fn:length(map['data']) == 0}">
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <th width="50%">流水号</th>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <th>卡号</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>类型</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>消费时间</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>消费金额(元)</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>余额(元)</th>
                            <td></td>
                        </tr>
                    </table>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${map['data']}" var="data">
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <th width="50%">流水号</th>
                            <td width="50%">${data['RECORD_ID']}</td>
                        </tr>
                        <tr>
                            <th>卡号</th>
                            <td>${data['CARD_NUMBER']}</td>
                        </tr>
                        <tr>
                            <th>类型</th>
                            <td>
                                <c:choose>
                                    <c:when test="${data['CONS_STATE']=='0'}">
                                        消费
                                    </c:when>
                                    <c:when test="${data['CONS_STATE']=='1'}">
                                        充值
                                    </c:when>
                                    <c:when test="${data['CONS_STATE']=='2'}">
                                        取款
                                    </c:when>
                                    <c:when test="${data['CONS_STATE']=='3'}">
                                        补助
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: red">非法退出</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>消费时间</th>
                            <td>
                                <jsp:useBean id="consTime" class="java.util.Date" />
                                <jsp:setProperty name="consTime" property="time" value="${data['CONS_TIME']}" />
                                <fmt:formatDate value="${consTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                        </tr>
                        <tr>
                            <th>消费金额(元)</th>
                            <td>${data['CONS_FARE']}</td>
                        </tr>
                        <tr>
                            <th>余额(元)</th>
                            <td>${data['X_MONEY']}</td>
                        </tr>
                    </table>
                        </br>
                    </c:forEach>
                </c:otherwise>
            </c:choose>


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
