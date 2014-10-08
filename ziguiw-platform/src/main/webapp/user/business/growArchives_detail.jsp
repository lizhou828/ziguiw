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
       
        <!--表单样式2-->
            <fieldset class="formStyle2 clearfix" style="">
                <legend>${growArchives.student.user.nickName}的成长档案</legend>      
                <ul>
                    <li>
                		<div class="label">我的爱好：</div><div class="field ">${growArchives.hobby}</div>
                	</li>
                	<li>
                		<div class="label">我的理想：</div><div class="field ">${growArchives.ideal}</div>
                	</li>
                	<li>
                		<div class="label">我的特长：</div><div class="field ">${growArchives.specialty}</div>
                	</li>
                	<li>
                		<div class="label">我的名言：</div><div class="field ">${growArchives.famous}</div>
                	</li>
                	
            		<li>
                		<div class="label">父母寄语：</div><div class="field ">${growArchives.parentsSendWord}
                		</div>
                	</li>
                	<li>
                		<div class="label">老师寄语：</div><div class="field ">
                		${growArchives.teacherSendWord}
                		</div>
                	</li>
                	<li>
                		<div class="label">荣誉和作品：</div><div class="field ">
                		${growArchives.honorAndWorks}
                		</div>
                	</li>
                	<li>
                		<div class="label">我的得意之作：</div><div class="field ">
                		${growArchives.favouriteWork}
                		</div>
                	</li>
                	
            		<li>
                		<div class="label">阅读能力：</div>
                		<div class="field ">
                		 老师评价：${growArchives.readingAbilityTeacher}	分
						  自我评价：${growArchives.readingAbilitySelf}分
						  父母评价：${growArchives.readingAbilityParents}分
                		</div>
                	</li>
                		<li>
                		<div class="label">表达能力：</div>
                		<div class="field ">
                		 老师评价：${growArchives.expressionAbilityTeacher}	分
						  自我评价：${growArchives.expressionAbilitySelf}分
						  父母评价：${growArchives.expressionAbilityParents}分
            
                		</div>
                	</li>
                		<li>
                		<div class="label">书写能力：</div>
                		<div class="field ">
                		老师评价：${growArchives.writingAbilityTeacher}	分
						  自我评价：${growArchives.writingAbilitySelf}分
						  父母评价：${growArchives.writingAbilityParents}分
                		</div>
                	</li>
                		<li>
                		<div class="label">团队合作能力：</div>
                		<div class="field ">
                		老师评价：${growArchives.cooperationAbilityTeacher}	分
						  自我评价：${growArchives.cooperationAbilitySelf}分
						  父母评价：${growArchives.cooperationAbilityParents}分
                		 
                		</div>
                	</li>
                		<li>
                		<div class="label">艺术能力：</div>
                		<div class="field ">
                		老师评价：${growArchives.artAbilityTeacher}	分
						  自我评价：${growArchives.artAbilitySelf}分
						  父母评价：${growArchives.artAbilityParents}分
                		
                		</div>
                	</li>
                		<li>
                		<div class="label">体育能力：</div>
                		<div class="field ">
                		老师评价：${growArchives.sportAbilityTeacher}	分
						  自我评价：${growArchives.sportAbilitySelf}分
						  父母评价：${growArchives.sportAbilityParents}分
            
                		</div>
                	</li>
                		<li>
                		<div class="label">日常行为规范：</div>
                		<div class="field ">
                		老师评价：${growArchives.dailyAbilityTeacher}	分
						  自我评价：${growArchives.dailyAbilitySelf}分
						  父母评价：${growArchives.dailyAbilityParents}分
           
                		</div>
                	</li>
            	</ul>
            	<input type="hidden" name="growArchives.state" value="1"/>
                <s:if test='growArchives!=null && growArchives.id!=0'><input type="hidden" name='growArchives.id' value="${growArchives.id}"/></s:if>
               
                
            </fieldset>
        <!--表单样式2-->
            
    </div>
  
</div>    
<!--content E -->
<!--content E -->
<%@ include file="../../inc/templateFoot.jsp"%>