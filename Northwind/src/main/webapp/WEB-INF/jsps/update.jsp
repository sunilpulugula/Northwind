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
			<b>Product Details</b>
		</h1>
		<br>
		<br>
		<c:if test="${!empty product}">
			<form method="post" action="update" name="updateForm" onsubmit="">
				<table>
					<tr>
						<td>Product ID</td>
						<td><input type="text" name="Product_ID" id="Product_ID"
							value="${product.PID}" readonly/></td>
					</tr>
					<tr>
						<td>Product Name</td>
						<td><input type="text" name="Product_Name" id="Product_Name"
							value="${product.name}" /></td>
					</tr>
					<tr>
						<td>Price</td>
						<td><input type="text" name="price" id="price"
							value="${product.price}" /></td>
					</tr>
					<tr>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td colspan="2">             
						<input type="submit" value="Update" />         
						</td>
					</tr>
				</table>
			</form>
		</c:if>
	</Center>
</body>
</html>