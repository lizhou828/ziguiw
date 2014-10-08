<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">新闻：新增频道</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/template/dataFunction_saveOrUpdate.action">
<table class="table">
	<colgroup>
		<col  style="width:820px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>数据源名称</span>
			<input name="dataFunction.name" type="text" class="inp_txt" value="${dataFunction.name}" />
		</td>
		
		<td class="v-b" ><span class="red_star">*</span>数据源名称全局唯一，不能重复</td>
	</tr>
	
	<tr>
		<td><span>模版描述</span>
			<input name="dataFunction.description" type="text" class="inp_txt" value="${dataFunction.description}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>模版的用途</td>
	</tr>
	
	<tr>
		<td><span>模版参数</span>
			<input name="dataFunction.parameter" type="text" class="inp_txt" value="${dataFunction.parameter}" id="parameter"/>
		</td>
		<td class="v-b" ><span class="red_star">*</span>模版参数，用&分隔，前面标识数据类型，例如Long型用*L_开头,模版里面将类型信息去掉</td>
	</tr>
	
	
	<tr>
		<td><span>模版内容</span>
			<textarea name="dataFunction.content" type="text" rows="5" cols="120" id="hql">${dataFunction.content}</textarea>
		</td>
	</tr>
	
	<tr>
		<td><span>测试模版参数</span>
			<input name="testParameter" type="text" class="inp_txt" id="testParameter" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>模版参数，用&分隔，前面标识数据类型，例如Long型用*L_开头,模版里面将类型信息去掉</td>
	</tr>
	<input type="hidden" name="dataFunction.id" value=${dataFunction.id}>

</table>

<p class="submit">
<input name="testFunction" id="testFunction" type="button" value="测试函数"  class="inp_btn"/>
<input name="Submit1" id="Submit1" type="submit" value="提交"  class="inp_btn"/>

</p>
</form>
</div>

<%@ include file="../inc/foot.jsp"%>