<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.zigui.utils.ImageUtils"%>
<%@ include file="../inc/templateHead.jsp"%>
<!--header E -->

<div class="content clearfix">
	<div class="ad01">
		<a href="#" title=""><img src="<%=path%>/images/adv01.jpg" alt=""/></a>
	</div>
	<!--ad01 E -->
	
   <div class="l mod01 w-295">
				
				<div class="lunbo pr lunbo_bbs sub_box_3">
				                 <div id="p-select" class="sub_nav_3">
					                    <div class="sub_no" id="bd1lfsj">
					                        <ul>
					                            <li class="show">1</li><li class="">2</li><li class="">3</li><li class="">4</li><li class="">5</li>
					                        </ul>
					                    </div>
					                </div>
								<div id="bd1lfimg_3">					
					                    <div>					
					                        <dl class="show"></dl>
					                       <s:action var="pptForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
										      <s:param name="query.boardid" value="2"></s:param>
										      <s:param name="query.isnew" value="1"></s:param>
										      <s:param name="countPerPage" value="5"></s:param>
										      <s:param name="currentPage" value="1"></s:param>
										      <s:param name="query.state" value="1"></s:param>
										      <s:param name="query.regionId" value="54"></s:param>
									        </s:action>
									        <s:iterator value="#pptForums.forums.list" var="forum">
					                        <dl class="">												
					                            <dt><a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" title="" target="_blank"><img style="width:300px;height:230px" src="<s:property value="#forum.firstImage"/>" alt="<s:property value="#forum.title"/>"></a></dt>					
					                            <dd>					
					                                <h4 class="fyahei"><a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" target="_blank"><s:property value="#forum.title"/></a></h4>                                					
					                            </dd>					
					                            <dd class="bg_lunbo_3"></dd>					
					                        </dl>
					                        </s:iterator>	
					                    	
					                	</div>					
					                </div>
					              
					                <script type="text/javascript">movec_3();</script>
								
				</div>			                              					                
				<div class="sq_nav mt10">
								<h2 class="sq_nav_h2"></h2>								
								<ul class="newList02 red_f63">
								<s:action var="tongling" name="board_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
							      <s:param name="query.parentBoardId" value="2"></s:param>
							      <s:param name="query.state" value="1"></s:param>
						        </s:action>
								<li><a target="_blank" title="" href="<%=path%>/community/age/list" class="fb red_f63">同龄圈</a> -
								<s:iterator value="#tongling.boards.list" var="board">
								<s:if test="#board.id!=#board.parentBoard.id">
								<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#board.id"/>" class="m-l4"><s:property value="#board.boardname"/></a>
								</s:if>
								</s:iterator>
								</li>
								
								<s:action var="tongcheng" name="board_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
							      <s:param name="query.parentBoardId" value="3"></s:param>
							      <s:param name="query.state" value="1"></s:param>
						        </s:action>
								<li><a target="_blank" title="" href="<%=path%>/community/city/list" class="fb red_f63">同城圈</a> -
								<s:iterator value="#tongcheng.boards.list" var="board">
								<s:if test="#board.id!=#board.parentBoard.id">
								<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#board.id"/>" class="m-l4"><s:property value="#board.boardname"/></a>
								</s:if>
								</s:iterator>
								</li>
								
								<s:action var="jiaoliu" name="board_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
							      <s:param name="query.parentBoardId" value="4"></s:param>
							      <s:param name="query.state" value="1"></s:param>
						        </s:action>
								<li><a target="_blank" title="" href="<%=path%>/community/jiaoliu/list" class="fb red_f63">学习交流</a> -
								<s:iterator value="#jiaoliu.boards.list" var="board">
								<s:if test="#board.id!=#board.parentBoard.id">
								<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#board.id"/>" class="m-l4"><s:property value="#board.boardname"/></a>
								</s:if>
								</s:iterator>
								</li>
								
								<s:action var="zhuanjia" name="board_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
							      <s:param name="query.parentBoardId" value="5"></s:param>
							      <s:param name="query.state" value="1"></s:param>
						        </s:action>
								<li><a target="_blank" title="" href="<%=path%>/community/zhuanjia/list" class="fb red_f63">专家问答</a> -
								
								<s:iterator value="#zhuanjia.boards.list" var="board">
								<s:if test="#board.id!=#board.parentBoard.id">
								<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#board.id"/>" class="m-l4"><s:property value="#board.boardname"/></a>
								</s:if>
								</s:iterator>
								</li>
								
								<s:action var="aixin" name="board_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
							      <s:param name="query.parentBoardId" value="5"></s:param>
							      <s:param name="query.state" value="1"></s:param>
						        </s:action>
							</ul>	
			</div>
		</div>	
		<div class="r w-650 box09">
				<div class="box09_hd sq_dting">
				 <script language='javascript'>
				  var temp = "tabid2";
				  var temptabnav = "tab2";
				  function changeTab(n){
				      var tabnav = "tab"+n;
				      var tabid = "tabid"+n;
				      if(temp != tabid){
				        clearDislay(temp,temptabnav);
				        temp =tabid;
				        temptabnav = tabnav;
				    }
				      document.getElementById(tabid).style.display="block";
				      document.getElementById(tabnav).setAttribute('class','on');
				  };
				  function clearDislay(tab,tabnav){
				      var clearTabid = tab;
				      var clearTabnav = tabnav;
				      document.getElementById(clearTabid).style.display = "none";
				      document.getElementById(clearTabnav).removeAttribute('class');
				  }
				  </script>
								<h2>社区大厅</h2>
								<div class="pubMore07">
												<a id="tab2" title="同龄圈" href="<%=path%>/community/age/list" class="on" onmouseover='changeTab(2)'>同龄圈</a>
												
												<a id="tab3" title="同城圈" href="<%=path%>/community/city/list" onmouseover='changeTab(3)'>同城圈</a>
												
												<a id="tab4" title="学习交流" href="<%=path%>/community/jiaoliu/list" onmouseover='changeTab(4)'>学习交流</a>
												
												<a id="tab5" title="专家问答" href="<%=path%>/community/zhuanjia/list" onmouseover='changeTab(5)'>专家问答</a>
												
								</div>
				</div>
				<div class="box09_bd" id='tabid2' >
				<ul class="newList03">
				<s:action var="tonglingForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="2"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="14"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="51"></s:param>
		        </s:action>
		        <s:iterator value="#tonglingForums.forums.list" var="forum"><li><span>【<a href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>" target="_blank"><s:property value="#forum.board.boardname"/></a>】</span><a href="<%=path%>/community/age/detail/<s:property value="#forum.id"/>" target="_blank">
		             <s:if test='#forum.title.length()<=13'>
			          <s:property value="#forum.title"/>
			          </s:if>
			          <s:else>
			          <s:property value="#forum.title.substring(0,13)"/>...
			         </s:else>
		        </a></li></s:iterator>
				</ul>
				</div>
				
					<div class="box09_bd" id='tabid3' style='display:none;'>
				<ul class="newList03">
				<s:action var="tongchengForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="3"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="14"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="51"></s:param>
		        </s:action>
		        <s:iterator value="#tongchengForums.forums.list" var="forum"><li><span>【<a href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>" target="_blank"><s:property value="#forum.board.boardname"/></a>】</span><a href="<%=path%>/community/city/detail/<s:property value="#forum.id"/>" target="_blank">
		         <s:if test='#forum.title.length()<=13'>
		          <s:property value="#forum.title"/>
		          </s:if>
		          <s:else>
		          <s:property value="#forum.title.substring(0,13)"/>...
		         </s:else>
		        </a></li></s:iterator>
				</ul>
				</div>
				
					<div class="box09_bd" id='tabid4' style='display:none;'>
				<ul class="newList03">
				<s:action var="jiaoliuForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="4"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="14"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="51"></s:param>
		        </s:action>
		        <s:iterator value="#jiaoliuForums.forums.list" var="forum"><li><span>【<a href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>" target="_blank"><s:property value="#forum.board.boardname"/></a>】</span><a href="<%=path%>/community/jiaoliu/detail/<s:property value="#forum.id"/>" target="_blank">
		         <s:if test='#forum.title.length()<=13'>
			          <s:property value="#forum.title"/>
			          </s:if>
			          <s:else>
			          <s:property value="#forum.title.substring(0,13)"/>...
			      </s:else>
		        </a></li></s:iterator>
				</ul>
				</div>
				
					<div class="box09_bd" id='tabid5' style='display:none;'>
				<ul class="newList03">
				<s:action var="zhuanjiaForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="5"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="14"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="51"></s:param>
		        </s:action>
		        <s:iterator value="#zhuanjiaForums.forums.list" var="forum"><li><span>【<a href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>" target="_blank"><s:property value="#forum.board.boardname"/></a>】</span><a href="<%=path%>/community/zhuanjia/detail/<s:property value="#forum.id"/>" target="_blank">
		         <s:if test='#forum.title.length()<=13'>
		          <s:property value="#forum.title"/>
		          </s:if>
		          <s:else>
		          <s:property value="#forum.title.substring(0,13)"/>...
		         </s:else>
		        </a></li></s:iterator>
				</ul>
				</div>
				
					<div class="box09_bd" id='tabid1' style='display:none;'>
				<ul class="newList03">
				<s:action var="aixinForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="1"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="14"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="51"></s:param>
		        </s:action>
		        <s:iterator value="#aixinForums.forums.list" var="forum"><li><span>【<a href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>" target="_blank"><s:property value="#forum.board.boardname"/></a>】</span><a href="<%=path%>/community/aixin/detail/<s:property value="#forum.id"/>" target="_blank">
		         <s:if test='#forum.title.length()<=13'>
		          <s:property value="#forum.title"/>
		          </s:if>
		          <s:else>
		          <s:property value="#forum.title.substring(0,13)"/>...
		         </s:else>
		        </a></li></s:iterator>
				</ul>
				</div>
       </div>
       <!-- datingend -->
       <div class="r box09 mt10">
		<div class="box09_hd">
				<h2>活跃分子</h2>			
		</div>
		<div class="box09_bd hyfz">
		<s:action var="mingxing103Users" name="user_listByCondition" namespace="/user" executeResult="false" ignoreContextParams="false">
	      <s:param name="countPerPage" value="3"></s:param>
	      <s:param name="currentPage" value="1"></s:param>
	      <s:param name="query.state" value="1"></s:param>
	      <s:param name="query.regionId" value="103"></s:param>
	      <s:param name="orderField" value="regionTime"></s:param>
        </s:action>
        <s:iterator value="#mingxing103Users.users.list" var="user">
        <dl>
		<dd><a href="<%=path%>/myhome/<s:property value="#user.nickName"/>" target="_blank" class="a-img1 l"><img alt="" src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#user.portrait"), path + "/images/head.jpg", 70)%>" width=99 height=100/></a></dd>
		<dt><s:property value="#user.nickName"/></dt>
		<dd><p><s:property value="#user.signature"/></p></dd>
		</dl>
        </s:iterator>
	</div>				
	</div>	
