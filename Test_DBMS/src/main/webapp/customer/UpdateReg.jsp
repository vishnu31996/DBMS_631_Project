<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Details</title>
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
	width: /*whatever width you want*/;
}
</style>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js">
	
</script>
<body>

	<%
	ServletContext context = getServletContext();
	//out.print(session.getAttribute("uid"));
	%>

	<div class="float-container">
		<div class="outer">
			<div class="middle">
				<div class="inner">

					<div class="creditCardForm">
						<div class="heading" align="center">
							<h1>Update Personal Details</h1>
						</div>
						<div class="payment">
							<form action="/Test_DBMS/UpdateReg" method="post">
								<div class="form-floating">
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="First Name" name="uFname"> <label
											for="floatingInput">First Name</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Last Name" name="uLname"> <label
											for="floatingInput">Last Name</label>
									</div>
									<div class="form-floating mb-3">
										<input class="form-control" id="floatingInput" type="text"
											placeholder="<c:out value="${sessionScope.uid}" escapeXml="true"></c:out>"
											readonly="<c:out value="${requestScope.uId}" escapeXml="true"></c:out>">
										<label for="floatingInput">Email </label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Address Number" name="uAddress"> <label
											for="floatingInput">Address Number</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Phone" name="uPhone"> <label
											for="floatingInput">Phone</label>
									</div>
									<div class="form-group" id="cardType">
										<label>Membership</label> <select name="SAB" required>
											<option value="Silver">Silver</option>
											<option value="Gold">Gold</option>
											<option value="Platinum">Platinum</option>
										</select>
									</div>
									<div class="d-grid gap-2">
										<a href="/Test_DBMS/ProfileServlet">
											<button class="btn btn-lg btn-primary" type="button"
												id="cancelAddShipping">Cancel</button>
										</a>
										<button class="btn btn-lg btn-primary" type="submit"
											id="AddShippingAddress">Confirm</button>

									</div>
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