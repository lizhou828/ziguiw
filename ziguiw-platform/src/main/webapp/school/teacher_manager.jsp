<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>

<div class="content clearfix pr">
  <%@ include file="inc/school_left.jsp"%>
  <!--ad01 E -->
<div class="ask_and_sea found w-760 hei-700 r" style="min-height:550px">
       	<h2 class="col-h3">用户中心</h2>

	<table class="listStyle1" cellpadding="2" cellspacing="2" border="0" >
	
		<thead>
			<tr>
               	<td class="field1 datetime" width="30%">教师ID</td>
               	<td class="light" width="20%">教师姓名</td>
               	<td class="light" width="20%">管理</td>
               </tr>
		</thead>
		
		<s:action var="getTeacher" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
	     	<s:param name="hql">from Teacher where school.XxID = <%=school.getXxID()%></s:param>
	     	<s:param name="queryString">1=1</s:param>
		</s:action>
		
	    <s:iterator value="#getTeacher.obj.list" var="data">
           	<tr>
               	<td class="field1 datetime"><s:property value="#data.teacherID"/></td>
               	<td class="light"><s:property value="#data.name"/></td>
               	<td class="light"><a href="javascript:selectClasz(<s:property value="#data.teacherID"/>, <%=school.getXxID()%>)">修改班级</a></td>
               </tr>
           	
               </s:iterator>
         </table>
           
           <s:if test="#getTeacher.obj.pageString != null && #getTeacher.obj.pageString != ''">
			<div class="pagenum">
				<s:property value="#getTeacher.obj.pageString" escape="false"/>
			</div>
			</s:if>
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

<link href="http://static01.babytreeimg.com/img/css/boxy2.css?ver=20110707" rel="stylesheet" type="text/css" />
<script src="http://static01.babytreeimg.com/img/js/boxy2.js?ver=1315188206" type="text/javascript"></script>

<!--创建相册-->
<div id="create_clasz_dialog" style="display:none;">
<div class="alert alert-620">
	<form action="<%=path%>/user/school_saveOrUpdateTeacherClasz.action" method="post" id="form_create_album" name="form_create_album" >
  <div class="body">
    <div class="cont">
      <ul class="padding-25">
        <li>
                	<div class="label">选择班级：</div>
                		<table width="100%" border="0" cellpadding="0" cellspacing="1" style="display:inline">
				     <tr>
				      <td width="110">
		                <select id="stuId"  size="10" multiple="multiple" style="width:100px" >
	                		<s:action var="getTeacher" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
						     	<s:param name="hql">from Teacher where school.XxID = <%=school.getXxID()%></s:param>
						     	<s:param name="queryString">1=1</s:param>
							</s:action>
	                	</select>
				     </td>
				     <td width="50">
						<input type="button" value="   >>   " onclick="move(this.form.stuId,this.form.stuIdList)" name="B1"/><br/>
					 	<input type="button" value="   <<   " onclick="move(this.form.stuIdList,this.form.stuId)" name="B2"/>
				     </td>
				     <td>
		                <select name="sidArray" id="stuIdList" size="10" multiple="multiple" style="width:100px" >
		                
		                	<s:action var="getTeacherClasz" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
						     	<s:param name="hql">from Teacher where school.XxID = <%=school.getXxID()%></s:param>
						     	<s:param name="queryString">1=1</s:param>
							</s:action>
		                </select>
				     </td>
				    </tr>   
					</table>
			</li>
        
        <input type="hidden" name="teacherId" id="teacherId"></input>
       
        <li class="nopadding btn-box">
          <input type="submit" value="确定" class="btn2"/>
          <img src="/img/common/loading_pic_s.gif" style="display:none" id="form_create_album_loading"/>
        </li>
      </ul>
    </div>
  </div>
  </form>
  </div>
  </div>

<script>
jQuery(document).ready(function(){
	 boxy2_init(true);    //里面的参数表示是否带标题栏
	});
function createClasz(){
	boxy2.pop(jQuery('#create_clasz_dialog').html(),420,260); 
	boxy2.title('修改班级');
}

function modifyAlbum(albumId){
	$("#modifyAlbumId").val(albumId);
	
	boxy2.pop(jQuery('#create_clasz_dialog').html(),420,260); 
	boxy2.title('修改班级');
}

function selectClasz(teacherId, schoolId){
	$.ajax({
      url:"<%=path%>/baseData_ajaxGetNoClaszByTeacher.action",
      type:"post",
      data:{'teacherId':teacherId, 'schoolId':schoolId},
      dataType : 'text',
      success:function(msg){
          if(msg != 'error'){
          	$("#stuId option").remove();//移除左边列表
			$("#stuId").html(msg);
			
          }else{
          	alert("出错！请与管理员联系！");
          	return false;
          }
      }
    });
	
	$.ajax({
	      url:"<%=path%>/baseData_ajaxGetClaszByTeacher.action",
	      type:"post",
	      data:{'teacherId':teacherId, 'schoolId':schoolId},
	      dataType : 'text',
	      success:function(msg){
	          if(msg != 'error'){
	          	$("#stuIdList option").remove();//移除右边列表
				$("#stuIdList").html(msg);
				
	          }else{
	          	alert("出错！请与管理员联系！");
	          	return false;
	          }
	      }
	    });
	
	$('#teacherId').val(teacherId);
	createClasz();
	
}

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

<%@ include file="../inc/templateFoot.jsp"%>