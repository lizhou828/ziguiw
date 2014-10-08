<%@ page import="com.zigui.domain.CcczgInfoSousown" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的资源-子贵网用户中心</title>
<link href="../css/uccss.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/userCenter/ucPublic/pageQuery.js"></script>
    <script type="text/javascript">
          function changeUploadPage(){
              var pageNum =document.getElementById("upload").value;
              var maxPage=document.getElementById("uploadMaxPage").value;
              if (pageNum.length==0||isNaN(pageNum)){
                  alert('非法的页码');
                  return false;
              }
              if(maxPage==0||maxPage==''||maxPage==null){
                  alert('无记录可以查询');
                  return false;
              }
              if (1<=pageNum && pageNum <=maxPage){
                  document.getElementById("gotoPage").setAttribute('value',pageNum);
                  document.getElementById("querySource").submit();
              }
              if(pageNum>maxPage){
                  document.getElementById("uploadSourceGotoPage").setAttribute('value',maxPage);
                  document.getElementById("querySource").submit();
              }
              if(pageNum<1){
                  document.getElementById("uploadSourceGotoPage").setAttribute('value','1');
                  document.getElementById("querySource").submit();
              }

              return true;
          }

        function changeDownloadPage(){
            alert("changeDownloadPage");
            var pageNum =document.getElementById("download").value;
            var maxPage=document.getElementById("downloadMaxPage").value;
            alert("pageNum ="+pageNum +",maxPage="+maxPage);
            if (pageNum.length==0||isNaN(pageNum)){
                alert('非法的页码');
                return false;
            }
            if(maxPage==0||maxPage==''||maxPage==null){
                alert('无记录可以查询');
                return false;
            }
            if (1<=pageNum && pageNum <=maxPage){
                document.getElementById("gotoPage").setAttribute('value',pageNum);
                document.getElementById("querySource").submit();
            }
            if(pageNum>maxPage){
                pageNum=maxPage;
                document.getElementById("downloadSourceGotoPage").setAttribute('value',maxPage);
                document.getElementById("querySource").submit();
            }
            if(pageNum<1){
                pageNum=1;
            }
            document.getElementById("downloadSourceGotoPage").setAttribute('value','1');
            document.getElementById("querySource").submit();
            return true;
        }

    </script>

</head>

<body>
<jsp:include page="ucPublic/head.jsp"></jsp:include>
    
    

    <div class="uc_midd">
        <form action="/userCenter/mySource.action" id=querySource>
    	<div class="res_tit">
        	<span>我的资源</span><span class="res_en">MY RESOURCE</span>
        </div>
        <div class="res_con">
        	<div class="upload_t">
            	我上传的资源
            </div>
            <div class="res_tb">

                <table border="0">
                    <tr>
                        <th>资源标题</th>
                        <th>审核状态</th>
                        <th>上传时间</th>
                        <th>积分</th>
                        <th>下载次数</th>
                    </tr>


                    <c:forEach items="${sourceUploadList}" var="source">
                        <tr>
                            <td  class="res_si"> ${source.title}</td>
                            <td  class="res_si">
                                <c:choose>
                                    <c:when test="${source.checkSign == 0}">
                                        未审核
                                    </c:when>
                                    <c:when test="${source.checkSign == 1}">
                                        已审核
                                    </c:when>
                                    <c:otherwise>
                                        未通过
                                    </c:otherwise>
                                </c:choose>


                            </td>
                            <td  class="res_si"> ${source.createDate}</td>
                            <td  class="res_si"> ${source.needpoint}</td>
                            <td  class="res_si"> ${source.resourceDownnum}</td>
                        </tr>
                    </c:forEach>


                </table>
                    <input type="hidden"  id="uploadSourceGotoPage" name="uploadSourceGotoPage" />
                <div class="points_p">
                    <p>
                        <a href="javascript:document.getElementById('uploadSourceGotoPage').setAttribute('value','1');querySource.submit();">1</a>
                        <a href="javascript:document.getElementById('uploadSourceGotoPage').setAttribute('value','2');querySource.submit();">2</a>
                        <a href="javascript:document.getElementById('uploadSourceGotoPage').setAttribute('value','3');querySource.submit();">3</a>
                        <a href="#">…</a>
                        <a href="javascript:document.getElementById('uploadSourceGotoPage').setAttribute('value','${uploadTotalPage}');querySource.submit();">${uploadTotalPage}</a>
                        <input id=uploadMaxPage type=hidden value="${uploadTotalPage}">
                        转到 <input id ="upload" type="text"  size="2"/> 页
                        <input onclick="changeUploadPage();" type="button" id="uploadGotoPage" value="Go"/>
                    </p>
                </div>

           </div>
           
           <div class="download_t">
            	我下载的资源
            </div>
            <div class="res_do">

                <table border="0">
                    <tr>
                        <th>资源标题</th>
                        <th>下载时间</th>
                        <th>所需积分</th>
                        <th>上传用户</th>
                    </tr>
                    <%
                        List<CcczgInfoSousown> downSourceList=(List<CcczgInfoSousown>)request.getAttribute("sourceDownList");
                        if(downSourceList!=null && downSourceList.size() != 0){
                            for(int i=0;i<downSourceList.size();i++){
                                %>
                                <tr>
                                    <td class="res_d">
                                        <%=downSourceList.get(i).getCcczgInfoSouce().getTitle()%>
                                    </td>
                                    <td class="res_d">
                                        <fmt:formatDate value="<%=downSourceList.get(i).getCreateDate()%>" pattern="yyyy-MM-dd HH:mm"/>
                                    </td>
                                    <td class="res_d">
                                        <%=downSourceList.get(i).getCcczgInfoSouce().getNeedpoint()%>
                                    </td>
                                    <td class="res_d">
                                        <%=downSourceList.get(i).getUser().getNickName()%>
                                    </td>
                                </tr>
                                <%
                            }

                        }else {
                              %>
                                <tr>
                                    <td>
                                        没有记录！
                                    </td>
                                </tr>

                               <%
                        }
                    %>



                </table>
                    <input type="hidden"   id="downloadSourceGotoPage"  name="downloadSourceGotoPage" />
                <div class="points_p">

                    <p>
                        <a href="javascript:document.getElementById('downloadSourceGotoPage').setAttribute('value','1');querySource.submit();">1</a>
                        <a href="javascript:document.getElementById('downloadSourceGotoPage').setAttribute('value','2');querySource.submit();">2</a>
                        <a href="javascript:document.getElementById('downloadSourceGotoPage').setAttribute('value','3');querySource.submit();">3</a>
                        <a href="#">…</a>
                        <a href="javascript:document.getElementById('downloadSourceGotoPage').setAttribute('value','${downloadTotalPage}');querySource.submit();">${downloadTotalPage}</a>
                        转到 <input id ="download" type="text"  size="2"/> 页
                        <input id=downloadMaxPage type=hidden value="${downloadTotalPage}">
                        <input type="button" onclick="changeDownloadPage()" value="Go"/>

                </div>

           </div>
           
        </div>
        </form>

     <div class="clear"></div>
 
 
    <div class="uc_noti">
    	<p>注：退出本系统前请先注销用户,否则在系统默认的帐号登录时间内，同名的管理员帐号无法从另一IP登录,同时也防止管理员权限贮留，产生安全瘾患！</p>
    </div>
           
    </div>

<jsp:include page="/inc/templateFoot.jsp"/>