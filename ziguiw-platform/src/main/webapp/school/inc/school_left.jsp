<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
if(hostUser.getType() != 1){
	response.sendRedirect(path + "/user/myhome/" + hostUser.getNickName());
	return;
}
%>
<div class="l w-190 my_left clearfix" style="" >
		<div class="my_info tt userCenterLeft" style="height:550px">
			
			<div class="userCenterMenuBar">
			<ul>
            <li><a href="<%=path%>/user/school_summaryManage.action" class="bj8 font-2 <%if(request.getRequestURL().toString().indexOf("summary") > 0){%>focus<%}%>">学校简介</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/user/school_speechManage.action" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("speech") > 0){%>focus<%}%>">校长致辞</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/user/school_jobManage.action" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("job") > 0){%>focus<%}%>">招聘信息</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/user/school_updateInfo.action" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("info") > 0 || request.getRequestURL().toString().indexOf("Info") > 0){%>focus<%}%>">地址信息</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/school/publish_diary.jsp?type=5" class="bj9 font-2">校园新闻</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/school/publish_diary.jsp?type=6" class="bj9 font-2">学校公告</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/school/publish_diary.jsp?type=7" class="bj9 font-2">招生信息</a></li>
          </ul>
			 <ul>
            <li><a href="<%=path%>/school/point_list.jsp" class="bj9 font-2">我的积分</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/school/fillment_school.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("fillment") > 0){%>focus<%}%>">完善资料</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/school/update_passwd.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("passwd") > 0){%>focus<%}%>">修改密码</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/user/upload_portrait.jsp" class="bj9 font-2 ">修改头像</a></li>
          </ul>
<!--  
          <ul>
            <li><a href="<%=path%>/school/clasz_manager.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("clasz") > 0){%>focus<%}%>">班级管理</a></li>
          </ul>
          <ul>
            <li><a href="<%=path%>/school/teacher_manager.jsp" class="bj9 font-2 <%if(request.getRequestURL().toString().indexOf("teacher") > 0){%>focus<%}%>">教师管理</a></li>
          </ul>
	-->			
			</div>
			
		</div>
	</div>