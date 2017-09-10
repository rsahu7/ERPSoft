<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Details</title>
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
<c:url var="addAction" value="/add/Customer"></c:url>
<c:set var="prevURL" value="${sessionScope.prevURL}" scope="session" />
	<form:form action="${addAction}" commandName="Customer" method="POST" class="empform">
	<form:hidden path="Id" />
		<div id="left-table">
				<table>
					<tr>
						<td><form:label path="code">
								<spring:message text="Id : " />
							</form:label></td>
						<td><form:input path="code"  value="CUS${Customer.code}"  readonly="true"/></td>
					</tr>
					<tr>
						<td><form:label path="emailid">
								<spring:message text="Email ID : " />
							</form:label></td>
						<td><form:input path="emailid" /></td>
					</tr>
					<tr>
						<td><form:label path="shortname">
								<spring:message text="Short Name : " />
							</form:label></td>
						<td><form:input path="shortname" /></td>
					</tr>
					<tr>
						<td><form:label path="website">
								<spring:message text="Website : " />
							</form:label></td>
						<td><form:input path="website" /></td>
					</tr>
					<tr>
						<td><form:label path="accountcode">
								<spring:message text="Account Code : " />
							</form:label></td>
						<td><form:input path="accountcode" /></td>
					</tr>
					<tr>
						<td><form:label path="address">
								<spring:message text="Address : " />
							</form:label></td>
						<td><form:input path="address" /></td>
					</tr>
					<tr>
						<td><form:label path="country">
								<spring:message text="Country : " />
							</form:label></td>
						<td><form:input path="country" /></td>
					</tr>
					<tr>
						<td><form:label path="payment">
								<spring:message text="Payment : " />
							</form:label></td>
						<td><form:input path="payment" /></td>
					</tr>
					<tr>
						<td><form:label path="creditlimit">
								<spring:message text="Credit Limit : " />
							</form:label></td>
						<td><form:input path="creditlimit" /></td>
					</tr>					
					
						
				</table>
				</div>
				<div id="right-table">
					<table>
					<tr>
						<td><form:label path="name">
								<spring:message text="Customer Name : " />
							</form:label></td>
						<td><form:input path="name"/></td>
					</tr>
					<tr>
						<td><form:label path="contactno">
								<spring:message text="Contact No : " />
							</form:label></td>
						<td><form:input path="contactno" /></td>
					</tr>
					<tr>
						<td><form:label path="designation">
								<spring:message text="Designation : " />
							</form:label></td>
						<td><form:input path="designation" /></td>
					</tr>
					<tr>
						<td><form:label path="telephone">
								<spring:message text="Telephone : " />
							</form:label></td>
						<td><form:input path="telephone" /></td>
					</tr>
					<tr>
						<td><form:label path="fax">
								<spring:message text="Fax : " />
							</form:label></td>
						<td><form:input path="fax" /></td>
					</tr>
					<tr>
						<td><form:label path="region">
								<spring:message text="Region : " />
							</form:label></td>
						<td><form:input path="region" /></td>
					</tr>
					<tr>
						<td><form:label path="zipcode">
								<spring:message text="Zipcode : " />
							</form:label></td>
						<td><form:input path="zipcode" /></td>
					</tr>
					<tr>
						<td><form:label path="contactperson">
								<spring:message text="Contact Person : " />
							</form:label></td>
						<td><form:input path="contactperson" /></td>
					</tr>
					<tr>
						<td><form:label path="creationdate">
						<spring:message text="Creation Date :" />
						</form:label></td>
						<fmt:formatDate var="creationdate" pattern="dd-MM-yyyy" value="${Customer.creationdate}" />
						<td><form:input path="creationdate" value="${creationdate}" readonly="true" /></td>
					</tr>		
				</table>
				</div>
				</div>
		<div id="savebuttons">
				<c:if test="${!empty Customer.name}">
					<input id="save" type="submit" value="<spring:message text="Edit Customer"/>" />
					<button type="button" onClick="if(!confirm('Are you sure you want to delete?')) return false; else javascript:location.href = '${contextroot}delete/Customer/${Customer.id}';" />Delete Customer</button>
				</c:if>
				<c:if test="${empty Customer.name}">
					<input id="save" type="submit" value="<spring:message text="Add Customer"/>" />
				</c:if>
				<button type="button" onClick="javascript:location.href = '${contextroot}${sessionScope.prevURL}';" />Return</button>
			</div>
		</form:form>
</div>
</body>
</html>