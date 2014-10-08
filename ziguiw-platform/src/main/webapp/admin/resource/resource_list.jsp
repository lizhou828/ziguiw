<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/comm/common_tag.jsp" %>
<%@ include file="../inc/head.jsp"%>

<%@ include file="../inc/left.jsp"%>
<script type="text/javascript">
		$(document).ready(function(){
			$("#btnQuery").click(function(){
				$("#auditform1").attr("action","${pageContext.request.contextPath }/admin/resource/listBykey.action");
				$("#auditform1").submit();
			});
		});
		///审核事件
		function updclick(id){
			auditform1.action = "${pageContext.request.contextPath }/admin/resource/sourceGet.action?subjectId="+id;
			auditform1.submit();
		}
</script>
<style>
.pageNav {
	text-align:right;
	margin-right:20px;
	}
.pageNav a {
	padding:3px 4px;
	border:1px solid #BBB;
	margin:0 2px;
	color:#000;
	text-decoration:none;
	}
.pageNav a:hover {
	color:#0068B6;
	text-decoration:underline;
	}
.pageNav a:visited {
	color:#0068B6;
	text-decoration:none;
	}
</style>
<div class="right">

<%@ include file="../inc/navigation.jsp"%>

<p class="map">资源审核</p>
	<form action="${pageContext.request.contextPath }/admin/resource/resourcesAudit_list.action" method="post" id="auditform1">
					<input type="hidden" id="id" name="id">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="tabb">
					   <tbody><tr>
				        <td bgcolor="#F5FAFE" align="right">快速搜索：</td>
				        <td bgcolor="#F5FAFE" align="left" colspan="5">
				        <select id="queryType" name="queryType">
				          <option  value="">审核状态</option>
				          <option value="1" <c:if test="${'1' eq queryType}">selected</c:if>>已审核</option>
				          <option value="2" <c:if test="${'2' eq queryType}">selected</c:if>>未审核</option>
				        </select>&nbsp;
				        资源名称：<input type="text" "="" size="15" value="" id="resname" name="resname">				        
				        <input type="button" value="查询" id="btnQuery" name="btnQuery"></td>
				      </tr>
					</tbody></table>
					
                    <table width="100%" cellspacing="0" cellpadding="0" border="0" class="tabb">
					    <thead>
							<tr>
								<th class="bsortable">序号</th>
								<th class="bsortable">资源标题</th>
								<th class="bsortable">资源类别</th>
								<th class="bsortable">版本名称</th>
								<th class="bsortable">是否审核</th>
								<th class="bsortable">上传时间</th>
								<th class="bsortable">修改时间</th>
								<th class="bsortable">操作</th>
							</tr>
						</thead>
						
						
							<s:iterator value="result.content" status="st" id = "source">
							<tr>
								<td>${st.count}</td>
								<td>
								<c:choose>
				          		<c:when test="${fn:length(source.title)<25}">${source.title}</c:when>
				          		<c:otherwise>${fn:substring(source.title,0,23)}...</c:otherwise> 
				          		</c:choose>
								</td> 
								<td><c:if test="${source.sourceType.typeName != null}">${source.sourceType.typeName}</c:if>
								<c:if test="${source.sourceType.typeName == null}">无分类</c:if>
								</td><!-- ${source.sourceType.typeName} -->
								<td><c:if test="${source.souceVersion.VName != null}">${source.souceVersion.VName}</c:if>
								<c:if test="${source.souceVersion.VName == null}">无版本</c:if>
								</td><!-- source.souceVersion.VName -->
								<td>
								<c:if test="${source.checkSign==0}">未审核</c:if>
								<c:if test="${source.checkSign==1}">审核通过</c:if>
								<c:if test="${source.checkSign==2}">审核未通过</c:if>
								</td>
								<td><fmt:formatDate value="${source.createDate}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${source.modifyDate}" pattern="yyyy-MM-dd"/></td>
								<td>
									<img id="updimg" style="cursor:pointer" onclick="updclick('${source.subjectId}');" src="${pageContext.request.contextPath}/images/ico_edit_16.png" class="icon16 fl-space2" alt="" title="审核" />
								</td>
							</tr>
						</s:iterator>
					</table>            
					<div class="tab-footer">
						  <div class="fl">
					  		
						  </div>
						  <t:tpage page="${result.page}" formId="auditform1"></t:tpage>
					</div> 
					</form>

</div>

<script src="${pageContext.request.contextPath}/js/admin.js" charset="UTF-8"></script>
<%@ include file="../inc/foot.jsp"%>