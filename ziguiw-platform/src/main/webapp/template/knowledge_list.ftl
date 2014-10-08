<#import "pager.ftl" as pager>
<#include "head.ftl">
<!--header E -->
<#assign cid="${parameter.channleId[0]}">
 <#if cid = "1">
	      <#assign urlstr="youer">
	      <#assign namestr="幼儿期">
	  <#elseif cid = "2">
	      <#assign urlstr="shaoer">
	      <#assign namestr="少儿期">
	  <#elseif cid = "3">
	       <#assign urlstr="qingnian">
	       <#assign namestr="青年期">
       <#elseif cid = "4">
            <#assign urlstr="psychology">
            <#assign namestr="心理咨询">
        <#else>
           <#assign urlstr="others">
           <#assign namestr="其他">
</#if>
<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
    <div class="mypos fsong"><a href="${path!}/index">子贵首页</a> &gt; <a href="${path!}/study/index">教育知识</a> &gt; ${namestr!}</div>
    <div class="col1 fl">
    <@dataFunction function="channleKnowledges" pageNo="${(parameter.pageNo[0])!1}" pageSize="15" channleId="${parameter.channleId[0]}">
    	<div class="newslist">    	 
    		<ul>
    		 <#list functionData.list as knowledge>
    		     <li><span>${(knowledge.formatedStartTime)?substring(0,10)}</span><a href="${path!}/study/${urlstr}/detail/${knowledge.id}">${knowledge.title}</a></li>
    		 </#list>
        	</ul>
        	
        	<div class="pagenum"><@pager.p page="${functionData.currentPage}" totalpage="${functionData.pageCount}" params='templateName=knowledge_list.ftl&channleId=${parameter.channleId[0]}'/></div>
    	</div>
    </@dataFunction>
    </div>
    <div class="col2 fl ml10">
    	<div class="hottj borda pr pt40 rdwd">
        	<div class="h3tita pa">
            	<h3 class="fyahei">热点推荐</h3>
            </div>
                   <ul class="hottj_top">
                   <@dataFunction function="recommendKnowledges" pageNo="1" pageSize="2" recommendRegionId="1">
                        <#list functionData.list as knowledge>
			            	<li class="clearfix">
			
			                	<img src="${(knowledge.firstImage)!}" alt="${knowledge.title}" />
			                    <#if knowledge.knowledgeChannle.id = 1>
			                    <h4><a href="${path!}/study/youer/detail/${knowledge.id}">${knowledge.title}</a></h4>
			                    <#elseif knowledge.knowledgeChannle.id = 2>
									      <h4><a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a></h4>
							    <#elseif knowledge.knowledgeChannle.id = 3>
									       <h4><a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a></h4>
								<#elseif knowledge.knowledgeChannle.id = 4>
									       <h4><a href="${path!}/study/psychology/detail/${knowledge.id}">${knowledge.title}</a></h4>       
							    <#else>
							          <h4><a href="${path!}/study/others/detail/${knowledge.id}">${knowledge.title}</a></h4>
								</#if>			
			                    <p><#if knowledge.description?? || knowledge.description!="">${knowledge.description}<#else>${knowledge.autoDescription}</#if>...</p>
			
			                </li>
                         </#list>
                    </@dataFunction>   

            </ul>

            <ul class="hottj_bot">
				<@dataFunction function="recommendKnowledges" pageNo="1" pageSize="3" recommendRegionId="4">
				    <#list functionData.list as knowledge>
				                <li><span>${knowledge.formatedStartTime}</span>
				                <#if knowledge.knowledgeChannle.id = 1>
			                    <a href="${path!}/study/youer/detail/${knowledge.id}">${knowledge.title}</a>
			                    <#elseif knowledge.knowledgeChannle.id = 2>
								<a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a>
								<#elseif knowledge.knowledgeChannle.id = 3>
								<a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a>
								<#elseif knowledge.knowledgeChannle.id = 4>
								<a href="${path!}/study/psychology/detail/${knowledge.id}">${knowledge.title}</a>
								<#else>
								<a href="${path!}/study/others/detail/${knowledge.id}">${knowledge.title}</a>
								</#if>					                			                
				                </li>				
				    </#list>
				</@dataFunction>
            </ul>

        </div>
        <div class="ad_col2_1 borda mt10"><a href="#"><img src="${path!}/images/img_268_88.jpg" /></a></div>
        <div class="ph mt10">
        	<div class="h3titb">
            	<h3 class="fyahei">人气排行</h3>
            </div>
            <@dataFunction function="knowledgesOrderByPv" pageNo="1" pageSize="10" knowledgeId="0">
            <div class="bordb">
                <ul>
                <#list functionData.list as knowledge>
                        <#if knowledge.knowledgeChannle.id = 1>
			                    <li><a href="${path!}/study/youer/detail/${knowledge.id}">${knowledge.title}</a></li>
			                    <#elseif knowledge.knowledgeChannle.id = 2>
							    <li><a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a></li>
							    <#elseif knowledge.knowledgeChannle.id = 3>
							    <li><a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a></li>
							    <#elseif knowledge.knowledgeChannle.id = 4>
							    <li><a href="${path!}/study/psychology/detail/${knowledge.id}">${knowledge.title}</a></li>
							    <#else>
							    <li><a href="${path!}/study/others/detail/${knowledge.id}">${knowledge.title}</a></li>
						</#if>	
                    
                </#list>
                </ul>            
            </div>
            </@dataFunction>
        </div>
    </div>        
    <#include "link.ftl">
</div>    
<!--content E -->
<#include "foot.ftl">