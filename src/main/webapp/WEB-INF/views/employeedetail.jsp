<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<spring:url value="/resources" var="resourcesPath" />
<spring:url value="/" var="contextroot" />
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-ui.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/additional-methods.js"></script>
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/empdetail.js"></script>
<link rel="stylesheet" href="${resourcesPath}/css/jquery-ui.css" />
<link rel="stylesheet" href="${resourcesPath}/css/empdetail.css" />

</head>
<body>
	<jsp:include page="includes/navigation.jsp" />
	<div id="tablecontainer" style="background-color: #F6F6F6; margin: 2%; padding: 10px;">

		<c:if test="${!empty sessionScope.message}">
			<h3>${sessionScope.message}</h3>
			<c:set var="message" value="" scope="session"  />
			<%-- <c:set var="prevURL" value="/" scope="session"  /> --%>
			<%-- <% request.getSession().setAttribute("message", "");%> --%>
		</c:if>
		<%-- <c:set var="prevURL" value="${sessionScope.curURL}" scope="session" />
		<c:set var="curURL" value="<%=request.get %>" scope="session" /> --%>
		<ul class="tab">
			<li><a href="javascript:void(0)" class="tablinks active"	onclick="openCity(event, 'personal')">Personal Details</a></li>
			<li><a href="javascript:void(0)" class="tablinks"	onclick="openCity(event, 'education')">Contact info & Education</a></li>
			<li><a href="javascript:void(0)" class="tablinks"	onclick="openCity(event, 'experience')">Work Experience</a></li>
			<c:if test="${!empty employee.name}">
				<li ><a href="javascript:void(0)" class="tablinks" 	onclick="openCity(event, 'job')" id="Interview">Interview Details</a></li>
				<li><a href="javascript:void(0)" class="tablinks"	onclick="openCity(event, 'attachment')" id="Attachments">Attachments</a></li>
			</c:if>
		</ul>

		<c:url var="addAction" value="/employee/add"></c:url>
		<form:form action="${addAction}" commandName="employee" method="POST" class="empform" id="empdetailForm">
			<div id="personal" class="tabcontent " style="display: block;">
				<div id="personaldetails">

				<table>
					<tr>
						<td><form:hidden path="id" />
							<form:hidden path="creationdate" /></td>
					</tr>
					<tr>
						<td><form:label path="name">
								<spring:message text="First Name : " />
							</form:label></td>
						<td><form:input path="name" /></td>
					</tr>
					<tr>
						<td><form:label path="lastname">
								<spring:message text="Last name :" />
							</form:label></td>
						<td><form:input path="lastname" /></td>
					</tr>
					<tr>
						<td><form:label path="middlename">
								<spring:message text="Middle name :" />
							</form:label></td>
						<td><form:input path="middlename" /></td>
					</tr>
					<tr>
						<td><form:label path="gender">
								<spring:message text="gender :" />
							</form:label></td>
						<td>
						Male <form:radiobutton path="gender" value="Male" />
						Female <form:radiobutton path="gender" value="Female" />
						<%-- <form:input path="gender" /> --%></td>
					</tr>
					<tr>
						<td><form:label path="age">
								<spring:message text="Age :" />
							</form:label></td>
						<td><form:input path="age" id="age" /></td>
					</tr>
					<tr>
						<td><form:label path="birthdate">
								<spring:message text="Birth Date :" />
							</form:label></td>
						<fmt:formatDate var="birthdate" pattern="MM/dd/yyyy" value="${employee.birthdate}" />
						<td><form:input path="birthdate" class="date" type="text" id="birthdate" value="${birthdate}" /></td>
					</tr>
					<tr>
						<td><form:label path="maritialstatus">
								<spring:message text="maritialstatus :" />
							</form:label></td>
						<td><%-- <form:input path="maritialstatus" /> --%>
							Married <form:radiobutton path="maritialstatus" value="Married" />
							Single <form:radiobutton path="maritialstatus" value="Single" />
						</td>
					</tr>
					<tr>
						<td><form:label path="spousename">
								<spring:message text="Spouse name :" />
							</form:label></td>
						<td><form:input path="spousename" /></td>
					</tr>
				</table>
				</div>

				<div id="profilemain">
					<c:if test="${!empty employee.name}">
						<div id="profilepic">
							<div id='profresult'></div>
							<c:if test="${empty employee.photo}">
								<img src="${resourcesPath}/images/default_profile.png">
							</c:if>
							<c:if test="${!empty employee.photo}">
								<img src="${pageContext.request.contextPath}/edit/imageController/${employee.id}">
							</c:if>
						</div>
						<p style="color: blue">Max upload size is 2MB</p>
						<input type="hidden" name="MAX_FILE_SIZE" value="2194304" />
						<input name="file" id="file" type="file" />
						<input type="button" value="Upload Profile Pic"	onclick="uploadFormData('${employee.id}','profile')" />
					</c:if>
				</div>

			</div>

			<div id="education" class="tabcontent">
				<table>
					<tr>
						<td colspan="2">
							<p id="errmsg" style="color:red"></p>
						</td>
					</tr>
					<tr>
						<td><form:label path="address">
								<spring:message text="Address :" />
							</form:label></td>
						<td><form:input path="address" /></td>
					</tr>
					<tr>
						<td><form:label path="city">
								<spring:message text="City :" />
							</form:label></td>
						<td><form:input path="city" /></td>
					</tr>
					<tr>
						<td><form:label path="state">
								<spring:message text="State : " />
							</form:label></td>
						<td><form:input path="state" /></td>
					</tr>
					<tr>
						<td><form:label path="email">
								<spring:message text="Email : " />
							</form:label></td>
						<td><form:input id="email" path="email" /></td>
					</tr>
					<tr>
						<td><form:label path="contact1">
								<spring:message text="Contact no : " />
							</form:label></td>
						<td><form:input path="contact1" /></td>
					</tr>
					<tr>
						<td><form:label path="contact2">
								<spring:message text="Alternate no : " />
							</form:label></td>
						<td><form:input path="contact2" /></td>
					</tr>
					<tr>
						<td><form:label path="education">
								<spring:message text="Education : " />
							</form:label></td>
						<td><form:input path="education" /></td>
					</tr>
				</table>

			</div>

			<div id="experience" class="tabcontent">
				<table>
					<tr>
						<td><form:label path="exprience">
								<spring:message text="Exprience : " />
							</form:label></td>
						<td><form:input path="exprience" /></td>
					</tr>
					<tr>
						<td><form:label path="designation">
								<spring:message text="Designation : " />
							</form:label></td>
						<td><form:input path="designation" /></td>
					</tr>
					<tr>
						<td><form:label path="department">
								<spring:message text="Department : " />
							</form:label></td>
						<td><form:input path="department" /></td>
					</tr>
					<tr>
						<td><form:label path="industry">
								<spring:message text="Industry : " />
							</form:label></td>
						<td><form:input path="industry" /></td>
					</tr>
					<tr>
						<td><form:label path="salary">
								<spring:message text="Salary : " />
							</form:label></td>
						<td><form:input path="salary" /></td>
					</tr>
					<tr>
						<td><form:label path="prefworkinterest">
								<spring:message text="Preferd work interest : " />
							</form:label></td>
						<td><form:input path="prefworkinterest" /></td>
					</tr>
					<tr>
						<td><form:label path="prefworkloc">
								<spring:message text="Preferd work location : " />
							</form:label></td>
						<td><form:input path="prefworkloc" /></td>
					</tr>
					<tr>
						<td><form:label path="expectedsal">
								<spring:message text="Expected salary : " />
							</form:label></td>
						<td><form:input path="expectedsal" /></td>
					</tr>
					<tr>
						<td><form:label path="worknature">
								<spring:message text="Work nature : " />
							</form:label></td>
						<td><form:input path="worknature" /></td>
					</tr>
					<tr>
						<td><form:label path="areaofwork">
								<spring:message text="Area of work : " />
							</form:label></td>
						<td><form:input path="areaofwork" /></td>
					</tr>
					<tr>
						<td><form:label path="reference">
								<spring:message text="Reference : " />
							</form:label></td>
						<td><form:input path="reference" /></td>
					</tr>
				</table>

			</div>
			<div id="savebuttons">
				<c:if test="${!empty employee.name}">
					<input id="save" type="submit" value="<spring:message text="Edit Employee"/>" />
					<button type="button" onClick="if(!confirm('Are you sure you want to delete?')) return false; else javascript:location.href = '${contextroot}/remove/${employee.id}';" />Delete Employee</button>
				</c:if>
				<c:if test="${empty employee.name}">
					<input id="save" type="submit" value="<spring:message text="Add Employee"/>" />
				</c:if>
				<button type="button" onClick="javascript:location.href = '${contextroot}${sessionScope.prevURL}';" />Return</button>
			</div>
		</form:form>

		<c:if test="${!empty employee.name}">

			<div id="job" class="tabcontent">

				<c:url var="addActionInter" value="/interview/add"></c:url>

				<form:form action="${addActionInter}" commandName="interview"	method="POST" class="interview">
					<table>
						<tr>
							<td><h3>Schedule Interview</h3></td>
							<td><form:hidden path="id" /> <form:hidden path="empId"	value="${employee.id}" /></td>
						</tr>
						<tr>
							<td>Company Name</td>
							<td><form:input path="companyName" />	<td />
						</tr>
						<tr>
							<td>Position</td>
							<td><form:input path="position" />	<td />
						</tr>
						<tr>
							<td>Department</td>
							<td><form:input path="department" /> <td />
						</tr>
						<tr>
							<td>Interview date</td>
							<td><form:input path="interviewDate" class="datepick"	id="interviewDate0" />	<td/>
						</tr>
						<tr>
							<td>Status</td>
							<td><form:input path="status" />	<td />
						</tr>
						<tr>
							<td>Remark</td>
							<td><form:input path="remark" />	<td />
						</tr>
						<tr>
							<td><input type="submit"
								value="<spring:message text="Add Interview"/>" /></td>
						</tr>
					</table>
				</form:form>

				<c:if test="${ !empty employee.interview}">
					<table >
							<col width="15">
  							<col width="15">
							<tr>
								<th></th>
								<th>Company Name</th>
								<th>Position</th>
								<th>Department</th>
								<th  style="width:20px">Interview date</th>
								<th>Status</th>
								<th>Remark</th>
								<th>Edit</th>
							</tr>
							<c:forEach items="${employee.interview}" varStatus="loop" var="item">
								<tr>
									<form:form action="${addActionInter}" commandName="interview"	method="POST" class="interview">
										<td>${loop.index + 1}<form:hidden path="id" /> <form:hidden path="empId" value="${employee.id}" /> <form:hidden path="interviewId"	value="${item.id}" /></td>
										<td><form:input path="companyName"	value="${item.companyName}" />	</td >
										<td><form:input path="position" value="${item.position}" />	</td >
										<td><form:input path="department"	value="${item.department}" />	</td >
										<td ><fmt:formatDate var="interviewDate" pattern="MM/dd/yyyy" value="${item.interviewDate}" /><form:input path="interviewDate" class="datepick"	value="${interviewDate}" />		</td >
										<td><form:input path="status" value="${item.status}" />	</td >
										<td><form:input path="remark" value="${item.remark}" />	</td >
										<td><input type="submit" value="<spring:message text="Edit"/>" /></td>
									</form:form>
								</tr>
							</c:forEach>
					</table>
				</c:if>

