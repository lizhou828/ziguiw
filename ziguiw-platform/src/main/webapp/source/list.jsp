<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>

<%--<%@ taglib prefix="s" uri= "/struts-tags" %>
<%@ taglib prefix="t" uri="/ttxs-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>--%>

<%@include file="/comm/common_tag.jsp" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
      
      <title>${selstr}_子贵网</title>
      <meta name="Keywords" content="教育资源,教学课件,考试试卷,教学视频">
      <meta name="Description" content="为全国高中小学各学科教师提供展示教学、实现教育资源交易的平台。
      打破教室、教师、校园的界限，实现校校互通、班班互联、资源共享。栏目针对高中小院校的各科目及各年级、教材版本的不同进行资源的分类展示。">
      <link href="${ctx}/source/css.css" rel="stylesheet" type="text/css" />
      <style type="text/css">
        <!--
        .STYLE1 {
            color: #FF3300;
            font-weight: bold;
        }
        .STYLE2 {color: #666666}
        -->
      </style>
  </head>

<body>

	<div class="main">
	<jsp:include page="source_top.jsp"></jsp:include>

		<div class="t-sou-lie">
			<p class="text-1-3"><span class="text-1-4">${selstr }教学资源 </span>&nbsp;&nbsp;&nbsp;&nbsp;
			您的位置：子贵网&nbsp;&gt;&nbsp;<a href="${ctx}/source/sourceInfo.action">教育资源</a>&nbsp;&gt;&nbsp;
			<a href="${ctx}/source/sourcePageList.action?seltj=${ seltj }&selstr=${ selstr}">${ selstr }</a>
			</p>
	  </div>
	    <div class="t-neirong-01">
	     <form action="${ctx}/source/sourcePageList.action" id="nextPage" method="post">
   		<div class="t-neirong-liebiao-z">
			<ul>
                <s:iterator value="sourceList" status="vs" var="rc">
                <!--  -->


               				<li>             								
               				
               				<a href="${ctx}/source/sourcePageList.action?seltj=2&selstr=<s:property value="#rc.souceStugrade.njMcheng" />  "><span class="STYLE1"><s:property value="#rc.souceStugrade.njMcheng" /></span></a>&nbsp;
               				<a href="${ctx}/source/sourcePageList.action?seltj=3&selstr=<s:property value="#rc.souceSubject.subjectName" />"><span style="color: #000000;"><strong>[<s:property value="#rc.souceSubject.subjectName" />]</strong></span></a>&nbsp;
               				<a href="${ctx}/source/sourcePageList.action?seltj=4&selstr=<s:property value="#rc.sourceType.typeName" />"><span class="STYLE2">[<s:property value="#rc.sourceType.typeName" />]</span></a>&nbsp;

                            <s:if test="#rc.sourceTypes==1">

                                <a href="${ctx}/source/sourceGet.action?date=<s:property value="#rc.createDate"/>&ftpFile=<s:property value="#rc.ftpFileLocal"/>">
                                    <s:if test="%{#rc.title!=null&&#rc.title.length()>8}">
                                        <s:property value="#rc.title.substring(0, 8)+'...'" />
                                    </s:if>
                                    <s:else>
                                        <s:property value="#rc.title" />
                                    </s:else>
                                </a>

                            </s:if>
                            <s:else>
                            <a href="${ctx}/source/sourceGet.action?id=${rc.subjectId}"  title="${rc.title}" target="_blank">
               				<c:choose>
				          		<c:when test="${fn:length(rc.title)<36}">${rc.title}</c:when>
				          		<c:otherwise>${fn:substring(rc.title,0,36)}...</c:otherwise> 
				          		</c:choose>
               				
               				</a>
                            </s:else>
               					</li>
                </s:iterator>
                <!--  -->


				</ul>				
				<input type="hidden" name="seltj" value="${seltj }"/>
				<input type="hidden" name="selstr" value="${selstr }"/>
					<t:tpage page="${result.page}" formId="nextPage"></t:tpage>					
		</div>
		</form>
			<div class="t-neirong-01-y">
				<div class="t-neirong-01-y-biao">&nbsp;最新推荐</div>
				<div class="t-neirong-01-y-nei">
					<ul> 
						<s:iterator id="zuixin" value="zuixinList"> 
                            <li>

                                <s:if test="#zuixin.sourceTypes==1">

                                    <a href="${ctx}/source/sourceGet.action?date=<s:property value="#zuixin.createDate"/>&ftpFile=<s:property value="#zuixin.ftpFileLocal"/>">
                                        <s:if test="%{#zuixin.title!=null&&#zuixin.title.length()>8}">
                                            <s:property value="#zuixin.title.substring(0, 8)+'...'" />
                                        </s:if>
                                        <s:else>
                                            <s:property value="#zuixin.title" />
                                        </s:else>
                                    </a>
                                </s:if>
                                <s:else>
                                    <a href="<s:if test="#zuixin.netPath!=null"><s:property value="#zuixin.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#zuixin.subjectId" /></s:else>"  title="  <s:property value="#zuixin.title" />" target="_blank">
                                        <s:if test="%{#zuixin.title!=null&&#zuixin.title.length()>14}">
                                                                                    <s:property value="#zuixin.title.substring(0, 14)+'...'" />
                                                                                 </s:if>
                                                                                 <s:else>
                                                                                   <s:property value="#zuixin.title" />
                                                                                 </s:else>
                                    </a>
                                </s:else>
                            </li>
						</s:iterator>
					</ul>
				</div>
				<div class="t-neirong-01-y-biao">&nbsp;本月热门</div>
				<div class="t-neirong-01-y-nei">
					<ul>  
						<s:iterator id="remen" value="remenList"> 
						<li>
                            <s:if test="#remen.sourceTypes==1">

                                <a href="${ctx}/source/sourceGet.action?date=<s:property value="#remen.createDate"/>&ftpFile=<s:property value="#remen.ftpFileLocal"/>">
                                    <s:if test="%{#remen.title!=null&&#remen.title.length()>8}">
                                        <s:property value="#remen.title.substring(0, 8)+'...'" />
                                    </s:if>
                                    <s:else>
                                        <s:property value="#remen.title" />
                                    </s:else>
                                </a>
                            </s:if>
                            <s:else>
                                <a href="<s:if test="#remen.netPath!=null"><s:property value="#remen.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#remen.subjectId" /></s:else>"  title="  <s:property value="#remen.title" />" target="_blank">
                                <s:if test="%{#remen.title!=null&&#remen.title.length()>14}">
                                                                            <s:property value="#remen.title.substring(0, 14)+'...'" />
                                                                         </s:if>
                                                                         <s:else>
                                                                           <s:property value="#remen.title" />
                                                                         </s:else>
                                </a>

                            </s:else>
                        </li>
						</s:iterator>
					</ul>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
			<!--底部 消息提示 -->
  		<jsp:include page="/comm/member_foot.jsp"></jsp:include>
</body>
</html>
