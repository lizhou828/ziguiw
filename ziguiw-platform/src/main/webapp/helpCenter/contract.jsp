﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<!--	<base href="http://www.ziguiw.com:80/"/>-->
		<title>服务协议——子贵网帮助中心</title>
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
            <a href="#">联系我们</a>   &gt;
            <a href="#"></a>   正文
        </div>
    <!--banner E-->
	
    <!--middle B-->
    	<div class="p_middle">
        	<div class="p_middle_left" id="knowqqleft">
            	<ul id="centerleft">
                    <li><a href="/helpCenter/about.jsp">关于子贵网</a></li>
                    <li><a id="on" href="/helpCenter/contract.jsp">服务协议</a></li>
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
                <div class="p_title"><p><em>&#8226;</em>服务协议</p></div>
                <div class="p_con">
                	<img src="../images/p_first.gif" />
                    <div class="copy_con_middle">
                        <p><strong>协议如下：</strong></p>
                        <hr style="margin:8px;">
                        <div class="copy_con_w">
                            <p>本协议由您与湖南爱瑞杰科技发展股份有限公司共同签订。本协议中协议双方合称协议方，湖南爱瑞杰科技发展股份有限公司在协议中亦称为"子贵网"。本协议中"子贵网"指由湖南爱瑞杰科技发展股份有限公司运营的网络平台，包括子贵网（域名为ziguiw.com）。</p>
                            <p>一、协议内容及签署</p>
                            <p>1．本协议内容包括协议正文及所有子贵网已经发布的或将来可能发布的各类规则。所有规则为本协议不可分割的组成部分，与协议正文具有同等法律效力。除另行明确声明外，任何子贵网及其关联公司提供的服务（以下简称为子贵网）均受本协议约束。</p>
                            <p>2．您应当在使用子贵网之前认真阅读全部协议内容，对于协议中以加粗字体显示的内容，您应重点阅读。如您对协议有任何疑问的，应向子贵网客服人员咨询。但无论您事实上是否在使用子贵网之前认真阅读本协议内容，只要您使用子贵网，则本协议即对您产生约束，届时您不应以未阅读本协议的内容或者未获得子贵网对您问询的解答等理由，主张本协议无效，或要求撤销本协议。</p>
                            <p>3．您承诺接受并遵守本协议的约定。如果您不同意本协议的约定，您应立即停止注册程序或停止使用子贵网。</p>
                            <p>4．子贵网有权根据需要不时地制定、修改本协议及/或各类规则，并以网站公示的方式进行公告，不再单独通知您。变更后的协议和规则一经在网站公示后，立即自动生效。如您不同意相关变更，应当立即停止使用子贵网。您继续使用子贵网，即表示您接受经修订的协议和规则。</p>
                            <p>二、注册</p>
                            <p>1．注册者资格</p>
                            <p>您确认，在您完成注册程序或以其他子贵网允许的方式实际使用子贵网时，您应当是具备完全民事权利能力和完全民事行为能力的自然人、法人或其他组织。若您不具备前述主体资格，则您及您的监护人应承担因此而导致的一切后果，且子贵网有权注销（永久冻结）您的子贵网账户，并向您及您的监护人索偿。</p>
                            <p>2．账户</p>
                            <p>在您签署本协议，完成会员注册程序或以其他子贵网允许的方式实际使用子贵网时，子贵网会向您提供唯一编号的子贵网账户（以下简称账户）。</p>
                            <p>您可以对账户设置会员名和密码，通过该会员名密码或与该会员名密码关联的其他用户名密码登录子贵网。您设置的会员名不得侵犯或涉嫌侵犯他人合法权益。</p>
                            <p>您应对您的账户（会员名）和密码的安全，以及对通过您的账户（会员名）和密码实施的行为负责。除非有法律规定或司法裁定，且征得子贵网的同意，否则，账户（会员名）和密码不得以任何方式转让、赠与或继承（与账户相关的财产权益除外）。<strong>如果发现任何人违法使用您的账户或有任何其他可能危及您账户安全的情形时，您应当立即以有效方式通知子贵网，要求子贵网暂停相关服务。您理解子贵网对您的请求采取行动需要合理时间，子贵网对在采取行动前已经产生的后果（包括但不限于您的任何损失）不承担任何责任。</strong></p>
                            <p><font color="red"><strong>为方便您使用子贵网及子贵网关联公司或其他组织的服务（以下称其他服务），您同意并授权子贵网将您在注册、使用子贵网过程中提供、形成的信息传递给向您提供其他服务，或从提供其他服务获取您在注册、使用其他服务期间提供、形成的信息。</strong></font></p>
                            <p>3．会员</p>
                            <p>在您按照注册页面提示填写信息、阅读并同意本协议及完成全部注册程序后或以其他子贵网允许的方式实际使用子贵网时，您即成为子贵网会员（亦称会员）。</p>
                            <p>在注册时，您应当按照法律法规要求，或注册页面的提示准确提供，并及时更新您的资料，以使之真实、及时，完整和准确。<strong>如有合理理由怀疑您提供的资料错误、不实、过时或不完整的，子贵网有权向您发出询问及/或要求改正的通知，并有权直接做出删除相应资料的处理，直至终止对您提供部分或全部子贵网服务。子贵网对此不承担任何责任，您将承担因此产生的任何直接或间接支出。</strong></p>
                            <p><strong>您应当准确填写并及时更新您提供的电子邮件地址、联系电话、联系地址等联系信息，以便子贵网或其他会员与您进行有效联系，若通过这些联系方式无法与您取得联系，导致您在使用子贵网平台过程中产生任何损失或增加费用的，应由您完全独自承担。</strong></p>
                            <p>您在使用子贵网过程中，所产生的应纳税赋，以及一切硬件、软件、服务及其它方面的费用，均由您独自承担。</p>
                            <p>三、子贵网信息平台服务</p>
                            <p>1．通过子贵网及其关联公司提供的子贵网信息平台服务和其他服务，会员可在子贵网信息平台上浏览或发布教育类相关信息、孩子成长日记、发布照片、会员互动、学习交流、参与子贵网组织的活动以及使用其它信息服务及技术服务。此外，还可享受《数字化校园智能信息管理平台》服务，家长查看孩子学习情况、孩子安全信息、家校互联互动等。</p>
                            <p>2．您了解并同意，子贵网有权应政府部门（包括司法及行政部门）的要求，向其提供您在子贵网填写的注册信息和发布留言等必要信息。<strong>如您涉嫌侵犯他人知识产权，则子贵网有权在初步判断涉嫌侵权行为存在的情况下，向权利人提供您必要的身份信息。</strong></p>
                            <p>四、子贵网使用规范</p>
                            <p>1．在使用子贵网的过程中，您承诺遵守以下约定：</p>
                            <p>a）在使用子贵网过程中实施的所有行为均遵守国家法律、法规等规范性文件及子贵网各项规则的规定和要求，您如果违反前述承诺，产生任何法律后果的，您应以自己的名义独立承担所有的法律责任，并确保子贵网免于因此产生任何损失；</p>
                            <p>b) 不发布危害国家安全利益、破坏国家和平、扰乱社会秩序、散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪、侮辱或者诽谤他人、含有法律及行政法规禁止的其他内容、进行商业广告行为等信息；</p>
                            <p>c) 不以虚构或歪曲事实的方式不当评价其他会员，不采取不正当方式制造或提高自身的信用度，不采取不正当方式制造或提高（降低）其他会员的信用度；</p>
                            <p>d) 不对子贵网上的任何数据作商业性利用，包括但不限于在未经子贵网事先书面同意的情况下，以复制、传播等任何方式使用子贵网上展示的资料；</p>
                            <p>e) 对于您涉嫌违反承诺的行为对任意第三方造成损害的，您均应当以自己的名义独立承担所有的法律责任，并应确保子贵网免于因此产生损失或增加费用。</p>
                            <p>五、免责声明</p>
                            <p>1．不论在何种情况下，子贵网均不对由于信息网络正常的设备维护，信息网络连接故障，电脑、通讯或其他系统的故障，电力故障，罢工，劳动争议，暴乱，起义，骚乱，生产力或生产资料不足，火灾，洪水，风暴，爆炸，战争，政府行为，司法行政机关的命令或第三方的不作为而造成的不能服务或延迟服务承担责任。</p>
                            <p>2．您了解子贵网上的部分信息系用户自行发布，且可能存在风险和瑕疵。子贵网仅作为您获取信息，学习交流的场所，但子贵网无法控制交流信息的真实性或准确性，您应自行谨慎判断确定相关信息的真实性、合法性和有效性，并自行承担因此产生的责任与损失。</p>
                            <p>六、协议终止</p>
                            <p>1．您同意，子贵网有权自行全权决定以任何理由不经事先通知的中止、终止向您提供部分或全部子贵网服务，暂时冻结或永久冻结（注销）您的账户，且无须为此向您或任何第三方承担任何责任。</p>
                            <p>2．出现以下情况时，子贵网有权直接以注销账户的方式终止本协议:</p>
                            <p>a) 子贵网终止向您提供服务后，您涉嫌再一次直接或间接或以他人名义注册为子贵网用户的；</p>
                            <p>b) 您提供的电子邮箱不存在或无法接收电子邮件，且没有其他方式可以与您进行联系，或子贵网以其它联系方式通知您更改电子邮件信息，而您在子贵网通知后三个工作日内仍未更改为有效的电子邮箱的；</p>
                            <p>c) 您注册信息中的主要内容不真实或不准确或不及时或不完整；</p>
                            <p>d) 本协议（含规则）变更时，您明示并通知子贵网不愿接受新的服务协议的；</p>
                            <p>e) 其它子贵网认为应当终止服务的情况。</p>
                            <p>3．您有权向子贵网要求注销您的账户，经子贵网审核同意的，子贵网注销（永久冻结）您的账户，届时，您与子贵网基于本协议的合同关系即终止。您的账户被注销（永久冻结）后，子贵网没有义务为您保留或向您披露您账户中的任何信息，也没有义务向您或第三方转发任何您未曾阅读或发送过的信息。</p>
                            <p>4．您同意，您与子贵网的合同关系终止后，子贵网仍享有下列权利：</p>
                            <p>a) 继续保存您的注册信息及您使用子贵网期间发布的所有信息；</p>
                            <p>b) 您在使用子贵网期间存在违法行为或违反本协议和/或规则的行为的，子贵网仍可依据本协议向您主张权利。</p>
                            <p>七、法律适用、管辖与其他</p>
                            <p>1．本协议之效力、解释、变更、执行与争议解决均适用中华人民共和国法律，如无相关法律规定的，则应参照通用国际商业惯例和（或）行业惯例。</p>
                            <p>2．因本协议产生之争议，应依照中华人民共和国法律予以处理，并以子贵网所在地人民法院提起诉讼。</p>

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
        <jsp:include page="/helpCenter/footer.jsp"/>
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
