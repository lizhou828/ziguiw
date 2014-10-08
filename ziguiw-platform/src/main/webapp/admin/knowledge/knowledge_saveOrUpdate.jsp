<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String path = request.getContextPath(); 
String currentUrl = request.getRequestURL().toString();
String queryString = request.getQueryString() == null ? "1=1" : request.getQueryString();
queryString = queryString.replace("currentPage=", "");
queryString = queryString.replace("orderField=", "");
queryString = queryString.replace("orderType=", "");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="<%=path%>/css/base-min.css" rel="stylesheet"/>
<link href="<%=path%>/css/editor-pkg-min-datauri.css" rel="stylesheet"/>
<link href="<%=path%>/css/admin.css" type="text/css" rel="stylesheet" media="all"/>
<title>子贵网CMS</title>
</head>
<body>
<%@ include file="../inc/left.jsp"%>
<div class="right">

<%@ include file="/admin/inc/navigation.jsp"%>

<p class="map">知识：新增知识</p>
<s:fielderror/>

<form method="post" action="<%=path%>/admin/knowledge/knowledge_saveOrUpdate.action">
<textarea id="content"
                  name="knowledge.content"
                  tabindex="0"
                  style="width:1100;height:400px;margin:0 auto;">${knowledge.content}
    </textarea>
<table class="table">
	<colgroup>
		<col  style="width:720px;"></col>
		<col></col>
	</colgroup>
	<tr>
		<td><span>知识标题</span>
			<input name="knowledge.title" type="text" class="inp_txt" value="${knowledge.title}"  maxlength="30"/>
		</td>
		<td class="v-b" ><span class="red_star">*</span>知识标题</td>
	</tr>
	<tr>
		<td><span>知识副标题</span>
			<input name="knowledge.subtitle" type="text" class="inp_txt" value="${knowledge.subtitle}"  maxlength="30"/>
		</td>
		<td class="v-b" >知识副标题</td>
	</tr>
	<tr>
		<td><span>关键词</span>
			<input name="knowledge.keywords" type="text" class="inp_txt" value="${knowledge.keywords}" />
		</td>
		<td class="v-b" >关键词，从知识内容中提炼</td>
	</tr>
	
	<tr>
		<td><span>责任编辑</span>
			<input name="knowledge.editors" type="text" class="inp_txt" value="${knowledge.editors}" />
		</td>
		<td class="v-b" ><span class="red_star">*</span>责任编辑</td>
	</tr>

	<tr>
		<td><span>知识首图</span>
			<input name="knowledge.firstImage" type="text" class="inp_txt" value="${knowledge.firstImage}"  maxlength="64"/>
		</td>
		<td class="v-b" >知识的第一张图片，在显示上有特殊处理，如果为空默认用知识内容的第一张图片</td>
	</tr>
	
	<tr>
		<td><span>知识PV</span>
			<input name="knowledge.clickCount" type="text" class="inp_txt" value="${knowledge.clickCount}" />
		</td>
		<td class="v-b" >知识PV</td>
	</tr>
	
	<tr>
		<td><span>描述</span>
			<textarea name="knowledge.description" type="text" class="textarea" value="" cols="10" rows="5">${knowledge.description}</textarea>
		</td>
		<td class="v-b" >知识描述，如果为空的话自动会从知识中截取前200字</td>
	</tr>
	
	<tr>
		<td><span>知识类别</span>
			<select name='knowledge.classType' id="classType" class="inp_select">
			    <option selected="selected" value="2">转载</option>
			    <option value="1">原创</option>
			    
			</select>
		</td>
		<td class="v-b" >知识类别</td>
	</tr>
	
	<tr>
		<td><span>知识频道</span>
			<s:action var="knowledgeChannle_getAll" name="knowledgeChannle_getAll" namespace="/admin/knowledge" executeResult="false" ignoreContextParams="false">
			</s:action>
			<select name='knowledge.knowledgeChannle.id' id="channleId" class="inp_select">
			    <option selected="selected" value="0">请选择频道</option>
			    <s:iterator value="#knowledgeChannle_getAll.knowledgeChannles" var="channle">
					<s:if test='#channle.id == knowledge.knowledgeChannle.id'>
				       <option value="<s:property value="#channle.id"/>" selected="selected"><s:property value="#channle.name"/></option>
				    </s:if>
				    <s:else>
				       <option value="<s:property value="#channle.id"/>"><s:property value="#channle.name"/></option>
				    </s:else>
				</s:iterator>			
			</select>
		</td>
		<td class="v-b" ><span class="red_star">*</span>知识频道</td>
	</tr>
	
	<tr>
		<td><span>知识省份</span>
			<select name="knowledge.province" id='province' onChange="getCity()" class="inp_select"/>
			<s:if test="knowledge.province!=null"><option value="${knowledge.province}">${knowledge.province}</option></s:if><s:else><option value="">请选择所在省份</option></s:else>
			    <option value="北京" >北京</option>
			    <option value="天津" >天津</option>
			    <option value="上海" >上海</option>
			    <option value="重庆" >重庆</option>
			    <option value="香港" >香港</option>
			    <option value="澳门" >澳门</option>
			    <option value="河北" >河北</option>
			    <option value="山西" >山西</option>
			    <option value="内蒙古" >内蒙古</option>
			    <option value="辽宁" >辽宁</option>
			    <option value="吉林" >吉林</option>
			    <option value="黑龙江" >黑龙江</option>
			    <option value="江苏" >江苏</option>
			    <option value="浙江" >浙江</option>
			    <option value="安徽" >安徽 </option>
			    <option value="福建" >福建 </option>
			    <option value="江西" >江西</option>
			    <option value="山东" >山东</option>
			    <option value="河南" >河南</option>
			    <option value="湖北" >湖北</option>
			    <option value="湖南" >湖南</option>
			    <option value="广东" >广东</option>
			    <option value="广西" >广西</option>
			    <option value="海南" >海南</option>
			    <option value="四川" >四川</option>
			    <option value="贵州" >贵州</option>
			    <option value="云南" >云南</option>
			    <option value="西藏" >西藏</option>
			    <option value="陕西" >陕西 </option>
			    <option value="甘肃" >甘肃 </option>
			    <option value="青海" >青海</option>
			    <option value="宁夏" >宁夏</option>
			    <option value="新疆" >新疆</option>
			    <option value="台湾" >台湾</option>
			</select>
		</td>
		<td class="v-b" >地方知识的省份</td>
	</tr>
	
	<tr>
		<td><span>知识城市</span>
			<select name='knowledge.city' id="city" class="inp_select">
			    <s:if test="knowledge.city!=null"><option value="${knowledge.city}">${knowledge.city}</option></s:if><s:else><option selected="selected" value="">请选择所在城市</option></s:else>
			</select>
		</td>
		<td class="v-b" >地方知识的城市</td>
	</tr> 

