<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>

<div class="w_home_content">
  <div class="w_home_content_box clearfix">
<div class="content clearfix   pr">
<%@ include file="inc/right.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400  r">
       	<h2 class="col-h3">选择角色</h2>

		<ul class="link_gray ul-li-s">
			<li><span>选择角色</span>
			<select name="role" id="role" class="inp_txt" style="height:30px" <%if(user.getType() > 0){%> disabled="disabled" <%} %>>>
				<option value="0">请选择角色</option>
				<option value="1" <%if(user.getType() == 1){ %>selected="true"<%} %>>学校</option>
				<option value="2" <%if(user.getType() == 2){ %>selected="true"<%} %>>老师</option>
				<option value="3" <%if(user.getType() == 3){ %>selected="true"<%} %>>家长</option>
				<option value="4" <%if(user.getType() == 4){ %>selected="true"<%} %>>学生</option>
			</select>
			</li>
			<li><input name="button" type="button" value="选择角色" class="btn2" id="role_select" onclick="javascript:void(0)"/></li>		
		</ul>
    </div>
  
</div>    
</div>
</div>
<!--content E -->
<script type="text/javascript">
	$('#role_select').click(function(){
		if($('#role').val() == 0){
			alert("请选择角色");
			return;
		}
		
		$.post("<%=path%>/user_ajaxSelectRole.action?user.type=" + $('#role').val(), function(data) {
			  alert("类型保存成功");
		});
		
		if($('#role').val() == 4){
			window.location = "<%=path%>/user/fufillment_student.jsp";
		}else if($('#role').val() == 3){
			window.location = "<%=path%>/user/fufillment_parent.jsp";
		}else if($('#role').val() == 2){
			window.location = "<%=path%>/user/fufillment_teacher.jsp";
		}else if($('#role').val() == 1){
			window.location = "<%=path%>/user/fufillment_school.jsp";
		}
	});
</script>

<%@ include file="../inc/templateFoot.jsp"%>