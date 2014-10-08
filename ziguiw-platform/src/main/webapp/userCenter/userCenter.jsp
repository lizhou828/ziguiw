<%@ page import="com.zigui.domain.UserBase" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心主页-子贵网用户中心</title>
<link href="/css/uccss.css" type="text/css" rel="stylesheet" />
<script src="/js/basic.js.jsp"></script>


</head>
<body >

<!--onload="ajaxShowHeadMsg()"-->
    <jsp:include page="/userCenter/ucPublic/head.jsp"></jsp:include>
    <div class="uc_midd">
    	<div class="uc_l">


            <jsp:include page="dsisQueryNavigator.jsp"/>

            <jsp:include page="commonQuestion.jsp" >
                <jsp:param name="columnId" value="5"></jsp:param>
                <jsp:param name="columnName" value="常见问题"></jsp:param>
            </jsp:include>
        	

        </div>


        <div class="uc_right">
            <%UserBase user=(UserBase)session.getAttribute("VALID_USER");%>
            <div id=roleBasicInfo>
                    <s:action name="showRoleInfo"  executeResult="true" namespace="/userCenter">
                    </s:action>

            </div>

            <div class="uc_r">
                 <div class="uc_tipp">
                    <div class="lit_bg">温馨提示</div>
                    <ul>
                        <p class="uc_tip">请您注意，您的积分接近0，为不影响您正常的操作，请及时充值!</p>               
                    </ul>
                </div>
                
            <div class="uc_note">
            	<div class="lit_bg">
                    <span class="uc_sn">学校最新公告</span>
                    <span class="uc_more"><a href="/userCenter/schoolNoticeList.jsp">更多&gt;&gt;</a></span>
                </div>
            	<ul>
                   <s:action name="showSchoolNotice" namespace="/userCenter" executeResult="false" ignoreContextParams="false"></s:action>
                    <c:choose>
                        <c:when test="${map['data'] != null && fn:length( map['data'] ) != 0 } ">
                            <c:forEach items="${map['data']}" var="data">
                                <li>
                                        ${data['MSG_CONTENT']}
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p class="uc_tip">无最新学校公告！ </p>
                        </c:otherwise>
                    </c:choose>
                </ul>               
            </div>    
            
              	<div class="uc_letter">
                    <div class="lit_bg">站内信</div>

                          <s:action var="getInnerLetter" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="false">
                              <s:param name="hql">select cm from CommonMessage cm   where cm.toUser.id = <%=user.getId()%> and cm.kind = 3 and cm.state = 1 and cm.title is not null and cm.fromUser is not null  and rownum<=3 order by cm.createTime desc</s:param>
                              <s:param name="queryString">1=1</s:param>
                          </s:action>
                    <ul>
                        <p class="uc_tip">尊敬的<%=user.getNickName()%>，您最新的站内信信息： </p>
                        <c:choose>
                            <c:when test="${obj.list != null && fn:length(obj.list)!=0}">
                                <c:forEach items="${obj.list}" var="innerLetters">
                                    <p  class="uc_tip">
                                        <a href="/userCenter/innerLetterDetail.action?letterId=${innerLetters.id}">${innerLetters.title}</a>
                                    </p>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p class="uc_tip">无最新站内信！ </p>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                        
            </div>
        
        </div>
        
     <div class="clear"></div>
    
    <div class="uc_noti">
    	<p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>        
        
    </div>

    <jsp:include page="/inc/templateFoot.jsp"/>
