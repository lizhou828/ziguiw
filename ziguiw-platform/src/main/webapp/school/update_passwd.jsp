<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>


<div class="content clearfix   pr">
<%@ include file="inc/school_left.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400  r" style="min-height:550px">
       	<h2 class="col-h3">修改密码</h2>

		<form method="post" action="<%=path%>/user/updateSchoolPassword.action">
<div class="red"><s:fielderror/></div>
		<ul class="link_gray ul-li-s">
			<li><span>现有密码:</span><input name="oldPassword" type="password" class="inp_txt" /></li>
				<li><span>新密码:</span><input name="user.password" type="password" class="inp_txt" /></li>
				<li><span>再输入一次:</span><input name="secondPassword" type="password" class="inp_txt" /></li>				
				<li><input name="Submit1" type="submit" value="确认修改" class="btn2"/></li>	
				
		</ul>
		</form>
    </div>
  
</div>    
<!--content E -->
<script type="text/javascript">
function initSelect(){
	var sex = document.getElementById("sex");
	var province = document.getElementById("province");

	var oldSex = document.getElementById("oldSex").value;
	var oldProvince = document.getElementById("oldProvince").value;
	
	
    for(var i=0;i<sex.options.length;i++)
    {
        if(sex.options[i].value == oldSex)
        {
        	sex.options[i].selected = true;
            break;
        }
    }
    
    for(var i=0;i<province.options.length;i++)
    {
        if(province.options[i].value == oldProvince && oldProvince != "")
        {
        	province.options[i].selected = true;
        	break;
        }
    }
    
    
    
    
}

initSelect();
</script>

<%@ include file="../inc/templateFoot.jsp"%>