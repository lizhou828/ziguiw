<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">新闻：新增新闻</p>
<s:fielderror/>
<center>

<script>

      var pgo=0;

      function JumpUrl(){

        if(pgo==0){ location='javascript:history.go(-1);'; pgo=1; }

      }

document.write("<br /><div style='width:450px;padding:0px;border:1px solid #DADADA;'><div style='padding:6px;font-size:12px;border-bottom:1px solid #DADADA;background:#DBEEBD url(<%=path%>/images/wbg.gif)';'><b>操作提示信息</b></div>");

document.write("<div style='height:130px;font-size:10pt;background:#ffffff'><br />");

document.write("亲，频道转移已经成功");

document.write("<br /><a href='javascript:history.go(-1);'>如果你的浏览器没反应，请点击这里...</a><br/></div>");

setTimeout('JumpUrl()',5000);</script>

</center>

</div>

<%@ include file="../inc/foot.jsp"%>