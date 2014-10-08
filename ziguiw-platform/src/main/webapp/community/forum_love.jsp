<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/templateHead.jsp"%>
<!--header E -->

<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="<%=path%>/images/adv01.jpg" alt=""/></a>
	</div>		
	<div class="map mt10 mb10"><label><img alt="" src="<%=path%>/images/bbs_map_bg _h.png" /></label><a href="#">子贵首页</a> &gt; <a href="<%=path%>/community/index">互动社区</a> &gt;爱心站</div>
	<div class="bbs_info_tlq mb10 no_dashed">
		<h3><b class="on">爱心站</b><label>
			<s:action var="gonggaoForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
		      <s:param name="query.isnew" value="1"></s:param>
		      <s:param name="countPerPage" value="3"></s:param>
		      <s:param name="currentPage" value="1"></s:param>
		      <s:param name="query.state" value="1"></s:param>
		      <s:param name="query.regionId" value="56"></s:param>
		      <s:param name="orderField" value="createTime"></s:param>
		    </s:action>
		    <s:iterator value="#gonggaoForums.forums.list" var="forum">
		        <a href="<%=path%>/community/aixin/detail/<s:property value="#forum.id"/>" target="_blank"><s:property value="#forum.title"/><i><s:property value="#forum.formatedStartTime.subString(4)"/></i></a>
		    </s:iterator> 
		</label></h3>
	 	<ul class="aixz">
	 	<li><a target="_blank" href="<%=path%>/community/aixin/list" class="on">爱心活动</a></li>
	 	<li><a target="_blank" href="<%=path%>/forum/forumList.action?boardId=6">救助的孩子们</a></li>
	 	<li><a target="_blank" href="<%=path%>/page.action?templateName=createForum.ftl&boardId=1">我要捐助</a></li>
	 	<li><a target="_blank" href="<%=path%>/page.action?templateName=createForum.ftl&boardId=1">申请救助</a></li>
	 	</ul>
	</div>
	<!--ad01 E -->
	<div class="col1 fl  bbs_list_tl">
		<div class="h3titc">
			<h3 class="pr fyahei">爱心活动</h3>		
		</div>	
			
    	<div class=" borda">
   		  <s:action var="loveForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
		      <s:param name="query.boardid" value="1"></s:param>
		      <s:param name="query.isnew" value="1"></s:param>
		      <s:param name="query.istop" value="1"></s:param>
		      <s:param name="countPerPage" value="5"></s:param>
		      <s:param name="currentPage" value="1"></s:param>
		      <s:param name="query.state" value="1"></s:param>
	        </s:action>
	        <s:iterator value="#loveForums.forums.list" var="forum">
	        	<div class="bbs_list_axhd">    		
	    		<dl>
	    		<dd class="l"><a href="#" target="_blank" class="a-img3"><img alt="" src="<s:property value="#forum.firstImage"/>"></a></dd>
	    		<dt><a href="<%=path%>/community/aixin/detail/<s:property value="#forum.id"/>" target="_blank"><s:property value="#forum.title"/></a></dt>
	    		<dd>发起人：<a href="<%=path%>/myhome/<s:property value="#forum.creatorNick"/>" target="_blank"><s:property value="#forum.creatorNick"/></a>        发起时间：<s:property value="#forum.formatedStartTime"/></dd>
	    		<dd><p><s:property value="#forum.autoDescription"/></p></dd>
	    		<dd class="a-r"><a href="<%=path%>/community/aixin/detail/<s:property value="#forum.id"/>#ai" target="_blank"><img alt="" src="<%=path%>/images/community_love_wyjz.gif" ></a><a href="<%=path%>/community/aixin/detail/<s:property value="#forum.id"/>" target="_blank"><img alt="" src="<%=path%>/images/community_love_ckxq.gif"></a></dd>
	    		</dl> 
	    		</div>	
	        </s:iterator>  
            <s:if test="#loveForums.forums.pageString != null && #loveForums.forums.pageString != ''">
			<s:property value="#loveForums.forums.pageString" escape="false"/>
			</s:if>	
    	</div>
    	
    </div>
    <div class="col2 fl ml10">
    	<div class="hottj borda pr pt40">
        	<div class="h3tita pa">
            	<h3 class="fyahei">爱心公示榜</h3>
            </div>
            <div class="">
	            <ul class="axz_jub">
	            <s:action var="loves" name="love_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="10"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#loves.pagedLoves.list" var="love">
		          <li><a href="href="<%=path%>/myhome/<s:property value="#love.donateUser.nickName"/>" target="_blank"" class=" red"><s:property value="#love.donateUser.nickName"/></a> 资助 <s:property value="#love.toNick"/><b><s:property value="#love.info"/></b><i><s:property value="#love.formatedStartTime"/></i></li>
		        </s:iterator>
	            </ul>  
            </div>
        </div>
        <div class="ad_col2_1 borda mt10"><a href="#"><img src="<%=path%>/images/community_love_25.jpg" ></a></div>
        <div class="mt10">
        	<div class="h3titb">
            	<h3 class="fyahei">我要求助</h3>
            </div>
            <div class="borda">
	            <ul class="newsli">
	            <s:action var="hotForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="1"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="query.istop" value="1"></s:param>
			      <s:param name="countPerPage" value="10"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="orderField" value="clickCount"></s:param>
		        </s:action>
		        <s:iterator value="#hotForums.forums.list" var="forum">
		            <li><a href="<%=path%>/community/aixin/detail/<s:property value="#forum.id"/>"><s:property value="#forum.title"/></a></li>
		        </s:iterator>
	            </ul>            
            </div>
        </div>
    </div>        
    <%@ include file="../inc/templateLink.jsp"%>
    </div>     
<%@ include file="../inc/templateFoot.jsp"%>