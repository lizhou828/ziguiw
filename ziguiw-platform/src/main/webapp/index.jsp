<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/templateHead.jsp"%>
<%@ page import="com.zigui.dao.UserPhotoDao"%>
<%@ page import="com.zigui.utils.BeanFactoryUtils"%>
<%@ page import="com.zigui.utils.ImageUtils" %>
<%@ page import="org.hibernate.HibernateException" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.springframework.orm.hibernate3.HibernateCallback" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<div class="content">
	<div class="ad01" id="ad01">
	 <!--
		<s:action var="getAd1" name="adPlan_getCurrentPlanById" namespace="/" executeResult="false" ignoreContextParams="false">
	      <s:param name="countPerPage" value="1"></s:param>
	      <s:param name="ad.id" value="1"></s:param>
        </s:action>
        <s:if test="#getAd1.adPlan != null">
        	<a href="<s:property value="#getAd1.adPlan.link"/>" title=""><img src="<s:property value="#getAd1.adPlan.imageUrl"/>" alt=""/></a>
        </s:if>
      -->
      <s:action name="ad_getByStateAndFlag" namespace="/" var="adAll">
      	<s:param name="ad.state" value="1"></s:param>
      	<s:param name="ad.flag" value="1"></s:param>
      </s:action>
      <s:if test="#adAll.ads.size>0">
       		<s:iterator var="ad" value="#adAll.ads" status="s">
       			<s:if test="#s.first">
	       			<div >
			        	<a href="http://${ad.link}" title=""><img src="${pageContext.request.contextPath }<s:property value="#ad.imageUrl"/>" alt=""/></a>
			         </div>
       			</s:if>
       			<s:else>
		       	 <div style="display:none">
		        	<a href="http://${ad.link}" title=""><img src="${pageContext.request.contextPath }<s:property value="#ad.imageUrl"/>" alt=""/></a>
		         </div>
       			</s:else>
       		</s:iterator>
      </s:if>
      
        <s:else>
			<a href="#" title=""><img src="<%=path%>/images/adv01.jpg" alt=""/></a>
		</s:else>
	</div>
	<!--ad01 E -->
	<!--  <div class="mod01">--><div class="mod01">
	    <!-- 
		<div class="focus01">
			<ul class="pic">
				<li class="z-index:1;"><a href="#" title=""><img src="<%=path%>/images/308X268.jpg" alt=""/></a></li>
				<li><a href="#" title=""><img src="<%=path%>/images/308X268.jpg" alt=""/></a></li>
				<li><a href="#" title=""><img src="<%=path%>/images/308X268.jpg" alt=""/></a></li>
				<li><a href="#" title=""><img src="<%=path%>/images/308X268.jpg" alt=""/></a></li>
				<li><a href="#" title=""><img src="<%=path%>/images/308X268.jpg" alt=""/></a></li>
			</ul>
			<div class="num">
				<a class="cur" href="javascript:void(0)" title="1">1</a>
				<a href="javascript:void(0)" title="2">2</a>
				<a href="javascript:void(0)" title="3">3</a>
				<a href="javascript:void(0)" title="4">4</a>
				<a href="javascript:void(0)" title="5">5</a>
			</div>			
	      </div>
	      -->
	      
	       <div class="sub_box_4">
	       						<s:action var="pptNews" name="news_listNewsByCondition" namespace="/news" executeResult="false" ignoreContextParams="false">
										      <s:param name="countPerPage" value="5"></s:param>
										      <s:param name="currentPage" value="1"></s:param>
										      <s:param name="query.state" value="2"></s:param>
										      <s:param name="query.regionId" value="15"></s:param>
									        </s:action>
				                 <div id="p-select" class="sub_nav_4">
					                    <div class="sub_no" id="bd1lfsj">
					                        <ul>
					                            <li class="show">1</li>
					                            <s:iterator value="#pptNews.pagedNews.list" var="news" status="st">
					                            <s:if test="!#st.first">
					                            	<li class=""><s:property value="#st.index + 1"/></li>
					                            </s:if>
					                            </s:iterator>
					                        </ul>
					                    </div>
					                </div>
								<div id="bd1lfimg_4">
					                    <div>					
					                        <dl class="show"></dl>
					                     	
									        <s:iterator value="#pptNews.pagedNews.list" var="news">
					                        <dl class="">												
					                            <dt><a href="<%=path%>/news/detail/<s:property value="#news.id"/>" title="" ><img src="<s:property value="#news.firstImage"/>" alt="<s:property value="#news.title"/>"></a></dt>					
					                            <dd>					
					                                <h4 class="fyahei"><a href="<%=path%>/news/detail/<s:property value="#news.id"/>" ><s:property value="#news.title"/></a></h4>                                					
					                            </dd>					
					                            <dd class="bg_lunbo_4"></dd>					
					                        </dl>
					                        </s:iterator>	
					                    	
					                	</div>					
					                </div>
					              
					                <script type="text/javascript">movec_4();</script>								
				</div>			                              					             
		<!--focus01 E -->
		<div class="toDayNew">
			<div class="toDayNewTit">
				<h2><span>今日头条</span><img src="<%=path%>/images/todayNew.jpg" alt=""/></h2>
			</div>
			<!--toDayNewTit E -->
			<div class="toDayNewCon">
				<ul class="toDayNewConList">
				<s:action var="toutiaoNews" name="news_listNewsByCondition" namespace="/news" executeResult="false" ignoreContextParams="false">
			      <s:param name="countPerPage" value="2"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="2"></s:param>
			      <s:param name="query.regionId" value="16"></s:param>
		        </s:action>
		        <s:iterator value="#toutiaoNews.pagedNews.list" var="news">
		          	<li>
						<dl>
							<dt><span>[<a href="<%=path%>/news/list/<s:property value="#news.newsChannle.id"/>" title="" ><s:property value="#news.newsChannle.name"/></a>] </span><a href="<%=path%>/news/detail/<s:property value="#news.id"/>" title="" ><s:property value="#news.title"/></a></dt>
							<dd><s:property value="#news.description.substring(0, 60)"/>...
							</dd>
						</dl>
					</li>
		        </s:iterator>
				</ul>
			</div>
			<!--toDayNewCon E -->
		</div>
		<!--toDayNew E -->
		<div class="allNews">
			<div class="logInWrap">
				<a class="logIn" href="<%=path%>/user/login.jsp" title="我要登录" ><span>我要登录</span></a>
				<a class="reg" href="<%=path%>/user/register.jsp" title="我要注册" ><span>我要注册</span></a>
			</div>
			<ul class="newsBtn01">
				<li class="cur"><a href="javascript:void(0);return false;">编辑推荐</a></li>
			</ul>
			<ul class="newList01">
				<s:action var="bianjituijianNews" name="news_listNewsByCondition" namespace="/news" executeResult="false" ignoreContextParams="false">
			      <s:param name="countPerPage" value="8"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="2"></s:param>
			      <s:param name="query.regionId" value="17"></s:param>
		        </s:action>
		        <s:iterator value="#bianjituijianNews.pagedNews.list" var="news">
		        <li><a href="<%=path%>/news/detail/<s:property value="#news.id"/>" title="<s:property value="#news.title"/>" >
		        	<s:if test='#news.title.length()<=15'>
			          <s:property value="#news.title"/>
			       </s:if>
			       <s:else>
			          <s:property value="#news.title.substring(0,15)"/>...
			       </s:else>

		        </a></li>
		        </s:iterator>
			</ul>
		</div>
	</div>
	<!--mod01 E -->
	<div class="box07">
		<div class="box07_hd">
			<h2><span>教育在线</span><img src="<%=path%>/images/jyzx.png" alt=""/></h2>
			<div class="pubMore07">
					<a href="<%=path%>/news/list/1" title="焦点话题" >焦点话题</a>
					|
					<a href="<%=path%>/news/list/2" title="校园新闻" >校园新闻</a>
					|
					<a href="<%=path%>/news/list/3" title="考试升学" >考试升学</a>
			</div>
		</div>
		<!--box07_hd E -->
		<div class="box07_bd">
			<div class="box07_bd_l">
				<div class="box02">
					<div class="box02_hd">
						<h2>焦点话题</h2>
						<a class="pubMore02" href="<%=path%>/news/list/1" title="更多 >" >更多 ></a>
					</div>
					<div class="box02_bd">
						<ul class="newList03">
						<s:action var="xiaoyuanNews" name="news_listNewsByCondition" namespace="/news" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="8"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="1"></s:param>
					      <s:param name="query.regionId" value="18"></s:param>
				        </s:action>
				        <s:iterator value="#xiaoyuanNews.pagedNews.list" var="news">
				           <li class="tri_icon">
								<a href="<%=path%>/news/detail/<s:property value="#news.id"/>" title="<s:property value="#news.title"/>" >
									<s:if test='#news.title.length()<=19'>
								          <s:property value="#news.title"/>
								       </s:if>
								       <s:else>
								          <s:property value="#news.title.substring(0,19)"/>...
								       </s:else>

								</a>
							</li>
				        </s:iterator>
						</ul>
					</div>
				</div>
				<!--box02 E -->
			</div>
			<!--box07_bd_l E -->
			
			<div class="box07_bd_m">
				<div class="box02">
					<div class="box02_hd">
						<h2>校园新闻</h2>
						<a class="pubMore02" href="<%=path%>/news/list/2" title="更多 >" >更多 ></a>
					</div>
					<div class="box02_bd">
						<ul class="newList04">
						<s:action var="jiaodianNews" name="news_listNewsByCondition" namespace="/news" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="2"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="2"></s:param>
					      <s:param name="query.regionId" value="18"></s:param>
				        </s:action>
				        <s:iterator value="#jiaodianNews.pagedNews.list" var="news">
				        	<li>
								<div class="pic">
									<a href="<%=path%>/news/detail/<s:property value="#news.id"/>" title="<s:property value="#news.title"/>"><img src="<s:property value="#news.firstImage"/>" style="width:127px;height:94px" alt="<s:property value="#news.title"/>"/></a>
								</div>
								<h3><a href="<%=path%>/news/detail/<s:property value="#news.id"/>" title="<s:property value="#news.title"/>" >
								<s:if test='#news.title.length()<=15'>
								          <s:property value="#news.title"/>
								       </s:if>
								       <s:else>
								          <s:property value="#news.title.substring(0,15)"/>...
								       </s:else>
								</a></h3>
								<p>
								<s:if test='#news.description.length()<=55'>
								          <s:property value="#news.description"/>
								       </s:if>
								       <s:else>
								          <s:property value="#news.description.substring(0,55)"/>...
								       </s:else>
								
								<span>[<a href="<%=path%>/news/detail/<s:property value="#news.id"/>" title="详细" >详细</a>]</span></p>
							</li>
				        </s:iterator>
						</ul>
					</div>
				</div>
				<!--box02 E -->
			</div>
			<!--box07_bd_m E -->
			
			<div class="box07_bd_r">
				<div class="box02">
					<div class="box02_hd">
						<h2>考试升学</h2>
						<a class="pubMore02" href="<%=path%>/news/list/3" title="更多 >" >更多 ></a>
					</div>
					<div class="box02_bd">
						<ul class="newList02">
						<s:action var="kaoshiNews" name="news_listNewsByCondition" namespace="/news" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="8"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="3"></s:param>
					      <s:param name="query.regionId" value="18"></s:param>
				        </s:action>
				        <s:iterator value="#kaoshiNews.pagedNews.list" var="news">
				          <li><a href="<%=path%>/news/detail/<s:property value="#news.id"/>" title="" >
				          <s:if test='#news.title.length()<=15'>
								          <s:property value="#news.title"/>
								       </s:if>
								       <s:else>
								          <s:property value="#news.title.substring(0,15)"/>...
								       </s:else>
				          </a></li>
				        </s:iterator>
						</ul>
					</div>
				</div>
				<!--box02 E -->
			</div>
			<!--box07_bd_m E -->
		</div>
	</div>

    <div class="ad01">
        <s:action name="ad_getByStateAndFlag" namespace="/" var="adAll">
            <s:param name="ad.state" value="1"></s:param>
            <s:param name="ad.flag" value="2"></s:param>
        </s:action>
        <s:if test="#adAll.ads.get(0)!= null">
            <a href="http://<s:property value="#adAll.ads.get(0).link"/>" title=""><img src="${pageContext.request.contextPath }<s:property value="#adAll.ads.get(0).imageUrl"/>" alt=""/></a>
        </s:if>

        <s:else>
            <a href="http://site.ziguiw.com" target="_blank"><img src="<%=path%>/images/znz_banner.jpg" alt=""/></a>
        </s:else>
    </div>

    <div class="box07">
    <div class="box07_hd">
        <h2><span>校园站内站</span><img src="images/znz.png" alt=""/></h2>
        <div class="pubMore07">
            <a href="http://site.ziguiw.com" title="校园站内站首页" >校园站内站首页</a>
        </div>
    </div>
    <!--box07_hd E -->
    <!--box07_bd_l B -->
    <div class="box07_bd">
        <!--名校展示开始-->
        <div class="box_schoolzs">
            <div class="s_title">
                <div class="t_img"><span>名校展示</span></div>
            </div>

            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301113000" target="_blank">
                        <img src="images/1363070612832.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301113000" target="_blank">
                        地质中学
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301117006" target="_blank">
                        <img src="http://static.ziguiw.com/upload/images/2013/0111/1357885399548.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301117006" target="_blank">
                        长沙市第十五中学
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301048001" target="_blank">
                        <img src="images/1358324437109.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301048001" target="_blank">
                        中南大学附属中学
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301047001" target="_blank">
                        <img src="images/1358324399474.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301047001" target="_blank">
                        岳麓外国语实验中学
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301057000" target="_blank">
                        <img src="images/1358414197929.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301057000" target="_blank">
                        长沙大学附属中学
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301046000" target="_blank">
                        <img src="images/1358324035222.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301046000" target="_blank">
                        湘仪学校
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4306021009" target="_blank">
                        <img src="images/zqxx.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4306021009" target="_blank">
                        岳阳市站前小学
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4306021005" target="_blank">
                        <img src="images/mz.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4306021005" target="_blank">
                        湖南民族学院附属小学
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4306023002" target="_blank">
                        <img src="images/jiu.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4306023002" target="_blank">
                        岳阳市第九中学
                    </a>
                </div>
            </div>
            <div class="school_s">
                <div class="ss_1">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4306023001" target="_blank">
                        <img src="images/shier.jpg" width="121" height="90" />
                    </a>
                </div>
                <div class="ss_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4306023001" target="_blank">
                        岳阳市第十二中学
                    </a>
                </div>
            </div>

        </div>
        <!--名校展示结束-->

        <!--最新加入学校开始-->

        <div class="new_school">
            <div class="n_title">
                <span>&nbsp;&nbsp;最新加入学校</span>
                <a class="n_more" href="http://site.ziguiw.com">
                    更多 >
                </a>
            </div>
            <div class="ns_school">
                <div class="ns_img">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301113000" target="_blank">
                        <img src="images/1363070612832.jpg" width="80" height="60" />
                    </a>
                </div>
                <div class="ns_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301113000" target="_blank">
                        地质中学
                    </a>
                </div>
            </div>
            <div class="ns_school">
                <div class="ns_img">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301117006" target="_blank">
                        <img src="http://static.ziguiw.com/upload/images/2013/0111/1357885399548.jpg" width="80" height="60" />
                    </a>
                </div>
                <div class="ns_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301117006" target="_blank">
                        长沙市第十五中学
                    </a>
                </div>
            </div>
            <div class="ns_school">
                <div class="ns_img">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301048001" target="_blank">
                        <img src="images/1358324437109.jpg" width="80" height="60" />
                    </a>
                </div>
                <div class="ns_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301048001" target="_blank">
                        中南大学附属中学
                    </a>
                </div>
            </div>
            <div class="ns_school">
                <div class="ns_img">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301047001" target="_blank">
                        <img src="images/1358324399474.jpg" width="80" height="60" />
                    </a>
                </div>
                <div class="ns_name">
                    <a href="http://site.ziguiw.com/schools/pshow?xxId=4301047001" target="_blank">
                        岳麓外国语实验中学
                    </a>
                </div>
            </div>
        </div>

        <!--最新加入学校结束-->
    </div>
    </div>
	<!--box07 E -->
	<div class="ad01">
		<s:action name="ad_getByStateAndFlag" namespace="/" var="adAll">
      	<s:param name="ad.state" value="1"></s:param>
      	<s:param name="ad.flag" value="2"></s:param>
      </s:action>
       <s:if test="#adAll.ads.get(0)!= null">
        	<a href="http://<s:property value="#adAll.ads.get(0).link"/>" title=""><img src="${pageContext.request.contextPath }<s:property value="#adAll.ads.get(0).imageUrl"/>" alt=""/></a>
        </s:if>
      
        <s:else>
			<a href="#" title=""><img src="<%=path%>/images/adv02.jpg" alt=""/></a>
		</s:else>
	</div>
	<!--ad01 E -->

	<div class="box07">
		<div class="box07_hd">
			<h2><span>教育知识</span><img src="<%=path%>/images/jyzs.png" alt=""/></h2>
			<div class="pubMore07">
					<a href="<%=path%>/study/youer/list" title="幼儿期" >幼儿期</a>
					|
					<a href="<%=path%>/study/shaoer/list" title="少儿期" >少儿期</a>
					|
					<a href="<%=path%>/study/qingnian/list" title="青年期" >青年期</a>
					|
					<a href="<%=path%>/study/yuer/list/0" title="育儿问答" >育儿问答</a>
					|
					<a href="<%=path%>/study/bianlun/list" title="用户辩论" >用户辩论</a>
			</div>
		</div>
		<!--box07_hd E -->
		<div class="box07_bd">
			<div class="box07_bd_l02">
				<ul class="newList05 newList05_a">
					<li>
						<div class="pic">
							<a href="<%=path%>/study/youer/list" title=""><img src="<%=path%>/images/182X137a.jpg" alt=""/></a>
						</div>
						<s:action var="youer1Knowledges" name="knowledge_listKnowledgesByCondition" namespace="/knowledge" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="1"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="1"></s:param>
					      <s:param name="query.regionId" value="7"></s:param>
				        </s:action>
				        <s:iterator value="#youer1Knowledges.pagedKnowledges.list" var="knowledge">
				         <h3><a class="bg01" href="<%=path%>/study/youer/detail/<s:property value="#knowledge.id"/>" title="" ><s:property value="#knowledge.title"/></a></h3>
						<p>　　<s:property value="#knowledge.description"/><span>[<a href="<%=path%>/study/youer/detail/<s:property value="#knowledge.id"/>" title="详细" >详细</a>]</span></p>
				        </s:iterator>
						
						<ul class="newList02">
						<s:action var="youer2Knowledges" name="knowledge_listKnowledgesByCondition" namespace="/knowledge" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="4"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="1"></s:param>
					      <s:param name="query.regionId" value="8"></s:param>
				        </s:action>
				        <s:iterator value="#youer2Knowledges.pagedKnowledges.list" var="knowledge">
				          <li><a  title="<s:property value="#knowledge.title"/>" href="<%=path%>/study/youer/detail/<s:property value="#knowledge.id"/>">
				          		<s:if test='#knowledge.title.length()<=15'>
						          <s:property value="#knowledge.title"/>
						       </s:if>
						       <s:else>
						          <s:property value="#knowledge.title.substring(0,15)"/>...
						       </s:else>

				          </a></li>
				        </s:iterator>
						</ul>
					</li>
					<!--  -->
					<li>
						<div class="pic">
							<a href="<%=path%>/study/shaoer/list" title=""><img src="<%=path%>/images/182X137b.jpg" alt=""/></a>
						</div>
						<s:action var="shaoer1Knowledges" name="knowledge_listKnowledgesByCondition" namespace="/knowledge" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="1"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="2"></s:param>
					      <s:param name="query.regionId" value="7"></s:param>
				        </s:action>
				        <s:iterator value="#shaoer1Knowledges.pagedKnowledges.list" var="knowledge">
				         <h3><a class="bg01" href="<%=path%>/study/shaoer/detail/<s:property value="#knowledge.id"/>" title="" ><s:property value="#knowledge.title"/></a></h3>
						<p>　　<s:property value="#knowledge.description"/><span>[<a href="<%=path%>/study/shaoer/detail/<s:property value="#knowledge.id"/>" title="详细" >详细</a>]</span></p>
				        </s:iterator>
						
						<ul class="newList02">
						<s:action var="shaoer2Knowledges" name="knowledge_listKnowledgesByCondition" namespace="/knowledge" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="4"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="2"></s:param>
					      <s:param name="query.regionId" value="8"></s:param>
				        </s:action>
				        <s:iterator value="#shaoer2Knowledges.pagedKnowledges.list" var="knowledge">
				          <li><a  title="<s:property value="#knowledge.title"/>" href="<%=path%>/study/shaoer/detail/<s:property value="#knowledge.id"/>">
				          	<s:if test='#knowledge.title.length()<=15'>
						          <s:property value="#knowledge.title"/>
						       </s:if>
						       <s:else>
						          <s:property value="#knowledge.title.substring(0,15)"/>...
						       </s:else>
				          </a></li>
				        </s:iterator>
						</ul>
					</li>
					
							<!--  -->
					<li>
						<div class="pic">
							<a href="<%=path%>/study/qingnian/list" title=""><img src="<%=path%>/images/182X137c.jpg" alt=""/></a>
						</div>
						<s:action var="qingnian1Knowledges" name="knowledge_listKnowledgesByCondition" namespace="/knowledge" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="1"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="3"></s:param>
					      <s:param name="query.regionId" value="7"></s:param>
				        </s:action>
				        <s:iterator value="#qingnian1Knowledges.pagedKnowledges.list" var="knowledge">
				         <h3><a class="bg01" href="<%=path%>/study/qingnian/detail/<s:property value="#knowledge.id"/>" title="" ><s:property value="#knowledge.title"/></a></h3>
						<p>　　<s:property value="#knowledge.description"/><span>[<a href="<%=path%>/study/qingnian/detail/<s:property value="#knowledge.id"/>" title="详细" >详细</a>]</span></p>
				        </s:iterator>
						
						<ul class="newList02">
						<s:action var="qingnian2Knowledges" name="knowledge_listKnowledgesByCondition" namespace="/knowledge" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="4"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="2"></s:param>
					      <s:param name="query.channleName" value="3"></s:param>
					      <s:param name="query.regionId" value="8"></s:param>
				        </s:action>
				        <s:iterator value="#qingnian2Knowledges.pagedKnowledges.list" var="knowledge">
				          <li><a  title="<s:property value="#knowledge.title"/>" href="<%=path%>/study/qingnian/detail/<s:property value="#knowledge.id"/>">
				          	<s:if test='#knowledge.title.length()<=15'>
						          <s:property value="#knowledge.title"/>
						       </s:if>
						       <s:else>
						          <s:property value="#knowledge.title.substring(0,15)"/>...
						       </s:else>
				          </a></li>
				        </s:iterator>
						</ul>
					</li>
                    </ul>
			</div>

			<!--box07_bd_l02 E -->
			
			<div class="box07_bd_r02">
				<div class="bianLun">
					<a class="pic" href="<%=path%>/study/bianlun/list" title=""><img src="<%=path%>/images/238X177.jpg" alt=""/></a>
					<s:action var="indexDebate" name="debate_listByCondition" namespace="/debate" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="1"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="orderField" value="clickCount"></s:param>
				        </s:action>
				        <s:iterator value="#indexDebate.debates.list" var="debate">
				          <p><a href="<%=path%>/study/bianlun/detail/<s:property value="#debate.id"/>" title="" >辩论：<s:property value="#debate.title"/></a></p>
				        </s:iterator>
					
				</div>
				<!--newList04 E -->
				<div class="box03">
					<div class="box03_hd">
						<h2>育儿问答</h2>
						<a  title="更多 >" href="<%=path%>/study/yuer/list/0" class="pubMore03">更多 ></a>
					</div>
					<div class="box03_bd">
						<ul class="newList02 newList02_a ">
						<s:action var="indexQuestion" name="question_listByCondition" namespace="/question" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="7"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="orderField" value="clickCount"></s:param>
				        </s:action>
				        <s:iterator value="#indexQuestion.questions.list" var="question">
				          <li><a  title="<s:property value="#question.title"/>" href="<%=path%>/study/yuer/detail/<s:property value="#question.id"/>" >				          
				          <s:if test='#question.title.length()<=12'>
				          <s:property value="#question.title"/>
				          </s:if>
				          <s:else>
				          <s:property value="#question.title.substring(0,12)"/>...
				          </s:else>
				          </a></li>
				        </s:iterator>
						</ul>
					</div>
				</div>
				<!--box03 E -->
			</div>
			<!--box07_bd_m E -->
		</div>
	</div>
	<!--box07 E -->
	<div class="ad01">
		<s:action name="ad_getByStateAndFlag" namespace="/" var="adAll">
      	<s:param name="ad.state" value="1"></s:param>
      	<s:param name="ad.flag" value="3"></s:param>
      </s:action>
       <s:if test="#adAll.ads.get(0)!= null">
        	<a href="http://<s:property value="#adAll.ads.get(0).link"/>" title=""><img src="${pageContext.request.contextPath }<s:property value="#adAll.ads.get(0).imageUrl"/>" alt=""/></a>
        </s:if>
      
        <s:else>
			<a href="#" title=""><img src="<%=path%>/images/adv03.jpg" alt=""/></a>
		</s:else>
	</div>
	<!--ad01 E -->
	<div class="box07">
		<div class="box07_hd">
			<h2><span>教育在线</span><img src="<%=path%>/images/hdsq.png" alt=""/></h2>
			<div class="pubMore07">
					<a href="<%=path%>/community/age/list" title="同龄圈" >同龄圈</a>
					|
					<a href="<%=path%>/community/city/list" title="同城圈" >同城圈</a>
					|
					<a href="<%=path%>/community/jiaoliu/list" title="学习交流" >学习交流</a>
					|
					<a href="<%=path%>/community/zhuanjia/list" title="专家问答" >专家问答</a>
					|
					<a href="<%=path%>/community/aixin/list" title="爱心站" >爱心站</a>
			</div>
		</div>
		<!--box07_hd E -->
		<div class="box07_bd">
			<div class="box07_bd_l03">
				<div class="bianLun">
					<a title="" href="#" class="pic"><img alt="" src="<%=path%>/images/238X177b.jpg"></a>
				</div>
				<!-- bianLunE -->

                <div class="box05">
                    <div class="box05_hd">
                        <h2>心理咨询</h2>
                        <a  title="更多 >" href="<%=path%>/study/psychology/list" class="pubMore05">更多 ></a>
                    </div>
                    <!--box05_hd E -->
                    <div class="box05_bd">
                        <ul class="newList06">

                            <s:action var="xinliKnowledges" name="knowledge_listKnowledgesByCondition" namespace="/knowledge" executeResult="false" ignoreContextParams="false">
                                <s:param name="countPerPage" value="7"></s:param>
                                <s:param name="currentPage" value="1"></s:param>
                                <s:param name="query.state" value="2"></s:param>
                                <s:param name="query.channleName" value="4"></s:param>
                            </s:action>
                            <s:iterator value="#xinliKnowledges.pagedKnowledges.list" var="knowledge">
                                <li><a  title="<s:property value="#knowledge.title"/>" href="<%=path%>/study/psychology/detail/<s:property value="#knowledge.id"/>">
                                    <s:if test='#knowledge.title.length()<=13'>
                                        <s:property value="#knowledge.title"/>
                                    </s:if>
                                    <s:else>
                                        <s:property value="#knowledge.title.substring(0,13)"/>...
                                    </s:else>
                                </a></li>
                            </s:iterator>

                        </ul>
                    </div>
                    <!--box05_bd E -->
                </div>
				<!--box05 E -->
			</div>
			<!--box07_bd_l E -->
			
			<div class="box07_bd_m03">
				<div class="box01 mb18">
					<div class="box01_hd">
						<h2>同龄圈</h2>
						<a  title="更多 >" href="<%=path%>/community/age/list" class="pubMore01">更多 ></a>
					</div>
					<div class="box01_bd">
						<div class="newTop01">
						<s:action var="tongling58Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
					      <s:param name="query.boardid" value="2"></s:param>
					      <s:param name="query.isnew" value="1"></s:param>
					      <s:param name="countPerPage" value="5"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="query.regionId" value="58"></s:param>
					      <s:param name="orderField" value="createTime"></s:param>
				        </s:action>
				        <s:iterator value="#tongling58Forums.forums.list" var="forum" status="st">
				        <s:if test="#st.first">
				        	<h3><a href="<%=path%>/community/age/detail/<s:property value="#forum.id"/>" title="" >
				        	<s:if test='#forum.title.length()<=15'>
				          <s:property value="#forum.title"/>
				          </s:if>
				          <s:else>
				          <s:property value="#forum.title.substring(0,15)"/>...
				          </s:else></a></h3>
							<p>
							<s:if test='#forum.autoDescription.length()<=45'>
				          <s:property value="#forum.autoDescription"/>
				          </s:if>
				          <s:else>
				          <s:property value="#forum.autoDescription.substring(0,45)"/>...
				          </s:else>
				          </p>
						</s:if>
				        </s:iterator>
						
						</div>
						<div class="newBottom01">
							<div class="pic">
								<a href="<%=path%>/community/age/list" title=""><img src="<%=path%>/images/127X94.jpg" alt=""/></a>
							</div>
							<ul class="newList02">
					        <s:iterator value="#tongling58Forums.forums.list" var="forum" status="st">
					        <s:if test="!#st.first">
					          <li><a  title="" href="<%=path%>/community/age/detail/<s:property value="#forum.id"/>">
					          <s:if test='#forum.title.length()<=15'>
				          <s:property value="#forum.title"/>
				          </s:if>
				          <s:else>
				          <s:property value="#forum.title.substring(0,15)"/>...
				          </s:else></a></li>
					        </s:if>
					        </s:iterator>
							</ul>
						</div>
					</div>
				</div>
				<!--box01 E -->
				<div class="box01">
					<div class="box01_hd">
						<h2>同城圈</h2>
						<a  title="更多 >" href="<%=path%>/community/city/list" class="pubMore01">更多 ></a>
					</div>
					<div class="box01_bd">
						<div class="newTop01">
						<s:action var="tongcheng57Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
					      <s:param name="query.boardid" value="3"></s:param>
					      <s:param name="query.isnew" value="1"></s:param>
					      <s:param name="countPerPage" value="5"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="query.regionId" value="57"></s:param>
					      <s:param name="orderField" value="createTime"></s:param>
				        </s:action>
				        <s:iterator value="#tongcheng57Forums.forums.list" var="forum" status="st">
				        	<s:if test="#st.first">
					        	<h3><a href="<%=path%>/community/city/detail/<s:property value="#forum.id"/>" title="" >
					        	<s:if test='#forum.title.length()<=15'>
				          <s:property value="#forum.title"/>
				          </s:if>
				          <s:else>
				          <s:property value="#forum.title.substring(0,15)"/>...
				          </s:else></a></h3>
								<p>
								<s:if test='#forum.autoDescription.length()<=45'>
				          <s:property value="#forum.autoDescription"/>
				          </s:if>
				          <s:else>
				          <s:property value="#forum.autoDescription.substring(0,45)"/>...
				          </s:else>
								</p>
							</s:if>
				        </s:iterator>						
						</div>
						<div class="newBottom01">
							<div class="pic">
								<a href="<%=path%>/community/city/list" title=""><img src="<%=path%>/images/127X94c.jpg" alt=""/></a>
							</div>
							<ul class="newList02">
					        <s:iterator value="#tongcheng57Forums.forums.list" var="forum" status="st">
					        	<s:if test="!#st.first">
					          		<li><a  title="" href="<%=path%>/community/city/detail/<s:property value="#forum.id"/>">
					          		<s:if test='#forum.title.length()<=15'>
				          				<s:property value="#forum.title"/>
				          			</s:if>
				          		<s:else>
				          			<s:property value="#forum.title.substring(0,15)"/>...
				          		</s:else>
                                	</a></li>
					         	</s:if>
					        </s:iterator>
							</ul>
						</div>
					</div>
				</div>
				<!--box01 E -->
			</div>
			<!--box07_bd_m E -->
			
			<div class="box07_bd_r03">
				<div class="box04">
					<div class="box04_hd">
						<h2>学习交流</h2>
						<a  title="更多" href="<%=path%>/community/jiaoliu/list" class="pubMore04">更多 ></a>
					</div>
					<div class="box04_bd">
						<ul class="newList02 newList02_a ">
						<s:action var="jiaoliu57Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
					      <s:param name="query.boardid" value="4"></s:param>
					      <s:param name="query.isnew" value="1"></s:param>
					      <s:param name="countPerPage" value="7"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="query.regionId" value="57"></s:param>
					      <s:param name="orderField" value="createTime"></s:param>
				        </s:action>
				        <s:iterator value="#jiaoliu57Forums.forums.list" var="forum">
				          <li><a href="<%=path%>/community/jiaoliu/detail/<s:property value="#forum.id"/>" title="" >
				          <s:if test='#forum.title.length()<=15'>
				          <s:property value="#forum.title"/>
				          </s:if>
				          <s:else>
				          <s:property value="#forum.title.substring(0,15)"/>...
				          </s:else>
				        </s:iterator>
						</ul>
					</div>
				</div>
				<!--box04 E -->
				<div class="box04">
					<div class="box04_hd">
						<h2>专家问答</h2>
						<a title="更多" href="<%=path%>/community/zhuanjia/list" class="pubMore04">更多 ></a>
					</div>
					<div class="box04_bd">
						<ul class="newList02 newList02_a ">
							<s:action var="zhuanjia57Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
					      <s:param name="query.boardid" value="5"></s:param>
					      <s:param name="query.isnew" value="1"></s:param>
					      <s:param name="countPerPage" value="7"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="query.regionId" value="57"></s:param>
					      <s:param name="orderField" value="createTime"></s:param>
				        </s:action>
				        <s:iterator value="#zhuanjia57Forums.forums.list" var="forum">
				          <li><a href="<%=path%>/community/zhuanjia/detail/<s:property value="#forum.id"/>" title="" >
				          <s:if test='#forum.title.length()<=15'>
				          <s:property value="#forum.title"/>
				          </s:if>
				          <s:else>
				          <s:property value="#forum.title.substring(0,15)"/>...
				          </s:else>
				        </s:iterator>
						</ul>
					</div>
				</div>
				<!--box04 E -->
				
			</div>
			<!--box07_bd_m E -->
		</div>
	</div>
	<!--box07 E -->
	<div class="ad01">
		<s:action name="ad_getByStateAndFlag" namespace="/" var="adAll">
      	<s:param name="ad.state" value="1"></s:param>
      	<s:param name="ad.flag" value="4"></s:param>
      </s:action>
       <s:if test="#adAll.ads.get(0)!= null">
        	<a href="http://<s:property value="#adAll.ads.get(0).link"/>" title=""><img src="${pageContext.request.contextPath }<s:property value="#adAll.ads.get(0).imageUrl"/>" alt=""/></a>
        </s:if>
      
        <s:else>
			<a href="#" title=""><img src="<%=path%>/images/adv04.jpg" alt=""/></a>
		</s:else>
	</div>

	<div class="box07">
		<div class="box07_hd">
			<h2><span>我的小家</span><img src="<%=path%>/images/wdxj.png" alt=""/></h2>
			<div class="pubMore07">
			</div>
		</div>
		<!--box07_hd E -->
		<div class="box07_bd">
			<div class="box07_bd_l02">
				<div class="picListWrap01">
					<h2>明<br />星<br />墙</h2>
					<ul class="picList01 clearfix">

					   	<s:action var="mingxing101Users" name="user_listByCondition" namespace="/user" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="24"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="query.regionId" value="101"></s:param>
					      <s:param name="orderField" value="createTime"></s:param>
				        </s:action>
				        <s:iterator value="#mingxing101Users.users.list" var="user">
				          <li><a href="<%=path%>/myhome/<s:property value="#user.nickName"/>" title=""><img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#user.portrait"), path + "/images/head.jpg", 70)%>" alt="<s:property value="#user.nickName"/>" width=48 height=48/></a></li>
				        </s:iterator>
					</ul>
					<div class="tips">
						
					</div>
				</div>
				<!-- picList01 -->

				<div class="box01 addAtt01">
					<div class="box01_hd">
						<h2>个人日记</h2>
						
					</div>
					<div class="box01_bd">
						<ul class="newList05 newList05_a">
						<s:action var="riji121UserDiaries" name="userDiary_listByCondition" namespace="/user" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="9"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.regionId" value="121"></s:param>
					      <s:param name="orderField" value="createTime"></s:param>
				        </s:action>
				        <s:iterator value="#riji121UserDiaries.userDiaries.list" var="userDiary">
				        <li><a href="<%=path%>/myhome/${userDiary.user.nickName}/diary/<s:property value="#userDiary.id"/>" title="" >

				        <s:if test='#userDiary.title.length()<=15'>
				          <s:property value="#userDiary.title"/>
				          </s:if>
				          <s:else>
				          <s:property value="#userDiary.title.substring(0,15)"/>...
				          </s:else>
				        </a></li>
				        </s:iterator>							
						</ul>
					</div>
				</div>
				<!--box01 E -->

				<div class="box01">
					<div class="box01_hd">
						<h2>照片</h2>
						
					</div>
					<div class="box01_bd">
						<ul class="newList07">
					   <s:action var="zhaopian141UserPhotos" name="userPhoto_listByCondition" namespace="/user" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="9"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.regionId" value="141"></s:param>
					      <s:param name="orderField" value="createTime"></s:param>
				        </s:action>
				        <s:iterator value="#zhaopian141UserPhotos.userPhotos.list" var="userPhoto">
				           <li>
								<div class="pic">
									<a href="<%=path%>/myhome/${userPhoto.user.nickName}/photo/<s:property value="#userPhoto.albumId"/>/<s:property value="#userPhoto.id"/>" title=""><img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#userPhoto.url"), path + "/images/head.jpg", 120)%>" alt="<s:property value="#userPhoto.title"/>" width=105 height=77/></a>
								</div>
								<p><a href="<%=path%>/myhome/${userPhoto.user.nickName}/photo/<s:property value="#userPhoto.albumId"/>/<s:property value="#userPhoto.id"/>" title="" ><s:property value="#userPhoto.title"/></a></p>
							</li>
				        </s:iterator>
						</ul>
					</div>
				</div>
				<!--box01 E -->
			</div>
			<!--box07_bd_l E -->
			
			<div class="box07_bd_r02">
				<div class="topics">
					<a href="#" title=""><img src="<%=path%>/images/241X77.jpg" alt=""  height=128/></a>
				</div>
				<!--topics E -->
				<div class="box06">
					<div class="box06_hd">
						<h2>热门日记</h2>
					</div>
					<div class="box06_bd">
						<ul class="newList08">
						<s:action var="riji122UserDiaries" name="userDiary_listByCondition" namespace="/user" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="10"></s:param>
					      <s:param name="currentPage" value="1"></s:param>
					      <s:param name="query.regionId" value="122"></s:param>
					      <s:param name="orderField" value="viewCount"></s:param>
				        </s:action>
				        <s:iterator value="#riji122UserDiaries.userDiaries.list" var="userDiary">
				        <li>
								
								<a href="<%=path%>/myhome/${userDiary.user.nickName}/diary/<s:property value="#userDiary.id"/>" title="" >
									<s:if test='#userDiary.title.length()<=16'>
							          <s:property value="#userDiary.title"/>
							          </s:if>
							          <s:else>
							          <s:property value="#userDiary.title.substring(0,16)"/>...
							          </s:else>

								</a>
							</li>
				        </s:iterator>
						</ul>
					</div>
				</div>

			</div>
			<!--box07_bd_r E -->

				<!--box01 E -->
		</div>
	</div>
	<!--box07 E -->
	
	<script>
	$(function(){
		$('a').attr("target", "_blank");

	});
	</script>
	
 <%@ include file="inc/templateLink.jsp"%>
    </div>
<!-- Baidu Button BEGIN -->
<script type="text/javascript" id="bdshare_js" data="type=slide&img=0&uid=1998022" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
   
    function getById(e){return document.getElementById(e);}
    function myPlayer(con, child, speed){
        var ts = getById(con).getElementsByTagName(child),//获取容器内需要轮流显示的子元素：这里是 div
            timer,k=0;
            var timer = setInterval(function(){//定义时间控制器
                for(var m=0;m<ts.length;m++){ts[m].style.display='none';}//先隐藏全部，此处效率降低，可以考虑不用循环
                ts[k].style.display='';//显示需要显示的
                if(k>ts.length-2){
                   k=0;
                }else{
                   k++;
                }
            },speed);
    }
    myPlayer('ad01','div',5000);//调用，参数一外层元素ID，参数二循环元素名称，间隔时间：秒数的1000倍
   
</script>
<!-- Baidu Button END -->
<%@ include file="inc/templateFoot.jsp"%>
