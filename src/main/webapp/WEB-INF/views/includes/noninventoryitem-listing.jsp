<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true" %>
<spring:url value="/" var="contextroot" />
<c:set var="formcontext" value="${sessionScope.formContext}" scope="session"  />
<html>
<head>
	<title>NonInventoryItem List</title>
</head>
<body>
<div id="tablecontainer" style="background-color: #F6F6F6; margin: 2%; padding: 10px;">

	<c:if test="${!empty sessionScope.message}">
		<h3>${sessionScope.message}</h3>
		<c:set var="message" value="" scope="session"  />
		<%-- <% request.getSession().setAttribute("message", "");%> --%>
	</c:if>

	<table id="example" class="display" cellspacing="0" width="98%" style="overflow-x:auto">
				<thead>
					<tr>
						<th>ID</th>
						<th>Description</th>
						<th>Group</th>
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

	var data =eval('${NonInventoryItemList}');

	var table = $('#example').DataTable( {
		  "data": data,
		  "order": [],
		  "columns": [
			{ "data": "code" ,
			  "render" : function(data, type, row, meta){
	    			if(type === 'display'){
	    			   return $('<a>')
	    				   .attr('href', '${contextroot}edit/${formName}/'+row.id) 
	    				  /* .attr('href', '#') */
	    				  .attr('onclick', 'viewEmp('+data+')')
	    				  .text('CUS' + data)
	    				  .wrap('<div></div>')
	    				  .parent()
	    				  .html();

	    			} else {
	    			   return data;
	    			}
	    		 }
			},
		    { "data": "description"},
		    { "data": "group"}
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