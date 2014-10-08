
<%@ page language="java" pageEncoding="utf-8"%>
<%
    String mainDomain = "http://www.ziguiw.com";
%>
<div class="new_header">

    <a class="logo" href="${pageContext.request.contextPath}" title=""><img src="${pageContext.request.contextPath}/images/logo.jpg" alt=""></a>

    <form action="http://www.ziguiw.com/search.action">

        <div class="search">

            <input type="text" class="text" name="keyWords" value="请输入搜索关键词...">

            <input type="submit" class="btn" name="" value="搜索">

        </div>
    </form>

    <div class="nav">

        <ul class="nav_tit clearfix">

            <li id="index_tab1" class=""><a href="${pageContext.request.contextPath}/index.jsp" title="首   页">首   页</a></li>

            <li id="index_tab2" class=""><a href="${pageContext.request.contextPath}/news/index" title="教育在线">教育在线</a></li>

            <li id="index_tab3" class=""><a href="${pageContext.request.contextPath}/study/index" title="教育知识">教育知识</a></li>

            <li id="index_tab4" class=""><a href="${pageContext.request.contextPath}/source/sourceInfo.action" title="教育资源">教育资源</a></li>

            <li id="index_tab5" class=""><a href="${pageContext.request.contextPath}/community/index" title="互动社区">互动社区 <img src="/images/new.png" alt=""></a></li>

            <li id="index_tab6" class=""><a href="${pageContext.request.contextPath}/user/home_index.jsp" title="我的小家">我的小家</a></li>

        </ul>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
        <script language="javascript">
            $(".nav_tit li").hover(
                    function(){
                        $(".nav_tit li:eq(0)").removeClass("cur");
                        $(this).addClass("cur");

                    },
                    function(){

                        $(this).removeClass("cur");
                    }
            );
            function aaa() {
                var url = self.location.href;

                if(url.indexOf('news')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').setAttribute('class','cur');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').removeAttribute('class');
                }else if(url.indexOf('study')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').setAttribute('class','cur');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').removeAttribute('class');
                }else if(url.indexOf('source')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').setAttribute('class','cur');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').removeAttribute('class');
                }else if(url.indexOf('community')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').setAttribute('class','cur');
                    document.getElementById('index_tab6').removeAttribute('class');
                }else if(url.indexOf('home')>0){
                    document.getElementById('index_tab1').removeAttribute('class');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').setAttribute('class','cur');
                }else{
                    document.getElementById('index_tab1').setAttribute('class','cur');
                    document.getElementById('index_tab2').removeAttribute('class');
                    document.getElementById('index_tab3').removeAttribute('class');
                    document.getElementById('index_tab4').removeAttribute('class');
                    document.getElementById('index_tab5').removeAttribute('class');
                    document.getElementById('index_tab6').removeAttribute('class');
                }
            }
            aaa();
        </script>
        <ul class="nav_sub_tit">

            <li>

                <a href="${pageContext.request.contextPath}/news/list/1" title="焦点话题" target="_blank">焦点话题</a>

                <a href="${pageContext.request.contextPath}/news/list/2" title="校园新闻" target="_blank">校园新闻</a>

                <a href="${pageContext.request.contextPath}/news/list/3" title="考试升学" target="_blank">考试升学</a>

            </li>

            <li>

                <a href="${pageContext.request.contextPath}/study/youer/list" title="幼儿期" target="_blank">幼儿期</a>

                <a href="${pageContext.request.contextPath}/study/shaoer/list" title="少儿期" target="_blank">少儿期</a>

                <a href="${pageContext.request.contextPath}/study/qingnian/list" title="青年期" target="_blank">青年期</a>

                <a href="${pageContext.request.contextPath}/study/psychology/list" title="心理咨询" target="_blank">心理咨询</a>

                <a href="${pageContext.request.contextPath}/study/yuer/list/0" title="育儿问答" target="_blank">育儿问答</a>

                <a href="${pageContext.request.contextPath}/study/bianlun/list" title="辩论" target="_blank">辩论</a>

            </li>

            <li>

                <a href="${pageContext.request.contextPath}/community/age/list" title="同龄圈" target="_blank">同龄圈</a>

                <a href="${pageContext.request.contextPath}/community/city/list" title="同城圈" target="_blank">同城圈</a>

                <a href="${pageContext.request.contextPath}/community/jiaoliu/list" title="学习交流" target="_blank">学习交流</a>

                <a href="${pageContext.request.contextPath}/community/zhuanjia/list" title="专家问答" target="_blank">专家问答</a>

                <a href="${pageContext.request.contextPath}/community/aixin/list" title="爱心站" target="_blank">爱心站</a>

            </li>


        </ul>

    </div>

    <!--nav E -->

</div>