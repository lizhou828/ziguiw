<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/comm/common_tag.jsp" %>



<!-- 1. 学校用户
2. 家长
3. 老师
4. 学生,5.其他注册用户 -->
<s:if test="#session.loginMemberType == 1">
	<div class="side-left">
		<div class="tt">
			<div class="ru1">
				<img src="${ctx}/images/user/imgmo.gif" onerror="this.src='${ctx}/images/user/imgmo.gif'"/> 
				<p class="font-3">
					<b class="font-2">${loginMemberNikename }</b>
					<br />

					<a href="${ctx}/myspace/headChange.jsp">更换头像>></a>
				</p>
			</div>
			<div class="chaxun">
				<div class="title-1 font-1">
					快捷导航
				</div>
				<ul>
					<li id="school/basicinfo/school!viewSchoolinfo.action">
						<a href="${ctx}/school/basicinfo/school!viewSchoolinfo.action" class="bj23 font-2">基本资料</a>
					</li>
				</ul>
				<ul>
					<li id="school/teacher/teacherList.jsp">
						<a href="${ctx}/school/teacher/teacherList.jsp" class="bj24 font-2">师资查询</a>
					</li>
				</ul>
				<ul>
					<li id="school/teacher/school_tercherList.jsp">
						<a href="${ctx}/school/teacher/school_tercherList.jsp" class="bj25 font-2">任课老师查询</a>
					</li>
				</ul>
				<ul>
					<li id="school/systemsetup/stugradeType!openListStugradeType.action">
						<a
							href="${ctx}/school/systemsetup/stugradeType!openListStugradeType.action"
							class="bj29 font-2">部/年级/班级查询</a>
					</li>
				</ul>
				<!--  
				<ul>
					<li id="school/systemsetup/subject!openListSubject.action">
						<a href="school/systemsetup/subject!openListSubject.action"
							class="bj27 font-2">科目查询</a>
					</li>
				</ul>
				-->
				<ul>
					<li id="school/systemsetup/attendance!openListAttendance.action">
						<a href="${ctx}/school/systemsetup/attendance!openListAttendance.action"
							class="bj28 font-2">考勤设定查询</a>
					</li>
				</ul>
				<ul>
					<li id="school/systemsetup/msgtype!openListMsgtypeByLeave.action">
						<a href="${ctx}/school/systemsetup/msgtype!openListMsgtypeByLeave.action" class="bj30 font-2">老师留言类别</a>
					</li>
				</ul>
				<ul>
					<li id="school/systemsetup/msgtype!openListMsgtypeByInform.action">
						<a href="${ctx}/school/systemsetup/msgtype!openListMsgtypeByInform.action" class="bj31 font-2">班级通知类别</a>
					</li>
				</ul>
				<ul>
					<li id="school/systemsetup/msgtype!openListMsgtypeByNotice.action">
						<a href="${ctx}/school/systemsetup/msgtype!openListMsgtypeByNotice.action" class="bj32 font-2">学校公告类别</a>
					</li>
				</ul>
				<ul>
					<li id="user/schMsgList.action?typebelong=03">
						<a href="${ctx}/user/schMsgList.action?typebelong=03" class="bj26 font-2">学校公告</a>
					</li>
				</ul>
				<ul>
					<li id="user/schDsisMsgList.action?typebelong=-1">
						<a href="${ctx}/user/schDsisMsgList.action?typebelong=-1" class="bj13 font-2">学校通知</a>
					</li>
				</ul>

			
							<ul>
					<li id="source/addsourceGet!addget.action">
						<a href="${ctx}/source/addsourceGet!addget.action" class="bj35 font-2">上传资源</a>
					</li>
				</ul>
				<ul>
					<li id="source/sourceList.action">
						<a href="${ctx}/source/sourceList.action" class="bj35 font-2">我上传的资源</a>
					</li>
				</ul>	
				
				
			</div>
			<div class="chaxun">
				<div class="title-1 font-1">
					设置
				</div>
				<ul>
					<li id="school/basicinfo/school!openModifySchoolinfo.action">
						<a href="${ctx}/school/basicinfo/school!openModifySchoolinfo.action" class="bj34 font-2">修改基本信息</a>
					</li>
				</ul>
				<ul>
					<li id="school/member/teacher!openAddTeacherMember.action">
						<a href="${ctx}/school/member/teacher!openAddTeacherMember.action" class="bj24 font-2">添加老师用户</a>
					</li>
				</ul>
				<ul>
					<li id="myspace/updatepwd.jsp">
						<a href="${ctx}/myspace/updatepwd.jsp" class="bj22 font-2">修改密码</a>
					</li>
				</ul>
			</div>
		</div>
	</div>

