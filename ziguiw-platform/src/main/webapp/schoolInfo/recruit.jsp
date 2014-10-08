
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>学校招聘——子贵网</title>
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
            	<li id="menu_on"><a href="recruit.jsp">学校招聘</a>|</li>
            	<li><a href="contact.jsp">联系方式</a>|</li>
            	<li><a href="comment.jsp">网友评价</a></li>
            </ul>
        </div>
        
	
            <div class="comm_k">
            	<div class="comm_w">
                	<p>学校招聘</p>
                </div>
                <div class="recruit_l">
                	<p style="color:red; font-size:14px;font-weight:bold;height:25px;">人才理念</p>
                    <p style="color:#7c7c7c;text-indent:25px;">核心理念：让想干事的人有机会，能干事的人有舞台，干成事的人有地位有待遇。唯才是举，真才必用。
</p>
                </div>
                <!--招聘内容开始-->
                <div class="recruit_con">
                	<table>
                    	<tr>
                        	<td class="position" colspan="5" style="font-size:18px;">小学英语教师</td>
                        </tr>
                        <tr>
                        	<td class="posi_gray" width="80">规模：30人</td>
                        	<td class="posi_gray" colspan="3">时间：2012年10月12日-2012年11月12日</td>
                        </tr>
                        <tr>
                        	<td>&nbsp;</td>
                        </tr>
                        <tr>
                        	<td><b>职位信息</b></td>
                        </tr>
                        <tr>
                        	<td class="pisi_info">更新时间：</td>
                            <td class="pisi_c">今天</td>
                            <td class="pisi_info">薪资范围：</td>
                            <td class="pisi_c">面议</td>
                        </tr>
                        <tr>
                        	<td class="pisi_info">学历要求：</td>
                            <td class="pisi_c">不限</td>
                            <td class="pisi_info">招聘人数：</td>
                            <td>6人</td>
                        </tr> 
                        <tr>
                        	<td class="pisi_info">工作经验：</td>
                            <td class="pisi_c">不限</td>
                            <td class="pisi_info">工作区域：</td>
                            <td class="pisi_c">长沙</td>
                        </tr>
                        <tr>
                        	<td>&nbsp;</td>
                        </tr>
                        <tr>
                        	<td colspan="2"> <b>岗位要求：</b></td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <p>1、英语师范专业或有1年以上相关教学经验；</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <p>2、上课生动活泼，吸引学生；</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <p>3、工作有责任心、耐心；</p>
                            </td>
                        </tr>
                        <tr>
                        	<td colspan="4">
                                <p>4、吃苦耐劳，服从学校安排；</p>
                            </td>
                        </tr>
                        	<td colspan="4">
                                <p>5、一经录用，公司提供食宿。</p>
                            </td>
                        </tr>
                    </table>
                    <div class="posi_b">
                    	<input class="posi_btn" type="button" value="申请该职位"/>
                        <div class="posi_p">
                        	<p>联系人：王老师</p>
                            <p>联系电话：0731-11222222</p>
                        </div>
                    </div>
                </div>
				<div class="clear"></div>
                
			<!--招聘内容结束-->
				
			<!--招聘内容开始-->
                  <div class="recruit_con">
                	<table>
                    	<tr>
                        	<td class="position" colspan="5" style="font-size:18px;">小学英语教师</td>
                        </tr>
                        <tr>
                        	<td class="posi_gray" width="80">规模：30人</td>
                        	<td class="posi_gray" colspan="3">时间：2012年10月12日-2012年11月12日</td>
                        </tr>
                        <tr>
                        	<td>&nbsp;</td>
                        </tr>
                        <tr>
                        	<td><b>职位信息</b></td>
                        </tr>
                        <tr>
                        	<td class="pisi_info">更新时间：</td>
                            <td class="pisi_c">今天</td>
                            <td class="pisi_info">薪资范围：</td>
                            <td class="pisi_c">面议</td>
                        </tr>
                        <tr>
                        	<td class="pisi_info">学历要求：</td>
                            <td class="pisi_c">不限</td>
                            <td class="pisi_info">招聘人数：</td>
                            <td>6人</td>
                        </tr> 
                        <tr>
                        	<td class="pisi_info">工作经验：</td>
                            <td class="pisi_c">不限</td>
                            <td class="pisi_info">工作区域：</td>
                            <td class="pisi_c">长沙</td>
                        </tr>
                        <tr>
                        	<td>&nbsp;</td>
                        </tr>
                        <tr>
                        	<td colspan="2"> <b>岗位要求：</b></td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <p>1、英语师范专业或有1年以上相关教学经验；</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <p>2、上课生动活泼，吸引学生；</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <p>3、工作有责任心、耐心；</p>
                            </td>
                        </tr>
                        <tr>
                        	<td colspan="4">
                                <p>4、吃苦耐劳，服从学校安排；</p>
                            </td>
                        </tr>
                        	<td colspan="4">
                                <p>5、一经录用，公司提供食宿。</p>
                            </td>
                        </tr>
                    </table>
                    <div class="posi_b">
                    	<input class="posi_btn" type="button" value="申请该职位"/>
                        <div class="posi_p">
                        	<p>联系人：王老师</p>
                            <p>联系电话：0731-11222222</p>
                        </div>
                    </div>
                </div>
				<div class="clear"></div>
			<!--招聘内容结束-->
               
                                
                                
                
          
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
