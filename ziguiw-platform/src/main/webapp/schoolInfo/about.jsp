
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>学校介绍——子贵网</title>
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
            	<li id="menu_on"><a href="about.jsp">学校介绍</a>|</li>
            	<li><a href="spee.jsp">校长致辞</a>|</li>
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
                	<p>学校介绍</p>
                </div>
                <div class="about_c">
                	<div class="about_inf">
                    	<div class="about_title">衡水市第二中学</div>
                            <div class="a_intro_inf">
                                <ul>
                                    <li><div class="a_s_name">学校名称</div><span class="a_s_name_w">东北财经大学</span></li>
                                    <li><div class="a_s_name">学校等级</div><span class="a_s_name_w">省级示范性中学</span></li>
                                    <li><div class="a_s_name">办学层次</div><span class="a_s_name_w">九年制学校</span></li>
                                    <li><div class="a_s_name">联系电话</div><span class="a_s_name_w">010-52454582</span></li>
                                    <li><div class="a_s_name">联系地址</div><span class="a_s_name_w">北京市朝阳区天马朝阳路254号</span></li>
                                    <li><div class="a_s_name">学校网址</div><span class="a_s_name_w">www.zguiw.com</span></li>
                                </ul>
                            </div>
                        
                        
                       <!-- <div class="about_l">
                    	<p><b>学校性质</b>：公办</p>
                        <p><b>学校等级</b>：省级示范性中学</p>
                        <p><b>办学层次</b>：九年制小学</p>
                        <p><b>联系电话</b>：0731-54654555</p>
                        <p><b>联系地址</b>：湖南省长沙市</p>
                        <p><b>学校网址</b>：www.ziguiw.com</p>
                    </div>-->
                    	<p>衡水市第二中学暨衡水外国语学校创建于1996年，与新生的衡水市同年诞生，是河北省重点高中（省级示范性学校），是衡水市人民政府直属的两所重点高中之一，是衡水市政府授权面向衡水11个县市区招生的唯一一所市直重点中学，是衡水市唯一一所外国语学校。现有在校学生7800人，教职工400余人。近年来，学校贯彻落实科学发展观，树立"以师生发展为本，办人民满意教育"的办学思想，全面实施素质教育，取得了突出的办学成绩，成为全市12所重点中学中发展最快的一流学校。</p>
                        <p><b>设施建设</b>：学校占地210亩，有6×400m塑胶环形跑道的高标准操场，并有塑胶10个标准篮球场和4个排球场。学校建有理化生实验室15个、多媒体教室4个、语音室10个、计算机教室6个、电子备课室5个、配有微机1000余台；各教室配备了微机、多媒体投影等现代化教学设备；建成了多媒体双向教学系统；建设了宽带数字化校园网络，教师人手一台高配置微机，教学办公实现了自动化、信息化。所有教室、宿舍配备空调，每个宿舍都安装了IC卡电话。</p>
                        <p><b>办学特色</b>：学校始终坚持"面向全体学生，优化教育过程，培养素质特长，促进全面发展" 的教育原则，把"求知、创新、发展、责任感"作为学生培养目标，逐步形成了以"面向全体，分层教学，培优补差，人人成才"为主要特色的教学模式，促使优等生更加优秀，不同层次、不同类别的学生都得到转化、提高和发展。</p>
                        <p><b>师资队伍</b>：学校拥有专任教师360人，其中高级教师占26％，中级教师占41％，拥有本科学历的教师100％。衡水二中师资力量雄厚，教师队伍学历层次高，政治素质高，业务素质硬，师德高尚，结构合理。</p>
                        <p><b>取得成绩</b>：近几年我校高考成绩一步一个台阶，虽无一流生源，但创一流佳绩。2009年高考，衡水二中取得优异成绩：1、本一上线人数1019，本二上线人数2016。 2、文科本二上线人数名列全省第一。 3、应届生文科本二上线人数名列全省第一。 4、应届生本一、本二上线人数均名列全市第二。 5、应届生本一、本二上线率均名列全市第二。 6、应届生文科本一上线人数名列全市第二。 7、文科本一上线人数名列全市第二。 8、语文、数学、英语、文科综合、理科综合平均分及总平均分均名列全市第二。（注：以上各数据均不含音体美特长生）</p>
                        <p><b>两个校区</b>：衡水二中分设东西两个校区，东校区位于问津北街98号，西校区位于新华西路256号，东西两个校区实行统一管理。根据教学业绩对任课教师实行平均调配。高一入学新生则完全按照成绩均衡分班、分校区。两个校区站在同一起跑线上，学生评价和教师评价实行统一的评价体系，两个校区之间要形成竞争态势，以竞争促发展、促提高。 两个校区的硬件建设同步进行，实验室、微机室、多媒体教室、语音室、教室内设施都统一配置。 学校的作息时间、课时分配、教学管理、学生管理、后勤服务、餐厅管理实行统一标准，对学生入学军训、日常行为、就餐制度、宿舍内务的管理以及班级量化评比实行统一标准。</p>
                        <p><b>未来展望</b>：我们的发展目标：一流的教育观念，一流的校园环境，一流的教学装备，一流的师资队伍，一流的教学质量，一流的管理水平；把学校办成省内一流的"示范性高中"。</p>
                    </div>

                    <div class="about_r">
                    	<ul>
                    		<li><img src="../images/about.gif"/></li>
                            <li><img src="../images/about.gif"/></li>
                            <li><img src="../images/about.gif"/></li>
                        </ul>
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
