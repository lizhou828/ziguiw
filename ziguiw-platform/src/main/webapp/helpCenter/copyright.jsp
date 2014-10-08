<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<!--	<base href="http://www.ziguiw.com:80/"/>-->
		<title>版权所有——子贵网帮助中心</title>
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

		<input type="submit" class="btn" name="" value="搜索" id=""/>

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
            <a href="#">联系我们</a>   &gt;
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
                    <li><a href="map.jsp">网站地图</a></li>
                    <li><a href="contact.jsp">联系我们</a></li>
                    <li><a id="on" href="copyright.jsp">版权所有</a></li>
                    <li><a href="help.jsp">帮助中心</a></li>
                    <li><a href="flink.jsp">友情链接</a></li>
                    <li><a href="http://dsis.ziguiw.com/topic/cloud/footmark_two.jsp">轨迹查询</a></li>
                    <li><a href="http://dsis.ziguiw.com/topic/cloud/index.jsp">数字校园云服务展会专题</a></li>
                </ul>
            </div>
            <div class="p_middle_right">
                <div class="p_title"><p><em>&#8226;</em>版权所有</p></div>
                <div class="p_con">
                	<img src="../images/p_first.gif" />
                    <div class="copy_con_middle">
                    	<p>声明如下：</p>
                        <hr style="margin:8px;"/>
                        <div class="copy_con_w">
                        	<p><strong>版权声明：</strong></p>
                        	<p>湖南爱瑞杰科技发展股份有限公司（以下简称"爱瑞杰"）对其发行的或与合作公司共同发行的包括但不限于产品或服务的全部内容及爱瑞杰网站上的材料拥有版权等知识产权，受法律保护。</p>
                            <p>未经本公司书面许可，任何单位及个人不得以任何方式或理由对上述产品、服务、信息、材料的任何部分进行使用、复制、修改、抄录、传播或与其它产品捆绑使用、销售。</p>
                            <p>凡侵犯本公司版权等知识产权的，本公司必依法追究其法律责任。
本公司法律事务部受本公司指示，特此郑重法律声明！</p>
							</div>
							
                         <div class="copy_con_w">                            
                        	<p><strong>商标声明：</strong></p>
                        	<p>爱瑞杰等商标为本公司或关联公司注册商标或商标，受法律保护，侵权必究。</p>
                            <p>未经本公司或商标权人书面许可，任何单位及个人不得以任何方式或理由对该商标的任何部分进行使用、复制、修改、传播、抄录或与其它产品捆绑使用销售。</p>
                            <p>凡侵犯本公司商标权的，我公司必依法追究其法律责任。</p>
                            <p>本公司法律事务部受本公司指示，特此郑重法律声明！</p>
                         </div>
                         
                         <div class="copy_con_w">                            
                        	<p><strong> 隐私权声明：</strong></p>
                        	<p>	  爱瑞杰对保护您的个人隐私的保护。有时候我们需要某些信息才能为您提供您请求的服务，本隐私权声明解释了这些情况下的数据收集和使用情况。本隐私权声明适用于爱瑞杰网站的所有相关服务。</p>
                         </div>                         
                         							
                         <div class="copy_con_w">                            
                        	<p><strong>您个人信息的搜集：</strong></p>
                            <p>当我们需要能识别您身份的信息（个人信息）或者可以与您联系的信息时，我们会征求您的同意。通常，在您注册爱瑞杰会员或其他爱瑞杰在线服务时，我们会请求您提供这些信息。爱瑞杰搜集的信息通常仅限于您的姓名、性别、年龄、出生日期、身份证号、家庭住址、教育程度、公司情况、所属行业、兴趣爱好等。</p>
                         </div>
                         
                        <div class="copy_con_w">                            
                        	<p><strong>控制您的个人信息：</strong></p>
                            <p>爱瑞杰会在法律要求或符合爱瑞杰网站的相关服务条款、软件许可使用协议约定的情况下透露您的个人信息，或者有充分理由相信必须这样做才能：(a) 满足法律的明文规定，或者符合爱瑞杰或本站点适用的法律程序；（b）符合爱瑞杰网站相关服务条款、软件许可使用协议的约定；(c) 保护爱瑞杰及其系列 Web 站点的权利或财产，以及 (d) 在紧急情况下保护爱瑞杰员工、爱瑞杰产品或服务的用户或大众的个人安全。</p>
                            <p>爱瑞杰不会未经允许将这些信息与第三方共享，本声明已经列出的情况除外。</p>
                         </div>
                         
                         <div class="copy_con_w">                            
                        	<p><strong>您个人信息的安全：</strong></p>
                            <p>严格保护您的个人信息的安全。我们使用各种安全技术和程序来保护您的个人信息不被未经授权的访问、使用或泄漏。如果您对我们的隐私保护有任何置疑，请email至kf@ziguiw.com。</p>
                         </div>
                         
                           <div class="copy_con_w">                            
                        	<p><strong>对青少年个人信息的搜集和使用：</strong></p>
                            <p>爱瑞杰非常重视对青少年个人信息搜集和使用的安全性和保护。腾讯建议，任何16岁以下的未成年人参加网上活动应事先取得家长或其法定监护人（以下简称"监护人"）的书面同意。</p>
                            <p>未经监护人事先同意，爱瑞杰不会使用青少年的个人信息，也不会向任何第三方透露可识别的信息，除非是为了向青少年提供服务之必要。 经监护人同意，爱瑞杰可以搜集青少年的个人信息，但监护人有权拒绝爱瑞杰进一步搜集其子女或被监护人的个人信息，有权审阅或要求爱瑞杰删除该青少年的个人信息。</p>
                            <p>爱瑞杰保证不会要求青少年提供额外的个人资料，以作为允许其参与网上活动的条件。</p>
                         </div>
                         
                         <div class="copy_con_w">                            
                        	<p><strong>关于免责：</strong></p>
                            <p>就下列相关事宜的发生，爱瑞杰不承担任何法律责任：</p>
                            <p>1.爱瑞杰根据法律规定或相关政府的要求提供您的个人信息；</p>
                            <p>2.由于您将用户密码告知他人或与他人共享注册帐户，由此导致的任何个人信息的泄漏，或其他非因爱瑞杰原因导致的个人信息的泄漏；</p>
                            <p>3.任何第三方根据爱瑞杰各服务条款及声明中所列明的情况使用您的个人信息，由此所产生的纠纷；</p>
                            <p>4.任何由于黑客攻击、电脑病毒侵入或政府管制而造成的暂时性网站关闭；因不可抗力导致的任何后果；</p>
                            <p>5.爱瑞杰在各服务条款及声明中列明的使用方式或免责情形。</p>
                            <p>爱瑞杰会不时更新本隐私权声明。更新时，我们还会修改隐私权声明顶部的"最近更新"的日期。</p>
                            <p style="text-align:right;">湖南爱瑞杰科技发展股份有限公司</p>
                            <p style="text-align:right;">二零一二年九月三日</p>
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
