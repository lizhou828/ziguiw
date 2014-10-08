<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="<%=path%>/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
    <div class="mypos fsong"><a href="#">子贵首页</a> &gt; <a href="#">教育知识</a> &gt; 育儿问答</div>
    <div class="col1 fl">
    	<div class="newslist ask_question">
    	<h2 class="col-h3">
    	<a href="<%=path%>/study/yuer/0"  target="_self" <s:if test="#parameters.queryType[0]==2">class="on"</s:if>>待解决问题<span></span></a>
    	<a href="<%=path%>/study/yuer/1"  target="_self" <s:if test="#parameters.queryType[0]==3">class="on"</s:if>>已解决问题<span></span></a>
    	<%if(user != null && user.getId() != 0) {%><a href="<%=path%>/ask/question_list.jsp?queryType=4"  target="_self" <s:if test="#parameters.queryType[0]==4">class="on"</s:if>>我的问题<span></span></a><%} %>
    	                                                 
    	</h2>
    		<ul class="ul_sign_3">
    			<s:action var="question_listByCondition" name="question_listByCondition" namespace="/question" executeResult="false" ignoreContextParams="false"></s:action>
    			<s:iterator value="#question_listByCondition.questions.list" var="question">
    				<li><span>3</span><a href="<%=path%>/question/question_getById.action?questionId=<s:property value="#question.id"/>"><s:property value="#question.title"/></a></li>
    			</s:iterator>
                
        	</ul>
        	<div class="pagenum"><em>1</em><a href="/category-0-2.html">2</a><a href="/category-0-3.html">3</a><a href="/category-0-4.html">4</a><a href="/category-0-5.html">5</a><a href="/category-0-6.html">6</a><a href="/category-0-7.html">7</a><a href="/category-0-8.html">8</a>...<a href="/category-0-19.html">19</a><a href="/category-0-2.html" class="btnnum btnnum_no">下一页</a></div>
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
                    <li><a href="#" class="a-img1"><img alt="" src="<%=path%>/images/dygs.jpg" /></a><a href="#">小狐狸</a></li>
                    <li><a href="#" class="a-img1"><img alt="" src="<%=path%>/images/dygs.jpg" /></a><a href="#">小狐狸</a></li>
                    <li><a href="#" class="a-img1"><img alt="" src="<%=path%>/images/dygs.jpg" /></a><a href="#">小狐狸</a></li>
                    <li><a href="#" class="a-img1"><img alt="" src="<%=path%>/images/dygs.jpg" /></a><a href="#">小狐狸</a></li>
                    <li><a href="#" class="a-img1"><img alt="" src="<%=path%>/images/dygs.jpg" /></a><a href="#">小狐狸</a></li>
                    <li><a href="#" class="a-img1"><img alt="" src="<%=path%>/images/dygs.jpg" /></a><a href="#">小狐狸</a></li>
                    <li><a href="#" class="a-img1"><img alt="" src="<%=path%>/images/dygs.jpg" /></a><a href="#">小狐狸</a></li>
                    <li><a href="#" class="a-img1"><img alt="" src="<%=path%>/images/dygs.jpg" /></a><a href="#">小狐狸</a></li>                  
                </ul>            
            </div>
        </div>
    </div>        


   <%@ include file="../inc/foot.jsp"%>