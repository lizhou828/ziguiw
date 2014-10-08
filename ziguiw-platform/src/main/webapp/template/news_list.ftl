<#import "pager.ftl" as pager>
<#include "head.ftl">
<#assign cid="${parameter.channleId[0]}">
 <#if cid = "1">
	      <#assign urlstr="youer">
	      <#assign namestr="焦点话题">
	  <#elseif cid = "2">
	      <#assign urlstr="shaoer">
	      <#assign namestr="校园新闻">
	  <#elseif cid = "3">
	       <#assign urlstr="qingnian">
	       <#assign namestr="考试升学">
        <#else>
           <#assign urlstr="others">
           <#assign namestr="其他">
</#if>
<div class="content clearfix">

	<div class="ad01">

		<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>

	</div>

	<!--ad01 E -->

    <div class="mypos fsong"><a href="${path!}/index">子贵首页</a> > <a href="${path!}/news/index">教育在线</a> > ${namestr}</div>

    <div class="col1 fl">

    	<div class="newslist">

    		<ul>
				<@dataFunction function="queryChannleNews" pageNo="${(parameter.pageNo[0])!1}" pageSize="15" channleId="${parameter.channleId[0]}">
                        <#list functionData.list as news>

				<li><span>${news.formatedStartTime?substring(0,10)}</span><a href="${path!}/news/detail/${news.id}">${news.title}</a></li>
</#list>
                        
        	</ul>
        	<div class="pagenum"><@pager.p page="${functionData.currentPage}" totalpage="${functionData.pageCount}" params='templateName=news_list.ftl&channleId=${parameter.channleId[0]}'/></div>
</@dataFunction>

    	</div>

    </div>

    <div class="col2 fl ml10">

    	<div class="hottj borda pr pt40">

        	<div class="h3tita pa">

            	<h3 class="fyahei">热点推荐</h3>

            </div>

            <ul class="hottj_top">
<@dataFunction function="queryRecommendNews" pageNo="1" pageSize="2" reommendRegionId=11>
                        <#list functionData.list as news>
            	<li class="clearfix">

                	<img src="${news.firstImage}" alt="${news.title}" />

                    <h4><a href="${path!}/news/detail/${news.id}">${news.title}</a></h4>

                    <p><#if news.description?? && news.description!="">${news.description}<#else>${news.autoDescription}</#if>...</p>

                </li>
</#list>
                        </@dataFunction>   

            </ul>

            <ul class="hottj_bot">
<@dataFunction function="queryRecommendNews" pageNo="1" pageSize="3" reommendRegionId=12>
                        <#list functionData.list as news>
                <li><span>${(news.formatedCreateTime)?substring(0,10)}</span><a href="${path!}/news/detail/${news.id}">${news.title}</a></li>

</#list>
                        </@dataFunction>   

            </ul>

        </div>

        <div class="ad_col2_1 borda mt10"><a href="#"><img src="${path!}/images/img_268_88.jpg" /></a></div>

        <div class="ph mt10">

        	<div class="h3titb">

            	<h3 class="fyahei">人气排行</h3>

            </div>

            <div class="bordb">

                <ul>
<@dataFunction function="newsOrderByPv" pageNo="1" pageSize="10" newsId="0">
                        <#list functionData.list as news>
                    <li><a href="${path!}/news/detail/${news.id}">${news.title}</a></li>

</#list>
                        </@dataFunction>   
                </ul>            

            </div>

        </div>

    </div>        
<#include "link.ftl">
</div>
<#include "foot.ftl">