<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<spring:url value="/" var="contextroot" />
<html>
<head>
	<title>Employee Page</title>
	<style type="text/css">
		.bgimg {
		    background-image: url('${contextroot}/resources/images/header-dart.jpg');
		    background-size: 100% 100%;
		    background-repeat: no-repeat;
		    width: 100%;
    		height: 300px;
		}
		.callus{

			    text-align: center;
		}
		p{
			font-family: "playfair","Georgia",Cambria,Times New Roman,Times,serif;
		}
	</style>
</head>
<body>
<jsp:include page="includes/navigation.jsp"/>

<div id="tablecontainer" style="background-color: #F6F6F6; margin: 2%; padding: 10px;">
 <div class="bgimg">
 </div>
 <div class=".callus" >
        <h3>GIVE US A SHOUT</h3>
        <p>If you face any issue drop us an e-mail @ <strong>ricky.mudaliar69@gmail.com</strong>.  </p>
        <p>Old-fashioned phone calls work too ~ <strong>9987-005-032</strong>.</p>
 </div>
<%-- <h1>
	Add a Employee
</h1>

<c:url var="addAction" value="/employee/add" ></c:url>

<form:form action="${addAction}" commandName="employee">
<table>
	<c:if test="${!empty employee.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td>
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="age">
				<spring:message text="age"/>
			</form:label>
		</td>
		<td>
			<form:input path="age" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty employee.name}">
				<input type="submit"
					value="<spring:message text="Edit Employee"/>" />
			</c:if>
			<c:if test="${empty employee.name}">
				<input type="submit"
					value="<spring:message text="Add Employee"/>" />
			</c:if>
		</td>
	</tr>
</table>
</form:form>
<br>
<h3>Persons List</h3>
<c:if test="${!empty listEmployees}">
	<table class="tg">
	<tr>
		<th width="80">Employee ID</th>
		<th width="120">Employee Name</th>
		<th width="120">Employee Age</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listEmployees}" var="employee">
		<tr>
			<td>${employee.id}</td>
			<td>${employee.name}</td>
			<td>${employee.age}</td>
			<td><a href="<c:url value='/edit/${employee.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${employee.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if> --%>
</div>
</body>
</html>