</s:if>
<!-- 家长 -->
<s:elseif test="#session.loginMemberType == 2">
	<div class="side-left">
		<div class="tt">
			<div class="ru1">
				<img  <c:if test="${empty loginMemberHeadpath}">src='${ctx}/images/user/imgmo.gif'</c:if> <c:if test="${not empty loginMemberHeadpath}">src='${loginMemberHeadpath}'</c:if> />
				<p class="font-3">
					<b class="font-2">${loginMemberNikename }</b>
					<br />

					<a href="${ctx}/myspace/headChange.jsp">更换头像>></a>
				</p>
			</div>
			<div class="chaxun">
				<div class="title-1 font-1">
					快捷导航
				</div>
					<ul>
            <li id="user/attenQueryAction!parAttendancePager.action?isDefaultQuery=1"><a href="${ctx}/user/attenQueryAction!parAttendancePager.action?isDefaultQuery=1" class="bj8 font-2">考勤查询</a></li>
          </ul>
          <ul>
            <li id="user/smsMsgAction!parQuery.action?isDefaultQuery=1"><a href="${ctx}/user/smsMsgAction!parQuery.action?isDefaultQuery=1" class="bj9 font-2">作业查询</a></li>
          </ul>
          <ul>
            <li id="user/examQueryAction!parExamList.action"><a href="${ctx}/user/examQueryAction!parExamList.action" class="bj10 font-2">成绩查询</a></li>
          </ul>
		  <ul>
            		<li id="stuinfo/growfile!parentQuery.action">

		<s:if test="#session.loginMemberUsername == '13875900410'">
            <a href="${ctx}/student/growfile/grow_list_wdd.jsp" class="bj21 font-2">                        
            成长档案</a>
            </s:if>
            <s:elseif test="#session.loginMemberUsername == '13874939044'">
             <a href="${ctx}/student/growfile/grow_list_tr.jsp" class="bj21 font-2">           
            成长档案</a>
            </s:elseif>
            <s:elseif test="#session.loginMemberUsername == '18229932197'">
           <a href="${ctx}/student/growfile/grow_list_hzh.jsp" class="bj21 font-2">           
            成长档案</a>
            </s:elseif>
            <s:else >           
             <a href="${ctx}/stuinfo/growfile!parentQuery.action" class="bj21 font-2">
					成长档案
				</a>
            </s:else>
				
		</li>
          </ul>
          				    <ul>
            <li id="parent/parentinfo!getstudent.action"><a href="parent/parentinfo!getstudent.action" class="bj17 font-2">孩子学籍档案</a></li>
          </ul>
          <ul>
            <li id="stuinfo/footmark!parentQuery.action"><a href="${ctx}/stuinfo/footmark!parentQuery.action" class="bj12 font-2">成长足迹</a></li>
          </ul>
				<ul>
					<li id="user/parMsgList.action?typebelong=03">
						<a href="${ctx}/user/parMsgList.action?typebelong=03" class="bj26 font-2">学校公告</a>
					</li>
				</ul>
				<ul>
					<li id="user/parMsgList.action?typebelong=02">
						<a href="${ctx}/user/parMsgList.action?typebelong=02" class="bj18 font-2">班级通知</a>
					</li>
				</ul>
				<ul>
					<li id="user/parMsgList.action?typebelong=01">
						<a href="${ctx}/user/parMsgList.action?typebelong=01" class="bj11 font-2">老师留言</a>
					</li>
				</ul>
				<ul>
					<li id="user/parDsisMsgList.action?typebelong=-1">
						<a href="${ctx}/user/parDsisMsgList.action?typebelong=-1" class="bj13 font-2">学校通知</a>
					</li>
				</ul>
				<ul>
					<li id="user/parDsisFeedbackList.action?typebelong=-2">
						<a href="${ctx}/user/parDsisFeedbackList.action?typebelong=-2" class="bj13 font-2">家长反馈</a>
					</li>
				</ul>
				<ul>
					<li id="user/parDsisMsgInSchList.action?typebelong=-1&&msgtype=3">
						<a href="${ctx}/user/parDsisMsgInSchList.action?typebelong=-1&&msgtype=3" class="bjb font-2">学生评语</a>
					</li>
				</ul>
				<ul>
					<li id="user/parDsisMsgInSchList.action?typebelong=-1&&msgtype=5">
						<a href="${ctx}/user/parDsisMsgInSchList.action?typebelong=-1&&msgtype=5" class="bjc font-2">学习攻略</a>
					</li>
				</ul>
				<ul>
					<li id="user/parDsisMsgInSchList.action?typebelong=-1&&msgtype=6">
						<a href="${ctx}/user/parDsisMsgInSchList.action?typebelong=-1&&msgtype=6" class="bjd font-2">营养膳食</a>
					</li>
				</ul>
				<ul>
					<li id="user/parDsisMsgInSchList.action?typebelong=-1&&msgtype=2">
						<a href="${ctx}/user/parDsisMsgInSchList.action?typebelong=-1&&msgtype=2" class="bje font-2">家长交流</a>
					</li>
				</ul>
				<ul>
					<li id="consume/consumeRecords!getListByParent.action">
						<a href="${ctx}/consume/consumeRecords!getListByParent.action" class="bj14 font-2">一卡通消费查询</a>
					</li>
				</ul>
				<ul>
					<li id="consume/payRecords!parentListRecords.action">
						<a href="${ctx}/consume/payRecords!parentListRecords.action" class="bj35 font-2">一卡通缴费查询</a>
					</li>
				</ul>
			
				
								<ul>
					<li id="source/addsourceGet!addget.action">
						<a href="${ctx}/source/addsourceGet!addget.action" class="bj35 font-2">上传资源</a>
					</li>
				</ul>
				<ul>
					<li id="source/sourceList.action">
						<a href="${ctx}/source/sourceList.action" class="bj35 font-2">我上传的资源</a>
					</li>
				</ul>
			</div>
			<div class="chaxun">
				<div class="title-1 font-1">
					设置
				</div>
				<ul>
					<li id="myspace/updatepwd.jsp">
						<a href="${ctx}/myspace/updatepwd.jsp" class="bj22 font-2">修改密码</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</s:elseif>
