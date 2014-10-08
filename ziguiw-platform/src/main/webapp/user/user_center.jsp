<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path1 = request.getContextPath();
	UserBase user1 = (UserBase) session.getValue("VALID_USER");

	System.out.println("user4:" + user1);

	if (user1 == null || user1.getId() < 1) {
		response.sendRedirect(path1 + "/user/login.jsp");
		return;
	}
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/info_head.jsp"%>

<div class="content clearfix pr">
	<%@ include file="inc/info_left.jsp"%>

	<div class="banner-ad">
		<img src="<%=path1%>/images/banner-ls.gif" />
	</div>
	<!--ad01 E -->
	<div style=" float:right; width:260px">
            	<div style="padding:5px; background:#faf4ea; border:1px solid #fd6e1f; margin-top:10px;">
                	<div class="title" style="font-weight:bold; color:#fd6e1f">温馨提示：</div>
                    <div class="c">
                    	<%=ConfigUtils.getStringByKey("user_center_notice", "网站真懒，啥也没说")%>
                    </div>
                </div>
	<UL class=newsBtn01 style="margin-top:10px;">
  	<LI class=cur style="width:94px"><A href="javascript:void(0);return false;">学校最新公告</A> </LI></UL>
	<UL class=newList01>
	  <s:action var="diaryList" name="schoolDiary_getByNickName" namespace="/user" executeResult="false" ignoreContextParams="false">
			<s:param name="status" value="1"></s:param>
			<s:param name="pageSize" value="10"></s:param>
			<s:param name="type" value="6"></s:param>
			<s:param name="hostUserName"><%=hostUser.getSchool().getUser().getNickName()%></s:param>
		</s:action>
		
		<s:iterator value="#diaryList.pagedDiaries.list" var="diaries">
		
		<A href="<%=path%>/user/getShoolDiaryById?diary.id=<s:property value="#diaries.id"/>&hostUserName=<%=hostUser.getSchool().getUser().getNickName()%>"><s:property value="#diaries.title"/> </A>
		
		</s:iterator>
		
  </UL>
                     
            </div>
            
            <!-- 教师用户的展示 -->
		<%
			if (user.getType() == 2) {
		%>
            <div style="width:490px; margin-left:210px; ">
            <fieldset class="formStyle2 clearfix" style="border-bottom:0px; margin-bottom:10px; padding:5px; padding-left:25px;">
                <legend>基本信息</legend>
                <div>
            		<div>

					姓名：<strong><%=user.getRealName()%></strong>

                	</div>
            		<div>
                		所在学校：<strong><%=user.getTeacher().getSchool().getSch_name()%></strong>
                	</div>
            		<div>
                		管理的班级：<strong><s:action var="getClasz"
						name="baseData_getClaszByTeacher" namespace="/info"
						executeResult="false" ignoreContextParams="false">
						<s:param name="countPerPage" value="20"></s:param>
						<s:param name="query.state" value="1"></s:param>
						<s:param name="user.foreignKey"><%=hostUser.getForeignKey()%></s:param>
					</s:action> <s:iterator value="#getClasz.pageObj.list" var="clasz">
						<s:property value="#clasz.clasz.Bj_mcheng" />&nbsp;
					</s:iterator></strong>
                	</div>
            		      		
            	</div>
               
            </fieldset>

            <fieldset class="formStyle2 clearfix" style="border-bottom:0px; margin-bottom:10px;">
                <legend style=" margin-left:20px;">最新站内信</legend>
                <ul>
                	<s:action var="getLastestMessage" name="commonQuery" namespace="/"
					executeResult="false" ignoreContextParams="false">
					<s:param name="hql">from CommonMessage where toUser.id = <%=hostUser.getId()%> and kind = 3  and state = 1 order by lastModifyTime desc</s:param>
					<s:param name="queryString">1=1</s:param>
					<s:param name="countPerPage">5</s:param>
				</s:action>
				<s:iterator value="#getLastestMessage.obj.list" var="message">
            		<li>

						<s:property value="#message.text" /><span class="r">来自（<s:property value="#message.formUser.realName" />）</span>

                	</li>
            	</s:iterator>        		
            	</ul>
               
            </fieldset>
        
            </div> 
            <%
			}
		%>
		
		
		
		 <!-- 家长用户的展示 -->
		<%
			if (user.getType() == 3) {
		%>
            <div style="width:490px; margin-left:210px; ">
            <fieldset class="formStyle2 clearfix" style="border-bottom:0px; margin-bottom:10px; padding:5px; padding-left:25px;">
                <legend>基本信息</legend>
                <div>
            		<div>

					姓名：<strong><%=user.getRealName()%></strong>

                	</div>
            		<div>孩子姓名：<strong><%=user.getParent().getStudent().getXs_xming()%></strong>
					</div>
					<div>孩子学校： <strong><%=user.getParent().getStudent().getSchool()
							.getSch_name()%></strong>
					</div>
					<div>孩子班级： <strong><%=user.getParent().getStudent().getClasz()
							.getBj_mcheng()%></strong>
					</div>
            		      		
            	</div>
               
            </fieldset>

            <fieldset class="formStyle2 clearfix" style="border-bottom:0px; margin-bottom:10px;">
                <legend style=" margin-left:20px;">最新作业</legend>
                <ul>
                	<s:action var="homeWorks" name="homeWork_listByCondition"
					namespace="/info" executeResult="false" ignoreContextParams="false">
					<s:param name="countPerPage" value="5"></s:param>
					<s:param name="query.state" value="1"></s:param>
				</s:action>
				<s:iterator value="#homeWorks.homeWorks.list" var="homeWork">
            		<li>

						<s:property value="#homeWork.content" />

                	</li>
            	</s:iterator>        		
            	</ul>
               
            </fieldset>
        
            </div> 
            <%
			}
		%>
		
		
		 <!-- 学生用户的展示 -->
		<%
			if (user.getType() == 4) {
		%>
            <div style="width:490px; margin-left:210px; ">
            <fieldset class="formStyle2 clearfix" style="border-bottom:0px; margin-bottom:10px; padding:5px; padding-left:25px;">
                <legend>基本信息</legend>
                <div>
            		<div>

					姓名：<strong><%=user.getRealName()%></strong>

                	</div>
            		
					<div>学校： <strong><%=user.getStudent().getSchool()
							.getSch_name()%></strong>
					</div>
					<div>班级： <strong><%=user.getStudent().getClasz()
							.getBj_mcheng()%></strong>
					</div>
            		      		
            	</div>
               
            </fieldset>

            <fieldset class="formStyle2 clearfix" style="border-bottom:0px; margin-bottom:10px;">
                <legend style=" margin-left:20px;">最新作业</legend>
                <ul>
                	<s:action var="homeWorks" name="homeWork_listByCondition"
					namespace="/info" executeResult="false" ignoreContextParams="false">
					<s:param name="countPerPage" value="5"></s:param>
					<s:param name="query.state" value="1"></s:param>
				</s:action>
				<s:iterator value="#homeWorks.homeWorks.list" var="homeWork">
            		<li>

						<s:property value="#homeWork.content" />

                	</li>
            	</s:iterator>        		
            	</ul>
               
            </fieldset>
        
            </div> 
            <%
			}
		%>
                  
    </div>
  
	

<%@ include file="../inc/templateFoot.jsp"%>