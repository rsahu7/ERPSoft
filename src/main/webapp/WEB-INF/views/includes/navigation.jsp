<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>


	<spring:url value="/resources/js/prefix-free.js" var="prefix" />
		<script src="${prefix}"></script>
	<spring:url value="/resources/css/iconic.css" var="iconic" />
		<link href="${iconic}" rel="stylesheet" />
	<spring:url value="/resources/css/style.css" var="style" />
		<link href="${style}" rel="stylesheet" />
	<spring:url value="/" var="contextroot" />

<!-- <div id="logout" style="margin: 10px; padding: 10px;float: right;">
	<button> Logout </button>
</div>
 -->
 <style>
 .menu li ul { display:none; position:absolute; background-color:#c7c4e2 !important; } 
 .menu li:hover ul{ display:block; background-color:black; height:auto; width:auto; z-index:100} 
 .menu li ul li{ clear:both; border-style:none;} 
 .menu-background {background-color : #c7c4e2}
 </style>


<div class="wrap">
	<div id="logo">
		<%-- <img src="${contextroot}/resources/images/logo.png"> --%>COMPANY LOGO
	</div>
	<nav>
		<ul class="menu">
			<li><a href="#"><span class="iconic home"></span> Masters</a>
			<ul class="submenu">
				<li><a href="${contextroot}form/list/Supplier">Supplier</a></li>
				<li><a href="${contextroot}form/list/Customer">Customer</a></li>
				<li><a href="${contextroot}form/list/Project">Project</a></li>
				<li><a href="${contextroot}form/list/Item">Items</a></li>
				<li><a href="${contextroot}form/list/NonInventoryItem">Non Inventory Item</a></li>
				<li><a href="${contextroot}form/list/Warehouse">Warehouse</a></li>
			</ul>
			</li>
			<li><a href="${contextroot}"><span class="iconic home"></span>Purchase</a>
				<ul class="submenu">
					<li><a href="${contextroot}add/RFQ">Create Request Quotation</a></li>
					<li><a href="${contextroot}form/list/RFQ">Edit Request Quotation</a></li>
					<li><a href="${contextroot}form/list/PurchaseQuot">Purchase Quotation</a></li>
					<li><a href="${contextroot}form/list/PurchaseOrd">Purchase Order</a></li>
				</ul>
			</li>
			<li><a href="${contextroot}addemployee"><span class="iconic home"></span>Sales</a></li>
			<li><a href="${contextroot}setting"><span class="iconic mail"></span> Configuration</a></li>
			<li><a href="${contextroot}logout"><span class="iconic spin"></span> Logout </a></li>
		</ul>
		<div class="clearfix"></div>
	</nav>
</div>