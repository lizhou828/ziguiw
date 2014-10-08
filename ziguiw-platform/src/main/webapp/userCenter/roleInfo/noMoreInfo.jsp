<%@include file="/comm/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="uc_m">
    <div class="uc_m_con">
        <p class="m_con_t"></p>
        <ul class="m_inf">
            <li>抱歉，未查到相关信息！</li>
            <li>可能原因：</li>
            <c:choose>
                <c:when test="${user.type==2}">
                    <li style="color: red">未绑定学校或班级。</li>
                </c:when>
                <c:when test="${user.type==3}">
                    <li style="color: red">未绑定学生卡号。</li>
                </c:when>
                <c:when test="${user.type==4}">
                    <li style="color: red">未绑定学生卡号。</li>
                </c:when>
                <c:otherwise>
                   Sorry, can't find out the information you're looking for...
                </c:otherwise>
            </c:choose>

        </ul>
    </div>
    <hr style="height:1px;border: 1px solid #F3F3F3;"/>
    <div class="m_sug">
        <p>我对"子贵网"有<strong><a href="http://dsis.ziguiw.com/feedback/add.jsp">建议或意见</a></strong>，我要跟"子贵网" 说两句</p>
    </div>
</div>