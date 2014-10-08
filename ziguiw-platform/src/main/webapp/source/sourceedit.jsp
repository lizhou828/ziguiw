<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>

<%@include file="/comm/common_tag.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  
<title>子贵网--资源下载中心</title>

 <meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<link href="${ctx}/source/css.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/changeskin.js"></script>
	<script type="text/javascript" src="${ctx}/js/msg.js"></script>
	<script type="text/javascript" src="${ctx}/js/png-tm.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-plugin/validate/jquery.validate.js"></script>
		<script type="text/javascript">
	$().ready(function() {
		var checked = $("#net").attr("checked");
		$("#netPath").attr("disabled",!checked);
		$("#sourcefile").attr("disabled",checked);
		$("#needpoint").attr("disabled",checked);
		$("#net").bind("change",function(){
			var checked = $(this).attr("checked");
			$("#netPath").attr("disabled",!checked);
			$("#sourcefile").attr("disabled",checked);
			$("#needpoint").attr("disabled",checked);
		});
		
			
		var sourcetype = document.getElementsByName("sourcetype");
		for(var i=0;i<sourcetype.length;i++){
			if(sourcetype[i].checked){
				document.getElementById(sourcetype[i].value).style.display = "block";
			}
		}
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
	var loginMemberId = document.getElementById("loginMemberId").value;
	if(loginMemberId.length == 0 || loginMemberId == "null"){
		alert("您还没登陆!");
		window.location = "${ctx}/login.jsp";
		return false;
	}
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
   		<form id="form1" action="${ctx}/source/sourceUpd.action" method="post" onsubmit="return subform1()" class="form" enctype="multipart/form-data">
					<input type="hidden" name="tid" value="${source.subjectId }" />
					<% String   loginMemberId   =   (String)session.getAttribute("loginMemberId"); %>
			<input type="hidden" name="loginMemberId" id="loginMemberId" value="<%=loginMemberId %>"/>
			<s:token></s:token>
   		     <table width="100%" border="0" cellspacing="0" cellpadding="0">
   		      <tr>
   		      	<td height="50" align="right" class="text-1-2">
   		      		
   		      		<input type="checkbox" id="net" name="net" <c:if test="${!empty source.netPath }"> checked="checked"</c:if> />&nbsp;外部链接：
   		      	</td>
   		      	<td height="50">
                 <input type="text" disabled="disabled" id="netPath"  name="netPath" value="${source.netPath }" type="text" class="text-1-3-shuru" maxlength="100" size="70" />
                 </td>
   		      </tr>
              <tr>
                <td width="14%" height="50" align="right" class="text-1-2">*选择文件：</td>
                <td width="86%" height="50">               
                <input type="file" id="sourcefile"  class="text-1-3" name="sourcefile" style="width:300px; height: 24px""/>
                  <span class="text-1-3">所上传的视频文件大小不能超过1024M </span></td>
              </tr>
              <tr>
                <td height="50" align="right" class="text-1-2">*文件标题：</td>
                <td height="50">
                 <input type="text" id="title"  name="title" type="text" class="text-1-3-shuru" size="70"  value="${source.title }"/></td>
                </tr>
              <tr>
                <td height="50" align="right" class="text-1-2">*文件类型：</td>
                <td height="50" class="text-1-3">  
                 <c:forEach items="${typeList}" var="st">
                 <c:choose>
				          		<c:when test="${st.typeId==source.sourceType.typeId}"> <input type="radio" id="sourcetype" name="sourcetype" value="${st.typeId }" onclick="suffix(this)" checked/>${st.typeName}</c:when>
				          		<c:otherwise><input type="radio" id="sourcetype" name="sourcetype" value="${st.typeId }" onclick="suffix(this)" />${st.typeName}</c:otherwise> 
				          		</c:choose>
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
                 <c:choose>
                 <c:when test="${sv.versionId==source.souceVersion.versionId}"> <input type="radio" id="sourceversion"  name="sourceversion" value="${sv.versionId }" checked/>${sv.VName}</c:when>
				          		<c:otherwise><input type="radio" id="sourceversion"  name="sourceversion" value="${sv.versionId }" />${sv.VName}</c:otherwise> 
				          		</c:choose>
                 </c:forEach>    
              </td>
              </tr>
              
                <tr>
                <td height="50" align="right" class="text-1-2">文件年级：</td>
                <td height="50" class="text-1-3">
                 <select name="sourcegrade" id="sourcegrade">
			    <c:forEach items="${gradeList}" var="sg">
			    <c:choose>
			    <c:when test="${sg.njId==source.souceStugrade.njId}"> <option value="${sg.njId }" selected="selected">${sg.njMcheng}</option></c:when>
				          		<c:otherwise><option value="${sg.njId }">${sg.njMcheng}</option></c:otherwise> 
				          		</c:choose>
			    
			                      				
                 </c:forEach>
			    </select>
              </td>
              </tr>
              
                              <tr>
                <td height="50" align="right" class="text-1-2">文件科目：</td>
                <td height="50" class="text-1-3">
                <select name="sourcesubject" id="sourcesubject">
			    <c:forEach items="${subjectList}" var="ss">		
			    <c:choose>	    
			    			    <c:when test="${ss.subjectId==source.souceSubject.subjectId}"> <option value="${ss.subjectId }" selected="selected">${ss.subjectName}</option></c:when>
				          		<c:otherwise><option value="${ss.subjectId }">${ss.subjectName}</option>  </c:otherwise> 
				          		</c:choose>
			        </c:forEach>
			    </select>
              </td>
              </tr>
              
              <tr>
                <td height="50" align="right" class="text-1-2">文件说明：</td>
                <td height="50">               
                <textarea id="resourceNotice" name="resourceNotice" cols="68" rows="6" class="text-1-3">${source.resourceNotice }</textarea></td>
              </tr>
              <tr>
                <td height="50" align="right" class="text-1-2">文件关键字：</td>
                <td height="50"><input id="keys"  name="keys" type="text" class="text-1-3-shuru" size="70"  value="${source.keys }"/></td>
              </tr>
              <tr>
                <td height="50" align="right" class="text-1-2">文件积分：</td>
                <td height="50">
                <select name="needpoint" id="needpoint">
                <c:forEach var="num" begin="0" end="22"><option value="${num }" <c:if test="${source.needpoint==num}">selected="selected"</c:if>>${num }</option></c:forEach>
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
            <input type="hidden" name="id" value="${id }"/>
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
			<!--底部 消息提示 -->
  		<jsp:include page="/comm/member_foot.jsp"></jsp:include>
</body>
</html>
