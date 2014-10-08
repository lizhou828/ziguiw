<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/templateHead.jsp"%>
<div class="content clearfix pr">
	<%@ include file="inc/left.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea w-760 hei-400 r about_us">
		<!--<h2 class="col-h3">&nbsp;</h2>-->
	  <div style="padding-left:20px; padding-right:20px;" id="_wContent">
		<h3><img src="../images/about_content_title.gif" width="673" height="83" /></h3>
			
		  <style>
			.yq{ border-bottom:1px solid #e0e0e0; height:30px}
          	.yq li{ float:left ; margin-right:10px; }
			.yq-1{ padding-left:50px; font-size:12px; list-style-type:none}
          </style>
			<div class="ttl-1 font-2"><strong>网络运营部</strong></div>
            <ul class="yq font-2">
              <li>招聘岗位：<b>技术部主管</b></li>
              <li>招聘人数：<b>1人</b></li>
              <li>专业：<b>计算机或相关专业</b></li>
              <div class="clear"></div>
            </ul>
            <ul class="yq-1 font-2">
              <li>岗位要求:</li>
              <li>1、精通网站架构和服务器构建；</li>
              <li>2、精通PHP/JAVA语言，有实际的项目开发经验，有成功作品案例者优先；</li>
              <li>3、有良好的编码习惯，代码编写规范，热爱开发事业；</li>
              <li>4、能够从技术角度提供网站和数据库的的优化建议，制定优化方案；</li>
              <li>5、沟通能力强，能有效制定计划，并把控项目进度；</li>
              <li>6、拥有1年以上技术运营及技术管理团队经验，保证按时按量完成工作；</li>
              <li>7、有一定的学习和理解能力，有较强的分析及解决问题的能力；</li>
              <li>8、具有面向对象分析及设计思想的经验者优先；</li>
              <li><b>面试时，应聘者请携带好大型网站开发的作品案例；</b></li>
            </ul>
            <ul class="yq font-2">
              <li>招聘岗位：<strong>网站采编</strong></li>
              <li>招聘人数：<b>1人</b></li>
              <li>专业：<b>新闻学、传播学相关专业</b></li>
              <div class="clear"></div>
            </ul>
            <ul class="yq-1 font-2">
              <li>岗位要求:</li>
              <li>1、负责公司网站的管理及日常信息更新、维护，采集相关资料； </li>
              <li>2、视野开阔、思维活跃。有很好的文字功底，有策划选题、撰写文章的能力；</li>
              <li>3、负责网站的炒作，信息交流和用户沟通； </li>
              <li>4、有2年以上网站编辑、媒体工作经验； </li>
              <li>5、有较强的工作责任心，踏实肯干，工作认真、严谨，团队合作精神强，善于与人沟通；</li>
              <li>6、对网站用户界面、网站内容、网站功能有一定的了解；</li>
            </ul>
            <ul class="yq font-2">
              <li>招聘岗位：<strong>网站美工</strong></li>
              <li>招聘人数：<b>1人</b></li>
              <li>专业：<b>美术相关专业</b></li>
              <div class="clear"></div>
            </ul>
            <ul class="yq-1 font-2">
              <li>岗位要求:</li>
              <li>1、精通Photoshop、llustrator、Flash、dreamweaver等网页设计图形设计软件，有独立设计制作公司网站的能力； </li>
              <li>2、精通HTML. DIV+CSS .Javascript,能看懂和修改基本代码，熟悉Web标准，对网站整合及栏目设置有一定见解； </li>
              <li>3、有一定的美术功底和网站/专题策划能力，熟悉大型网站制作的整套流程，有丰富的创意； </li>
              <li>4、善于协作与沟通，具备良好的团队合作精神，能与程序员很好地配合； </li>
              <li>5、美术或设计相关专业大专以上学历，2年以上设计工作经验； </li>
              <li><b>面试时，应聘者请携带好大型网站开发的作品案例；</b></li>
            </ul>
            <ul class="yq font-2">
              <li>招聘岗位：<strong>网站程序员</strong></li>
              <li>招聘人数：<b>1人</b></li>
              <li>专业：<b>计算机或相关专业</b></li>
              <div class="clear"></div>
            </ul>
            <ul class="yq-1 font-2">
              <li>岗位要求:</li>
              <li>1、有3年以上相关工作经历，精通PHP、JAVA、MSSQL网站开发，有实际的大型项目开发经验； </li>
              <li>2、熟悉网站架构和服务器构建； </li>
              <li>3、有良好的编码习惯，代码编写规范，热爱开发事业； </li>
              <li>4、熟悉网站页面静态处理技术，具有SEO优化开发经验并部署实施； </li>
              <li>5、服从意识好、上进心强，忠诚，能吃苦耐劳，对互联网充满热情； </li>
              <li>6、有良好的沟通和学习能力、有较强的团队协作能力以及快速解决问题的能力； </li>
              <li><b>面试时，应聘者请携带好大型网站开发的作品案例；</b></li>
            </ul>
        </div>
	</div>
  
</div>    
<!--content E -->
<script type="text/javascript">


function initSelect(){
	var sex = document.getElementById("sex");
	var province = document.getElementById("province");

	var oldSex = document.getElementById("oldSex").value;
	var oldProvince = document.getElementById("oldProvince").value;
	
	
    for(var i=0;i<sex.options.length;i++)
    {
        if(sex.options[i].value == oldSex)
        {
        	sex.options[i].selected = true;
            break;
        }
    }
    
    for(var i=0;i<province.options.length;i++)
    {
        if(province.options[i].value == oldProvince && oldProvince != "")
        {
        	province.options[i].selected = true;
        	break;
        }
    }
    
    
    
    
}

initSelect();
</script>

<%@ include file="../inc/templateFoot.jsp"%>