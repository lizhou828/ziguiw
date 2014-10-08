<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="inc/home_head.jsp"%>

<%
SchoolServiceImpl schoolService = (SchoolServiceImpl)BeanFactoryUtils.getBean("schoolService");
School school = null;
if(user.getType() == 1 && user.getForeignKey() != null && user.getForeignKey() > 0){
	school = schoolService.getById(user.getForeignKey());
}

if(school == null){
	school = new School();
}
%>

<div class="content clearfix   pr">
<%@ include file="inc/right.jsp"%>
	<!--ad01 E -->
    <div class="ask_and_sea found w-760 hei-400  r">
       	<h2 class="col-h3">完善学校信息</h2>

		<form method="post" action="<%=path%>/user/fillSchoolInfo.action">
		<ul class="link_gray ul-li-s" name="T_SCHOOLINFO" id="T_SCHOOLINFO">
			<li><span>学校名称:</span><input name="school.sch_name" id="sch_name" type="text" class="inp_txt" value="<%=school.getSch_name() == null ? "" : school.getSch_name()%>"/></li>
			<li><span>联系人:</span><input name="school.linkman" id="linkman" type="text" class="inp_txt" value="<%=school.getLinkman() == null ? "" : school.getLinkman()%>"/></li>
			<li><span>联系电话:</span><input name="school.contactphone" id="contactphone" type="text" class="inp_txt" value="<%=school.getContactphone() == null ? "" : school.getContactphone()%>" /></li>
			<li><span>省份:</span><select name="school.ProId" id="ProId" type="text" class="inp_txt" onchange="select()" style="height:30px">
				<option value="1">北京</option>
　　　　　　　　　　　　　　　　　　　　<option value="2">上海</option>
　　　　　　　　　　　　　　　　　　　　<option value="3">天津</option>
　　　　　　　　　　　　　　　　　　　　<option value="4">重庆</option>
　　　　　　　　　　　　　　　　　　　　<option value="5">河北</option>
　　　　　　　　　　　　　　　　　　　　<option value="6">山西</option>
　　　　　　　　　　　　　　　　　　　　<option value="7">内蒙古</option>
　　　　　　　　　　　　　　　　　　　　<option value="8">辽宁</option>
　　　　　　　　　　　　　　　　　　　　<option value="9">吉林</option>
　　　　　　　　　　　　　　　　　　　　<option value="10">黑龙江</option>
　　　　　　　　　　　　　　　　　　　　<option value="11">江苏</option>
　　　　　　　　　　　　　　　　　　　　<option value="12">浙江</option>
　　　　　　　　　　　　　　　　　　　　<option value="13">安徽</option>
　　　　　　　　　　　　　　　　　　　　<option value="14">福建</option>
　　　　　　　　　　　　　　　　　　　　<option value="15">江西</option>
　　　　　　　　　　　　　　　　　　　　<option value="16">山东</option>
　　　　　　　　　　　　　　　　　　　　<option value="17">河南</option>
　　　　　　　　　　　　　　　　　　　　<option value="18">湖北</option>
　　　　　　　　　　　　　　　　　　　　<option value="19">湖南</option>
　　　　　　　　　　　　　　　　　　　　<option value="20">广东</option>
　　　　　　　　　　　　　　　　　　　　<option value="21">广西</option>
　　　　　　　　　　　　　　　　　　　　<option value="22">海南</option>
　　　　　　　　　　　　　　　　　　　　<option value="23">四川</option>
　　　　　　　　　　　　　　　　　　　　<option value="24">贵州</option>
　　　　　　　　　　　　　　　　　　　　<option value="25">云南</option>
　　　　　　　　　　　　　　　　　　　　<option value="26">西藏</option>
　　　　　　　　　　　　　　　　　　　　<option value="27">陕西</option>
　　　　　　　　　　　　　　　　　　　　<option value="28">甘肃</option>
　　　　　　　　　　　　　　　　　　　　<option value="29">宁夏</option>
　　　　　　　　　　　　　　　　　　　　<option value="30">青海</option>
　　　　　　　　　　　　　　　　　　　　<option value="31">新疆</option>
　　　　　　　　　　　　　　　　　　　　<option value="32">香港</option>
　　　　　　　　　　　　　　　　　　　　<option value="33">澳门</option>
　　　　　　　　　　　　　　　　　　　　<option value="34">台湾</option>
			</select></li>		
			<li><span>城市:</span><select name="school.CityId" id="CityId" type="text" class="inp_txt" style="height:30px">
			
			</select>
			</li>
			<li><span>备注:</span><input name="school.reamrks" id="reamrks" type="text" class="inp_txt" value="<%=school.getReamrks() == null ? "" : school.getReamrks()%>"/></li>								
			<input name="school.xx_ID" type="hidden" value="<%=school.getXx_ID() == 0 ? "" : school.getXx_ID()%>"/>
			<li><input name="submit" type="submit" value="确认修改" class="btn2"/></li>	
				
		</ul>
		</form>
    </div>
  
</div>    
<!--content E -->
<script type="text/javascript">
function initSelect(){
	var sex = document.getElementById("sex");
	var province = document.getElementById("province");

	var oldSex = document.getElementById("oldSex").value;
	var oldProvince = document.getElementById("oldProvince").value;
	
	
    for(var i=0;i<sex.options.length;i++)
    {
        if(sex.options[i].value == oldSex)
        {
        	sex.options[i].selected = true;
            break;
        }
    }
    
    for(var i=0;i<province.options.length;i++)
    {
        if(province.options[i].value == oldProvince && oldProvince != "")
        {
        	province.options[i].selected = true;
        	break;
        }
    }
    
    
    
    
}

initSelect();

