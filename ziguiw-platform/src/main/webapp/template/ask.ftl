<#include "head.ftl">
<!--header E -->
<script src="${path!}/js/common.js.jsp"></script>
<script src="${path!}/js/validate.js"></script>
<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
    <div class="mypos fsong"><a href="${path!}/index">子贵首页</a> &gt; <a href="${path!}/study/index">教育知识</a> &gt; <a href="#">育儿问答</a>&gt; 我要提问</div>
    <div class="col1 ask_and_sea fl">
       	<h2 class="col-h3">我要提问</h2>
		<form method="post" action="${path!}/question/question_save.action" id='myform' onsubmit='return validateQuestionSave();'>
		<div class="red">${parameter.errorMsg[0]}</div>
		<ul class="link_gray ul-li-s">
		<li><span>您的问题：</span><input type="text" value="" class="inp_txt" name="question.title" id='title'><label class="red_b9 m-l4">*</label></li>
		<li><span>悬赏积分：</span><input type="text"  class="inp_txt" name="question.point" id='point'></li>
		<li><span>开放天数：</span><input type="text"  class="inp_txt" name="question.pendingTime" id='pendingTime'><label class="red_b9 m-l4">*</label>开放天数必须小于90天</li>
		<li><textarea class="area" rows="6" cols="72" name="question.content" id='content1'></textarea></li>
		<li>您最多可以输入800个字符</li>
		<li class="a-r"><input type="submit" class="btn2"" value="提交问题" name="Button1" ></li>
		</ul>
		</form>  
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