<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=path%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript">
			
		KE.show({
			id : 'content1',
			imageUploadJson : '/ziguiw/kdImgUpload.action',
			allowFileManager : false,
				items : [
				'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
			afterCreate : function(id) {
				KE.event.ctrl(document, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
				KE.event.ctrl(KE.g[id].iframeDoc, 13, function() {
					KE.util.setData(id);
					document.forms['example'].submit();
				});
			}
		});
</script>
<style type="text/css">
.ul-li-s2{line-height:24px;padding-left:120px; color:#666;}
.ul-li-s2 li{padding:8px 0; overflow:visible}
.ul-li-s2 label{float:left;width:110px;margin-left:-120px; text-align:right}
.ul-li-s2 .inp_txt{height:22px;line-height:22px;width:220px;padding:0 2px;}
.found .ul-li-s2 label {
    color: #333333;
    font-size: 14px;
    font-weight: bold;
}
</style>


<div class="content clearfix pr">
	<%@ include file="inc/school_left.jsp"%>
	<!--ad01 E -->
	<form action="<%=path%>/user/job_save.action" method="post">
    <div class="ask_and_sea found w-760 hei-700  r">
       	<h2 class="col-h3">招聘信息</h2>

		<div class="hei-400">
		<div class="art_zw">
					<textarea name="schoolInfo.job" cols="100" rows="8" style="width:700px;height:500px;" id="content1"><s:property value="schoolInfo.job" escape="false"/></textarea>
			</div>
		</div>
		
		<input type="submit" class="btn2 l mt5" value="保存">
    </div>
    </form>
  
</div>    
<!--content E -->
<script type="text/javascript">
function initSelect(){
	var sex = document.getElementById("sex");
	var province = document.getElementById("province");

	var oldSex = document.getElementById("oldSex").value;
	var oldProvince = document.getElementById("oldProvince").value;
	
	
    for(var i=0;i<sex.options.length;i++)
    {
        if(sex.options[i].value == oldSex)
        {
        	sex.options[i].selected = true;
            break;
        }
    }
    
    for(var i=0;i<province.options.length;i++)
    {
        if(province.options[i].value == oldProvince && oldProvince != "")
        {
        	province.options[i].selected = true;
        	break;
        }
    }
    
}

initSelect();
</script>

<%@ include file="../inc/templateFoot.jsp"%>