</table>
<input type="hidden" name="knowledge.state" value="1"/>
 <input type="hidden" name='knowledge.id' value="${knowledge.id}"/>

<p class="submit">
<input name="Submit1" id="Submit1" type="submit" value="提 交"  class="inp_btn" onclick="getEditorContent()"/>
</p>
</form>
</div>
<script src="http://a.tbcdn.cn/s/kissy/1.2.0/??kissy-min.js,uibase-min.js,component-min.js,dd-min.js,overlay-min.js,editor-min.js"></script>
<script src="<%=path%>/js/editor-plugin-pkg-min.js"></script>
<script src="<%=path%>/js/connection-min.js"></script> 
<script src="<%=path%>/js/json-min.js"></script>
<script>
KISSY.ready(function(S) {

    S.use('editor', function() {

        var KE = KISSY.Editor;
        var cfg = {
            attachForm:true,
            baseZIndex:10000,
            pluginConfig: {
                "image":{
                    upload:{
                        serverUrl:"<%=path%>/imageUpload.action",
                        surfix:"png,jpg,jpeg,gif",
                        fileInput:"uploadFile",
                        sizeLimit:3000,//k
                        extraHtml:"<p style='margin-top:10px;'>"
                    }
                },
                "flash":{
                    defaultWidth:"300",
                    defaultHeight:"300"
                },
                "templates": [
                    {
                        demo: "模板1效果演示html"  ,
                        html: "<div style='border:1px solid red'>模板1效果演示html</div><p></p>"
                    },
                    {
                        demo: "模板2效果演示html",
                        html: "<div style='border:1px solid red'>模板2效果演示html</div>"
                    }
                ],
                "multi-upload":{
                    serverUrl:"/code/upload/upload.jsp",
                    
                    //previewSuffix:"_60x60",
                    previewWidth:"80px",
                    sizeLimit:3000//k,
                    ,numberLimit:15,
                    extraHtml:"<p style='margin-top:10px;'>" +
                            "</p>"
                },
                "video":{
                    urlCfg:[
                        {
                            reg:/tudou\.com/i,
                            url:"http://bangpai.taobao.com/json/getTudouVideo.htm?" +
                                    "url=@url@&callback=@callback@"//"&rand=@rand@"
                        }
                    ],
                    urlTip:"请输入优酷网、土豆网、酷7网的视频播放页链接...",
                    providers:[
                        {
                            reg:/youku\.com/i,
                            width:480,
                            height:400,
                            detect:function(url) {
                                var m = url.match(/id_([^.]+)\.html$/);
                                if (m) {
                                    return "http://player.youku.com/player.php/sid/" + m[1] + "/v.swf";
                                }
                                m = url.match(/v_playlist\/([^.]+)\.html$/);
                                if (m) {
                                    return;
                                }
                                return url;
                            }
                        },
                        {
                            reg:/tudou\.com/i,
                            width:480,
                            height:400,
                            detect:function(url) {
                                return url;
                            }
                        },
                        {
                            reg:/ku6\.com/i,
                            width:480,
                            height:400,
                            detect:function(url) {
                                var m = url.match(/show[^\/]*\/([^.]+)\.html$/);
                                if (m) {
                                    return "http://player.ku6.com/refer/" + m[1] + "/v.swf";
                                }
                                return url;
                            }
                        }
                    ]
                },
                music:{
                    //必须和网址url同域而不是类库同域
                    musicPlayer:KE.Config.base + "music/niftyplayer.swf"
                },
                "draft":{
                    interval:5,
                    limit:10,
                    helpHtml:  "<div " +
                            "style='width:200px;'>" +
                            "<div style='padding:5px;'>草稿箱能够自动保存您最新编辑的内容，" +
                            "如果发现内容丢失，" +
                            "请选择恢复编辑历史</div></div>"
                },
                "resize":{
                    direction:["y"]
                },

                dragupload:{
                    surfix:"png,jpg,jpeg,gif",
                    fileInput:"uploadFile",
                    sizeLimit:3000,
                    serverUrl:"<%=path%>/imageUpload.action",
                    serverParams:{
                        waterMark:function() {
                            return true;
                        }
                    }
                }
            }
        };


        function dispalyEditor() {
            window.editor2 = KE("#content", S.clone(cfg)).use("elementpaths," +
                    "sourcearea,preview," +
                    "checkbox-sourcearea," +
                    "templates,separator," +
                    "undo,separator," +
                    "removeformat,font,format,separator," +
                    "list,indent," +
                    "justify,separator,link," +
                    "image," +
                    "smiley,separator,table,resize," +
                    "draft," +
                    "pagebreak,separator,maximize,dragupload");

        }

        dispalyEditor();
    });
});

