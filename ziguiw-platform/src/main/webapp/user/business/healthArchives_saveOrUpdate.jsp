<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>
<link href="<%=path%>/css/info.css" rel="stylesheet"/>

<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="../inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">用户中心</h2><div class="title-bottom">.</div>
		
        <!--列表样式3-->
       
       
        <!--表单样式2-->
        	<form method="post" action="<%=path%>/info/healthArchives_saveOrUpdate.action">
            <fieldset class="formStyle2 clearfix" style="">
                <legend>添加/编辑通用健康档案</legend>      
                <ul>
            		
                		<li>
                		<div class="label">视力：</div><div class="field "><input class="field-200" type="text" value="${healthArchives.vision}" name="healthArchives.vision"/></div>
                	</li>
                		<li>
                		<div class="label">听力：</div><div class="field "><input class="field-200" type="text" value="${healthArchives.listeningComprehension}" name="healthArchives.listeningComprehension"/></div>
                	</li>
                		<li>
                		<div class="label">身高：</div><div class="field "><input class="field-200" type="text" value="${healthArchives.height}" name="healthArchives.height"/></div>
                	</li>
                		<li>
                		<div class="label">体重：</div><div class="field "><input class="field-200" type="text" value="${healthArchives.weight}" name="healthArchives.weight"/></div>
                	</li>
                	
                	
            	
            	</ul>
            	<input type="hidden" name="healthArchives.state" value="1"/>
                <input type="hidden" name='healthArchives.id' value="${healthArchives.id}"/>
                  <s:if test='healthArchives==null||healthArchives.id==0'><input type="hidden" name='healthArchives.userId' value="<s:property value="#parameters.sid"/>"/></s:if>                
                <s:else><input type="hidden" name='healthArchives.userId' value="${healthArchives.userId}"/></s:else>
               
            </fieldset>
             <div class="formBtnBar formBtnBar-noBorder"><input name="reset" id="reset" type="reset" value="取 消"  class="inp_btn actionBtn5"/> <input name="Submit1" id="Submit1" type="submit" value="提 交" class="actionBtn7" /></div>
            </form>
        <!--表单样式2-->
            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>