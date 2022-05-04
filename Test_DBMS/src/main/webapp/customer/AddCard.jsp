<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD CARD</title>
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.min.css">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">
<style>
.float-container {
	border: 3px solid #fff;
	padding: 20px;
	max-width: 50%;
}

}
.float-child {
	float: right;
	padding: 20px;
}

img {
	float: right;
	max-width: 18%;
}

.outer {
	display: table;
	position: absolute;
	height: 75%;
	width: 75%;
}

.middle {
	display: table-cell;
	vertical-align: middle;
}

.inner {
	margin-left: 35%;
	margin-right: 20%;
	width: 
}
</style>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>

	<%
	ServletContext context = getServletContext();
	%>

	<div class="float-container">
		<div class="outer">
			<div class="middle">
				<div class="inner">

					<div class="creditCardForm">
						<div class="heading" align="center">
							<h1>ADD Card</h1>
						</div>
						<div class="payment"> 
							<form action="/Test_DBMS/addCard" method="post">
								<div class="form-group owner">
									<label for="owner">Name on Card</label> <input type="text"
										class="form-control" name="owner" required="required">
								</div>
								<div class="form-group CVV">
									<label for="cvv">CVV</label> <input type="text"
										class="form-control" name="cvv" required="required">
								</div>
								<div class="form-group" id="card-number-field">
									<label for="cardNumber">Card Number</label> <input type="text"
										class="form-control" name="cardNumber" required="required">
								</div>
								<div class="form-group" id="card-Address-field">
									<label for="billAddress">Billing Address</label> <input type="text"
										class="form-control" name="billAddress" required="required">
								</div>
								<div class="form-group" id="expiration-date">
									<label>Expiration Date</label> <select name="expiration-date-month" required="required">
										<option value="01">January</option>
										<option value="02">February</option>
										<option value="03">March</option>
										<option value="04">April</option>
										<option value="05">May</option>
										<option value="06">June</option>
										<option value="07">July</option>
										<option value="08">August</option>
										<option value="09">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
									</select> <select name="expiration-date-year">
										<option value="2022">2022</option>
										<option value="2023">2023</option>
										<option value="2024">2024</option>
										<option value="2025">2025</option>
										<option value="2026">2026</option>
										<option value="2027">2027</option>
										<option value="2028">2028</option>
										<option value="2029">2029</option>
										<option value="2030">2030</option>

									</select>
								</div>
								<div class="form-group" id="cardType">
									<label>Card Type</label> <select name="card-Type" required="required">
										<option value="credit">Credit</option>
										<option value="02">Debit</option>
									</select>
								</div>
								<div class="form-group" id="credit_cards">
									<img src="/Test_DBMS/img/creditcardLogo.png" id="cardLogo">
								</div>
								<div class="d-grid gap-2">
									<a href="/Test_DBMS/ProfileServlet">
									<button class="btn btn-lg btn-primary" type="button"
										id="cancelAddCard">Cancel</button></a>
									<button class="btn btn-lg btn-primary" type="submit"
										id="AddCard">Confirm</button>

								</div>
							</form>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
</body>

</html>