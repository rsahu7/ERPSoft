<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>App Configuration</title>
<spring:url value="/resources" var="resourcesPath" />
<spring:url value="/" var="contextroot" />
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-ui.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/additional-methods.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/formdetail.js"></script>
<link rel="stylesheet" href="${resourcesPath}/css/jquery-ui.css" />
<link rel="stylesheet" href="${resourcesPath}/css/empdetail.css" />
<style>
	
</style>
</head>
<body>
<jsp:include page="includes/navigation.jsp" />
<jsp:include page="includes/toolbar.jsp" />
<div id="tablecontainer" style="background-color: #F6F6F6;">
<c:url var="addAction" value="/add/Project"></c:url>
<c:set var="prevURL" value="${sessionScope.prevURL}" scope="session" />
	<form:form action="${addAction}" commandName="Project" method="POST" class="empform">
	<form:hidden path="Id" />
		<div id="left-table">
				<table>
					<tr>
						<td><form:label path="code">
								<spring:message text="Id : " />
							</form:label></td>
						<td><form:input path="code"  value="PRJ${Project.code}"  readonly="true"/></td>
					</tr>
					<tr>
						<td><form:label path="projectno">
								<spring:message text="Project No : " />
							</form:label></td>
						<td><form:input path="projectno" /></td>
					</tr>
					<tr>
						<td><form:label path="address">
								<spring:message text="Address : " />
							</form:label></td>
						<td><form:textarea path="address" /></td>
					</tr>						
				</table>
				</div>
				</div>
		<div id="savebuttons">
				<c:if test="${!empty Project.description}">
					<input id="save" type="submit" value="<spring:message text="Edit Project"/>" />
					<button type="button" onClick="if(!confirm('Are you sure you want to delete?')) return false; else javascript:location.href = '${contextroot}delete/Project/${Project.id}';" />Delete Project</button>
				</c:if>
				<c:if test="${empty Project.description}">
					<input id="save" type="submit" value="<spring:message text="Add Project"/>" />
				</c:if>
				<button type="button" onClick="javascript:location.href = '${contextroot}${sessionScope.prevURL}';" />Return</button>
			</div>
		</form:form>
</div>
</body>
</html>