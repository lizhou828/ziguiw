<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<!--	<base href="http://www.ziguiw.com:80/"/>-->
		<title>网站招聘——子贵网帮助中心</title>
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
        	<img src="../images/f-pbanner.gif" />
        </div>
        <div class="mypos fsong">
            <a href="#">子贵首页</a>  &gt;
            <a href="#">网站招聘</a>   &gt;
            <a href="#"></a>   正文
        </div>
    <!--banner E-->
	
    <!--middle B-->
    	<div class="p_middle">
        	<div class="p_middle_left" id="knowqqleft">
            	<ul id="centerleft">
                    <li><a href="about.jsp">关于子贵网</a></li>
                    <li><a href="contract.jsp">服务协议</a></li>
                    <li><a href="service.jsp">客服中心</a></li>
                    <li><a href="http://dsis.ziguiw.com/feedback/add.jsp">意见反馈</a></li>
                    <li><a  id="on" href="pin.jsp">网站招聘</a></li>
                    <li><a href="map.jsp">网站地图</a></li>
                    <li><a href="contact.jsp">联系我们</a></li>
                    <li><a href="copyright.jsp">版权所有</a></li>
                    <li><a href="help.jsp">帮助中心</a></li>
                    <li><a href="flink.jsp">友情链接</a></li>
                    <li><a href="http://dsis.ziguiw.com/topic/cloud/footmark_two.jsp">轨迹查询</a></li>
                    <li><a href="http://dsis.ziguiw.com/topic/cloud/index.jsp">数字校园云服务展会专题</a></li>
                </ul>
            </div>
            <div class="p_middle_right">
                <div class="p_title"><p><em>&#8226;</em>招聘部门：网络运营部</p></div>
                <div class="p_con">
                	<img src="../images/p_first.gif" />
                    <div class="p_con_middle">
                    	<p><span><strong>招聘岗位：技术部主管</strong></span><span><strong>招聘人数：1人</strong></span><span><strong>专业：计算机或相关专业</strong></span></p>
                        <hr style="margin:8px;"/>
                        <div class="p_con_w">
                        	<p>岗位要求: </p>
                            <p>1、精通网站架构和服务器构建；</p> 
                            <p>2、精通PHP/JAVA语言，有实际的项目开发经验，有成功作品案例者优先； </p>
                            <p>3、有良好的编码习惯，代码编写规范，热爱开发事业； </p>
                            <p>4、能够从技术角度提供网站和数据库的的优化建议，制定优化方案； </p>
                            <p>5、沟通能力强，能有效制定计划，并把控项目进度； </p>
                            <p>6、拥有1年以上技术运营及技术管理团队经验，保证按时按量完成工作； </p>
                            <p>7、有一定的学习和理解能力，有较强的分析及解决问题的能力； </p>
                            <p>8、具有面向对象分析及设计思想的经验者优先； </p>
                            <p>面试时，应聘者请携带好大型网站开发的作品案例； </p>
                        </div>
                    </div>
                    <img src="../images/p_last.gif" />
                </div>
                
                <div class="p_con">
                	<img src="../images/p_first.gif" />
                    <div class="p_con_middle">
                    	<p><span><strong>招聘岗位：网站采编</strong></span><span><strong>招聘人数：1人</strong></span><span><strong>专业：新闻学、传播学相关专业</strong></span></p>
                        <hr style="margin:8px;"/>
                        <div class="p_con_w">
                        	<p>岗位要求: </p>
                            <p>1、负责公司网站的管理及日常信息更新、维护，采集相关资料； </p> 
                            <p>2、视野开阔、思维活跃。有很好的文字功底，有策划选题、撰写文章的能力； </p>
                            <p>3、负责网站的炒作，信息交流和用户沟通； </p>
                            <p>4、有2年以上网站编辑、媒体工作经验； </p>
                            <p>5、有较强的工作责任心，踏实肯干，工作认真、严谨，团队合作精神强，善于与人沟通； </p>
                            <p>6、对网站用户界面、网站内容、网站功能有一定的了解； </p>
                        </div>
                    </div>
                    <img src="../images/p_last.gif" />
                </div>
                
                <div class="p_con">
                	<img src="../images/p_first.gif" />
                    <div class="p_con_middle">
                    	<p><span><strong>招聘岗位：网站美工</strong></span><span><strong>招聘人数：1人</strong></span><span><strong>专业：美术相关专业</strong></span></p>
                        <hr style="margin:8px;"/>
                        <div class="p_con_w">
                        	<p>岗位要求: </p>
                            <p>1、精通Photoshop、llustrator、Flash、dreamweaver等网页设计软件，有独立设计制作公司网站的能力； </p> 
                            <p>2、精通HTML. DIV+CSS .Javascript,能看懂和修改代码，熟悉Web标准，对网站整合及栏目设置有一定见解； </p>
                            <p>3、有一定的美术功底和网站/专题策划能力，熟悉大型网站制作的整套流程，有丰富的创意；</p>
                            <p>4、善于协作与沟通，具备良好的团队合作精神，能与程序员很好地配合；</p>
                            <p>5、美术或设计相关专业大专以上学历，2年以上设计工作经验； </p>
                            <p>面试时，应聘者请携带好大型网站开发的作品案例； </p>
                        </div>
                    </div>
                    <img src="../images/p_last.gif" />
                </div>
                
                <div class="p_con">
                	<img src="../images/p_first.gif" />
                    <div class="p_con_middle">
                    	<p><span><strong>招聘岗位：网站程序员</strong></span><span><strong>招聘人数：1人</strong></span><span><strong>专业：计算机或相关专业</strong></span></p>
                        <hr style="margin:8px;"/>
                        <div class="p_con_w">
                        	<p>岗位要求: </p>
                            <p>1、有3年以上相关工作经历，精通PHP、JAVA、MSSQL网站开发，有实际的大型项目开发经验； </p> 
                            <p>2、熟悉网站架构和服务器构建； </p>
                            <p>3、有良好的编码习惯，代码编写规范，热爱开发事业； </p>
                            <p>4、熟悉网站页面静态处理技术，具有SEO优化开发经验并部署实施； </p>
                            <p>5、服从意识好、上进心强，忠诚，能吃苦耐劳，对互联网充满热情； </p>
                            <p>6、有良好的沟通和学习能力、有较强的团队协作能力以及快速解决问题的能力；</p>
                            <p>面试时，应聘者请携带好大型网站开发的作品案例。 </p>
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
        <jsp:include page="footer.jsp"/>
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
