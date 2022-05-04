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
				<li class="nav-item"><a class="nav-link" href="/Test_DBMS/Cart">Cart</a></li>


				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/TransactionHistory">Order History<span
						class="visually-hidden">(current)</span></a></li>
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
	<h1>Transaction History</h1>
	<table class="table table-hover">
		<c:forEach items="${requestScope.transactionDetails}" var="transact">
			<thead>
				<tr class="table-warning">
					<th scope="col">Product Name</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price Sold</th>
					<th scope="col">Transaction Number</th>
					<th scope="col">Transaction Date</th>
					<th scope="col">Order Status</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${transact.getProducts()}" var="tran">
					<tr>
						<th scope="row"><c:out value="${tran.getpName()}"></c:out></th>
						<td><c:out value="${tran.getpQuantity()}"></c:out></td>
						<td><c:out value="${tran.getpPricesold()}"></c:out>$</td>
				</c:forEach>
				<td><c:out value="${transact.getBid()}"></c:out></td>
				<td><c:out value="${transact.getTDate()}"></c:out></td>
				<td><c:out value="${transact.getTstatus()}"></c:out></td>
				<c:if test="${transact.getTstatus()=='Processing'}">
					<td><a
						href="/Test_DBMS/cancelOrder?Bid=${transact.getBid()}">
							Cancel Order </a></td>

				</c:if>
				</tr>
				<tr class="table-dark">
					<td></td>
					<th scope="row">TOTAL</th>
					<td><c:out value="${transact.getTCost()}" escapeXml="true"></c:out>$</td>
					<td></td>

				</tr>
			</tbody>
		</c:forEach>
	</table>

</body>
</html>