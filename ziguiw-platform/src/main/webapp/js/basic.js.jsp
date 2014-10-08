<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<% 
String path = request.getContextPath();
%>

jQuery.cookie = function(name, value, options) {
if (typeof value != 'undefined') {
   options = options || {};
   if (value === null) {
    value = '';
    options = $.extend({}, options);
    options.expires = -1;
   }
   var expires = '';
   if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
    var date;
    if (typeof options.expires == 'number') {
     date = new Date();
     date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
    } else {
     date = options.expires;
    }
    expires = '; expires=' + date.toUTCString();
   }
   var path = options.path ? '; path=' + (options.path) : '';
   var domain = options.domain ? '; domain=' + (options.domain) : '';
   var secure = options.secure ? '; secure' : '';
   document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
} else {
   var cookieValue = null;
   if (document.cookie && document.cookie != '') {
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; i++) {
     var cookie = jQuery.trim(cookies[i]);
     if (cookie.substring(0, name.length + 1) == (name + '=')) {
      cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
      break;
     }
    }
   }
   return cookieValue;
}
};

$(function(){
	$.post("<%=path%>/user/user_ajaxGetLogonInfo.action", function(data) {
		if(data != null && data.user != null && data.user.nickName != null){
		  $(".subNav").html("欢迎你来到子贵," + "<a target=\"_blank\" href=\"<%=path%>/myhome/" + data.user.nickName+ "\">"+ data.user.nickName + "</a><a href=\"<%=path%>/user/user_logout.action\" title=\"退出登录\">退出</a>");

		}
	});
	
});

$(function(){ //文档加载

         $(".nav_tit li").hover(

         function(){
				   $(".nav_tit li:eq(0)").removeClass("cur");
                   $(this).addClass("cur");

         },

         function(){

                   $(this).removeClass("cur");

         }

         );

});

$(function(){
	$(".subNav a").click(
		function(){
			$.post("<%=path%>/user/user_setLoginPreUrl.action", function(data) {
			
			});
		}
	);

});


$(function(){
	$('input[type="text"]').bind("click", myFun1 = function(){
          //$(this).val('');//多么悲催的代码 removed by YeQiang 2013-01-10
          $(this).unbind("click", myFun1);
    });

});


$(function(){
	$("#testTemplate").click(
		function(){
		$.post("<%=path%>/admin/template/template_ajaxPreviewTemplate.action", {templateContent:encodeURIComponent($("#templateContent").attr("value"))}, function(data) {
		  OpenWindow=window.open("", "newwin", "height=500, width=500,toolbar=no,menubar=no"); 
		  //写成一行 
		  OpenWindow.document.write("" + data.templateResult);
		});
	});
});

$(function(){
	$("#testFunction").click(
		function(){
		$.post("<%=path%>/admin/template/template_ajaxPreviewFunction.action?hql=" + encodeURIComponent($("#hql").attr("value")) + "&parameter=" + encodeURIComponent($("#parameter").attr("value")) + "&testParameter=" + encodeURIComponent($("#testParameter").attr("value")), function(data) {
		  OpenWindow=window.open("", "newwin", "height=500, width=500,toolbar=no,menubar=no"); 
		  //写成一行 
		  OpenWindow.document.write("" + JSON.stringify(data.functionData, ""));
		});
	});
});

