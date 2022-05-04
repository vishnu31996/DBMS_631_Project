<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ADD Shipping</title>
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
	%>

	<div class="float-container">
		<div class="outer">
			<div class="middle">
				<div class="inner">

					<div class="creditCardForm">
						<div class="heading" align="center">
							<h1>Update Shipping Address</h1>
						</div>
						<div class="payment">
							<form action="/Test_DBMS/addShppingAddress" method="post">
								<div class="form-floating">
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Address Name" name="sAddName"> <label
											for="floatingInput">Address Name</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Recepient Name" name="sRName"> <label
											for="floatingInput">Recepient Name</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Street Name" name="sSName"> <label
											for="floatingInput">Street Name</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Street Number" name="sSNum"> <label
											for="floatingInput">Street Number</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="City" name="sCity"> <label
											for="floatingInput">City</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Zip" name="sZip"> <label
											for="floatingInput">Zip</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="State" name="sState"> <label
											for="floatingInput">State</label>
									</div>
									<div class="form-floating mb-3">
										<input type="text" class="form-control" id="floatingInput"
											placeholder="Country" name="sCountry"> <label
											for="floatingInput">Country</label>
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