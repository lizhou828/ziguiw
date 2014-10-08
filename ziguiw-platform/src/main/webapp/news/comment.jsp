<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/templateHead.jsp"%>

<!--header E -->

	<s:action name="news_getById" var="getNews" namespace="/admin/news" executeResult="false" ignoreContextParams="false">
		<s:param name="newsId">${commentedId}</s:param>
	</s:action>

<div class="content clearfix  leave_message ">
<div class=" mypos1 fsong mb10"> <a href="#">子贵新闻</a> &gt; <s:property value="#getNews.news.title"/></div>
	<div class="bordb new_view">
		<div class="h3titb">
			<h3 class="fyahei">发表评论</h3>
		</div>
		<div class="p_b10 mb10 ">
		<form action="<%=path%>/user/commentNews.action">
				<div class="o-v leave_k">
					<ul class="ul-li-s">				
					<li><span>用户名：</span><input type="text" value="" name="loginUser.nickName" class="inp_txt"><a href="" target="_blank" class="m-l10">快速注册新用户</a></li>
					<li><span>密码：</span><input type="password" name="loginUser.password" class="inp_txt"></li>
					<li><span></span><textarea name="message.text" class="area3"></textarea></li>
					<input type="hidden" name="message.type" value="2">
					<input type="hidden" name="message.commentedId" value="${commentedId}">
					<li><input type="submit" name="Submit1" value="发 表" class="btn2 l mt5"></li>
				</ul>
				</div>	
			</form>	
		</div>	
	</div>		
	
	<s:iterator value="messages.list" var="message">
	<div class="clearfix message_list pr">
		<div class="l w-120 mes_list_l">
		<a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank" class="mt10">
		<img alt="" src="<s:property value="#message.formUser.portrait"/>" width="100" height="100"></a>
		<a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank"><s:property value="#message.formUser.nickName"/></a>
		</div>
		<div class="mes_list_r">
			<label class="gray"><span class="r"><s:property value="#message.formUser.nickName"/> <s:property value="#message.createTime"/>评论</label>
			<p class="block"><s:property value="#message.text"/></p>
				<div class="mes_hf">
				<s:iterator value="#message.sortedChildMsgs" var="childMsg">
					<dl>
					<dt class="l"><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#childMsg.formUser.id"/>" target="_blank" class="a-img1"><img alt="" src="<s:property value="#childMsg.formUser.portrait"/>" width="48" height="48"></a></dt>
					<dd><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#childMsg.formUser.id"/>" target="_blank"><s:property value="#childMsg.formUser.nickName"/>：</a><s:property value="#childMsg.text"/> </dd>
					<dd class="gray mt5"><s:property value="#childMsg.createTime"/></dd>
					</dl>
				</s:iterator>
				<form action="<%=path%>/user/commentNews.action">
				<p class="mes_hf_k">
				<textarea class="area3" name="message.text"></textarea>
				<input type="submit" class="btn2 l mt5" value="发 表" name="Submit1">	
				<input type="hidden" name="message.commentedId" value="${commentedId}"></textarea>
				<input type="hidden" name="message.type" value=2></textarea>
				<input type="hidden" name="message.parentMsg.id" value="<s:property value="#message.id"/>"/>
				</p>
				</form>
				</div>
		</div>			
	</div>
	</s:iterator>

</div>

<%@ include file="../inc/templateFoot.jsp"%>