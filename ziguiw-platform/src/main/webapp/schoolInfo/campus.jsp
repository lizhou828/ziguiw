<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>校园风光——子贵网</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="http://www.ziguiw.com/css/basc.css" rel="stylesheet" type="text/css" />
		<script src="../js/changimages.js" type="text/javascript"></script>
        <script src="../js/jquery.js" type="text/javascript"></script>
        <script src="../js/slide.js" type="text/javascript"></script>
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
            	<li id="menu_on"><a href="campus.jsp">校园风光</a>|</li>
            	<li><a href="recruit.jsp">学校招聘</a>|</li>
            	<li><a href="contact.jsp">联系方式</a>|</li>
            	<li><a href="comment.jsp">网友评价</a></li>
            </ul>
        </div>
        
	
            <div class="comm_k">
            	<div class="comm_w">
                	<p>校园风光</p>
                </div>

                <div class="wrap picshow">
                <!--大图轮换区-->
                    <div id="picarea">
                    <div class="slidearea">
                    <div style="margin: 0px auto;  overflow: hidden" id="bigpicarea">
                    <p class="bigbtnprev"><span id=big_play_prev></span></p>
                    <div id="image_xixi-01" class="image">
                    	<a href="#" target=_blank><img alt="" src="../images/1b.jpg" width=772 height=434></a> 
                        <div class="word">
                            <h3>全景图 摄于2012年9月12日</h3>
                        </div>
                    </div>
                    <div id=image_xixi-02 class=image><a href="#" target=_blank><img alt="" src="../images/2b.jpg" width=772 height=434></a> 
                            <div class=word>
                            <h3>主教学楼 摄于2012年9月12日</h3></div></div>
                            <div id=image_xixi-03 class=image><a href="#" target=_blank><img alt="" src="../images/3b.jpg" width=772 height=434></a> 
                            <div class=word>
                            <h3>教学楼 摄于2012年9月12日</h3></div></div>
                            <div id=image_xixi-04 class=image><a href="#" target=_blank><img alt="" src="../images/4b.jpg" width=772 height=434></a> 
                            <div class=word>
                            <h3>图书馆 摄于2012年9月12日</h3></div></div>
                            <!--<div id=image_xixi-05 class=image><a href="#" target=_blank><img alt="" src="../images/5b.jpg" width=772 height=434></a> 
                            <div class=word>
                            <h3>校园一角 摄于2012年9月12日</h3></div></div>
                            <div id=image_xixi-06 class=image><a href="#" target=_blank><img alt="" 
                            src="../images/42766146.jpg" width=772 height=434></a> 
                            <div class=word>
                            <h3>回眸</h3></div></div>
                            <div id=image_xixi-07 class=image><a href="#" target=_blank><img alt="" 
                            src="../images/42766159.jpg" width=772 height=434></a> 
                            <div class=word>
                            <h3>眩光的诱惑</h3>
                        </div>
                    </div>-->
                    <p class=bigbtnNext><span id=big_play_next></span></p>
                    </div></div>
                        <div id=smallpicarea>
                            <div id=thumbs>
                                <ul>
                                  <li class="first btnprev"><img id=play_prev src="../images/left.png"></li>
                                  <li class=slideshowItem>
                                  <a id=thumb_xixi-01 href="#"><img src="../images/1.jpg" width=213 height=158></a>
                                  </li>
                                  <li class=slideshowItem>
                                  <a id=thumb_xixi-02 href="#"><img src="../images/2.gif" width=213 height=158></a>
                                  </li>
                                  <li class=slideshowItem>
                                  <a id=thumb_xixi-03 href="#"><img src="../images/3.gif" width=213 height=158></a>
                                  </li>
                                  <li class=slideshowItem>
                                  <a id=thumb_xixi-04 href="#"><img src="../images/4.gif" width=213 height=158></a>
                                  </li>
                                  <li class="last btnNext"><img id=play_next src="../images/right.png"></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <SCRIpT>
                    var target = ["xixi-01","xixi-02","xixi-03","xixi-04","xixi-05","xixi-06","xixi-07"];
                    </SCRIpT>
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
