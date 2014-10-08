//级联下拉列表
//老师根据班级id查询学生
function queryStudentsByClassId(bjId){
        if(bjId && bjId != null){
                var sel=document.getElementById("xsId");
                $.ajax({
                    url:"/Teachers/queryStudentsByClassId",
                    data:{"bjId":bjId},
                    type:'get',
                    dataType:"json",
                    success:function(data){
                        sel.innerHTML='';
                        if( data == null || data.length==0){
                            sel.options.add(new Option("--无学生--",'-1'));
                        }else{
                            sel.options.add(new Option("--请选择--",'-1'));
                            for(var i= 0;i< data.length;i++){
                                var option=new Option(data[i]['xsXming'],data[i]['xsId']);
                                sel.options.add(option);
                            }
                        }
                    },
                    error:function(){
                       alert("出错啦!!");
                    }
                });

        }
}
//老师根据班级id查询学期
function queryTermByClazzId(bjId){
    var sel=document.getElementById("termId");
    var bjId= document.getElementById("bjId").value;
    if( !bjId || bjId == null || bjId == '-1'){
        alert("请选定班级！");
        return false;
    }
    $.ajax({
        url:"/Clazzs/findTermByClazz",
        data:{"bjId":bjId},
        type:'get',
        dataType:"json",
        success:function(data){
            sel.innerHTML='';
            if( data == null || data.length == 0 ){
                sel.options.add(new Option("--无信息--",'-1'));
            }else{
                sel.options.add(new Option("--请选择--",'-1'));
                for(var i= 0;i< data.length;i++){
                    var option=new Option(data[i]['termName'],data[i]['termId']);
                    sel.options.add(option);
                }
            }

        },
        error:function(){
            alert("出错啦!!");
        }
    });

}
//查询考试信息
 function queryExamInfo(termId){
     var sel=document.getElementById("examId");
     var bjId= document.getElementById("bjId").value;
     var termId=termId;
     if( !bjId || bjId == null || bjId == '-1'){
         alert("请选定班级！");
         return false;
     }
     if( !termId || termId == null || termId == '-1'){
         alert("请选定学期！");
         return false;
     }
     $.ajax({
         url:"/TermSets/findExam",
         data:{"bjId":bjId,"termId":termId},
         type:'get',
         dataType:"json",
         success:function(data){
             sel.innerHTML='';
             if( data == null || data.length == 0 ){
                 sel.options.add(new Option("--请选择--",'-1'));
             }else{
                 sel.options.add(new Option("--请选择--",'-1'));
                 for(var i= 0;i< data.length;i++){
                     var option=new Option(data[i]['examName'],data[i]['examId']);
                     sel.options.add(option);
                 }
             }
         },
         error:function(){
             alert("出错啦!!");
         }
     });
 }

//(有多个孩子的)家长根据学生id查询学期信息
function queryTermByXsId(xsId){
     if(xsId && xsId != null && xsId != '-1'){
         var sel=document.getElementById("termId");
         $.ajax({
             url:"/TermSets/findTermByXsId",
             data:{"xsId":xsId},
             type:'get',
             dataType:"json",
             success:function(data){
                 sel.innerHTML='';
                 if( data == null || data.length == 0 ){
                     sel.options.add(new Option("--无信息--",'-1'));
                 }else{
                     sel.options.add(new Option("--请选择--",'-1'));
                     for(var i= 0;i< data.length;i++){
                         var option=new Option(data[i]['termName'],data[i]['termId']);
                         sel.options.add(option);
                     }
                 }
             },
             error:function(){
                 alert("出错啦!!");
             }
         });
     }else{
         return false;
     }
}