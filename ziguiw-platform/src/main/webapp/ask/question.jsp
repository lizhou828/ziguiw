<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>



<div class="content clearfix">

	<div class="ad01">

		<a href="#" title=""><img src="<%=path%>/images/adv01.jpg" alt=""/></a>

	</div>

	<!--ad01 E -->

    <div class="mypos fsong"><a href="#">子贵首页</a> &gt; <a href="#">教育知识</a> &gt; <a href="#">育儿问答</a>&gt; 问答内容</div>

    <div class="col1 ask_info fl">

    	<div class="question">

	       	<dl class="col-h3">

	    	<dt>${question.title}</dt>

	    	<dd><span>提问者：<label class="red_f63">刘梅868</label></span>

	    	<span>浏览次数：<label class="red_f63">3</label>次</span>

	    	<span>回答数：<label class="red_f63">9</label>个</span> 

	    	<span>提问时间：<label class="red_f63">2012-06-25</label></span>

	    	<span>解决时间：<label class="red_f63">2012-06-25</label></span>

	    	<span>悬赏分：<label class="red_f63">10<img alt="" src="<%=path%>/images/learn_jb2.png" class="m-l4"/></label></span></dd>

	    	</dl>

	    	<p>${question.content}</p>

	    	<form method="post" action="<%=path%>/question/answer_save.action">

	    	<ul>

	    	<li><img alt="" src="<%=path%>/images/learn_FAQ_wlhd.gif"/></li>

	    	<li><input name="answer.content" type="text" class="textarea"/></li>

			<input name="answer.question.id" type="hidden" value="<s:property value="#parameters.questionId" />" class="inp_sum"/>
	    	<li class="a-r">请登录后再回答问题！您每回答一个育儿问题，都可获得相应积分奖励。
	    	<input name="Submit1" type="submit" value="" class="inp_sum"/></li>

	    	</ul>

	    	</form>

    	</div>

       	<div class="good_ask">

		<h2 class="col-h3"><span>满意答案</span></h2>

    	<p>${bestAnswer.content}</p>

    	</div>

       	<div class="other_ask">

		<h2 class="col-h3"><span>其他答案</span></h2>
		
		<s:action var="answer_getAnswerByQuestionId" name="answer_getAnswerByQuestionId" namespace="/question" executeResult="false" ignoreContextParams="false"></s:action>
			<s:iterator value="#answer_getAnswerByQuestionId.answers.list" var="answer">
				<dl>

		    	<dt>答案提供者：<label class="red_f63"><s:property value="#answer.creatorNick"/></label>回答时间：<label class="red_f63"><s:property value="#answer.formatedCreateTime"/></label></dt>
		
		    	<dd><s:property value="#answer.content"/></dd>
		    	
		    	<a href="<%=path%>/question/question_satisfied.action?question.bestAnswerId=<s:property value="#answer.id"/>&question.id=<s:property value="#parameters.questionId" />"><input type="button" value="采纳该答案" class="btn2"/></a>
		
		    	</dl>
			</s:iterator>

    	</div>


    </div>

    <div class="col2 fl ml10">

    <div class="clearfix">

    <a href="<%=path%>/study/ask.html" target="_blank" class="ask l"><img  alt="我有问题" src="<%=path%>/images/learn_ask.png"/></a>
    <a href="<%=path%>/ask/question_list.jsp?queryType=2" target="_blank" class="answer r"><img  alt="我来解答" src="<%=path%>/images/learn_answer.png"/></a>

    </div>

	 <div class="ph mt10">

        	<div class="h3titb">

            	<h3 class="fyahei">人气关注</h3>

            </div>

            <div class="bordb">

                <ul>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                    <li><a href="#">鲜明对比 大陆最奢华与最简陋教室</a></li>

                </ul>            

            </div>

        </div>



        <div class="ad_col2_1 borda mt10"><a href="#"><img src="<%=path%>/images/img_268_88.jpg" /></a></div>

        <div class="mt10">

        	<div class="h3titb">

            	<h3 class="fyahei">答疑高手</h3>

            </div>

            <div class="bordb dygs">

                <ul class="img_f">

                    <li><a href="#" class="a-img1"><img alt="" src="images/dygs.jpg" /></a><a href="#">小狐狸</a></li>

                    <li><a href="#" class="a-img1"><img alt="" src="images/dygs.jpg" /></a><a href="#">小狐狸</a></li>

                    <li><a href="#" class="a-img1"><img alt="" src="images/dygs.jpg" /></a><a href="#">小狐狸</a></li>

                    <li><a href="#" class="a-img1"><img alt="" src="images/dygs.jpg" /></a><a href="#">小狐狸</a></li>

                    <li><a href="#" class="a-img1"><img alt="" src="images/dygs.jpg" /></a><a href="#">小狐狸</a></li>

                    <li><a href="#" class="a-img1"><img alt="" src="images/dygs.jpg" /></a><a href="#">小狐狸</a></li>

                    <li><a href="#" class="a-img1"><img alt="" src="images/dygs.jpg" /></a><a href="#">小狐狸</a></li>

                    <li><a href="#" class="a-img1"><img alt="" src="images/dygs.jpg" /></a><a href="#">小狐狸</a></li>                  

                </ul>            

            </div>

        </div>

    </div>        

       <%@ include file="../inc/foot.jsp"%>