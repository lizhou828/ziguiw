<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/templateHead.jsp"%>
<div class="content clearfix pr">
	<%@ include file="inc/left.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea w-760 hei-400 r about_us">
		<!--<h2 class="col-h3">&nbsp;</h2>-->
	  <div style="padding-left:20px; padding-right:20px;" id="_wContent">
		<h3><img src="../images/about_content_title.gif" width="673" height="83" /></h3>
		<style>
        	._wul{list-style-type:none; width:550px; margin-bottom:30px;}
			._wul li{height:60px; border-bottom:1px dotted #e0e0e0;line-height:30px; padding-left:30px; }
			._wul i{ margin-left::30px; white-space:nowrap;}
        </style>
        <div  style=" width:700px; margin-left:100px; margin-top:40px;">
		<b >湖南爱瑞杰科技发展股份有限公司</b>
          <ul class="_wul">

            <li >
              <h2 >联系电话：</h2>
              <i style="margin-left:30px;">0731-84159925</i>
              
              </li>
            <li >
              <h2>服务电话：</h2>
              <i style="margin-left:30px;">952116</i></li>
            <li >
              <h2>E-Mail：</h2>
              <i style="margin-left:30px;"><a href="mailto:admin@ziguiw.com">admin@ziguiw.com</a></i></li>
            <li >
              <h2>公司地址：</h2>
              <i style="margin-left:30px;">长沙市雨花区万家丽路二段68号华晨双帆国际1206室</i>
          </ul>
          <img src="../images/dt-img_03.gif" alt="" width="445" height="234" align="middle"  style="margin-left:30px"/></li>
        </div>
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