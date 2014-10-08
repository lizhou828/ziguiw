<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/templateHead.jsp"%>
<%@ page import="com.zigui.utils.ImageUtils"%>

<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="<%=path%>/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
    <div class="mypos fsong"><a href="#">子贵首页</a> &gt; 搜索结果</div>
    <div class="col1 fl w-650">
	    <div class="sea_kuang qh">
	    <form action="<%=path%>/search.action">
		    <h2 class="col-h3 qh-h2 h3">
			    <a <s:if test="type=='news'">class="on"</s:if> target="_self" href="<%=path%>/search.action?keyWords=${keyWords}&type=news">新闻</a>
			    <a <s:if test="type=='knowledge'">class="on"</s:if> target="_self" href="<%=path%>/search.action?keyWords=${keyWords}&type=knowledge">知识</a>
			    <a <s:if test="type=='user'">class="on"</s:if> target="_self" href="<%=path%>/search.action?keyWords=${keyWords}&type=user">空间</a>
	    	</h2>
			 <p><input type="text" id="" value="" name="keyWords" class="inp_txt">
			 <input type="hidden" id="" value="${type}" name="type" class="inp_txt">
			<input type="submit" id="" value="搜索" name="" class="btn2"></p>
		</form>
		 </div>   
		 
		 
		 <div class="sea_result newList03">
		 
		 <s:if test="type=='news'">	
		 <s:iterator value="searchResult.list" var="record">
		 	<dl>
			 <dt> <a href="<%=path%>/news/detail/<s:property value="#record.id"/>" target="_blank"><s:property value="#record.title" escape="false"/></a></dt>
			 
			 <dd><p><s:property value="#record.content" escape="false"/></p></dd>
			 </dl>
		 </s:iterator>
		 </s:if>
		 
		 <s:if test="type=='knowledge'">	
		 <s:iterator value="searchResult.list" var="record">
		 	<dl>
			 <dt> <a href="<%=path%>/study/detail/<s:property value="#record.id"/>" target="_blank"><s:property value="#record.title" escape="false"/></a></dt>
			 
			 <dd><p><s:property value="#record.content" escape="false"/></p></dd>
			 </dl>
		 </s:iterator>
		 </s:if>
		 
		 <s:if test="type=='user'">	
		 <s:iterator value="searchResult.list" var="record">
		 	<dl class="uesr_space">
			 <dt><a href="<%=path%>/myhome/<s:property value="#record.rawNickName"/>" class="a-img1 l"><img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#record.portrait"), path + "/images/head.jpg", 70)%>" /></a></dt>
			 <dd class="f14 fb"><span><a href="<%=path%>/myhome/<s:property value="#record.rawNickName"/>" target="_blank"><s:property value="#record.nick_name" escape="false"/></a></span></dd>
			 <dd><s:property value="#record.signature" escape="false"/></dd>
			 </dl>
		 </s:iterator>	
		 </s:if>
		 
		 </div>
		 
		 <s:if test="searchResult.pageString != null && searchResult.pageString != ''">
			<div class="pagenum">
				<s:property value="searchResult.pageString" escape="false"/>
			</div>
		</s:if>
		 
		 
		 
    </div>
    <div class="col2 fl ml10 w310">
    	
       <a href="#"><img src="<%=path%>/images/ad.jpg"/></a> 
        
    </div>        
    
</div>   

<%@ include file="../inc/templateFoot.jsp"%>