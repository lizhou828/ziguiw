<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学生学籍档案——子贵网用户中心</title>
    <link href="../css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="../css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $().ready(function(){
            $("input[name='studentId']").change(function(){
                window.location="/userCenter/showXueJi.action?studentId="+$(this).val();
            });
        });
    </script>

</head>

<body>
<jsp:include page="/userCenter/ucPublic/head.jsp"></jsp:include>

<div class="uc_midd">
    <div class="uc_l">

        <jsp:include page="/userCenter/dsisQueryNavigator.jsp"/>

        <jsp:include page="/userCenter/commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>

    </div>


    <div class="uc_right">
        <!--插入数字化校园的区域 开始-->

        <div class="right">
            <div class="bobti font-5">${student.xs_xming}-学籍档案</div>
            <c:choose>
                <c:when test="${ fn:length(students) > 1}">
                    <div class="jz-i-8 font-3" style="margin-left: 20px">
                        <table width="100%" cellspacing="0" cellpadding="0" border="0">
                            <tbody>
                            <tr>
                                <td width="26%">
                                    <b>请选择你的孩子：</b>
                                </td>

                                    <td>
                                        <c:forEach items="${students}" var="student">
                                            <input type="radio"  value="${student.xs_id}" name="studentId">
                                                ${student.xs_xming}
                                        </c:forEach>
                                    </td>

                            </tr>
                            </tbody>
                        </table>
                    </div>
                </c:when>
            </c:choose>
            <div class="zzll">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="21%" rowspan="2" valign="top">
                            <table border="0" cellspacing="0" cellpadding="0" class="tx-img-1 font-2">
                                <tr>
                                    <td valign="top">
                                        <c:if test="${student.personalPhoto == null ||  '' == student.personalPhoto}">
                                            <div class="xx-img">

                                                <img src="/images/default.jpg"/>
                                            </div>
                                        </c:if>
                                        <c:if test="${student.personalPhoto != null && ''!= student.personalPhoto}">
                                            <div class="xx-img">
                                                <img src="http://dsis.952116.com/${student.personalPhoto}" onerror = "this.src = '/images/default.jpg'" />
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="79%"><table border="0" cellspacing="0" cellpadding="0" class="tab-xx-5 font-2">
                            <tr>
                                <td align="left" valign="middle" class="xgr-1"><b>基本信息</b><a href="#"></a></td>
                                <td align="right" valign="middle" class="xgr-1">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="20%" align="left" valign="middle" class="xgr">孩子姓名：</td>
                                <td width="80%" class="xgr">
                                    ${student.xs_xming}&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td  align="left" valign="middle" class="xgr">出生日期：</td>
                                <td  class="xgr">
                                    <c:if test="${student.birthday == null|| ''==student.birthday || 'null' == student.birthday}">
                                    </c:if>
                                    <c:if test="${student.birthday !=null  && ''!= student.birthday  &&  'null' != student.birthday}">
                                        <fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd"/>
                                        &nbsp;
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td  align="left" valign="middle" class="xgr">性别：</td>
                                <td  class="xgr">

                                    <c:if test="${student.sex == null|| ''==student.sex || 'null' == student.sex}">
                                    </c:if>
                                    <c:if test="${student.sex !=null  && ''!= student.sex  &&  'null' != student.sex}">
                                        ${student.sex}&nbsp;
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle" class="xgr">一卡通卡号：</td>
                                <td class="xgr"><p>${student.IDCard}&nbsp;</p></td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle" class="xgr">民族：</td>
                                <td class="xgr">

                                    <c:if test="${student.mzhu == null|| ''==student.mzhu || 'null' == student.mzhu}">
                                    </c:if>
                                    <c:if test="${student.mzhu !=null  && ''!= student.mzhu  &&  'null' != student.mzhu}">
                                        ${student.mzhu}&nbsp;
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" valign="top" class="xgr">家庭成员：</td>
                                <td class="xgr">

                                    <c:if test="${student.otherlinks == null|| ''==student.otherlinks || 'null' == student.otherlinks}">
                                    </c:if>
                                    <c:if test="${student.otherlinks !=null  && ''!= student.otherlinks  &&  'null' != student.otherlinks}">
                                        ${student.otherlinks}&nbsp;
                                    </c:if>

                                </td>
                            </tr>
                            <tr>
                                <td align="left" valign="top" class="xgr">手机:</td>
                                <td class="xgr">

                                    <c:if test="${student.homephone == null|| ''==student.homephone || 'null' == student.homephone}">
                                    </c:if>
                                    <c:if test="${student.homephone !=null  && ''!= student.homephone  &&  'null' != student.homephone}">
                                        ${student.homephone}&nbsp;
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" valign="top"  class="xgr">家庭住址：</td>
                                <td class="xgr">

                                    <c:if test="${student.homeaddress == null|| ''==student.homeaddress || 'null' == student.homeaddress}">
                                    </c:if>
                                    <c:if test="${student.homeaddress !=null  && ''!= student.homeaddress  &&  'null' != student.homeaddress}">
                                        ${student.otherlinks}&nbsp;
                                    </c:if>
                                </td>
                            </tr>
                        </table></td>
                    </tr>
                    <tr>
                        <td><table border="0" cellspacing="0" cellpadding="0" class="tab-xx-5 font-2">
                            <tr>
                                <td align="left" valign="middle" class="xgr-1"><b>在校信息</b></td>
                                <td align="right" valign="middle" class="xgr-1">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="20%" align="left" valign="middle"class="xgr">所在学校：</td>
                                <td width="80%" class="xgr">${student.school.sch_name}&nbsp;</td>
                            </tr>
                            <tr>
                                <td  align="left" valign="middle"class="xgr">入校日期：</td>
                                <td class="xgr">${student.dateToSchool}&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"class="xgr">所在年级：</td>
                                <td class="xgr">
                                  ${grade.nj_mcheng}&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"class="xgr">所在班级：</td>
                                <td class="xgr">${student.clasz.bj_mcheng}&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"class="xgr">学号：</td>
                                <td class="xgr">

                                    <c:if test="${student.xj_bhao == null|| ''==student.xj_bhao || 'null' == student.xj_bhao}">
                                    </c:if>
                                    <c:if test="${student.xj_bhao !=null  && ''!= student.xj_bhao  &&  'null' != student.xj_bhao}">
                                        ${student.xj_bhao}&nbsp;
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle"class="xgr">政治面貌：</td>
                                <td class="xgr">
                                    <c:if test="${student.politicalFace == null|| ''==student.politicalFace || 'null' == student.politicalFace}">
                                    </c:if>
                                    <c:if test="${student.politicalFace !=null  && ''!= student.politicalFace  &&  'null' != student.politicalFace}">
                                        ${student.politicalFace}&nbsp;
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" valign="middle" class="xgr">住宿情况：</td>
                                <td class="xgr">
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
                        </table></td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="clear"></div>



    </div>
</div>

<div class="uc_noti">
    <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
</div>
<jsp:include page="/inc/templateFoot.jsp"/>
