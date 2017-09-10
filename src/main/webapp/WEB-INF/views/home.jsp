<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>
<html>
<c:set var="formContext" value="${sessionScope.formContext}" scope="session" />
<jsp:include page="includes/header.jsp"/>
<jsp:include page="includes/navigation.jsp"/>
<jsp:include page="includes/toolbar.jsp"/>
<c:if test="${!empty formContext}">
<jsp:include page="includes/${formContext.toLowerCase()}-listing.jsp"/>
</c:if>
<c:if test="${empty formContext}">
<jsp:include page="includes/body.jsp"/>
</c:if>
<jsp:include page="includes/footer.jsp"/>