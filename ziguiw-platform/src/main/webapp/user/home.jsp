<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="inc/home_head.jsp"%>
<script src="<%=path%>/js/common.js.jsp"></script>


<div class="w_home_content">
  <div class="w_home_content_box clearfix">
  
  
<div class="content clearfix  pr">

	

	<%@ include file="inc/right.jsp"%>

	<div class="l w-520 my_mid">	
	<%if(isHost){ %>
	<form action="<%=path%>/user/publishMood.action" onsubmit='return validateMoodSave();'>		
		<div class="sbss o-v">
		<textarea name="mood.text" class="area2" id='content1'></textarea>
		<input name="submit" type="submit" value="发 表" class="btn2 r mt5" />		
		</div>
	</form>
	<%} %>
		<div><a class="ad01 mt10"><img alt="" src="<%=path%>/images/ad_520.jpg"></a></div>

		<div class="l speak_list">
		
		
		<s:action var="listMood" name="gr_mood_list" namespace="/user" executeResult="false" ignoreContextParams="false">
			<s:param name="pageSize" value="10"></s:param>
		</s:action>


		<s:iterator value="#listMood.userMoods.list" var="moods">
			<ul>	
				<li><s:property value="#moods.text"/></li>
				<li class="c_666"><s:property value="#moods.createTime"/>&nbsp;<%if(isHost){ %><a class="r" href="<%=path%>/user/delMood.action?mood.id=<s:property value="#moods.id"/>">删除</a><%} %></li>
				
			</ul>
		</s:iterator>	
		
		<s:if test="#listMood.userMoods.pageString != null && #listMood.userMoods.pageString != ''">
		<div class="pagenum">
			<s:property value="#listMood.userMoods.pageString" escape="false"/>
		</div>
		</s:if>
	
		</div>
	</div>
		<div class="r w-240">
		

		<div class="mt10">
			<div class="h3titb">
			<h3 class="fyahei">最近来访</h3>
			</div>
			<div class="bordb dygs wdxc">
				<ul class="img_f">
				<s:action var="visit_history_list" name="visit_history_list" namespace="/user" executeResult="false" ignoreContextParams="false">
				
				</s:action>
				<s:iterator value="#visit_history_list.spaceVisitHistorys" var="history">
				<li><a class="a-img1" href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#history.formUser.id"/>">
				<img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#history.formUser.portrait"), path + "/images/head.jpg", 70)%>" alt=""></a><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>"><s:property value="#history.formUser.nickName"/></a></li>
				</s:iterator>       
				</ul>            
			</div>
		</div>			
			<div class="djdzs mt10" >
				<div class="box03_hd">
				<h2>最新评论</h2>
				<a class="pubMore03" href="#" title="查看更多" target="_blank">查看更多</a>
				</div>	
				<div class="box03_bd">
			    	<div class="box_bd_son">
			    		<s:action var="message_list" name="message_list" namespace="/user" executeResult="false" ignoreContextParams="false">
							<s:param name="pageSize" value="5"></s:param>
						</s:action>
			    	
			    	<s:iterator value="#message_list.messages.list" var="message">
		 				<dl class="f">
						<dt class="l"><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank" class="a-img1">
						<img alt=" " src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#message.formUser.portrait"), path + "/images/head.jpg", 50)%>" width="48" height="48" /></a></dt>
						<dd class="p-l60 mb5"><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>"><s:property value="#message.formUser.nickName"/>:</a><s:property value="#message.text"/></dd>
						<dd><s:property value="#message.createTime"/>&nbsp;</dd>
						</dl>				
						</s:iterator>
		        	</div>
				</div>			
			</div>	
	</div>
		</div>			
</div>	
</div>
<!--content E -->

<%@ include file="../inc/templateFoot.jsp"%>