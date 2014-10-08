

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<!--	<base href="http://www.ziguiw.com:80/"/>-->
		<title>网站地图——子贵网帮助中心</title>
        <meta name="Keywords" content="教育资源,教学课件,考试试卷,教学视频"/>
        <meta name="Description" content="为全国高中小学各学科教师提供展示教学、实现教育资源交易的平台。
        打破教室、教师、校园的界限，实现校校互通、班班互联、资源共享。
        栏目针对高中小院校的各科目及各年级、教材版本的不同进行资源的分类展示。"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="http://www.ziguiw.com/css/basc.css" rel="stylesheet" type="text/css" />
        <link href="../css/footer_css.css" rel="stylesheet" type="text/css" />
		<base target="_blank"/>		
	<script>
        function dj(i)
        {
          for(j=1;j<100;j++)  //遍历刷新所有门的状态，序号与参数相同的的一种状态，其余一种状态
          {
                if(document.getElementById("a"+j)==null){
                    break;
                }
               document.getElementById("a"+j).className="li1";
               document.getElementById("b"+j).style.display="none";
           }
           document.getElementById("a"+i).className="li2";
           document.getElementById("b"+i).style.display="block";

        }
    </script>
	</head>
	<body>
		  
<div class="toolbar">

	<div class="tit">
欢迎来到子贵网！
        </div>

        <div class="subNav">

		
    <a title="用户登录" href="/user/login.jsp" target="_blank">用户登录</a>

    <a title="新用户注册" href="/user/register.jsp" target="_blank">新用户注册</a>

    <a href="http://dsis.ziguiw.com" title="数字化校园平台" target="_blank"><font color="red"><strong>数字化校园平台</strong></font></a>

    <a href="http://dsis.ziguiw.com/downandroid/mobile.jsp" title="手机版" target="_blank">手机版</a>

    <a href="javascript:setHome(window.location);" target="_self">设为首页</a>

    <a href="javascript:addFavorite(window.location, document.title)" target="_self">加入收藏</a>



    <script>
        function addFavorite(sURL, sTitle) {
            try {
                window.external.addFavorite(sURL.toString(), sTitle.toString());
            }
            catch (e) {
                try {
                    window.sidebar.addPanel(sTitle.toString(), sURL.toString(), "");
                }
                catch (e) {
                    alert("加入收藏失败，请使用Ctrl+D进行添加");
                }
            }
        }

        function setHome(vrl) {
            if(document.all){
                document.body.style.behavior='url(#default#homepage)';
                document.body.setHomePage(vrl);
            }
            else if(window.sidebar){
                if(window.netscape){
                    try{
                        netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                    }
                    catch(e){
                        alert("您的浏览器未启用[设为首页]功能，开启方法：先在地址栏内输入about:config,然后将项 signed.applets.codebase_principal_support 值该为true即可");
                    }
                }
                var prefs=Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                prefs.setCharPref('browser.startup.homepage',vrl);
            }
        }
    </script>
</div>
<div class="subNav"> </div>


	


</div>

<!--toolbar E -->



<div class="header">

	<a class="logo" href="#" title=""><img src="../images/logo.jpg" alt=""/></a>

	<form action="/search.action">

	<div class="search">

		<input type="text" class="text" name="keyWords" value="请输入搜索关键词..." id=" "/>

		<input type="submit" class="btn" name="" value="搜索" id=" "/>

	</div>
        </form>
        
        
			
	<div class="nav">

		<ul class="nav_tit clearfix">

			<li id='index_tab1'><a href="/index.jsp" title="首   页" >首   页</a></li>
			
			<li id='index_tab2'><a href="/news/index" title="教育在线" >教育在线</a></li>

			<li id='index_tab3'><a href="/study/index" title="教育知识" >教育知识</a></li>
                        
            <li id='index_tab4'><a href="http://dsis.ziguiw.com/source/sourceInfo.action" title="教育资源" >教育资源</a></li>

			<li id='index_tab5'><a href="/community/index" title="互动社区" >互动社区 <img src="../images/new.png" alt=""/></a></li>

			<li id='index_tab6'><a href="/user/home_index.jsp" title="我的小家" >我的小家</a></li>

		</ul>
           <script language='javascript'>
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

				<a href="/news/list/1" title="焦点话题" target="_blank">焦点话题</a>

				<a href="/news/list/2" title="校园新闻" target="_blank">校园新闻</a>

				<a href="/news/list/3" title="考试升学" target="_blank">考试升学</a>

			</li>

			<li>

				<a href="/study/youer/list" title="幼儿期" target="_blank">幼儿期</a>

				<a href="/study/shaoer/list" title="少儿期" target="_blank">少儿期</a>

				<a href="/study/qingnian/list" title="青年期" target="_blank">青年期</a>
				
				<a href="/study/psychology/list" title="心理咨询" target="_blank">心理咨询</a>

				<a href="/study/yuer/list/0" title="育儿问答" target="_blank">育儿问答</a>

				<a href="/study/bianlun/list" title="辩论" target="_blank">辩论</a>

			</li>

			<li>

				<a href="/community/age/list" title="同龄圈" target="_blank">同龄圈</a>

				<a href="/community/city/list" title="同城圈" target="_blank">同城圈</a>

				<a href="/community/jiaoliu/list" title="学习交流" target="_blank">学习交流</a>
				
				<a href="/community/zhuanjia/list" title="专家问答" target="_blank">专家问答</a>
				
				<a href="/community/aixin/list" title="爱心站" target="_blank">爱心站</a>

			</li>


		</ul>

	</div>

	<!--nav E -->

