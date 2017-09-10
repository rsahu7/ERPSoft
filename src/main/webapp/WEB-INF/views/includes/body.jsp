<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>
<spring:url value="/" var="contextroot" />
<html>
<head>
	<title>Homepage</title>
</head>
<body>
<div id="tablecontainer" style="background-color: #F6F6F6; margin: 2%; padding: 10px;">

	<c:if test="${!empty sessionScope.message}">
		<h3>${sessionScope.message}</h3>
		<c:set var="message" value="" scope="session"  />
		<%-- <% request.getSession().setAttribute("message", "");%> --%>
	</c:if>

<button id="showhide" style="margin-left: 5px;"> Show /Hide advance search </button>

<button id="print" style="margin-left: 5px;" onclick="printDiv('example_wrapper')"> Print </button>

<a href="<%=request.getContextPath()%>/downloadExcel"> <button style="margin-left: 5px;" id="print">Export Employee Data </button></a>

<div id="advancesearch">
	<table cellpadding="3" cellspacing="0" border="0" style="width: 67%; margin: 0 auto 2em auto;">
        <thead>
            <tr>
                <th>Search Column</th>
                <th>Search Text</th>
            </tr>
        </thead>
        <tbody>
        <!--
            <tr id="filter_global">
                <td>Global search</td>
                <td align="center"><input type="text" class="global_filter" id="global_filter"></td>
            </tr>
            <tr id="filter_col1" data-column="0">
                <td>Column - Employee ID</td>
                <td align="center"><input type="text" class="column_filter" id="col0_filter"></td>
            </tr>
             -->
            <tr id="filter_col2" data-column="1">
                <td>Name</td>
                <td align="center"><input type="text" class="column_filter" id="col1_filter"></td>
            </tr>
            <tr id="filter_col4" data-column="4">
                <td>City</td>
                <td align="center"><input type="text" class="column_filter" id="col4_filter"></td>
            </tr>
            <tr id="filter_col5" data-column="5">
                <td>Work Nature</td>
                <td align="center"><input type="text" class="column_filter" id="col5_filter"></td>
            </tr>
            <tr id="filter_col6" data-column="6">
                <td>Qualification</td>
                <td align="center"><input type="text" class="column_filter" id="col6_filter"></td>
            </tr>
            <tr id="filter_col8" data-column="8">
                <td>Preferred Work</td>
                <td align="center"><input type="text" class="column_filter" id="col8_filter"></td>
            </tr>
        </tbody>
    </table>
</div>

	<table id="example" class="display" cellspacing="0" width="98%" style="overflow-x:auto">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Contact</th>
						<th>Email</th>
						<th>City</th>
						<th>Work Nature</th>
						<th>Qualification</th>
						<th>Exp</th>
						<th>Preferred Work</th>
					</tr>
				</thead>
	</table>
</div>

<div id = "viewdialog">
</div>

</body>
</html>
<script type="text/javascript">


function filterGlobal () {
    $('#example').DataTable().search(
        $('#global_filter').val(),
        $('#global_regex').prop('checked'),
        $('#global_smart').prop('checked')
    ).draw();
}

function filterColumn ( i ) {
    $('#example').DataTable().column( i ).search(
        $('#col'+i+'_filter').val(),
        $('#col'+i+'_regex').prop('checked'),
        $('#col'+i+'_smart').prop('checked')
    ).draw();
}

$(document).ready(function(){

	 $("#advancesearch").hide();

	var data =eval('${employeeList}');

	var table = $('#example').DataTable( {
		  "data": data,
		  "order": [],
		  "columns": [
			{ "data": "id" ,
			  "render" : function(data, type, row, meta){
	    			if(type === 'display'){
	    			   return $('<a>')
	    				   .attr('href', '${contextroot}edit/'+data) 
	    				  /* .attr('href', '/HRMSB/edit/5') */
	    				  /* .attr('onclick', 'viewEmp('+data+')') */
	    				  .text(data)
	    				  .wrap('<div></div>')
	    				  .parent()
	    				  .html();

	    			} else {
	    			   return data;
	    			}
	    		 }
			},
		    { "data": "name",  "mRender":function(data, type, full){return full.name + " " +full.lastname ;} },
		    { "data": "contact1"},
		    { "data": "email"},
		    { "data": "city"},
		    { "data": "worknature"},
		    { "data": "education"},
		    { "data": "exprience"},
		    { "data": "prefworkinterest"}
		  ],
		  "paging":true
		});

	 $('input.global_filter').on( 'keyup click', function () {
	        filterGlobal();
     } );

     $('input.column_filter').on( 'keyup click', function () {
        filterColumn( $(this).parents('tr').attr('data-column') );
     } );

     $("#showhide").click(function(){
         $("#advancesearch").toggle(1000);
     });

});

function viewEmp(data) {
	var url=data;
	 $( "#viewdialog" ).dialog({
    	modal: true,
        open: function ()
        {

        	$("#viewdialog").load('view/'+url);
        },
        buttons: {
           EDIT: function() {
           	 	$( "#viewdialog" ).dialog("close");
            	window.open('edit/'+url ,'_self');
            },
			/* DELETE: function() {
				$( "#viewdialog" ).dialog("close");
           		window.open('remove/'+url ,'_self');
            }, */
        },
        width: 500,
        title: 'View Employee Details'
    });
}

function printDiv(divName) {
	var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;
     document.body.innerHTML = printContents;
     window.print();
     document.body.innerHTML = originalContents;
}
</script>