<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>充值成功</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="http://www.ziguiw.com/css/basc.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/changimages.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath }/payment/css/cz.css" rel="stylesheet" type="text/css" />
		<!--<base target="_blank"/>	-->	
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
        function pay(){
        	window.location.href = "./chongzhi_1.jsp";
        	
        }
        function goHome(){
        	window.location.href = "../user/portal.jsp";
        }
    </script>
    
    <%@ include file="/inc/templateHead.jsp"%>
	</head>
<style> 
 
</style>
	<body>
		

 
 
 
<!--middle  --B-->
	<div class="czcenter1">

        <div class="mypos fsong">
            <a href="#">子贵首页</a>&gt;
            <a href="/news/index">用户中心</a>&gt;
            <a href="/news/list/1">充值中心</a>
        </div> 

		<div class="cz_k">
        	<div class="cz_title">
            	<span style="color:#515151;font-size:28px;font-weight:bold;">子贵网-</span><span style="color:#c00202;font-size:28px;font-weight:bold;">充值中心</span>
            </div>
            <div class="cz_status4">
            	<span class="cz_status_num">1 充值类别</span>
                <span class="cz_status_num">2 充值方式</span>
                <span class="cz_status_num">3 充值操作</span>
                <span class="cz_status_num" id="cz_curr">4 充值完成</span>
            </div>
            <div class="cz_status_com">
            	<p class="cz_status_w"><img src="../images/status_yes.gif" /> 恭喜您，充值成功！</p>
                <div class="cz_customer">
                <div style="font-size:12px;color:#999;vertical-align:middle; height:22px;float:left;text-align:center;margin-top:4px;">如有疑问，请拨打客服电话 952116 或联系企业QQ客服</div><div style="margin-top:-8px;float:left;"></div><!-- WPA Button Begin -->
<script type="text/javascript" src="http://static.b.qq.com/account/bizqq/js/wpa.js?wty=1&type=1&kfuin=4009952116&ws=www.ziguiw.com&btn1=%E4%BC%81%E4%B8%9AQQ%E4%BA%A4%E8%B0%88&aty=0&a=&key=%5B2%067%050ThQl%06e%075P8R3%046Tm_1TgP%3E%5C%3A%05b%5B7%022Xd%056"></script></div>
<!-- WPA Button END --></p>
            	<p><input type="button" onclick="pay()" class="status_goon" value="继续充值" style="background: url('${pageContext.request.contextPath }/images/status_goon.gif');" onclick="pay()">
            	</input> 
            	<input type="button" onclick="goHome()" class="status_home" value="返回个人中心" style="background: url('${pageContext.request.contextPath }/images/status_home.gif');"/></p>
            </div>
            
        </div>        
        
        
        
    </div>	   
        
 
<!--middle  --E-->
 
 <div class="clear"></div>
    <!--footer B-->
    	<div class="p_flink">
        	<p><a href="#">关于我们</a>|<a href="#">服务协议</a>|<a href="#">客服中心</a>|<a href="#">意见反馈</a>|<a href="#">网站招聘</a>|<a href="#">网站地图</a>|<a href="#">联系我们</a>|<a href="#">数字校园云服务展会专题</a>|<a href="#">轨迹查询</a>|<a href="#">版权所有</a>|<a href="#">帮助中心</a></p>
        </div>
        
    
        
        <div class="p_logo">
        	<p>客户服务热线：952116　　客服邮箱：kf@ziguiw.com</p>
            <p><span>Copyright©2012</span><span> <a href="http://www.ziguiw.com">ziguiw.com</a> </span><span>All Rights Reserved</span>  <span>湘ICP备09002922号-3</span></p>
        	<img src="../images/p_logo.gif" />
        </div>
        
        
 
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

