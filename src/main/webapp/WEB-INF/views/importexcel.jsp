<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<c:set var="formcontext" value="${sessionScope.formContext}" scope="session"  />
<html>
	<head>
		<title>Import Data</title>
		<style type="text/css">
		p{
			font-family: "playfair","Georgia",Cambria,Times New Roman,Times,serif;
		}
	</style>
	</head>
	<body>

		<%-- <jsp:include page="includes/navigation.jsp" /> --%>

		<div id="tablecontainer" style="background-color: #F6F6F6; margin: 2%; padding: 10px;">
			<h3>Import ${formcontext} Data</h3>
			<p  style="color: red;">${message}<strong>${error}</strong>.  </p>
			<br />
			<br />
			<form:form action="processExcelData" method="post"	enctype="multipart/form-data">
				<input name="excelfile" type="file" id="file" >
				<input type="hidden" name="formName" id="formName" value="${formcontext}" />
				<input type="submit" value="processExcel" id="submit">
			</form:form>
		</div>

	</body>
</html>