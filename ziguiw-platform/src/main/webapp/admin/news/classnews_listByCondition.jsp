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
            $("<option/>").attr("value", "").text("-加载中，请稍后-").appendTo($("#class_id"));
            var url = "/admin/news/classNews_ajaxGetClass.action?schoolId=" + schoolId;
            $.getJSON(url, function (data) {
                $("#class_id").empty();
                $("<option/>").attr("value", "").text("-请选择班级-").appendTo($("#class_id"));
                $.each(data.dataNode, function (i, item) {
                    $("<option/>").attr("value", item.key).text(item.value).appendTo($("#class_id"));
                });
            });
        }
    </script>
    <title>子贵网CMS</title>
</head>
<body>

<%@include file="../inc/left.jsp"%>

<div class="right">
    <%@ include file="../inc/navigation.jsp" %>

    <p class="map">新闻：班级新闻</p>

    <form action="<%=path%>/admin/news/classNews_listByCondition.action">
        <ul class="col-ul ul_li_sp m-t10">
            <li>
                <span>学校</span>
                <s:select list="schoolList" name="query.xx_id" listKey="XxID" listValue="sch_name" headerKey=""
                          headerValue="--请选择学校--" theme="simple" onchange="getClassBySchoolId(this.value)"/>
            </li>
            <li><span>班级</span>
                <select name='query.class_id' id="class_id" class="inp_select">
                    <option value="">-请选择班级-</option>
                </select>
            </li>
            <li>
                <span>类型</span>
                <s:select list="newsTypeList" name="query.type" listKey="key" listValue="value" theme="simple"
                          headerKey="" headerValue="-所有类型-"/>
            </li>

            <li><span>新闻状态</span>
                <s:select list="newsStatusList" name="query.status" listKey="key" listValue="value" theme="simple"
                          headerKey="" headerValue="-所有状态-"/>
            </li>
            <li><span>编辑时间</span>
                <input type="text" class="inp_txt" name="query.startTime" value="${query.startTime}"
                       onclick="SelectDate(this)" readonly="readonly"/> -
                <input type="text" class="inp_txt" name="query.endTime" value="${query.endTime}"
                       onclick="SelectDate(this)" readonly="readonly"/>
            </li>

            <li><span>新闻标题</span><input type="text" class="inp_txt" name="query.title" value="${query.title}"></li>


            <input type="hidden" name="opType" value="<s:property value="#parameters.opType" />">

            <input type="submit" value="查询" class="inp_btn"/>
        </ul>
    </form>
    </p>
</div>
<form action="/admin/news/classNews_batchAuditPass.action" method="post" id="auditForm">
<table class="table">
    <colgroup>
    </colgroup>
    <thead>
    <td>
        <input type="checkbox" name="chkAll" id="chkAll" onclick='CheckAll(this.form)'>全部
    </td>
    <td>
        新闻标题
    </td>
    <td>
        学校
    </td>
    <td>
        班级
    </td>
    <td>
        类型
    </td>
    <td>
        创建时间
    </td>
    <td>
        状态
    </td>
    <td>
        管理
    </td>
    </thead>
    <s:iterator value="pagedVClassNews.list" var="vClassNews">
        <tr>
            <td>
                <input type="checkbox" name="classNewsIdList" value="${vClassNews.id}">
            </td>
            <td>${vClassNews.title}</td>
            <td>${vClassNews.schoolName}</td>
            <td>${vClassNews.className}</td>
            <td>${vClassNews.type}</td>
            <td>${vClassNews.createTime}</td>
            <td>
                    ${vClassNews.status}
                <s:if test="#vClassNews.status == \"审核失败\""><a
                        href="newsAudit_viewRejectReason.action?classNewsId=${vClassNews.id}">查看原因</a></s:if>
                <s:if test="#vClassNews.status == \"已经删除\""><a
                                href="classNews_restore.action?id=${vClassNews.id}">还原</a></s:if>
            </td>
            <td>
                <a href="<%=path%>/admin/news/classNews_getById.action?id=${vClassNews.id}">修改</a>
                <a href="<%=path%>/admin/news/classNews_deleteById.action?id=${vClassNews.id}">删除</a>
            </td>
        </tr>
    </s:iterator>
</table>
<input type="submit" id="button" value="通过" class="inp_btn"/>
</form>
<div class="fenye a-r">
    ${pagedVClassNews.pageString}
</div>
<script type="text/javascript">
    //JavaScript Document
    function validation(form) {
        if (form.chkAll.checked) {
            form.submit();
        }
        var isSelect = false;
        for (var i = 0; i < form.elements.length; i++) {
            var e = form.elements[i];
            if (e.Name != 'chkAll' && e.disabled == false && e.checked == true)
                isSelect = true;
        }
        if (isSelect) {
            form.submit();
        } else {
            alert('请选择要操作的新闻！');
        }
    }

    function initSelect() {
        var province = document.getElementById("province");
        var channleId = document.getElementById("channleId");
        var state = document.getElementById("state");

        var oldChannleId = document.getElementById("oldChannleId").value;
        var oldprovince = document.getElementById("oldprovince").value;
        var oldState = document.getElementById("oldState").value;


        for (var i = 0; i < channleId.options.length; i++) {
            if (channleId.options[i].value == oldChannleId) {
                channleId.options[i].selected = true;
                break;
            }
        }

        for (var i = 0; i < province.options.length; i++) {
            if (province.options[i].value == oldprovince && oldprovince != "") {
                province.options[i].selected = true;
                break;
            }
        }

        for (var i = 0; i < state.options.length; i++) {
            if (state.options[i].value == oldState) {
                state.options[i].selected = true;
                break;
            }
        }


    }

    initSelect();


    //转载请保留出处 http://www.dwww.cn
    function unselectall() {
        if (document.myform.chkAll.checked) {
            document.myform.chkAll.checked = document.myform.chkAll.checked & 0;
        }
    }
    function CheckAll(form) {
        for (var i = 0; i < form.elements.length; i++) {
            var e = form.elements[i];
            if (e.Name != 'chkAll' && e.disabled == false)
                e.checked = form.chkAll.checked;
        }
    }

    function approve(approve) {
        document.getElementById("approveFlag").value = approve;
        validation(document.getElementById('myform'));
    }


</script>

<script src="<%=path%>/js/date.js" charset="UTF-8"></script>


<script type="text/javascript" src="http://a.tbcdn.cn/s/kissy/1.2.0/kissy.js"></script>
<%@ include file="../inc/foot.jsp" %>


