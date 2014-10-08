<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>校长信箱——子贵网</title>
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
            	<li><a href="inner.jsp">学校主页</a>|</li>
            	<li><a href="about.jsp">学校介绍</a>|</li>
            	<li><a href="spee.jsp">校长致辞</a>|</li>
            	<li id="menu_on"><a href="letter.jsp">校长信箱</a>|</li>
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
                	<p>校长信箱</p>
                </div>
                <div class="letter_l">
                    <div class="add">
                         校长信箱地址：**@952116.com
                    </div>
                    <form>
                    	<input type="text" class="l_title" value="点击输入标题" onfocus="if (value =='点击输入标题'){value =''}" onblur="if (value ==''){value='点击输入标题'}" />
                    </form>
                    <div class="remark">
                    	<p>选择分类：<input type="radio" value="" name=""/>留言<input name="" type="radio" value="" />建议<input name="" type="radio" value="" />举报<input name="" type="radio" value="" />投诉</p>
                        <p>留言内容：<span style="color:red;">（留言内容只有校长办公室相关负责人可见，请家长放心填写）</span></p>
                    </div>
					<textarea class="l_cont"></textarea>                    
                    <div class="l_info">
                    	<p>请输入有效的联系方式，以便尽快给您回复！</p>
                        <p>您的电话：<input type="text" class="heig"/></p>
                        <p>您的邮箱：<input class="heig" type="text"/></p>
                        <p>您的姓名：<input class="heig" type="text" value="匿名" onfocus="if (value=='匿名'){value=''}" onblur="if(value==''){value='匿名'}"/></p>
                        <p><input type="button" class="lett_btn"  value="确认提交"/></p>
                    </div>
          		</div>
                <div class="letter_r">
                	<p>欢迎来到**学校官方网站，如果有任何意见和建议，欢迎留言告诉我们。</p>
                    <p>如果您的孩子在**学校学习的过程中发现或发生任何违法、违规或不正当的行为，您可以选择举报或投诉。</p>
                    <p>您的意见和建议是我们前进的动力，您的参与将帮助我们改进服务和提升品质!</p>
                    <p>请正确选择留言分类，以便系统分配给相关的负责人来跟进并及时给您回复，留言内容只有校长办公室相关负责人可见，请家长放心填写。</p>
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
