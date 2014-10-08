<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/head.jsp"%>  

<!--header E -->

<div class="content clearfix  pr">
<div class="mypos1 fsong mb10"> <a href="<%=path%>/school/photo_upload.jsp" class="r btn4">上传图片</a><a href="<%=path%>/school/school_view.jsp">校园风光</a> &gt; ${album.title}</div>
	<div class="clearfix">
	<div class="my_home_xc_list pr">
	<a href="<%=path%>/user/getSchoolRoundPhoto.action?photoId=${prePhoto.id}&albumId=${albumId}&hostUserName=${prePhoto.user.nickName}" class="my_home_list_l">
        <img alt="" src="<%=path%>/images/my_home_xc_list_l1.png"></a>
	<!--可以点击时，使用
	<a href="#" class="my_home_list_l"><img alt="" src="images/my_home_xc_list_l2.png"></a>-->
		<ul class="img_f">
		<s:iterator value="photos" var="photo">
			<li><a class="a-img1" href="<%=path%>/user/getSchoolRoundPhoto.action?photoId=<s:property value='#photo.id'/>&albumId=${albumId}&hostUserName=<s:property value='#photo.user.nickName'/>" >
                <img src="${ctx}/<%=(String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#photo.url")%>" alt=""></a></li>

		</s:iterator>   
		
       
		</ul>    
	<a href="<%=path%>/user/getSchoolRoundPhoto.action?photoId=${nextPhoto.id}&albumId=${albumId}&hostUserName=${nextPhoto.user.nickName}" class="my_home_list_r">
        <img alt="" src="<%=path%>/images/my_home_xc_list_r1.png"></a>
	<!--可以点击时，使用
	<a href="#" class="my_home_list_l"><img alt="" src="images/my_home_xc_list_r2.png"></a>-->

	</div>
	<div class="bordb xc_dt">
        <img alt=" " src="${ctx}/<s:property value='photo.url'/>" onmouseover="upNext(this)">
	</div>
	</div>
	
	<div class="bordb my_home hi-at mt10">
		<div class="h3titb">
			<h3 class="fyahei">发表评论</h3>
		</div>
		<form action="<%=path%>/user/commentSchoolPhoto.action" method="post">
		<div class="p_b10 mb10">
				<div class="o-v leave_k">
				<textarea class="area3" name="message.text"></textarea>
				<input type="hidden" name="albumId" value=${albumId}></textarea>
				<input type="hidden" name="message.commentedId" value="<s:property value='photo.id'/>"></textarea>
				<input type="hidden" name="message.type" value=1></textarea>
				<input type="submit" class="btn2 l mt5" value="发 表" name="Submit1">	
				</div>		
		</div>	
		</form>
	</div>		
	<s:action name="comment_list" var="message_list" namespace="/user" executeResult="false" ignoreContextParams="false">
		<s:param name="type" value="1"></s:param>
	</s:action>
	
	<s:iterator value="#message_list.messages.list" var="message">
	<div class="clearfix message_list pr">
		<div class="l w-120 mes_list_l">
		<a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank" class="mt10">
		<img alt="" src="${ctx}/<%=(String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#message.formUser.portrait")%>" width="100" height="100"></a>
		<a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#message.formUser.id"/>" target="_blank"><s:property value="#message.formUser.nickName"/></a>
		</div>
		<div class="mes_list_r">
			<label class="gray"><span class="r"><a href="<%=path%>/user/delSchoolPhotoCommentById.action?message.id=<s:property value="#message.id"/>&albumId=${albumId}&message.commentedId=<s:property value='photo.id'/>" class="m-lr4">删除</a> </span> <s:property value="#message.formUser.nickName"/> <s:date name="#message.createTime" format="yyyy-MM-dd HH:mm:ss" /> 留言</label>
			<p class="block"><s:property value="#message.text"/></p>
				<div class="mes_hf">
				<s:iterator value="#message.sortedChildMsgs" var="childMsg">
					<dl>
					<dt class="l"><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#childMsg.formUser.id"/>" target="_blank" class="a-img1">
                        <img alt="" src="${ctx}/<%=(String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#childMsg.formUser.portrait")%>" width="48" height="48"></a></dt>
					<dd><a href="<%=path%>/user/portal.jsp?hostUserId=<s:property value="#childMsg.formUser.id"/>" target="_blank"><s:property value="#childMsg.formUser.nickName"/>：</a><s:property value="#childMsg.text"/> </dd>
					<dd class="gray mt5"><s:date name="#childMsg.createTime" format="yyyy-MM-dd HH:mm:ss" /> <a href="<%=path%>/user/delSchoolPhotoCommentById.action?message.id=<s:property value="#childMsg.id"/>&albumId=${albumId}&message.commentedId=<s:property value='photo.id'/>">删除</a></dd>
					</dl>
				</s:iterator>
				<form action="<%=path%>/user/commentSchoolPhoto.action">
				<p class="mes_hf_k">
				<textarea class="area3" name="message.text"></textarea>
				<input type="submit" class="btn2 l mt5" value="发 表" name="Submit1">	
				<input type="hidden" name="hostUserId" value="<s:property value="#parameters.hostUserId"/>"/>
				<input type="hidden" name="albumId" value=${albumId}></textarea>
				<input type="hidden" name="message.commentedId" value="<s:property value='photo.id'/>"></textarea>
				<input type="hidden" name="message.type" value=1></textarea>
				<input type="hidden" name="message.parentMsg.id" value="<s:property value="#message.id"/>"/>
				</p>
				</form>
				</div>
		</div>			
	</div>
	</s:iterator>
	
	<s:if test="#message_list.messages.pageString != null && #message_list.messages.pageString != ''">
		<div class="pagenum">
			<s:property value="#message_list.messages.pageString" escape="false"/>
		</div>
		</s:if>

		
	


<script>
function getOffset(evt)
{
  var target = evt.target;
  if (target.offsetLeft == undefined)
  {
    target = target.parentNode;
  }
  var pageCoord = getPageCoord(target);
  var eventCoord =
  { 
    x: window.pageXOffset + evt.clientX,
    y: window.pageYOffset + evt.clientY
  };
  var offset =
  {
    offsetX: eventCoord.x - pageCoord.x,
    offsetY: eventCoord.y - pageCoord.y
  };
  return offset;
}

function getPageCoord(element)
{
  var coord = {x: 0, y: 0};
  while (element)
  {
    coord.x += element.offsetLeft;
    coord.y += element.offsetTop;
    element = element.offsetParent;
  }
  return coord;
}

function upNext(bigimg){  
    var lefturl     = "<s:property value='prePhoto.id'/>";  
    var righturl    = "<s:property value='nextPhoto.id'/>";
    var imgurl = righturl;
    var width   = bigimg.width;  
    var height  = bigimg.height;  
    bigimg.onmousemove=function(ev){  
    	var eva=ev||event;
    	
    	var es=eva.offsetX||getOffset(eva).offsetX;
        if(es<width/2){  
            bigimg.style.cursor = 'url(<%=path%>/images/pre.cur),auto';  
            imgurl              = lefturl;  
        }else if(es>width/2){  
            bigimg.style.cursor = 'url(<%=path%>/images/next.cur),auto';  
            imgurl              = righturl;  
        }  
    }  
    bigimg.onmouseup=function(){  
        top.location= "<%=path%>/user/getSchoolRoundPhoto.action?photoId=" + imgurl + "&albumId=${albumId}&hostUserName=<%=hostUser.getNickName()%>";
    }  
}
</script>

<!--content E -->
<%@ include file="../inc/templateFoot.jsp"%>