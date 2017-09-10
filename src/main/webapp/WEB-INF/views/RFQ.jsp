<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>

<link href="css/jquery-ui.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/rfq.js"></script>
<link rel="stylesheet" href="css/rfq.css" >
<title>RFQ</title>
</head>
<body>
<div id="rfqdiv">
<table id="rfqhead">
<tr><td>RFQ ID:<input type="text" id="rfqid"></td><td>RFQ Creation Date: <input type="text" id="rfcreatedate"></td></tr>
<tr><td>RFQ Status:<input type="text" id="rfqstatus"></td><td>RFQ Type : <input type="text" id="rfqtype"></td></tr>
</table>
<table id="supt">
<tr><td colspan="2">Selected suppliers</td></tr>

</table>
</div>
<form  id="rfqform" method="get">
<input type="button" value="creare demoRFQ" name="sub" id="sub">
</form>


</body>
</html>