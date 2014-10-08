<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>校长致辞——子贵网</title>
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
            	<li id="menu_on"><a href="spee.jsp">校长致辞</a>|</li>
            	<li><a href="letter.jsp">校长信箱</a>|</li>
            	<li><a href="teachers.jsp">师资力量</a>|</li>
            	<li><a href="news.jsp">学校动态</a>|</li>
            	<li><a href="campus.jsp">校园风光</a>|</li>
            	<li><a href="recruit.jsp">学校招聘</a>|</li>
            	<li><a href="contact.jsp">联系方式</a>|</li>
            	<li><a href="comment.jsp">网友评价</a></li>
            </ul>
        </div>
        
	
            <div class="comm_k">
            	<div class="comm_w">
                	<p>校长致辞</p>
                </div>
                <div class="spee_con">
                	<h1>给家长的一封信</h1>
                    <h2>作者：王校长</h2>
                    <p>亲爱的爸爸、妈妈：</p>
                    <p class="teachers_t">你们好！</p>
                    <p class="teachers_t">我在这所新学校已经学习了差不多一个学期了，各方面都很适应，尤其是学习，已经完全踏入了正轨，这里的设施都很齐全，学习、生活都很方便，再加上华附的一套成熟的管理方法，使这里完全不像一个才开办的新学校，你们完全不必为我的学习和生活担心。</p>
                    <p class="teachers_t">这里的老师都很勤奋努力地工作，对我们的学习抓得很紧，想方设法提高我们的学习成绩和各方面的素质。</p>
                    <p class="teachers_t">黄启林校长，还不到四十岁，前几年他评上特级教师时是我省最年轻的特级教师、苏步青数学一等奖获得者、中国数学奥林匹克高级教练员，他教的学生已有多人获得国际数学奥林匹克竞赛的金牌，现任我们的数学老师，他每周给我们上六节正课，还在星期四下午上两节竞赛辅导课，工作量很大，负担很重，但他很认真，从不缺一节课，有时还带病坚持上课，使我们都很感动。能听他讲课，做他的学生，是我们的福气。他的教学方法的确不同凡响，经过一个学期的学习，我班同学的数学成绩提高得很快，尤其是我们清远来的十几个同学，如吕小凡、吴晓雨、谢炳初等同学进步更大，当然我也不落后。</p>
                    <p class="teachers_t">还有教政治的徐志洪副校长、教电脑的余应邦主任、教英语的曹玲老师等来自华师附中的教学骨干，他们给我们带来了全新的教学方法，使我们在快乐中学到丰富的知识。其他的老师也一样。学校为每位老师都配备一台手提电脑，几乎每节课他们都用精美实用的多媒体课件上课，大大提高了我们的学习效率和质量。</p>
                    <p class="teachers_t">每位老师都想很多办法来提高我们的学习兴趣和学习成绩。例如英语的听力与阅读理解都抓得很紧，每单元有两次听力训练，还有everyday  reading——每天两篇的阅读理解，对我的听力、积累词汇有很大的帮助，一学期下来进步很大。又如语文进行五分钟的课前说话，内容有介绍作家作品、讲《三国演义》等，能训练我们的口头表达能力和记忆力，还要每天背一首诗，每星期写一篇生活杂记或读书笔记，还要练字等等，一个学期积累下来，对写作能力乃至文化素质修养都大有裨益。</p>
                    <p class="teachers_t">学校进行了许多竞赛来提高我们的学习积极性。这个学期语文和英语各进行了四次竞赛，我获得语文演讲比赛一等奖、语文知识竞赛三等奖、英语阅读竞赛二等奖。这些活动对我能力的培养及成长是极为有益的。当然我还要继续努力，争取更好的成绩向你们汇报。</p>
                    <p class="teachers_t">学校总共开设了二十多个课外活动小组来提高同学们各方面的素质。仅数学一科就开设了五个：奥林匹克竞赛小组，基础、初级、中级、高级提高小组，这些课外活动是对我们课本知识和课外知识的有益补充，当然老师们要付出艰辛的劳动了。我根据自己的情况，参加了动画设计小组、莎士比亚英语戏剧小组、物理化学兴趣小组和读书会等。我希望通过这些"完整的现代教育"做一个"高素质的现代人"，这是我们学校的办学宗旨。</p>
                    <p class="teachers_t">衷心祝你们</p>
                    <p>身体健康！</p>
                    <p style="text-align:right;">2012年9月22日</p>
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
