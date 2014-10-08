<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>


<div class="content clearfix pr">
	<%@ include file="inc/school_left.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 r" style="min-height:550px">
       	<h2 class="col-h3">学校概况</h2>

		<div class="hei-400">
		<p>
			<s:property value="schoolInfo.summary" escape="false"/>
		</p>
		
		<a href="<%=path%>/user/summary_prepare_update.action"><input type="button" class="btn2 r mt5" value="修改"></a>
		</div>
		
		
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