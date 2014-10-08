<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.zigui.domain.UserBase"%>
<%@ page import="java.io.*,org.apache.commons.lang.StringUtils,org.apache.commons.lang.exception.ExceptionUtils,org.apache.commons.codec.digest.DigestUtils,com.zigui.utils.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>
<%@ include file="../comm/common.jsp"%>

	<link href="<%=path%>/css/xiaohome.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/school/js/campus_jquery.js"></script>
    <script type="text/javascript" src="${ctx}/school/js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="${ctx}/school/js/slider.js"></script>
    <link href="${ctx}/school/css/style.css" rel="stylesheet" type="text/css" />

<div class="content clearfix  pr">

<div class="userCenterContent" >

<div class="clearfix">
    <div class="w_user_topChannel" style="width:315px;height:233px;margin-right:5px;float:left">
        <!-- 图片切换区域开始 -->

        <div class="content_right">
            <s:action var="schoolView" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
                <s:param name="pageNo" value="1"></s:param>
                <s:param name="pageSize" value="5"></s:param>
                <s:param name="hql">from UserPhoto where user.id = <%=hostUser.getId()%></s:param>
            </s:action>
            <div class="ad" >
                <ul class="slider" >
                <s:iterator value="#schoolView.obj.list" var="list" status="status">
                    <s:if test="#status.index < 5">
                        <li>
                            <a href="<%=path%>/school/<s:property value="#list.user.nickName"/>/photo/<s:property value="#list.albumId"/>/<s:property value="#list.id"/>" target="_blank">
                                <img src="${ctx}/<s:property value="#list.url"/>" width="315" height="233">
                            </a>
                        </li>
                    </s:if>
                </s:iterator>
                </ul>
                <ul class="num" >
                    <li>1</li>
                    <li>2</li>
                    <li>3</li>
                    <li>4</li>
                </ul>
            </div>
        </div>
        <!-- 图片切换区域 结束-->
    </div>

    <div class="w_user_topChannel" style="width:318px; height:233px; float:left; margin-right:5px;">
        <div class="w_user_topChannelheader">
            <div class="i_left">
                <img src="<%=path%>/images/w_user_titlePoint.gif"/>校园新闻
            </div>
            <div style="line-height:28px;float:right;margin-right:5px;">
                <a href="<%=path%>/school/school_news.jsp?type=5&hostUserName=<%=hostUser.getNickName()%>">
                    更多&gt
                </a>
            </div>
        </div>
        <div class="w_user_topChannelContent">
            <table class="w_user_topChannelContentBox">
                <tr>
                    <td class="w_topLeft"></td>
                    <td class="w_topMiddle"></td>
                    <td class="w_topRight"></td>
                </tr>
                <tr>
                    <td class="w_middleLeft"></td>
                    <td class="w_middleMiddle">
                        <ul class="w_user_topList">
                            <s:action var="diaryList" name="schoolDiary_getByNickName" namespace="/user" executeResult="false" ignoreContextParams="false">
                                <s:param name="status" value="1"></s:param>
                                <s:param name="pageSize" value="5"></s:param>
                                <s:param name="type" value="5"></s:param>
                            </s:action>

                            <s:iterator value="#diaryList.pagedDiaries.list" var="diaries">
                                <li style="height:30px">
                                    <a href="<%=path%>/user/getShoolDiaryById.action?diary.id=<s:property value="#diaries.id"/>&hostUserName=<s:property value="#diaries.user.nickName"/>" target="_blank" class="w_title">
                                        <s:property value="#diaries.title"/>
                                    </a>
                                    <span class="w_datetime"><s:property value="#diaries.formatedCreateDate"/></span>
                                </li>
                            </s:iterator>


                        </ul>
                    </td>
                    <td class="w_middleRight"></td>
                </tr>
                <tr>
                    <td class="w_bottomLeft"></td>
                    <td class="w_bottomMiddle"></td>
                    <td class="w_bottomRight"></td>
                </tr>
            </table>
        </div>
    </div>

    <div class="w_user_topChannel" style="width:318px;height:233px; float:left;">
        <div class="w_user_topChannelheader">
            <div class="i_left">
                <img src="<%=path%>/images/w_user_titlePoint.gif"/>最新公告
            </div>
            <div style="line-height:28px;float:right;margin-right:5px;">
                <a href="<%=path%>/school/school_news.jsp?type=6&hostUserName=<%=hostUser.getNickName()%>">
                    更多&gt
                </a>
            </div>
        </div>
        <div class="w_user_topChannelContent">
            <table class="w_user_topChannelContentBox">
                <tr>
                    <td class="w_topLeft"></td>
                    <td class="w_topMiddle"></td>
                    <td class="w_topRight"></td>
                </tr>
                <tr>
                    <td class="w_middleLeft"></td>
                    <td class="w_middleMiddle">
                        <ul class="w_user_topList">
                            <s:action var="diaryList1" name="schoolDiary_getByNickName" namespace="/user" executeResult="false" ignoreContextParams="false">
                                <s:param name="status" value="1"></s:param>
                                <s:param name="pageSize" value="5"></s:param>
                                <s:param name="type" value="6"></s:param>
                            </s:action>

                            <s:iterator value="#diaryList1.pagedDiaries.list" var="diaries">
                                <li style="height:30px">
                                    <a href="<%=path%>/user/getShoolDiaryById.action?diary.id=<s:property value="#diaries.id"/>&hostUserName=<s:property value="#diaries.user.nickName"/>" target="_blank" class="w_title">
                                        <s:property value="#diaries.title"/>
                                    </a>
                                    <span class="w_datetime"><s:property value="#diaries.formatedCreateDate"/></span>
                                </li>
                            </s:iterator>

                        </ul>
                    </td>
                    <td class="w_middleRight"></td>
                </tr>
                <tr>
                    <td class="w_bottomLeft"></td>
                    <td class="w_bottomMiddle"></td>
                    <td class="w_bottomRight"></td>
                </tr>
            </table>
        </div>
    </div>

