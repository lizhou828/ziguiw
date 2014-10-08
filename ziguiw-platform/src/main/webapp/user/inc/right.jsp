<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="l w-190 my_left hei-400" style="left:20px;">
		
		<div class="my_info">
		
				<dl class="f">
					<%--<dt><a href="#" target="_blank" class="a-img1"><img alt=" " src="<%=ImageUtils.getSizedImage(hostUser.getPortrait(), path + "/images/head.jpg", 120)%>" /></a></dt>--%>
                    <dt><a href="#" target="_blank" class="a-img1"><img alt=" " src="/<%=hostUser.getPortrait()%>" /></a></dt>
					<dd><a href="#" class="name"><%=hostUser.getNickName()%></a></dd>
					<dd>积分：<%=hostUser.getPoints()%></dd>
					<dd>性别：
						<%if(hostUser.getSex() == null){%>未知<%}else if(hostUser.getSex() == 1){%>男<%}else if(hostUser.getSex() == 2) {%>女<%}%>
					</dd>
					<dd>地区：<%if(hostUser.getProvince() != null){%><%=hostUser.getProvince()%><%}else {%><%}%></dd>
				</dl>
				<p class="reqm"><b>个人签名：</b><%if(hostUser.getSignature() != null){%><%=hostUser.getSignature()%><%}else {%><%}%></p>
				<%if(isHost){ %>
				
				<a href="<%=path%>/user/upload_portrait.jsp" class="inp_sum4">修改头像</a>
				<a href="<%=path%>/user/user_setting.jsp" class="inp_sum4">修改资料</a>
				<a href="<%=path%>/user/update_passwd.jsp" class="inp_sum4">修改密码</a>
				<a href="<%=path%>/user/select_role.jsp" class="inp_sum4">完善资料</a>
				
				<%} else if(user != null && user.getId() != hostUserId){%>
					<a style="display:none" href="javascript:void(0)" class="inp_sum4" id="addFriend">加为好友</a>
					<a style="display:none" href="javascript:void(0)" class="inp_sum4" id="removeFriend">移除好友</a>
					<a style="display:none" href="javascript:void(0)" class="inp_sum4" id="sendMail">发私信</a>
				<%}%>
		</div>
	</div>
	
	<div id="send_mail_dialog" style="display:none;">
	<div class="alert alert-620">
	<form action="<%=path%>/user/message_saveMail.action" method="post" id="form_send_mail" name="form_send_mail" >
  <div class="body">
    <div class="cont">
      <ul class="padding-25">
        <li class="margin-10">
          <label class="label font-14">私信标题：</label>
          <input type="text" class="txt input-265" name="message.title" id="message.title" validation="required"  maxlength="20"/><span  style="display:none;color:red;">私信标题不能为空</span>
        </li>
        <li class="margin-10" onclick="document.getElementById('placeholder').style.display='none';">
          <label class="label_top font-14">私信内容：</label>
          <textarea rows="5" class="area" name="message.text"  id="message.text"></textarea>
          <span style="position: absolute; top: 70px; color: rgb(153, 153, 153); left: 134px;" class="placeholder" id="album-placeholder"></span>
          <span  style="display:none;color:red;"></span>
        </li>
        
        <input type="hidden" name="message.toUser.id" value="<%=hostUser.getId()%>"></input>
       
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
	<link href="http://static01.babytreeimg.com/img/css/boxy2.css?ver=20110707" rel="stylesheet" type="text/css" />
	<script src="http://static01.babytreeimg.com/img/js/boxy2.js?ver=1315188206" type="text/javascript"></script>
	<script>
	jQuery(document).ready(function(){
		 boxy2_init(true);    //里面的参数表示是否带标题栏
		});
	$(function(){
		$("#addFriend").click(
			function(){
			$.post("<%=path%>/userFriend_addFriendAjax.action?hostUserName=" + encodeURIComponent('<%=hostUserName%>'), function(data) {
				$('#removeFriend').show();
				$('#addFriend').hide();
			});
		});
	});
	$('#removeFriend').click(function(){
		$.post("<%=path%>/userFriend_removeFriendAjax.action?hostUserName=" + encodeURIComponent('<%=hostUserName%>'), function(data) {
			$('#removeFriend').hide();
			$('#addFriend').show();
			
		});
	});
	$('#sendMail').click(function(){
		boxy2.pop(jQuery('#send_mail_dialog').html(),620,220); 
		boxy2.title('发送私信');
	});
	
	$(function(){
		$.post("<%=path%>/userFriend_getFriendRelationAjax.action?hostUserName=" + encodeURIComponent('<%=hostUserName%>'), function(data) {
			
			if(data.userFriend != null && data.userFriend.id > 0){
				$('#removeFriend').show();
				$('#sendMail').show();
				$('#addFriend').hide();
			}else{
				$('#removeFriend').hide();
				$('#sendMail').hide();
				$('#addFriend').show();
			}
			
		});
	});
	</script>
	