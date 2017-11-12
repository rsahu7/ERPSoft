<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<spring:url value="/resources" var="resourcesPath" />
<script type="text/javascript" charset="utf8" src="${resourcesPath}/js/jquery-3.1.1.js"></script>
<link rel="stylesheet" href="${resourcesPath}/css/jquery-ui.css" />
<spring:url value="/" var="contextroot" />

<script>
    window.onunload = refreshParent;
    function refreshParent() {
        window.opener.location.reload();
    }
    function deleteSupp(rfqSupplierId) { 
    	var url = ${contextroot}+"/remove/"+rfqSupplierId;
    	 $.ajax({
             url : url,
             success: function(response){
               location.reload();
             },
             error:function(response){
                 alert("something went wrong, try again");
             }
         });
    }
    function jsFunction(value,RFQId,RFQItemId)
    {
    	var name = $("#supplierList option:selected").text(); 
    	var url = ${contextroot}+"/addupdatesupp";
        $.ajax({
            url : url,
            type: "POST",
            data: {
               "supplierId": value,
               "supplierName":name,
               "rfqSupplierId":0,
               "rfqId":RFQId,
               "rfqItemId":RFQItemId
            },
            success: function(response){
              location.reload();
            },
            error:function(response){
                alert("something went wrong, try again");
            }
        });
    }

</script>


	
<body>
	<div id="tablecontainer" style="background-color: #F6F6F6; margin: 2%; padding: 10px;">

			<%-- <div  style="background-color:#F6F6F6; ">
				${RFQId} and the other values are ${RFQItemId}
			</div> --%>
			<div id="Suppliers">Select a supplier :
				<select id="supplierList" name="supplierList"  onmousedown="this.value='';" onchange="jsFunction(this.value,${RFQId},${RFQItemId});">
				   <c:forEach var="supplier" items="${listSupplier}" >
				     <option value="${supplier.id}">
          				<c:out value="${supplier.name}" />
     				</option>
				   </c:forEach>
				</select>
			</div>
			
			<div id="RFQSuppliers">
			<c:if test="${ !empty listRFQSupplier}">
					<table >
							<col width="15">
  							<col width="15">
							<tr>
								<th></th>
								<th>Supplier Name</th>
								<th>Rate</th>
								<th>Delivery Terms</th>
								<th>Edit</th>
								<th>Delete</th>
								
							</tr>
							<c:forEach items="${listRFQSupplier}" varStatus="loop" var="RFQSupplier">
								<tr>
									<form:form action="${contextroot}/addupdatesupp" commandName="rfqSupplier" method="POST" >
										<td>${loop.index + 1}
											<form:hidden id="rfqSupplierId" path="rfqSupplierId" value="${RFQSupplier.rfqSupplierId}" />
											<form:hidden path="rfqId" value="${RFQId}" /> 
											<form:hidden path="supplierId" value="${RFQSupplier.supplierId}" /> 
											<form:hidden path="rfqItemId" value="${RFQItemId}" /></td> 
										<td><form:input path="supplierName"	value="${RFQSupplier.supplierName}" />	</td >
										<td><form:input path="rate" value="${RFQSupplier.rate}" />	</td >
										<td><form:input path="deliveryTerms"	value="${RFQSupplier.deliveryTerms}" />	</td >
										<td><input type="submit" value="<spring:message text="Edit"/>" /></td>
										<td><input id="Delete" onclick="deleteSupp(${RFQSupplier.rfqSupplierId})" type="button" value="<spring:message text="Delete"/>" /></td >
									</form:form>
								</tr>
							</c:forEach>
					</table>
				</c:if>
			</div>


		</div>
</body>

