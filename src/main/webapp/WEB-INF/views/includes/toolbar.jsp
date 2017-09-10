<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>


	<spring:url value="/resources/js/prefix-free.js" var="prefix" />
		<script src="${prefix}"></script>
	<spring:url value="/resources/css/iconic.css" var="iconic" />
		<link href="${iconic}" rel="stylesheet" />
	<spring:url value="/resources/css/style.css" var="style" />
		<link href="${style}" rel="stylesheet" />
	<spring:url value="/" var="contextroot" />
	<c:set var="formcontext" value="${sessionScope.formContext}" scope="session"  />
	<c:set var="isListing" value="${sessionScope.isListing}" scope="session"  />
	
<script>
function openImportPage()
{
	var url = ${contextroot}+"importexcel";
	window.open(url,null,
			"height=400,width=600,status=yes,toolbar=no,menubar=no,location=no");
	}
</script>
<div id="toolbar">
		<ul class="menu">
			<li><a href="${contextroot}add/${formcontext}"><button type="button" />Add</button></a></li>
			<c:if test="${!empty contextroot}">
			<li><a href="#" onclick="openImportPage()"><button type="button" />Import</button></a></li>
			<li><a href="${contextroot}downloadExcel/${formcontext}"><button type="button" />Export</button></a></li>
			</c:if>		
		</ul>
	</div>