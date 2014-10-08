<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>子贵网--资源下载专题</title>
		<link href="source/css.css" rel="stylesheet" type="text/css" />
			
		<script>
function dj(i)
{
  for(j=1;j<100;j++)  //遍历刷新所有门的状态，序号与参数相同的的一种状态，其余一种状态
  {
	    if(document.getElementById("a"+j)==null){
	    	break;
	    }
	   document.getElementById("a"+j).className="li1";
	   document.getElementById("b"+j).style.display="none";
   }
   document.getElementById("a"+i).className="li2";
   document.getElementById("b"+i).style.display="";
 
}
</script>
	</head>
	<body>
		<div class="main">
		  <jsp:include page="source_top.jsp"></jsp:include>
			<div class="t-neirong-01">
				<!--<jsp:include page="include.jsp"></jsp:include>
				--><div class="t-neirong-01-z">
					<table width="662" height="217" border="0" cellpadding="0"
						cellspacing="0" bordercolor="#003399">
						<tr>
							<s:iterator id="subject" value="subjectList" status="s">
								<td width="69" height="31" background="images/aaaq1.jpg">
									<div align="center" id="a<s:property value="#s.index+1" />"
										class="li<s:if test="#s.index==0">2</s:if><s:if test="#s.index!=0">1</s:if>">
										<a href="source/source!list_qt.action?seltj=3&selstr=<s:property value="#subject.subjectName" />"
											onmousemove="dj(<s:property value="#s.index+1" />)">
											<s:property	value="#subject.subjectName" /> </a>
									</div>
								</td>
							</s:iterator>
						</tr>
						<tr>
							<td height="500" colspan="<s:property value="subjectList.size"/>" valign="top">
								<s:iterator id="subject" value="subjectList" status="s">
									<div align="center" id="b<s:property value='#s.index+1' />"
										class="wen-nei-box"
										style="display: <s:if test = "#s.index==0" > black; </s:if ><s:if test = "#s.index!=0" > none; </s:if>">
										<div class="wen-nei-da-box">
											<div class="wen-nei-da-biao">
												&nbsp;小学
											</div>
											<div class="wen-nei-da-neinong">
												<ul>
													<s:iterator id="source" value="xiaoxueSourceLists[#s.index]" status="st">
																<li style="text-align: left">
																	[<a href="source/source!list_qt.action?seltj=2&selstr=<s:property value="#source.souceStugrade.njMcheng" />"><s:property value="#source.souceStugrade.njMcheng" /></a>]
																	[<a href="source/source!list_qt.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
																	<a href="source/source!get.action?id=<s:property value="#source.subjectId" />" target="_blank">
																	 <s:if test="%{#source.title!=null&&#source.title.length()>10}"> 
					                                                    <s:property value="#source.title.substring(0, 10)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>																	
																	</a>
																</li>
													</s:iterator>
												</ul>
											</div>
										</div>
										<div class="wen-nei-da-box">
											<div class="wen-nei-da-biao">
												&nbsp;初中
											</div>
											<div class="wen-nei-da-neinong">
												<ul>
													<s:iterator id="source" value="chuzhongSourceLists[#s.index]" status="st">
																<li style="text-align: left">
																	[<a href="source/source!list_qt.action?seltj=2&selstr=<s:property value="#source.souceStugrade.njMcheng" />"><s:property value="#source.souceStugrade.njMcheng" /></a>]
																	[<a href="source/source!list_qt.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
																	<a href="source/source!get.action?id=<s:property value="#source.subjectId" />" target="_blank">
																	 <s:if test="%{#source.title!=null&&#source.title.length()>6}"> 
					                                                    <s:property value="#source.title.substring(0, 6)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>																	
																	</a>
																</li>
													</s:iterator>
												</ul>
											</div>
										</div>
										<div class="wen-nei-da-box">
											<div class="wen-nei-da-biao">
												&nbsp;高中
											</div>
											<div class="wen-nei-da-neinong">
												<ul>
													<s:iterator id="source" value="gaozhongSourceLists[#s.index]" status="st">
																<li style="text-align: left">
																	[<a href="source/source!list_qt.action?seltj=2&selstr=<s:property value="#source.souceStugrade.njMcheng" />"><s:property value="#source.souceStugrade.njMcheng" /></a>]
                                                                     [<a href="source/source!list_qt.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
                                                                    <a href="source/source!get.action?id=<s:property value="#source.subjectId" />" target="_blank">
																	 <s:if test="%{#source.title!=null&&#source.title.length()>6}"> 
					                                                    <s:property value="#source.title.substring(0, 6)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>																	
																	</a>
																</li>
													</s:iterator>
												</ul>
											</div>
										</div>
									</div>
								</s:iterator>
							</td>
						</tr>
					</table>
				</div>
				<div class="t-neirong-01-y">
					<div class="t-neirong-01-y-biao">
						&nbsp;最新推荐
					</div>
					<div class="t-neirong-01-y-nei">
						<ul>
							<s:iterator value="newTjiansourceList" id="source" status="s">
							<li>
								[<a href="source/source!list_qt.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
								<a href="source/source!get.action?id=<s:property value="#source.subjectId" />" target="_blank">
								 <s:if test="%{#source.title!=null&&#source.title.length()>10}"> 
					                                                    <s:property value="#source.title.substring(0, 10)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else></a>
							</li>
							</s:iterator>
						</ul>
					</div>
					<div class="t-neirong-01-y-biao">
						&nbsp;本月热门
					</div>
					<div class="t-neirong-01-y-nei">					
					<ul>						
						<s:iterator value="remenList" id="source"  status="s"> 
					        <li>
					        [<a href="source/source!list_qt.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
								<a href="source/source!get.action?id=<s:property value="#source.subjectId" />" target="_blank">
 <s:if test="%{#source.title!=null&&#source.title.length()>10}"> 
					                                                    <s:property value="#source.title.substring(0, 10)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else></a>
							</li>
						</s:iterator>						
					</ul>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="t-AD">
				<img src="source/images/add1.jpg" />
			</div>
			<div class="xia-nei-da-box">
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新教学视频
						<br />
					</div>
					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<s:iterator value="newVadiosourceList" id="source" status="s">
							<li>
							[<a href="source/source!list_qt.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
								<a href="source/source!get.action?id=<s:property value="#source.subjectId" />" target="_blank">
 <s:if test="%{#source.title!=null&&#source.title.length()>10}"> 
					                                                    <s:property value="#source.title.substring(0, 10)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>
								</a>
							</li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新考试试卷
					</div>
					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<s:iterator value="newSjuansourceList" id="source" status="s">
							<li>
							[<a href="source/source!list_qt.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
								<a href="source/source!get.action?id=<s:property value="#source.subjectId" />" target="_blank">
 <s:if test="%{#source.title!=null&&#source.title.length()>10}"> 
					                                                    <s:property value="#source.title.substring(0, 10)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>
								</a>
							</li>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新教学课件
					</div>
					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<s:iterator value="newKejiansourceList" id="source" status="s">
							<li>
							[<a href="source/source!list_qt.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />"><s:property value="#source.souceVersion.VName" /></a>]
								<a href="source/source!get.action?id=<s:property value="#source.subjectId" />" target="_blank">
 <s:if test="%{#source.title!=null&&#source.title.length()>10}"> 
					                                                    <s:property value="#source.title.substring(0, 10)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>
								</a>
							</li>
							</s:iterator>
						</ul>
					</div>
				</div>
			</div>
			<div class="xia-nei-da-box">
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新焦点话题
						<br />
					</div>
					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<li>
								恩施高考状元乘车“高调游街”引热议
							</li>
							<li>
								家长晒培训账单：笔笔过万超月薪
							</li>
							<li>
								小学生徒步劝募400公里募7000元善款
							</li>
							<li>
								老师要求定点检查视力遭借机推销质疑
							</li>
							<li>
								高考高分生：从小就近入学 与补习班绝缘
							</li>
							<li>
								南京女生画高中“拟人图”引网友围观(图)
							</li>
							<li>
								17岁留学生办教育论坛 探讨中国教育发展
							</li>
							<li>
								每年8万孩子死于意外 谨防四类伤害夺命
							</li>
							<li>
								中国孩子伤不起：一拼爹妈二靠考试
							</li>
						</ul>
					</div>
				</div>
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新校园新闻
					</div>
					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<li>
						  <p>名校盘点：全国百所一流中学集合 <br />
						    中学禁女教师穿超短裙男教师禁穿拖鞋 <br />
                               郑州小学生暑期练摊不为赚钱为体验(图)<br />
                               培训老师遭“调包”学员要求退费被拒<br />
						    曝光“名师”上课：放动画片看报纸 <br />
						    武汉4名教师办家教受处罚 辞退1人 <br />
						    教育部门一提减负最高兴的是培训机构 <br />
						    补习孩子心声：我好累提起暑假就伤心 <br />
						    理科状元谈早恋：摆正位置不影响学习 </p>
						</li>
						</ul>
					</div>
				</div>
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;考试升学信息
					</div>
					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<li>
							  <p>2012北京26所示范高中中考预录取分数线<br />
							    中学生暑假扎堆学托福 应理性对待留学热 <br />
							    衔接班暑假升温：盛行的背后是教育恐慌<br />
							    探密小升初辅导班：选择“包过班”要慎重<br />
							    安徽教育厅：竞赛证书不能为招生“加分”<br />
							    2012湘三本(A)征集志愿投档线8月1日公布<br />
							    网瘾退学大学生时隔4年再次考入华科(图)<br />
							    上海民间传出反对”异地高考”声音遭议<br />
							    2013年”初升高”衔接问题指南家长手册</p>
						</ul>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	<jsp:include page="source_foot.jsp"></jsp:include>
	</body>
</html>
