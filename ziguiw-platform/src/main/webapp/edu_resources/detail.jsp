<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../comm/common_tag.jsp" %>
<%@ taglib prefix="t" uri="/ttxs-tags"%>
<%   
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>"/> 
    
    <title>子贵网-教学资源下载</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="stylesheet" href="<%=basePath%>css/Public.css" type="text/css"/>
	<link rel="stylesheet" href="<%=basePath%>css/user/kaoqinchaxun.css" type="text/css"/>
	<link rel="stylesheet" href="<%=basePath%>css/skin_0.css" type="text/css" id="skinCss" />
	<link rel="stylesheet" href="<%=basePath%>css/user/left.css" type="text/css"/>
	<link rel="stylesheet" href="<%=basePath%>edu_resources/css/zhuanti2.css"type="text/css"/>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/png-tm.js"></script>
	<script language="javascript">AC_FL_RunContent = 0;</script>
	<script src="<%=basePath%>edu_resources/js/AC_RunActiveContent.js" language="javascript"></script>
 	<script type="text/javascript">
		function download(id){
			if(null!==id){
			    alert("<%=basePath%>teachsource/teacheresource!downLoad.action?id="+id);
				down.action="<%=basePath%>teachsource/teacheresource!downLoad.action?id="+id;
				down.submit();
				return true;
			}else{
				return false;
			}
			return false;
		}
	</script>
	<script type="text/javascript" src="<%=basePath%>edu_resources/flexpaper/js/flexpaper_flash.js"></script>
  </head>
  <body>
