<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=path%>/kindeditor/kindeditor.js"></script>
	<script type="text/javascript">
			
		KE.show({
			id : 'content1',
			imageUploadJson : '<%=path%>/kdImgUpload.action',
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
<div class="w_home_content">
  <div class="w_home_content_box clearfix">
<div class="content clearfix">
	<!--ad01 E -->
    <div class="mypos fsong"> <a href="#">我的小家</a> &gt;创建日志</div>
    <div class="col1 ask_and_sea fl found">
       	<h2 class="col-h3">创建日志</h2>
		<form method="post"  action="<%=path%>/user/diary_save.action" name="form" >
		<div class="red"><s:fielderror/></div>
		<ul class="link_gray ul-li-s2">			
		<li><label>日志标题:</label><input name="diary.title" type="text" class="inp_txt" value="<s:property value="diary.title"/>"/></li>			
		<li><label>日志正文:</label><div class="art_zw">
						<textarea name="diary.text" cols="100" rows="8" style="width:700px;height:500px;visibility:hidden;" id="content1"><s:property value="diary.text"/></textarea></div></li>			
		
		<input type="hidden" name="diary.id" value="<s:property value="diary.id"/>">
		<li><input name="Submit1" type="submit" value="提 交" class="btn2" onclick="getEditorContent()"/></li>
				
		</ul>
		</form>  
    </div>
    
</div>   
</div>
</div> 
<!--content E -->

<script src="<%=path%>/js/json-min.js"></script>
<%@ include file="../inc/templateFoot.jsp"%>