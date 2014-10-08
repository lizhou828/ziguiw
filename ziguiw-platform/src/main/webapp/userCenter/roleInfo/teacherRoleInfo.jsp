<%@ page import="com.zigui.domain.Clasz" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/comm/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="uc_m">
    <div class="uc_m_con">
        <p class="m_con_t">您的基本信息：</p>

        <ul class="m_inf">
            <li>姓名：${teacher.name}</li>
            <li>所在学校：${teacher.school.sch_name}</li>
            <li>管理的班级： <li>
            <li style="overflow:auto;height:auto;">
            <%
                List<Clasz> claszList = (List<Clasz>) request.getAttribute("claszList");
                for (Clasz clasz : claszList) {
                    %>
                    &nbsp;<%= clasz.getBj_mcheng()%>&nbsp;
                    <%
                }
            %>
            </li>

            <li>共（<span class="m_red">${studentCount} </span>）名学生</li>
        </ul>
        <% String currentTime=new SimpleDateFormat("yyyy年MM月dd日").format(new Date()); %>
        <p class="m_con_t">您的班级学生<span><%=currentTime%></span>基本考勤情况：</p>
        <p class="m_kaoq"><span>正常考勤(<strong class="m_red">0</strong>)次 </span><span> 迟到(<strong class="m_red">0</strong>)次</span></p>
    </div>
    <hr style="height:1px;border: 1px solid #F3F3F3;"/>
    <div class="m_sug">
        <p>我对"子贵网"有<strong><a href="http://dsis.ziguiw.com/feedback/add.jsp">建议或意见</a></strong>，我要跟"子贵网" 说两句</p>
    </div>
</div>