<jsp:include page="/comm/member_top_top.jsp"></jsp:include>
	<div id="mian">
		<jsp:include page="/comm/member_top.jsp"></jsp:include>
	    <div id="cont-1">
		<jsp:include page="/comm/member_left.jsp"></jsp:include>
		<form action="teachsource/teacheresource!downLoad.action" id="down" method="post">
			<input type="hidden" name="id" id="id" value="${resource.id}"/>
		</form>
	  	<div class="right">
	      <div class="bobti">教学资源</div>
	      <div class="cont_1">
        	<h4 class="bt_2">${resource.title}</h4>
            <div class="about_zl">
            	<div class="tb_1" id="Imgfirst">
                	<script>
                		var base='<%=basePath%>';
                		switch('${resource.resourceType}'){
                			case "FLV":switchimg("movie"); break;
                			case "PPT":switchimg("ppt"); break;
                			case "TXT":switchimg("text"); break;
                			case "DOC":switchimg("doc"); break;
                			case "XLS":switchimg("xls"); break;
                			case "PDF":switchimg("pdf"); break;
                			case "DOCX":switchimg("doc"); break;
                	};
                	function switchimg(img){
                		
						$("<img/>").attr("src",base+"topic/cloud/images/"+img+".png").appendTo($("#Imgfirst"));
                	};
               	 </script>
            	</div>
                <span class="sm">
                <b>说明</b><br /> ${resource.resourceNotice}</span>
                <%--资源下载 --%>
                <a href="javascript:download('${resource.id}');" class="Download"><span class="sp1">立即下载</span><span class="sp2">${resource.resourceSize}</span></a>
               	<%--资源 --%>
                <ul class="jj_1">
                	<li>资料格式：${resource.resourceType }</li>
                    <li>下载次数：${resource.resourceDownnum} </li>
                    <li>上传时间：<fmt:formatDate type="both" value="${resource.createDate}"/></li>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="line_1"></div>
            <div class="neir">
            	<%--视频播放区域 --%>
            	<s:if test='#request.resource.resourceType == "FLV"'>
				<script language="javascript">
					if (AC_FL_RunContent == 0) {
						alert("This page requires AC_RunActiveContent.js.");
					} else {
						AC_FL_RunContent(
							'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
							'width', '665',
							'height', '379',
							'src', '<%=basePath%>edu_resources/js/jcplayer',
							'quality', 'high',
							'pluginspage', 'http://www.macromedia.com/go/getflashplayer',
							'align', 'middle',
							'play', 'true',
							'loop', 'true',
							'scale', 'noScale',
							'wmode', 'window',
							'devicefont', 'false',
							'id', 'jcplayer',
							'bgcolor', '#ffffff',
							'name', 'jcplayer',
							'menu', 'true',
							'allowFullScreen', 'true',
							'allowScriptAccess','sameDomain',
							'FlashVars','videoURL=<%=basePath%>${resource.resourcePath }&autoPlay=false&startPhotoSource=<%=basePath%>${resource.thumbnail}&backgroundColor1=0x333333&backgroundColor2=0x222222',
							'movie', '<%=basePath%>edu_resources/js/jcplayer',
							'salign','TL'
							); //end AC code
					}
				<!--Settable JCPlayer  params :startPhotoSource,videoURL,loop,autoPlay,scaleMode,volume,bufferTime,backgroundColor1,movieBackgroundColor,backgroundColor2,highlightColor,inkColor,playButton,timeBar,seekBar,volumeButton,fullScreenButton,skin,smoothing,autoHide,autoHideFullScreen,offsetY,offsetYFullScreen,margins,marginsFullScreen -->
				</script>
				<noscript>
					<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="609" height="379" id="jcplayer" align="TL">
					<param name="allowScriptAccess" value="sameDomain" />
					<param name="FlashVars" value="videoURL=<%=basePath%>${resource.resourcePath }&autoPlay=false&startPhotoSource=<%=basePath%>${resource.thumbnail}&backgroundColor1=0x333333&backgroundColor2=0x222222"/>
					<param name="allowFullScreen" value="true" />
				    <param name="salign" value="TL" />
				    <param name="scale" value="noScale" />
					<param name="movie" value="<%=basePath%>edu_resources/js/jcplayer.swf" />
				    <param name="quality" value="high" />
				    <param name="bgcolor" value="#ffffff" />	
				    <embed src="<%=basePath%>edu_resources/js/jcplayer.swf" FlashVars="videoURL=<%=basePath%>${resource.resourcePath }&autoPlay=false&startPhotoSource=<%=basePath%>${resource.thumbnail }&backgroundColor1=0x333333&backgroundColor2=0x222222" quality="high" bgcolor="#ffffff" width="609" height="379" name="jcplayer" align="middle" salign="TL" scale="noScale" allowScriptAccess="sameDomain" allowFullScreen="true" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
					</object>
				</noscript>
				</s:if>
				<s:else>
	        	<a id="viewerPlaceHolder" style="width:880px;height:800px;display:block"></a>
		        <script type="text/javascript">
		     		var fp = new FlexPaperViewer(	
							 '<%=basePath%>edu_resources/flexpaper/FlexPaperViewer',
							 'viewerPlaceHolder', { config : {
							 SwfFile : escape('<%=basePath%>${resource.resourceSwfpath}'),
							 Scale : 0.6, 
							 ZoomTransition : 'easeOut',
							 ZoomTime : 0.5,
							 ZoomInterval : 0.2,
							 FitPageOnLoad : true,
							 FitWidthOnLoad : false,
							 PrintEnabled : false,
							 FullScreenAsMaxWindow : false,
							 ProgressiveLoading : true,
							 MinZoomSize : 0.2,
							 MaxZoomSize : 5,
							 SearchMatchAll : false,
							 InitViewMode : 'Portrait',
							 ViewModeToolsVisible : true,
							 ZoomToolsVisible : true,
							 NavToolsVisible : true,
							 CursorToolsVisible : true,
							 SearchToolsVisible : true,
	  						 localeChain: 'zh_CN'
							 }});
		        </script>
				</s:else>
            </div>	
            <div class="line_1"></div>
        </div>
	    </div>
		    <div class="clear"></div>
		  </div>
		</div>
	<jsp:include page="/comm/member_foot.jsp"></jsp:include>
	</body>
</html>
