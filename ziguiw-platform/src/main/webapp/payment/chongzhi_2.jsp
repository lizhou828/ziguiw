<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>充值中心</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="http://www.ziguiw.com/css/basc.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/changimages.js" type="text/javascript"></script>
         <link href="${pageContext.request.contextPath}/payment/css/cz.css" rel="stylesheet" type="text/css" />
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
    </script>
    
    <% 
    	String type = request.getParameter("type");
    	String amount = request.getParameter("amount");
    
    %>
	</head>
<%@ include file="/inc/templateHead.jsp"%>
 
 
<!--middle  --B-->
	<div class="czcenter1">

        <div class="mypos fsong">
             <a href="${pageContext.request.contextPath}/index.jsp">子贵首页</a>&gt;
            <a href="${pageContext.request.contextPath}/user/portal.jsp">用户中心</a>&gt;
            <a href="${pageContext.request.contextPath}/payment/chongzhi_1.jsp">充值中心</a>
        </div> 

		<div class="cz_k">
        	<div class="cz_title">
            	<span style="color:#515151;font-size:28px;font-weight:bold;">子贵网-</span><span style="color:#c00202;font-size:28px;font-weight:bold;">充值中心</span>
            </div>
            <div class="cz_status2">
            	<span class="cz_status_num">1 充值类别</span>
                <span class="cz_status_num" id="cz_curr">2 充值方式</span>
                <span class="cz_status_num">3 充值操作</span>
                <span class="cz_status_num">4 充值完成</span>
            </div>
            <div class="cz_bank">
            	<form action="${pageContext.request.contextPath}/payment/pay.action">
            		<input type="hidden" name="type" value="<%=type%>"/>
            		<input type="hidden" name="amount" value="<%=amount%>"/>
                	<table>
                    	<tr>
                        	<td align="right" rowspan="4" style="font-size:16px;font-weight:bold;">请选择其中一种支付方式：</td>
                        </tr>
                        <tr>
                           	<td>
                           		<input type="radio" checked="true" name="paymethod" value="1"/> &nbsp;<img src="${pageContext.request.contextPath }/images/zfb.gif"/>
                           	</td>
                            
                        </tr>
                        <tr>
                           	<td>
                           		<input type="radio" name="paymethod" value="3"/> &nbsp;<img src="${pageContext.request.contextPath }/images/kq.gif"/>
                           	</td>
                        </tr>
                        <tr>
                           	<td>
                           		<input type="radio" name="paymethod" value="2"/> &nbsp;<img src="${pageContext.request.contextPath }/images/cft.gif"/>
                           	</td>
                        </tr>
                        <tr>
                        	<td colspan="3" align="center">
                        		<input type="submit" class="cz_next" value="确认"/>
                        	</td>
                        </tr>
                    </table>
                </form>
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
8
