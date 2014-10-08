<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>

<div class="w_home_content">

      
<div class="content clearfix  leave_message">
<div class=" mypos1 fsong mb10"> <a href="#">我的小家</a> &gt; 站内信</div>


	<div class="bordb my_home" style="height:80px;min-height:80px">
		<div class="h3titb">
			<h3 class="fyahei">我的信箱</h3>
		</div>
	
		<div class="p_b10 mb10">
			<a href="<%=path%>/user/mail.jsp?mailType=1">【发件箱】</a>&nbsp;<a href="<%=path%>/user/mail.jsp?mailType=2">【收件箱】</a>
		</div>	
	</div>
	
	<s:action name="userMessage_getMailList" var="message_list" namespace="/user" executeResult="false" ignoreContextParams="false">
		<s:param name="mailType"><s:property value="#parameters.mailType"/></s:param>
	</s:action>
	
	<s:iterator value="#message_list.messages.list" var="message">
	<div class="clearfix message_list pr">
		<div class="l w-120 mes_list_l">
		<a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank" class="mt10">
		<img alt="" src="<s:property value="#message.formUser.portrait"/>" width="100" height="100"></a>
		<a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank"><s:property value="#message.formUser.nickName"/></a>
		</div>
		<div class="mes_list_r">
			<label class="gray"><span class="r"><%if(isHost) {%><a href="<%=path%>/user/delMailById.action?message.id=<s:property value="#message.id"/>&mailType=<s:property value="#parameters.mailType"/>" class="m-lr4">删除</a><%} %> </span> <s:property value="#message.title"/> <s:property value="#message.formatedCreateDate"/></label>
			<p class="block"><s:property value="#message.text"/></p>
				<div class="mes_hf">
				<form action="<%=path%>/user/message_saveMail.action">
				<s:fielderror/>
				<p class="">
				<%if(request.getParameter("mailType") != null && request.getParameter("mailType").equals("2")){ %>
				<input type="button" class="btn2 l mt5 reply_mail" value="回信" name="reply_mail"/>	
				<%} %>
				<input type="hidden" name="title" value="<s:property value="#message.title"/>">
				<input type="hidden" name="toUser" value="<s:property value="#message.formUser.id"/>">
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

<div id="send_mail_dialog" style="display:none;">
	<div class="alert alert-620">
	<form action="<%=path%>/user/message_saveMail.action" method="post" id="form_send_mail" name="form_send_mail" >
  <div class="body">
    <div class="cont">
      <ul class="padding-25">
        <li class="margin-10">
          <label class="label font-14">私信标题：</label>
          <input type="text" class="txt input-265" name="message.title" id="reply_title" validation="required"  maxlength="20"/><span  style="display:none;color:red;">私信标题不能为空</span>
        </li>
        <li class="margin-10" onclick="document.getElementById('placeholder').style.display='none';">
          <label class="label_top font-14">私信内容：</label>
          <textarea rows="5" class="area" name="message.text"  id="message.text"></textarea>
          <span style="position: absolute; top: 70px; color: rgb(153, 153, 153); left: 134px;" class="placeholder" id="album-placeholder"></span>
          <span  style="display:none;color:red;"></span>
        </li>
        
        <input type="hidden" name="message.toUser.id" id="toUser"></input>
       
        <li class="nopadding btn-box">
          <input type="submit" value="确定" class="btn2"/>
          <img src="/img/common/loading_pic_s.gif" style="display:none" id="form_send_mail_loading"/>
        </li>
      </ul>
    </div>
  </div>
  </form>
  </div>
  </div>
 </div>
<link href="http://static01.babytreeimg.com/img/css/boxy2.css?ver=20110707" rel="stylesheet" type="text/css" />
	<script src="http://static01.babytreeimg.com/img/js/boxy2.js?ver=1315188206" type="text/javascript"></script>
	
	<script>
	jQuery(document).ready(function(){
		 boxy2_init(true);    //里面的参数表示是否带标题栏
		});
	$(function(){
		$(".reply_mail").click(
			function(){
				boxy2.pop(jQuery('#send_mail_dialog').html(),620,220); 
				boxy2.title('回复私信');
				
				$("#reply_title").val("RE:" + $(this).next('input').val());
				$("#toUser").val($(this).next('input').next('input').val());
		});
	});
	
	</script>

<%@ include file="../inc/templateFoot.jsp"%>

<script type="text/javascript" src="<%=path %>/js/user/leave_message.js"></script>
