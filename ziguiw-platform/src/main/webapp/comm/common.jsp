<%@ taglib uri="/WEB-INF/tld/c.tld"   prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/webui.tld" prefix="ps"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page errorPage="/fail.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath == \"/\" ? \"\" : pageContext.request.contextPath}"/>
