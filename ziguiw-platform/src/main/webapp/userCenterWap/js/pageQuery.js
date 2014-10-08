function changePage(){
    var pageNum =document.getElementById("inputPage").value;
    var maxPage=document.getElementById("maxPage").value;
    if (pageNum.length==0||isNaN(pageNum)){
        alert('非法的页码');
        return false;
    }
    if(maxPage==0||maxPage==''||maxPage==null){
        alert('无记录可以查询');
        return false;
    }
    if (1<=pageNum && pageNum <=maxPage){

        document.getElementById("gotoPage").setAttribute('value',pageNum);
        document.forms[0].submit()

    }
    if(pageNum>maxPage){

        document.getElementById("gotoPage").setAttribute('value',maxPage);
        document.forms[0].submit()
    }
    if(pageNum<1){

        document.getElementById("gotoPage").setAttribute('value','1');
        document.forms[0].submit()
    }
    return true;
}