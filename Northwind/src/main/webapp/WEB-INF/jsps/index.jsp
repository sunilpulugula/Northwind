<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<table>
<tr>
	<th>PID</th>
	<th>Product Name</th>
	<th>Price</th>
</tr>
<c:forEach items="${products}" var="product">
	<tr>
		<td>${product.PID}</td>
		<td>${product.Name}</td>
		<td>${product.Price}</td>
	</tr>
</c:forEach>
</table>
</c:if>

</body>
</html>
