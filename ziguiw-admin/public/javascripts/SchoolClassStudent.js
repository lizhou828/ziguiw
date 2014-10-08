function getClasses(obj){
    var xxId = obj.value;
    $("#xsId").empty();
    $("#bjId").empty();
    $.get("/Schools/clazz", {xxId : xxId}, function (data){
        if(data.length == 0){
            $("#bjId").append("<option value='" + -1 +"'>" + "--无--" + "</option>");
        } else{
            $("#bjId").append("<option value='" + 0 +"'>" + "-请选择-" + "</option>");
            for (var i = 0; i < data.length; i++) {
                $("#bjId").append("<option value='" + data[i].id +"'>" + data[i].name + "</option>");
            }
        }
    });

}
function getStudent(obj){
    $("#xsId").empty();
    var bjId = obj.value;
    $.get("/Clazzs/student", {bjId : bjId}, function (data){
        if(data.length == 0 ){
            $("#xsId").append("<option value='" + -1 +"'>" + "--无--" + "</option>");
        }else{
            $("#xsId").append("<option value='" + 0 +"'>" + "--全班学生--" + "</option>");
        }
        for (var i = 0; i < data.length; i++) {
            $("#xsId").append("<option value='" + data[i].xsId +"'>" + data[i].xsXming+ "</option>");
        }
    });

}