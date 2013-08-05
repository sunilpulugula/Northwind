<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ page isELIgnored="false"%>
</head>
<body>

<Center>
	<h1><b>List Of Products</b></h1>

	<c:if test="${!empty products}">
Products are not empty
<table BGCOLOR = #E0F2F7 CELLSPACING = 5>
			<tr bgcolor="lightsalmon">
				<th>PID</th>
				<th>Product Name</th>
				<th>Price</th>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.PID}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</Center>
</body>
</html>