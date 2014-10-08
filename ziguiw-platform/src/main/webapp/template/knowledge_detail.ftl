<#include "head.ftl">
<!--header E -->
<input type="hidden" name="resourceId" id="resourceId" value="${parameter.knowledgeId[0]}"/>
<input type="hidden" name="resourceType" id="resourceType" value="knowledge"/> 
<script src="${path!}/js/addClickCount.js.jsp"></script>
<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
	<@dataFunction function="knowledge" knowledgeId="${parameter.knowledgeId[0]}">	
     <#list functionData.list as knowledge>
     <#assign cid="${knowledge.knowledgeChannle.id}">
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
    <div class="mypos fsong"><a href="${path!}/">子贵首页</a> > <a href="${path!}/study/index">教育知识</a> > <a href="${path!}/study/${urlstr}/list">${knowledge.knowledgeChannle.name}</a> >正文</div>
    <div class="col1 fl">
       
    	<div class="newscot">
    		<h1 class="fyahei">${knowledge.title}</h1>
            <p class="news_tip fb"><span>时间：${(knowledge.formatedStartTime)?substring(0,10)}</span><span>来源：${knowledge.editors}</span><span>编辑： ${knowledge.creatorNick}</span></p>
            <div class="news_info">
            	<strong>内容摘要：</strong>
                <p>${knowledge.description}</p>
            </div>
            <div class="newscots">
                ${knowledge.content}
            </div>
        	<div class="news_kwords">关键词：${knowledge.keywords}</div>
    	</div>
        </#list>
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
			
			                	<img src="${knowledge.firstImage}" alt="${knowledge.title}" />
			                    <#if knowledge.knowledgeChannle.id = "1">
			                    <h4><a href="${path!}/study/youer/detail/${knowledge.id}">${knowledge.title}</a></h4>
			                    <#else>
									    <#if knowledge.knowledgeChannle.id = "2">
									      <h4><a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a></h4>
									      <#else>
									       <h4><a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a></h4>
									     </#if>
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
			                    <#else>
									    <#if knowledge.knowledgeChannle.id = 2>
									      <a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a>
									      <#else>
									       <a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a>
									     </#if>
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
			                    <#else>
									    <#if knowledge.knowledgeChannle.id = 2>
									      <li><a href="${path!}/study/shaoer/detail/${knowledge.id}">${knowledge.title}</a></li>
									      <#else>
									       <li><a href="${path!}/study/qingnian/detail/${knowledge.id}">${knowledge.title}</a></li>
									     </#if>
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