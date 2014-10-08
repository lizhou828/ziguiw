<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  page language="java" import="com.zigui.domain.UserBase" %>
<%@ include file="/comm/common_tag.jsp" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>${source.title}_子贵网</title>
    
    <meta http-equiv="keywords" content="${source.keys}"/>
    <meta http-equiv="description" content="${source.resourceNotice}"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <script type="text/javascript" src="${ctx}/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="${ctx}/js/png-tm.js"></script>
    <script language="javascript">AC_FL_RunContent = 0;</script>
    <script src="${ctx}/edu_resources/js/AC_RunActiveContent.js" language="javascript"></script>
    <script type="text/javascript">
        function download(id,result1){
            if ( result1 == null || result1.length==0 || result1 == "null" || result1 == "") {
                alert("对不起！您尚未登录！");
                down.action = "${ctx}/user/login.jsp";
                down.submit();
                return true;
            } else {
                if (null != id) {
                    down.action="${ctx}/source/downLoad.action?id="+id;
                    down.submit();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        function addRecommend(id){
            if(null!==id){
                addr.action="${ctx}/source/addRecommend.action?id="+id;
                addr.submit();
                return true;
            }else{
                return false;
            }
            return false;
        }
    </script>
    <script type="text/javascript" src="${ctx}/edu_resources/flexpaper/js/flexpaper_flash.js"></script>

    <link href="${ctx}/source/css.css" rel="stylesheet" type="text/css" />
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

    <div class="t-sou-lie">
        <p class="text-1-3">
            您的位置：子贵网&nbsp;&gt;&nbsp;<a href="${ctx}/source/sourceInfo.action">教育资源</a>
            &nbsp;&gt;&nbsp;<a href="${ctx}/source/sourceGet.action?id=${source.subjectId}">${source.title}</a>
        </p>
    </div>
    <div class="t-neirong-01">
        <div class="t-neirong-liebiao-z">

            <p align="center" class="text-1-4" style="line-height: 60px;">${source.title}</p>
            <p align="center" class="text-1-3" style="line-height: 36px;">上传者：${source.member.nickName}   上传时间：
                <fmt:formatDate value="${source.createDate}" pattern="yyyy-MM-dd" />
            </p>
            <p></p>
            <%
                UserBase userBase = (UserBase)session.getAttribute("VALID_USER");
            	String loginMemberId = "";
            	if(userBase!=null){
            		loginMemberId = userBase.getId()+"";
            	}
            %>
            <form action="${ctx}/source/downLoad.action" id="down" method="post">
                <input type="hidden" name="id" id="id" value="${source.subjectId}"/>
            </form>

            <form action="${ctx}/source/source!addRecommend.action" id="addr" name="addr" method="post">
                <input type="hidden" name="id" id="id" value="${source.subjectId}"/>
            </form>





            <%--资源下载 --%>
            <!--
            <a href="javascript:download('${source.subjectId}', '<%=loginMemberId %>' );" class="Download"><span class="sp1">立即下载</span><span class="sp2">${source.resourceSize}</span></a>
           -->
           <%--资源 --%>

            <!--
            <ul class="jj_1">
                <li>资料格式：${source.sourcefileType }</li>
                <li>下载次数：${source.resourceDownnum} </li>
                <li>资源分数：${source.needpoint} </li>
                <li>推荐：<a href="javascript:addRecommend('${source.subjectId}');" >推荐</a></li>

            </ul>
            -->

            <c:choose>
                <c:when test='${source.sourcefileType =="FLV"}'>
                    <script language="javascript">
                        if (AC_FL_RunContent == 0) {
                            alert("This page requires AC_RunActiveContent.js.");
                        } else {
                            AC_FL_RunContent(
                                    'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
                                    'width', '665',
                                    'height', '379',
                                    'src', '${ctx}/edu_resources/js/jcplayer',
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
                                    'FlashVars','videoURL=${ctx}/${source.resourcePath }&autoPlay=false&startPhotoSource=${ctx}/${resource.thumbnail}&backgroundColor1=0x333333&backgroundColor2=0x222222',
                                    'movie', '${ctx}/edu_resources/js/jcplayer',
                                    'salign','TL'
                            ); //end AC code
                        }
                        <!--Settable JCPlayer  params :startPhotoSource,videoURL,loop,autoPlay,scaleMode,volume,bufferTime,backgroundColor1,movieBackgroundColor,backgroundColor2,highlightColor,inkColor,playButton,timeBar,seekBar,volumeButton,fullScreenButton,skin,smoothing,autoHide,autoHideFullScreen,offsetY,offsetYFullScreen,margins,marginsFullScreen -->
                    </script>
                    <noscript>
                        <object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="609" height="379" id="jcplayer" align="TL">
                            <param name="allowScriptAccess" value="sameDomain" />
                            <param name="FlashVars" value="videoURL=${ctx}/${source.resourcePath }&autoPlay=false&backgroundColor1=0x333333&backgroundColor2=0x222222"/>
                            <param name="allowFullScreen" value="true" />
                            <param name="salign" value="TL" />
                            <param name="scale" value="noScale" />
                            <param name="movie" value="${ctx}/edu_resources/js/jcplayer.swf" />
                            <param name="quality" value="high" />
                            <param name="bgcolor" value="#ffffff" />
                            <embed src="${ctx}/edu_resources/js/jcplayer.swf" FlashVars="videoURL=${ctx}/${source.resourcePath }&autoPlay=false&backgroundColor1=0x333333&backgroundColor2=0x222222" quality="high" bgcolor="#ffffff" width="609" height="379" name="jcplayer" align="middle" salign="TL" scale="noScale" allowScriptAccess="sameDomain" allowFullScreen="true" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
                        </object>
                    </noscript>
					
				</c:when>
                <c:when  test='${source.sourcefileType =="RAR"}'>

                </c:when>
                <c:when  test='${source.sourcefileType =="CHM"}'>

                </c:when>
                <c:when  test='${source.sourcefileType =="EXE"}'>

                </c:when>
                <c:otherwise>
                    <a id="viewerPlaceHolder" style="width:665px;height:650px;display:block"></a>
                    <script type="text/javascript">
                        var fp = new FlexPaperViewer (
                                '${ctx}/edu_resources/flexpaper/FlexPaperViewer',
                                'viewerPlaceHolder', { config : {
                                    SwfFile : escape('${ctx}/${source.resourceSwfpath}'),
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
                                    localeChain : 'zh_CN',
                                    height : '650px'
                                }});
                    </script>

                </c:otherwise>
            </c:choose>


            <span class="sm">
                    <b>说明:</b> ${source.resourceNotice}
            </span>




            <!-- Baidu Button BEGIN -->
            <div class="sharepo">


                <div class="s_button">
                    <div class="s_point">
                        <ul>
                            <li><a href="javascript:download('${source.subjectId}', '<%=loginMemberId %>'  );"  ><img src="${ctx}/source/images/Buttons_d.jpg"></a>                    		<a target="_self" href="javascript:addFavorite(window.location, document.title)"><img src="${ctx}/source/images/Buttons_s.jpg"></a> </li>
                            <li><span>(大小:${source.resourceSize}</span><span>所需积分:${source.needpoint})	</span></li>
                        </ul>
                    </div>
                    <div class="${ctx}/source/images/s_button_img">


                    </div>

                </div>


                <div class="bdshare_align bdshare_t bds_tools get-codes-bdshare" id="bdshare">
                    <span class="bds_more">分享到：</span>
                    <a class="bds_qzone" title="分享到QQ空间" href="#"></a>
                    <a class="bds_tsina" title="分享到新浪微博" href="#"></a>
                    <a class="bds_tqq" title="分享到腾讯微博" href="#"></a>
                    <a class="bds_renren" title="分享到人人网" href="#"></a>
                    <a class="shareCount" href="#" title="累计分享0次">0</a>
                </div>
                <script data="type=tools" id="bdshare_js" type="text/javascript" src="http://bdimg.share.baidu.com/static/js/bds_s_v2.js?cdnversion=20120912"></script>
                <script id="bdshell_js" type="text/javascript" src="http://share.baidu.com/static/js/shell_v2.js?cdnversion=16"></script>
                <script type="text/javascript">
                    document.getElementById("bdshell_js").src = "http://share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
                </script>
            </div>
            <!-- Baidu Button END -->


        </div>
        <div class="t-neirong-01-y">
            <div class="source_inf">
                <div class="source_inf_t">
                    文档信息
                </div>
                <div class="source_inf_cont">
                    <ul>
                        <li>上传者：${source.member.nickName}</li>
                        <li>上传时间：${source.createDate}</li>
                        <li>文件大小：${source.resourceSize}</li>
                        <li>所需积分：${source.needpoint}</li>

                        <li>下载次数：${source.resourceDownnum}</li>
                        <li style="text-align:right;padding-right:18px;"><a href="${ctx}/source/addsourceGet.action"><img src="${ctx}/source/images/Buttons_03.jpg"></a></li>
                    </ul>
                </div>
            </div>

            <div class="source_inf">
                <div class="source_inf_t">
                    最新上传
                </div>
                <div class="source_inf_cont">
                    <ul>
                        <s:iterator id="zuixin" value="zuixinList">
                            <li>
                                <a href="<s:if test="#zuixin.netPath!=null"><s:property value="#zuixin.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#zuixin.subjectId" /></s:else>" target="_blank">
                                    <s:if test="%{#zuixin.title!=null&&#zuixin.title.length()>14}">
                                        <s:property value="#zuixin.title.substring(0, 14)+'...'" />
                                    </s:if>
                                    <s:else>
                                        <s:property value="#zuixin.title" />
                                    </s:else>
                                </a>

                            </li>
                        </s:iterator>
                    </ul>
                </div>
            </div>


            <div class="t-neirong-01-y-biao">

                下载排行榜

            </div>
            <div class="t-neirong-01-y-nei">
                <ul>
                    <s:iterator id="remen" value="remenList">
                        <li>
                            <a href="<s:if test="#remen.netPath!=null"><s:property value="#remen.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#remen.subjectId" /></s:else>" target="_blank">
                                <s:if test="%{#remen.title!=null&&#remen.title.length()>14}">
                                    <s:property value="#remen.title.substring(0, 14)+'...'" />
                                </s:if>
                                <s:else>
                                    <s:property value="#remen.title" />
                                </s:else>
                            </a></li>
                    </s:iterator>

                </ul>
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
