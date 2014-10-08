<%@ page import="com.zigui.exception.ErrorCodeEnum" %>
<%@ page import="com.zigui.exception.ErrorCodeMap" %>
<%@ page import="com.zigui.exception.handler.AbstractExceptionHandler" %>
<%@ page import="com.zigui.exception.handler.DefaultExceptionHandler" %>
<%@ page import="com.zigui.utils.LogTool" %>
<%@ page import="org.apache.commons.lang.exception.ExceptionUtils" %>
<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

            //set the map class,this can provide access to errorCodeMap.xml
            errorCode.setErrorCodeMap(ErrorCodeMap.getInstance());     //how to use spring ioc and singleton
            clientErrorMessage = errorCode.getClientExceptionMessage();
            if (handler.getAbstractException().isAppendExceptionMessageToClient()) {
                clientErrorMessage += "[" + handler.getAbstractException().getMessage() + "]";
            }
            redirectUrl = errorCode.getRedirectUrl();
            target = errorCode.getTarget();
        } catch (Exception e) {
            LogTool.getLog().error(e);
        }
    }
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Wap版子贵网——页面错了......</title>
    <link href="../css/style.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<div class=img404><img src="../images/failure.png" /></div>
<div class="f_middle">
    <div class="sorrych">抱歉，页面加载失败了……</div>
    <div class="wearesorry">We're sorry but the page your are looking for is Not Found...</div>
    <div class=content>仔细找过啦，没有成功加载您需要的页面。最可能的原因是：
        <ul>
            <li style="color: red"><%=clientErrorMessage%></li>
            <li>在地址中可能存在键入错误。
            <li>当你点击某个链接时，它可能已过期。
            </li></ul>
        <strong>点击以下链接继续浏览子贵网的其它内容</strong>（<a href="http://www.ziguiw.com/">http://www.ziguiw.com/</a>）：

        <div class=show14>
            <ul>
                <li><a title=返回子贵网首页 href="http://www.ziguiw.com/">返回子贵网首页</a>
                <li><a title=返回上一个页面 href="javascript:history.back(-1)">返回上一页</a></li>
            </ul>
        </div>

    </div>
</div>

</body>
</html>
