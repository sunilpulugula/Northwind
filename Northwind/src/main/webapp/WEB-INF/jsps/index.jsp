<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ page isELIgnored="false"%>
</head>
<body>

	<h2>Index : List Of Products</h2>

	<c:if test="${!empty products}">
Products are not empty
<table>
			<tr>
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
</body>
</html>