<!-- 老师 -->
<s:elseif test="#session.loginMemberType == 3">
	<div class="side-left">
		<div class="tt">
			<div class="ru1">
				<img  <c:if test="${empty loginMemberHeadpath}">src="${ctx}/images/user/imgmo.gif"</c:if> <c:if test="${not empty loginMemberHeadpath}">src="${loginMemberHeadpath}"</c:if> />
				<p class="font-3">
					<b class="font-2">${loginMemberNikename }</b>
					<br />

					<a href="${ctx}/myspace/headChange.jsp">更换头像>></a>
				</p>
			</div>
			<div class="chaxun">
				<div class="title-1 font-1">
					快捷导航
				</div>
				<ul>
					<li id="teacher/studentinfo!getstudentlist.action">
						<a href="${ctx}/teacher/studentinfo!getstudentlist.action"
							class="bj17 font-2">学生学籍档案</a>
					</li>
				</ul>	
				<ul>
                 <li id="user/attenQueryAction!teaAttendancePager.action?isDefaultQuery=1"><a href="${ctx}/user/attenQueryAction!teaAttendancePager.action?isDefaultQuery=1" class="bj8 font-2">考勤查询</a></li>
              </ul>
              <ul>
                 <li id="user/smsMsgAction!teaQuery.action?isDefaultQuery=1"><a href="${ctx}/user/smsMsgAction!teaQuery.action?isDefaultQuery=1" class="bj9 font-2">作业查询</a></li>
             </ul>
             <ul>
               <li id="user/examQueryAction!teaExamList.action?isDefaultQuery=1"><a href="${ctx}/user/examQueryAction!teaExamList.action?isDefaultQuery=1" class="bj10 font-2">成绩查询</a></li>
              </ul>
			<ul>			
               <li id="stuinfo/growfile!teaQuery.action">
               <s:if test="#session.loginMemberUsername == 201241002">
            <a href="${ctx}/student/growfile/grow_list_wdd.jsp" class="bj21 font-2">            
            成长档案</a>
            </s:if>
            <s:elseif test="#session.loginMemberUsername ==201241003">
            <a href="${ctx}/student/growfile/grow_list_tr.jsp" class="bj21 font-2">            
            成长档案</a>
            </s:elseif>
             <s:elseif test="#session.loginMemberUsername ==201241001">
            <a href="${ctx}/student/growfile/grow_list_hzh.jsp" class="bj21 font-2">            
            成长档案</a>
            </s:elseif>
            <s:else>
             <a href="${ctx}/stuinfo/growfile!teaQuery.action" class="bj21 font-2">成长档案</a>
            </s:else>              
               </li>
            </ul>
            <ul>
              <li id="stuinfo/footmark!teaQuery.action"><a href="${ctx}/stuinfo/footmark!teaQuery.action" class="bj12 font-2">成长足迹</a></li>
            </ul>
              <ul>
					<li id="user/teaMsgList.action?typebelong=03">
						<a href="${ctx}/user/teaMsgList.action?typebelong=03" class="bj26 font-2">学校公告</a>
					</li>
				</ul>
				<ul>
					<li id="user/teaMsgList.action?typebelong=02">
						<a href="${ctx}/user/teaMsgList.action?typebelong=02" class="bj18 font-2">班级通知</a>
					</li>
				</ul>
				<ul>
					<li id="user/teaMsgList.action?typebelong=01">
						<a href="${ctx}/user/teaMsgList.action?typebelong=01" class="bj11 font-2">老师留言</a>
					</li>
				</ul>			
						<ul>
					<li id="user/teaDsisMsgList.action?typebelong=-1">
						<a href="${ctx}/user/teaDsisMsgList.action?typebelong=-1" class="bja font-2">学校通知</a>
					</li>
				</ul>
				<ul>
					<li id="user/teaDsisMsgInSchList.action?typebelong=-1&&msgtype=3">
						<a href="${ctx}/user/teaDsisMsgInSchList.action?typebelong=-1&&msgtype=3" class="bjb font-2">学生评语</a>
					</li>
				</ul>
				<ul>
					<li id="user/teaDsisMsgInSchList.action?typebelong=-1&&msgtype=5">
						<a href="${ctx}/user/teaDsisMsgInSchList.action?typebelong=-1&&msgtype=5" class="bjc font-2">学习攻略</a>
					</li>
				</ul>
				<ul>
					<li id="user/teaDsisMsgInSchList.action?typebelong=-1&&msgtype=6">
						<a href="${ctx}/user/teaDsisMsgInSchList.action?typebelong=-1&&msgtype=6" class="bjd font-2">营养膳食</a>
					</li>
				</ul>
				<ul>
					<li id="user/teaDsisMsgInSchList.action?typebelong=-1&&msgtype=2">
						<a href="${ctx}/user/teaDsisMsgInSchList.action?typebelong=-1&&msgtype=2" class="bje font-2">家长交流</a>
					</li>
				</ul>
				<ul>
					<li id="teacher/parentinfo!toListParentinfo.action">
						<a href="${ctx}/teacher/parentinfo!toListParentinfo.action"
							class="bj16 font-2">学生家庭信息</a>
					</li>
				</ul>

				
				<ul>
					<li id="source/addsourceGet!addget.action">
						<a href="${ctx}/source/addsourceGet!addget.action" class="bj35 font-2">上传资源</a>
					</li>
				</ul>
				<ul>
					<li id="source/sourceList.action">
						<a href="${ctx}/source/sourceList.action" class="bj35 font-2">我上传的资源</a>
					</li>
				</ul>
				
			</div>
			<div class="chaxun">
				<div class="title-1 font-1">
					设置
				</div>
				<ul>
					<li id="myspace/updatepwd.jsp">
						<a href="${ctx}/myspace/updatepwd.jsp" class="bj22 font-2">修改密码</a>
					</li>
				</ul>
				<ul>
					<li id="teacher/studentinfo/add.jsp">
						<a href="${ctx}/teacher/studentinfo/add.jsp"
							class="bj36 font-2">添加学籍档案</a>
					</li>
				</ul>
				<ul>
					<li id="teacher/parentinfo/add.jsp">
						<a href="${ctx}/teacher/parentinfo/add.jsp"
							class="bj37 font-2">添加家庭信息</a>
					</li>	
				</ul>
			</div>
		</div>
	</div>
