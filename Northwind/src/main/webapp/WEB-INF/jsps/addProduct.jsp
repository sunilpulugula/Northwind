<html>
<head>
<title>Spring Product Application - Northwind</title>
<style type="text/css">
body {
	font-family: sans-serif;
	background: #E0E0E0;
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

form {
	display: block;
	background-color: A6DEEE;
	padding: 20px;
	width: 540px; /*200 + 300 + (20 * 2)*/
}

h2 {
	font: bold 1.5em "Times New Roman", Times, serif;
	color: 3923D6;
}
</style>
</head>
<body>
	<Center>
		<h2>
			<b>Provide New Product Details</b>
		</h2>
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
					<td colspan="2">             <input type="submit" value="Add" />
					</td>
				</tr>
			</table>
		</form>
	</Center>
</body>
</html>