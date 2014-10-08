<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.zigui.domain.UserBase,java.util.Map,org.apache.struts2.ServletActionContext,com.opensymphony.xwork2.ActionContext"%>
<% 
String path = request.getContextPath();
ActionContext context = ActionContext.getContext();
Map<String, Object> session1 = context.getSession();
UserBase user = (UserBase)session1.get("VALID_USER");
long uid = 0;
if(user!=null){
  uid = user.getId();
} 
%>

function validateSave() {
  var uid = <%=uid %>;
  if(uid==0){
    alert('您好，必须要登录之后才可以发帖!');
     return false;
  }
  var isnew = document.getElementById('isnew').getAttribute('value');
  var boardId = document.getElementById('boardId').value;//alert('isnew:'+isnew);alert('boardId:'+boardId);
  //var content = document.getElementById('content1').value;alert('content:'+content);
  var content = KE.util.getData('content1');
  if(content==null||content==""){
  	alert('请输入内容！');
  	 return false;
  }
  
  if(boardId==null||boardId==0){
  	alert('请选择版区！');
  	 return false;
  }
  if(isnew==1){
      var title = document.getElementById('title').value;//alert('title:'+title);
	  if(title==null||title==''){
	  	alert('话题标题不能为空');	
	  	return false;
	  }else if(title.length>100){
	  	alert('话题标题太长');
	  	return false;
	  }
	  
  }else if(isnew == 0){
  
  }else{
    return false;
  }
  return true;
}

function validateQuestionSave() {
  var uid = <%=uid %>;
  if(uid==0){
    alert('您好，必须要登录之后才可以发帖!');
     return false;
  }
  //var content = document.getElementById('content1').value;alert('content:'+content);
  var content =  document.getElementById('content1').value;
   var title = document.getElementById('title').value;//alert('title:'+title);
   var pendingTime = document.getElementById('pendingTime').value;
   var point = document.getElementById('point').value;
   var   r   =   /^\+?[1-9][0-9]*$/;
  if(content==null||content==""||content.length>800){
  	alert('内容不符合规定！');
  	 return false;
  }
  
  if(title==null||title==''){
	  	alert('话题标题不能为空');	
	  	return false;
	  }else if(title.length>100){
	  	alert('话题标题太长');
	  	return false;
   }
   
   if(pendingTime==null||point==null||!r.test(pendingTime)||!r.test(point)){
       alert('时间或积分要求为正整数！');	
	   return false;
   }
   if(pendingTime<1||pendingTime>90){
       alert('问题的开放时间必须要1~90天!');	
	   return false;
   }
  return true;
}

function validateAnswerSave() {
  var uid = <%=uid %>;
  if(uid==0){
    alert('您好，必须要登录之后才可以发帖!');
     return false;
  }

  var content =  document.getElementById('content1').value;
  if(content==null||content==""){
  	alert('请输入内容！');
  	 return false;
  }
  return true;
}

function validateDebateOpinionSave(type) {
  var uid = <%=uid %>;
  if(uid==0){
    alert('您好，必须要登录之后才可以发帖!');
     return false;
  }

  var content  =  document.getElementById('content'+type).value;
  if(content==null||content==""){
  	alert('请输入内容！');
  	 return false;
  }
  return true;
}

function validatetest() {alert('a');
  var   r   =   /^\+?[1-9][0-9]*$/;
  alert(r.test('18'));
}

function validateMoodSave() {
  var uid = <%=uid %>;
  if(uid==0){
    alert('您好，必须要登录之后才可以发帖!');
     return false;
  }

  var content =  document.getElementById('content1').value;
  //var content =  this.content1.value;alert(content);
  if(content==null||content==""){
  	alert('请输入内容！');
  	 return false;
  }
  return true;
}
