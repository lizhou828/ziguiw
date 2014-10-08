<#include "head.ftl">
<!--header E -->

<div class="content clearfix">
	<div class="ad01">
		<@dataFunction function="getAdPlan" pageNo="1" pageSize="1" adId=1>
        <#list functionData.list as adPlan>
			<a href="${adPlan.link}" title=""><img src="${adPlan.imageUrl}" alt=""/></a>
		</#list>
		<#if functionData.list??>
			<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>
		</#if>
        </@dataFunction>
	</div>
	<!--ad01 E -->
    <div class="clearfix">
    	<div class="lunbo2 fl pr">
          <div class="sub_box_1">

                <div id="p-select" class="sub_nav_1">

                    <div class="sub_no" id="bd1lfsj">

                        <ul>

                            <li class="show">1</li><li class="">2</li><li class="">3</li><li class="">4</li><li class="">5</li>

                        </ul>

                    </div>

                </div>

                <div id="bd1lfimg_1">

                    <div>

                        <dl class="show"></dl>
                       <@dataFunction function="recommendKnowledges" pageNo="1" pageSize="5" recommendRegionId=6>
                        <#list functionData.list as knowledge>
                        <dl class="">
							
                            <dt><a href="${path!}/study/detail/${knowledge.id}" title="" target="_blank"><img src="${(knowledge.firstImage)!}" alt="${knowledge.title}"></a></dt>

                            <dd>

                                <h4 class="fyahei"><a href="${path!}/study/detail/${knowledge.id}" target="_blank">${knowledge.title}</a></h4>                                

                            </dd>

                            <dd class="bg_lunbo"></dd>

                        </dl>
                        </#list>
                        </@dataFunction>

                	</div>

                </div>

            </div>

			<script type="text/javascript">movec_1();</script>
        </div>
        <div class="learn_bg fl pr">
        	<div class="learn borda pa">
        	<@dataFunction function="debatesOrderByPv" pageNo="1" pageSize="1" debateId="0">
        	 <#list functionData.list as debate>
                <h2 class="fyahei f18"><a href="${path!}/study/bianlun/detail/${debate.id}">
                <#if ((debate.title)?length) lt 15>
                  ${debate.title}
                  <#else>
                  ${(debate.title)?substring(0,15)}...
                </#if>
              
                </a></h2>
                <p class="f14">
                   <#if ((debate.description)?length) lt 90>
                  ${(debate.description)!}
                  <#else>
                  ${(debate.description)?substring(0,90)}...
                </#if>
                
   
                <a href="${path!}/study/bianlun/detail/${debate.id}">参与辩论</a></p>
				<div class="talk_vs">
					<div class="talk_z">
						<p>${debate.positiveOpinion}</p>
						<var>支持数</var><sub>${debate.positiveSupportCount}</sub>
					</div>
					
					<div class="talk_f">
						<p>${debate.negativeOpinion}</p>
						<var>支持数</var><sub>${debate.negativeSupportCount}</sub>
					</div>
					<ul class="col-ul talk_time">
						<li><span>发起人</span>${debate.creatorNick}</li>
					</ul>
				</div>
			  </#list>
			</@dataFunction>
        	</div>

        </div>
  <div class="fr w-270 yuer">  
	<div class="hottj borda pr pt40">
		<div class="h3tita pa">
			<h3 class="fyahei">育儿问答</h3>
		</div>
		<@dataFunction function="questionsByStatus" pageNo="1" pageSize="10" status="0">
		<ul class="hottj_bot">
		<#list functionData.list as question>
		<li><span>
		<#assign date1="${question.formatedStartTime}"?date("yyyy-MM-dd")>
	    ${date1}
		</span><a href="${path!}/study/yuer/detail/${question.id}">${question.title}</a></li>
		</#list>
		</ul>
		</@dataFunction>
	</div>

	<div class="ad_col2_1 borda mt5"><a href="#"><img src="${path!}/images/img_268_88.jpg"></a></div>
	</div>
   </div>
