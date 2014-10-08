<%@include file="/comm/common.jsp"%>
<%@page language="java" contentType="text/html;UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/userCenterWap/css/style.css" type="text/css" rel="stylesheet"/>
<title>子贵网_手机版_详细页 </title>
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("input[name='studentId']").change(function(){
                window.location="/userCenterWap/showXueJi.action?studentId="+$(this).val();
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

<div class="c_name pl10 d_line">
    <jsp:include page="../userInfo.jsp"></jsp:include>
</div>
<div class="middle">
    <jsp:include page="../chooseChild.jsp"/>
	<div class="detail">
    	<p class="d_base">基本信息</p>
    </div>
    <div class="line"></div>
    <div class="d_info">
    	<table width="98%" cellpadding="0" cellspacing="0">
        	<tr>
            	<td rowspan="5" width="57" align="center">
                    <div class="stu_img">
                        <c:choose>
                             <c:when test="${student.personalPhoto == null ||  '' == student.personalPhoto || 'null' == student.personalPhoto }">
                                 <img src="/images/default.jpg"/>
                             </c:when>
                            <c:otherwise>
                                <img src="http://dsis.952116.com/${student.personalPhoto}" onerror = "this.src = '/images/default.jpg'" />
                            </c:otherwise>
                        </c:choose>

                    </div>
                </td>
            </tr>
            <tr>
            	<td><strong>孩子姓名</strong>:${student.xs_xming}</td>
            </tr>
            <tr>
                <td><strong>出生日期</strong>:
                    <c:if test="${student.birthday == null|| ''==student.birthday || 'null' == student.birthday}">
                    </c:if>
                    <c:if test="${student.birthday !=null  && ''!= student.birthday  &&  'null' != student.birthday}">
                        <fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd"/>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td><strong>民族</strong>:
                    <c:if test="${student.mzhu == null|| ''==student.mzhu || 'null' == student.mzhu}">
                    </c:if>
                    <c:if test="${student.mzhu !=null  && ''!= student.mzhu  &&  'null' != student.mzhu}">
                        ${student.mzhu}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td><strong>手机</strong>:
                    <c:if test="${student.homephone == null|| ''==student.homephone || 'null' == student.homephone}">
                    </c:if>
                    <c:if test="${student.homephone !=null  && ''!= student.homephone  &&  'null' != student.homephone}">
                        ${student.homephone}&nbsp;
                    </c:if>
                </td>
            </tr>
           	<tr>
            	<td colspan="2"><strong>家庭成员</strong>:
                    <c:if test="${student.otherlinks == null|| ''==student.otherlinks || 'null' == student.otherlinks}">
                    </c:if>
                    <c:if test="${student.otherlinks !=null  && ''!= student.otherlinks  &&  'null' != student.otherlinks}">
                        ${student.otherlinks}&nbsp;
                    </c:if>
                </td>
            </tr> 
            <tr>
                <td colspan="2"><strong>卡号</strong>:
                    ${student.IDCard}
                </td>
            </tr>
            <tr>
                <td colspan="2"><strong>家庭住址</strong>:
                    <c:if test="${student.homeaddress == null|| ''==student.homeaddress || 'null' == student.homeaddress}">
                    </c:if>
                    <c:if test="${student.homeaddress !=null  && ''!= student.homeaddress  &&  'null' != student.homeaddress}">
                        ${student.otherlinks}&nbsp;
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
    <div class="detail">
    	<p class="d_base">在校信息</p>
    </div>
    <div class="line"></div>
    <div class="d_info_sch">
    	<table width="98%" cellpadding="0" cellspacing="0">
        	<tr>
            	<td><strong>所在学校</strong>：${student.school.sch_name}</td>
            </tr>
            <tr>
            	<td><strong>入校日期</strong>：${student.dateToSchool} </td>
            </tr>
            <tr>
            	<td><strong>所在年级</strong>：${grade.nj_mcheng}</td>
            </tr>
            <tr>
            	<td><strong>所在班级</strong>：${student.clasz.bj_mcheng}</td>
            </tr>
            <tr>
            	<td><strong>学号</strong>：
                    <c:if test="${student.xj_bhao == null|| ''==student.xj_bhao || 'null' == student.xj_bhao}">
                    </c:if>
                    <c:if test="${student.xj_bhao !=null  && ''!= student.xj_bhao  &&  'null' != student.xj_bhao}">
                        ${student.xj_bhao}&nbsp;
                    </c:if>
                </td>
            </tr>
            <tr>
            	<td><strong>政治面貌</strong>：
                    <c:if test="${student.politicalFace == null|| ''==student.politicalFace || 'null' == student.politicalFace}">
                    </c:if>
                    <c:if test="${student.politicalFace !=null  && ''!= student.politicalFace  &&  'null' != student.politicalFace}">
                        ${student.politicalFace}&nbsp;
                    </c:if>
                </td>
            </tr>
            <tr>
            	<td><strong>住宿情况</strong>:
                    <c:choose>
                        <c:when test="${student.accommodation == 1}">
                            住校&nbsp;
                        </c:when>
                        <c:otherwise>
                            不住校&nbsp;
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </div>     

<div class="footer_line"></div>
</div>


</body>
</html>
