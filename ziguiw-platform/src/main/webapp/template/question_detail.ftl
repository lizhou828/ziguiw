<#include "head.ftl">
<!--header E -->
<input type="hidden" name="resourceId" id="resourceId" value="${parameter.questionId[0]}"/>
<input type="hidden" name="resourceType" id="resourceType" value="question"/> 
<script src="${path!}/js/addClickCount.js.jsp"></script>
<script src="${path!}/js/common.js.jsp"></script>
<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
    <div class="mypos fsong"><a href="${path!}/index">子贵首页</a> &gt; <a href="${path!}/study/index">教育知识</a> &gt; <a href="${path!}/study/yuer/list/0">育儿问答</a>&gt; 问答内容</div>
    <div class="col1 ask_info fl"> <#assign uid="${session.VALID_USER.id}">
    <@dataFunction function="question" pageNo="1" pageSize="24" questionId="${parameter.questionId[0]}">
        <div class="question">
        <#list functionData.list as question>
        <#assign creatorId="${question.creatorId}">
       
         	<dl class="col-h3">
	    	<dt>${question.title}</dt>
	    	<dd><span>提问者：<label class="red_f63">${question.creatorNick}</label></span>
	    	<span>浏览次数：<label class="red_f63">${question.clickCount}</label>次</span>
	    	<span>回答数：<label class="red_f63">${question.answerCount}</label>个</span> 
	    	<span>提问时间：<label class="red_f63">
	    	<#assign date1="${question.formatedStartTime}"?date("yyyy-MM-dd")>
	    	${date1}
	    	</label></span>
	    	<span>最后回答时间：<label class="red_f63">	    	
	    	<#assign date2="${question.formatedLTime}"?date("yyyy-MM-dd")>
	    	${date2}
	    	</label></span>
	    	<span>悬赏分：<label class="red_f63">${question.point}<img alt="" src="${path!}/images/learn_jb2.png" class="m-l4"/></label></span></dd>
	    	</dl>         		      
	    	<p>${question.content}</p>
	    	<#assign bestAnswerId="${question.bestAnswerId}">
	    	<#assign status="${question.status}">
	    	</#list>
	    	<#if creatorId!=uid && status=='0'>
	    	 <form method="post" action="${path!}/question/answer_save.action" onsubmit='return validateAnswerSave();'>
	    	<ul>
	    	<li><img alt="" src="${path!}/images/learn_FAQ_wlhd.gif"/></li>
	    	<li><input name="answer.content" type="text" class="textarea" id='content1'/></li>
			<input name="answer.question.id" type="hidden" value="${parameter.questionId[0]}" class="inp_sum"/>
	    	<li class="a-r">请登录后再回答问题！您每回答一个育儿问题，都可获得相应积分奖励。
	    	<input name="Submit1" type="submit" value="" class="inp_sum"/></li>
	    	</ul>
	    	</form>
	    	</#if>
    	</div>
    </@dataFunction>
    <@dataFunction function="answer" pageNo="1" pageSize="24" answerId="${bestAnswerId}">
       	<div class="good_ask">
		<h2 class="col-h3"><span>满意答案</span></h2>
		<#list functionData.list as answer>
		<p>${answer.content}</p>
		</#list>    	
    	</div>
    </@dataFunction>
    <@dataFunction function="answersByQuestionId" pageNo="1" pageSize="24" questionId="${parameter.questionId[0]}">
    
       	<div class="other_ask">
		<h2 class="col-h3"><span>其他答案</span></h2>
		<#list functionData.list as answer>
		<dl>
    	<dt>答案提供者：<label class="red_f63">${answer.creatorNick}</label>  回答时间：
    	<label class="red_f63">
    	<#assign date3="${answer.formatedCreateTime}"?date("yyyy-MM-dd")>
	    ${date3}
    	</label></dt>
    	<dd>${answer.content}</dd>
    	<#if creatorId==uid>
    	<a href="${path!}/question/question_satisfied.action?question.bestAnswerId=${answer.id}&question.id=${parameter.questionId[0]}"><input type="button" value="采纳该答案" class="btn2"/></a>
    	</#if>
    	</dl>
		</#list> 	
    	</div>
    </@dataFunction>
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