<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/admin/inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

<p class="map">知识：删除爱心榜信息</p>
<s:fielderror/>
<center>

<script>

      var pgo=0;

      function JumpUrl(url){

  	    if(pgo==0){ if(url != null){
  	        	location=url;
  	        }else{
  	        	location='javascript:history.go(-1);';
  	        }
  	      	pgo=1; 
  	     }

  	  }

document.write("<br /><div style='width:450px;padding:0px;border:1px solid #DADADA;'><div style='padding:6px;font-size:12px;border-bottom:1px solid #DADADA;background:#DBEEBD url(<%=path%>/images/wbg.gif)';'><b>操作提示信息</b></div>");

document.write("<div style='height:130px;font-size:10pt;background:#ffffff'><br />");

document.write("亲，您已成功删除爱心榜信息！快去看看都有哪些爱心榜信息吧！");

document.write("<br /><a href='<%=path%>/admin/community/love_listByCondition_success.jsp?opType=0'>如果你的浏览器没反应，请点击这里...</a><br/></div>");

setTimeout('JumpUrl("<%=path%>/admin/community/love_listByCondition_success.jsp?opType=0")',5000);</script>

</center>
</div>

<%@ include file="/admin/inc/foot.jsp"%>