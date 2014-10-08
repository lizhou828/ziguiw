<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/info_head.jsp"%>


<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">信息中心</h2><div class="title-bottom">.</div>
		
        <!--列表样式2-->
         	<table class="listStyle2" cellpadding="0" cellspacing="0" border="0" >
            	<thead>
                    <tr>
                    <th width="150px">时间</th>               
                    <th width="550px">内容</th>
                    </tr>
                </thead>
                <tbody>
                    
               
			        <s:iterator value="datas.list" var="commonMessage">
			              <tr>
<s:bean name="java.util.Date" var="pTime">
<s:param name="time"><s:property value="#commonMessage.PUB_DATE"/></s:param>
</s:bean>
			        <td><s:date name="pTime" format="yyyy-MM-dd HH:dd:ss"></s:date></td>
                    <td><s:property value="#commonMessage.MSG_CONTENT"/></td>
                    
                    </tr>
			        </s:iterator>			                     
                </tbody>
    </table>
    <s:if test="datas.pageString != null && datas.pageString != ''">
		<div class="pagenum">
			<s:property value="datas.pageString" escape="false"/>
		</div>
		</s:if>
        <p>&nbsp;</p>
        <p>             
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../inc/templateFoot.jsp"%>