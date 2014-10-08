<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" isELIgnored = "false"%>

<%@include file="/source/common_tag.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  
<title>子贵网--资源下载中心</title>

 <meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    

	
	<script type="text/javascript" src="${ctx}/source/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ctx}/source/js/changeskin.js"></script>
	<script type="text/javascript" src="${ctx}/source/js/png-tm.js"></script>
	<script type="text/javascript" src="${ctx}/source/js/msg.js"></script>
		
	<link href="${ctx}/source/css.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="${ctx}/source/js/jquery/jquery-plugin/validate/jquery.validate.js"></script>
	<script type="text/javascript">
	$().ready(function() {
		
		$("#net").bind("change",function(){
			var checked = $(this).attr("checked");
			$("#netPath").attr("disabled",!checked);
			$("#sourcefile").attr("disabled",checked);
			$("#needpoint").attr("disabled",checked);
		});
	});
	</script>

<script>
function subform1(){
	if(isLogin() && isNetPathorSourcefile() && istitle() && isTypeAndVersion()){
		return true;
	}
	return false;
}

//验证文件标题
function istitle(){
	var title = $("#title").val();
	if(title.length==0){
		alert("文件标题不能为空!");
		return false;
	}
	if(title.length > 256){
		alert("文件标题字符必须小于256!");
		return false;
	}
	return true;
}

//验证文件内容
function isNetPathorSourcefile(){
	if($("#net").attr("checked")){
		var netPath = $("#netPath").val();
		if(netPath.length == 0){
			alert("外部链接不能为空");
			return false;
		}
	}else{
		var sourcefile = $("#sourcefile").val();
		if(sourcefile.length == 0){
			alert("资源不能为空!");
			return false;
		}
	}
	return true;
}

//验证类型与版本
function isTypeAndVersion(){
	var sourcetype = document.getElementsByName("sourcetype");
	for(var i=0;i<sourcetype.length;i++){
		if(sourcetype[i].checked){
			break;
		}
		if(i==(sourcetype.length-1)){
			alert("请选择文件类型！");
			return false;
		}
	}
	var sourceversion = document.getElementsByName("sourceversion");
	for(var i=0;i<sourceversion.length;i++){
		if(sourceversion[i].checked){
			break;
		}
		if(i==(sourceversion.length-1)){
			alert("请选择文件版本！");
			return false;
		}
	}
	return true;
}

//格式
function suffix(r){
	var sourcetype = document.getElementsByName("sourcetype");
	for(var i=0;i<sourcetype.length;i++){
		document.getElementById(sourcetype[i].value).style.display = "none";
	}
	document.getElementById(r.value).style.display = "block";
}

//验证是否登陆
function isLogin(){
	/* var loginMemberId = document.getElementById("loginMemberId").value;
	if(loginMemberId.length == 0 || loginMemberId == "null"){
		alert("您还没登陆!");
		window.location = "${ctx}/login.jsp";
		return false;
	} */
	return true;
}

function dj(i)
{
  for(j=1;j<10;j++)  //遍历刷新所有门的状态，序号与参数相同的的一种状态，其余一种状态
    {
  if(i==j){
   document.getElementById("a"+j).className="li2";
   document.getElementById("b"+j).style.display="";
          }
   else
    {
   document.getElementById("a"+j).className="li1";
   document.getElementById("b"+j).style.display="none";
      }
     }
 
}
</script>

