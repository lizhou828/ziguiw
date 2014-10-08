<#include "head.ftl">

<div class="content clearfix">

	<div class="ad01">

		<a target="_blank"  href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>

	</div>

	<!--ad01 E -->

    <div class="clearfix">

    	<div class="lunbo fl pr">

          <div class="sub_box">

                <div id="p-select" class="sub_nav">

                    <div class="sub_no" id="bd1lfsj">

                        <ul>

                            <li class="show">1</li><li class="">2</li><li class="">3</li><li class="">4</li><li class="">5</li>

                        </ul>

                    </div>

                </div>

                <div id="bd1lfimg">

                    <div>

                        <dl class="show"></dl>
			<@dataFunction function="queryRecommendNews" pageNo="1" pageSize="5" reommendRegionId=1>
                        <#list functionData.list as news>
                        <dl class="">
							
                            <dt><a href="${path!}/news/detail/${news.id}" title="" target="_blank"><img src="${news.firstImage}" alt="${news.title}"></a></dt>

                            <dd>

                                <h4 class="fyahei"><a href="${path!}/news/detail/${news.id}" target="_blank">${news.title}</a></h4>                                

                            </dd>

                            <dd class="bg_lunbo"></dd>

                        </dl>
                        </#list>
                        </@dataFunction>

                	</div>

                </div>

            </div>

			<script type="text/javascript">movec();</script>

			<!-----------图片切换  end----------->


        </div>

        <div class="topnews_bg fl pr">

        	<div class="topnews borda pa">

               <@dataFunction function="queryRecommendNews" pageNo="1" pageSize="1" reommendRegionId=2>
				<#list functionData.list as news>
                <h2 class="fyahei f18"><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></h2>

                <p class="f14"><#if news.description?? && news.description!="">${news.description}<#else>${news.autoDescription}</#if><a target="_blank"  href="${path!}/news/detail/${news.id}">查看全文</a></p>
              

                <dl>
				</#list>
                        </@dataFunction>

                  <dt>小编推荐</dt>
				   <@dataFunction function="queryRecommendNews" pageNo="1" pageSize="3" reommendRegionId=3>
                        <#list functionData.list as news>

				   		<dd class="f14"><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></dd>
				   </#list>
                        </@dataFunction>
                  

                </dl>

                <dl>

                  <dt>热门点击</dt>

                    <@dataFunction function="queryRecommendNews" pageNo="1" pageSize="3" reommendRegionId=4>
                        <#list functionData.list as news>

					<dd  class="f14"><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></dd>
		
