<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/info_head.jsp"%>
<style>
			.field-100{ width:100px}
			.field-200{ width:100px}
			.field-short{ width:30%}
			.field-normal{ width:50%;}
			.field-long{ width:80%;}
			
			.formStyle1{ font-size:14px;border-top:2px solid #fd6e1f;border-bottom:2px solid #fd6e1f;  padding-left:5px; }
			.formStyle1 legend{ margin:0px; padding:0px; font-size:14px; font-weight:bold;color:#fd6e1f }
        	.formStyle1 .label{ float:left;padding-top:4px; font-weight:bold; text-align:right; width:90px;}
			.formStyle1 .field{ padding-top:4px;display:inline-block; *display:inline;zoom:1px;*padding-top:4px; *line-height:20px; }
			.formStyle1 .field input[type=text]{ vertical-align:middle; margin-right:10px; border:1px solid #CCC; font-size:14px;}
			.formStyle1 ul li{  margin:10px 5px; }


			.formStyle2{ font-size:14px;border-top:2px solid #fd6e1f;border-bottom:2px solid #fd6e1f;  padding-left:5px; background:#faf4ea }
			.formStyle2 legend{ margin:0px; padding:0px;font-size:14px; font-weight:bold}
        	.formStyle2 .label{ float:left;padding-top:4px; font-weight:bold; text-align:right; width:90px;}
			.formStyle2 .field{ padding-top:4px;display:inline-block; *display:inline;zoom:1px;*padding-top:4px; *line-height:20px; }
			.formStyle2 .field input[type=text]{ vertical-align:middle; margin-right:10px; border:1px solid #f8e0b4; font-size:14px;}
			.formStyle2 textarea{border:1px solid #f8e0b4 !important;}
			.formStyle2 ul li{  padding:5px 2px; border-bottom:1px dotted #eeb247 ; width:90%; margin:auto}
			.formStyle2 ul li.end{  border-bottom:0px !important;}

			.formStyle3 { width:100%; table-layout:fixed;}
			.formStyle3 .head{  font-weight:bold; letter-spacing:2px;}
			.formStyle3 .head td{ width:inherit !important; background:#fd6e1f !important; color:#fff;}
			.formStyle3 .foot td{ height:6px; overflow:hidden; background:#fd6e1f !important;}
			.formStyle3 td{ background:#fcf2e0; border:1px solid #fff; border-right:2px solid #fff;padding:6px; color:#666; }
			.formStyle3 td.field1{ background:#ececec;color:gray; width:90px; text-align:center; font-size:12px; text-align:right;}
			.formStyle3 input[type=text]{ vertical-align:middle; margin-right:10px; border:1px solid #f8e0b4; font-size:14px;}
			.formStyle3 textarea{border:1px solid #f8e0b4 !important;}


			.formBtnBar { border-bottom:2px solid #fcf2e0; padding-bottom:4px; text-align:right; margin-right:60px;}
			.formBtnBar button{border:1px solid #d34b00; color:#fff; font-size:14px; padding:2px 8px; margin-right:10px;background:#fd6e1f;}
			.formBtnBar .gray{ background:#CCC !important;border:1px solid #999 !important;}
			.formBtnBar-bottom { border:0px !important; border-top:2px solid #fcf2e0  !important;paddding:0px; padding-top:4px  !important; text-align:right}
			.formBtnBar-noBorder{border:0px !important;}
			
			.actionBtnBar { }
			.actionBtn1{border:1px solid #CCC; color:#fff; font-size:14px; padding:2px 8px; margin-right:10px;background:#fd6e1f;}
			.actionBtn2{border:1px solid #d34b00 !important; color:#fff; font-size:14px; padding:2px 8px; margin-right:10px;background:#fd6e1f;}
			.actionBtn3{border:0px solid #CCC; color:#fff; font-size:14px; padding:2px 8px; margin-right:10px;background:#fd6e1f;}
			.actionBtn4{border:1px solid #CCC; color:#fff; font-size:14px; padding:2px 8px; margin-right:10px;background:#ccc;}
			.actionBtn5{border:1px solid #c1c8d2; border-top:0px; border-left:0px; color:#333333; font-size:14px; padding:2px 8px; margin-right:10px;background:#f2f4f6;}
			.actionBtn6{border:1px solid #c1c8d2; border-top:0px; border-left:0px; color:#333333; font-size:14px; padding:2px 8px; margin-right:10px;background:#fcf2e0;}
			.actionBtn7{border:1px solid #ff6f20; border-top:0px; border-left:0px; color:#ff3b20; font-size:14px; padding:2px 8px; margin-right:10px;background:#fcf2e0;}
			.actionBtn8{border:1px solid #999; border-top:0px; border-left:0px; color:#000; font-size:14px; padding:2px 8px; margin-right:10px;background:#CCC;}
        </style>

<div class="content clearfix pr">
  <%@ include file="inc/info_left.jsp"%>
  <!--ad01 E -->
<div class="ask_and_sea found w-760 hei-700 r ">
       	<h2 class="col-h3">用户中心</h2>

	<table class="listStyle1" cellpadding="2" cellspacing="2" border="0" >
	    <s:iterator value="datas.list" var="data">
           	<tr>
                 <td class="light"><s:property value="#data.SMS_CONTENT"/></td>
               </tr>
           	
               </s:iterator>
           </table>
           
           <s:if test="datas.pageString != null && datas.pageString != ''">
		<div class="pagenum">
			<s:property value="datas.pageString" escape="false"/>
		</div>
		</s:if>
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