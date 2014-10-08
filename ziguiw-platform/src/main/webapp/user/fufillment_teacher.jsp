<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>
<%
CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
Teacher teacher = null;
if(user.getType() == 2 && user.getForeignKey() != null  && user.getForeignKey() > 0){
	teacher = commonService.get(Teacher.class, user.getForeignKey());
}

if(teacher == null){
	teacher = new Teacher();
}
%>

<div class="content clearfix   pr">
<%@ include file="inc/right.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400  r">
       	<h2 class="col-h3">完善教师信息</h2>

		<form method="post" action="<%=path%>/user/fillment_teacher.action">
		<ul class="link_gray ul-li-s" name="T_TEACHERINFO" id="T_TEACHERINFO">
			<li><span>姓名:</span><input name="teacher.name" id="name" type="text" class="inp_txt" value="<%=teacher.getName() == null ? "" : teacher.getName()%>"/></li>
			
			<li><span>学校:</span><select name="teacher.school.xx_ID" id="XxID" class="inp_txt" style="height:30px">
				<s:action var="getVipSchools" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
			     	<s:param name="hql">select sc from UserBase as ub, School as sc  where ub.type = 1 and ub.foreignKey = sc.xx_ID and ub.state >= 2</s:param>
			     	<s:param name="queryString">1=1</s:param>
				</s:action>
				<s:iterator value="#getVipSchools.obj.list" var="school">
					<option value="<s:property value="#school.xx_ID"/>"><s:property value="#school.sch_name"/></option>
				</s:iterator>
				</select>
			</li>
			
			<li><span>职称:</span><select name="teacher.jobtilte" id="jobtilte" type="text" class="inp_txt" style="height:30px">
				<option value="1">初级</option>
				<option value="2">初级</option>
				<option value="3">高级</option>
			</select></li>
			
			</		
			<li><span>学历:</span><select name="teacher.Education" id="Education" type="text" class="inp_txt" style="height:30px">
				<option value="1">中专/高中</option>
				<option value="2">本科</option>
				<option value="3">研究生</option>
			</select>
			</li>		
			<li><span>毕业学校:</span><input name="teacher.graduatesch" id="graduatesch" type="text" class="inp_txt" value="<%=teacher.getGraduatesch() == null ? "" : teacher.getGraduatesch()%>"/></li>		
			<li><span>在校职务:</span><input name="teacher.duties" id="duties" type="text" class="inp_txt" value="<%=teacher.getDuties() == null ? "" : teacher.getDuties()%>"/></li>		
			<li><span>个人简历:</span><input name="teacher.resume" id="resume" type="text" class="inp_txt" value="<%=teacher.getResume() == null ? "" : teacher.getResume()%>"/></li>		
			<li><span>办公电话:</span><input name="teacher.officephone" id="officephone" type="text" class="inp_txt" value="<%=teacher.getOfficephone() == null ? "" : teacher.getOfficephone()%>"/></li>		
			<li><span>办公传真:</span><input name="teacher.fax" id="fax" type="text" class="inp_txt" value="<%=teacher.getFax() == null ? "" : teacher.getFax()%>"/></li>		
			<li><span>家庭电话:</span><input name="teacher.homephone" id="homephone" type="text" class="inp_txt" value="<%=teacher.getHomephone() == null ? "" : teacher.getHomephone()%>"/></li>		
			<li><span>手机:</span><input name="teacher.moblie" id="moblie" type="text" class="inp_txt" value="<%=teacher.getMoblie() == null ? "" : teacher.getMoblie()%>"/></li>		
			<li><span>家庭邮编:</span><input name="teacher.zipcode" id="zipcode" type="text" class="inp_txt" value="<%=teacher.getZipcode() == null ? "" : teacher.getZipcode()%>"/></li>		
			<li><span>家庭住址:</span><input name="teacher.address" id="address" type="text" class="inp_txt" value="<%=teacher.getAddress() == null ? "" : teacher.getAddress()%>"/></li>	
			<input type="hidden" name="teacher.teacherID" value="<%=teacher.getTeacherID() == 0 ? "" : teacher.getTeacherID()%>"/>					
			<li><input name="Submit1" type="submit" value="确认修改" class="btn2"/></li>	
				
		</ul>
		</form>
    </div>
  
</div>    
<!--content E -->
<script type="text/javascript">
function initSelect(){
	var XxID = document.getElementById("XxID");
	var jobtilte = document.getElementById("jobtilte");
	var Education = document.getElementById("Education");

	var oldXxID = "";
	<%if(teacher.getSchool() != null){%>
		oldXxID = <%=teacher.getSchool().getXx_ID()%>
	<%}%>
	
	var oldJobtilte = "";
	<%if(teacher.getJobtilte() != null){%>
		oldJobtilte = <%=teacher.getJobtilte()%>
	<%}%>
	
	var oldEducation = "";
	<%if(teacher.getEducation() != null){%>
		oldEducation = <%=teacher.getEducation()%>
	<%}%>
	
	
    for(var i=0;i<XxID.options.length;i++)
    {
        if(XxID.options[i].value == oldXxID)
        {
        	XxID.options[i].selected = true;
            break;
        }
    }
    
    for(var i=0;i<jobtilte.options.length;i++)
    {
        if(jobtilte.options[i].value == oldJobtilte)
        {
        	jobtilte.options[i].selected = true;
            break;
        }
    }    
    
    for(var i=0;i<Education.options.length;i++)
    {
        if(Education.options[i].value == oldEducation)
        {
        	Education.options[i].selected = true;
            break;
        }
    }    
    
}

initSelect();
</script>

<%@ include file="../inc/templateFoot.jsp"%>