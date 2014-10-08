<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %> 
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="t" uri="/ttxs-tags"%>
<%
    String ctx = request.getContextPath();
    if ("/".equals(ctx)) ctx = "";
%>
<c:set value="<%=ctx%>" var="ctx"/>
 