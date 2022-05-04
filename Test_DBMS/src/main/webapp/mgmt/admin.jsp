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
<style>
.float-container {
	border: 3px solid #fff;
	padding: 20px;
}

.float-child {
	float: left;
	padding: 20px;
}

img {
	float: right;
	max-width: 50px;
}
</style>

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
				<li class="nav-item"><a class="nav-link active" href="#">Admin
						Home <span class="visually-hidden">(current)</span>
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/secureAccess/logout.jsp">Logout</a></li>
			</ul>

		</div>
	</div>
</nav>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script>


<link rel="stylesheet"
	href="https://formden.com/static/cdn/bootstrap-iso.css" />


<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
</head>

<body>

	<%
	ServletContext context = getServletContext();
	%>


	<div class="bootstrap-iso">
		<div class="container-fluid">
			<div class="row">

				<div class="col-md-6 col-sm-6 col-xs-12">

					<form method="post" action="/Test_DBMS/getAdminData">
						<div class="form-group">
							<label class="control-label" for="date1">Start Date</label> <input
								type="date" name='date1'>
						</div>

						<div class="form-group">
							<label class="control-label" for="date2">End Date</label><input
								type="date" name='date2'>
						</div>
						<div class="form-group">
							<button class="btn btn-primary " name="submit" type="submit">Submit</button>
						</div>
					</form>


					<h3>Compute most frequently sold products</h3>
				
					<table class="table table-hover">
						<thead>
							<tr class="table-warning">
								<th scope="col">Product Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data1}" var="dat1">
								<tr>
									<th scope="row"><c:out value="${dat1}"></c:out></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<h3>compute the products which are sold to the highest number of
						distinct customers</h3>
					
					<table class="table table-hover">
						<thead>
							<tr class="table-warning">
								<th scope="col">Product Name</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data2}" var="dat2">
								<tr>
									<th scope="row"><c:out value="${dat2}"></c:out></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<h3>compute the 10 best customers (in terms of money spent) in
						descending order.</h3>
					<table class="table table-hover">
						<thead>
							<tr class="table-warning">
								<th scope="col">Customer ID</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data3}" var="dat1">
								<tr>
									<th scope="row"><c:out value="${dat3}"></c:out></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<h3>compute the maximum basket total amount per credit card.</h3>
					<table class="table table-hover">
						<thead>
							<tr class="table-warning">
								<th scope="col">Card Number</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data4}" var="dat4">
								<tr>
									<th scope="row"><c:out value="${dat4}"></c:out></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<h3>compute the average selling product price per product type
						for desktops, laptops and printers.</h3>
					<table class="table table-hover">
						<thead>
							<tr class="table-warning">
								<th scope="col">Product Name</th>
								<td>Price</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${data5}" var="dat5">
								<c:forEach items="${data6}" var="dat6">

									<tr>
										<th scope="row"><c:out value="${dat5}"></c:out></th>
									</tr>
									<tr>
										<th scope="row"><c:out value="${dat6}"></c:out></th>
									</tr>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>




				</div>
			</div>
		</div>
	</div>


</body>

</html>