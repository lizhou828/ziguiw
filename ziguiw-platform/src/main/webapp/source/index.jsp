<%@ page language="java" pageEncoding="utf-8" %>

<%@ include file="/comm/common_tag.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

		<title>1-12年级的语文、数学、英语、物语等各科目的课件,题库,教案,视频等_子贵网</title>
        <meta name="Keywords" content="教育资源,教学课件,考试试卷,教学视频"/>
        <meta name="Description" content="为全国高中小学各学科教师提供展示教学、实现教育资源交易的平台。
        打破教室、教师、校园的界限，实现校校互通、班班互联、资源共享。
        栏目针对高中小院校的各科目及各年级、教材版本的不同进行资源的分类展示。"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="${ctx}/source/css.css" rel="stylesheet" type="text/css" />
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
               document.getElementById("b"+i).style.display="block";
    
            }
        </script>
	</head>
	<body>
		<div class="main">
		  <jsp:include page="source_top.jsp"></jsp:include>
			<div class="t-neirong-01">
				<div class="t-neirong-01-z">
					<table width="662" height="217" border="0" cellpadding="0"
						cellspacing="0" bordercolor="#003399">
						<tr>
							<s:iterator id="subject" value="subjectList" status="s">
								<td width="69" height="31" background="images/aaaq1.jpg">
									<div align="center" id="a<s:property value="#s.index+1" />"
										class="li<s:if test="#s.index==0">2</s:if><s:if test="#s.index!=0">1</s:if>">
										<a href="${ctx}/source/sourcePageList.action?seltj=3&selstr=<s:property 
										    value="#subject.subjectName" />"
											onmousemove="dj(<s:property value="#s.index+1" />)" target="_blank">
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
                                       <!-- 小学隐藏其没有的课程-->
                                        <s:if test="#subject.subjectName == '语文'|| #subject.subjectName=='数学' ||  #subject.subjectName=='英语'">
                                        <div class="wen-nei-da-box">
											<div class="wen-nei-da-biao">
												&nbsp;小学
											</div>

											<div class="wen-nei-da-neinong">
												<ul>
													<s:iterator id="source" value="xiaoxueSourceLists[#s.index]" status="st">
																<li style="text-align: left">
																	[<a href="${ctx}/source/sourcePageList.action?seltj=2&selstr=<s:property value="#source.souceStugrade.njMcheng"  />" target="_blank">
																	<s:property value="#source.souceStugrade.njMcheng" /></a>]
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
                                                                        <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>"  title=" <s:property value="#source.title" />" target="_blank">
                                                                            <s:if test="%{#source.title!=null&&#source.title.length()>8}">
                                                                                <s:property value="#source.title.substring(0, 8)+'...'" />
                                                                            </s:if>
                                                                            <s:else>
                                                                                <s:property value="#source.title" />
                                                                            </s:else>
                                                                        </a>
																    </s:else>

																</li>
													</s:iterator>
												</ul>
											</div>
										</div>
                                         </s:if>

										<div class="wen-nei-da-box">
											<div class="wen-nei-da-biao">
												&nbsp;初中
											</div>
											<div class="wen-nei-da-neinong">
												<ul>
													<s:iterator id="source" value="chuzhongSourceLists[#s.index]" status="st">
																<li style="text-align: left">
																	[<a href="${ctx}/source/sourcePageList.action?seltj=2&selstr=<s:property value="#source.souceStugrade.njMcheng" />" target="_blank"><s:property value="#source.souceStugrade.njMcheng" /></a>]
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
                                                                    <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>" title=" <s:property value="#source.title" />" target="_blank">
																	 <s:if test="%{#source.title!=null&&#source.title.length()>8}"> 
					                                                    <s:property value="#source.title.substring(0, 8)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>																	
																	</a>
                                                                    </s:else>

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
																	[<a href="${ctx}/source/sourcePageList.action?seltj=2&selstr=<s:property value="#source.souceStugrade.njMcheng" />" target="_blank"><s:property value="#source.souceStugrade.njMcheng" /></a>]
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
                                                                    <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>" title=" <s:property value="#source.title" />" target="_blank">
																	 <s:if test="%{#source.title!=null&&#source.title.length()>8}"> 
					                                                    <s:property value="#source.title.substring(0, 8)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else>																	
																	</a>
                                                                    </s:else>

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
                                <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>" title=" <s:property value="#source.title" />" target="_blank">
								 <s:if test="%{#source.title!=null&&#source.title.length()>12}"> 
					                                                    <s:property value="#source.title.substring(0, 12)+'...'" />					 
					                                                 </s:if>
						                                             <s:else>
																	   <s:property value="#source.title" />
																	 </s:else></a>
                                </s:else>
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
                                <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>" title=" <s:property value="#source.title" />" target="_blank">
 									<s:if test="%{#source.title!=null&&#source.title.length()>12}"> 
					                	<s:property value="#source.title.substring(0, 12)+'...'" /></s:if>
									<s:else>
										<s:property value="#source.title" />
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
			<div class="t-AD">
                <!--添加链接-->
                <a href="user/teaIndexAction.action">
				    <img src="${ctx}/source/images/add1.jpg" />
                </a>
			</div>
			<div class="xia-nei-da-box">
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新教学视频
                        <div class="more">
                            <a href="${ctx}/source/soureMore.action?type=1">更多</a>
                        </div>
						<br />
					</div>

					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<s:iterator value="newVadiosourceList" id="source" status="s">
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
                                <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>" title=" <s:property value="#source.title" />" target="_blank">
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
					</div>
				</div>
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新考试试卷
                        <div class="more">
                            <a href="${ctx}/source/soureMore.action?type=2">更多</a>
                        </div>
					</div>

					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<s:iterator value="newSjuansourceList" id="source" status="s">
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
                                <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>" title=" <s:property value="#source.title" />" target="_blank">
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
					</div>
				</div>
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新教学课件
                        <div class="more">
                            <a href="${ctx}/source/soureMore.action?type=3">更多</a>
                        </div>
					</div>

					<div class="xia-nei-da-box-xia-nei">
						<ul>
							<s:iterator value="newKejiansourceList" id="source" status="s">
								<li>
									[<a href="${ctx}/source/sourcePageList.action?seltj=1&selstr=<s:property value="#source.souceVersion.VName" />" target="_blank">
										<s:property value="#source.souceVersion.VName" /></a>]

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
                                    <a href="<s:if test="#source.netPath!=null"><s:property value="#source.netPath" /></s:if><s:else>${ctx}/source/sourceGet.action?id=<s:property value="#source.subjectId" /></s:else>" title=" <s:property value="#source.title" />" target="_blank">
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
					</div>
				</div>
			</div>
			<div class="xia-nei-da-box">
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新焦点话题
                        <div class="more">
                            <a href="http://www.ziguiw.com/news/list/1">更多</a>
                        </div>
						<br />
					</div>

					<div class="xia-nei-da-box-xia-nei">
                        <ul>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/244">湖南最牛高考班 破记录32人上清华北大</a>
                            </li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/555">“澄”字怎么读？网友惊呼被化学老师骗了</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/521">恩施高考状元乘车“高调游街”引热议</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/522">家长晒培训账单：笔笔过万超月薪</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/523">小学生徒步劝募400公里募7000元善款</a>
                            </li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/525">高考高分生：从小就近入学 与补习班绝缘</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/527">17岁留学生办教育论坛 探讨中国教育发展</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/528">每年8万孩子死于意外 谨防四类伤害夺命</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/529">中国孩子伤不起：一拼爹妈二靠考试</a>
                            </li>
                        </ul>

					</div>
				</div>
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;最新校园新闻

                        <div class="more">
                            <a href="http://www.ziguiw.com/news/list/2">更多</a>
                        </div>
					</div>

					<div class="xia-nei-da-box-xia-nei">
                        <ul>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/556">长沙初中不再分重点班、实验班、特长班</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/557">盘点学生圈中最受喜爱的十类老师（组图）</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/558">长郡AP管理委员会胡敏解读2012年招生政策</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/530"> 名校盘点：全国百所一流中学集合 </a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/531">中学禁女教师穿超短裙男教师禁穿拖鞋 </a></li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/534"> 曝光“名师”上课：放动画片看报纸 </a></li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/536">教育部门一提减负最高兴的是培训机构 </a></li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/537"> 补习孩子心声：我好累提起暑假就伤心 </a></li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/538"> 理科状元谈早恋：摆正位置不影响学习 </a>
                            </li>
                        </ul>
					</div>
				</div>
				<div class="xia-nei-da-box-xia">
					<div class="xia-nei-da-box-xia-biao">
						&nbsp;考试升学信息
                        <div class="more">
                            <a href="http://www.ziguiw.com/news/list/3">更多</a>
                        </div>
					</div>

					<div class="xia-nei-da-box-xia-nei">
                        <ul>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/550">湖南理科最高分考生：从小就是年级第一</a>
                            </li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/553">2012年湖南高考数学（文科）试卷及答案</a>
                            </li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/552">2012年湖南高考数学（理科）试卷及答案</a>
                            </li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/554">湖南文科卷面最高分女生称想当高级会计师</a>
                            </li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/540">  中学生暑假扎堆学托福 应理性对待留学热 </a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/541"> 衔接班暑假升温：盛行的背后是教育恐慌</a>
                            </li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/542">  探密小升初辅导班：选择“包过班”要慎重</a>
                            </li>

                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/544">  2012湘三本(A)征集志愿投档线8月1日公布</a></li>
                            <li>
                                <a target="blank" href="http://www.ziguiw.com/news/detail/547"> 2012年”初升高”衔接问题指南家长手册</a></li>
                        </ul>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	<jsp:include page="/comm/member_foot.jsp"></jsp:include>
	</body>
</html>
