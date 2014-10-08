<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>
<%
if(hostUser.getType() == 1){
	response.sendRedirect(path + "/myschool/" + hostUserName);
	return;
}
%>
 <script src="<%=path%>/js/common.js.jsp"></script>
<link href="../css/basc.css" rel="stylesheet" type="text/css">
<link href="../css/xiaohome.css" rel="stylesheet" type="text/css">

<div class="w_home_content">
  <div class="w_home_content_box clearfix">

	<%@ include file="inc/right.jsp"%>

	<div class="l w-520 my_mid">	
	<%if(isHost){ %>
	<form action="<%=path%>/user/publishMood.action"  onsubmit='return validateMoodSave();'>		
		<div class="sbss o-v">
		<textarea name="mood.text" class="area2"  id='content1'></textarea>
		<input name="submit" type="submit" value="发 表" class="btn2 r mt5" />		
		</div>
	</form>
	<%} %>
		<div><a class="ad01 mt10"><img alt="" src="<%=path%>/images/ad_520.jpg"></a></div>

		<s:action var="listMood" name="gr_mood_list" namespace="/user" executeResult="false" ignoreContextParams="false">
			<s:param name="pageSize" value="5"></s:param>
		</s:action>

	<div class="speak_list">
		<s:iterator value="#listMood.userMoods.list" var="moods">
		
			<ul>	
				<li><s:property value="#moods.text"/></li>
				<li class="c_666">
				<s:property value="#moods.createTime"/>
				&nbsp;
				</li>
			</ul>
			
		</s:iterator>	
	
		</div>
		<div class="my_home_col mt10">
		
		<div class="h3titb pr">
		<h3 class="fyahei">我的日志</h3>
			<div class="pubMore07">
				<a target="_blank" href="<%=path%>/user/diary_list.jsp.?hostUserName=<%=hostUser.getNickName()%>">更多</a>
			</div>
		</div>
		<div class="bordb dygs wdxc p_b10 mb10">
		
		
		<s:action var="diaryList" name="diary_getByNickName" namespace="/user" executeResult="false" ignoreContextParams="false">
			<s:param name="status" value="1"></s:param>
			<s:param name="pageSize" value="3"></s:param>
			<s:param name="userId" value="0"></s:param>
		</s:action>
				
		<s:iterator value="#diaryList.pagedDiaries.list" var="diaries">
			<div class="my_art_dy">
				<h4><a href="<%=path%>/user/getDiaryById.action?diary.id=<s:property value="#diaries.id"/>&hostUserName=<s:property value="#diaries.user.nickName"/>" target="_blank" class="red_f63"><s:property value="#diaries.title"/></a></h4>
		 		<p><s:property value="#diaries.description" escape="false"/></p>
				<p class="c_666">	
		 		<a href="<%=path%>/user/getDiaryById.action?diary.id=<s:property value="#diaries.id"/>&hostUserName=<s:property value="#diaries.user.nickName"/>" target="_blank">查看全文</a>
		 		<a href="#" target="_blank">阅读(<s:property value="#diaries.viewCount"/>)</a>
		 		</p>
			</div>
		</s:iterator>
	    </div>
		</div>
		<div class="my_home_col mt10">
		<div class="h3titb pr">
			<h3 class="fyahei pr">我的相册</h3>
			<div class="pubMore07">
				<a target="_blank" href="<%=path%>/user/album_list.action?hostUserName=<%=hostUser.getNickName()%>">更多</a>
			</div>
		</div>
		<div class="bordb big_dygs wdxc p_b10 mb10">
		<ul class="img_f">
		<s:action var="albumList" name="album_list" namespace="/user" executeResult="false" ignoreContextParams="false">
			<s:param name="pageSize" value="2"></s:param>
		</s:action>
		<s:iterator value="#albumList.albums.list" var="album">
			<li><a class="big-img1" href="<%=path%>/user/getPhotoByAlbumId.action?albumId=<s:property value='#album.id'/>&hostUserName=<%=hostUser.getNickName()%>"><img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#album.coverPhoto.url"), path + "/images/head.jpg", 120)%>" alt=""></a><a href="<%=path%>/user/getPhotoByAlbumId.action?albumId=<s:property value='#album.id'/>&hostUserName=<%=hostUser.getNickName()%>"><s:property value='#album.title'/></a></li>
		</s:iterator>      
		</ul>       
	
  		</div>
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
				<li><a class="a-img1" href="<%=path%>/user/portal.jsp?hostUserName=<s:property value="#history.formUser.nickName"/>">
				<img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#history.formUser.portrait"), path + "/images/head.jpg", 70)%>" alt=""></a><a href="<%=path%>/user/portal.jsp?hostUserName=<s:property value="#history.formUser.nickName"/>"><s:property value="#history.formUser.nickName"/></a></li>
				</s:iterator>       
				</ul>            
			</div>
		</div>			
			<div class="djdzs mt10" >
				<div class="box03_hd">
				<h2>最新评论</h2>
				<a class="pubMore03" href="<%=path%>/user/leave_message.jsp?hostUserName=<%=hostUser.getNickName()%>" title="查看更多" target="_blank">查看更多</a>
				</div>	
				<div class="box03_bd">
			    	<div class="box_bd_son">
			    		<s:action var="message_list" name="message_list" namespace="/user" executeResult="false" ignoreContextParams="false">
							<s:param name="pageSize" value="5"></s:param>
						</s:action>
			    	
			    	<s:iterator value="#message_list.messages.list" var="message">
		 				<dl class="f">
						<dt class="l"><a href="<%=path%>/user/portal.jsp?hostUserName=<s:property value="#message.formUser.nickName"/>" target="_blank" class="a-img1">
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
<!--content E -->
<%@ include file="../inc/templateFoot.jsp"%>