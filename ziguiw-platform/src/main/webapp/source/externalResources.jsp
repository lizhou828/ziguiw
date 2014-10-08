
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/comm/common_tag.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
      <s:iterator value="#list" var="cxList" >
         <s:iterator value="#cxList.list" var="cx">
             <s:if test="#cx=='enter'">
                 <br/>
             </s:if>
             <s:elseif test="#cx=='' || #cx==null">

             </s:elseif>
             <s:else>
                 ${cx}
             </s:else>
         </s:iterator>


      </s:iterator>
</body>
</html>