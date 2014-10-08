<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/info_head.jsp"%>
<link href="<%=path%>/css/info.css" rel="stylesheet"/>

<div class="content clearfix pr">


<!--左侧菜单-->
<%@ include file="../inc/info_left.jsp"%>
<!--左侧菜单-->

<div class="w-760 r userCenterContent" >
       	<h2 class="title">用户中心</h2><div class="title-bottom">.</div>
		
        <!--列表样式3-->
       
       
        <!--表单样式2-->
        	<form method="post" action="<%=path%>/info/growArchives_saveOrUpdate.action">
            <fieldset class="formStyle2 clearfix" style="">
                <legend>添加/编辑成长档案</legend>      
                <ul>
                    <li>
                		<div class="label">我的爱好：</div><div class="field "><input class="field-400" type="text" value="${growArchives.hobby}" name="growArchives.hobby"/></div>
                	</li>
                	<li>
                		<div class="label">我的理想：</div><div class="field "><input class="field-400" type="text" value="${growArchives.ideal}" name="growArchives.ideal"/></div>
                	</li>
                	<li>
                		<div class="label">我的特长：</div><div class="field "><input class="field-400" type="text" value="${growArchives.specialty}" name="growArchives.specialty"/></div>
                	</li>
                	<li>
                		<div class="label">我的名言：</div><div class="field "><input class="field-400" type="text" value="${growArchives.famous}" name="growArchives.famous"/></div>
                	</li>
                	
            		<li>
                		<div class="label">父母寄语：</div><div class="field ">
                		<textarea class="field-400" rows="4" name="growArchives.parentsSendWord" type="text">${growArchives.parentsSendWord}</textarea>
                		</div>
                	</li>
                	<li>
                		<div class="label">老师寄语：</div><div class="field ">
                		<textarea class="field-400" rows="4" name="growArchives.teacherSendWord" type="text">${growArchives.teacherSendWord}</textarea>
                		</div>
                	</li>
                	<li>
                		<div class="label">荣誉和作品：</div><div class="field ">
                		<textarea class="field-400" rows="4" name="growArchives.honorAndWorks" type="text">${growArchives.honorAndWorks}</textarea>
                		</div>
                	</li>
                	<li>
                		<div class="label">我的得意之作：</div><div class="field ">
                		<textarea class="field-400" rows="4" name="growArchives.favouriteWork" type="text">${growArchives.favouriteWork}</textarea>
                		</div>
                	</li>
                	
            		<li>
                		<div class="label">阅读能力：</div>
                		<div class="field ">
                		 老师评价：<select name='growArchives.readingAbilityTeacher' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.readingAbilityTeacher==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.readingAbilityTeacher==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.readingAbilityTeacher==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.readingAbilityTeacher==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.readingAbilityTeacher==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  自我评价：<select name='growArchives.readingAbilitySelf' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.readingAbilitySelf==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.readingAbilitySelf==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.readingAbilitySelf==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.readingAbilitySelf==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.readingAbilitySelf==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  父母评价：<select name='growArchives.readingAbilityParents' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.readingAbilityParents==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.readingAbilityParents==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.readingAbilityParents==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.readingAbilityParents==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.readingAbilityParents==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>
                		</div>
                	</li>
                		<li>
                		<div class="label">表达能力：</div>
                		<div class="field ">
                		 老师评价：<select name='growArchives.expressionAbilityTeacher' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.expressionAbilityTeacher==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.expressionAbilityTeacher==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.expressionAbilityTeacher==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.expressionAbilityTeacher==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.expressionAbilityTeacher==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  自我评价：<select name='growArchives.expressionAbilitySelf' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.expressionAbilitySelf==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.expressionAbilitySelf==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.expressionAbilitySelf==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.expressionAbilitySelf==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.expressionAbilitySelf==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  父母评价：<select name='growArchives.expressionAbilityParents' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.expressionAbilityParents==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.expressionAbilityParents==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.expressionAbilityParents==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.expressionAbilityParents==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.expressionAbilityParents==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>
                		</div>
                	</li>
                		<li>
                		<div class="label">书写能力：</div>
                		<div class="field ">
                		 老师评价：<select name='growArchives.writingAbilityTeacher' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.writingAbilityTeacher==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.writingAbilityTeacher==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.writingAbilityTeacher==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.writingAbilityTeacher==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.writingAbilityTeacher==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  自我评价：<select name='growArchives.writingAbilitySelf' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.writingAbilitySelf==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.writingAbilitySelf==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.writingAbilitySelf==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.writingAbilitySelf==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.writingAbilitySelf==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  父母评价：<select name='growArchives.writingAbilityParents' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.writingAbilityParents==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.writingAbilityParents==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.writingAbilityParents==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.writingAbilityParents==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.writingAbilityParents==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>
                		</div>
                	</li>
                		<li>
                		<div class="label">团队合作能力：</div>
                		<div class="field ">
                		 老师评价：<select name='growArchives.cooperationAbilityTeacher' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.cooperationAbilityTeacher==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.cooperationAbilityTeacher==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.cooperationAbilityTeacher==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.cooperationAbilityTeacher==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.cooperationAbilityTeacher==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  自我评价：<select name='growArchives.cooperationAbilitySelf' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.cooperationAbilitySelf==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.cooperationAbilitySelf==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.cooperationAbilitySelf==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.cooperationAbilitySelf==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.cooperationAbilitySelf==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  父母评价：<select name='growArchives.cooperationAbilityParents' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.cooperationAbilityParents==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.cooperationAbilityParents==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.cooperationAbilityParents==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.cooperationAbilityParents==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.cooperationAbilityParents==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>
                		</div>
                	</li>
                		<li>
                		<div class="label">艺术能力：</div>
                		<div class="field ">
                		 老师评价：<select name='growArchives.artAbilityTeacher' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.artAbilityTeacher==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.artAbilityTeacher==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.artAbilityTeacher==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.artAbilityTeacher==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.artAbilityTeacher==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  自我评价：<select name='growArchives.artAbilitySelf' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.artAbilitySelf==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.artAbilitySelf==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.artAbilitySelf==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.artAbilitySelf==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.artAbilitySelf==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  父母评价：<select name='growArchives.artAbilityParents' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.artAbilityParents==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.artAbilityParents==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.artAbilityParents==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.artAbilityParents==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.artAbilityParents==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>
                		</div>
                	</li>
                		<li>
                		<div class="label">体育能力：</div>
                		<div class="field ">
                		 老师评价：<select name='growArchives.sportAbilityTeacher' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.sportAbilityTeacher==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.sportAbilityTeacher==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.sportAbilityTeacher==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.sportAbilityTeacher==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.sportAbilityTeacher==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  自我评价：<select name='growArchives.sportAbilitySelf' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.sportAbilitySelf==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.sportAbilitySelf==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.sportAbilitySelf==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.sportAbilitySelf==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.sportAbilitySelf==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  父母评价：<select name='growArchives.sportAbilityParents' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.sportAbilityParents==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.sportAbilityParents==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.sportAbilityParents==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.sportAbilityParents==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.sportAbilityParents==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>
                		</div>
                	</li>
                		<li>
                		<div class="label">日常行为规范能力：</div>
                		<div class="field ">
                		 老师评价：<select name='growArchives.dailyAbilityTeacher' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.dailyAbilityTeacher==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.dailyAbilityTeacher==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.dailyAbilityTeacher==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.dailyAbilityTeacher==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.dailyAbilityTeacher==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  自我评价：<select name='growArchives.dailyAbilitySelf' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.dailyAbilitySelf==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.dailyAbilitySelf==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.dailyAbilitySelf==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.dailyAbilitySelf==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.dailyAbilitySelf==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>						 
						  父母评价：<select name='growArchives.dailyAbilityParents' class="field-100">
						    <option selected="selected" value="0">请选择得分</option>
						    <option <s:if test="growArchives.dailyAbilityParents==1">selected="selected"</s:if> value="1">1分</option>
						    <option <s:if test="growArchives.dailyAbilityParents==2">selected="selected"</s:if> value="2">2分</option>
						    <option <s:if test="growArchives.dailyAbilityParents==3">selected="selected"</s:if> value="3">3分</option>
						    <option <s:if test="growArchives.dailyAbilityParents==4">selected="selected"</s:if> value="4">4分</option>
						    <option <s:if test="growArchives.dailyAbilityParents==5">selected="selected"</s:if> value="5">5分</option>				    
						 </select>
                		</div>
                	</li>
            	</ul>
            	<input type="hidden" name="growArchives.state" value="1"/>
                <s:if test='growArchives!=null && growArchives.id!=0'><input type="hidden" name='growArchives.id' value="${growArchives.id}"/></s:if>
               
                
            </fieldset>
            <div class="formBtnBar formBtnBar-noBorder"><input class="actionBtn5 inp_btn" name="reset" id="reset" type="reset" value="取 消" /> <input name="Submit1" id="Submit1" type="submit" value="提 交"  class="actionBtn7"/></div>
            </form>
        <!--表单样式2-->
            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>