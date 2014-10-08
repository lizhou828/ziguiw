function checkBind(str){
    $.ajax({
        url:"/userCenter/ajaxCheckBind.action",
        async:false,
        success:function(data){
            var ok=data['bind'];
            if(ok){
                window.location=str;
            }else{
                alert(data['bindMsg']);
                return  false;
            }
        }
    });

}