<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>

<div class="right">

    <%@ include file="../inc/navigation.jsp"%>

    <p class="map">新闻：新增新闻</p>
    <s:fielderror/>
    <center>

        <script>

            var pgo=0;

            function JumpUrl(url){

                if(pgo==0){ if(url != null){
                    location=url;
                }else{
                    location='/admin/news/news_listNewsByCondition.action?opType=1&query.state=1';
                }
                    pgo=1;
                }

            }

            document.write("<br /><div style='width:450px;padding:0px;border:1px solid #DADADA;'><div style='padding:6px;font-size:12px;border-bottom:1px solid #DADADA;background:#DBEEBD url(<%=path%>/images/wbg.gif)';'><b>操作提示信息</b></div>");

            document.write("<div style='height:130px;font-size:10pt;background:#ffffff'><br />");

            document.write("亲，您的评审操作已经生效");

            </script>

    </center>

</div>

<%@ include file="../inc/foot.jsp"%>
<%
        /**
         * bad code,too many common code everywhere...
         */
%>