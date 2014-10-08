<#functionData function="channle_news" pageNo=1 pageSize=10 channleId=1>
<#list functionData.list as news>
<dl class="">
	
    <dt><a href="newsPage_getNews.action?newsId=${news.id}" title="" target="_blank"><img src="${news.firstImage}" alt="${news.title}"></a></dt>

    <dd>

        <h4 class="fyahei"><a href="newsPage_getNews.action?newsId=${news.id}" target="_blank">${news.title}</a></h4>                                

    </dd>

    <dd class="bg_lunbo"></dd>

</dl>
</#list>
</#functionData>