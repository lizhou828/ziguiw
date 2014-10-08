<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>
<%
CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
Parent parent = null;
if(user.getType() == 3 && user.getForeignKey() != null  && user.getForeignKey() > 0){
	parent = commonService.get(Parent.class, (int)user.getForeignKey().longValue());
}

if(parent == null){
	parent = new Parent();
}
%>

<div class="content clearfix   pr">
<%@ include file="inc/right.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400  r">
       	<h2 class="col-h3">完善家长信息</h2>

		<form method="post" action="<%=path%>/user/fillment_parent.action">
		<ul class="link_gray ul-li-s">
			<li><span>姓名:</span><input name="parent.PARENTS_NAME" id="PARENTS_NAME" type="text" class="inp_txt" value="<%=parent.getPARENTS_NAME() == null ? "" : parent.getPARENTS_NAME()%>"/></li>
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
			<li><span>班级编号:</span><select name="student.Bj_ID" id="Bj_ID" type="text" class="inp_txt"">
				
			</select>
			</li>
			<li><span>学生:</span><select name="parent.student.Xs_id" id="Xs_id" type="text" class="inp_txt"">
				
			</select>
			</li>
			<li><span>办公室电话:</span><input name="parent.officePhone" id="officePhone" type="text" class="inp_txt" value="<%=parent.getOfficePhone() == null ? "" : parent.getOfficePhone()%>"/></li>
			<li><span>家长手机:</span><input name="parent.mobliephone" id="mobliephone" type="text" class="inp_txt" value="<%=parent.getMobliephone() == null ? "" : parent.getMobliephone()%>"/></li>		
			<li><span>家长称呼:</span><input name="parent.PARENTS_CALLER" id="PARENTS_CALLER" type="text" class="inp_txt" value="<%=parent.getPARENTS_CALLER() == null ? "" : parent.getPARENTS_CALLER()%>"/></li>	
			<li><span>职业:</span><input name="parent.Professional" id="Professional" type="text" class="inp_txt" value="<%=parent.getProfessional() == null ? "" : parent.getProfessional()%>"/></li>	
			<li><span>工作单位:</span><input name="parent.workaddress" id="workaddress" type="text" class="inp_txt" value="<%=parent.getWorkaddress() == null ? "" : parent.getWorkaddress()%>"/></li>	
			<li><span>备注:</span><input name="parent.remarks" id="remarks" type="text" class="inp_txt" value="<%=parent.getRemarks() == null ? "" : parent.getRemarks()%>"/></li>	
			<input type="hidden" name="parent.PARENTS_ID" value="<%=parent.getPARENTS_ID() == 0 ? "" : parent.getPARENTS_ID()%>"/>						
			<li><input name="Submit1" type="submit" value="确认修改" class="btn2"/></li>	
				
		</ul>
		</form>
    </div>
  
</div>    
<!--content E -->
<script type="text/javascript">
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

$('#Bj_ID').change(function(){
	$.ajax({
	      url:"<%=path%>/baseData_ajaxGetStudentByClasz1.action",
	      type:"post",
	      data:{'clasz.clasz.Bj_id':$(this).val()},
	      dataType : 'text',
	      success:function(msg){
	          if(msg != 'error'){
	          	$("#Xs_id option").remove();//移除右边列表
				$("#Xs_id").html(msg);
				
	          }else{
	          	alert("出错！请与管理员联系！");
	          	return false;
	          }
	      }
	    });
});

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