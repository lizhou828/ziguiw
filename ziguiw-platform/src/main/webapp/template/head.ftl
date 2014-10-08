<#assign path="${request.getContextPath()}">
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8" />
	<title>${(statics["com.zigui.utils.CommonUtils"].getHeadInfo()).title}</title>
	<meta name="generator" content="editplus" />
	<meta name="author" content="" />
	<meta name="keywords" content="${(statics["com.zigui.utils.CommonUtils"].getHeadInfo()).keywords}" />
	<meta name="description" content="${(statics["com.zigui.utils.CommonUtils"].getHeadInfo()).description}" />
	<link href="${path!}/css/basc.css" rel="stylesheet" type="text/css">
	<script src="${path!}/js/jquery-1.7.2.min.js"></script>
	<script src="${path!}/js/basic.js.jsp"></script>
	<script src="${path!}/js/focus.js"></script>
</head>

<body>

<div class="toolbar">

	<div class="tit">
欢迎来到子贵网！
        </div>

        <div class="subNav">

		
    <a title="用户登录" href="${path!}/user/login.jsp" target="_blank">用户登录</a>

    <a title="新用户注册" href="${path!}/user/register.jsp" target="_blank">新用户注册</a>

   </div>

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
</body>

<!--toolbar E -->



<div class="header">

	<a class="logo" href="#" title=""><img src="${path!}/images/logo.jpg" alt=""/></a>

	<form action="${path!}/search.action">

	<div class="search">

		<input type="text" class="text" name="keyWords" value="请输入搜索关键词..." id=""/>

		<input type="submit" class="btn" name="" value="搜索" id=""/>

	</div>
        </form>
        
        
			
	<div class="nav">

		<ul class="nav_tit clearfix">

			<li id='index_tab1'><a href="${path!}/index.jsp" title="首   页" >首   页</a></li>
			
			<li id='index_tab2'><a href="${path!}/news/index" title="教育在线" >教育在线</a></li>

			<li id='index_tab3'><a href="${path!}/study/index" title="教育知识" >教育知识</a></li>
                        
            <li id='index_tab4'><a href="http://dsis.ziguiw.com/source/sourceInfo.action" title="教育资源" >教育资源</a></li>

			<li id='index_tab5'><a href="${path!}/community/index" title="互动社区" >互动社区 <img src="${path!}/images/new.png" alt=""/></a></li>

			<li id='index_tab6'><a href="${path!}/user/home_index.jsp" title="我的小家" >我的小家</a></li>

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

				<a href="${path!}/news/list/1" title="焦点话题" target="_blank">焦点话题</a>

				<a href="${path!}/news/list/2" title="校园新闻" target="_blank">校园新闻</a>

				<a href="${path!}/news/list/3" title="考试升学" target="_blank">考试升学</a>

			</li>

			<li>

				<a href="${path!}/study/youer/list" title="幼儿期" target="_blank">幼儿期</a>

				<a href="${path!}/study/shaoer/list" title="少儿期" target="_blank">少儿期</a>

				<a href="${path!}/study/qingnian/list" title="青年期" target="_blank">青年期</a>
				
				<a href="${path!}/study/psychology/list" title="心理咨询" target="_blank">心理咨询</a>

				<a href="${path!}/study/yuer/list/0" title="育儿问答" target="_blank">育儿问答</a>

				<a href="${path!}/study/bianlun/list" title="辩论" target="_blank">辩论</a>

			</li>

			<li>

				<a href="${path!}/community/age/list" title="同龄圈" target="_blank">同龄圈</a>

				<a href="${path!}/community/city/list" title="同城圈" target="_blank">同城圈</a>

				<a href="${path!}/community/jiaoliu/list" title="学习交流" target="_blank">学习交流</a>
				
				<a href="${path!}/community/zhuanjia/list" title="专家问答" target="_blank">专家问答</a>
				
				<a href="${path!}/community/aixin/list" title="爱心站" target="_blank">爱心站</a>

			</li>


		</ul>

	</div>

	<!--nav E -->

</div>

<!--header E -->