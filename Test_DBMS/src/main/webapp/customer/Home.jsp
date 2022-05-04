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
 	float:right;
    max-width:50px;
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
				<li class="nav-item"><a class="nav-link active" href="#">Home
						<span class="visually-hidden">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="/Test_DBMS/Cart">Cart</a></li>

				<li class="nav-item"><a class="nav-link" href="/Test_DBMS/TransactionHistory">Order
						History</a></li>
				<li class="nav-item"><a class="nav-link" href="/Test_DBMS/ProfileServlet">Profile</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/Test_DBMS/secureAccess/logout.jsp">Logout</a></li>
			</ul>

		</div>
	</div>
</nav>

</head>

<body>
 <%
RequestDispatcher dis1=request.getRequestDispatcher("/secureAccess/CartF.jsp");
dis1.include(request, response);
%>
 <%
RequestDispatcher dis=request.getRequestDispatcher("/secureAccess/CartS.jsp");
dis.include(request, response);
%>
	<%
	ServletContext context = getServletContext();
	%>
	<div class="float-container">
		<c:forEach items="${requestScope.prodDetails}" var="prod">
		


			<div class="float-child">

				<div class="card border-primary mb-3" style="max-width: 20rem;">
					<div class="card-header">
					
						<c:out value="${prod.getPname()}" ></c:out>
					</div>
					<div class="card-body">
						<h4 class="card-title"><c:out value="${prod.getPdesc()}" ></c:out></h4>
						<p class="card-text"><c:out value="${prod.getPrice()}" ></c:out></p>
						<a href="/Test_DBMS/addCart?Pid=${prod.getPid()}" >
						<img alt="Add to Cart" src="<%=request.getContextPath()%>/img/cart.png"/></a>
					</div>
				</div>
			</div>
			

		</c:forEach>

	</div>

	<br>
	<br>
	<br>
	<br>




</body>

</html>