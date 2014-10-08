
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>学校主页——子贵网</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="http://www.ziguiw.com/css/basc.css" rel="stylesheet" type="text/css" />
		<script src="../js/changimages.js" type="text/javascript"></script>
        <link href="../css/style.css" rel="stylesheet" type="text/css" />
		<!--<base target="_blank"/>-->		
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
            	<li id="menu_on"><a href="inner.jsp">学校主页</a>|</li>
            	<li><a href="about.jsp">学校介绍</a>|</li>
            	<li><a href="spee.jsp">校长致辞</a>|</li>
            	<li><a href="letter.jsp">校长信箱</a>|</li>
            	<li><a href="teachers.jsp">师资力量</a>|</li>
            	<li><a href="news.jsp">学校动态</a>|</li>
            	<li><a href="campus.jsp">校园风光</a>|</li>
            	<li><a href="recruit.jsp">学校招聘</a>|</li>
            	<li><a href="contact.jsp">联系方式</a>|</li>
            	<li><a href="comment.jsp ">网友评价</a></li>
            </ul>
        </div>
        
        <div class="t_intro">
			<div class="intro_title">
            	<div class="intro_title_l">
                	<img src="../images/title_note_bg.gif" />
                    <span><strong>学校介绍</strong></span>
                </div>
                <div class="intro_title_r">
                    <span><a href="#">更多&gt;&gt;</a></span>
                </div>
            </div>
            
            <div class="clear"></div>
            <div class="intro_left">
                <div class="intro_img">
                    <table width="250" height="180" border="0" cellpadding="0" cellspacing="0" style="margin-left:5px;">
                      <tr>
                        <td width="250" height="180">
                        <div class=pic_show>
                          <div id=imgADPlayer></div>
                          <script> 
                                    PImgPlayer.addItem( "", "http://www.ziguiw.com/", "../images/01.jpg"); 
                                    PImgPlayer.addItem( "", "http://www.ziguiw.com/", "../images/02.jpg"); 
                                    PImgPlayer.addItem( "", "http://www.ziguiw.com/", "../images/03.jpg"); 
                                    PImgPlayer.addItem( "", "http://www.ziguiw.com/", "../images/04.jpg"); 
                                    PImgPlayer.addItem( "", "http://www.ziguiw.com/", "../images/05.jpg"); 
                                    PImgPlayer.addItem( "", "http://www.ziguiw.com/", "../images/06.jpg"); 
                                    PImgPlayer.init( "imgADPlayer", 250, 180 );   
                        </script>
                        </div></td>
                      </tr>
                    </table>
                </div>
                <div class="intro_words">
                    <p><a href="#">江西师范大学位于具有深厚历史文化底蕴、素有"物华天宝、人杰地灵"美誉的江西省会城市南昌，现有瑶湖、青山湖和共青城三个校区，是一所融文学、历史学、哲学、经济学、管理学、法学、理学、工学、教育学、艺术学等十大学科门类于一体，师范与非师范并举，对江西的政治、经济、文化和社会发展有较大影响、被省政府确定为优先发展的省属重点（师范）大学。学校占地面积4500余亩，建筑面积140余万平方米.余万册……</a></p>
                </div>
            </div>
            <div class="intro_right">
            	<div class="intro_inf">
                	<ul>
                    	<li><div class="s_name">学校名称</div><span class="s_name_w">东北财经大学</span></li>
                    	<li><div class="s_name">学校等级</div><span class="s_name_w">省级示范性中学</span></li>
                    	<li><div class="s_name">办学层次</div><span class="s_name_w">九年制学校</span></li>
                        <li><div class="s_name">联系电话</div><span class="s_name_w">010-52454582</span></li>
                        <li><div class="s_name">联系地址</div><span class="s_name_w">北京市朝阳区天马朝阳路254号</span></li>
                        <li><div class="s_name">学校网址</div><span class="s_name_w">www.zguiw.com</span></li>
                    </ul>
                </div>
            </div>
            
        </div>
        
        <div class="clear"></div>
        
        <div class="intro_photo">
			<div class="intro_title">
            	<div class="intro_title_l">
                	<span><img src="../images/title_note_bg.gif" /></span>
                    <span><strong>师资力量</strong></span>
                </div>
                <div class="intro_title_r">
                    <span><a href="#">更多&gt;&gt;</a></span>
                </div>
            </div>  
                
                <div class="clear"></div>
                
                <div class="intro_photo_img">
                	<ul>
                    	<li>
                        	<div class="photo_con"><img src="../images/wufumin.gif"/></div>
                            <p>吴富敏</p>
                        </li>
                        <li>
                        	<div class="photo_con"><img src="../images/wufumin.gif"/></div>
                            <p>吴富敏</p>
                        </li>
                        <li>
                        	<div class="photo_con"><img src="../images/wufumin.gif"/></div>
                            <p>吴富敏</p>
                        </li>
                        <li>
                        	<div class="photo_con"><img src="../images/wufumin.gif"/></div>
                            <p>吴富敏</p>
                        </li>
                        <li>
                        	<div class="photo_con"><img src="../images/wufumin.gif"/></div>
                            <p>吴富敏</p>
                        </li>
                        <li>
                        	<div class="photo_con"><img src="../images/wufumin.gif"/></div>
                            <p>吴富敏</p>
                        </li>
                        <li>
                        	<div class="photo_con"><img src="../images/wufumin.gif"/></div>
                            <p>吴富敏</p>
                        </li>
                    </ul>
                </div>
          
        </div>
        
        <div class="clear"></div>
        
        
      
      <div class="intro_news">
      		
      	<div class="intro_major">
      		<div class="m_intro_title">
            	<div class="m_intro_title_l">
                	<img src="../images/title_note_bg.gif" />
                    <span><strong>行业资讯</strong></span>
                </div>
                <div class="m_intro_title_r">
                    <span><a href="#">更多&gt;&gt;</a></span>
                </div>
            </div>
    

            <div class="m_intro_con">
            	<ul>
                	<li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                </ul>
            </div>
    	 </div>

		<div class="intro_major">
      		<div class="m_intro_title">
            	<div class="m_intro_title_l">
                	<img src="../images/title_note_bg.gif" />
                    <span><strong>学术交流</strong></span>
                </div>
                <div class="m_intro_title_r">
                    <span><a href="#">更多&gt;&gt;</a></span>
                </div>
            </div>
    

            <div class="m_intro_con">
            	<ul>
                	<li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                </ul>
            </div>
    	 </div>
         
         
         
         <div class="intro_honor">
      		<div class="m_intro_title">
            	<div class="m_intro_title_l">
                	<img src="../images/title_note_bg.gif" />
                    <span><strong>学校荣誉</strong></span>
                </div>
                <div class="m_intro_title_r">
                    <span><a href="#">更多&gt;&gt;</a></span>
                </div>
            </div>
    

            <div class="m_intro_con">
            	<ul>
                	<li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                    <li>
                    	<div class="news_left">
                    	<img src="../images/tri.gif" />
                    	<span><a href="#">教师节前夕开发区领导来学校</a></span></div>
                        <span class="news_time">2012-09-01</span>
                    </li>
                </ul>
            </div>
    	 </div>
         
         
        
      </div>
      
     <div class="clear"></div>
     
   		<div class="intro_photo">
			<div class="intro_title">
            	<div class="intro_title_l">
                	<img src="../images/title_note_bg.gif" />
                    <span><strong>校园风光</strong></span>
                </div>
                <div class="intro_title_r">
                    <span><a href="#">更多&gt;&gt;</a></span>
                </div>
            </div>  
                
                <div class="clear"></div>
                
                <div class="intro_school_img">
                	<ul>
                    	<li>
                        	<img src="../images/lib.jpg"/>
                        </li>
                        <li>
                        	<img src="../images/lib.jpg"/>
                        </li>
                        <li>
                        	<img src="../images/lib.jpg"/>
                        </li>
                        <li>
                        	<img src="../images/lib.jpg"/>
                        </li>
                    </ul>
                </div>
          
        </div>
        
     <div class="clear"></div>
   		<div class="intro_comm">
			<div class="intro_title">
            	<div class="intro_title_l">
                	<img src="../images/title_note_bg.gif" />
                    <span><strong>网友评价</strong></span>
                </div>
                <div class="intro_title_r">
                    <span><a href="#">更多&gt;&gt;</a></span>
                </div>
            </div>  

                
                <div class="clear"></div>	
                
                <div class="comment_out">
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
						<textarea class="commtext_con"rows="8"></textarea>
                    </div>
                    <div class="comme_b">
                    	<div class="comme_b_left">
                        	<p style="height:27px;line-height:27px;">网友评论仅供其表达个人看法，并不表明本站同意其观点或证实其描述。</p>
                        </div>
                        <div class="comme_b_right">
							<input type="checkbox" class="usinp" /><span style="margin:0 8px;">匿名发表</span>
                            <input type="button" border="0" class="comme_ok"/>
                        </div>
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
