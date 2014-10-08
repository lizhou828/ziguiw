<%@include file="/comm/common.jsp"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="uc_m">
    <div class="uc_m_con">
        <c:choose>
            <c:when test="${ fn:length(students) > 1}">
                <div class="jz-i-8 font-3">
                    <table width="100%" cellspacing="0" cellpadding="0" border="0">
                        <tbody>
                            <tr>
                                <td width="26%">
                                    <b>您有${fn:length(students)}个在校孩子：</b>
                                </td>
                                <c:forEach items="${students}" var="student">
                                    <td>
                                        <label>
                                            <input type="radio"  value="${student.xs_id}" name="studentId">
                                            ${student.xs_xming}
                                        </label>
                                    </td>
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </c:when>
        </c:choose>
        <p class="m_con_t">您的孩子的基本信息：</p>
        <ul class="m_inf">
            <li>姓名：${student.xs_xming}</li>
            <li>所在学校：${student.school.sch_name}</li>
            <li>所在班级：${clasz.bj_mcheng}</li>

        </ul>
        <% String currentTime=new SimpleDateFormat("yyyy年MM月dd日").format(new Date()); %>
        <p class="m_con_t">您的孩子<span><%=currentTime%> </span> 基本考勤情况：</p>
        <p class="m_kaoq"><span>正常考勤(<strong class="m_red">0</strong>)次 </span><span> 迟到(<strong class="m_red">0</strong>)次</span></p>
    </div>
    <hr style="height:1px;border: 1px solid #F3F3F3;"/>
    <div class="m_sug">
        <p>我对"子贵网"有<strong><a href="http://dsis.ziguiw.com/feedback/add.jsp">建议或意见</a></strong>，我要跟"子贵网" 说两句</p>
    </div>
</div>
<script type="text/javascript">
    $().ready(function(){
        $("input[name='studentId']").change(function(){
            window.location="/userCenter/userCenter.jsp?studentId="+$(this).val();
        });
    });
</script>