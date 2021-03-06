<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ page isELIgnored="false"%>
<title>Spring Product Application - Northwind</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">
	function validateForm() {
		var productID = document.getElementById("productID").value;
		var productName = document.getElementById("productName").value;
		var price = document.getElementById("price").value;
		var qunatityPerUnit = document.getElementById("qunatityPerUnit").value;
		var unitsInStock = document.getElementById("unitsInStock").value;
		var unitsOnOrder = document.getElementById("unitsOnOrder").value;
		if (productName == "" || productName == null) {
			alert("Product name should not be empty!!!");
			return false;
		}
		if (price == "" || price == null) {
			alert("Product price should not be empty!!!");
			return false;
		}
		if (qunatityPerUnit == "" || qunatityPerUnit == null) {
			alert("Qunatity Per Unit should not be empty!!!");
			return false;
		}
		if (unitsInStock == "" || unitsInStock == null) {
			alert("Units In Stock should not be empty!!!");
			return false;
		}
		if (unitsOnOrder == "" || unitsOnOrder == null) {
			alert("Units On Order should not be empty!!!");
			return false;
		}
		if (!(validateNumber(price, "price")
				&& validateNumber(qunatityPerUnit, "QunatityPerUnit")
				&& validateNumber(unitsInStock, "UnitsInStock") && validateNumber(
				unitsOnOrder, "UnitsOnOrder"))) {
			return false;
		}

		return confirm('Are you sure, you want to update product with ID : '
				+ productID + ' ?');
	}

	function validateNumber(value, name) {
		if (isNaN(parseInt(value * 1))) {
			alert(name + " is not a valid Integer!!!");
			return false;
		}
		if (value < 0) {
			alert(name + " can not be negative!!!");
			return false;
		}
		return true;
	}
	
	function doAjaxPost() {
		if (validateForm()) {
			var productID = $('#productID').val();
			var productName = $('#productName').val();
			var price = $('#price').val();
			var qunatityPerUnit = $('#qunatityPerUnit').val();
			var unitsInStock = $('#unitsInStock').val();
			var unitsOnOrder = $('#unitsOnOrder').val();

			$.ajax({
				type : "POST",
				url : "/Northwind/productApp/edit",
				data : "productID=" + productID +  "&name=" + productName + "&price=" + price
						+ "&qunatityPerUnit=" + qunatityPerUnit
						+ "&unitsInStock=" + unitsInStock + "&unitsOnOrder="
						+ unitsOnOrder,
				success : function(response) {
					$('#info').html(response);
				},
				error : function(error) {
					$('#info').html(error);
				}
			});
		}
	}
</script>
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

h6 {
	font: bold 0.75em "Times New Roman", Times, serif;
	color: 3923D6;
}
</style>
</head>
<body>
	<Center>
		<h2>
			<b>Product Details</b>
		</h2>
		<c:if test="${!empty product}">
			<table>
				<tr>
					<td>Product ID</td>
					<td><input type="text" name="productID" id="productID"
						value="${product.productID}" /></td>
				</tr>
				<tr>
					<td>Product Name</td>
					<td><input type="text" name="productName" id="productName"
						value="${product.name}" /></td>
				</tr>
				<tr>
					<td>Unit Price</td>
					<td><input type="text" name="price" id="price"
						value="${product.price}" /></td>
				</tr>
				<tr>
					<td>QunatityPerUnit</td>
					<td><input type="text" name="qunatityPerUnit"
						id="qunatityPerUnit" value="${product.qunatityPerUnit}" /></td>
				</tr>
				<tr>
					<td>UnitsInStock</td>
					<td><input type="text" name="unitsInStock" id="unitsInStock"
						value="${product.unitsInStock}" /></td>
				</tr>
				<tr>
					<td>UnitsOnOrder</td>
					<td><input type="text" name="unitsOnOrder" id="unitsOnOrder"
						value="${product.unitsOnOrder}" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="Update"
						onclick="doAjaxPost()"></td>
				</tr>
				<tr></tr>
			<tr></tr>
			<tr>
				<td colspan="2"><div id="info" style="color: red;"></div></td>
			</tr>
			</table>
		</c:if>
		<h6>
				<a href="/Northwind/productApp/">Go Home</a>
		</h6>
	</Center>
</body>
</html>