<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>招生信息——子贵网</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="http://www.ziguiw.com/css/basc.css" rel="stylesheet" type="text/css" />
		<script src="../js/changimages.js" type="text/javascript"></script>
        <script src="../js/news_js.js" type="text/javascript"></script>
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
	<body  onload="new Accordian('basic-accordian',5,'header_highlight');">

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
            	<li id="menu_on"><a href="news.jsp">学校动态</a>|</li>
            	<li><a href="campus.jsp">校园风光</a>|</li>
            	<li><a href="recruit.jsp">学校招聘</a>|</li>
            	<li><a href="contact.jsp">联系方式</a>|</li>
            	<li><a href="comment.jsp">网友评价</a></li>
            </ul>
        </div>
        
		<div class="clear"></div>
                
        <div class="news_cont">
            <div class="news_menu">
                <p class="news_t">学校动态 <span style="font-size:12px;font-family:Arial, Helvetica, sans-serif">NEWS</span></p>
                <ul>
                    <li><a href="news.jsp">校园新闻</a></li>
                    <li><a href="notice.jsp">学校公告</a></li>
                    <li id="on"><a href="info.jsp">招生信息</a></li>
                </ul>	
            </div>
            <div class="news_right">
                <div class="news_at">
                	<p>招生信息 &gt;&gt;</p>
                </div>
                <div class="news_tips">
                	
                    <!--新闻JS开始-->
                    
                    <div class="main">
                    <div id="basic-accordian" ><!--菜单开始-->
                    <div id="test-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月1日</span></div><!--1新闻资讯-->
                    <div id="test-content">
                        <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div> 
                      
                    
                      <div id="test1-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月12日</span></div><!--2学院概况-->
                      <div id="test1-content">
                        <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div> 
                    
                      <div id="test2-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月12日</span></div> <!--3课程设置-->
                    <div id="test2-content">
                            <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div>
                    
                      <div id="test3-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月12日</span></div><!--4国际文凭课程-->
                    <div id="test3-content">
                            <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div>
                            
                              <div id="test4-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月12日</span></div>
                              <!--5作品赏析-->
                    <div id="test4-content">
                            <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div>
                    
                              <div id="test5-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月12日</span></div><!--6学校视频-->
                    <div id="test5-content">
                            <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div>
                    
                        <div id="test6-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月12日</span></div><!--7招生信息-->
                    <div id="test6-content">
                            <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div>
                    
                              <div id="test7-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月12日</span></div><!--8招生信息-->
                    <div id="test7-content">
                            <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div>
                    
                              <div id="test8-header" class="accordion_headings" ><span class="news_theme">长大附中2012年下半年招生信息</span><span class="news_time">2012年9月12日</span></div><!--8在线咨询-->
                    <div id="test8-content">
                            <div class="accordion_child">
                            <ul>
                                <div class="news_img1"><img src="../images/news1.gif" /></div>
                                <div class="news_cont_n">
                                    <p><b>长大附中2012年下半年招生信息</b></p>
                                    <p>根据有关文件规定，本校各年级的招生人数如下：</p>
                                    <p class="news_spe"><a href="#">详情&gt;&gt;</a></p>
                                </div>
                            </ul>   
                        </div> 
                    </div>
                            
                    </div><!--菜单结束-->
                    </div><!--main结束-->
                    
                    <!--新闻JS结束-->
                </div>
            </div>
            
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
