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
        	<form method="post" action="<%=path%>/info/homeWork_saveOrUpdate.action">
            <fieldset class="formStyle2 clearfix" style="">
                <legend>添加/编辑作业</legend>      
                <ul>
                	<li>
                	<div class="label">班级：</div><div class="field">
                		<s:action var="getClasz" name="baseData_getClaszByTeacher" namespace="/info" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="20"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="user.foreignKey"><%=hostUser.getForeignKey()%></s:param>
				        </s:action>
				        <select name='homeWork.classId' id="classSelector" class="field-200">
						 <option selected="selected" value="0">请选择班级</option>
						 <s:iterator value="#getClasz.pageObj.list" var="clasz">
							       <option value="<s:property value="#clasz.clasz.Bj_id"/>"><s:property value="#clasz.clasz.Bj_mcheng"/></option>
						</s:iterator>
							    
						</select>
						</li>
            		
            		
                	<li>
                		<div class="label">内容：</div><div class="field "><textarea class="field-400" rows="4" name="homeWork.content">${homeWork.content}</textarea></div>
                	</li>
            	</ul>
            	<input type="hidden" name="homeWork.state" value="1"/>
                <input type="hidden" name='homeWork.id' value="${homeWork.id}"/>
                <div class="formBtnBar"><input name="reset" id="reset" type="reset" value="取 消"  class="inp_btn"/> <input name="Submit1" id="Submit1" type="submit" value="提 交" /></div>
            </fieldset>
            </form>
        <!--表单样式2-->
            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>