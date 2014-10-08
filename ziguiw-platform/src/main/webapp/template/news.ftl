<#include "head.ftl">
<input type="hidden" name="resourceId" id="resourceId" value="${parameter.newsId[0]}"/>
<input type="hidden" name="resourceType" id="resourceType" value="news"/> 
<div class="content clearfix">

	<div class="ad01">

		<a href="#" title=""><img src="${path!}/images/adv01.jpg" alt=""/></a>

	</div>

	<!--ad01 E -->




<@dataFunction function="queryNewsById" pageNo="1" pageSize="1" newsId="${parameter.newsId[0]}">
<#list functionData.list as news>

  
    <div class="mypos fsong"><a href="#">子贵首页</a> > <a href="${path!}/news/index">教育在线</a> > <a href="${path!}/news/list/${news.newsChannle.id}">${news.newsChannle.name}</a> > 正文</div>

    <div class="col1 fl">

    	<div class="newscot">

    		<h1 class="fyahei">${news.title}</h1>

            <p class="news_tip fb"><span>时间：${news.formatedStartTime?substring(0,10)}</span><span>编辑： ${news.editors}</span></p>

            <div class="news_info">

            	<strong>内容摘要：</strong>

                <p class="f14"><#if news.description?? && news.description!="">${news.description}<#else>${news.autoDescription}</#if></p>

            </div>

            <div class="newscots">

                ${news.content}

            </div>


    	</div>
</#list>
                        </@dataFunction> 

 
     <div class="tz_xg p_b10 o-v">
	 <h2>相关文章</h2>
	 <ul class="clearfix">
           
     </ul>

    </div>
    
    <script>
    	function shareto(id){
			var pic="";
			var url=encodeURIComponent(document.location.href);
			var title=encodeURIComponent(document.title);
			var _gaq = _gaq || [];
			
			if(id=="fav"){
			addBookmark(document.title);
			return;
			}else if(id=="qzone"){
			_gaq.push(['_trackEvent', 'SocialShare', 'Share', 'QZone', 1]);
			window.open(' http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+url);
			return;
			}else if(id=="sina"){
			_gaq.push(['_trackEvent', 'SocialShare', 'Share', 'SinaT', 1]);
			//window.open(' http://v.t.sina.com.cn/share/share.php?title='+title+'&url='+url+'&source=bookmark','_blank');
			window.open(" http://v.t.sina.com.cn/share/share.php?url="+url+"&appkey=610475664&title="+title+"&pic="+pic,"_blank","width=615,height=505");
			return;
			}else if(id=="baidu"){
			_gaq.push(['_trackEvent', 'SocialShare', 'Share', 'Baidu', 1]);
			window.open(' http://cang.baidu.com/do/add?it='+title+'&iu='+url+'&fr=ien#nw=1','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes');
			return;
			}else if(id=="googlebuzz"){
			_gaq.push(['_trackEvent', 'SocialShare', 'Share', 'GoogleBuzz', 1]);
			window.open(' http://www.google.com/buzz/post?url='+url+'&imageurl='+pic,'_blank');
			return;
			}else if(id=="douban"){
			_gaq.push(['_trackEvent', 'SocialShare', 'Share', 'Douban', 1]);
			var d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r=' http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e(d.title)+'&sel='+e(s)+'&v=1',x=function(){if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=450,height=330'))location.href=r+'&r=1'};
			if(/Firefox/.test(navigator.userAgent)){setTimeout(x,0)}else{x()}
			return;
			}else if(id=="renren"){
			_gaq.push(['_trackEvent', 'SocialShare', 'Share', 'RenRen', 1]);
			window.open(' http://www.connect.renren.com/share/sharer?url='+url+'&title='+title,'_blank','resizable=no');
			return;
			}else if(id=="xianguo"){
			_gaq.push(['_trackEvent', 'SocialShare', 'Share', 'XianGuo', 1]);
			window.open(' http://xianguo.com/service/submitdigg/?link='+url+'&title='+title,'_blank');
			return;
			}else if(id=="mail"){
			_gaq.push(['_trackEvent', 'SocialShare', 'Share', 'Mail', 1]);
			window.open('mailto:?subject='+title+'&body='+encodeURIComponent('这是我看到了一篇很不错的文章，分享给你看看！\r\n\r\n')+title+encodeURIComponent('\r\n')+url);
			return;
			}
		}
		
		function ShareButtons(){
			document.write('<div class="entry o-v tz_xg">');
			document.write('<h2>分享到:</h2>');
			document.write('<span class="btns qq"><s></s><a class="sharebutton" id="share_qzone" href="javascript:shareto(\'qzone\');" title="分享到QQ空间">QQ空间</a></span>');
			document.write('<span class="btns sina"><s></s><a class="sharebutton" id="share_sina" href="javascript:shareto(\'sina\');" title="分享到新浪微博">新浪微博</a></span>');
			document.write('<span class="btns douban"><s></s><a class="sharebutton" id="share_douban" href="javascript:shareto(\'douban\');" title="分享到豆瓣">豆瓣</a></span>');
			document.write('<span class="btns renren"><s></s><a class="sharebutton" id="share_renren" href="javascript:shareto(\'renren\');" title="分享到人人网">人人网</a></span>');
			document.write('</div>');
		}
		
		ShareButtons();
	</script>
	
	<div class="bordb hi-at mt10 new_view">
		<div class="h3titb">
			<h3 class="fyahei">发表评论</h3>
		</div>
		<div class="p_b10 mb10">
			<form action="${path!}/user/commentNews.action">
				<div class="o-v leave_k">	
				<ul class="ul-li-s">
				   <li class="a-r"><a href="${path!}/news/queryComment.action?commentedId=${parameter.newsId[0]}&type=2" target="_blank">【查看评论】</a></li>
					<li><span>用户名：</span><input type="text" value="" name="loginUser.nickName" class="inp_txt"><a href="" target="_blank" class="m-l10">快速注册新用户</a></li>
					<li><span>密码：</span><input type="password" name="loginUser.password" class="inp_txt"></li>
					<li><span></span><textarea name="message.text" class="area3"></textarea></li>
					<input type="hidden" name="message.type" value="2">
					<input type="hidden" name="message.commentedId" value="${parameter.newsId[0]}">
					<li><input type="submit" name="Submit1" value="发 表" class="btn2 l mt5"></li>
				</ul>
				</div>
			</form>	
				
		</div>	
	</div>	

    </div>
    
    

    <div class="col2 fl ml10">

    	<div class="hottj borda pr pt40">

        	<div class="h3tita pa">

            	<h3 class="fyahei">热点推荐</h3>
            </div>

            <ul class="hottj_top">

            	<@dataFunction function="queryRecommendNews" pageNo="1" pageSize="2" reommendRegionId=11>
                        <#list functionData.list as news>
            	<li class="clearfix">

                	<img src="${news.firstImage}" alt="${news.title}" />

                    <h4><a href="${path!}/news/detail/${news.id}">${news.title}</a></h4>

                    <p><#if news.description?? && news.description!="">${news.description}<#else>${news.autoDescription}</#if>...</p>

                </li>
