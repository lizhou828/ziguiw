<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/templateHead.jsp"%>

<div class="content">
	<div class="ad01">
	<a title="" href="#"><img alt="" src="<%=path%>/images/adv01.jpg"></a>
	</div>
	<div class="clearfix bg_f7 h-225">
		<div class="l w-730 pr">
			<ul class="picList02 clearfix">
			<s:action var="mingxing101Users" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
				       <s:param name="pageNo" value="1"></s:param>
			      <s:param name="pageSize" value="40"></s:param>
				      <s:param name="hql">from UserBase where knowledgeRecommendRegion.id = 105</s:param>
			        </s:action>
			        <s:iterator value="#mingxing101Users.obj.list" var="user"> 
			        <li><a title="" href="<%=path%>/myhome/<s:property value="#user.nickName"/>"><img alt="" style="width:70px;height:70px" src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#user.portrait"), path + "/images/head.jpg", 70)%>"></a></li>
			        </s:iterator>
			</ul>		
		</div>
		<div class="r w-210 start_home">
			<a href="<%=path%>/myhome"><img alt=""  src="<%=path%>/images/go_myhome.png" /></a>
		</div>	
	</div>
	<div class="clearfix mt10">
		<div class="l w-730">
			
		<div class="clearfix borda mt10 mb10 jyzx">    
        <div class="h3titc">
            <h3 class="pr fyahei">日记精选</h3>
        
        </div>
        <div class="fl w-690">
        
            <ul class="xy_top clearfix" style="height:auto">
            <s:action var="riji123UserDiaries" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
			      <s:param name="pageNo" value="1"></s:param>
			      <s:param name="pageSize" value="4"></s:param>
			      <s:param name="hql">from UserDiary where knowledgeRecommendRegion.id = 123 and firstImage is not null  order by createTime desc</s:param>
	        </s:action>
	        <s:iterator value="#riji123UserDiaries.obj.list" var="userDiary">
	        	<li>
                	<a href="<%=path%>/myhome/<s:property value="#userDiary.user.nickName"/>/diary/<s:property value="#userDiary.id"/>"><img src="<s:property value="#userDiary.firstImage"/>" alt="图片关键字" /></a>
                    <h4><a href="<%=path%>/myhome/<s:property value="#userDiary.user.nickName"/>/diary/<s:property value="#userDiary.id"/>"><s:property value="#userDiary.title"/></a></h4>
                    <p><s:property value="#userDiary.description"/><a href="<%=path%>/myhome/<s:property value="#userDiary.user.nickName"/>/diary/<s:property value="#userDiary.id"/>" class="fsong">[详细]</a></p>
                </li>
	        </s:iterator>
            </ul>
            <ul class="xy_bot clearfix" >
            	
       		<s:action var="riji123UserDiaries1" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
			      <s:param name="pageNo" value="1"></s:param>
			      <s:param name="pageSize" value="10"></s:param>
			      <s:param name="hql">from UserDiary where knowledgeRecommendRegion.id = 124 and firstImage is null  order by createTime desc</s:param>
	        </s:action>
	        <s:iterator value="#riji123UserDiaries1.obj.list" var="userDiary">
	        <li><a href="<%=path%>/myhome/<s:property value="#userDiary.user.nickName"/>/diary/<s:property value="#userDiary.id"/>" title="" target="_blank"><s:property value="#userDiary.title"/></a></li>
	        </s:iterator>	
			 </ul>
        </div>    
		 
		</div>
		<div class="ad_w730"><a href="#"><img src="<%=path%>/images/ad_730_1.jpg" alt="关键字" /></a></div>
            <div class="clearfix borda mt10 rjjx mb10">
	        <div class="h3titc">
	            <h3 class="pr fyahei">照片精选</h3>
	        </div>
	        <div class="l w-270 xcjx">
	        
	        <div class="lunbo3 fl pr">

          <div class="sub_box_2">

                <div id="p-select" class="sub_nav_2">

                    <div class="sub_no" id="bd1lfsj">

                        <ul>

                            <li class="show">1</li><li class="">2</li><li class="">3</li><li class="">4</li>

                        </ul>

                    </div>

                </div>

                <div id="bd1lfimg_2">

                    <div>
                    	<s:action var="userPhotoPPT" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
						      <s:param name="pageNo" value="1"></s:param>
						      <s:param name="pageSize" value="4"></s:param>
						      <s:param name="hql">from UserPhoto where knowledgeRecommendRegion.id = 143</s:param>
				        </s:action>
				        

                        <dl class="show"></dl>
                        <s:iterator value="#userPhotoPPT.obj.list" var="userPhoto">
                        <dl class="">
							
                            <dt>
                                <a href="<%=path%>/myhome/<s:property value="#userPhoto.user.nickName"/>/photo/<s:property value="#userPhoto.albumId"/>/<s:property value="#userPhoto.id"/>" title="" target="_blank">
                                    <img style="width:240px;height:190px" src="${ctx}/<s:property value="#userPhoto.url"/>" alt="<s:property value="#userPhoto.title"/>">
                                </a>
                            </dt>

                            <dd>

                                <h4 class="fyahei">
                                    <a href="<%=path%>/myhome/<s:property value="#userPhoto.user.nickName"/>/photo/<s:property value="#userPhoto.albumId"/>/<s:property value="#userPhoto.id"/>" target="_blank">
                                    <s:property value="#userPhoto.title"/>
                                    </a>
                                </h4>

                            </dd>

                            <dd class="bg_lunbo_2"></dd>

                        </dl>
                        </s:iterator>	

                	</div>

                </div>

            </div>

			<script type="text/javascript">movec_2();</script>
			
			

			<!-----------图片切换  end----------->
			</div>

		       <ul class="xy_bot w_li">
                <s:action var="bottonUserPhotos" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
			      <s:param name="pageNo" value="1"></s:param>
			      <s:param name="countPerPage" value="7"></s:param>
			      <s:param name="hql">from UserPhoto where knowledgeRecommendRegion.id = 145  order by createTime desc</s:param>
	        </s:action>
	        <s:iterator value="#bottonUserPhotos.obj.list" var="userPhoto">
                <li><strong class="fsong"></strong>
                    <a href="<%=path%>/myhome/<s:property value="#userPhoto.user.nickName"/>/photo/<s:property value="#userPhoto.albumId"/>/<s:property value="#userPhoto.id"/>"><s:property value="#userPhoto.text"/></a></li>
            </s:iterator>           
	            </ul>

	        </div>
	        
			<div class="r w-445 p_list">
				<ul class="img_f mt10 clearfix">
					<s:action var="hotPhoto" name="commonQuery" namespace="/" executeResult="false" ignoreContextParams="true">
				      <s:param name="pageNo" value="1"></s:param>
				      <s:param name="countPerPage" value="9"></s:param>
				      <s:param name="hql">from UserPhoto where knowledgeRecommendRegion.id = 144 order by createTime desc</s:param>
		        </s:action>
		        <s:iterator value="#hotPhoto.obj.list" var="userPhoto">
	                    <li style="height:120px;width:120px">
                            <a class="a-img1" href="<%=path%>/myhome/<s:property value="#userPhoto.user.nickName"/>/photo/<s:property value="#userPhoto.albumId"/>/<s:property value="#userPhoto.id"/>">
                                <img src="${ctx}/<%=((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#userPhoto.url"))%>" alt=""></a><a href="<%=path%>/myhome/<s:property value="#userPhoto.user.nickName"/>/photo/<s:property value="#userPhoto.albumId"/>/<s:property value="#userPhoto.id"/>"><s:property value="#userPhoto.title"/></a></li>
	            </s:iterator>
	                 
	                </ul>
			</div>
        </div>	
		</div>
		<div class="r w-240">
			<div class="jyrqb">
				<div class="box03_hd">
				<h2>家园人气榜</h2>
				
				</div>					
				<ul class="">
					<s:action var="listHotHome" name="hot_home_list" namespace="/user" executeResult="false" ignoreContextParams="true">
						<s:param name="pageSize" value="10"></s:param>
					</s:action>
	
            		<s:iterator value="#listHotHome.users.list" var="hotUser">
            			<li><a href="<%=path%>/myhome/<s:property value="#hotUser.nickName"/>" title="" target="_blank"><s:property value="#hotUser.nickName"/></a>
            				<span><s:property value="#hotUser.spacePv"/></span>
            			</li>
					</s:iterator>
				</ul>				
			</div>				
			<div class="djdzs mt10" >
				<div class="box03_hd">
				<h2>大家都在说</h2>
				</div>	
				<div class="box03_bd">
			    	<div class="box_bd_son">
			    	
			    	<s:action var="moodList" name="mood_list" namespace="/user" executeResult="false" ignoreContextParams="false">
						<s:param name="pageSize" value="4"></s:param>
					</s:action>
	
            		<s:iterator value="#moodList.moods" var="moods">
            			<dl class="f">
							<dt class="l">
                                <a href="<%=path%>/myhome/<s:property value="#moods.user.nickName"/>" target="_blank" class="a-img1">
                                    <img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#moods.user.portrait"), path + "/images/head.jpg", 70)%>" width=48 height=48/>
                                </a>
                            </dt>
							<dd class="p-l60 mb5">
                                <a href="<%=path%>/myhome/<s:property value="#moods.user.nickName"/>">
                                    <c:if test="${fn:length(moods.user.nickName) == 11 && fn:startsWith(moods.user.nickName, '1')}">
                                        ${fn:substring(moods.user.nickName, 0, 3)}****${fn:substring(moods.user.nickName, 7, 11)}
                                    </c:if>
                                    <c:if test="${fn:length(moods.user.nickName) != 11 && !fn:startsWith(moods.user.nickName, '1')}">
                                        ${moods.user.nickName}
                                    </c:if>
                                    :
                                </a>
                                <s:property value="#moods.text"/>
                            </dd>
							<dd><s:property value="#moods.createTime"/>&nbsp;</dd>
						</dl>	
					</s:iterator>
					
					
		        	</div>
				</div>	
		
		</div>	
	</div>
	
  </div>
  <%@ include file="../inc/templateLink.jsp"%>
</div>
<!-- Baidu Button BEGIN -->
<script type="text/javascript" id="bdshare_js" data="type=slide&img=0&uid=1998022" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
</script>
<!-- Baidu Button END -->
<%@ include file="../inc/templateFoot.jsp"%>