var x = [35]; 
x[0]="" ;
x[1]="北京,东城,西城,崇文,宣武,朝阳,丰台,石景山,海淀,门头沟,房山,通州,顺义,昌平,大兴,平谷,怀柔,密云,延庆" ;
x[2]="上海,黄浦,卢湾,徐汇,长宁,静安,普陀,闸北,虹口,杨浦,闵行,宝山,嘉定,浦东,金山,松江,青浦,南汇,奉贤,崇明" ;
x[3]="天津,和平,东丽,河东,西青,河西,津南,南开,北辰,河北,武清,红挢,塘沽,汉沽,大港,宁河,静海,宝坻,蓟县,大邱庄"; 
x[4]="重庆,万州,涪陵,渝中,大渡口,江北,沙坪坝,九龙坡,南岸,北碚,万盛,双挢,渝北,巴南,黔江,长寿,綦江,潼南,铜梁,大足,荣昌,壁山,梁平,城口,丰都,垫江,武隆,忠县,开县,云阳,奉节,巫山,巫溪,石柱,秀山,酉阳,彭水,江津,合川,永川,南川"; 
x[5]="石家庄,邯郸,邢台,保定,张家口,承德,廊坊,唐山,秦皇岛,沧州,衡水"; 
x[6]="太原,大同,阳泉,长治,晋城,朔州,吕梁,忻州,晋中,临汾,运城"; 
x[7]="呼和浩特,包头,乌海,赤峰,呼伦贝尔盟,阿拉善盟,哲里木盟,兴安盟,乌兰察布盟,锡林郭勒盟,巴彦淖尔盟,伊克昭盟" ;
x[8]="沈阳,大连,鞍山,抚顺,本溪,丹东,锦州,营口,阜新,辽阳,盘锦,铁岭,朝阳,葫芦岛" ;
x[9]="长春,吉林,四平,辽源,通化,白山,松原,白城,延边" ;
x[10]="哈尔滨,齐齐哈尔,牡丹江,佳木斯,大庆,绥化,鹤岗,鸡西,黑河,双鸭山,伊春,七台河,大兴安岭" ;
x[11]="南京,镇江,苏州,南通,扬州,盐城,徐州,连云港,常州,无锡,宿迁,泰州,淮安" ;
x[12]="杭州,宁波,温州,嘉兴,湖州,绍兴,金华,衢州,舟山,台州,丽水" ;
x[13]="合肥,芜湖,蚌埠,马鞍山,淮北,铜陵,安庆,黄山,滁州,宿州,池州,淮南,巢湖,阜阳,六安,宣城,亳州" ;
x[14]="福州,厦门,莆田,三明,泉州,漳州,南平,龙岩,宁德" ;
x[15]="南昌市,景德镇,九江,鹰潭,萍乡,新馀,赣州,吉安,宜春,抚州,上饶" ;
x[16]="济南,青岛,淄博,枣庄,东营,烟台,潍坊,济宁,泰安,威海,日照,莱芜,临沂,德州,聊城,滨州,菏泽,博兴" ;
x[17]="郑州,开封,洛阳,平顶山,安阳,鹤壁,新乡,焦作,濮阳,许昌,漯河,三门峡,南阳,商丘,信阳,周口,驻马店,济源" ;
x[18]="武汉,宜昌,荆州,襄樊,黄石,荆门,黄冈,十堰,恩施,潜江,天门,仙桃,随州,咸宁,孝感,鄂州" ;
x[19]="长沙,常德,株洲,湘潭,衡阳,岳阳,邵阳,益阳,娄底,怀化,郴州,永州,湘西,张家界" ;
x[20]="广州,深圳,珠海,汕头,东莞,中山,佛山,韶关,江门,湛江,茂名,肇庆,惠州,梅州,汕尾,河源,阳江,清远,潮州,揭阳,云浮" ;
x[21]="南宁,柳州,桂林,梧州,北海,防城港,钦州,贵港,玉林,南宁地区,柳州地区,贺州,百色,河池" ;
x[22]="海口,三亚" ;
x[23]="成都,绵阳,德阳,自贡,攀枝花,广元,内江,乐山,南充,宜宾,广安,达川,雅安,眉山,甘孜,凉山,泸州" ;
x[24]="贵阳,六盘水,遵义,安顺,铜仁,黔西南,毕节,黔东南,黔南" ;
x[25]="昆明,大理,曲靖,玉溪,昭通,楚雄,红河,文山,思茅,西双版纳,保山,德宏,丽江,怒江,迪庆,临沧" ;
x[26]="拉萨,日喀则,山南,林芝,昌都,阿里,那曲" ;
x[27]="西安,宝鸡,咸阳,铜川,渭南,延安,榆林,汉中,安康,商洛" ;
x[28]="兰州,嘉峪关,金昌,白银,天水,酒泉,张掖,武威,定西,陇南,平凉,庆阳,临夏,甘南" ;
x[29]="银川,石嘴山,吴忠,固原" ;
x[30]="西宁,海东,海南,海北,黄南,玉树,果洛,海西" ;
x[31]="乌鲁木齐,石河子,克拉玛依,伊犁,巴音郭勒,昌吉,克孜勒苏柯尔克孜,博 尔塔拉,吐鲁番,哈密,喀什,和田,阿克苏" ;
x[32]="香港," ;
x[33]="澳门," ;
x[34]="台北,高雄,台中,台南,屏东,南投,云林,新竹,彰化,苗栗,嘉义,花莲,桃园,宜兰,基隆,台东,金门,马祖,澎湖" ;

$('#ProId').change(function(){
	var citys=x[$('#ProId').val()].split(",");
	for(var i=0;i<citys.length;i++){
		$('#CityId').options[i]=new Option($('#ProId').val() + "000" + i,citys[i]);
	}
});

</script>

<%@ include file="../inc/templateFoot.jsp"%>