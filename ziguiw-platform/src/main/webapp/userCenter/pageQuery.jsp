<%@include file="/comm/common.jsp"%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<input type="hidden" id="gotoPage" value="1"  name="gotoPage" />
<script type="text/javascript">
    function gotoPage(page){
        if (document.getElementById("classId")) {
            var _classId=document.getElementById("classId").value;
            if(_classId == '-1' || _classId=='undefined'){
                alert("请选定班级！");
            }
        }
        if (page) {
            document.getElementById('gotoPage').value = page;
            document.forms[1].submit();
        }
    }
</script>

<c:choose>
    <%--判断总页数是否为零--%>
    <c:when test="${totalPages != 0 && totalPages != null && totalPages != '' }">
        <a href="javascript:gotoPage(1)" >首页</a>

        <%--上一页是否可用--%>
        <c:choose>
            <c:when test="${ gotoPage-1 <= 0 }">
                <span class="na">&lt;上一页</span>
            </c:when>
            <c:otherwise>
                <a class="f12" href="javascript:gotoPage(${gotoPage-1});">&lt;上一页</a>
            </c:otherwise>
        </c:choose>


        <c:choose>
            <%--总页数大于等于10--%>
            <c:when test="${pageList != null && fn:length(pageList) >= 10}">
                <%--总页数大于10的时候分两种情况--%>
                <%--1、当前页不是个位数--%>
                <c:choose>
                        <c:when test="${gotoPage - gotoPage % 10 > 0 }">
                            <c:choose>
                                <%--若总页数为35，当前页为29，显示20—30页--%>
                                 <c:when test="${gotoPage - gotoPage % 10 +10 < totalPages}">
                                     <c:forEach begin="${gotoPage - gotoPage % 10 }" step="1" end="${gotoPage - gotoPage % 10 +10}" varStatus="status">
                                         <%--若是当前页则高亮显示--%>
                                         <c:choose>
                                             <c:when test="${gotoPage == status.index}">
                                                 <a style="background: none repeat scroll 0 0 #C41C1B;" href="javascript:gotoPage(${status.index});">${status.index}</a>
                                             </c:when>
                                             <c:otherwise>
                                                 <a href="javascript:gotoPage(${status.index});">${status.index}</a>
                                             </c:otherwise>
                                         </c:choose>

                                     </c:forEach>
                                 </c:when>
                                <%--若总页数为25，当前页为21，显示20—25页--%>
                                <c:otherwise>
                                    <c:forEach begin="${gotoPage - gotoPage % 10}" step="1" end="${totalPages}" varStatus="status">
                                        <%--若是当前页则高亮显示--%>
                                        <c:choose>
                                            <c:when test="${gotoPage == status.index}">
                                                <a style="background: none repeat scroll 0 0 #C41C1B;" href="javascript:gotoPage(${status.index});">${status.index}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="javascript:gotoPage(${status.index});">${status.index}</a>
                                            </c:otherwise>
                                        </c:choose>

                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                        </c:when>
                        <%--2、当前页是个位数--%>
                        <c:otherwise>
                            <c:forEach begin="1" step="1" end="10" varStatus="status">
                                <%--若是当前页则高亮显示--%>
                                <c:choose>
                                    <c:when test="${gotoPage == status.index}">
                                        <a style="background: none repeat scroll 0 0 #C41C1B;" href="javascript:gotoPage(${status.index});">${status.index}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:gotoPage(${status.index});">${status.index}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                </c:choose>

            </c:when>


                <%--总页数小于10--%>
            <c:otherwise>
                <c:forEach items="${pageList}"  var="page">
                    <c:choose>
                        <c:when test="${gotoPage == page}">
                            <a style="background: none repeat scroll 0 0 #C41C1B;" href="javascript:gotoPage(${page})">${page}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:gotoPage(${page})">${page}</a>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </c:otherwise>
        </c:choose>



        <%--下一页是否可用--%>
        <c:choose>
            <c:when test="${gotoPage+1 <= totalPages}">
                <a class="f12" href="javascript:gotoPage(${gotoPage+1})">下一页&gt;</a>
            </c:when>
            <c:otherwise>
                <span class="na">下一页&gt;</span>
            </c:otherwise>
        </c:choose>


        <a href="javascript:gotoPage(${totalPages});">尾页</a>
    </c:when>
</c:choose>


