<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored = "false"%>
<%@ include file="../comm/common_tag.jsp" %>
<%@ taglib prefix="t" uri="/ttxs-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>子贵网-教学资源列表</title>
<link rel="stylesheet" href="<%=basePath%>css/Public.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath%>css/user/kaoqinchaxun.css" type="text/css"/>
<link rel="stylesheet" href="<%=basePath%>css/skin_0.css" type="text/css" id="skinCss" />
<link rel="stylesheet" href="<%=basePath%>css/user/left.css" type="text/css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"  defer="defer"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript">
	function changeImg(objId,img){
		$("<img/>").attr("src","topic/cloud/images/"+img+".png").appendTo($("#"+objId));
    }
</script>
</head>
<body>
<jsp:include page="/comm/member_top_top.jsp"></jsp:include>
	<div id="mian">
		<jsp:include page="/comm/member_top.jsp"></jsp:include>
	    <div id="cont-1">
		<jsp:include page="/comm/member_left.jsp"></jsp:include>
	  	<div class="right">
	      <div class="bobti">教学资源</div>
	      <form action="teachsource/teacheresource!list.action" id="nextPage" method="post">
	      <div class="xunc_2 font-2">
	      <table border="0" cellpadding="0" cellspacing="0" class="sideright_tab1">
	        <tr bgcolor="#f2f4f6" >
           	 <td width="608" class="td_4">资源列表</td>
            </tr>
	        <c:choose>
               		<c:when test="${empty result || fn:length(result.content)==0}">
               		</c:when>
               		<c:otherwise>
               			<c:forEach items="${result.content}" var="rc" varStatus="vs">
               			<tr class="${vs.count % 2 == 0?'menu_2':'menu_2 menu_4'}">
               				<td>&nbsp;&nbsp;&nbsp;
               					<div class="tb" id="Img${vs.count}">
               					<script>
               						switch('${rc.resourceType}'){
               							case "FLV":changeImg('Img${vs.count}',"movie"); break;
               							case "PPT":changeImg('Img${vs.count}',"ppt"); break;
               							case "TXT":changeImg('Img${vs.count}',"text"); break;
               							case "DOC":changeImg('Img${vs.count}',"doc"); break;
               							case "XLS":changeImg('Img${vs.count}',"xls"); break;
               							case "PDF":changeImg('Img${vs.count}',"pdf"); break;
               							case "DOCX":changeImg('Img${vs.count}',"doc"); break;
               						};
               					</script>
               					</div>
               					<div class="btm">
               					<a href="teachsource/teacheresource!get.action?id=${rc.id}" target="_blank">${rc.title }</a>
               					</div>
               					<div class="btmsj">${rc.createDate}</div>
               				</td></tr>
               			</c:forEach>
               		</c:otherwise>
               	</c:choose>
               	</table>
               	<t:tpage page="${result.page}" formId="nextPage"></t:tpage>
	      </div>
	      </form>
	    </div>
		    <div class="clear"></div>
		  </div>
		</div>
	<jsp:include page="/comm/member_foot.jsp"></jsp:include>
	</body>
</html>
