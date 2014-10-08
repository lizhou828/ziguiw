//查询学期信息
function queryTerm(BJid) {
    var sel=document.getElementById("termId");
    $.ajax({
        url:"/userCenter/queryTermInfo.action",
        data:{"bjid":BJid},
        async:false,
        success:function(data){
            sel.innerHTML='';
            sel.options.add(new Option("--请选择--",'-1'));
            if(data != null && data.length != 0 ){
                for(var i=0;i<data.length;i++){
                    var option=new Option(data[i]["termName"],data[i]["termId"]);
                    sel.options.add(option);
                }
            }
            for(var i=0;i<sel.options.length;i++){
                if (_xqId && _xqId != ''&& _xqId == sel.options[i].value) {
                    sel.option[i].selected = true;
                    queryExam(_xqId);
                    _xqId = '';
                }
            }
        }
    });
}
//查询考试信息
function queryExam(TermId){
    var bjid=document.getElementById("classId").value;
    var termid=document.getElementById("termId").value;
    var sel=document.getElementById("examId");
    if(termid == '-1'|| termid=='undefined' ){
        sel.innerHTML='';
        sel.options.add(new Option("--无信息--",'-1'));
    }else if(termid != '-1' && termid !='undefined'){
        $.ajax({
            url:"/userCenter/queryExam.action",
            data:{"termId":TermId,"bjid":bjid},
            async:false,
            success:function(data){
                sel.innerHTML='';
                sel.options.add(new Option("--请选择--",'-1'));
                if(data != null && data.length != 0 ){
                    for(var i= 0;i< data.length;i++){
                        var option=new Option(data[i]["EXAM_NAME"],data[i]["EXAM_id"]);
                        sel.options.add(option);
                    }
                }
                for(var i=0;i<sel.options.length;i++){
                    if (_ksId && _ksId != '' && _ksId == sel.options[i].value) {
                        sel.options[i].selected = true;
                        _ksId = '';
                    }
                }
            }
        });
    }
}

function queryStudentsByClassId(classId){
    if(classId != '-1'){
        var sel=document.getElementById("studentId");
        $.ajax({
            url:"/userCenter/queryStudentsByClassId.action",
            data:{"bjid":classId},
            async:false,
            success:function(data){
                sel.innerHTML='';
                if(data.length==0){
                    sel.options.add(new Option("--无学生--",'-1'));
                }else{
                    sel.options.add(new Option("--请选择--",'-1'));
                    for(var i= 0;i< data.length;i++){
                        var option=new Option(data[i][1],data[i][0]);
                        sel.options.add(option);
                    }
                    for(var i= 0;i<sel.options.length ;i++){
                        if (_xsId && _xsId != '' &&   _xsId == sel.options[i].value) {
                            sel.options[i].selected = true;
                            _xsId = '';
                        }
                    }
                }

            }
        });
    }
}