<%-- 				<c:forEach items="${employee.interview}" varStatus="loop" var="item">

					<form:form action="${addActionInter}" commandName="interview"	method="POST" class="interview">
						<table>
							<tr>
								<td><h3>Interview number ${loop.index + 1}</h3></td>
								<td><form:hidden path="id" /> <form:hidden path="empId" value="${employee.id}" /> <form:hidden path="interviewId"	value="${item.id}" /></td>
							</tr>
							<tr>
								<td>Company Name</td>
								<td><form:input path="companyName"	value="${item.companyName}" />	<td />
							</tr>
							<tr>
								<td>Position</td>
								<td><form:input path="position" value="${item.position}" />	<td />
							</tr>
							<tr>
								<td>Department</td>
								<td><form:input path="department"	value="${item.department}" />	<td />
							</tr>
							<fmt:formatDate var="interviewDate" pattern="MM/dd/yyyy" value="${item.interviewDate}" />		<tr>
								<td>Interview date & time</td>
								<td><form:input path="interviewDate" class="datepick"	value="${interviewDate}" id="interviewDate${loop.index + 1}" />		<td />
							</tr>
							<tr>
								<td>Status</td>
								<td><form:input path="status" value="${item.status}" />	<td />
							</tr>
							<tr>
								<td>Remark</td>
								<td><form:input path="remark" value="${item.remark}" />	<td />
							</tr>
							<tr>
								<td><input type="submit" value="<spring:message text="Edit Itnterview"/>" /></td>
							</tr>
						</table>
					</form:form>

					<br />
				</c:forEach> --%>

			</div>

			<div id="attachment" class="tabcontent">

			<!-- method='POST' action='uploadFile' -->
			<div id="result"><c:if test="${!empty employee.docdetails}">Click on filename to download file</c:if></div>
				<input name="text" id="filedata" type="hidden"  value = ''/><br/>
				<input name="file" id="file" type="file" multiple="multiple" />
				<input type="button" value="Upload File" onclick="uploadFormData('${employee.id}','attach')" />
					<div id="attachmentListDiv">
					<c:if test="${!empty employee.docdetails}">
					<table id="attachmentList">
						<thead>
							<tr>
								<th>Sr. No.</th>
								<th>Name</th>
								<th>Delete</th>
							</tr>
						</thead>
						<c:set var="attachfiles" value="${employee.docdetails}" />
						<c:set var="numberOfRows" value="1" />
						<c:set var="string2" value="${fn:split(attachfiles, '@@@')}" />
						<%-- <c:if test="${!empty employee.docdetails}"> --%>
						<tbody>
							<c:forEach items="${string2}" var="name1">
								<tr>
									<td>${numberOfRows}</td>
									<td><a class="downloadlink"	href="<%=request.getContextPath()%>/edit/downloadfile/${employee.id}/${name1}/">${name1}</a></td>
									<td><input type="button" name="${name1}" value="Delete"	onclick="deletefile('${name1}','${employee.id}')" /></td>
								</tr>
								<c:set var="numberOfRows" value="${numberOfRows+1}" />
							</c:forEach>
						<%-- </c:if> --%>
						</tbody>
					</table>
					</c:if>
				</div>
			</div>

		</c:if>

		</div>
</body>
</html>