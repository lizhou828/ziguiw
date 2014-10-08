<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>
<link href="<%=path%>/css/info.css" rel="stylesheet"/>
<link href="<%=path%>/css/base.css" rel="stylesheet"/>
<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="../inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">用户中心</h2><div class="title-bottom">.</div>
		
        <!--列表样式3-->
       
       
        <!--表单样式2-->
        	<form method="post" action="<%=path%>/info/commonMessage_saveOrUpdate.action">
            <fieldset class="formStyle2 clearfix" style="">
                <legend>
                <s:if test="#parameters.kind[0]==5">发布学校公告</s:if>
                <s:elseif test='#parameters.kind[0]==4'>发布班级通知</s:elseif>
                <s:elseif test='#parameters.kind[0]==3'>发布老师留言</s:elseif>
                <s:elseif test='#parameters.kind[0]==6'>发布学校通知</s:elseif>
                <s:elseif test='#parameters.kind[0]==7'>发布学生评语</s:elseif>
                <s:elseif test='#parameters.kind[0]==8'>发布家长交流</s:elseif>              
                </legend>      
                <ul>
            		<li>
                		<div class="label">主题：</div><div class="field"><input class="field-200" type="text" value="${commonMessage.title}" name="commonMessage.title"/></div>
                	</li>
                	<%String type = request.getParameter("kind"); %>
                	<s:if test="#parameters.kind[0]==3||#parameters.kind[0]==4||#parameters.kind[0]==5">
                	
	                
                	</s:if>
                	
                	<s:if test="#parameters.kind[0]==3||#parameters.kind[0]==7||#parameters.kind[0]==4">
                	<li>
                	<div class="label">班级：</div><div class="field">
                		<s:action var="getClasz" name="baseData_getClaszByTeacher" namespace="/info" executeResult="false" ignoreContextParams="false">
					      <s:param name="countPerPage" value="20"></s:param>
					      <s:param name="query.state" value="1"></s:param>
					      <s:param name="user.foreignKey"><%=hostUser.getForeignKey()%></s:param>
				        </s:action>
				        <select name='commonMessage.classId' id="classSelector" class="field-200">
						 <option selected="selected" value="0">请选择班级</option>
						 <s:iterator value="#getClasz.pageObj.list" var="clasz">
							       <option value="<s:property value="#clasz.clasz.Bj_id"/>"><s:property value="#clasz.clasz.Bj_mcheng"/></option>
						</s:iterator>
							    
						</select>
						</li>
						
						<s:if test="#parameters.kind[0]==3||#parameters.kind[0]==7">
						<li>
                	<div class="label">选择学生：</div>
                		<table width="100%" border="0" cellpadding="0" cellspacing="1" style="display:inline">
				     <tr>
				      <td width="110">
		                <select id="stuId"  size="10" multiple="multiple" style="width:100px" >
	                	
	                </select>
				     </td>
				     <td width="50">
						<input type="button" value="   >>   " onclick="move(this.form.stuId,this.form.stuIdList)" name="B1"/><br/>
					 	<input type="button" value="   <<   " onclick="move(this.form.stuIdList,this.form.stuId)" name="B2"/>
				     </td>
				     <td>
		                <select name="sidArray" id="stuIdList" size="10" multiple="multiple" style="width:100px" >
		                </select>
				     </td>
				    </tr>   
					</table>
						</li>
						</s:if>
                	</s:if>
                	
                	<li>
                		<div class="label">内容：</div><div class="field ">
                		<textarea class="field-400" rows="4" name="commonMessage.text" type="text">${commonMessage.text}</textarea>
                		</div>
                	</li>
            	</ul>
            	<input type="hidden" name="commonMessage.state" value="1"/>
            	<input type="hidden" name="type" value="<%=type%>"/>
                <input type="hidden" name='commonMessage.kind' value="<s:property value="#parameters.kind"/>"/>

            </fieldset>
                         <div class="formBtnBar formBtnBar-noBorder"><input name="reset" id="reset" type="reset" value="取 消"  class="inp_btn actionBtn5"/> <input name="Submit1" id="Submit1" type="submit" value="提 交" class="actionBtn7" /></div>
            </form>
        <!--表单样式2-->
            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<script>
$("#classSelector").change(function(){
	var classId = $(this).val();
	if(classId == '-1'){
		$("#stuId option").remove();
	}else{
		$.ajax({
	      url:"<%=path%>/baseData_ajaxGetStudentByClasz.action",
	      type:"post",
	      data:{'clasz.clasz.Bj_id':classId},
	      dataType : 'text',
	      success:function(msg){
	          if(msg != 'error'){
	          	$("#stuId option").remove();//移除左边列表
	          	$("#stuIdList option").remove();//移除右边列表
				$("#stuId").html(msg);
				
	          }else{
	          	alert("查询学生出错！请与管理员联系！");
	          	return false;
	          }
	      }
	    });
	}
	
});

function move(fbox,tbox) {
	for(var i=0; i<fbox.options.length; i++) {
	if(fbox.options[i].selected && fbox.options[i].value != "") {
	var no = new Option();
	no.value = fbox.options[i].value;
	no.text = fbox.options[i].text;
	tbox.options[tbox.options.length] = no;
	fbox.options[i].value = "";
	fbox.options[i].text = "";
	   }
	}
	BumpUp(fbox);
	selectD(tbox);
	
}
function BumpUp(box)  {
	for(var i=0; i<box.options.length; i++) {
	if(box.options[i].value == "")  {
	for(var j=i; j<box.options.length-1; j++)  {
	box.options[j].value = box.options[j+1].value;
	box.options[j].text = box.options[j+1].text;
	}
	var ln = i;
	break;
	   }
	}
	if(ln < box.options.length)  {
	box.options.length -= 1;
	BumpUp(box);
	   }
}
	
function SortD(box)  {
	var temp_opts = new Array();
	var temp = new Object();
	for(var i=0; i<box.options.length; i++)  {
	temp_opts[i] = box.options[i];
	}
	for(var x=0; x<temp_opts.length-1; x++)  {
	for(var y=(x+1); y<temp_opts.length; y++)  {
	if(temp_opts[x].text > temp_opts[y].text)  {
	temp = temp_opts[x].text;
	temp_opts[x].text = temp_opts[y].text;
	temp_opts[y].text = temp;
	temp = temp_opts[x].value;
	temp_opts[x].value = temp_opts[y].value;
	temp_opts[y].value = temp;
	      }
	   }
	}
	for(var i=0; i<box.options.length; i++)  {
	box.options[i].value = temp_opts[i].value;
	box.options[i].text = temp_opts[i].text;
	box.options[i].selected = true;
	   }
} 

function selectD(box)  {
	for(var i=0; i<box.options.length; i++)  {
		box.options[i].selected = true;
	}
	
}


</script>
<%@ include file="../../inc/templateFoot.jsp"%>