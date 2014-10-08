<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/comm/common_tag.jsp"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><s:if test="leve==1">小学资源</s:if><s:if test="leve==2">初中资源</s:if><s:if test="leve==3">高中资源 </s:if>_子贵网</title>
        <meta name="Keywords" content="教育资源,教学课件,考试试卷,教学视频">
        <meta name="Description" content="为全国高中小学各学科教师提供展示教学、实现教育资源交易的平台。
      打破教室、教师、校园的界限，实现校校互通、班班互联、资源共享。栏目针对高中小院校的各科目及各年级、教材版本的不同进行资源的分类展示。">
        <link href="${ctx}/source/css.css" rel="stylesheet" type="text/css" />
		<script>
function dj(i)
{
  for(j=1;j<10;j++)  //遍历刷新所有门的状态，序号与参数相同的的一种状态，其余一种状态
    {
  if(i==j){
   document.getElementById("a"+j).className="li2";
   document.getElementById("b"+j).style.display="";
          }
   else
    {
   document.getElementById("a"+j).className="li1";
   document.getElementById("b"+j).style.display="none";
      }
     }
 
}
</script>
	</head>

	<body>
	
		<div class="main">
		<jsp:include page="source_top.jsp"></jsp:include>
			<div class="t-box">				
				<div class="t-sou-lie">
					<p class="text-1-3">
						<span class="text-1-4">
						<s:if test="leve==1">小学资源 </s:if>
						<s:if test="leve==2">初中资源 </s:if>
						<s:if test="leve==3">高中资源 </s:if>
						</span>&nbsp;&nbsp;&nbsp;&nbsp;您的位置：子贵网&nbsp;&gt;&nbsp;
						<a href="${ctx}/source/sourceInfo.action">教育资源</a>&nbsp;&gt;&nbsp;
						<s:if test="leve==1"><a href="${ctx}/source/schoolInfo.action?leve=1">小学</a></s:if>
						<s:if test="leve==2"><a href="${ctx}/source/schoolInfo.action?leve=2">初中</a></s:if>
						<s:if test="leve==3"><a href="${ctx}/source/schoolInfo.action?leve=3">高中</a></s:if>
					</p>
				</div>
				<s:iterator id="subject" value="subjectList" status="s">
					<s:if test="#s.index==2">
						<div class="xia-nei-da-box-xia">
							<div class="xia-nei-da-box-xia-biao">
								<div class="biao">
									&nbsp;<s:if test="leve==1">小学</s:if>
						<s:if test="leve==2">初中</s:if>
						<s:if test="leve==3">高中</s:if>热门资源
								</div>
								<div class="more">
									<a href="#"></a>
								</div>
							</div>
							<div class="xia-nei-da-box-xia-nei">
								<ul>
									<s:iterator id="remen" value="newRemensourceList">
										<li>
[<a href="${ctx}/source/sourcePageList.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
											<a href="<s:if test="#remen.netPath!=null"><s:property value="#remen.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#remen.subjectId" /></s:else>"  title="  <s:property value="#remen.title" />" target="_blank">
 <s:if test="%{#remen.title!=null&&#remen.title.length()>12}"> 
					                                                    <s:property value="#remen.title.substring(0, 12)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#remen.title" />
																	   </s:else>
											</a>
										</li>
									</s:iterator>
								</ul>
							</div>
						</div>
			</div>
			</s:if>
			<s:if test="#s.index==4">
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						<div class="biao">
							&nbsp;最新资源
						</div>
						<div class="more">
							<a href="#"></a>
						</div>
					</div>
					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<s:iterator id="zuixin" value="newZuixinsourceList">
								<li>
									[<a href="${ctx}/source/sourcePageList.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
											<a href="<s:if test="#zuixin.netPath!=null"><s:property value="#zuixin.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#zuixin.subjectId" /></s:else>"  title="  <s:property value="#zuixin.title" />" target="_blank">
 <s:if test="%{#zuixin.title!=null&&#zuixin.title.length()>12}"> 
					                                                    <s:property value="#zuixin.title.substring(0, 12)+'...'" />
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#zuixin.title" />
																	   </s:else>
											</a>
											</li>
							</s:iterator>
						</ul>
					</div>
				</div>
		</div>
		</s:if>

		<s:if test="#s.index==0 || #s.index==2 || (#s.index>=4 && #s.index%3==1)">
			<div class="xia-nei-da-box">
		</s:if>
		<div class="xia-nei-da-box-xia">
			<div class="xia-nei-da-box-xia-biao">
				<div class="biao">
						&nbsp;
					<s:property value="#subject.subjectName" />

				</div>
				<div class="more">
					<a href="${ctx}/source/sourcePageList.action?seltj=3&selstr=<s:property value="#subject.subjectName" />">更多</a>
				</div>
			</div>
			<div class="xia-nei-da-box-xia-nei">
				<ul>
					<s:if test="leve==1">						
						<s:iterator id="source" value="xiaoxueSourceLists[#s.index]"
							status="st">
								
							     <li>			
											[<a href="${ctx}/source/sourcePageList.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
											<a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>"  title="  <s:property value="#source.title" />" target="_blank">
											 <s:if test="%{#source.title!=null&&#source.title.length()>14}"> 
					                                                    <s:property value="#source.title.substring(0, 14)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	   </s:else>
											</a>										
									</li>
									
						</s:iterator>
					</s:if>
					<s:if test="leve==2">
						<s:iterator id="source" value="chuzhongSourceLists[#s.index]"
							status="st">
							<li>
							[<a href="${ctx}/source/sourcePageList.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
							<a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>"  title="  <s:property value="#source.title" />" target="_blank">
 <s:if test="%{#source.title!=null&&#source.title.length()>14}"> 
					                                                    <s:property value="#source.title.substring(0, 14)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	   </s:else>
							</a>
										
							</li>
						</s:iterator>
					</s:if>
					<s:if test="leve==3">
						<s:iterator id="source" value="gaozhongSourceLists[#s.index]"
							status="st">
							<li>
							[<a href="${ctx}/source/sourcePageList.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
						<a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>"  title="  <s:property value="#source.title" />" target="_blank">
						 <s:if test="%{#source.title!=null&&#source.title.length()>14}"> 
					                                                    <s:property value="#source.title.substring(0, 14)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>
						</a>
							</li>
						</s:iterator>
					</s:if>
				</ul>
			</div>
		</div>
		<s:if test="#s.index>=6 && #s.index%3==0">
			</div>
		</s:if>
		<s:set name="test" value="#s.index" />
		
		
		</s:iterator>
		
		<s:if test="#test<4">
						<div class="xia-nei-da-box-xia" >
					<div class="xia-nei-da-box-xia-biao">
						<div class="biao">
							&nbsp;最新资源
						</div>
						<div class="more">
							<a href="#"></a>
						</div>
					</div>
					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<s:iterator id="zuixin" value="newZuixinsourceList">
								<li>
									[<a href="${ctx}/source/sourcePageList.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
											<a href="<s:if test="#zuixin.netPath!=null"><s:property value="#zuixin.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#zuixin.subjectId" /></s:else>"  title="  <s:property value="#zuixin.title" />" target="_blank">
 <s:if test="%{#zuixin.title!=null&&#zuixin.title.length()>14}"> 
					                                                    <s:property value="#zuixin.title.substring(0, 14)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#zuixin.title" />
																	   </s:else>
											</a>
											</li>
							</s:iterator>
						</ul>
					</div>
				</div>
		</s:if>
		
		<div class="clear"></div>
			<jsp:include page="/comm/member_foot.jsp"></jsp:include>
	</body>
</html>
