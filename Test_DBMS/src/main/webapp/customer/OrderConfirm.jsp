<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.min.css">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">
<link rel="stylesheet" href="/Test_DBMS/lib/OrderConfirm.css">
</head>
<script src="https://code.iconify.design/2/2.2.1/iconify.min.js"></script>
<body>
	<%
	ServletContext context = getServletContext();
	%>
	<div class="card">
		<div class="title">
			Thank You for shopping with us <span class="iconify"
				data-icon="bi:emoji-smile"></span>
		</div>
		<div class="info">
			<div class="row">
				<div class="col-7">

					<span id="heading">Date</span><br> <span id="details"><c:out
							value="${requestScope.transactionDetails.getTDate()}"
							escapeXml="true"></c:out></span>
				</div>
				<div class="col-5 pull-right">
					<span id="heading">Order No.</span><br> <span id="details"><c:out
							value="${requestScope.transactionDetails.getBid()}"
							escapeXml="true"></c:out></span>
				</div>
			</div>
		</div>
		<div class="pricing">
			<div class="row">
				<c:forEach items="${requestScope.cartDetails}" var="cartDetail">
					<div class="col-9">
						<span id="name"><c:out value="${cartDetail.getpName()}">
							</c:out></span>
					</div>
					<div class="col-3">
						<span id="price"><c:out
								value="${cartDetail.getpPricesold()}">
							</c:out>$</span>
					</div>
				</c:forEach>
			</div>



			<div class="total">
				<div class="row">
					<div class="col-9"></div>
					<div class="col-3">
						<big><c:out
								value="${requestScope.transactionDetails.getTCost()}"
								escapeXml="true"></c:out>$</big>
					</div>
				</div>
			</div>
		</div>
		<div class="tracking">
			<div class="title">Track Your Order</div>
		</div>
		<div class="progress-track">
			<ul id="progressbar">
				<li class="step0 active " id="step1">Ordered</li>
				<li class="step0 text-center" id="step2">Shipped</li>
				<li class="step0 text-right" id="step3">On the way</li>
				<li class="step0 text-right" id="step4">Delivered</li>
			</ul>
		</div>
		<div class="footer">
			<div class="row">
				<div class="col-2">
					<p>@Newark IT Company</p>
				</div>
				<div class="col-10">
					Want any help? Please &nbsp;<a>contact us</a>
				</div>
				<div class="d-grid gap-2"></div>
				<a href="/Test_DBMS/secureAccess/loadPage.jsp">
					<button class="btn btn-warning" autofocus="autofocus" type="button">Continue
						&nbsp; Shopping</button>
				</a>
			</div>
		</div>


	</div>

</body>
</html>