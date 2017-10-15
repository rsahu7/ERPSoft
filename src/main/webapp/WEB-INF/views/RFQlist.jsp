<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RFQ Details</title>
<spring:url value="/resources" var="resourcesPath" />
<spring:url value="/" var="contextroot" />
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-ui.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/additional-methods.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/rfq.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/formdetail.js"></script>
<link rel="stylesheet" href="${resourcesPath}/css/jquery-ui.css" />
<link rel="stylesheet" href="${resourcesPath}/css/rfq.css" />
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/empdetail.js"></script>
<link rel="stylesheet" href="${resourcesPath}/css/jquery-ui.css" />
<link rel="stylesheet" href="${resourcesPath}/css/empdetail.css" />
<style>
	
</style>
</head>
<body>
<jsp:include page="includes/navigation.jsp" />
<jsp:include page="includes/toolbar.jsp" />
<div id="tablecontainer" style="background-color: #F6F6F6;">
<c:url var="addAction" value="/add/RFQs"></c:url>
<c:set var="prevURL" value="${sessionScope.prevURL}" scope="session" />
	<form:form action="${addAction}" commandName="RFQ" method="POST" class="empform">
	<form:hidden path="Id" />
						<div id="left-table">
					<table>
					<tr>
						<td><form:label path="rfqno">
								<spring:message text="PR No : " />
							</form:label></td>
						<td><form:input path="rfqno"/></td>
					</tr>
					<tr>
						<td><form:label path="rfqdate">
						<spring:message text="PR Date :" />
						</form:label></td>
						<fmt:formatDate var="rfqdate" pattern="dd/MM/yyyy" value="${RFQ.rfqdate}" />
						<td><form:input  path="rfqdate" class="datepick" type="text" id="rfqdate" value="${rfqdate}" /></td>
					</tr>
					<tr>
					<td><form:label path="deliveryduedate">
						<spring:message text="Delivery Due Date :" />
						</form:label></td>
						<fmt:formatDate var="deliveryduedate" pattern="MM/dd/yyyy" value="${RFQ.deliveryduedate}" />
						<td><form:input path="deliveryduedate" class="datepick" type="text" id="deliveryduedate"  value="${deliveryduedate}"  /></td>
					</tr> 
					<tr>
						<td><form:label path="refno">
								<spring:message text="Ref No : " />
							</form:label></td>
						<td><form:input path="refno"/></td>
					</tr>
					<tr>
						<td><form:label path="warehouse">
								<spring:message text="Warehouse : " />
							</form:label></td>
						<td><form:input path="warehouse"/></td>
					</tr>
					<tr>
						<td><form:label path="project">
								<spring:message text="Project / Job : " />
							</form:label></td>
						<td><form:input path="project"/></td>
					</tr>
					<tr>
						<td><form:label path="reqdepartment">
								<spring:message text="Req Department : " />
							</form:label></td>
						<td><form:input path="reqdepartment"/></td>
					</tr>
					<tr>
						<td><form:label path="reqby">
								<spring:message text="Req By : " />
							</form:label></td>
						<td><form:input path="reqby"/></td>
					</tr>
					<tr>
						<td><form:label path="address">
								<spring:message text="Address : " />
							</form:label></td>
						<td><form:input path="address"/></td>
					</tr>
					<tr>
						<td><form:label path="remarks">
								<spring:message text="Remarks : " />
							</form:label></td>
						<td><form:input path="remarks"/></td>
					</tr>
									</table>
				</div>
				<div>
	<c:forEach items="${RFQ.items}" varStatus="vs">
		<form:input
            path="RFQ.items[${vs.index}].description" />
          </c:forEach>
	<div>
		<div id="savebuttons">
				<c:if test="${!empty RFQ.rfqdate}">
					<input id="save" type="submit" value="<spring:message text="Edit Quotation"/>" />
					<button type="button" onClick="if(!confirm('Are you sure you want to delete?')) return false; else javascript:location.href = '${contextroot}delete/RFQ/${RFQ.rfqdate}';" />Delete Supplier</button>
				</c:if>
				<c:if test="${empty RFQ.rfqdate}">
					<input id="save" type="submit" value="<spring:message text="Create Quatation"/>" />
				</c:if>
				<button type="button" onClick="javascript:location.href = '${contextroot}${sessionScope.prevURL}';" />Return</button>
			</div>
		</form:form>
</div>
</body>
</html>