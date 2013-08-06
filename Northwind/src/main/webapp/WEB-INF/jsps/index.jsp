<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>Spring Product Application - Northwind</title>
<style type="text/css">
body {
	font-family: sans-serif;
}

.data,.data td {
	border-collapse: collapse;
	width: 200%;
	border: 1px solid #aaa;
	margin: 2px;
	padding: 2px;
	background-color: #5C82FF;
}

.data th {
	font-weight: bold;
	background-color: #5C82FF;
	color: #5C82FF;
}

.data tr {
	background-color: "lightsalmon"
}
</style>
</head>
<body>

	<Center>
		<h1>
			<b>List Of Products</b>
		</h1>

		<c:if test="${!empty products}">
			<table>
				<tr>
					<th>Product ID</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${products}" var="product">
					<tr>
						<td>${product.PID}</td>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td><a href="edit/${product.PID}">edit</a></td>
						<td><a href="delete/${product.PID}">delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<B><h4>More Options</h4></B>
		<ul>
			<table>
				<tr>
					<th>
						<li><a href="addProduct">Add Product</a></li>
					</th>
					</tr>
					<tr>
					<th>
						<li><a href="applyDiscount">Apply Discount</a></li>
					</th>
				</tr>
			</table>
		</ul>
	</Center>
</body>
</html>