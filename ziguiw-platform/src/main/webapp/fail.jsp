        <%@ page import="com.zigui.exception.ErrorCodeEnum" %>
        <%@ page import="com.zigui.exception.ErrorCodeMap" %>
        <%@ page import="com.zigui.exception.handler.AbstractExceptionHandler" %>
        <%@ page import="com.zigui.exception.handler.DefaultExceptionHandler" %>
        <%@ page import="com.zigui.utils.LogTool" %>
        <%@ page import="org.apache.commons.lang.exception.ExceptionUtils" %>
        <%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();

    String clientErrorMessage = "发生错误";
    String redirectUrl = "/index";
    String target = "_self";//_self,_blank,_parent,_top
    if (exception != null) {
        try {
            AbstractExceptionHandler handler = new DefaultExceptionHandler(exception);//how to use spring ioc???
            handler.handleException();
            ErrorCodeEnum errorCode = handler.getAbstractException().getErrorCode();
            if (errorCode != null) {
                //set the map class,this can provide access to errorCodeMap.xml
                errorCode.setErrorCodeMap(ErrorCodeMap.getInstance());     //how to use spring ioc and singleton
                clientErrorMessage = errorCode.getClientExceptionMessage();
                if (handler.getAbstractException().isAppendExceptionMessageToClient()) {
                    clientErrorMessage += "[" + handler.getAbstractException().getMessage() + "]";
                }
                redirectUrl = errorCode.getRedirectUrl();
                target = errorCode.getTarget();
            }
        } catch (Exception e) {
            LogTool.getLog().error(e.getMessage(), e);
        }
    }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>页面错误了</title>
<style type="text/css">
<!--
BODY { PADDING-RIGHT: 0px; PADDING-LEFT: 35px; PADDING-BOTTOM: 0px; MARGIN: 0px; FONT: 12px Arial, Helvetica, sans-serif; COLOR: #333; PADDING-TOP: 35px}
A { COLOR: #007ab7; TEXT-DECORATION: none}
A:hover {COLOR: #007ab7; TEXT-DECORATION: none}
A:hover {COLOR: #de1d6a}
.hidehr {DISPLAY: none}
.show12 {PADDING-RIGHT: 0px; DISPLAY: block; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 5px 0px; PADDING-TOP: 0px}
.show13 {PADDING-RIGHT: 0px; DISPLAY: block; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 5px 0px; PADDING-TOP: 0px}
.show12 A { BORDER-RIGHT: #bfdeed 1px solid; PADDING-RIGHT: 6px; BORDER-TOP: #bfdeed 1px solid; DISPLAY: inline-block; PADDING-LEFT: 6px; BACKGROUND: #d8ebf4; PADDING-BOTTOM: 2px; OVERFLOW: hidden; BORDER-LEFT: #bfdeed 1px solid; LINE-HEIGHT: 17px; PADDING-TOP: 2px; BORDER-BOTTOM: #bfdeed 1px solid; HEIGHT: 16px}
.show13 A { BORDER-RIGHT: #bfdeed 1px solid; PADDING-RIGHT: 6px; BORDER-TOP: #bfdeed 1px solid; DISPLAY: inline-block; PADDING-LEFT: 6px; BACKGROUND: #d8ebf4; PADDING-BOTTOM: 2px; OVERFLOW: hidden; BORDER-LEFT: #bfdeed 1px solid; LINE-HEIGHT: 17px; PADDING-TOP: 2px; BORDER-BOTTOM: #bfdeed 1px solid; HEIGHT: 16px}
.show12 A:hover { BORDER-RIGHT: #ea5e96 1px solid; BORDER-TOP: #ea5e96 1px solid; BACKGROUND: #fce8f0; BORDER-LEFT: #ea5e96 1px solid; COLOR: #de1d6a; BORDER-BOTTOM: #ea5e96 1px solid; TEXT-DECORATION: none}
.show13 A:hover { BORDER-RIGHT: #ea5e96 1px solid; BORDER-TOP: #ea5e96 1px solid; BACKGROUND: #fce8f0; BORDER-LEFT: #ea5e96 1px solid; COLOR: #de1d6a; BORDER-BOTTOM: #ea5e96 1px solid; TEXT-DECORATION: none}
.show12 A { FONT-WEIGHT: normal; FONT-SIZE: 12px}
.show13 A { BORDER-RIGHT: #ed268c 1px solid; BORDER-TOP: #ed268c 1px solid; BACKGROUND: #dd137b; BORDER-LEFT: #ed268c 1px solid; COLOR: #fff; BORDER-BOTTOM: #ed268c 1px solid}
.img404 {
 PADDING-RIGHT: 0px;
 PADDING-LEFT: 0px;
 BACKGROUND: url(<%=path%>/images/fail.gif) no-repeat left top;
 FLOAT: left;
 PADDING-BOTTOM: 0px;
 MARGIN: 0px;
 WIDTH: 80px;
 PADDING-TOP: 0px;
 POSITION: relative;
 HEIGHT: 90px;
 background-image: url(<%=path%>/images/fail.gif);
}
H2 {PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 16px; FLOAT: left; PADDING-BOTTOM: 25px; MARGIN: 0px; WIDTH: 80%; LINE-HEIGHT: 0; PADDING-TOP: 25px; BORDER-BOTTOM: #ccc 1px solid; POSITION: relative}
H3.wearesorry { PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-WEIGHT: normal; FONT-SIZE: 10px; LEFT: 117px; PADDING-BOTTOM: 0px; MARGIN: 0px; COLOR: #ccc; LINE-HEIGHT: 10px; PADDING-TOP: 0px; POSITION: absolute; TOP: 70px}
.content { CLEAR: both; PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 13px; LEFT: 80px; FLOAT: left; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 80%; LINE-HEIGHT: 19px; PADDING-TOP: 0px; POSITION: relative; TOP: -30px}
.content UL {PADDING-RIGHT: 35px; PADDING-LEFT: 35px; PADDING-BOTTOM: 20px; MARGIN: 0px; PADDING-TOP: 10px}
.show12 UL {PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px 0px 0px 20px; PADDING-TOP: 0px; LIST-STYLE-TYPE: none}
.show14 UL LI { MARGIN-BOTTOM: 5px}
-->
</style>
</head>
<body>
<H1 class=hidehr>当前在子贵网上（www.ziguiw.com）不能加载您需要页面。</H1>
<DIV class=img404>　</DIV>
<H2>抱歉，页面加载失败了……</H2>
<H3 class=wearesorry>We're sorry but the page your are looking for is Not Found...</H3>
<DIV class=content>仔细找过啦，没有成功加载您需要的页面。最可能的原因是：
  <UL>
  <LI style="color: red"><%=clientErrorMessage%></LI>
  <LI>在地址中可能存在键入错误。
  <LI>当你点击某个链接时，它可能已过期。
</LI></UL>
<STRONG>点击以下链接继续浏览子贵网的其它内容</STRONG>（<a href="http://www.ziguiw.com/">http://www.ziguiw.com/</a>）：

    <DIV class=show14>
        <UL>
             <LI><A title=返回子贵网首页 href="http://www.ziguiw.com/">返回子贵网首页</A>
             <LI><A title=返回上一个页面 href="javascript:history.back(-1)">返回上一页</A></LI>
        </UL>
    </DIV>

    <div style="display: none;">
        <%
                String stack=null;
                if (exception != null) {
                    stack = ExceptionUtils.getStackTrace(exception);
                }%>
        <%=stack%>
        </div>
</DIV>
</body>
</html>
<%
//    redirect function need view layer develop
%>