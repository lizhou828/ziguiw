<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>网友评价——子贵网</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="http://www.ziguiw.com/css/basc.css" rel="stylesheet" type="text/css" />
		<script src="../js/changimages.js" type="text/javascript"></script>
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
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
	</head>
<style>

</style>
	<body>
    <jsp:include page="head.jsp"/>


    <!--header E -->


<!--middle  --B-->
	<div class="t_middle">
    
    	<div class="t_banner">
        	<div class="bann_logo">
            	<img src="../images/bann_logo.jpg" />
            </div>
            <div class="banner_sc">
            	<img src="../images/banner_sc.gif" />
            </div>

        </div>
        
        <div class="clear"></div>
        
        <div class="t_nav">
        	<ul>
            	<li><a href="inner.jsp">学校主页</a>|</li>
            	<li><a href="about.jsp">学校介绍</a>|</li>
            	<li><a href="spee.jsp">校长致辞</a>|</li>
            	<li><a href="letter.jsp">校长信箱</a>|</li>
            	<li><a href="teachers.jsp">师资力量</a>|</li>
            	<li><a href="news.jsp">学校动态</a>|</li>
            	<li><a href="campus.jsp">校园风光</a>|</li>
            	<li><a href="recruit.jsp">学校招聘</a>|</li>
            	<li><a href="contact.jsp">联系方式</a>|</li>
            	<li id="menu_on"><a href="comment.jsp">网友评价</a></li>
            </ul>
        </div>
        
	
            <div class="comm_k">
            	<div class="comm_w">
                	<p>网友评价</p>
                </div>
                <div class="comment_out_k">
                	<div class="commernt_out_t">
                    	<p><strong>最新评论</strong></p>
                    </div>
                    <div class="comment_cont">
                    	<ul>
                        	<li>
                            	<div class="comment_right">
                                	<div class="us_inf">
										<span class="us_inf_name">宋江</span>
                                        <span>ip:202.110.*.*</span>
                                    </div>
                                    <div class="us_time">
                                    	<span>2012-09-12</span>
                                        <span>03:57:10</span>
                                        <span>发表</span>
                                    </div>
                                </div>
                                <div class="comment_right_con">
                                	<p>中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之</p>
                                </div>
                                <div class="clear"></div>                                
                                <div class="comment_right_re">
                                	<span><a href="#">顶<span style="color:#b60408;">[5221]</span></a></span> <span><a href="#">回复</a></span><span><a href="#">收藏</a></span><span><a href="#">复制</a></span>
                                </div>                                
                            </li>

                        	<li>
                            	<div class="comment_right">
                                	<div class="us_inf">
										<span>宋江</span>
                                        <span>ip:202.110.*.*</span>
                                    </div>
                                    <div class="us_time">
                                    	<span>2012-09-12</span>
                                        <span>03:57:10</span>
                                        <span>发表</span>
                                    </div>
                                </div>
                                <div class="comment_right_con">
                                	<p>中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之</p>
                                </div>
                                <div class="clear"></div>                                
                                <div class="comment_right_re">
                                	<span><a href="#">顶<span style="color:#b60408;">[5221]</span></a></span> <span><a href="#">回复</a></span><span><a href="#">收藏</a></span><span><a href="#">复制</a></span>
                                </div>                                
                            </li>
                        	<li>
                            	<div class="comment_right">
                                	<div class="us_inf">
										<span style="color:#3d91e8">宋江</span>
                                        <span>ip:202.110.*.*</span>
                                    </div>
                                    <div class="us_time">
                                    	<span>2012-09-12</span>
                                        <span>03:57:10</span>
                                        <span>发表</span>
                                    </div>
                                </div>
                                <div class="comment_right_con">
                                	<p>中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之中国政府采取必要措施捍卫国家领土主权、维护历史事实和正义理所当然。侵犯国土必诛杀之</p>
                                </div>
                                <div class="clear"></div>
                                <div class="comment_right_re">
                                	<span><a href="#">顶<span style="color:#b60408;">[5221]</span></a></span> <span><a href="#">回复</a></span><span><a href="#">收藏</a></span><span><a href="#">复制</a></span>
                                </div>                                
                            </li>                                                        
                         </ul>
                    </div>
                </div>
                
                          <div class="clear"></div>
                
                <div class="intro_comm_con">
					<div class="comminput">
                    	用户名：<input class="usinp" size="15" type="text"/>
                        密码：<input size="15" class="pwinp" type="password"/>
                        <input class="comm_login" type="button"/>
                        <input class="comm_pw" type="button" />
                    </div>
                    <div class="commtext">
						<textarea class="commtext_con" rows="8"></textarea>
                    </div>
                    <div class="comme_b">
                    	<div class="comme_b_left">
                        	<p style="height:27px;line-height:27px;">网友评论仅供其表达个人看法，并不表明本站同意其观点或证实其描述。</p>
                        </div>
                        <div class="comme_b_right">
							<input type="checkbox" class="usinp"/><span style="margin:0 6px;">匿名发表</span>
                            <input type="button" border="0" class="comme_ok"/>
                        </div>
                    </div>
                </div>
                
                <div class="clear"></div>
          
          
        </div>
     
     
  </div>   
        

<!--middle  --E-->

<%@ include file="../inc/templateFoot.jsp"%>
        

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