</#list>
                        </@dataFunction>   

            </ul>

            <ul class="hottj_bot">

                <@dataFunction function="queryRecommendNews" pageNo="1" pageSize="6" reommendRegionId=12>
                        <#list functionData.list as news>
                <li><span>${news.formatedCreateTime}</span><a href="${path!}/news/detail/${news.id}">${news.title}</a></li>

</#list>
                        </@dataFunction>   

            </ul>

        </div>

        <div class="ad_col2_1 borda mt10"><a href="#"><img src="${path!}/images/img_268_88.jpg" /></a></div>

        <div class="mt10">

        	<div class="h3titb">

            	<h3 class="fyahei">相关资讯</h3>

            </div>

            <div class="bordb">

                <ul class="newsli">

                  <@dataFunction function="queryRecommendNews" pageNo="1" pageSize="6" reommendRegionId=13>
<#list functionData.list as news>
                    <li><a href="${path!}/news/detail/${news.id}">${news.title}</a></li>

</#list>
                        </@dataFunction>   

                </ul>            

            </div>

        </div>

        <div class="ph mt10">

        	<div class="h3titb">

            	<h3 class="fyahei">人气排行</h3>

            </div>

            <div class="bordb">

                <ul>

                   <@dataFunction function="newsOrderByPv" pageNo="1" pageSize="10" newsId="0">
                        <#list functionData.list as news>
                    <li><a href="${path!}/news/detail/${news.id}">${news.title}</a></li>

</#list>
                        </@dataFunction>   
                </ul>            

            </div>

        </div>

    </div>        

<#include "link.ftl">
</div>
<#include "foot.ftl">