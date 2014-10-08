<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>师资力量——子贵网</title>
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
            	<li id="menu_on"><a href="teachers.jsp">师资力量</a>|</li>
            	<li><a href="news.jsp">学校动态</a>|</li>
            	<li><a href="campus.jsp">校园风光</a>|</li>
            	<li><a href="recruit.jsp">学校招聘</a>|</li>
            	<li><a href="contact.jsp">联系方式</a>|</li>
            	<li><a href="comment.jsp">网友评价</a></li>
            </ul>
        </div>
        
	
            <div class="comm_k">
            	<div class="comm_w">
                	<p>师资力量</p>
                </div>
                <div class="teachers_a">
                	<table>
                        <tr>
                        	<th>老师姓名</th>
                            <th>照片</th>
                            <th>年/班级</th>
                            <th>任教科目</th>
                            <th>职位</th>
                            <th>职称</th>
                        </tr>
                    	<tr>
                        	<td>张老师</td>
                            <td><p><img src="../images/zhang.gif" /></p></td>
                            <td>一年级3班</td>
                            <td>语文</td>
                            <td>班主任</td>
                            <td>优秀教师</td>
                        </tr>
                    	<tr>
                        	<td><b>详细内容：</b></td>
                        	<td colspan="5" style="text-align:left; text-align:justify;text-justify:inter-ideograph;"> 杨慧，女，汉族，中共党员，中学英语一级教师，于1995年8月开始从事中学英语教学工作，是学校英语教研组组长。
 在教学工作中，善用现代教育技术手段辅助教学，以提高课堂教学效率，教学成绩突出。多次评为市、县优秀教师及优秀班主任；多篇论文在省市刊物发表，其中教育故事《给孩子成长的机会》被广东省中小学教育研究指导中心编进《真情言说》一书并出版。同时还获得梅县优秀班主任专业能力比赛一等奖；第二届梅州市班主任专业能力比赛一等奖；广东省中职组"教育故事叙述"一等奖；广东省中职组班主任能力大赛综合三等奖。 </td>
                        </tr>
                    </table>
                </div>
                                <div class="teachers_a">
                	<table>
                        <tr>
                        	<th>老师姓名</th>
                            <th>照片</th>
                            <th>年/班级</th>
                            <th>任教科目</th>
                            <th>职位</th>
                            <th>职称</th>
                        </tr>
                    	<tr>
                        	<td>张老师</td>
                            <td><p><img src="../images/zhang.gif" /></p></td>
                            <td>一年级3班</td>
                            <td>语文</td>
                            <td>班主任</td>
                            <td>优秀教师</td>
                        </tr>
                    	<tr>
                        	<td><b>详细内容：</b></td>
                        	<td colspan="5" style="text-align:left; text-align:justify;text-justify:inter-ideograph;"> 杨慧，女，汉族，中共党员，中学英语一级教师，于1995年8月开始从事中学英语教学工作，是学校英语教研组组长。
 在教学工作中，善用现代教育技术手段辅助教学，以提高课堂教学效率，教学成绩突出。多次评为市、县优秀教师及优秀班主任；多篇论文在省市刊物发表，其中教育故事《给孩子成长的机会》被广东省中小学教育研究指导中心编进《真情言说》一书并出版。同时还获得梅县优秀班主任专业能力比赛一等奖；第二届梅州市班主任专业能力比赛一等奖；广东省中职组"教育故事叙述"一等奖；广东省中职组班主任能力大赛综合三等奖。 </td>
                        </tr>
                    </table>
                </div>
                                <div class="teachers_a">
                	<table>
                        <tr>
                        	<th>老师姓名</th>
                            <th>照片</th>
                            <th>年/班级</th>
                            <th>任教科目</th>
                            <th>职位</th>
                            <th>职称</th>
                        </tr>
                    	<tr>
                        	<td>张老师</td>
                            <td><p><img src="../images/zhang.gif" /></p></td>
                            <td>一年级3班</td>
                            <td>语文</td>
                            <td>班主任</td>
                            <td>优秀教师</td>
                        </tr>
                    	<tr>
                        	<td><b>详细内容：</b></td>
                        	<td colspan="5" style="text-align:left; text-align:justify;text-justify:inter-ideograph;"> 杨慧，女，汉族，中共党员，中学英语一级教师，于1995年8月开始从事中学英语教学工作，是学校英语教研组组长。
 在教学工作中，善用现代教育技术手段辅助教学，以提高课堂教学效率，教学成绩突出。多次评为市、县优秀教师及优秀班主任；多篇论文在省市刊物发表，其中教育故事《给孩子成长的机会》被广东省中小学教育研究指导中心编进《真情言说》一书并出版。同时还获得梅县优秀班主任专业能力比赛一等奖；第二届梅州市班主任专业能力比赛一等奖；广东省中职组"教育故事叙述"一等奖；广东省中职组班主任能力大赛综合三等奖。 </td>
                        </tr>
                    </table>
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
