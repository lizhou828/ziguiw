<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@include file="/comm/common_tag.jsp"%>



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
            <a href="${ctx}/source/soureMore.action?type=${type}">
               <s:if test="type==1">
                   最新教学视频
               </s:if>
              <s:elseif test="type==2">
                  最新考试卷
              </s:elseif>
              <s:else>
                  最新课件
              </s:else>

            </a>
        </p>
    </div>
    <div class="t-neirong-01">
        <form action="${ctx}/source/soureMore.action" id="nextPage" method="post">
            <div class="t-neirong-liebiao-z">
                <ul>
                    <s:iterator value="sourceList" id="source" status="s">
                        <li>
                            [<a href="${ctx}/source/sourcePageList.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />" target="_blank"><s:property value="#source.souceVersion.VName" /></a>]

                            <s:if test="#source.sourceTypes==1">
                                <a href="${ctx}/source/sourceGet.action?date=<s:property value="#source.createDate"/>&ftpFile=<s:property value="#source.ftpFileLocal"/>">
                                    <s:if test="%{#source.title!=null&&#source.title.length()>8}">
                                        <s:property value="#source.title.substring(0, 8)+'...'" />
                                    </s:if>
                                    <s:else>
                                        <s:property value="#source.title" />
                                    </s:else>
                                </a>
                            </s:if>
                            <s:else>
                                <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/source!get.action?id=<s:property value="#source.subjectId" /></s:else>" title=" <s:property value="#source.title" />" target="_blank">
                                    <s:if test="%{#source.title!=null&&#source.title.length()>12}">
                                        <s:property value="#source.title.substring(0, 12)+'...'" />
                                    </s:if>
                                    <s:else>
                                        <s:property value="#source.title" />
                                    </s:else>
                                </a>
                            </s:else>
                        </li>
                    </s:iterator>
                </ul>

                <input type="hidden" name="type" value="<s:property value="type" />"/>
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
                                    <s:if test="%{#zuixin.title!=null&&#zuixin.title.length()>14}">
                                        <s:property value="#zuixin.title.substring(0, 14)+'...'" />
                                    </s:if>
                                    <s:else>
                                        <s:property value="#zuixin.title" />
                                    </s:else>
                                </a>
                            </s:if>
                            <s:else>
                            <a href="<s:if test="#zuixin.netPath!=null"><s:property value="#zuixin.netPath" /></s:if><s:else>${ctx}/source/source!get.action?id=<s:property value="#zuixin.subjectId" /></s:else>"  title="  <s:property value="#zuixin.title" />" target="_blank">
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
                                    <s:if test="%{#remen.title!=null&&#remen.title.length()>14}">
                                        <s:property value="#remen.title.substring(0, 14)+'...'" />
                                    </s:if>
                                    <s:else>
                                        <s:property value="#remen.title" />
                                    </s:else>
                                </a>
                            </s:if>


                            <s:else>
                                <a href="<s:if test="#remen.netPath!=null"><s:property value="#remen.netPath" /></s:if><s:else>${ctx}/source/source!get.action?id=<s:property value="#remen.subjectId" /></s:else>"  title="  <s:property value="#remen.title" />" target="_blank">
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
