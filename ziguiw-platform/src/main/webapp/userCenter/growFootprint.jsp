<%@ page import="com.zigui.domain.UserBase" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>成长足迹-子贵网用户中心</title>
    <link href="/css/uccss.css" type="text/css" rel="stylesheet" />
    <link href="/css/kaoqinchaxun.css" type="text/css" rel="stylesheet" />
    <script src="/js/basic.js.jsp"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" defer="defer"></script>

</head>
<body >

<!--onload="ajaxShowHeadMsg()"-->
<jsp:include page="/userCenter/ucPublic/head.jsp"></jsp:include>
<div class="uc_midd">
    <div class="uc_l">


        <jsp:include page="dsisQueryNavigator.jsp"/>

        <jsp:include page="commonQuestion.jsp" >
            <jsp:param name="columnId" value="5"></jsp:param>
            <jsp:param name="columnName" value="常见问题"></jsp:param>
        </jsp:include>


    </div>


    <div class="uc_right">
        <%UserBase user=(UserBase)session.getAttribute("VALID_USER");%>
        <form action="">
            <div class="right">
                <div class="bobti"> 成长足迹</div>

                 <div class="xunc_1 font-2">
                    <div class="xunc_1-1">
                    <table width="697" cellspacing="0" cellpadding="0" border="0">
                        <tbody>
                        <tr>
                            <td>
                                班级：
                                <select id="classId" name="classId">
                                    <option value="-1">
                                        --必选--
                                    </option>

                                    <option selected="selected" value="1717">
                                        188班
                                    </option>

                                </select>
                            </td>
                            <td>
                                学生:
                                <select id="stuId" name="stuId">
                                    <option value="0">
                                        --可选--
                                    </option>

                                    <option value="56983">
                                        蒋凌波
                                    </option>

                                    <option selected="selected" value="-1">
                                        --全部--
                                    </option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2">
                                日期:
                                <input type="text" onclick="WdatePicker()" style="float: none" class="Wdate" id="startDate" name="startTime">
                                到
                                <input type="text"  onclick="WdatePicker()" class="Wdate" style="float: none" id="endDate" name="endTime">
                            </td>
                            <td><input type="button" value="查询" class="cx" id="QueryBtn">
                            </td>
                        </tr>

                        </tbody></table>
                </div>
                </div>

                 <div class="xunc_2 font-2">
                <table width="729" cellspacing="0" cellpadding="0" border="0">
                    <tbody><tr>
                        <td class="t1 font-2">
                            时间
                        </td>
                        <td class="t1 font-2">
                            星期
                        </td>
                        <td class="t1 font-2">
                            活动名称
                        </td>
                        <td class="t1 font-2">
                            特长
                        </td>
                        <td class="t1 font-2">
                            操作
                        </td>
                    </tr>

                    <tr>

                        <td>
                            2013.04.22
                        </td>
                        <td>
                            星期日
                        </td>
                        <td class="t2">
                            全市青少年国际象棋锦标赛
                        </td>
                        <td>
                            下棋
                        </td>
                        <td>
                            <a class="icon16 " href="#"><img title="查看" alt="" src="ucimages/book_open.png"></a>

                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>

                 <input type="hidden" name="gotoPage" id="gotoPage">
                <div class="pageNav">
                    <span class="na">&lt;上一页</span><strong>1</strong><span class="na">下一页&gt;</span>
                </div>
        </div>
        </form>
    </div>

    <div class="clear"></div>

    <div class="uc_noti">
        <p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>

</div>

<jsp:include page="/inc/templateFoot.jsp"/>
