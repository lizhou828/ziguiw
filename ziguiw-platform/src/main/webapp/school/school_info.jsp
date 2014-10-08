<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>


<div class="content clearfix   pr">
<%@ include file="inc/school_left.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-700  r">
       	<h2 class="col-h3">资料完善</h2>

		<form method="post" action="<%=path%>/user/updateSchoolInfo.action">
		<ul class="link_gray ul-li-s">
			<li><span>校友信箱:</span><input name="schoolInfo.masterContact" type="text" class="inp_txt" value='<s:property value="schoolInfo.masterContact"/>'/></li>
			<li><span>地址:</span><textarea name="schoolInfo.contact" type="text" class="inp_txt"><s:property value="schoolInfo.contact"/></textarea></li>
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