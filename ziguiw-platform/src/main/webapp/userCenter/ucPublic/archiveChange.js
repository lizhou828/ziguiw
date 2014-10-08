$(function(){
    $("#quitSchool").click(function(){     //退学
        var _xs_ids=new Array();
        $("#dangan_tab :checkbox").each(function () {
            if($(this).attr("checked")==true){
                _xs_ids.push($(this).val());
            }
        });
        _xs_ids=_xs_ids.join("|");
        if(_xs_ids.length==0){
            alert("未选定学生");
        }
        alert("你确定修改");
        if(_xs_ids.length != 0 ){
            $.ajax({
                type: 'post',
                url:"/userCenter/changeSchoolStatus.action",
                data:{"xsIds":_xs_ids,"status":"2"},
                success:function(){

                }
            });
        }
        window.location.reload();
    });
});