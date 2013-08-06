<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>Spring Product Application - Northwind</title>
<style type="text/css">
body {
	font-family: sans-serif;
	background: #E0E0E0;
}

table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: 25A0C5;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: A6DEEE;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

h2 {
	font: bold 1.5em "Times New Roman", Times, serif;
	color: 3923D6;
}

table.options {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: black;
	border-width: 1px;
	border-color: ACF3FD;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<Center>

		<c:if test="${!empty products}">
			<table class="hovertable">
			<heading><h2><b>List Of Products</b></h2></heading>
				<tr>
					<th>Product ID</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach items="${products}" var="product">
					<tr onmouseover="this.style.backgroundColor='#ffff66';"
						onmouseout="this.style.backgroundColor='#d4e3e5';">
						<td>${product.PID}</td>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td><a href="edit/${product.PID}">edit</a></td>
						<td><a href="delete/${product.PID}">delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>


<br><br><br>
			<table class="options">
				<tr>
					<th><B>More Options</B></th>
				</tr>
				<tr>
					<td>
						<a href="addProduct">Add Product</a></li>
					</td>
				</tr>
				<tr>
					<td>
						<a href="applyDiscount">Apply Discount</a></li>
					</td>
				</tr>
			</table>

	</Center>
</body>
</html>