<style type="text/css">
<!--
.STYLE1 {
	color: #FF3300;
	font-weight: bold;
}
.STYLE2 {color: #666666}
-->
</style>
</head>

<body>
	              
	<div class="main">
  <jsp:include page="source_top.jsp"></jsp:include>
		<div class="t-neirong-01">
   		<div class="t-neirong-liebiao-z">
   		<form id="form1" method="post" class="form" action="${ctx}/source/sourceAdd.action" onsubmit="return subform1()" enctype="multipart/form-data">
			<s:token></s:token>
			<% String   loginMemberId   =   (String)session.getAttribute("loginMemberId"); %>
			<input type="hidden" name="loginMemberId" id="loginMemberId" value="<%=loginMemberId %>"/>
   		     <table width="100%" border="0" cellspacing="0" cellpadding="0">
   		      <!-- <tr>
   		      	<td height="50" align="right" class="text-1-2">
   		      		<input type="checkbox" id="net" name="net" />&nbsp;外部链接：
   		      	</td>
   		      	<td height="50">
                 <input type="text" disabled="disabled" id="netPath"  name="netPath" type="text" class="text-1-3-shuru" maxlength="100" size="70" />
                 </td>
   		      </tr> -->
              <tr>
                <td width="14%" height="50" align="right" class="text-1-2">*选择文件：</td>
                <td width="86%" height="50"><input type="file" id="sourcefile"  class="text-1-3" name="sourcefile"  style="width:300px; height: 24px"/>
                  <span class="text-1-3">所上传的视频文件大小不能超过1024M </span></td>
              </tr>
              <tr>
                <td height="50" align="right" class="text-1-2">*文件标题：</td>
                <td height="50">
                 <input type="text" id="title"  name="title" type="text" class="text-1-3-shuru" size="70" /></td>
                </tr>
              <tr>
                <td height="50" align="right" class="text-1-2">*文件类型：</td>
                <td height="50" class="text-1-3">  
                 <c:forEach items="${typeList}" var="st">
                <input type="radio" id="sourcetype" name="sourcetype" value="${st.typeId }" onclick="suffix(this)" />${st.typeName}
                 </c:forEach>
                  <c:forEach items="${typeList}" var="st">
                	<div id="${st.typeId }" style="display: none;">${st.suffix }</div>
                 </c:forEach>
              </td>
              </tr>
              
               <tr>
                <td height="50" align="right" class="text-1-2">*文件版本：</td>
                <td height="50" class="text-1-3">  
                 <c:forEach items="${versionList}" var="sv">
                <input type="radio" id="sourceversion"  name="sourceversion" value="${sv.versionId }" />${sv.VName}
                 </c:forEach>    
              </td>
              </tr>
              
                <tr>
                <td height="50" align="right" class="text-1-2">文件年级：</td>
                <td height="50" class="text-1-3">
                 <select name="sourcegrade" id="sourcegrade">
			    <c:forEach items="${gradeList}" var="sg">
			    	<option value="${sg.njId }">${sg.njMcheng}</option>                    				
                 </c:forEach>
			    </select>
              </td>
              </tr>
              
                              <tr>
                <td height="50" align="right" class="text-1-2">文件科目：</td>
                <td height="50" class="text-1-3">
                <select name="sourcesubject" id="sourcesubject">
			    <c:forEach items="${subjectList}" var="ss">
			    <option value="${ss.subjectId }">${ss.subjectName}</option>                    				
                 </c:forEach>
			    </select>
              </td>
              </tr>
              
              <tr>
                <td height="50" align="right" class="text-1-2">文件说明：</td>
                <td height="50">               
                <textarea id="resourceNotice" name="resourceNotice" cols="68" rows="6" class="text-1-3"></textarea></td>
              </tr>
              <tr>
                <td height="50" align="right" class="text-1-2">文件关键字：</td>
                <td height="50"><input id="keys"  name="keys" type="text" class="text-1-3-shuru" size="70" /></td>
              </tr>
              <tr>
                <td height="50" align="right" class="text-1-2">文件积分：</td>
                <td height="50">
    <select name="needpoint" id="needpoint">	
    			<option value="0">0</option>		   
			    <option value="1">1</option>
			    <option value="2">2</option>
			    <option value="3">3</option>
			    <option value="4">4</option> 
			    <option value="5">5</option>
			    <option value="6">6</option>
			    <option value="7">7</option>
			    <option value="8">8</option>  
			    <option value="9">9</option>
			    <option value="10">10</option>
			    <option value="11">11</option>
			    <option value="12">12</option> 
			    <option value="13">13</option>
			    <option value="14">14</option>
			    <option value="15">15</option>
			    <option value="16">16</option>  
			    <option value="17">17</option>
			    <option value="18">18</option>
			    <option value="19">19</option>
			    <option value="20">20</option> 
			    <option value="21">21</option>
			    <option value="22">22</option>	
			    </select>
</td>
              </tr>
              <tr>
                <td height="50" colspan="2" align="center"><span class="t-sousuo-box1">
                  <input name="Submit2" type="submit" class="t-sousuo-box-an-02" value="提  交" />
                  <input type="reset" class="t-sousuo-box-an-02"  value="取  消" />
                </span></td>
               </tr>
            </table>
            </form>
   		</div>
			<div class="t-neirong-01-y">
				<div class="t-neirong-01-y-biao">&nbsp;注意：</div>
				<div class="t-neirong-01-y-nei">
					<p class="text-1-3">1. 请保证您上传的资料不含有反动政治、黄色、严重暴力的内容。不侵犯其他任何人的合法权益。不含有涉及版权问题的片断。请不要上传您不拥有版权或者未经版权人允许的资料。 <br />
					  2. 对于已经上传到子贵网教育资源栏目的文件，我们会酌情进行处理。对此给您带来的不便，请您理解。                 <br />
					  3.尊重版权，避免引起版权纠纷<br />
					  支持文件格式：
				    Txt、doc、pdf、ppt、rar、exe、chm，视频格式为.FLV</p>
				</div>
		  </div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
		<!--底部 -->
  	
  	<jsp:include page="/source/member_foot.jsp"></jsp:include>
</body>
</html>
