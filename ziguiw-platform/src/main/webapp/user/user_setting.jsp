<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>

<div class="w_home_content">
  <div class="w_home_content_box clearfix">
<div class="content clearfix   pr">
<%@ include file="inc/right.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400  r">
       	<h2 class="col-h3">设置个人资料</h2>

		<form method="post" action="<%=path%>/user/updateSetting.action">
		<ul class="link_gray ul-li-s">
			<li><span>性别：</span><select name="user.sex" id="sex">
				<option value="0">保密</option>
				<option value="1">男</option>
				<option value="2">女</option>
			</select>
			<input type="hidden" id="oldSex" value="<%=hostUser.getSex()%>">
			</li>
			<li><span>地区：</span>
			<select name="user.province" id="province">
				<option value="北京" >北京</option>
			    <option value="天津" >天津</option>
			    <option value="上海" >上海</option>
			    <option value="重庆" >重庆</option>
			    <option value="香港" >香港</option>
			    <option value="澳门" >澳门</option>
			    <option value="河北" >河北</option>
			    <option value="山西" >山西</option>
			    <option value="内蒙古" >内蒙古</option>
			    <option value="辽宁" >辽宁</option>
			    <option value="吉林" >吉林</option>
			    <option value="黑龙江" >黑龙江</option>
			    <option value="江苏" >江苏</option>
			    <option value="浙江" >浙江</option>
			    <option value="安徽" >安徽 </option>
			    <option value="福建" >福建 </option>
			    <option value="江西" >江西</option>
			    <option value="山东" >山东</option>
			    <option value="河南" >河南</option>
			    <option value="湖北" >湖北</option>
			    <option value="湖南" >湖南</option>
			    <option value="广东" >广东</option>
			    <option value="广西" >广西</option>
			    <option value="海南" >海南</option>
			    <option value="四川" >四川</option>
			    <option value="贵州" >贵州</option>
			    <option value="云南" >云南</option>
			    <option value="西藏" >西藏</option>
			    <option value="陕西" >陕西 </option>
			    <option value="甘肃" >甘肃 </option>
			    <option value="青海" >青海</option>
			    <option value="宁夏" >宁夏</option>
			    <option value="新疆" >新疆</option>
			    <option value="台湾" >台湾</option>
			</select>
			
			<input type="hidden" id="oldProvince" value="<%=hostUser.getProvince()%>">
			</li>
			<li><span>个性签名：</span>
				<textarea name="user.signature" cols="20" rows="2" class="area"><%=hostUser.getSignature()%></textarea></li>	
			
			<li><input type="hidden" name="user.id" value="100"></li>
			<li><input name="Submit1" type="submit" value="提 交" class="btn2"/></li>	
				
		</ul>
		</form>
    </div>
  
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