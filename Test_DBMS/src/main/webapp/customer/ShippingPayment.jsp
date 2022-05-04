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
<link rel="stylesheet" href="/Test_DBMS/lib/bootstrap.min.css">
<link rel="stylesheet" href="/Test_DBMS/lib/bootstrap.css">
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

.visible {
	visibility: visible;
}

.invisible {
	visibility: hidden;
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
				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/secureAccess/loadPage.jsp">Home </a></li>
				<li class="nav-item"><a class="nav-link" href="/Test_DBMS/Cart">Cart</a></li>

				<li class="nav-item"><a class="nav-link" href="/Test_DBMS/TransactionHistory">Order
						History</a></li>
				<li class="nav-item"><a class="nav-link active"
					href="/Test_DBMS/ProfileServlet">Profile <span
						class="visually-hidden">(current)</span></a></li>


				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/secureAccess/logout.jsp">Logout</a></li>
			</ul>

		</div>
	</div>
</nav>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
	$("#confirm-purchase").click(function() {
		$(".creditCardForm").addClass("invisible");
	});
</script>
</head>

<body>

	<%
	ServletContext context = getServletContext();
	
	%>


	<c:forEach items="${requestScope.Shiperrors}" var="shiperr">
		<div class="alert alert-dismissible alert-danger">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			<c:out value="${shiperr}"></c:out>
		</div>
	</c:forEach>
	<c:forEach items="${requestScope.ShipSuccess}" var="shipS">
		<div class="alert alert-dismissible alert-success">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			<c:out value="${shipS}"></c:out>
		</div>
	</c:forEach>

	<div class="m-4">
		<div class="accordion" id="myAccordion">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingOne">
					<button type="button" class="accordion-button collapsed"
						data-bs-toggle="collapse" data-bs-target="#collapseOne">Shipping
						Address</button>
				</h2>
				<div id="collapseOne" class="accordion-collapse collapse"
					data-bs-parent="#myAccordion">
					<div class="card-body">

						<table class="table table-hover">
							<thead>
								<tr class="table-warning">
									<th scope="col">SAName</th>
									<th scope="col">RecepientName</th>
									<th scope="col">Street</th>
									<th scope="col">SNumber</th>
									<th scope="col">City</th>
									<th scope="col">Zip</th>
									<th scope="col">State</th>
									<th scope="col">Country</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.shipDetails}" var="shipDetail">
									<tr>
										<th scope="row"><c:out value="${shipDetail.getSAName()}"></c:out></th>
										<td><c:out value="${shipDetail.getRecepientName()}"></c:out></td>
										<td><c:out value="${shipDetail.getStreet()}"></c:out></td>
										<td><c:out value="${shipDetail.getSNumber()}"></c:out></td>
										<td><c:out value="${shipDetail.getCity()}"></c:out></td>
										<td><c:out value="${shipDetail.getZip()}"></c:out></td>
										<td><c:out value="${shipDetail.getState()}"></c:out></td>
										<td><c:out value="${shipDetail.getCountry()}"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>

						</table>
						<div>
							<a href="/Test_DBMS/customer/UpdateShipping.jsp">
								<button type="button" class="btn btn-warning">Update
									Shipping Details</button>
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingTwo">
					<button type="button" class="accordion-button collapsed"
						data-bs-toggle="collapse" data-bs-target="#collapseTwo">Credit
						Cards</button>
				</h2>
				<div id="collapseTwo" class="accordion-collapse collapse"
					data-bs-parent="#myAccordion">
					<div class="card-body">

						<table class="table table-hover">
							<thead>
								<tr class="table-warning">
									<th scope="col">Card Number</th>
									<th scope="col">CVV</th>
									<th scope="col">Name on Card</th>
									<th scope="col">Card Type</th>
									<th scope="col">Billing Address</th>
									<th scope="col">Expiry Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.cardDetails}" var="cardDetail">
									<tr>
										<th scope="row"><c:out
												value="${cardDetail.getCardNumber()}"></c:out></th>
										<td><c:out value="${cardDetail.getCvv()}"></c:out></td>
										<td><c:out value="${cardDetail.getName()}"></c:out></td>
										<td><c:out value="${cardDetail.getCardType()}"></c:out></td>
										<td><c:out value="${cardDetail.getBilAddress()}"></c:out></td>
										<td><c:out value="${cardDetail.getExpDate()}"></c:out></td>
									</tr>
								</c:forEach>

							</tbody>

						</table>
						<div>
							<a href="/Test_DBMS/customer/AddCard.jsp">
								<button type="button" class="btn btn-warning">Update
									Card</button>
							</a>
						</div>

					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingThree">
					<button type="button" class="accordion-button collapsed"
						data-bs-toggle="collapse" data-bs-target="#collapseThree">Personal
						Details</button>
				</h2>
				<div id="collapseThree" class="accordion-collapse collapse"
					data-bs-parent="#myAccordion">
					<div class="card-body">
						<table class="table table-hover">
							<thead>
								<tr class="table-warning">
									<th scope="col">FName</th>
									<th scope="col">LName</th>
									<th scope="col">EMail</th>
									<th scope="col">Address</th>
									<th scope="col">Phone</th>
									<th scope="col">Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.userDetails}" var="userDetail">
									<tr>
										<th scope="row"><c:out value="${userDetail.getuFname()}"></c:out></th>
										<td><c:out value="${userDetail.getuLname()}"></c:out></td>
										<td><c:out value="${userDetail.getUid()}"></c:out></td>
										<td><c:out value="${userDetail.getuAddress()}"></c:out></td>
										<td><c:out value="${userDetail.getuPhone()}"></c:out></td>
										<td><c:out value="${userDetail.getSILVER_AND_ABOVE()}"></c:out></td>
									</tr>
								</c:forEach>



							</tbody>

						</table>
						<div>
							<a href="/Test_DBMS/customer/UpdateReg.jsp">
								<button type="button" class="btn btn-warning">Update
									Personal Details</button>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>