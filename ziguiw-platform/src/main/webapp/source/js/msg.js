	//留言内容行点击事件，查询留言详情
	function msgtronclick(id){
		document.getElementById("contentid").value=id;
		form1.action = "user/MsgDetail.action";
		form1.submit();
		
	}
	
	//跳转到修改学校公告事件
	function scheditclick(id){
		document.getElementById("contentid").value=id;
		form1.action = "user/schMsgEditGo.action";
		form1.submit();
	}
	
	//删除学校公告事件
	function schdelclick(id){
		if(confirm('确定删除?')){
			document.getElementById("contentid").value=id;
			form1.action = "user/schMsgDel.action";
			form1.submit();
		}
	}
	
	//跳转到修改班级通知事件
	function classTZeditclick(id){
		document.getElementById("contentid").value=id;
		form1.action = "user/ClassTZEditGo.action";
		form1.submit();
	}
	
	//删除班级通知事件
	function classTZdelclick(id){
		if(confirm('确定删除?')){
			document.getElementById("contentid").value=id;
			form1.action = "user/teaMsgDel.action";
			form1.submit();
		}
	}
	
	
	//跳转到修改老师留言事件
	function teaMsgeditclick(id){
		document.getElementById("contentid").value=id;
		form1.action = "user/TeaMsgEditGo.action";
		form1.submit();
	}
	
	function remsgclick(){
		form1.action = "user/reMsg.action";
		form1.submit();
	}
	
	function goaction(actionurl){
		form1.action = actionurl;
		form1.submit();
	}
	 
	//查询按钮点击 修改默认查询标识
	function goQuery(){
		document.getElementById('firstsign').value=2;
		form1.submit();
	}
	//查询家长反馈信息详情 回复
	function feedbackonclick(sid,reid){
		if("0" == reid || "-1" == reid){
			alert("对不起，工作人员暂未进行回复，请耐心等待！");
			return false;
		}
		else{		
			document.getElementById('feedbackid').value=sid;
			form1.action = "user/parDsisFeedbackDetail.action";
			form1.submit();
		}
	}