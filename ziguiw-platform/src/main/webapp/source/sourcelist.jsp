<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/comm/common_tag.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  
    
    <title>子贵网--管理员中心</title>
    
<link rel="stylesheet" href="${ctx}/css/Public.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/css/user/kaoqinchaxun.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/css/skin_0.css" type="text/css" id="skinCss" />
<link rel="stylesheet" href="${ctx}/css/user/left.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"  defer="defer"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript">
	function changeImg(objId,img){
		$("<img/>").attr("src","topic/cloud/images/"+img+".png").appendTo($("#"+objId));
    }
</script>
	<script type="text/javascript">
	//修改事件
	function updclick(id){
		
		nextPage.action = "${ctx}/source/sourceUpGo.action?id="+id;
		nextPage.submit();
		
	}
	
	//删除事件
	function delclick(id){
		if(confirm('确定删除?')){
			
			nextPage.action = "${ctx}/source/sourceDel.action?id="+id;
			nextPage.submit();
		}
	}
	</script>
</head>

<body><jsp:include page="/comm/member_top_top.jsp"></jsp:include>
	<div id="mian">
		<jsp:include page="/comm/member_top.jsp"></jsp:include>
	    <div id="cont-1">
		<jsp:include page="/comm/member_left.jsp"></jsp:include>
	  	<div class="right">
	      <div class="bobti">教学资源</div>
	       <form action="${ctx}/source/sourceList.action" id="nextPage" method="post">	    
	       <s:token></s:token> 
	      <div class="xunc_2 font-2">
	      <table border="0" cellpadding="0" cellspacing="0" class="sideright_tab1">
	        <tr bgcolor="#f2f4f6" >
           	 <td width="100%" class="td_4">我的资源列表</td>
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
               						switch('${rc.sourcefileType}'){
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
               					<div class="btm" style="width: 62%">
               					<a href="<c:if test="${rc.netPath!=null }">${rc.netPath }</c:if><c:if test="${rc.netPath==null }">${ctx}/source/sourceGet.action?id=${rc.subjectId}</c:if>" target="_blank">${rc.title }</a>
&nbsp;&nbsp; <font style="width:50px; color:gray;">积分:${rc.needpoint} </font>         					
</div>
               					<div class="btmsj" style="width: 30%">
               					<fmt:formatDate value="${rc.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
               					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               					<img id="upd" style="cursor:pointer" onclick="updclick('${rc.subjectId }');" src="${ctx}/images/ico_edit_16.png" alt="" title="修改" />
									<img id="del" style="cursor:pointer" onclick="delclick('${rc.subjectId }');" src="${ctx}/images/ico_delete_16.png"  alt="" title="删除" />
								</div>
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
