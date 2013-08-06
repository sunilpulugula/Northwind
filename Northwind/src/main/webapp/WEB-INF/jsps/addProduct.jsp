<html>
<head>
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
			<b>Provide New Product Details</b>
		</h1>
		<br> <br>
		<form method="post" action="add" name="createForm" onsubmit="">
			<table>
				<tr>
					<td>Product Name</td>
					<td><input type="text" name="Product_Name" id="Product_Name" /></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><input type="text" name="price" id="price" /></td>
				</tr>
				<tr>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td colspan="2">             
					<input type="submit" value="Add" />
					</td>
				</tr>
			</table>
		</form>
	</Center>
</body>
</html>