<%
    String path = request.getContextPath();
%>
$(function(){
    $("#wapLogin").click(
        function(){
            $.ajax({
                type : "post",
                url : "/userCenterWap/wapAjaxLogin.action",
                dataType : 'text',
                data : '&user.nickName=' + encodeURIComponent($('#username').attr("value")) + '&user.password='+ $("#password").attr("value") + '&saveUserInCookie=' + $("#saveUser").attr("checked"),
                success : function(errorMsg) {
                    errorMsg=errorMsg.substr(1,errorMsg.length - 2);
                    if(errorMsg == 'success1'){
                        window.location.href="<%=path%>/school/school_manager.jsp";
                    }else if(errorMsg == 'success2'){
                        window.location.href="<%=path%>/userCenterWap/index.action";
                    }else if(errorMsg == 'success3'){
                        window.location.href="<%=path%>/user/portal.jsp";
                    }else{
                        $("#errorMsg").html(errorMsg);
                    }
                }
            });
        });
});