</div>



	<div class="w_user_topChannel" style="display: none;">
	  <div class="w_user_topChannelheader">
          <div class="i_left">
            <img src="<%=path%>/images/w_user_titlePoint.gif"/>师资力量
          </div>
          <div style="line-height:28px;float:right;margin-right:5px;">
              <a href="<%=path%>/school/teachers.jsp?hostUserName=<%=hostUser.getNickName()%>">
                  更多&gt
              </a>
          </div>
      </div>
	  <div class="w_user_topChannelContent">
	    <table class="w_user_topChannelContentBox">
	      <tr>
	        <td class="w_topLeft"></td>
	        <td class="w_topMiddle"></td>
	        <td class="w_topRight"></td>
          </tr>
	      <tr>
	        <td class="w_middleLeft"></td>
	        <td class="w_middleMiddle">
            	<ul class="w_user_topSzll">
            		<s:action var="schoolTeachers" name="baseData_getTeachersBySchool" namespace="/" executeResult="false" ignoreContextParams="true">
			      <s:param name="pageNo" value="1"></s:param>
			      <s:param name="pageSize" value="8"></s:param>
			      <s:param name="user.foreignKey"><%=hostUser.getForeignKey()%></s:param>
	        </s:action>
                <s:iterator value="#schoolTeachers.pageObj.list" var="teacher">
                    <li>
                        <%--<img src="${ctx}/<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#teacher.photo"),  path + "/images/head.jpg", 120)%>" width="120" height="120"/>--%>
                        <div class="name">
                            <s:property value="#teacher.name"/>
                        </div>
                    </li>
                  </s:iterator>
                	
                </ul>
            </td>
	        <td class="w_middleRight"></td>
          </tr>
	      <tr>
	        <td class="w_bottomLeft"></td>
	        <td class="w_bottomMiddle"></td>
	        <td class="w_bottomRight"></td>
          </tr>
        </table>
      </div>
    </div>

    <div class="clearfix">

    <div class="w_user_topChannel">
        <div class="w_user_topChannelheader"><img src="<%=path%>/images/w_user_titlePoint.gif"/>校园简介<span style="float:right; margin-right:250px; *margin-top:-29px;">
            <img src="<%=path%>/images/w_user_titlePoint.gif"/>校长致辞</span></div>


        <div class="w_user_topChannelContent">
            <table class="w_user_topChannelContentBox">
                <tr>
                    <td class="w_topLeft"></td><td class="w_topMiddle"></td><td class="w_topRight"></td>
                </tr>
                <tr>
                    <td class="w_middleLeft"></td><td class="w_middleMiddle">
                    <div class="w_user_intro" style="width:600px;display:inline">

                        <div style=" height:190px; display:block; overflow:hidden;">
                            <p>
                                <%if(schoolInfo != null && schoolInfo.getSummary() != null){%><%=schoolInfo.getSummary()%><%}%>
                            </p>
                        </div>

                        <div style="float:right; font-size:12px;" ><a href="<%=path%>/user/getSchoolSummary.action?hostUserName=<%=hostUser.getNickName()%>"><<更多</a></div>

                    </div>

                    <div class="w_user_ext" style="display:inline;float:none">
                        <p>
                            <%if(schoolInfo != null && schoolInfo.getSpeech() != null){%><%=schoolInfo.getSpeech()%><%}%>
                        </p>
                    </div>



                </td><td class="w_middleRight"></td>
                </tr>
                <tr>
                    <td class="w_bottomLeft"></td><td class="w_bottomMiddle"></td><td class="w_bottomRight"></td>
                </tr>
            </table>
        </div>
    </div>

  	

		<div class="w_user_topChannel" style="margin-top:8px;">
	  	<div class="w_user_topChannelheader">
              <div class="i_left">
                <img src="<%=path%>/images/w_user_titlePoint.gif"/>校园风光
              </div>
              <div style="line-height:28px;float:right;margin-right:5px;">
                  <a href="<%=path%>/user/school_album_list.action?hostUserName=<%=hostUser.getNickName()%>">
                      更多&gt
                  </a>
              </div>
          </div>
          <div class="w_user_topChannelContent">
              <div class="img-scroll">
                  <span class="prev">&lt;</span>
                  <span class="next">&gt;</span>
                  <div class="img-list">
                      <ul>
                          <s:action var="schoolView" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
                              <s:param name="pageNo" value="1"></s:param>
                              <s:param name="pageSize" value="5"></s:param>
                              <s:param name="hql">from UserPhoto where user.id = <%=hostUser.getId()%></s:param>
                          </s:action>
                          <s:iterator value="#schoolView.obj.list" var="userPhoto">
                              <li><a href="<%=path%>/school/<s:property value="#userPhoto.user.nickName"/>/photo/<s:property value="#userPhoto.albumId"/>/<s:property value="#userPhoto.id"/>">
                                  <img src="${ctx}/<s:property value="#userPhoto.url"/>" width="120" height="80" /></a></li>
                          </s:iterator>
                      </ul>
                  </div>
              </div>
              <script type="text/javascript" src="${ctx}/js/index_lunbo.js"></script>
          </div>
        </div>    
  </div>

	<input type="hidden" id="schoolId" value="<%=hostUserId%>">
	
	<!--box07 E -->
	<%@ include file="../inc/templateFoot.jsp"%>
