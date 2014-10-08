<%@ page import="com.zigui.domain.*" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@include file="/comm/common.jsp"%>




<%
    UserBase user=(UserBase)session.getAttribute("VALID_USER");
    int userType=user.getType();
%>

<div class="uc_p">
    <div class="uc_p_left">
        <div class="uc_photo">
            <c:choose>
                <c:when test="${user.portrait == null || '' == user.portrait}">
                    <img src="/userCenter/ucimages/photo.gif" />
                </c:when>
                <c:otherwise>
                    <img  width="165" height="160" src="${user.portrait}" onerror = "this.src = '/userCenter/ucimages/photo.gif'" />
                </c:otherwise>
            </c:choose>



        </div>
        <p class="modi"><a href="/user/upload_portrait.jsp">修改头像</a></p>
    </div>
    <div class="uc_p_right">
        <div class="uc_p_inf">
            <table width="400">


                <c:set var="userType" scope="session" value="<%=userType%>"/>
                <c:choose>
                    <c:when test="${userType==2}">
                        <tr>
                            <td class="n_str">姓&nbsp;&nbsp;&nbsp;名：</td>
                            <td class="n_con">${teacher.name}</td>

                        </tr>
                        <tr>

                            <td class="n_str">账&nbsp;&nbsp;&nbsp;号：</td>
                            <td class="n_con">${user.nickName}</td>
                        </tr>
                        <tr>
                            <input type="hidden" value="${user.type}"  >
                            <td class="n_str">身份类别：</td>
                            <td class="n_con" > 老师</td>
                        </tr>
                    </c:when>
                    <c:when test="${userType==3}">
                        <tr>
                        <td class="n_str">姓&nbsp;&nbsp;&nbsp;名：</td>
                        <td class="n_con">
                                <c:choose>
                                    <c:when test="${parent.PARENTS_NAME==null || parent.PARENTS_NAME=='' || 'null'==parent.PARENTS_NAME}">
                                        ${student.xs_xming}家长
                                    </c:when>
                                    <c:otherwise>
                                        ${parent.PARENTS_NAME}
                                    </c:otherwise>
                                </c:choose>
                        </td>

                         </tr>

                        <tr>
                            <td class="n_str">账&nbsp;&nbsp;&nbsp;号：</td>
                            <td class="n_con">${user.nickName}</td>
                        </tr>
                        <tr>
                            <input type="hidden" value="${user.type}"  >
                            <td class="n_str">身份类别：</td>
                            <td class="n_con" >  家长</td>
                        </tr>

                    </c:when>
                    <c:when test="${userType==4}">
                        <tr>
                            <td class="n_str">姓&nbsp;&nbsp;&nbsp;名：</td>
                            <td class="n_con">${student.xs_xming}</td>

                        </tr>
                        <tr>
                            <td class="n_str">账&nbsp;&nbsp;&nbsp;号：</td>
                            <td class="n_con">${user.nickName}</td>
                        </tr>
                        <tr>
                            <input type="hidden" value="${user.type}"  >
                            <td class="n_str">身份类别：</td>
                            <td class="n_con" >学生</td>
                        </tr>

                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td class="n_str">账&nbsp;&nbsp;&nbsp;号：</td>
                            <td class="n_con">${user.nickName}</td>
                        </tr>
                        <tr>
                            <input type="hidden" value="${user.type}"  >
                            <td class="n_str">身份类别：</td>
                            <td class="n_con" > 游客</td>
                        </tr>

                    </c:otherwise>

                </c:choose>




                <tr>
                    <td colspan="2">&nbsp;</td>
                </tr>
                <tr>
                    <td height="15">登录次数：</td>
                    <td>${user.loginCount}</td>
                </tr>
                <tr>
                    <td>上次登录：</td>
                    <td>
                    <fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" height="28">
                        <a href="/user/update_passwd.jsp"><input type="button" class="in_btn" value="修改密码"/></a>
                        <a href="/user/select_role.jsp"><input type="button" class="in_btn" value="完善资料"/></a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="uc_mon">
            <p>我的积分：<span style="color:#c90408;font-family:Arial, Helvetica, sans-serif;font-size:24px;">${user.points}</span>分</p>
            <div class="mon_btn">
                <a href="${pageContext.request.contextPath}/payment/chongzhi_1.jsp"><img  src="/userCenter/ucimages/mon_btn.gif" /></a>
            </div>
        </div>
    </div>
</div>