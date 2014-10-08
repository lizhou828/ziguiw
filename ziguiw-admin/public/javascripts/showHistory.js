/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-28
 * Time: P.M..4:32
 */

function showHistory(obj){
    alert(obj);
    $.get("/AnnualFees/findByXsId",{xsId:obj},function(data){
        for(var i=0; i<data.length; i++){

            alert(data[i].id+","+data[i].orderNo+","+data[i].payMethod+","+data[i].state+","+data[i].type+","+data[i].student.xsXming);
        } ;

    });
}