</div>

<!--header E -->

</div>
	<div class="foot_content">
    <!--banner B-->
    	<div class="f_banner">
        	<img src="../images/banner02.gif" />
        </div>
        <div class="mypos fsong">
            <a href="#">子贵首页</a>  &gt;
            <a href="#">网站地图</a>   &gt;
            <a href="#"></a>   正文
        </div>
    <!--banner E-->
	
    <!--middle B-->
    	<div class="p_middle">
        	<div class="p_middle_left" id="knowqqleft">
            	<ul id="centerleft">
                    <li><a href="about.jsp">关于子贵网</a></li>
                    <li><a href="contract.jsp">服务协议</a></li>
                    <li><a href="service.jsp" >客服中心</a></li>
                    <li><a href="http://dsis.ziguiw.com/feedback/add.jsp">意见反馈</a></li>
                    <li><a href="pin.jsp">网站招聘</a></li>
                    <li><a id="on" href="map.jsp">网站地图</a></li>
                    <li><a href="contact.jsp">联系我们</a></li>
                    <li><a href="copyright.jsp">版权所有</a></li>
                    <li><a href="help.jsp">帮助中心</a></li>
                    <li><a href="flink.jsp">友情链接</a></li>
                    <li><a href="http://dsis.ziguiw.com/topic/cloud/footmark_two.jsp">轨迹查询</a></li>
                    <li><a href="http://dsis.ziguiw.com/topic/cloud/index.jsp">数字校园云服务展会专题</a></li>
                </ul>
            </div>
            <div class="p_middle_right">
                <div class="p_title"><p><em>&#8226;</em>网站地图</p></div>
                <div class="p_con">
                	<div class="c_con_hd">
                    	<p class="map_title"><span>子贵首页</span>-<span>地图内容</span></p>
                    </div>
                    <div class="map_content">
                    	<p><span class="pindao">[子贵网首页]</span> <span class="lanmu"><a href="/index.jsp">子贵网首页</a></span></p>
                        <p><span class="pindao">[教育在线]</span> <span class="lanmu"><a href="/news/list/1">焦点话题</a></span><span class="lanmu"><a href="/news/list/2">校园新闻</a></span><span class="lanmu"><a href="/news/list/3">考试升学</a></span></p>
                        <p><span class="pindao">[教育知识]</span> <span class="lanmu"><a href="/study/youer/list">幼儿期</a></span><span class="lanmu"><a href="/study/shaoer/list">少儿期</a></span><span class="lanmu"><a href="/study/qingnian/list">青年期</a></span><span class="lanmu"><a href="/study/psychology/list">心理咨询</a></span><span class="lanmu"><a href="/study/yuer/list/0">育儿回答</a></span><span class="lanmu"><a href="/study/bianlun/list">辩论</a></span></p>
                        <p><span class="pindao">[教育资源]</span> <span class="lanmu"><a href="http://dsis.ziguiw.com/source/sourceInfo.action">资源首页</a></span><span class="lanmu"><a href="http://dsis.ziguiw.com/source/schoolInfo.action?leve=1">小学资源</a></span><span class="lanmu"><a href="http://dsis.ziguiw.com/source/schoolInfo.action?leve=2">初中资源</a></span><span class="lanmu"><a href="http://dsis.ziguiw.com/source/schoolInfo.action?leve=3">高中资源</a></span></p>
                        <p><span class="pindao">[互动社区]</span> <span class="lanmu"><a href="/community/age/list">同龄圈</a></span><span class="lanmu"><a href="/community/city/list">同城圈</a></span><span class="lanmu"><a href="/community/jiaoliu/list">学习交流</a></span><span class="lanmu"><a href="/community/zhuanjia/list">专家问答</a></span><span class="lanmu"><a href="/community/aixin/list">爱心站</a></span></p>
                        <p><span class="pindao">[我的小家]</span> <span class="lanmu"><a href="/user/home_index.jsp">访问我的小家空间</a></span></p>
		
                    </div>
                    
                </div>

            </div>
        </div>
    <!--middle E-->

 </div> 
<div class="clear"></div>    
    <!--footer B-->
    	<jsp:include page="footer.jsp"></jsp:include>
	<!--footer E-->    

 
        <script language="JavaScript">   
        	var msg = '';
        </script>
        
   		  
		
         <!-- actionerror  --> 
	        

         <!-- actionmessage  -->     
	        
      
         <!-- 打印转换好格式的fielderror信息    -->  
        <script language="JavaScript">  
        	if(msg != ''){ 
            	alert(msg); 
            }
        </script>   
		         
		         
         <!-- fielderror  -->    
          
     


<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-29729795-1']);
  _gaq.push(['_setDomainName', 'ziguiw.com']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>

	</body>
</html>
