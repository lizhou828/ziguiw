<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>
<%
CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
Student student = null;
if(user.getType() == 4 && user.getForeignKey() != null  && user.getForeignKey() > 0){
	student = commonService.get(Student.class, (int)user.getForeignKey().longValue());
}

if(student == null){
	student = new Student();
}
%>

<div class="content clearfix   pr">
<%@ include file="inc/right.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400  r">
       	<h2 class="col-h3">完善学生信息</h2>

		<form method="post" action="<%=path%>/user/fillment_student.action">
		<ul class="link_gray ul-li-s">
			<li><span>真实姓名</span><input name="student.Xs_xming" id="Xs_xming" type="text" class="inp_txt" value="<%=student.getXs_xming() == null ? "" : student.getXs_xming()%>"/></li>
			<li><span>学号:</span><input name="student.Xj_bhao" id="Xj_bhao" type="text" class="inp_txt" value="<%=student.getXj_bhao() == null ? "" : student.getXj_bhao()%>"/></li>
			<li><span>籍贯:</span><input name="student.Birthplace" id="Birthplace" type="text" class="inp_txt" value="<%=student.getBirthplace() == null ? "" : student.getBirthplace()%>"/></li>
			<li><span>政治面貌:</span><input name="student.PoliticalFace" id="PoliticalFace" type="text" class="inp_txt" value="<%=student.getPoliticalFace() == null ? "" : student.getPoliticalFace()%>"/></li>		
			<li><span>学生卡号:</span><input name="student.IDCard" id="IDCard" type="text" class="inp_txt" value="<%=student.getIDCard() == null ? "" : student.getIDCard()%>"/></li>		
			<li><span>户口所在地:</span><input name="student.AccountPlace" id="AccountPlace" type="text" class="inp_txt" value="<%=student.getAccountPlace() == null ? "" : student.getAccountPlace()%>"/></li>	
			
			<li><span>民族:</span><input name="student.mzhu" id="mzhu" type="text" class="inp_txt" value="<%=student.getMzhu() == null ? "" : student.getMzhu()%>"/></li>		
			<li><span>特长爱好:</span><input name="student.Hobby" id="Hobby" type="text" class="inp_txt" value="<%=student.getHobby() == null ? "" : student.getHobby()%>"/></li>		
			<li><span>家庭电话:</span><input name="student.homephone" id="homephone" type="text" class="inp_txt" value="<%=student.getHomephone() == null ? "" : student.getHomephone()%>"/></li>		
			<li><span>家庭邮编:</span><input name="student.zip" id="zip" type="text" class="inp_txt" value="<%=student.getZip() == null ? "" : student.getZip()%>"/></li>
			<li><span>家庭地址:</span><input name="student.homeaddress" id="homeaddress" type="text" class="inp_txt" value="<%=student.getHomeaddress() == null ? "" : student.getHomeaddress()%>"/></li>			
			<li><span>其他联系:</span><input name="student.otherlinks" id="otherlinks" type="text" class="inp_txt" value="<%=student.getOtherlinks() == null ? "" : student.getOtherlinks()%>"/></li>			
			<li><span>学生健康状况:</span><input name="student.Healthstate" id="Healthstate" type="text" class="inp_txt" value="<%=student.getHealthstate() == null ? "" : student.getHealthstate()%>"/></li>			
			<li><span>学校:</span><select name="student.school.xx_ID" id="XxID" class="inp_txt" style="height:30px">
				<s:action var="getVipSchools" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
			     	<s:param name="hql">select sc from UserBase as ub, School as sc  where ub.type = 1 and ub.foreignKey = sc.xx_ID and ub.state >= 2</s:param>
			     	<s:param name="queryString">1=1</s:param>
				</s:action>
				<s:iterator value="#getVipSchools.obj.list" var="school">
					<option value="<s:property value="#school.xx_ID"/>"><s:property value="#school.sch_name"/></option>
				</s:iterator>
				</select>
			</li>
			<li><span>班级编号:</span><select name="student.Bj_ID" id="Bj_ID" type="text" class="inp_txt" value="<%=student.getBj_ID() == null ? "" : student.getBj_ID()%>">
				
			</select>
			</li>		
			<input type="hidden" name="student.Xs_id" value="<%=student.getXs_id() == 0 ? "" : student.getXs_id()%>"/>						
			<li><input name="Submit1" type="submit" value="确认修改" class="btn2"/></li>	
				
		</ul>
		</form>
    </div>
  
</div>    
<!--content E -->
<script type="text/javascript">
function initSelect(){
	var XxID = document.getElementById("XxID");

	var oldXxID = "";
	<%if(student.getSchool() != null){%>
		oldXxID = <%=student.getSchool().getXx_ID()%>
	<%}%>
	
	
    for(var i=0;i<XxID.options.length;i++)
    {
        if(XxID.options[i].value == oldXxID)
        {
        	XxID.options[i].selected = true;
            break;
        }
    }
    
    $.ajax({
	      url:"<%=path%>/baseData_ajaxGetClaszBySchoolId.action",
	      type:"post",
	      data:{'Xx_id':oldXxID},
	      dataType : 'text',
	      success:function(msg){
	          if(msg != 'error'){
	          	$("#Bj_ID option").remove();//移除右边列表
				$("#Bj_ID").html(msg);
	          	
				var Bj_ID = document.getElementById("Bj_ID");

				var oldBj_ID = <%=student.getBj_ID()%>;
				
				
			    for(var i=0;i<Bj_ID.options.length;i++)
			    {
			        if(Bj_ID.options[i].value == oldBj_ID)
			        {
			        	Bj_ID.options[i].selected = true;
			            break;
			        }
			    }
				
	          }else{
	          	alert("出错！请与管理员联系！");
	          	return false;
	          }
	      }
	    });
    
    
}

initSelect();

$('#XxID').change(function(){
	$.ajax({
	      url:"<%=path%>/baseData_ajaxGetClaszBySchoolId.action",
	      type:"post",
	      data:{'Xx_id':$(this).val()},
	      dataType : 'text',
	      success:function(msg){
	          if(msg != 'error'){
	          	$("#Bj_ID option").remove();//移除右边列表
				$("#Bj_ID").html(msg);
				
	          }else{
	          	alert("出错！请与管理员联系！");
	          	return false;
	          }
	      }
	    });
});
</script>

<%@ include file="../inc/templateFoot.jsp"%>