<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="/comm/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生评语</title>
    <link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="/userCenterWap/js/pageQuery.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("input[name='studentId']").change(function(){
                window.location="/userCenterWap/studentCommentList.action?studentId="+$(this).val();
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
        学生评语<span style="color:#2d2d2d;font-family:Arial, Helvetica, sans-serif;font-weight:normal;font-size:12px;"></span>
    </div>
    <div class="xunc_1-1">
		<font color="red"><p> &nbsp;近一个月内的信息</p></font>
    </div>
    
   
   <div class="ly-tab font-2">
       <form action="/userCenterWap/studentCommentList.action" method="post">
           <jsp:include page="../chooseChild.jsp"/>
           </br></br>
		       <table cellspacing="0" cellpadding="0" border="0" class="sideright_tab1">
		          <tbody>
                        <tr bgcolor="#f2f4f6">
		                    <td width="100%" class="td_5">信息内容</td>
		                </tr>
			            <c:choose>
                            <c:when test="${map['data'] ==null || fn:length(map['data'])==0}">
                                <tr style="cursor:pointer">
                                    <td class="td_6">
                                        无相关信息！
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr style="cursor:pointer">
                                    <td class="td_6">${data['MSM_CONTENT']}&nbsp;
                                        <p>${data['DISPLAY_DATE']}&nbsp;</p>
                                    </td>
                                </tr>
                            </c:otherwise>
			            </c:choose>

		           </tbody>
               </table>
           <input type="hidden" value="1" name="gotoPage" id="gotoPage">

       </form>
   </div>

    <div class="pageNav">
        <input id=maxPage type=hidden  value="${totalPages}">
        第${gotoPage}/${totalPages}页，跳转到<input id ="inputPage" type="text"  size="1"/> 页
        <input onclick="changePage();" type="button" id="page" value="Go"/>
        
    </div>
    

</body>
</html>
