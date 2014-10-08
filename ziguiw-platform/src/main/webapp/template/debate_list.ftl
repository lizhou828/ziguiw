<#import "pager.ftl" as pager>
<#include "head.ftl">
<!--header E -->
<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
    <div class="mypos fsong"><a href="${path!}/index">子贵首页</a> &gt; <a href="${path!}/study/index"> 互动知识</a> &gt; 辩论</div>
    <div class="col1 fl">
    <@dataFunction function="debateList" pageNo="${(parameter.pageNo[0])!1}" pageSize="15" debateId="0">
    	<div class="newslist">    	 
    		<ul>
    		 <#list functionData.list as debate>
    		     <li><span>${debate.formatedStartTime}</span><a href="${path!}/study/bianlun/detail/${debate.id}">${debate.title}</a></li>
    		 </#list>
        	</ul>
        	
        	<div class="pagenum"><@pager.p page="${functionData.currentPage}" totalpage="${functionData.pageCount}" params='templateName=debate_list.ftl&debateId=0'/></div>
    	</div>
    </@dataFunction>
    </div>
    <div class="col2 fl ml10">
        <div class="ph mt10">
        	<div class="h3titb">
            	<h3 class="fyahei">人气排行</h3>
            </div>
            <@dataFunction function="debatesOrderByPv" pageNo="1" pageSize="10" debateId="0">
            <div class="bordb">
                <ul>
                <#list functionData.list as debate>
                        <li><a href="${path!}/study/bianlun/detail/${debate.id}">${debate.title}</a></li>                  
                </#list>
                </ul>            
            </div>
            </@dataFunction>
        </div>    
        <div class="ad_col2_1 borda mt10"><a href="#"><img src="${path!}/images/img_268_88.jpg" /></a></div>
    </div>        
    <#include "link.ftl">
</div>    
<!--content E -->
<#include "foot.ftl">