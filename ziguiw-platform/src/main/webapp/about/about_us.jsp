<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/templateHead.jsp"%>
<LINK rel=stylesheet type=text/css href="../css/basc.css" />

<div class="content clearfix pr">
	<%@ include file="inc/left.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400 r about_us">
		<!--<h2 class="col-h3">&nbsp;</h2>-->
		<div id="_wContent">
			<h3><img src="../images/about_content_title.gif" width="673" height="83" /></h3>
			
			<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;子贵网是中国最大的孩子成长信息平台，也是孩子成长重要的教育资源门户，不仅为全国家长提供优质专业的服务, 引导孩子健康成长，而且通过整合优质的教学资源，为全国高中小学各学科教师提供了展示教学水平的平台。更是中国孩子sns垂直社交网站的领跑者和佼佼者。为孩子成长带来一个绿色的精神家园和全新的世界。 </p>
		  <h3 class="a-l">子贵网部分服务介绍如下：</h3>
            <div style="padding-left:60px">
            <div class="line"></div>
			<h4 >◆ 孩子学习情况</h4>
			<p class="shortContent">家长可以即时登陆平台查看孩子在校表现、 考试成绩、 学校通知、学期评语、孩子作业、班级活动等。</p>
            <div class="line"></div>
			<h4>◆ 孩子安全信息</h4>
			<p class="shortContent">家长可以即时登陆平台查看孩子的出勤记录和考勤统计信息，同时学校可以利用强大的考勤功能，进行全方位的考勤管理。</p>
            <div class="line"></div>
			<h4> ◆ 家校互联互动</h4>
			<p class="shortContent">家长可以登陆平台，可以查看学校和班级的活动报道、教师留言、任课教师情况介绍等情况。</p>
            <div class="line"></div>
			<h4>◆ 孩子交流平台</h4>
			<p class="shortContent">子贵网在孩子成长信息网站中拓展了一个新的天空，实现了学生版的facebook。孩子可以登陆平台，建立自己的空间、找到兴趣相同的朋友、互相交流学习经验等.</p>
                        <div class="line"></div>
			</div>

			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;子贵网秉承为用户提供满意服务，以中国孩子健康成长为宗旨，与时俱进、开拓进取，凭借自身行业优势、技术优势，顺应市场需求和信息化潮流，打造中国最大、最专业、最权威的孩子成长信息平台而努力。</p>
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