<div class="ad01">
<a href="#" title=""><img src="<%=path%>/images/adv03.jpg" alt=""/></a>
</div>
<div class="clearfix">
	<div class="l w-690">
		<div class="l w-340">
				            <div class="h3titc">
				                <h3 class="pr fyahei">同龄圈</h3>
				                <ul>
				                    <li class="bordno"><a href="<%=path%>/community/age/list">进入圈子</a></li>
				                    <li><a href="<%=path%>/page.action?templateName=createForum.ftl&boardId=2">发表主题</a></li>
				                </ul>
				            </div>
				<div class="borda bbs_col">
			
				<ul class="newList03">
				<s:action var="tongling52Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="2"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="8"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="52"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#tongling52Forums.forums.list" var="forum"><li><span>[<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>"><s:property value="#forum.board.boardname"/></a>]</span><a href="<%=path%>/community/age/detail/<s:property value="#forum.id"/>" target="_blank">
		         <s:if test='#forum.title.length()<=13'>
			          <s:property value="#forum.title"/>
			          </s:if>
			          <s:else>
			          <s:property value="#forum.title.substring(0,13)"/>...
			         </s:else>
		        </a></li></s:iterator>
				</ul>				         
				</div>			       
		</div>
		
		<div class="r w-340">
				<div class="h3titc">
				<h3 class="pr fyahei">同城圈</h3>
				<ul>
				<li class="bordno"><a href="<%=path%>/community/city/list">进入圈子</a></li>
				<li><a href="<%=path%>/page.action?templateName=createForum.ftl&boardId=3">发表主题</a></li>
				</ul>
				</div>
				<div class="borda bbs_col">
				
				<ul class="newList03">
				<s:action var="tongcheng52Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="3"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="8"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="52"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#tongcheng52Forums.forums.list" var="forum"><li><span>[<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>"><s:property value="#forum.board.boardname"/></a>]</span><a href="<%=path%>/community/city/detail/<s:property value="#forum.id"/>" target="_blank">
		         <s:if test='#forum.title.length()<=13'>
			          <s:property value="#forum.title"/>
			          </s:if>
			          <s:else>
			          <s:property value="#forum.title.substring(0,13)"/>...
			         </s:else>
		        </a></li></s:iterator>
				</ul>					         
				</div>

		</div>	
<div class="ad01 mt10 l">
<a href="#" title=""><img src="<%=path%>/images/community_ad2.jpg" alt=""/></a>
</div>
		<div class="l w-340">
				            <div class="h3titc">
				                <h3 class="pr fyahei">学习交流</h3>
				                <ul>
				                    <li class="bordno"><a href="<%=path%>/community/jiaoliu/list">进入圈子</a></li>
				                    <li><a href="<%=path%>/page.action?templateName=createForum.ftl&boardId=4">发表主题</a></li>
				                </ul>
				            </div>
				<div class="borda bbs_col">
			
				<ul class="newList03">
				<s:action var="jiaoliu52Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="4"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="8"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="52"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#jiaoliu52Forums.forums.list" var="forum"><li><span>[<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>"><s:property value="#forum.board.boardname"/></a>]</span><a href="<%=path%>/community/jiaoliu/detail/<s:property value="#forum.id"/>" target="_blank">
		         <s:if test='#forum.title.length()<=13'>
			          <s:property value="#forum.title"/>
			          </s:if>
			          <s:else>
			          <s:property value="#forum.title.substring(0,13)"/>...
			         </s:else>
		        </a></li></s:iterator>
				</ul>					         
				</div>
				       
		</div>
		<div class="r w-340">
				<div class="h3titc">
				<h3 class="pr fyahei">专家问答</h3>
				<ul>
				<li class="bordno"><a href="<%=path%>/community/zhuanjia/list">进入圈子</a></li>
				<li><a href="<%=path%>/page.action?templateName=createForum.ftl&boardId=5">发表主题</a></li>
				</ul>
				</div>
				<div class="borda bbs_col">
			
					<ul class="newList03">
				<s:action var="zhuanjia52Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="5"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="8"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="52"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#zhuanjia52Forums.forums.list" var="forum"><li><span>[<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>"><s:property value="#forum.board.boardname"/></a>]</span><a href="<%=path%>/community/zhuanjia/detail/<s:property value="#forum.id"/>" target="_blank">
		         <s:if test='#forum.title.length()<=13'>
			          <s:property value="#forum.title"/>
			          </s:if>
			          <s:else>
			          <s:property value="#forum.title.substring(0,13)"/>...
			         </s:else>
		        </a></li></s:iterator>
				</ul>				         
				</div>

		</div>	


</div>		
	<div class="r  w-270">
				<div class="h3titc2">
				<h3 class="fyahei">最新话题</h3>
				</div>
				<div class="borda09">
				<ul class="newList09">
				<s:action var="newForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="11"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#newForums.forums.list" var="forum">
		         <li><span>[<a target="_blank" title="" href="<%=path%>/forum/forumList.action?boardId=<s:property value="#forum.board.id"/>"><s:property value="#forum.board.boardname"/></a>]</span><a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" target="_blank">
		          <s:if test='#forum.title.length()<=13'>
			          <s:property value="#forum.title"/>
			          </s:if>
			          <s:else>
			          <s:property value="#forum.title.substring(0,13)"/>...
			         </s:else>
		         </a></li>
		        </s:iterator>
				</ul>	
	            </div>	


<div class="ad01 mt10">
<a href="#" title=""><img src="<%=path%>/images/community_ad.jpg" alt=""/></a>
</div>
				<div class="h3titc2">
				<h3 class="fyahei">话题排行</h3>
				</div>
				<div class="ph borda09">
				<ul class="">
				<s:action var="hotForums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="10"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="53"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#hotForums.forums.list" var="forum"><li><a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" target="_blank"><s:property value="#forum.title"/></a></li></s:iterator>
				</ul>	
	            </div>	
	            	
</div>	
</div>	
<!--
<div class="clearfix borda mt10 axq">    
				<div class="h3titc">
				<h3 class="pr fyahei">爱心站</h3>
				</div>
        <div class="fl w-690">
        <s:action var="aixin55Forums" name="forum_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.boardid" value="1"></s:param>
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="2"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="query.regionId" value="55"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#aixin55Forums.forums.list" var="forum">
		           <dl class="zthd">
			        <dt><span>主题活动</span><s:property value="#forum.title"/></dt>
			        <dd><p><s:property value="#forum.autoDescription"/><a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" target="_blank">查看详细</a></p></dd>
			        <dd class="a-r"><label>已有<b class="red_f63"><s:property value="#forum.renum"/></b>  人献出了自己的爱心，还等什么，赶紧加入吧！</label><a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" target="_blank" class="wyjz">我要捐助</a><a href="<%=path%>/community/detail/<s:property value="#forum.id"/>" target="_blank" class="lyzx">留言咨询</a></dd>
			       </dl>
		        </s:iterator>
        </div>    
        <div class="know_rd2 r">
             <div class="axb">
             <h3 class="col-h4"><span>爱心榜</span></h3>
             
             <ul>
             <s:action var="loves" name="love_listByCondition" namespace="/forum" executeResult="false" ignoreContextParams="false">
			      <s:param name="query.isnew" value="1"></s:param>
			      <s:param name="countPerPage" value="10"></s:param>
			      <s:param name="currentPage" value="1"></s:param>
			      <s:param name="query.state" value="1"></s:param>
			      <s:param name="orderField" value="createTime"></s:param>
		        </s:action>
		        <s:iterator value="#loves.pagedLoves.list" var="love">
		        <li><a href="<%=path%>/myhome/<s:property value="#love.donateUser.nickName"/>" target="_blank" class="a-img1"><img src="<%=ImageUtils.getSizedImage((String)((com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack")).findValue("#love.donateUser.portrait"), path + "/images/head.jpg", 50)%>" width=48 height=48/></a><a href="<%=path%>/myhome/<s:property value="#love.donateUser.nickName"/>" target="_blank"><s:property value="#love.donateUser.nickName"/></a></li>
		        </s:iterator>            
             </ul>

             </div>
    
          
    
        </div>    
</div>
-->
	
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
