<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ include file="../inc/templateHead.jsp"%>
<!--header E -->

<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="<%=path%>/images/adv01.jpg" alt=""/></a>
	</div>
	    <s:action var="board" name="board_getById" namespace="/forum" executeResult="false" ignoreContextParams="false">
	      <s:param name="boardId" value="#parameters.boardId[0]"></s:param>
        </s:action>
        <%String pbname = null; String pbid = null;%>
        <s:if test='#board.board.id<=5'>
           <s:if test='#board.board.id==1'>
             <% pbname = "aixin";%>
             <% pbid = "1";%> 
           </s:if>
           <s:elseif test="#board.board.id==2">
             <% pbname = "age";%>
             <% pbid = "2";%> 
           </s:elseif>
           <s:elseif test="#board.board.id==3">
             <% pbname = "city";%>
             <% pbid = "3";%> 
           </s:elseif>
           <s:elseif test="#board.board.id==4">
             <% pbname = "jiaoliu";%>
             <% pbid = "4";%> 
           </s:elseif>
           <s:elseif test="#board.board.id==5">
             <% pbname = "zhuanjia";%>
             <% pbid = "5";%> 
           </s:elseif>
        
        </s:if>     
        <s:else>
           <s:if test='#board.board.parentBoard.id==1'>
             <% pbname = "aixin";%>
             <% pbid = "1";%> 
           </s:if>
           <s:elseif test="#board.board.parentBoard.id==2">
             <% pbname = "age";%>
             <% pbid = "2";%> 
           </s:elseif>
           <s:elseif test="#board.board.parentBoard.id==3">
             <% pbname = "city";%>
             <% pbid = "3";%> 
           </s:elseif>
           <s:elseif test="#board.board.parentBoard.id==4">
             <% pbname = "jiaoliu";%>
             <% pbid = "4";%> 
           </s:elseif>
           <s:elseif test="#board.board.parentBoard.id==5">
             <% pbname = "zhuanjia";%>
             <% pbid = "5";%> 
           </s:elseif>
           <s:else>
             <% pbname = "";%>
             <% pbid = "2";%> 
           </s:else>
        </s:else>
        <!-- subboard -->
        <s:if test='#board.board.id<=5'>
	        <div class="bbs_info_tlq">
			<h3><b class="on"><s:property value="#board.board.boardname"/></b>
			<label>
			<s:action var="gonggaoForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="3"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="56"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		    </s:action>
		    <s:iterator value="#gonggaoForums.forums.list" var="forum">
		        <a href="<%=path%>/community/<%=pbname%>/detail/<s:property value="#forum.id"/>" target="_blank"><s:property value="#forum.title"/><i><s:property value="#forum.formatedStartTime.subString(4)"/></i></a>
		    </s:iterator> 
			</label>
			</h3>
		 	<ul>
	 			<s:action var="subBoards" name="board_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
						      <s:param name="query.parentBoardId" value='#parameters.boardId[0]'></s:param>
						      <s:param name="query.state" value="1"></s:param>
						      <s:param name="countPerPage" value="100"></s:param>
				</s:action>
		        <s:iterator value="#subBoards.boards.list" var="subboard">
		           <li><a href="<%=path%>/forum/forumList.action?boardId=<s:property value="#subboard.id"/>" target="_blank"><s:property value="#subboard.boardname"/></a></li>
		        </s:iterator>
		 	</ul>
		    </div>	
        </s:if>
        
	<div class="map mt10 mb10"><label><img alt="" src="<%=path%>/images/bbs_map_bg _h.png" /></label><a href="<%=path%>/index">子贵首页</a> &gt; <a href="<%=path%>/community/index">互动社区</a> &gt;<s:property value="#board.board.boardname"/></div>

	<!--ad01 E -->
	<div class="col1 fl  bbs_list_tl">
		<div class="h3titc">
			<h3 class="pr fyahei"><s:property value="#board.board.boardname"/><span>主题：<b class="red_f63"><s:property value="#board.board.mainpostnum"/></b></span></h3>
			<ul>
			<li class="bordno">
	
			<li><a href="<%=path%>/page.action?templateName=createForum.ftl&boardId=<s:property value="#parameters.boardId[0]"/>"><img alt="" src="<%=path%>/images/bbs_list_tlq_fq.gif"></a></li>
			</ul>
		</div>		
    	<div class=" borda">    
    		<div class="bbs_list_tj">    		
    		<table cellpadding="0" class="table">
    		<thead>
				<tr>
					<td style="width:420px;" class="f14 fb bbs_list_zt">置顶主题</td>
					<td style="width:80px;">作者</td>
					<td style="width:60px;">回复/查看</td>
					<td>最后发表</td>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="#gonggaoForums.forums.list" var="forum">
				<tr>
					<td class="notice"><span>公告：<a href="<%=path%>/community/<%=pbname%>/detail/<s:property value="#forum.id"/>" target="_blank"><s:property value="#forum.title"/></a></span></td>
					<td><a href="<%=path%>/myhome/<s:property value="#forum.creatorNick"/>" target="_blank" class="	block"><s:property value="#forum.creatorNick"/></a><s:property value="#forum.formatedStartTime"/></td>
					<td><s:property value="#forum.renum"/>/<s:property value="#forum.clickCount"/></td>
					<td><a href="<%=path%>/myhome/<s:property value="#forum.lastPostNick"/>" target="_blank" class="	block"><s:property value="#forum.lastPostNick"/></a><s:property value="#forum.formatedLastPostTime"/></td>
				</tr>
			</s:iterator>	
				<s:action var="topForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="#parameters.boardId[0]"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="query.istop" value="1"></s:param>
			      <s:param name="countPerPage" value="6"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
		        </s:action>
		        <s:iterator value="#topForums.forums.list" var="forum">
					<tr>
					<td class="up_bbs"><span><a href="<%=path%>/community/<%=pbname%>/detail/<s:property value="#forum.id"/>" target="_blank" class="blue"><s:property value="#forum.title"/></a>
					<s:if test='#forum.elite==1'><img  src="<%=path%>/images/bbs_list_jj.gif" class="m-l4"></s:if>
					<s:if test='#forum.knowledgeRecommendRegion!=null&&#forum.knowledgeRecommendRegion.id>0'><img  src="<%=path%>/images/bbs_list_tj.png" class="m-l4"></s:if>
					</span></td>
					<td><a href="<%=path%>/myhome/<s:property value="#forum.creatorNick"/>" target="_blank" class="	block"><s:property value="#forum.creatorNick"/></a><s:property value="#forum.formatedStartTime.substring(5)"/></td>
					<td><s:property value="#forum.renum"/>/<s:property value="#forum.clickCount"/></td>
					<s:if test='#forum.renum>0'>
					<td><a href="<%=path%>/myhome/<s:property value="#forum.lastPostNick"/>" target="_blank" class="block"><s:property value="#forum.lastPostNick"/></a><s:property value="#forum.formatedLastPostTime.substring(5)"/></td>
					</s:if>
					 <s:else>
				       <td>----</td>
				    </s:else>				
				    </tr>
				</s:iterator>
				<tr>	
					<td colspan="4" class="f14 fb bbs_list_zt">板块主题</td>		
				</tr>
				<s:action var="normalForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="#parameters.boardId[0]"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="query.istop" value="0"></s:param>
			      <s:param name="countPerPage" value="20"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        
		        <s:iterator value="#normalForums.forums.list" var="forum">
					<tr>
					<td class="pt_bbs"><span><a href="<%=path%>/community/<%=pbname%>/detail/<s:property value="#forum.id"/>" target="_blank"><s:property value="#forum.title"/></a>
					<s:if test='#forum.elite==1'><img  src="<%=path%>/images/bbs_list_jj.gif" class="m-l4"></s:if>
					<s:if test='#forum.knowledgeRecommendRegion!=null&&#forum.knowledgeRecommendRegion.id>0'><img  src="<%=path%>/images/bbs_list_tj.png" class="m-l4"></s:if>
					</span></td>
					<td><a href="<%=path%>/myhome/<s:property value="#forum.creatorNick"/>" target="_blank" class="	block"><s:property value="#forum.creatorNick"/></a><s:property value="#forum.formatedStartTime.substring(5)"/></td>
					<td><s:property value="#forum.renum"/>/<s:property value="#forum.clickCount"/></td>
					<s:if test='#forum.renum>0'>
					<td><a href="<%=path%>/myhome/<s:property value="#forum.lastPostNick"/>" target="_blank" class="block"><s:property value="#forum.lastPostNick"/></a><s:property value="#forum.formatedLastPostTime.substring(5)"/></td>
				    </s:if>	
				    <s:else>
				       <td>----</td>
				    </s:else>
				    </tr>
				</s:iterator>
			</tbody>		
			</table>
    		
    		</div>
    		<s:if test="#normalForums.forums.pageString != null && #normalForums.forums.pageString != ''">
			<div class="pagenum">
			<s:property value="#normalForums.forums.pageString" escape="false"/>
			</div>
			</s:if>	
    	</div>
    	
    </div>
    <div class="col2 fl ml10">
    	<div class="hottj borda pr pt40">
        	<div class="h3tita pa">
            	<h3 class="fyahei">24小时热帖排行</h3>
            </div>
           	<s:action var="hot24Forums" name="forum_list" namespace="/forum" executeResult="false" ignoreContextParams="false">
		      <s:param name="query.isnew" value="1"></s:param>
		      <s:param name="countPerPage" value="10"></s:param>
		      <s:param name="query.state" value="1"></s:param>
		      <s:param name="currentPage" value="1"></s:param>
		      <s:param name="orderField" value="clickCount"></s:param>
	        </s:action>
            <div class="ph">
	            <ul>
	            <s:iterator value="#hot24Forums.forums.list" var="forum">
	               <li><a href="<%=path%>/community/<%=pbname%>/detail/<s:property value="#forum.id"/>"><s:property value="#forum.title"/></a></li>
	            </s:iterator>
	            </ul>  
            </div>
        </div>
        <div class="ad_col2_1 borda mt10"><a href="#"><img src="<%=path%>/images/img_268_88.jpg" /></a></div>
        <div class="mt10">
        	<div class="h3titb">
            	<h3 class="fyahei">活跃用户</h3>
            </div>
            <div class="bordb dygs">
                <ul class="img_f">
                <s:action var="mingxing104Users" name="user_listByCondition" namespace="/user" executeResult="false" ignoreContextParams="false">
			      <s:param name="countPerPage" value="3"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="104"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#mingxing104Users.users.list" var="user">
		           <li><a class="a-img1" href="<%=path%>/myhome/<s:property value="#user.nickName"/>"><img  alt="" src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#user.portrait"), path + "/images/head.jpg", 70)%>" width=65 height=65/></a><a href="<%=path%>/myhome/<s:property value="#user.nickName"/>"><s:property value="#user.nickName"/></a></li>
		        </s:iterator>              
                </ul>            
            </div>
        </div>
    </div>
    <%@ include file="../inc/templateLink.jsp"%>
    </div>     
<%@ include file="../inc/templateFoot.jsp"%>