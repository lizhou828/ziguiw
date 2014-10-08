<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>


<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="../inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">信息中心</h2><div class="title-bottom">.</div>
		<!--列表样式1-->
		 <div class="formBtnBar formBtnBar-bottom" style="margin-right:0px"><a href="<%=path%>/info/healthArchives_getBaseById.action"><input type="button" onclick="window.location.href='<%=path%>/info/healthArchives_getBaseById.action';" class="cx" value="修改"/></a></div>
		<!--列表样式1-->
		<s:action var="healthArchivess1" name="healthArchives_getBaseById" namespace="/info" executeResult="false" ignoreContextParams="false">
	      <s:param name="countPerPage" value="1"></s:param>
	      <s:param name="query.state" value="1"></s:param>
        </s:action>
		<table class="listStyle1" cellpadding="2" cellspacing="2" border="0" >
            	<tr>
                	<td class="field1 datetime">血型</td>
                  <td class="light"><s:property value="#healthArchivess1.baseHealth.bloodType"/></td>
                </tr>
                <tr>
                	<td class="field1 datetime">药物过敏史</td>
                  <td class="light"><s:property value="#healthArchivess1.baseHealth.drugAllergyHistory"/></td>
                </tr>
                <tr>
                	<td class="field1 datetime">地方病史</td>
                  <td class="light"><s:property value="#healthArchivess1.baseHealth.localHistory"/></td>
                </tr>
                <tr>
                	<td class="field1 datetime">既往病史</td>
                  <td class="light"><s:property value="#healthArchivess1.baseHealth.medicalHistory"/></td>
                </tr>
                <tr>
                	<td class="field1 datetime">遗传病史</td>
                  <td class="light"><s:property value="#healthArchivess1.baseHealth.geneticHistory"/></td>
                </tr>
        </table>
		
		
       <div class="formBtnBar formBtnBar-bottom" style="margin-right:0px"><a href="<%=path%>/user/business/healthArchives_saveOrUpdate.jsp"><input type="button" onclick="window.location.href='<%=path%>/user/business/healthArchives_saveOrUpdate.jsp';" class="gray" value="新增"></input></a></div>
        <!--列表样式2-->
         	<table class="listStyle2" cellpadding="0" cellspacing="0" border="0" >
            	<thead>
                    <tr>
                    <th width="100px">时间</th>
                    <th width="80px">视力</th>
                    <th width="80px">听力</th>
                    <th width="80px">身高</th>
                    <th width="80px">体重</th>
                    <th width="80px">管理</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <s:action var="healthArchivess" name="healthArchives_listByCondition" namespace="/info" executeResult="false" ignoreContextParams="false">
				      <s:param name="countPerPage" value="100"></s:param>
				      <s:param name="query.state" value="1"></s:param>
			        </s:action>
			        <s:iterator value="#healthArchivess.healthArchivess.list" var="healthArchives">
			              <tr>
                    <td><s:property value="#healthArchives.formatedCreateDate"/></td>
                    <td><s:property value="#healthArchives.vision"/></td>
                    <td><s:property value="#healthArchives.listeningComprehension"/></td>
                    <td><s:property value="#healthArchives.height"/></td>
                    <td><s:property value="#healthArchives.weight"/></td>
             
                    <td>
                    <a href="<%=path%>/info/healthArchives_getById.action?healthArchives.id=<s:property value="#healthArchives.id"/>">修改</a>
                    </td>
                    </tr>
			        </s:iterator>			                     
                </tbody>
    </table>
    <s:if test="#healthArchivess.healthArchivess.pageString != null && #healthArchivess.healthArchivess.pageString != ''">
			<div class="pagenum">
			<s:property value="#healthArchivess.healthArchivess.pageString" escape="false"/>
			</div>
    </s:if>            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>