<div class="clearfix borda mt10 mb10 jyzx">    
        <div class="h3titc">
            <h3 class="pr fyahei"><a href="${path!}/study/youer/list" title="幼儿期" target="_blank">幼儿期</a></h3>
        </div>
        <div class="fl w-690">
        <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="2" channleId="1" recommendRegionId="2">       
            <ul class="xy_top clearfix">
            <#list functionData.list as knowledge>
            	<li>
                	<a href="${path!}/study/qingnian/detail/${knowledge.id}"><img src="${knowledge.firstImage}" alt="${knowledge.title}" /></a>
                    <h4><a href="${path!}/study/youer/detail/${knowledge.id}">${knowledge.title}</a></h4>
                    <p><#if knowledge.description?? || knowledge.description!="">${knowledge.description}<#else>${knowledge.autoDescription}</#if><a href="${path!}/study/youer/detail/${knowledge.id}" class="fsong">[详细]</a></p>
                </li>
                </#list>
            </ul>            
            </@dataFunction>
            <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="12" channleId="1" recommendRegionId="5"> 
            <ul class="xy_bot clearfix">
            <#list functionData.list as knowledge>
                <li><strong class="fsong"><a href="${path!}/study/youer/list" title="幼儿期" target="_blank">[${knowledge.knowledgeChannle.name}]</a></strong><a href="${path!}/study/youer/detail/${knowledge.id}">${knowledge.title}</a></li>
            </#list>
            </ul>
            </@dataFunction>
        </div>    
        <div class="know_rd ph r">
        <h2 class="col-h2"><b class="l">幼儿期热点知识</b></h2>
		 <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="10" channleId="1" recommendRegionId="3"> 11
            <ul>
            <#list functionData.list as knowledge>
		     <li><a href="${path!}/study/youer/detail/${knowledge.id}">${knowledge.title}</a></li></#list></ul>
            </@dataFunction>
        </div>    
</div>
<div class="ad_w970"><a href="#"><img src="${path!}/images/ad_970_1.jpg" alt="关键字" /></a></div>
<div class="clearfix borda mt10 mb10 jyzx">    
        <div class="h3titc">
            <h3 class="pr fyahei"><a href="${path!}/study/shaoer/list" title="少儿期" target="_blank">少儿期</a></h3>
        </div>
        <div class="fl w-690">
            <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="2" channleId="2" recommendRegionId="2">       
            <ul class="xy_top clearfix">
            <#list functionData.list as knowledge>
            	<li>
                	<a href="${path!}/study/qingnian/detail/${knowledge.id}"><img src="${knowledge.firstImage}" alt="${knowledge.title}" /></a>
                    <h4><a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a></h4>
                    <p><#if knowledge.description?? || knowledge.description!="">${knowledge.description}<#else>${knowledge.autoDescription}</#if><a href="${path!}/study/youer/detail/${knowledge.id}" class="fsong">[详细]</a></p>
                </li>
                </#list>
            </ul>            
            </@dataFunction>
            <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="12" channleId="2" recommendRegionId="5"> 
            <ul class="xy_bot clearfix">
            <#list functionData.list as knowledge>
                <li><strong class="fsong"><a href="${path!}/study/shaoer/list" title="少儿期" target="_blank">[${knowledge.knowledgeChannle.name}]</a></strong><a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a></li>
            </#list>
            </ul>
            </@dataFunction>
        </div>    
        <div class="know_rd ph r">
        <h2 class="col-h2"><b class="l">少儿期热点知识</b></h2>
		 <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="10" channleId="2" recommendRegionId="3"> 
            <ul>
            <#list functionData.list as knowledge>
		     <li><a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a></li>
	        </#list>
            </ul>
            </@dataFunction>
        </div>    
</div>
<div class="ad_w970"><a href="#"><img src="${path!}/images/adv02.jpg" alt="关键字" /></a></div>

<div class="clearfix borda mt10 mb10 jyzx">    
        <div class="h3titc">
            <h3 class="pr fyahei"><a href="${path!}/study/qingnian/list" title="青年期" target="_blank">青年期</a></h3>
        </div>
        <div class="fl w-690">
            <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="2" channleId="3" recommendRegionId="2">       
            <ul class="xy_top clearfix">
            <#list functionData.list as knowledge>
            	<li>
                	<a href="${path!}/study/qingnian/detail/${knowledge.id}"><img src="${knowledge.firstImage}" alt="${knowledge.title}" /></a>
                    <h4><a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a></h4>
                    <p><#if knowledge.description?? || knowledge.description!="">${knowledge.description}<#else>${knowledge.autoDescription}</#if><a href="${path!}/study/youer/detail/${knowledge.id}" class="fsong">[详细]</a></p>
                </li>
                </#list>
            </ul>            
            </@dataFunction>
            <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="12" channleId="3" recommendRegionId="5"> 
            <ul class="xy_bot clearfix">
            <#list functionData.list as knowledge>
                <li><strong class="fsong"><a href="${path!}/study/qingnian/list" title="青年期" target="_blank">[${knowledge.knowledgeChannle.name}]</a></strong><a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a></li>
            </#list>
            </ul>
            </@dataFunction>
        </div>    
        <div class="know_rd ph r">
        <h2 class="col-h2"><b class="l">青年期热点知识</b></h2>
		 <@dataFunction function="knowledgesBy2Id" pageNo="1" pageSize="12" channleId="3" recommendRegionId="3"> 
            <ul>
            <#list functionData.list as knowledge>
		     <li><a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a></li>
	        </#list>
            </ul>
            </@dataFunction>
        </div>    
</div>
       
    
    <#include "link.ftl">
</div>    
<!--content E -->
<#include "foot.ftl">