</#list>
                        </@dataFunction>

                </dl>

        	</div>

            <i class="pa"></i>

        </div>

    </div>

    <div class="clearfix">

    	<div class="col1 fl">

            <div class="h3titc mt10">

                <h3 class="pr fyahei">焦点话题</h3>

                <ul>

                    <li class="bordno"><a target="_blank"  href="${path!}/news/list/1">更多 ></a></li>

                </ul>

            </div>

            <div class="borda">

                <ul class="xy_top clearfix">
                   <@dataFunction function="newsBy2Id" pageNo="1" pageSize="4" recommendRegionId="5" channleId="1">
                        <#list functionData.list as news>
                   	<li>
                   			<a class="xy_top_img" target="_blank"  href="${path!}/news/detail/${news.id}"><img src="${news.firstImage}" alt="${news.title}" /></a>
                   			<h4><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></h4>
                   			<p><#if news.description?? && news.description!="">${news.description}<#else>${news.autoDescription}</#if><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}" class="fsong">[详细]</a></p>
                   	</li>
					</#list>
                        </@dataFunction>                  

                </ul>

                <ul class="xy_bot clearfix">
                	 <@dataFunction function="newsBy2Id" pageNo="1" pageSize="6" recommendRegionId="6" channleId="1">
                        <#list functionData.list as news>

				<li><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></li>
			</#list>
                        </@dataFunction>   


                </ul>

            </div>

        </div>

        <div class="col2 fl ml10 mt10">

        	<div class="hottj borda pr pt40">

                <div class="h3tita pa">

                    <h3 class="fyahei">热点推荐</h3>


                </div>

                <ul class="hottj_top">
                   <@dataFunction function="queryRecommendNews" pageNo="1" pageSize="2" reommendRegionId=7>
                        <#list functionData.list as news>
                   	<li class="clearfix">
                   			<img src="${news.firstImage}" alt="${news.title}" />
                   			<h4><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></h4>
                   			<p><#if news.description?? && news.description!="">${news.description}<#else>${news.autoDescription}</#if>...</p>
                   	</li>
					</#list>
                        </@dataFunction>    

                </ul>

                <ul class="hottj_bot">

                   <@dataFunction function="queryRecommendNews" pageNo="1" pageSize="5" reommendRegionId=8>
                        <#list functionData.list as news>
							<li class="f14"><span>${news.formatedCreateDate}</span><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></li>
					   </#list>
                        </@dataFunction>  
                </ul>

            </div>

        </div>

    </div>

    <div class="ad_w970"><a target="_blank"  href="newsPage_getChannleNewsList.action?channleId=1"><img src="${path!}/images/ad_970_1.jpg" alt="关键字" /></a></div>

    <div class="clearfix">

    	<div class="col1 fl">

            <div class="h3titc mt10">

                <h3 class="pr fyahei">校园新闻</h3>

                <ul>

                  <li class="bordno"><a target="_blank"  href="${path!}/news/list/2">更多 ></a></li>

                </ul>

            </div>

            <div class="borda">

                <ul class="xy_top clearfix">
                	<@dataFunction function="newsBy2Id" pageNo="1" pageSize="4" recommendRegionId=5 channleId=2>
                        <#list functionData.list as news>
                   	<li>
                   			<a target="_blank"  href="${path!}/news/detail/${news.id}"><img src="${news.firstImage}" alt="${news.title}" /></a>
                   			<h4><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></h4>
                   			<p><#if news.description?? && news.description!="">${news.description}<#else>${news.autoDescription}</#if><a target="_blank"  href="${path!}/news/detail/${news.id}" class="fsong">[详细]</a></p>
                   	</li>
					</#list>
                        </@dataFunction>

                </ul>

                <ul class="xy_bot clearfix">

                    <@dataFunction function="newsBy2Id" pageNo="1" pageSize="6" recommendRegionId=6 channleId=2>
                        <#list functionData.list as news>

				<li><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></li>
			</#list>
                        </@dataFunction>

                </ul>

            </div>

            <div class="ad_690 mt10"><a target="_blank"  href="#"><img src="${path!}/images/ad_690.jpg" alt="关键字" /></a></div>

            <div class="h3titc mt10">

                <h3 class="pr fyahei">考试升学</h3>

                <ul>

                  <li class="bordno"><a target="_blank"  href="${path!}/news/list/3">更多 ></a></li>

                </ul>

            </div>

            <div class="borda">

                <ul class="topxx clearfix">
                	<@dataFunction function="newsBy2Id" pageNo="1" pageSize="6" recommendRegionId=5 channleId=3>
                        <#list functionData.list as news>

				<li><a target="_blank"  href="${path!}/news/detail/${news.id}"><img src="${news.firstImage}" alt="${news.title}" /></a><p><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></p></li>
			</#list>
                        </@dataFunction>


                </ul>

                <ul class="xy_bot clearfix">

                    <@dataFunction function="newsBy2Id" pageNo="1" pageSize="6" recommendRegionId=6 channleId=3>
                        <#list functionData.list as news>

				<li><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></li>
			</#list>
                        </@dataFunction>

                </ul>

            </div>

        </div>

        <div class="col2 fl ml10">

            <div class="ph mt10 bgwhite">

                <div class="h3titb">

                    <h3 class="fyahei">人气排行</h3>

                </div>

                <div class="borda">

                    <div class="ad_col2_1 borda mt10"><a target="_blank"  href="#"><img src="${path!}/images/img_268_88.jpg" /></a></div>

                    <ul>
						 <@dataFunction function="newsOrderByPv" pageNo="1" pageSize="10" newsId="0">
                        <#list functionData.list as news>

				<li><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></li>
			</#list>
                        </@dataFunction>

                    </ul>            

                </div>

            </div>

            <div class="mt10">

                <div class="h3titb">

                    <h3 class="fyahei">地方资讯</h3>

                </div>

                <div class="borda">
                    <!--
                    <ul class="dfzx">

                       <#include "province_list.ftl"> 

                    </ul>
                     -->
                    <ul class="hottj_bot bordno">
                    	<@dataFunction function="queryRecommendNews" pageNo="1" pageSize="12" reommendRegionId=14>
                        <#list functionData.list as news>
							<li><span>${news.formatedCreateDate}</span><a target="_blank"  href="${path!}/news/detail/${news.id}">${news.title}</a></li>
					  </#list>
                        </@dataFunction>


                    </ul>            

                </div>

            </div>

        </div>

    </div>
<#include "link.ftl">
</div>
<#include "foot.ftl">