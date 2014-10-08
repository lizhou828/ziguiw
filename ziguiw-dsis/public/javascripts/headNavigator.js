/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-30
 * Time: P.M.3:51
 */
function myPoint(){
    $.ajax({
        type:"GET",
        url:"/PointsHistories/index",
        beforeSend : function(){
            $("#uc_midd").html("<img src='/public/images/loading.gif'/>");
        },
        success : function(result){
            $("#uc_midd").html(result);
        },
        error : function(){
            alert("出错啦！");
        }
    });
    return false;
}

function mySource(url){
    $.ajax({
        type:"GET",
        url:url,
        beforeSend : function(){
            $("#uc_midd").html("<img src='/public/images/loading.gif'/>");
        },
        success : function(result){
            $("#uc_midd").html(result);
        },
        error : function(){
            alert("出错啦！");
        }
    });
    return false;
}