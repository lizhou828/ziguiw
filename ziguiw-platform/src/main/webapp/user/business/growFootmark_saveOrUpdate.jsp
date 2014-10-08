<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>
<link href="<%=path%>/css/info.css" rel="stylesheet"/>
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

<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="../inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">用户中心</h2><div class="title-bottom">.</div>
		
        <!--列表样式3-->
       
       
        <!--表单样式2-->
        	<form method="post" action="<%=path%>/info/growFootmark_saveOrUpdate.action">
            <fieldset class="formStyle2 clearfix" style="">
                <legend>添加/编辑成长足迹</legend>      
                <ul>
            		<li>
                		<div class="label">标题：</div><div class="field"><input class="field-200" type="text" value="${growFootmark.title}" name="growFootmark.title"/></div>
                	</li>
                    <li><div class="label">正文：</div><div class="field">
						<textarea name="growFootmark.text" cols="80" rows="8" style="width:550px;height:400px;visibility:hidden;" id="content1"><s:property value="growFootmark.text"/></textarea></div>
					</li>

            	</ul>
            	<input type="hidden" name="growFootmark.state" value="1"/>
            	<s:if test='growFootmark != null && growFootmark.id != 0'>
                	<input type="hidden" name='growFootmark.id' value="${growFootmark.id}"/>
                </s:if>

            </fieldset>
                            <div class="formBtnBar formBtnBar-noBorder"><input name="reset" id="reset" type="reset" value="取 消"  class="inp_btn actionBtn5"/> <input name="Submit1" id="Submit1" type="submit" value="提 交" class="actionBtn7" /></div>
            </form>
        <!--表单样式2-->
            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>