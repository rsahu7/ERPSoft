<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item Details</title>
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
<c:url var="addAction" value="/add/Item"></c:url>
<c:set var="prevURL" value="${sessionScope.prevURL}" scope="session" />
	<form:form action="${addAction}" commandName="Item" method="POST" class="empform">
	<form:hidden path="Id" />
		<div id="left-table">
				<table>
					<tr>
						<td><form:label path="code">
								<spring:message text="Id : " />
							</form:label></td>
						<td><form:input path="code"  value="ITM${Item.code}"  readonly="true"/></td>
					</tr>
					<tr>
						<td><form:label path="customerid">
								<spring:message text="Customer Id : " />
							</form:label></td>
						<td><form:input path="customerid" /></td>
					</tr>
					<tr>
						<td><form:label path="avgcost">
								<spring:message text="Average Cost : " />
							</form:label></td>
						<td><form:input path="avgcost" /></td>
					</tr>
					<tr>
						<td><form:label path="retailprice">
								<spring:message text="Retail Price : " />
							</form:label></td>
						<td><form:input path="retailprice" /></td>
					</tr>
					<tr>
						<td><form:label path="uom">
								<spring:message text="Unit Of Measure : " />
							</form:label></td>
						<td><form:input path="uom" /></td>
					</tr>
					<tr>
						<td><form:label path="currentstock">
								<spring:message text="Current Stock : " />
							</form:label></td>
						<td><form:input path="currentstock" /></td>
					</tr>
					<tr>
						<td><form:label path="minimumstock">
								<spring:message text="Minimum Stock : " />
							</form:label></td>
						<td><form:input path="minimumstock" /></td>
					</tr>
					<tr>
						<td><form:label path="packingsize">
								<spring:message text="Packing Size : " />
							</form:label></td>
						<td><form:input path="packingsize" /></td>
					</tr>
					<tr>
						<td><form:label path="barcode">
								<spring:message text="Bar Code : " />
							</form:label></td>
						<td><form:input path="barcode" /></td>
					</tr>
					
					<tr>
						<td><form:label path="wholesalemarkup">
								<spring:message text="Wholesale Markup : " />
							</form:label></td>
						<td><form:input path="wholesalemarkup" /></td>
					</tr>
					
					<tr>
						<td><form:label path="brandname">
								<spring:message text="Brand Name : " />
							</form:label></td>
						<td><form:input path="brandname" /></td>
					</tr>															
					
						
				</table>
				</div>
				<div id="right-table">
					<table>
					<tr>
						<td><form:label path="description">
								<spring:message text="Description : " />
							</form:label></td>
						<td><form:textarea path="description" /></td>
					</tr>
					<tr>
						<td><form:label path="costprice">
								<spring:message text="Cost Price : " />
							</form:label></td>
						<td><form:input path="costprice" /></td>
					</tr>
					<tr>
						<td><form:label path="wholesaleprice">
								<spring:message text="Wholesale Price : " />
							</form:label></td>
						<td><form:input path="wholesaleprice" /></td>
					</tr>
					<tr>
						<td><form:label path="primaryunit">
								<spring:message text="Primary Unit : " />
							</form:label></td>
						<td><form:input path="primaryunit" /></td>
					</tr>
					<tr>
						<td><form:label path="reservedstock">
								<spring:message text="Reserved Stock : " />
							</form:label></td>
						<td><form:input path="reservedstock" /></td>
					</tr>
					<tr>
						<td><form:label path="reorderlevel">
								<spring:message text="Reorder Level : " />
							</form:label></td>
						<td><form:input path="reorderlevel" /></td>
					</tr>
					<tr>
						<td><form:label path="volume">
								<spring:message text="Volume : " />
							</form:label></td>
						<td><form:input path="volume" /></td>
					</tr>
					<tr>
						<td><form:label path="customerbarcode">
								<spring:message text="Customer Barcode : " />
							</form:label></td>
						<td><form:input path="customerbarcode" /></td>
					</tr>
					
					<tr>
						<td><form:label path="retailmarkup">
								<spring:message text="Retail Markup : " />
							</form:label></td>
						<td><form:input path="retailmarkup" /></td>
					</tr>
					<tr>
						<td><form:label path="creationdate">
						<spring:message text="Creation Date :" />
						</form:label></td>
						<fmt:formatDate var="creationdate" pattern="dd-MM-yyyy" value="${Item.creationdate}" />
						<td><form:input path="creationdate" value="${creationdate}" readonly="true" /></td>
					</tr>		
				</table>
				</div>
				</div>
		<div id="savebuttons">
				<c:if test="${!empty Item.description}">
					<input id="save" type="submit" value="<spring:message text="Edit Item"/>" />
					<button type="button" onClick="if(!confirm('Are you sure you want to delete?')) return false; else javascript:location.href = '${contextroot}delete/Item/${Item.id}';" />Delete Item</button>
				</c:if>
				<c:if test="${empty Item.description}">
					<input id="save" type="submit" value="<spring:message text="Add Item"/>" />
				</c:if>
				<button type="button" onClick="javascript:location.href = '${contextroot}${sessionScope.prevURL}';" />Return</button>
			</div>
		</form:form>
</div>
</body>
</html>