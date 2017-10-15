<%@page import="com.hrms.model.RFQItem"%>
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
<link rel="stylesheet" href="${resourcesPath}/css/empdetail.css" />
<style>
	
</style>
</head>
<body>
<jsp:include page="includes/navigation.jsp" />
<jsp:include page="includes/toolbar.jsp" />
<div id="tablecontainer" style="background-color: #F6F6F6;">
<c:url var="addAction" value="/add/RFQ"></c:url>
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
				</table>
				</div>
				<div id="right-table">
				<table>
					<tr>
						<td><form:label path="reqby">
								<spring:message text="Req By : " />
							</form:label></td>
						<td><form:input path="reqby"/></td>
					</tr>
					<tr>
						<td><form:label path="reqdepartment">
								<spring:message text="Req Department : " />
							</form:label></td>
						<td><form:input path="reqdepartment"/></td>
					</tr>
					<tr>
						<td><form:label path="address">
								<spring:message text="Address : " />
							</form:label></td>
						<td><form:input path="address"/></td>
					</tr>
					<tr>
						<td><form:label path="project">
								<spring:message text="Project / Job : " />
							</form:label></td>
						<td><form:input path="project"/></td>
					</tr>
					<tr>
						<td><form:label path="remarks">
								<spring:message text="Remarks : " />
							</form:label></td>
						<td><form:input path="remarks"/></td>
					</tr>
				</table>
				</div>
				</div>
				<div id="rfqitems">
				<table border='1px'>	
					<tr>
						<td colspan=8>
						<spring:message text="Upload RFQ line Items" /> <button type="button" onClick="openImportPage()" />Upload File</button>
						</td>
					</tr>
					<tr>
						<th><input type="checkbox" /></th>
						<th>ItemId</th>
						<th>Description</th>
						<th>UnitofMeasurement</th>
						<th>Quantity</th>
						<c:if test="${RFQ.id > 0}">
							<th>Email To</th>
						</c:if>
					</tr>
			
			
			<c:forEach items="${RFQ.items}"  varStatus="vs">
			<%--
			 <tr>
				<td><form:input path="rfqitems" value="${listValue.ItemReference}"/></td>
				<td><form:input path="rfqitems" value="${listValue.Description}"/></td>
				<td><form:input path="rfqitems" value="${listValue.UnitofMeasurement}"/></td>
				<td><form:input path="rfqitems" value="${listValue.Quantity}"/></td>
				<td><form:input path="rfqitems" value="${listValue.Unitprice}"/></td>
				<td><form:input path="rfqitems" value="${listValue.MinimumUnitPrice}"/></td>
				<td><form:input path="rfqitems" value="${listValue.MaximumUnitprice}"/></td>
				<td><form:input path="rfqitems" value="${listValue.Price}"/></td>
				</tr> --%>
				<tr>
				<td><input type="checkbox" /></td>
				<td><form:input path="items[${vs.index}].rfqItemId"  border="0px solid"/></td>
				<td><form:input path="items[${vs.index}].description"/></td>
				<td><form:input path="items[${vs.index}].unit" /></td>
				<td><form:input path="items[${vs.index}].quantity" /></td>
				<c:if test="${RFQ.id > 0}">				
					<td><form:input path="items[${vs.index}].email" /></td>
				</c:if>
				</tr>
							
		 	</c:forEach> 
			</table>
		</div>
		
		<div id="savebuttons">
				<c:if test="${RFQ.id > 0}">
					<input id="save" type="submit" value="<spring:message text="Edit RFQ"/>" />
					<button type="button" onClick="if(!confirm('Are you sure you want to delete?')) return false; else javascript:location.href = '${contextroot}delete/RFQ/${RFQ.id}';" />Delete RFQ</button>
					<button type="button" onClick="if(!confirm('Are you sure you want to delete?')) return false; else javascript:location.href = '${contextroot}email/RFQ/${RFQ.id}';" />Send Mail</button>
				</c:if>
				<c:if test="${RFQ.id == 0}">
					<input id="save" type="submit" value="<spring:message text="Add RFQ"/>" />
				</c:if>
				<button type="button" onClick="javascript:location.href = '${contextroot}${sessionScope.prevURL}';" />Return</button>
			</div>
		</form:form>
</div>
</body>
</html>