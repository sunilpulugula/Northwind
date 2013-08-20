<html>
<head>
<title>Spring Product Application - Northwind</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript">
	function validateForm() {
		var discount = document.getElementById("discount").value;
		if (discount == "" || discount == null) {
			alert("Discount should not be empty!!!");
			return false;
		}
		if (!validatePrice(discount)) {
			alert("Discount is not a valid Integer!!!");
			return false;
		}
		if (discount < 0) {
			alert("Discount can not be negative Integer!!!");
			return false;
		}
		return confirm('Are you sure, you want to apply ' + discount
				+ '% discount on all products ?')
	}

	function validatePrice(price) {
		return !isNaN(parseInt(price * 1));
	}
	
	function doAjaxPost() {
		if (validateForm()) {
			var discount = $('#discount').val();
			$.ajax({
				type : "POST",
				url : "/Northwind/discount",
				data : "discount=" + discount,
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
			<b>Provide Discount percentage</b>
		</h2>
		<table>
			<tr>
				<td>Discount Percentage</td>
				<td><input type="text" name="discount" id="discount" value"0.0"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="Apply Discount"
					onclick="doAjaxPost()">           </td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr>
				<td colspan="2"><div id="info" style="color: red;"></div></td>
			</tr>
		</table>
		<h6>Note: This Discount will be applicable on all products in the
			repository.</h6>
			<h6>
				<a href="/Northwind/">Go Home</a>
		</h6>
	</Center>
</body>
</html>