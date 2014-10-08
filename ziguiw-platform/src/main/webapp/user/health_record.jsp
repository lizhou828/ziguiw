<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>


<div class="content clearfix pr">
  <%@ include file="inc/info_left.jsp"%>
  <!--ad01 E -->
<div class="ask_and_sea found w-760 hei-700 r ">
       	<h2 class="col-h3">用户中心</h2>

	
		<ul class="link_gray ul-li-s">
			<li><span>血型:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
			<li><span>药物过敏史:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
			<li><span>地方病史:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
			<li><span>既往病史:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
			<li><span>遗传病史:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
			<li><span>视力:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
			<li><span>听力:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
			<li><span>身高:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
			<li><span>体重:</span><input name="PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" /></li>
		</ul>
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