<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>
<link href="<%=path%>/css/info.css" rel="stylesheet"/>
<link href="<%=path%>/css/base.css" rel="stylesheet"/>
<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="../inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">用户中心</h2><div class="title-bottom">.</div>
		
        <!--列表样式3-->
       
       
        <!--表单样式2-->
        	<form method="post" action="<%=path%>/info/commonType_saveOrUpdate.action">
            <fieldset class="formStyle2 clearfix" style="">
                <legend>添加/编辑通用分类</legend>      
                <ul>
            		<li>
                		<div class="label">类别名称：</div><div class="field"><input class="field-200" type="text" value="${commonType.name}" name="commonType.name"/></div>
                	</li>
            		<li>
                		<div class="label">类别信息：</div><div class="field"><input class="field-400" type="text" value="${commonType.info}" name="commonType.info"/></div>
                	</li>
            		<li>
                		<div class="label">类别分类：</div><div class="field">
                		<select name='commonType.type' id="classType" class="field-200">
						    <option selected="selected" value="1">科目</option>
						    <option value="2">作业类别</option>
						    <option value="3">留言类别</option>
						    <option value="4">班级通知类别</option>
						    <option value="5">学校公告类别</option>
						    <option value="6">职位</option>				    
						</select>
                		</div>
                	</li>
            	</ul>
            	<input type="hidden" name="commonType.state" value="1"/>
                <input type="hidden" name='commonType.id' value="${commonType.id}"/>
                <div class="formBtnBar formBtnBar-bottom"><input name="reset" id="reset" type="reset" value="取 消"/> <input name="Submit1" id="Submit1" type="submit" value="提 交" /></div>
            </fieldset>
            </form>
        <!--表单样式2-->
            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>