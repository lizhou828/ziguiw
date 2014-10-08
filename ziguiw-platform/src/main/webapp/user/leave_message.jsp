<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>  

<div class="w_home_content">
  
  
<div class="content clearfix  leave_message">
<div class=" mypos1 fsong mb10"> <a href="#">我的小家</a> &gt; 留言板</div>

	<s:action var="listMessage" name="message_list" namespace="/user" executeResult="false" ignoreContextParams="false">
		<s:param name="pageSize" value="10"></s:param>
		<s:param name="pageNum" value="0"></s:param>
		<s:param name="ownerId" value="owner.id"></s:param>
	</s:action>


	<div class="bordb my_home">
		<div class="h3titb">
			<h3 class="fyahei">留言板</h3>
		</div>
	<form action="<%=path%>/user/publishMessage.action">
	<s:fielderror/>
		<div class="p_b10 mb10">
			<div class="o-v leave_k">
				<textarea class="area3" name="message.text"></textarea>
				<input type="submit" class="btn2 l mt5" value="发 表" name="" id="messagePostBtn">	
				<input id="hostUserId" type="hidden" name="hostUserId" value="<%=hostUserId%>"/>
				<input id="hostUserName" type="hidden" name="hostUserName" value="<%=hostUserName%>"/>
			</div>		
		</div>	
		</form>
	</div>
	
	<s:action name="message_list" var="message_list" namespace="/user" executeResult="false" ignoreContextParams="false">
	</s:action>
	
	<s:iterator value="#message_list.messages.list" var="message">
	<div class="clearfix message_list pr">
		<div class="l w-120 mes_list_l">
		<a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank" class="mt10">
		<img alt="" src="<s:property value="#message.formUser.portrait"/>" width="100" height="100"></a>
		<a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank"><s:property value="#message.formUser.nickName"/></a>
		</div>
		<div class="mes_list_r">
			<label class="gray"><span class="r"><%if(isHost) {%><a href="<%=path%>/user/delMessageById.action?message.id=<s:property value="#message.id"/>" class="m-lr4">删除</a><%} %> </span> <s:property value="#message.formUser.nickName"/> <s:property value="#message.createTime"/> 留言</label>
			<p class="block"><s:property value="#message.text"/></p>
				<div class="mes_hf">
				<s:iterator value="#message.sortedChildMsgs" var="childMsg">
					<dl>
					<dt class="l"><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#childMsg.formUser.id"/>" target="_blank" class="a-img1"><img alt="" src="<s:property value="#childMsg.formUser.portrait"/>" width="48" height="48"></a></dt>
					<dd><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#childMsg.formUser.id"/>" target="_blank"><s:property value="#childMsg.formUser.nickName"/>：</a><s:property value="#childMsg.text"/> </dd>
					<dd class="gray mt5"><s:property value="#childMsg.createTime"/> <%if(isHost) {%><a href="<%=path%>/user/delMessageById.action?message.id=<s:property value="#childMsg.id"/>">删除</a><%} %></dd>
					</dl>
				</s:iterator>
				<form action="<%=path%>/user/publishMessage.action">
				<s:fielderror/>
				<p class="mes_hf_k">
				<textarea class="area3" name="message.text"></textarea>
				<input type="submit" class="btn2 l mt5" value="发 表" name="Submit1">	
				<input type="hidden" name="hostUserId" value="<s:property value="#parameters.hostUserId"/>"/>
				<input type="hidden" name="message.parentMsg.id" value="<s:property value="#message.id"/>"/>
				<input id="hostUserName" type="hidden" name="hostUserName" value="<%=hostUserName%>"/>
				</p>
				</form>
				</div>
		</div>			
	</div>
	</s:iterator>
	
	<s:if test="#message_list.messages.pageString != null && #message_list.messages.pageString != ''">
		<div class="pagenum">
			<s:property value="#message_list.messages.pageString" escape="false"/>
		</div>
		</s:if>
	
	
</div>


</div>

<%@ include file="../inc/templateFoot.jsp"%>

<script type="text/javascript" src="<%=path %>/js/user/leave_message.js"></script>
