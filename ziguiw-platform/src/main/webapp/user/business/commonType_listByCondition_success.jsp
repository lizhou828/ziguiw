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

        
        <!--列表样式2-->
         	<table class="listStyle2" cellpadding="0" cellspacing="0" border="0" >
            	<thead>
                    <tr>
                    <th>类别名称</th>
                    <th>类别信息</th>
                    <th>类别分类</th>
                    <th>管理</th>
                    </tr>
                </thead>
                <tbody>
                    
                     <s:action var="commonTypes" name="commonType_listByCondition" namespace="/info" executeResult="false" ignoreContextParams="false">
				      <s:param name="countPerPage" value="20"></s:param>
				      <s:param name="query.state" value="1"></s:param>
			        </s:action>
			        <s:iterator value="#commonTypes.commonTypes.list" var="commonType">
			              <tr>
                    <td><s:property value="#commonType.name"/></td>
                    <td><s:property value="#commonType.info"/></td>
                    <td><s:property value="#commonType.type"/></td>
                    <td>
                    <a href="<%=path%>/info/commonType_getById.action?commonTypeId=<s:property value="#commonType.id"/>">修改</a>
			        | <a href="<%=path%>/info/commonType_fakeDelete.action?opIds=<s:property value="#commonType.id"/>">删除</a>
                    </td>
                    </tr>
			        </s:iterator>			                     
                </tbody>
    </table>
    <s:if test="#commonTypes.commonTypes.pageString != null && #commonTypes.commonTypes.pageString != ''">
			<div class="pagenum">
			<s:property value="#commonTypes.commonTypes.pageString" escape="false"/>
			</div>
    </s:if>	
        <p>&nbsp;</p>
        <p>             
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>