$(function(){
	$(".login_btn").click(
		function(){
		$.ajax({
		type : "post",
		url : "<%=path%>/user_ajaxLogin.action",
		dataType : 'text',
		data : '&user.nickName=' + encodeURIComponent($('#accountId').attr("value")) + '&user.password='+ $("#passwd").attr("value")  + '&validateCode='+ $("#validateCode").attr("value") ,
		success : function(text) {
			if(text == 'success1'){
				//$(".nav_tit li").removeClass("cur");
				//showLoginAfLayer();
				window.location.href='<%=path%>/school/school_manager.jsp';
				
			}else if(text == 'success2'){
				window.location.href='<%=path%>/userCenter/userCenter.jsp';
			}else if(text == 'success3'){
				window.location.href='<%=path%>/user/portal.jsp';
			}else if(text == 'error:1'){
				$('.red').html("验证码错误");
			}else if(text == 'error:2'){
				$('.red').html("不存在该用户");
			}else if(text == 'error:3'){
				$('.red').html("不存在该用户");
			}else if(text == 'error:4'){
				$('.red').html("密码错误");
			}else if(text == 'error:5'){
				$('.red').html("不存在该用户");
			}else if(text == 'error:6'){
				$('.red').html("密码错误");
			}else if(text == 'error:7'){
				$('.red').html("帐号未激活");
			}
		}
	   });
	});
});

//权限管理页面，授予权限
$(function(){
	$("#auth_grant").click(
		function(){
			var url = "<%=path%>/admin/auth/role_grantAuth.action?role.id=" + $("#roleId").val(); 
			$('input[name="auths"]:checked').each(function(){    
			   url = url + "&authIds=" + $(this).val();    
			});
			
			alert(url);
			  
			$.post(url, function(data){
				$(".tc_bg").hide();
				location.reload();
			});
		});

});

//权限管理页面，初始化的时候权限框不显示
$(function(){ //文档加载

     $(".tc_bg").hide();
     

});


//只调用该方法即可，显示login后的层
	function showLoginAfLayer(){
		//以下为全部html代码
		var maskhtmlstr='<div id="maskLayout" style="position:absolute;top:0px; left:0px; background:#000;filter:alpha(opacity=50);-moz-opacity:0.5;-khtml-opacity: 0.5; opacity: 0.5;width:100px; height:100px"></div>'
		//下面的html中两个A既为对应的连接
		var loginAfhtmlstr = '<div id="_loginAfPanle" style=" position:relative;width:883px; height:304px; top:-850"><div style="width:883px; height:304px; background:url(<%=path%>/images/loginAfPanel.png)"></div><div style=" position:absolute; height:104px; top:100px; left:48px; margin:auto"><a href="<%=path%>/user/portal.jsp" style="margin-right:50px;"><img border="0" src="<%=path%>/images/loginAfBtn1.png" /></a><a href="<%=path%>/userCenter/userCenter.jsp"><img border="0" src="<%=path%>/images/loginAfBtn2.png"/></a></div></div>';
		
		var loginAfLayoutDom = document.getElementById("_loginAfLayoutDom");
		//判断是否已经存该层
		if(!loginAfLayoutDom){
			loginAfLayoutDom = createloginAf(maskhtmlstr+loginAfhtmlstr);
		}
		
		var maskLayout = loginAfLayoutDom.firstChild;
		
		var loginAfPanle = document.getElementById("_loginAfPanle");
		
		maskLayout.style.width = document.documentElement.clientWidth +"px";
		maskLayout.style.height = 1200 +"px";
		
		loginAfLayoutDom.style.display="block";
		
		loginAfPanle.style.top= -850 +"px";
		loginAfPanle.style.left= ((document.documentElement.clientWidth - document.documentElement.scrollLeft) - loginAfPanle.offsetWidth)/2 +"px";
		
		
		
		//如果觉得load出来慢。可以把下面的function放在页面onlond中执行
		function createloginAf(str){
			
			var _lald = document.createElement("div");
			_lald.style.display="none";
			_lald.id = "_loginAfLayoutDom";
			
			
			document.body.appendChild(_lald);
			
			var _loginAfLayoutDom = document.getElementById("_loginAfLayoutDom");
			
			_loginAfLayoutDom.innerHTML= str;
			
			return _loginAfLayoutDom;
			
			
		}
	}
	//隐藏层
	function HidLoginAfLayer(){
		var loginAfLayoutDom = document.getElementById("_loginAfLayoutDom");
		if(loginAfLayoutDom){
			loginAfLayoutDom.style.display="none";
		}
	}

