<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="l w-190 my_left hei-400">
		<div class="my_info tt">
			
			<div class="chaxun">
					
				<ul>
					<li>
						<a href="<%=path%>/about/about_us.jsp" class="bj13 font-2">关于我们</a>
					</li>

					<li>
						<a href="<%=path%>/about/takejob.jsp" class="bjb font-2">网站招聘</a>
					</li>

					<li>
						<a href="<%=path%>/about/linkus.jsp" class="bje font-2">联系我们</a>
					</li>
				</ul>
				
				<div class="chanxun-bottom" id="chanxun-bottom"></div>
			</div>
			<script>
			//by windbell,need Jquery
			//重置选择的子栏目
			$(function(){
            	var lstr = self.location.href;
				var _menustr = lstr.substring(lstr.lastIndexOf("/")+1,lstr.length);

				$(".chaxun a").each(function(){
											 
											 if($(this).attr("href").indexOf(_menustr)>=0){

												 	$(this).parent().addClass("foucs")

												 }
											 })
					   })
			
//by windbell
//重置chanxun-bottom的高

$(function(){

		   	$("#chanxun-bottom").css("height",$("#_wContent").height()-122);

		   })			
            </script>
		</div>
	</div>