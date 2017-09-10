<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>


	<%-- <spring:url value="/resources/js/jquery-1.12.1.min.js" var="jqueryJs" />
		<script src="${jqueryJs}"></script> --%>

		<spring:url value="/resources" var="resourcesPath" />
	<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-1.9.1.js"></script>
	<spring:url value="/resources/js/jquery.dataTables.js" var="datatable" />
		<script src="${datatable}"></script>
	<spring:url value="/resources/css/jquery.dataTables.css" var="jquerydataTables" />
		<link href="${jquerydataTables}" rel="stylesheet" />
	<spring:url value="/resources/css/jquery.dataTables.min.css" var="jquerydataTablesMin" />
		<link href="${jquerydataTablesMin}" rel="stylesheet" />
	<spring:url value="/resources/css/common.css" var="common" />
		<link href="${common}" rel="stylesheet" />



	<spring:url value="/resources/js/jquery-ui.js" var="jqueryUI" />
		<script src="${jqueryUI}"></script>
	<link href = "${resourcesPath}/css/jquery-ui.css" rel = "stylesheet">