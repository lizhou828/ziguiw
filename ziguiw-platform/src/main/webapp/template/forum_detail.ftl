<#import "pager.ftl" as pager>
<#include "head.ftl">
<input type="hidden" name="resourceId" id="resourceId" value="${parameter.forumId[0]}"/>
<input type="hidden" name="resourceType" id="resourceType" value="forum"/> 
<!--header E -->
<script src="${path!}/js/common.js.jsp"></script>
<script src="${path!}/js/addClickCount.js.jsp"></script>
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

<div class="content">
    <@dataFunction function="forum" forumId="${parameter.forumId[0]}">
	<#list functionData.list as forum>
	<#assign boardId="${(forum.board.id)?string.number}">
	<#assign boardName="${forum.board.boardname}">
	<div class="map mt10"><label><img alt="" src="${path!}/images/bbs_map_bg _h.png" /></label><a href="${path!}/index">子贵首页</a> > <a href="${path!}/community/index">互动社区</a> > <a href="${path!}/forum/forumList.action?boardId=${boardId}">${boardName}</a> > ${forum.title}</div>
	<p class="bbs_ft">
	
	<a href="${path!}/page.action?templateName=createForum.ftl&boardId=${boardId}" target="_blank" class="l bbs_f">发帖</a><a href="#ai" class="bbs_h l">回复</a><a href="${path!}/forum/forumList.action?boardId=${boardId}" target="_blank" class="r">帖子列表</a>
	</p>
	<div class="bbs_xqing clearfix">
	
	<div class="bss_lou pr">
		<div class="bbs_lou_l">
			<ul>
			<li><a href="${path!}/myhome/${forum.creatorNick}" target="_blank" class="a-img2"><img alt="" src="${statics["com.zigui.utils.ImageUtils"].getSizedImage('${(forum.creatorPortrait)!}', '${path!}/images/head.jpg',70)}" width=100 height=100 ></a></li>
			<li><a href="${path!}/myhome/${forum.creatorNick}" target="_blank">${forum.creatorNick}</a></li>		
			</ul>
		</div>
		<div class="bbs_main">${forum.title}
			<h1><label class="r">浏览：<b class="m-r10">${forum.clickCount}</b>回复：<b>${forum.renum}</b></label></h1>
			<address>发表于：${forum.formatedStartTime}</address>
			<p>${forum.content}</p>
		</div>	   
	</div>
	</#list>
	</@dataFunction>
	<@dataFunction function="forumsByParentId" pageNo="${(parameter.pageNo[0])!1}" pageSize="10" forumId="${parameter.forumId[0]}">
	<#assign i=0>
	<#list functionData.list as forum>
	<div class="bss_lou pr">
				<div class="bbs_lou_l">
			<ul>
			<li><a href="${path!}/myhome/${forum.creatorNick}" target="_blank" class="a-img2"><img alt="" src="${statics["com.zigui.utils.ImageUtils"].getSizedImage('${(forum.creatorPortrait)!}', '${path!}/images/head.jpg',70)}" width=100 height=100 ></a></li>
			<li><a href="${path!}/myhome/${forum.creatorNick}" target="_blank">${forum.creatorNick}</a></li>		
			</ul>
		</div>
		<div class="bbs_main">
		    <#assign i=i+1>
		    <h1><label class="r">${i+functionData.startIndex}楼</label></h1>
			<address>发表于：${forum.formatedStartTime}</address>
			<p>${forum.content}</p>	
		</div>	   
	</div>
	</#list>
	<#assign pageSize="${(functionData.pageSize)?string.number}">
	<#if pageSize!="0">
	<div class="pagenum"><@pager.p page="${(parameter.pageNo[0])!1}" totalpage="${(functionData.totalCount)/((functionData.pageSize)!1)+1}" params='templateName=forum_detail.ftl&forumId=${parameter.forumId[0]}'/></div>
	</#if>	
	</@dataFunction>
	
	   
	<p class="bbs_ft">
	<a href="${path!}/page.action?templateName=createForum.ftl&boardId=${boardId}" target="_blank" class="l bbs_f">发帖</a><a href="#ai" class="bbs_h l">回复</a><a href="${path!}/forum/forumList.action?boardId=${boardId}" target="_blank" class="r">帖子列表</a>
	</p>		



	<div class="bss_lou pr">

		<div class="bbs_main">
			<form method="post" action="${path!}/forum/forum_saveOrUpdate.action" name="myform" onsubmit='return validateSave();'>
			    <a name="ai"></a>
				<div class="red"><s:fielderror/></div>	
			<p>
			<textarea name="forum.content" id="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"></textarea>
			<br>
			<input type="submit" value="" class="inp_sum3">
			</p>
			<input type="hidden" name="forum.isnew" value="0" id='isnew'/>
		    <input type="hidden" name="forum.state" value="1" id='state'/>
		    <input type="hidden" name="forum.parentForum.id" value="${parameter.forumId[0]}"/>
		    <input type="hidden" name="forum.board.id" value="${boardId}" id='boardId'/>	
			</form>
   </div>
	</div>	


	
	</div>
    <#include "link.ftl">
</div>    
<!--content E -->
<#include "foot.ftl">