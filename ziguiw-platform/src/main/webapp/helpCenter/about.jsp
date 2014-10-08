<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<!--	<base href="http://www.ziguiw.com:80/"/>-->
		<title>关于我们——子贵网帮助中心</title>
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

	<a class="logo" href="#" title=""><img src="/images/logo.jpg" alt=""/></a>

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

			<li id='index_tab5'><a href="/community/index" title="互动社区" >互动社区 <img src="/images/new.png" alt=""/></a></li>

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
            <a href="#">联系我们</a>   &gt;
            <a href="#"></a>   正文
        </div>
    <!--banner E-->
	
    <!--middle B-->
    	<div class="p_middle">
        	<div class="p_middle_left" id="knowqqleft">
            	<ul id="centerleft">
                    <li><a id="on" href="/helpCenter/about.jsp">关于子贵网</a></li>
                    <li><a href="/helpCenter/contract.jsp">服务协议</a></li>
                    <li><a href="/helpCenter/service.jsp" >客服中心</a></li>
                    <li><a href="http://dsis.ziguiw.com/feedback/add.jsp">意见反馈</a></li>
                    <li><a href="/helpCenter/pin.jsp">网站招聘</a></li>
                    <li><a href="/helpCenter/map.jsp">网站地图</a></li>
                    <li><a href="/helpCenter/contact.jsp">联系我们</a></li>
                    <li><a href="/helpCenter/copyright.jsp">版权所有</a></li>
                    <li><a href="/helpCenter/help.jsp">帮助中心</a></li>
                    <li><a href="/helpCenter/flink.jsp">友情链接</a></li>
                    <li><a href="http://dsis.ziguiw.com/topic/cloud/footmark_two.jsp">轨迹查询</a></li>
                    <li><a href="http://dsis.ziguiw.com/topic/cloud/index.jsp">数字校园云服务展会专题</a></li>
                </ul>
            </div>
            <div class="p_middle_right">
                <div class="p_title"><p><em>&#8226;</em>关于我们</p></div>
                <div class="p_con">
                	<img src="../images/p_first.gif" />
                    <div class="ab_con_middle">
                    	<p>子贵网（ www.ziguiw.com）——子承厚望  贵修举国，顺应教育信息化浪潮而生。</p>
                        <hr style="margin:8px;"/>
                        <div class="ab_con_w">
                        	<p>通过采用"一人一空间"形式，打造成集管理、教研、教学、学习、娱乐、分享、互动交流于一体的中国最大的孩子成长信息、教育资源门户云平台。子贵网不仅为全国家长提供孩子的考勤、作业、成绩等服务，为孩子的安全、学习信息提供有效参考，而且通过整合优质的教学资源，为全国高中小学各学科教师提供展示教学水平、实现教育资源交易、优质教育资源共享的平台。打破教室、教师、校园的界限，实现真正的校校互通、班班互联、资源共享。全面提升教育教学质量，引导孩子健康成长。 </p>
                         </div>
                    </div>
                    <img src="../images/p_last.gif" />
                </div>
                
                                <div class="p_con">
                	<img src="../images/p_first.gif" />
                    <div class="ab_con_middle">
                    	<p>网站的栏目功能：</p>
                        <hr style="margin:8px;"/>
                        <div class="ab_con_w">
                        	<p><strong>教育在线：</strong>致力于打造最大的教育资讯平台。面向全国高中小院校及家长、教师等各类用户提供及时、权威的教育新闻，发布升学考试政策，关注校园动态，追踪新闻热点。频道下设：焦点话题报道、校园新闻、升学考试指南及择校等教育行业资讯栏目； </p>
                        	<p><strong>教育知识：</strong>一个专业的教育知识平台，提供丰富的教育孩子健康成长的知识，分享孩子教育经验及心得，帮助学校及家长正确认识孩子教育方法，让孩子健康成长。频道下设：幼儿期、少儿期、青年期教育引导及同龄同城圈探讨、育儿问答、心理咨询、话题辩论等交流平台； </p>
                         <p><strong>教育资源：</strong>为全国高中小学各学科教师提供展示教学、实现教育资源交易的平台。打破教室、教师、校园的界限，实现校校互通、班班互联、资源共享。栏目针对高中小院校的各科目及各年级的不同进行资源的分类展示。 </p>
                        	<p><strong>互动社区：</strong>秉承为学生健康成长提供一个绿色的网络交流平台经营理念，实时为学生家长解答学习问题，用户可在线交流学习经验及技巧，还专门为需要帮助的贫困孩子开通爱心通道等； </p>   
                          	<p><strong>我的小家：</strong>用户的一人一空间栏目。用户可以在自己的空间写日记，记录心情、建立相册上传照片、还可以交友互动、给好友留言等。子贵网还为学生用户准备了智力游戏，引导孩子增强学习主动性，并促进情商和智商的健康全面发展； </p>
                        	<p><strong>数字校园功能：</strong>家长及老师用户登录子贵网即可免费查询到孩子的学籍档案、考勤查询、到校/离校时间、老师布置的家庭作业、考试成绩、健康状况、成长足迹、学校/老师发布的通知、在校的消费信息等。既优化了教育管理，又搭建起了家长、学校、老师和学生四方信息的平台，增进了家校交流。 </p>
                         </div>
                    </div>
                    <img src="../images/p_last.gif" />
                </div>
                
            </div>
        </div>
    <!--middle E-->
 
 </div> 
<div class="clear"></div>     
    <!--footer B-->
        <jsp:include page="/helpCenter/footer.jsp" />
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
