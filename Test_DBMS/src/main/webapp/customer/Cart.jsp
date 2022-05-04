<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DBMS System</title>
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.min.css">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">DBMS</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav me-auto">
				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/secureAccess/loadPage.jsp">Home </a></li>
				<li class="nav-item"><a class="nav-link active"
					href="/Test_DBMS/Cart">Cart <span class="visually-hidden">(current)</span></a></li>


				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/TransactionHistory">Order History</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/ProfileServlet">Profile</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/secureAccess/logout.jsp">Logout</a></li>
			</ul>

		</div>
	</div>
</nav>

</head>

<body>
	<%
	ServletContext context = getServletContext();
	%>
	<form action="/Test_DBMS/PlaceOrder" method="post">
		<table class="table table-hover">
			<thead>
				<tr class="table-warning">
					<th scope="col">Product</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price Sold</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.cartDetails}" var="cartDetail">
					<tr>
						<th scope="row"><c:out value="${cartDetail.getpName()}"></c:out></th>
						<td><c:out value="${cartDetail.getpQuantity()}"></c:out></td>
						<td><c:out value="${cartDetail.getpPricesold()}"></c:out></td>
						<td><a href="#">REMOVE</a></td>
					</tr>
				</c:forEach>
				<tr class="table-dark">
					<td></td>
					<th scope="row">TOTAL</th>
					<td><c:out value="${requestScope.cartTotal}" escapeXml="true"></c:out>$</td>

					<c:choose>
						<c:when test="${cStatus=='silver'}">
							<td>You have got 5% Discount for being silver!</td>
						</c:when>
						<c:when test="${cStatus=='gold'}">
							<td>You have got 10% Discount for being gold!</td>
						</c:when>
						<c:when test="${cStatus=='platinum'}">
							<td>You have got 15% Discount for being platinum!</td>
						</c:when>
					</c:choose>

					

				</tr>
			</tbody>

		</table>
		<div align="center" class="d-grid gap-2">
			<button class="btn btn-lg btn-primary" type="submit">Proceed
				to check out</button>

		</div>
	</form>
</body>
</html>