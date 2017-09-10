<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Employee Page</title>
	<spring:url value="/resources" var="resourcesPath" />
	<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-ui.js"></script>
	<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery.validate.min.js"></script>
	<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/additional-methods.js"></script>
	<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/paging.js"></script>
	<script>
	  $( function() {


        $('#tableData').paging({limit:10});


	    $( "#fromdate" ).datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth : true,
			changeYear : true,
			yearRange : "-80:+0",
			maxDate: 0,
			onSelect: function(selected) {
				          $("#todate").datepicker("option","minDate", selected)
				        }
		});
	    $( "#todate" ).datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth : true,
			changeYear : true,
			yearRange : "-80:+0",
			maxDate: 0,
			onSelect: function(selected) {
		          $("#fromdate").datepicker("option","maxDate", selected)
		        }
		});
	    $("#search").validate({
			rules : {
				name :  {
					minlength : 2,
					maxlength : 15,
					alpha: true
				},
				areaofwork : {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				exprience  : {
					minlength : 1,
					maxlength : 5,
					number: true,
					dollarsscents: true,
					Decimal: true
				},
				salary : {
					minlength : 1,
					maxlength : 6,
					number: true,
					dollarsscents: true,
					Decimal: true
				},
				industry  : {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				education	: {
					minlength : 2,
					maxlength : 50
				},
				designation	: {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				department	: {
					minlength : 2,
					maxlength : 50,
					alpha: true
				},
				worknature	: {
					minlength : 2,
					maxlength : 50,
					alpha: true
				}
			}
	    })
	  });
	  function exportData()
	  {
		  var formdata = $('#search').serialize();
		  var myform = document.getElementById("search");
		  var fd = new FormData(myform);
		  $.ajax({
			  url:"exportData",
			  data : formdata,
			  type : "GET",
			  async : true,
			  success : function(data)
			  {
				  window.open(data);
			  }
		  });
	  }
	</script>
	<link rel="stylesheet" href="${resourcesPath}/css/jquery-ui.css" />
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}

		.paging-nav {
		  /* text-align: right; */
		  padding-top: 2px;
		}

		.paging-nav a {
		  margin: auto 1px;
		  text-decoration: none;
		  display: inline-block;
		  padding: 1px 7px;
		  background: #91b9e6;
		  color: white;
		  border-radius: 3px;
		}

		.paging-nav .selected-page {
		  background: #187ed5;
		  font-weight: bold;
		}

		.paging-nav,
		#tableData {
		/*   width: 400px;
		  margin: 0 auto;
		  font-family: Arial, sans-serif; */
		}
	</style>
</head>
<body>
<jsp:include page="includes/navigation.jsp"/>

<div id="tablecontainer" style="background-color: #F6F6F6; margin: 2%; padding: 10px;">
<h3>
	Search Employees
</h3>
<c:url var="addAction" value="/search" ></c:url>

		<form action="${addAction}" method="post" id="search">
			<table>
				<tr>	<td>Name:			<br> </td><td><input type="text" name="name" value=""> <br>     	</td><td>Surname :			<br> </td><td><input type="text" name="lastname" value=""> <br>    </td> </tr>
				<tr>	<td>Gender:			<br> </td><td><input type="radio" name="gender" value="Male"> Male  <input type="radio" name="gender" value="Female"> Female </td></tr>
				<tr>	<td>Area of work:	<br> </td><td><input type="text" name="areaofwork" value=""> <br>   </td><td>Education :			<br> </td><td><input type="text" name="education" value=""> <br>    </td></tr>
				<tr>	<td>Experience:		<br> </td><td><input type="text" name="exprience" value=""> <br>   	</td><td>Designation:			<br> </td><td><input type="text" name="designation" value=""> <br>  </td></tr>
				<tr>	<td>Salary:			<br> </td><td><input type="text" name="salary" value=""> <br>   	</td><td>Department :			<br> </td><td><input type="text" name="department" value=""> <br>   </td></tr>
				<tr>	<td>Industry:		<br> </td><td><input type="text" name="industry" value=""> <br>   	</td><td>Work nature :			<br> </td><td><input type="text" name="worknature" value=""> <br>   </td></tr>
				<tr>	<td>From & To date :<br> </td><td><input type="text" name="fromdate" id="fromdate"> 	</td><td>to 					<br> </td><td><input  type="text" name="todate" id="todate"><br>  	</td></tr>
				<tr>	<td colspan="2">	<input type="submit" value="Search" onclick = "form.action='search';" /><input type="submit" value="Export" onclick = "form.action='exportData';"/>              		</td></tr>
			</table>
		</form>


<c:choose>
	<c:when test="${!empty listEmployees}">
		<p style="color:blue"> ${msg} </p><br/>
		<h3>Persons List</h3>
		<table class="tg" id="tableData">
			<thead>
				<tr>
					<th width="120">Employee Name</th>
					<th width="80">Contact</th>
					<th width="50">Age</th>
					<th width="150">Work Area</th>
			 		<th width="60">Experience</th>
			 		<th width="60">Salary</th>
			 		<th width="100">Industry</th>
					<th width="60">Edit</th>
					<th width="60">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listEmployees}" var="employee">
					<tr>
						<td>${employee.name} ${employee.lastname}</td>
						<td>${employee.contact1}<c:if test="${!empty employee.contact2}">, ${employee.contact2}</c:if></td>
						<td>${employee.age}</td>
						<td>${employee.areaofwork}</td>
						<td>${employee.exprience}</td>
						<td>${employee.salary}</td>
						<td>${employee.industry}</td>
						<td><a href="<c:url value='/edit/${employee.id}' />" >Edit</a></td>
						<td><a onClick="if(!confirm('Are you sure you want to delete?')) return false;" href="<c:url value='/remove/${employee.id}' />" >Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<h3 style="color:red" > ${errmsg} </h3><br/>
		<p style="color:blue"> ${msg} </p>
	</c:otherwise>
</c:choose>

</div>
</body>
</html>
