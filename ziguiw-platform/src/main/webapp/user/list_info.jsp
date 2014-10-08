<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>  

<div class="content clearfix  pr">
	
<div class="   hei-400">

	<div class="my_home">
	<div class="h3titb">
		<h3 class="fyahei">列表信息</h3>
	</div>
	<div class="bordb dygs wdxc p_b10 mb10">
		<table id="strudent_base_info" class="table">
			<thead>
			<tr>
				<td width="5%">姓名</td>
				<td>性别</td>
				<td>出生日期</td>
				<td>民族</td>
				<td>政治面貌</td>
				<td>身份证号</td>
				<td>所在学校</td>
				<td>年级</td>
				<td>班级</td>
				<td>班主任</td>
				<td>学号</td>
				<td>入学日期</td>
				<td>寄宿情况</td>
				<td>绑定手机</td>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td>test1</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
				<tr>
					<td>test2</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				</tr>
			</tbody>
		</table>
		
		<table id="strudent_family_info"  class="table">
			<thead>
			<tr>
				<td>家长姓名</td>
				<td>成员关系</td>
				<td>性别</td>
				<td>所在省</td>
				<td>市</td>
				<td>家庭住址</td>
				<td>家庭电话</td>
				<td>家庭邮编</td>
				<td>所在学校</td>
				<td>联系方式</td>
				<td>家长邮箱</td>
				<td>手机号码</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
						
		<table id="strudent_attendance_info"  class="table">
			<thead>
			<tr>
				<td>日期</td>
				<td>考勤项目</td>
				<td>考勤时段</td>
				<td>刷卡时间</td>
				<td>状态判定</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="strudent_homework_info"  class="table">
			<thead>
			<tr>
				<td>日期</td>
				<td>星期</td>
				<td>科目</td>
				<td>作业类别</td>
				<td>分数</td>
				<td>备注</td>
				<td>教师</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="strudent_exam_info"  class="table">
			<thead>
			<tr>
				<td>考试名称</td>
				<td>考试时间</td>
				<td>考试科目</td>
				<td>分数</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="teacher_message"  class="table">
			<thead>
			<tr>
				<td>日期</td>
				<td>星期</td>
				<td>留言类型</td>
				<td>留言内容</td>
				<td>老师</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="class_notify"  class="table">
			<thead>
			<tr>
				<td>日期</td>
				<td>通知类型</td>
				<td>标题</td>
				<td>内容</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="school_notice"  class="table">
			<thead>
			<tr>
				<td>日期</td>
				<td>公告类型</td>
				<td>标题</td>
				<td>内容</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		
		<table id="school_notice"  class="table">
			<thead>
			<tr>
				<td>日期</td>
				<td>公告类型</td>
				<td>标题</td>
				<td>内容</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="student_consume_info"  class="table">
			<thead>
			<tr>
				<td>姓名</td>
				<td>卡号</td>
				<td>消费日期</td>
				<td>星期</td>
				<td>消费金额</td>
				<td>缴费记录</td>
				<td>余额</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="student_health_info"  class="table">
			<thead>
			<tr>
				<td>姓名</td>
				<td>健康状况</td>
				<td>血型</td>
				<td>医保号</td>
				<td>既往史</td>
				<td>过敏史</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="student_grow_trace"  class="table">
			<thead>
			<tr>
				<td>姓名</td>
				<td>日期</td>
				<td>星期</td>
				<td>活动名称</td>
				<td>活动内容</td>
				<td>特长/爱好</td>
				<td>作品展示</td>
				<td>形象展示</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="student_award_info"  class="table">
			<thead>
			<tr>
				<td>姓名</td>
				<td>日期</td>
				<td>星期</td>
				<td>奖项</td>
				<td>级别</td>
				<td>受奖人或学校</td>
				<td>评语</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
		<table id="school_teacher_info"  class="table">
			<thead>
			<tr>
				<td>老师姓名</td>
				<td>照片</td>
				<td>年/班级</td>
				<td>科目</td>
				<td>所在省份/城市</td>
				<td>职位</td>
				<td>老师情况</td>
				<td>登录帐号</td>
				<td>密码</td>
				<td>展示设定</td>
			</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		
	</div>
	</div>
	
  </div>

</div>
<!--content E -->
<script>
$(document).ready(function(){
    var tdNods = $("td");
    tdNods.click(tdClick);
    
    $('thead td').unbind("click");
});
//td点击触发的函数
function tdClick(){
         //1.取出当前td里面的文本内容
         var td = $(this);
         var tdText = td.text();
        //2.清空td里面的文本内容
         td.html(""); //也可以使用td.empty();
        //3.建立一个输入框，也就是input标签
          var input = $("<input>");
        //4.将输入框的内容设为刚才保存的td里面的文本内容
          input.attr("value",tdText);
         //4.5.让文本框能够响应键盘按下的keyup事件，主要是用于处理回车确认
           input.keyup(function(event){
               //1.获取当前用户按下的键值
                  //解决不同浏览器获得事件对象的差异,
                 // IE用自动提供window.event，而其他浏览器必须显示的提供，即在方法参数中加上event
               var myEvent = event || window.event;
               var keyCode = myEvent.keyCode;
               //2.判断是否是回车按下
               if(keyCode == 13){
                   //2.保存当前输入框的内容
                    var input = $(this);
                    var inputText = input.val();//这个地方不能用text(),而是用val()
                   //3.清空td的内容,即去掉输入框
                   var td = input.parent("td");
                   td.empty();
                   //4.将保存的文本内容填充到td中去
                   td.html(inputText);
                   //5.让td重新拥有点击事件
                   td.click(tdClick);
               }
           });
         
           input.blur(function(){
        	   var input = $(this);
               var inputText = input.val();//这个地方不能用text(),而是用val()
              //3.清空td的内容,即去掉输入框
              var td = input.parent("td");
              td.empty();
              //4.将保存的文本内容填充到td中去
              td.html(inputText);
              //5.让td重新拥有点击事件
              td.click(tdClick);
        	  }); 
        //5.将输入框加到td中
          td.append(input);  //也可以用input.appendTo(td);
        //5.5 让文本框中的文字被高亮选中
        //需要将jquery对象转化为DOM对象
         var inputDom = input.get(0);
         inputDom.select();
        //6.需要移除td上的点击事件
        td.unbind("click");
};
</script>

<%@ include file="../inc/templateFoot.jsp"%>