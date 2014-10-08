<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>

<div class="content clearfix pr">
  <%@ include file="inc/school_left.jsp"%>
  <!--ad01 E -->
<div class="ask_and_sea found w-760 hei-700 r" style="min-height:550px">
       	<h2 class="col-h3">用户中心</h2>
       	<input name="button" type="button" value="增加班级" class="btn2" style="float:right" onclick="createClasz()"/>

	<table class="listStyle1" cellpadding="2" cellspacing="2" border="0" >
	
		<thead>
			<tr>
               	<td class="field1 datetime" width="30%">班级ID</td>
               	<td class="light" width="20%">班级名称</td>
               </tr>
		</thead>
		
		<s:action var="getClaszs" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
	     	<s:param name="hql">from Clasz where XxID = <%=school.getXxID()%></s:param>
	     	<s:param name="queryString">1=1</s:param>
		</s:action>
		
	    <s:iterator value="#getClaszs.obj.list" var="data">
           	<tr>
               	<td class="field1 datetime"><s:property value="#data.Bj_id"/></td>
               	<td class="light"><s:property value="#data.Bj_mcheng"/></td>
               </tr>
           	
               </s:iterator>
         </table>
           
           <s:if test="#getClaszs.obj.pageString != null && #getClaszs.obj.pageString != ''">
			<div class="pagenum">
				<s:property value="#getClaszs.obj.pageString" escape="false"/>
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
	<form action="<%=path%>/user/school_saveOrUpdateClasz.action" method="post" id="form_create_album" name="form_create_album" >
  <div class="body">
    <div class="cont">
      <ul class="padding-25">
        <li class="margin-10">
          <label class="label font-14">班级名称：</label>
          <input type="text" class="txt input-265" name="clasz.Bj_mcheng" id="clasz.Bj_mcheng" validation="required" maxlength="20"/><span  style="display:none;color:red;">班级名称不能为空</span>
        </li>
        
        <input type="hidden" name="clasz.Bj_id" id="modifyClaszId"></input>
       
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
	boxy2.pop(jQuery('#create_clasz_dialog').html(),320,120); 
	boxy2.title('创建班级');
}

function modifyAlbum(albumId){
	$("#modifyAlbumId").val(albumId);
	
	boxy2.pop(jQuery('#create_clasz_dialog').html(),320,120); 
	boxy2.title('修改班级');
}
</script>

<%@ include file="../inc/templateFoot.jsp"%>