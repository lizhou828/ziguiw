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
        	<form method="post" action="<%=path%>/info/healthArchives_saveOrUpdateBase.action">
            <fieldset class="formStyle2 clearfix" style="">
                <legend>添加/编辑通用健康档案</legend>      
                <ul>
            		<li>
                		<div class="label">血型：</div><div class="field "><input class="field-200" type="text" value="${baseHealth.bloodType}" name="baseHealth.bloodType"/></div>
                	</li>
                		<li>
                		<div class="label">药物过敏史：</div><div class="field "><input class="field-200" type="text" value="${baseHealth.drugAllergyHistory}" name="baseHealth.drugAllergyHistory"/></div>
                	</li>
                
                	<li>
                		<div class="label">地方病史：</div><div class="field ">
                		<textarea class="field-400" rows="4" name="baseHealth.localHistory" type="text">${baseHealth.localHistory}</textarea>
                		</div>
                	</li>
                	<li>
                		<div class="label">既往病史：</div><div class="field ">
                		<textarea class="field-400" rows="4" name="baseHealth.medicalHistory" type="text">${baseHealth.medicalHistory}</textarea>
                		</div>
                	</li>
                	<li>
                		<div class="label">遗传病史：</div><div class="field ">
                		<textarea class="field-400" rows="4" name="baseHealth.geneticHistory" type="text">${baseHealth.geneticHistory}</textarea>
                		</div>
                	</li>
                	
            	
            	</ul>
            	<input type="hidden" name="baseHealth.state" value="1"/>
                <input type="hidden" name='baseHealth.id' value="${baseHealth.id}"/>
                  <s:if test='baseHealth==null||baseHealth.id==0'><input type="hidden" name='baseHealth.userId' value="<s:property value="#parameters.sid"/>"/></s:if>                
                <s:else><input type="hidden" name='baseHealth.userId' value="${baseHealth.userId}"/></s:else>
               
            </fieldset>
             <div class="formBtnBar formBtnBar-noBorder"><input name="reset" id="reset" type="reset" value="取 消"  class="inp_btn actionBtn5"/> <input name="Submit1" id="Submit1" type="submit" value="提 交" class="actionBtn7" /></div>
            </form>
        <!--表单样式2-->
            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>