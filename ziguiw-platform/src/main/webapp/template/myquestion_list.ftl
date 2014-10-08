<#import "pager.ftl" as pager>
<#include "head.ftl">
<!--header E -->

<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
    <div class="mypos fsong"><a href="${path!}/index">子贵首页</a> &gt; <a href="${path!}/study/index">教育知识</a> &gt; 育儿问答</div>
    <div class="col1 fl">
    	<div class="newslist ask_question">
    	<h2 class="col-h3">
    	<#assign ps="${(parameter.status[0])!}">
    	<#if ps = "0">
	    	<a href="${path!}/study/yuer/list/0"  target="_self" class="on">待解决问题<span></span></a>
	    	<a href="${path!}/study/yuer/list/1"  target="_self">已解决问题<span></span></a>
	    	<a href="${path!}/study/yuer/list/3"  target="_self">我提的问题<span></span></a>
	    	<#elseif ps = "1">
	    	<a href="${path!}/study/yuer/list/0"  target="_self">待解决问题<span></span></a>
	    	<a href="${path!}/study/yuer/list/1"  target="_self" class="on">已解决问题<span></span></a>
	    	<a href="${path!}/study/yuer/list/3"  target="_self">我提的问题<span></span></a>
	    	<#else>
	    	<a href="${path!}/study/yuer/list/0"  target="_self">待解决问题<span></span></a>
	    	<a href="${path!}/study/yuer/list/1"  target="_self">已解决问题<span></span></a>
	    	<a href="${path!}/study/yuer/list/3"  target="_self" class="on">我提的问题<span></span></a>	
    	</#if>    	    	  	                                                 
    	</h2>
      <#assign uid="${session.VALID_USER.id}">
    	<@dataFunction function="myQuestions" pageNo="${(parameter.pageNo[0])!1}" pageSize="24" creatorId="${uid}">
    		<ul class="ul_sign_3">
    		<#list functionData.list as question>
                <li><span>${question.point}</span><a href="${path!}/study/yuer/detail/${question.id}">${question.title}</a></li>
            </#list>
        	</ul>
        	<div class="pagenum"><@pager.p page="${functionData.currentPage}" totalpage="${functionData.pageCount}" params='templateName=myquestion_list.ftl&creatorId=${uid}'/></div>
    	 </@dataFunction>
    	</div>
    </div>
    <div class="col2 fl ml10">
    <div class="clearfix">
    <a href="${path!}/study/yuer/ask" target="_blank" class="ask l"><img  alt="我有问题" src="${path!}/images/learn_ask.png"/></a>
    <a href="${path!}/study/yuer/list/0" target="_blank" class="answer r"><img  alt="我来解答" src="${path!}/images/learn_answer.png"/></a>
    </div>
	 <div class="ph mt10">
        	<div class="h3titb">
            	<h3 class="fyahei">人气关注</h3>
            </div>
            <div class="bordb">
            <@dataFunction function="questionsOrderByPv" pageNo="1" pageSize="10" questionId="0">
                <ul>
                <#list functionData.list as question>
                  <li><a href="${path!}/study/yuer/detail/${question.id}">${question.title}</a></li>
                </#list>
                </ul>
               </@dataFunction>            
            </div>
        </div>

        <div class="ad_col2_1 borda mt10"><a href="#"><img src="${path!}/images/img_268_88.jpg" /></a></div>
        <div class="mt10">
        	<div class="h3titb">
            	<h3 class="fyahei">答疑高手</h3>
            </div>
            <div class="bordb dygs">
                <ul class="img_f">
                   <@dataFunction function="usersByRecommendRegionId" pageNo="1" pageSize="9" recommendRegionId="102">
	                <#list functionData.list as user>
	                   <li><a href="${path!}/myhome/${user.nickName}" class="a-img1"><img alt="${user.nickName}" src="${statics["com.zigui.utils.ImageUtils"].getSizedImage('${(user.portrait)!}', '${path!}/images/head.jpg',70)}" width=65 height=65/></a><a href="${path!}/myhome/${user.nickName}">${user.nickName}</a></li>
	                </#list>
	               </@dataFunction>                       
                </ul>            
            </div>
        </div>
    </div>        
    <#include "link.ftl">
</div>    
<!--content E -->
<#include "foot.ftl">