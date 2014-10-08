<#include "head.ftl">
<!--header E -->
<script src="${path!}/js/common.js.jsp"></script>
<script type="text/javascript" charset="utf-8" src="${path!}/kindeditor/kindeditor.js"></script>
	<script type="text/javascript">
			
		KE.show({
			id : 'content1',
			imageUploadJson : '${path!}/kdImgUpload.action',
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
<input type="hidden" name="resourceType" id="resourceType" value=""/>

    <@dataFunction function="forum" forumId="${parameter.forumId[0]}">
	<#list functionData.list as forum>
	<#assign forumContent="${forum.content}">
	<#assign forumTitle="${forum.title}">
    <#assign forumId="${(forum.id)?string.number}">
	</#list>
	</@dataFunction>
	
<div class="content clearfix">
	<!--ad01 E -->
    <div class="mypos fsong"> <a href="#">互动社区</a> &gt;发表新话题</div>
    <div class="col1 ask_and_sea fl found">
       	<h2 class="col-h3">发表新话题</h2>
		<form method="post" action="${path!}/forum/forum_saveOrUpdate.action" name="myform" onsubmit='return validateSave();'>
		<div class="red"><s:fielderror/></div>
		<ul class="link_gray ul-li-s2">			
		<li><label>标题:</label><input name="forum.title" type="text" class="inp_txt2" id='title' value='${forumTitle!}'/></li>			
		<li><label>话题类型:</label><select name="forum.board.id" id='boardId'>
		        <option value="0">请选择要发表的版区</option>
				<@dataFunction function="boards" state="1" pageSize="1000">
				<#assign bid="${parameter.boardId[0]}">
				  <#list functionData.list as board>				  
				     <option <#if bid=="${board.id}"> selected="selected"</#if> value="${board.id}">${board.boardname}</option>
				  </#list>
				</@dataFunction>
			</select></li>			
		<li><label>正文:</label><div class="art_zw">
		<textarea name="forum.content" id="content1" cols="100" rows="8" style="width:700px;height:500px;visibility:hidden;">${forumContent!}</textarea>
						</div></li>
						
		<input type="hidden" name="forum.isnew" id='isnew' value="1"/>
		<input type="hidden" name="forum.state" id='state' value="1"/>
		<input type="hidden" name="forum.id" id='id' value="${forumId!}"/>
					
		<li><input name="Submit1" type="submit" value="提 交" class="btn2" id='submit'/></li>
				
		</ul>
	   </form>  
    </div>

    <#include "link.ftl">
</div>    
<!--content E -->
<#include "foot.ftl">