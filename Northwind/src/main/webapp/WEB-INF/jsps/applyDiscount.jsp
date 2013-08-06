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
			<b>Provide Discount percentage</b>
		</h1>
		<br> <br>
		<form method="post" action="discount" name="discountForm" onsubmit="">
			<table>
				<tr>
					<td>Discount Percentage</td>
					<td><input type="text" name="Discount" id="Discount" /></td>
				</tr>
				
				<tr>
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td colspan="2">             
					<input type="submit" value="Apply Discount" />
					</td>
				</tr>
			</table>
		</form>
		<h5>Note: </h5><h6>This Discount will be applicable on all products in the repository.</h6>
	</Center>
</body>
</html>