function getEditorContent(){
	document.getElementById("content").innerHTML = window.editor2.getData();
}

</script>

<script type="text/javascript">
//JavaScript Document

var city=[[['北京','北京']],[['天津','天津']],[['上海','上海']],[['重庆','重庆']],[['香港','香港']],[['澳门','澳门']],[['石家庄','石家庄'],['唐山','唐山'],['秦皇岛','秦皇岛'],['邯郸','邯郸'],['邢台','邢台'],['保定','保定'],['张家口','张家口'],['承德','承德'],['沧州','沧州'],['廊坊','廊坊'],['衡水','衡水']],[['太原','太原'],['大同','大同'],['阳泉','阳泉'],['长治','长治'],['晋城','晋城'],['朔州','朔州'],['晋中','晋中'],['运城','运城'],['忻州','忻州'],['临汾','临汾'],['吕梁','吕梁']],[['呼和浩特','呼和浩特'],['包头','包头'],['乌海','乌海'],['赤峰','赤峰'],['通辽','通辽'],['鄂尔多斯','鄂尔多斯'],['呼伦贝尔','呼伦贝尔'],['巴彦淖尔','巴彦淖尔'],['乌兰察布','乌兰察布'],['兴安','兴安'],['锡林郭勒','锡林郭勒'],['阿拉善','阿拉善']],[['沈阳','沈阳'],['大连','大连'],['鞍山','鞍山'],['抚顺','抚顺'],['本溪','本溪'],['丹东','丹东'],['锦州','锦州'],['营口','营口'],['阜新','阜新'],['辽阳','辽阳'],['盘锦','盘锦'],['铁岭','铁岭'],['朝阳','朝阳'],['葫芦岛','葫芦岛']],[['长春','长春'],['吉林','吉林'],['四平','四平'],['辽源','辽源'],['通化','通化'],['白山','白山'],['松原','松原'],['白城','白城'],['延边','延边']],[['哈尔滨','哈尔滨'],['齐齐哈尔','齐齐哈尔'],['鸡西','鸡西'],['鹤岗','鹤岗'],['双鸭山','双鸭山'],['大庆','大庆'],['伊春','伊春'],['佳木斯','佳木斯'],['七台河','七台河'],['牡丹江','牡丹江'],['黑河','黑河'],['绥化','绥化'],['大兴安岭','大兴安岭']],[['南京','南京'],['苏州','苏州'],['扬州','扬州'],['无锡','无锡'],['徐州','徐州'],['常州','常州'],['南通','南通'],['连云港','连云港'],['淮安','淮安'],['盐城','盐城'],['镇江','镇江'],['泰州','泰州'],['宿迁','宿迁']],[['杭州','杭州'],['宁波','宁波'],['温州','温州'],['嘉兴','嘉兴'],['湖州','湖州'],['绍兴','绍兴'],['金华','金华'],['衢州','衢州'],['舟山','舟山'],['台州','台州'],['丽水','丽水']],[['合肥','合肥'],['芜湖','芜湖'],['蚌埠','蚌埠'],['淮南','淮南'],['马鞍山','马鞍山'],['淮北','淮北'],['铜陵','铜陵'],['安庆','安庆'],['黄山','黄山'],['滁州','滁州'],['阜阳','阜阳'],['宿州','宿州'],['巢湖','巢湖'],['六安','六安'],['亳州','亳州'],['池州','池州'],['宣城','宣城']],[['福州','福州'],['宁德','宁德'],['南平','南平'],['厦门','厦门'],['莆田','莆田'],['三明','三明'],['泉州','泉州'],['漳州','漳州']],[['南昌','南昌'],['上饶','上饶'],['萍乡','萍乡'],['九江','九江'],['景德镇','景德镇'],['新余','新余'],['鹰潭','鹰潭'],['赣州','赣州'],['吉安','吉安'],['宜春','宜春'],['抚州','抚州']],[['济南','济南'],['青岛','青岛'],['淄博','淄博'],['枣庄','枣庄'],['东营','东营'],['烟台','烟台'],['潍坊','潍坊'],['威海','威海'],['济宁','济宁'],['泰安','泰安'],['日照','日照'],['莱芜','莱芜'],['临沂','临沂'],['德州','德州'],['聊城','聊城'],['滨州','滨州'],['菏泽','菏泽']],[['郑州','郑州'],['开封','开封'],['洛阳','洛阳'],['平顶山','平顶山'],['焦作','焦作'],['鹤壁','鹤壁'],['新乡','新乡'],['安阳','安阳'],['濮阳','濮阳'],['漯河','漯河'],['许昌','许昌'],['三门峡','三门峡'],['南阳','南阳'],['商丘','商丘'],['信阳','信阳'],['周口','周口'],['驻马店','驻马店']],[['武汉','武汉'],['十堰','十堰'],['襄樊','襄樊'],['鄂州','鄂州'],['黄石','黄石'],['荆州','荆州'],['宜昌','宜昌'],['荆门','荆门'],['孝感','孝感'],['黄冈','黄冈'],['咸宁','咸宁'],['随州','随州'],['恩施','恩施']],[['长沙','长沙'],['株洲','株洲'],['湘潭','湘潭'],['岳阳','岳阳'],['邵阳','邵阳'],['常德','常德'],['衡阳','衡阳'],['张家界','张家界'],['益阳','益阳'],['郴州','郴州'],['永州','永州'],['怀化','怀化'],['娄底','娄底'],['湘西','湘西']],[['广州','广州'],['清远','清远'],['潮州','潮州'],['东莞','东莞'],['珠海','珠海'],['深圳','深圳'],['汕头','汕头'],['韶关','韶关'],['佛山','佛山'],['江门','江门'],['湛江','湛江'],['茂名','茂名'],['肇庆','肇庆'],['惠州','惠州'],['梅州','梅州'],['汕尾','汕尾'],['阳江','阳江'],['河源','河源'],['中山','中山'],['揭阳','揭阳'],['云浮','云浮']],[['南宁','南宁'],['柳州','柳州'],['桂林','桂林'],['梧州','梧州'],['北海','北海'],['防城港','防城港'],['钦州','钦州'],['贵港','贵港'],['玉林','玉林'],['百色','百色'],['贺州','贺州'],['河池','河池'],['来宾','来宾'],['崇左','崇左']],[['海口','海口'],['三亚','三亚']],[['成都','成都'],['自贡','自贡'],['攀枝花','攀枝花'],['泸州','泸州'],['德阳','德阳'],['绵阳','绵阳'],['广元','广元'],['遂宁','遂宁'],['内江','内江'],['乐山','乐山'],['南充','南充'],['宜宾','宜宾'],['广安','广安'],['达州','达州'],['眉山','眉山'],['雅安','雅安'],['巴中','巴中'],['资阳','资阳'],['阿坝','阿坝'],['甘孜','甘孜'],['凉山','凉山']],[['贵阳','贵阳'],['六盘水','六盘水'],['遵义','遵义'],['安顺','安顺'],['铜仁','铜仁'],['毕节','毕节'],['黔西南','黔西南'],['黔东南','黔东南'],['黔南','黔南']],[['昆明','昆明'],['曲靖','曲靖'],['玉溪','玉溪'],['保山','保山'],['昭通','昭通'],['丽江','丽江'],['普洱','普洱'],['临沧','临沧'],['文山','文山'],['红河','红河'],['西双版纳','西双版纳'],['楚雄','楚雄'],['大理','大理'],['德宏','德宏'],['怒江','怒江'],['迪庆','迪庆']],[['拉萨','拉萨'],['昌都','昌都'],['山南','山南'],['日喀则','日喀则'],['那曲','那曲'],['阿里','阿里'],['林芝','林芝']],[['西安','西安'],['铜川','铜川'],['宝鸡','宝鸡'],['咸阳','咸阳'],['渭南','渭南'],['延安','延安'],['24_7','汉中'],['榆林','榆林'],['安康','安康'],['商洛','商洛']],[['兰州','兰州'],['白银','白银'],['定西','定西'],['敦煌','敦煌'],['嘉峪关','嘉峪关'],['金昌','金昌'],['天水','天水'],['武威','武威'],['张掖','张掖'],['平凉','平凉'],['酒泉','酒泉'],['庆阳','庆阳'],['临夏','临夏'],['陇南','陇南'],['甘南','甘南']],[['西宁','西宁'],['海东','海东'],['海北','海北'],['黄南','黄南'],['海南','海南'],['果洛','果洛'],['玉树','玉树'],['海西','海西']],[['银川','银川'],['石嘴山','石嘴山'],['吴忠','吴忠'],['固原','固原'],['中卫','中卫']],[['乌鲁木齐','乌鲁木齐'],['克拉玛依','克拉玛依'],['吐鲁番','吐鲁番'],['哈密','哈密'],['和田','和田'],['阿克苏','阿克苏'],['喀什','喀什'],['克孜勒苏柯尔克孜','克孜勒苏柯尔克孜'],['巴音郭楞蒙古','巴音郭楞蒙古'],['昌吉','昌吉'],['博尔塔拉蒙古','博尔塔拉蒙古'],['伊犁哈萨克','伊犁哈萨克'],['阿勒泰','阿勒泰']],[['台北','台北'],['高雄','高雄'],['基隆','基隆'],['台中','台中'],['台南','台南'],['新竹','新竹'],['嘉义','嘉义']]];


function getCity(){
 var sltProvince=document.getElementById('province'); 
 var sltCity=document.getElementById('city'); 
 var provinceCity=city[sltProvince.selectedIndex - 1]; 
 sltCity.length=1;
 sltCity[0]=new Option('请选择所在城市',''); 
 for(var i=0;i<provinceCity.length;i++){      
     sltCity[i+1]=new Option(provinceCity[i][1],provinceCity[i][0]); 
 }
}
</script>
<%@ include file="/admin/inc/foot.jsp"%>