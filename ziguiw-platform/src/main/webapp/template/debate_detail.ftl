<#include "head.ftl">
<!--header E -->
<input type="hidden" name="resourceId" id="resourceId" value="${parameter.debateId[0]}"/>
<input type="hidden" name="resourceType" id="resourceType" value="debate"/> 
<script src="${path!}/js/addClickCount.js.jsp"></script>
<script src="${path!}/js/common.js.jsp"></script>
<div class="content clearfix">
	<!--ad01 E -->
    <div class="mypos fsong"><a href="${path!}/index">子贵首页</a> &gt; <a href="${path!}/study/index"> 教育知识</a> &gt; 用户辩论</div>
	    <div class="argue_talk_ban">
	    
	    </div>
	    
	    
	    
	    <div class="argue_title">
	    <@dataFunction function="debate" debateId="${parameter.debateId[0]}">
	    <#list functionData.list as debate>
	    <h1>${debate.title}</h1>
	    <p><b>简介：</b>${debate.description}</p>
	    </div>
	    <div class="red">${parameter.errorMsg[0]}</div>
	    <div class="augre_z l">
	    <h2 class="sum"><span style="">${debate.positiveSupportCount}</span></h2>
	    <p class="augre_gd"><span class="fb">正方观点：</span>${debate.positiveOpinion}</p>
	    </#list>
	    </@dataFunction>
	    <form method="post" action="${path!}/debate/debateOpinion_saveOrUpdate.action" onsubmit='return validateDebateOpinionSave(1);'>
	    <textarea name="debateOpinion.opinion" cols="20" rows="2"  class="area" id='content1'></textarea>
	    <input type="hidden" name="debateOpinion.debate.id" value="${parameter.debateId[0]}"/>
	    <input type="hidden" name="debateOpinion.status" value="1"/>
		<input name="Submit1" type="submit" value="" class="inp_sum2"/>您的留言不能超过200字，请登录后提交。<a href="${path!}/user/register.jsp" target="_blank" class="m-l4 red_f63">注册用户</a>
		</form>
	    <div class="zjly">
	    <h3>最近留言 </h3>
      <div contenteditable="true" style="width:430px;height:400px;overflow:auto;padding:5px">
	    <@dataFunction function="debateOpinionsByDebateId" debateId="${parameter.debateId[0]}" pageSize="1000">
	    <#list functionData.list as debateOpinion>
	    <#if debateOpinion.status = 1>
	    <ul class="zjly_list">	    
	     <li class="l"><a href="${path!}/myhome/${debateOpinion.creatorNick}" class="a-img1"><img alt="" src="${statics["com.zigui.utils.ImageUtils"].getSizedImage('${(debateOpinion.creatorPortrait)!}', '${path!}/images/head.jpg',70)}" width=48 height=48 /></a><a href="${path!}/myhome/${debateOpinion.creatorNick}">${debateOpinion.creatorNick}</a></li>
	    <li class="zjly_border"><p>${debateOpinion.opinion}</p>
	     ${debateOpinion.formatedCreateTime}
        <var></var>
	    </li>
	    </ul>
	    </#if>	   
	    </#list>
	    </@dataFunction>
      </div>
	    </div>
	    </div>      
	    <div class="augre_f r">
	    <@dataFunction function="debate" debateId="${parameter.debateId[0]}">
	    <#list functionData.list as debate>
	    <h2 class="sum"><span>${debate.negativeSupportCount}</span></h2>
	    <p class="augre_gd"><span class="fb">反方观点：</span>${debate.negativeOpinion}</p>
        </#list>
	    </@dataFunction>
	     <form method="post" action="${path!}/debate/debateOpinion_saveOrUpdate.action" onsubmit='return validateDebateOpinionSave(2);'>
	    <textarea name="debateOpinion.opinion" cols="20" rows="2"  class="area" id='content2'></textarea>
	    <input type="hidden" name="debateOpinion.debate.id" value="${parameter.debateId[0]}"/>
	    <input type="hidden" name="debateOpinion.status" value="2"/>
		<input name="Submit1" type="submit" value="" class="inp_sum2"/>您的留言不能超过200字，请登录后提交。<a href="${path!}/user/register.jsp" target="_blank" class="m-l4 red_f63">注册用户</a>
		</form>
	    <div class="zjly">
	    <h3>最近留言 </h3>
	    <div contenteditable="true" style="width:430px;height:400px;overflow:auto;padding:5px">
	     <@dataFunction function="debateOpinionsByDebateId" debateId="${parameter.debateId[0]}" pageSize="1000">
	    <#list functionData.list as debateOpinion>
	    <#if debateOpinion.status = 2>
	    <ul class="zjly_list">
	   
	     <li class="l"><a href="${path!}/myhome/${debateOpinion.creatorNick}" class="a-img1"><img alt="" src="${statics["com.zigui.utils.ImageUtils"].getSizedImage('${(debateOpinion.creatorPortrait)!}', '${path!}/images/head.jpg',70)}" width=48 height=48 /></a><a href="${path!}/myhome/${debateOpinion.creatorNick}">${debateOpinion.creatorNick}</a></li>
	    <li class="zjly_border"><p>${debateOpinion.opinion}</p>
	     ${debateOpinion.formatedCreateTime}
        <var></var>
	    </li>

	    </ul>
	    </#if>	   
	    </#list>
	    </@dataFunction>
	    </div>
	    </div>	
	    </div>      
    <#include "link.ftl">
</div>    
<!--content E -->
<#include "foot.ftl">