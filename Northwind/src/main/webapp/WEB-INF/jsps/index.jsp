<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>List Of Products</title>
	<!--<style type="text/css">
		body {
			font-family: sans-serif;
		}
		.data, .data td {
			border-collapse: collapse;
			width: 100%;
			border: 1px solid #aaa;
			margin: 2px;
			padding: 2px;
		}
		.data th {
			font-weight: bold;
			background-color: #5C82FF;
			color: white;
		}
	</style>-->
</head>
<body>


${message}


<h2>List Of Products</h2>

<c:if  test="${!empty products}">
<table class="data">
<tr>
	<th>PID</th>
	<th>Product Name</th>
	<th>Price</th>
	<th>&nbsp;</th>
</tr>
<c:forEach items="${products}" var="product">
	<tr>
		<td>${product.PID}, ${product.Name} </td>
		<td>${product.Price}</td>
	</tr>
</c:forEach>
</table>
</c:if>


</body>
</html>
