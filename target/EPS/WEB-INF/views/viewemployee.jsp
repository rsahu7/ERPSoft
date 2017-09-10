<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<spring:url value="/resources" var="resourcesPath" />
<%-- <script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" href="${resourcesPath}/css/jquery-ui.css" />--%>
<spring:url value="/" var="contextroot" />

<style>

</style>


	<div  style="background-color:#F6F6F6; ">
		<table><tr><th colspan = "3">Personal Info</th></tr>
				<c:if test="${!empty employee.name}"><tr>   		<td>Name </td>				<td>:&nbsp;&nbsp;</td><td>${employee.name} ${employee.lastname} ${employee.middlename}</td>   <tr></c:if>
				<c:if test="${!empty employee.age}"><tr>   			<td> Age </td>				<td>:&nbsp;&nbsp;</td><td>${employee.age}</td>   <tr></c:if>
				<c:if test="${!empty employee.birthdate}"><tr>  	<td> Birthdate </td>		<td>:&nbsp;&nbsp;</td><td><fmt:formatDate var="birthdate" pattern="dd/MM/yyyy" value="${employee.birthdate}"/>${birthdate}</td>   <tr></c:if>
				<c:if test="${!empty employee.gender}"><tr>   		<td> Gender </td>			<td>:&nbsp;&nbsp;</td><td>${employee.gender}</td>   <tr></c:if>
				<c:if test="${!empty employee.maritialstatus}"><tr> <td> Maritial status </td>	<td>:&nbsp;&nbsp;</td><td>${employee.maritialstatus}</td>   <tr></c:if>
				<c:if test="${!empty employee.spousename}"><tr>   	<td> Spouse name </td>		<td>:&nbsp;&nbsp;</td><td>${employee.spousename}</td>   <tr></c:if>
				<c:if test="${!empty employee.address}"><tr>   		<td> Address </td>			<td>:&nbsp;&nbsp;</td><td>${employee.address}</td>   <tr></c:if>
				<c:if test="${!empty employee.city}"><tr>   		<td> City </td>				<td>:&nbsp;&nbsp;</td><td>${employee.city}</td>   <tr></c:if>
				<c:if test="${!empty employee.state}"><tr>   		<td> State </td>			<td>:&nbsp;&nbsp;</td><td>${employee.state}</td>   <tr></c:if>
				<c:if test="${!empty employee.email}"><tr>   		<td> Email </td>			<td>:&nbsp;&nbsp;</td><td>${employee.email}</td>   <tr></c:if>
				<c:if test="${!empty employee.contact1}"><tr>  		<td> Contact no </td>		<td>:&nbsp;&nbsp;</td><td>${employee.contact1}</td>   <tr></c:if>
				<c:if test="${!empty employee.contact2}"><tr>   	<td> Alternate no </td>		<td>:&nbsp;&nbsp;</td><td>${employee.contact2}</td>   <tr></c:if>
				<c:if test="${!empty employee.education}"><tr>   	<td> Education </td>		<td>:&nbsp;&nbsp;</td><td>${employee.education}</td>   <tr></c:if>
				<c:if test="${!empty employee.exprience && employee.exprience gt 0.0 }"><tr>   	<td> Experience  </td>		<td>:&nbsp;&nbsp;</td><td>${employee.exprience} years</td>   <tr></c:if>
				<c:if test="${!empty employee.designation}"><tr>   	<td> Designation </td>		<td>:&nbsp;&nbsp;</td><td>${employee.designation}	</td>   <tr></c:if>
				<c:if test="${!empty employee.department}"><tr>   	<td> Department </td>		<td>:&nbsp;&nbsp;</td><td>${employee.department}</td>   <tr></c:if>
				<c:if test="${!empty employee.industry}"><tr>   	<td> Industry </td>			<td>:&nbsp;&nbsp;</td><td>${employee.industry}</td>   <tr></c:if>
				<c:if test="${!empty employee.salary && employee.salary gt 0.0 }"><tr>   		<td> Salary </td>			<td>:&nbsp;&nbsp;</td><td>${employee.salary} c.p.a.</td>   <tr></c:if>
				<c:if test="${!empty employee.prefworkinterest}"><tr><td> Work interested</td>	<td>:&nbsp;&nbsp;</td><td>${employee.prefworkinterest}</td>   <tr></c:if>
				<c:if test="${!empty employee.prefworkloc}"><tr>   	<td> Preferred location </td><td>:&nbsp;&nbsp;</td><td>${employee.prefworkloc}</td>   <tr></c:if>
				<c:if test="${!empty employee.expectedsal && employee.expectedsal gt 0.0 }"><tr> <td> Expected salary </td>	<td>:&nbsp;&nbsp;</td><td>${employee.expectedsal} c.p.a.</td>   <tr></c:if>
				<c:if test="${!empty employee.worknature}"><tr>   	<td> Work nature </td>		<td>:&nbsp;&nbsp;</td><td>${employee.worknature}</td>   <tr></c:if>
				<c:if test="${!empty employee.areaofwork}"><tr>   	<td> Area of work </td>		<td>:&nbsp;&nbsp;</td><td>${employee.areaofwork}</td>   <tr></c:if>
				<c:if test="${!empty employee.reference}"><tr>   	<td> Reference </td>		<td>:&nbsp;&nbsp;</td><td>${employee.reference}</td>   <tr></c:if>
				<tr>
					<td colspan="3">
						<c:forEach items="${employee.interview}" varStatus="loop" var="item">
							<table>
								<tr><td><h3>Interview : ${loop.index + 1}</h3></td></tr>
								<tr><td>Company Name</td>	<td>:&nbsp;&nbsp;</td>		<td>${item.companyName}<td /></tr>
								<tr><td>Position</td>		<td>:&nbsp;&nbsp;</td>		<td>${item.position}<td /></tr>
								<tr><td>Department</td>		<td>:&nbsp;&nbsp;</td>		<td>${item.department}<td/></tr>
									<fmt:formatDate var="interviewDate" pattern="dd/MM/yyyy" value="${item.interviewDate}" />
								<tr><td>Interview date </td><td>:&nbsp;&nbsp;</td>		<td>${interviewDate}<td/></tr>
								<tr><td>Status</td>			<td>:&nbsp;&nbsp;</td>		<td>${item.status}<td/></tr>
								<tr><td>Remark</td>			<td>:&nbsp;&nbsp;</td>		<td>${item.remark}<td/></tr>
							</table>
						</c:forEach>
					</td>
				</tr>
			</table>
	</div>


