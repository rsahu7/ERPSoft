<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
<spring:url value="/resources" var="resourcesPath" />
<link rel="stylesheet" href="${resourcesPath}/css/loginstyle.css" />
</head>

<body>
	<hgroup>
		<h1>
			<!-- ODD JOBS HR -->
			Login
		</h1>
		<h3>
			<!-- Login -->${message}
		</h3>
	</hgroup>
	<form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
		<div class="group">
			<form:input id="username" name="username" path="username" />
			<span class="highlight"></span>
			<span class="bar"></span>
			<form:label path="username">Username</form:label>
		</div>
		<div class="group">
			<form:password id="password" name="password" path="password" />
			<span class="highlight"></span >
			<span class="bar"></span>
			<form:label path="password">Password</form:label>
		</div>
		<button type="Submit" class="button buttonBlue" >
			Login
			<div class="ripples buttonRipples">
				<span class="ripplesCircle"></span>
			</div>
		</button>
	</form:form>
	<footer> </footer>
	<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/index.js"></script>
</body>
</html>
