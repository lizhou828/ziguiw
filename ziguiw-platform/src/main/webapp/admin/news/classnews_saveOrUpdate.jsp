<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
    <link href="<%=path%>/css/base-min.css" rel="stylesheet"/>
    <link href="<%=path%>/css/editor-pkg-min-datauri.css" rel="stylesheet"/>
    <link href="<%=path%>/css/admin.css" type="text/css" rel="stylesheet" media="all"/>
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
        function getClassBySchoolId(schoolId) {
            $("#class_id").empty();
            $("<option/>").attr("value","").text("-加载中，请稍后-").appendTo($("#class_id"));
            var url = "/admin/news/classNews_ajaxGetClass.action?schoolId=" + schoolId;
            $.getJSON(url, function (data) {
                $("#class_id").empty();
                $("<option/>").attr("value","").text("-请选择班级-").appendTo($("#class_id"));
                $.each(data.dataNode, function (i, item) {
                    $("<option/>").attr("value", item.key).text(item.value).appendTo($("#class_id"));
                });
            });
        }
    </script>
    <title>子贵网CMS</title>
</head>
<body>

<%@ include file="../inc/left.jsp" %>
<div class="right">

    <%@ include file="../inc/navigation.jsp" %>

    <p class="map">新闻：新增站内站班级新闻</p>
    <s:fielderror/>

    <form method="post" action="<%=path%>/admin/news/classNews_saveOrUpdate.action">
        <textarea id="content"
                  name="classNews.content"
                  tabindex="0"
                  style="width:1100px;height:400px;margin:0 auto;">${classNews.content}
        </textarea>
        <table class="table">
            <colgroup>
                <col style="width:720px;"></col>
                <col></col>
            </colgroup>
            <tr>
                <td><span>新闻标题</span>
                    <input name="classNews.title" type="text" class="inp_txt" value="${classNews.title}" maxlength="30"/>
                </td>
                <td class="v-b"><span class="red_star">*</span>新闻标题，最长不能超过60字</td>
            </tr>
            
             <tr>
                <td><span>摘要</span>
                    <input name="classNews.autoDescription" type="text" class="inp_txt" value="${classNews.autoDescription}"/>
                </td>
                <td class="v-b"><span class="red_star">*</span>新闻标题，内容摘要</td>
            </tr>

            <tr>
                <td><span>校园</span>
                    <s:select list="schoolList" name="classNews.xx_id" listKey="XxID" listValue="sch_name" headerKey="0"
                              headerValue="--请选择学校--" theme="simple" onchange="getClassBySchoolId(this.value)"/>
                </td>
                <td class="v-b">选择学校</td>
            </tr>

            <tr>
                <td><span>班级</span>
                    <s:select list="classList" name="classNews.class_id" id="class_id" listValue="Bj_mcheng" listKey="Bj_id" theme="simple"/>
                </td>
                <td class="v-b">新闻类别</td>
            </tr>


            <tr>
                <td><span>类型</span>
                    <s:select list="newsTypeList" name="classNews.type" listKey="key" listValue="value" theme="simple"/>
                </td>
                <td class="v-b">新闻类别</td>
            </tr>
        </table>

        <input type="hidden" name='classNews.id' value="${classNews.id}"/>

        <p class="submit">
            <input name="Submit1" id="Submit1" type="submit" value="提 交" class="inp_btn" onclick="getEditorContent()"/>
            <a href="/admin/news/newsAudit_view.action?classNewsId=${classNews.id}">审核失败</a>
        </p>
    </form>
</div>

<script src="http://a.tbcdn.cn/s/kissy/1.2.0/??kissy-min.js,uibase-min.js,component-min.js,dd-min.js,overlay-min.js,editor-min.js"></script>
<script src="<%=path%>/js/editor-plugin-pkg-min.js"></script>
<script src="<%=path%>/js/connection-min.js"></script>
<script src="<%=path%>/js/json-min.js"></script>
<script>
    KISSY.ready(function (S) {

        S.use('editor', function () {

            var KE = KISSY.Editor;
            var cfg = {
                attachForm:true,
                baseZIndex:10000,
                pluginConfig:{
                    "image":{
                        upload:{
                            serverUrl:"<%=path%>/imageUpload.action",
                            surfix:"png,jpg,jpeg,gif",
                            fileInput:"uploadFile",
                            sizeLimit:3000, //k
                            extraHtml:"<p style='margin-top:10px;'>"
                        }
                    },
                    "flash":{
                        defaultWidth:"300",
                        defaultHeight:"300"
                    },
                    "templates":[
                        {
                            demo:"模板1效果演示html",
                            html:"<div style='border:1px solid red'>模板1效果演示html</div><p></p>"
                        },
                        {
                            demo:"模板2效果演示html",
                            html:"<div style='border:1px solid red'>模板2效果演示html</div>"
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
                                detect:function (url) {
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
                                detect:function (url) {
                                    return url;
                                }
                            },
                            {
                                reg:/ku6\.com/i,
                                width:480,
                                height:400,
                                detect:function (url) {
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
                        helpHtml:"<div " +
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
                            waterMark:function () {
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

    function getEditorContent() {
        document.getElementById("content").innerHTML = window.editor2.getData();
    }

</script>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>
<%@ include file="../inc/foot.jsp" %>