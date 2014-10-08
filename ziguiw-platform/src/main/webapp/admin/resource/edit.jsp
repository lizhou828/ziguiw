<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/comm/common_tag.jsp" %>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>


<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="${ctx}/js/png-tm.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-plugin/validate/jquery.validate.js"></script>
    <script type="text/javascript">
		$(document).ready(function(){
			$("#btnaudit").click(function(){
				var checkSign = document.getElementsByName("checkSign");
				for(var i=0;i<checkSign.length;i++){
					if(checkSign[i].checked){
						break;
					}
					if(i==(checkSign.length-1)){
						alert("请选择是否同意！");
						return false;
					}
				}
				$("#form1").attr("action","${pageContext.request.contextPath}/admin/resource/sourceAudit.action");
				$("#form1").submit();
			});
		});
		
		function back(){
			window.history.go(-1);
		}
		
	</script>
<div class="right">

<%@ include file="../inc/navigation.jsp"%>
<body>
		<div class="content-box">
			<div class="box-header"><h2>资源审核</h2></div>
			<form id="form1" action="" method="post" class="form" enctype="multipart/form-data">
			<input type="hidden" name="subjectId" value="${ccczgsouce.subjectId}" />													
			<div class="shbox-body">
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tbody><tr>
			    <td width="10%"><span class="form-label fl-space2">资源标题: <span class="required">*</span></span></td>
			    <td width="90%">
			    <span class="form-field">
			      <input type="text" readonly="readonly" value="${ccczgsouce.title}" name="title" class="text fl" id="title">
			    </span>
			    </td>
			  </tr>
			  <tr>
			    <td width="10%"><span class="form-label fl-space2">上传时间: <span class="required">*</span></span></td>
			    <td width="90%">
			    <span class="form-field">
				  <input type="text" value="${ccczgsouce.createDate}" readonly="readonly" name="createdate" class="text fl" id="createdate">
			    </span>
			    </td>
			  </tr>	
			  <tr>
			    <td width="10%"><span class="form-label fl-space2">资源类别: <span class="required">*</span></span></td>
			    <td width="90%">
			    <span class="form-field">
				   <input type="text" id="createdate" class="text fl" name="createdate" readonly="readonly" 
				  <c:if test="${ccczgsouce.sourceType.typeName == null}"></c:if>
				  <c:if test="${ccczgsouce.sourceType.typeName != null}">value="${ccczgsouce.sourceType.typeName}"</c:if>
			    </span>
			    </td>
			  </tr>
			  <tr>
			    <td width="10%"><span class="form-label fl-space2">资源版本: <span class="required">*</span></span></td>
			    <td width="90%">
			    <span class="form-field">
				   <input type="text" id="createdate" class="text fl" name="createdate" readonly="readonly" 
				  <c:if test="${ccczgsouce.souceVersion.VName == null}"></c:if>
				  <c:if test="${ccczgsouce.souceVersion.VName != null}">value="${ccczgsouce.souceVersion.VName}"</c:if>/>
			    </span>
			    </td>
			  </tr>
			   <tr>
			    <td valign="top"><span class="form-label fl-space2">资源说明: </span></td>
			    <td>
			    <span class="form-field">
			      <textarea id="resourceNotice" class="form-textarea" cols="100" rows="6" name="resourceNotice" readonly="readonly">${ccczgsouce.resourceNotice}</textarea>
			    </span>
			    </td>
			  </tr>
			  <tr>
			    <td width="10%"><span class="form-label fl-space2">是否同意: </span></td>
			    <td width="90%">
			    <span class="form-field">
				 <input type="radio" id="check1" name="checkSign" value="1"/>同意&nbsp;&nbsp;
				  <input type="radio" id="check2" name="checkSign" value="2"/>不同意&nbsp;&nbsp;
			    </span>
			    </td>
			  </tr>
			  <tr>
			    <td>&nbsp;</td>
			    <td>
			    	<input type="button" value="提  交" class="submit" id="btnaudit" name="btnaudit">
					<input type="button" value="返  回" class="submit" id="btnback" onclick="back()">
				</td>
			  </tr>
			</tbody></table>	
			</div>
			</form>
		</div>
</body>
</html>