</s:elseif>
<!-- 学生 -->
<s:elseif test="#session.loginMemberType == 4">
	<div class="side-left">
		<div class="tt">
			<div class="ru1">
				<img  <c:if test="${empty loginMemberHeadpath}">src="${ctx}/images/user/imgmo.gif"</c:if> <c:if test="${not empty loginMemberHeadpath}">src="${loginMemberHeadpath}"</c:if> />
				<p class="font-3">
					<b class="font-2">${loginMemberNikename }</b>
					<br />

					<a href="${ctx}/myspace/headChange.jsp">更换头像>></a>
				</p>
			</div>
			<div class="chaxun">
				<div class="title-1 font-1">
					快捷导航
				</div>
		  <ul>
            <li id="teacher/studentinfo!getMymsg.action?sid=${session.dsisUserid }"><a href="${ctx}/teacher/studentinfo!getMymsg.action?sid=${session.dsisUserid }" class="bj19 font-2">学籍档案查询</a></li>
          </ul>  
		   <ul>
	       <li id="user/attenQueryAction!stuAttendancePager.action?isDefaultQuery=1"><a href="${ctx}/user/attenQueryAction!stuAttendancePager.action?isDefaultQuery=1" class="bj8 font-2">考勤查询</a></li>
           </ul>
           <ul>
            <li id="user/smsMsgAction!stuQuery.action?isDefaultQuery=1"><a href="${ctx}/user/smsMsgAction!stuQuery.action?isDefaultQuery=1" class="bj9 font-2">作业查询</a></li>
           </ul>
          <ul>
            <li id="user/examQueryAction!stuExamList.action?isDefaultQuery=1"><a href="${ctx}/user/examQueryAction!stuExamList.action?isDefaultQuery=1" class="bj10 font-2">成绩查询</a></li>
          </ul>
				 <ul>
            <li id="stuinfo/growfile!stuQuery.action">
            
            <s:if test="#session.loginMemberUsername == '201241002'">
            <a href="${ctx}/student/growfile/grow_list_wdd.jsp" class="bj21 font-2">                        
            成长档案</a>
            </s:if>
            <s:elseif test="#session.loginMemberUsername == '201241003'">
             <a href="${ctx}/student/growfile/grow_list_tr.jsp" class="bj21 font-2">           
            成长档案</a>
            </s:elseif>
            <s:elseif test="#session.loginMemberUsername == '201241001'">
           <a href="${ctx}/student/growfile/grow_list_hzh.jsp" class="bj21 font-2">           
            成长档案</a>
            </s:elseif>
            <s:else >           
             <a href="${ctx}/stuinfo/growfile!stuQuery.action" class="bj21 font-2">           
            成长档案</a>
            </s:else>
            
            </li>
          </ul>        
           <ul>
            <li id="stuinfo/footmark!stuQuery.action"><a href="${ctx}/stuinfo/footmark!stuQuery.action" class="bj12 font-2">成长足迹</a></li>
          </ul>
				<ul>
					<li id="user/stuMsgList.action?typebelong=03">
						<a href="${ctx}/user/stuMsgList.action?typebelong=03" class="bj26 font-2">学校公告</a>
					</li>
				</ul>
				<ul>
					<li id="user/stuMsgList.action?typebelong=02">
						<a href="${ctx}/user/stuMsgList.action?typebelong=02" class="bj18 font-2">班级通知</a>
					</li>
				</ul>
				<ul>
					<li id="user/stuMsgList.action?typebelong=01">
						<a href="${ctx}/user/stuMsgList.action?typebelong=01" class="bj11 font-2">老师留言</a>
					</li>
				</ul>
				<ul>
					<li id="user/stuDsisMsgList.action?typebelong=-1">
						<a href="${ctx}/user/stuDsisMsgList.action?typebelong=-1" class="bja font-2">学校通知</a>
					</li>
				</ul>
				<ul>
					<li id="user/stuDsisMsgInSchList.action?typebelong=-1&&msgtype=3">
						<a href="${ctx}/user/stuDsisMsgInSchList.action?typebelong=-1&&msgtype=3" class="bjb font-2">学生评语</a>
					</li>
				</ul>
				<ul>
					<li id="user/stuDsisMsgInSchList.action?typebelong=-1&&msgtype=5">
						<a href="${ctx}/user/stuDsisMsgInSchList.action?typebelong=-1&&msgtype=5" class="bjc font-2">学习攻略</a>
					</li>
				</ul>
				<ul>
					<li id="user/stuDsisMsgInSchList.action?typebelong=-1&&msgtype=6">
						<a href="${ctx}/user/stuDsisMsgInSchList.action?typebelong=-1&&msgtype=6" class="bjd font-2">营养膳食</a>
					</li>
				</ul>
				<ul>
					<li id="consume/consumeRecords!getListByStudent.action">
						<a href="${ctx}/consume/consumeRecords!getListByStudent.action" class="bj14 font-2">一卡通消费查询</a>
					</li>
				</ul>
				<ul>
					<li id="consume/payRecords!studentListRecords.action">
						<a href="${ctx}/consume/payRecords!studentListRecords.action" class="bj35 font-2">一卡通缴费查询</a>
					</li>
				</ul>

								<ul>
					<li id="source/addsourceGet!addget.action">
						<a href="${ctx}/source/addsourceGet!addget.action" class="bj35 font-2">上传资源</a>
					</li>
				</ul>
				<ul>
					<li id="source/sourceList.action">
						<a href="${ctx}/source/sourceList.action" class="bj35 font-2">我上传的资源</a>
					</li>
				</ul>
			</div>
			<div class="chaxun">
				<div class="title-1 font-1">
					设置
				</div>
				<ul>
					<li id="myspace/updatepwd.jsp">
						<a href="${ctx}/myspace/updatepwd.jsp" class="bj22 font-2">修改密码</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</s:elseif>

<s:else></s:else>


<script type="text/javascript">
<!--
	 tempurl = (window.location.pathname+window.location.search).substring(1);
	
	 if(document.getElementById(tempurl) != null){
	 	document.getElementById(tempurl).className="hit";
	 }
//-->
</script>
