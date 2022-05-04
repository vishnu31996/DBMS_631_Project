
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://bootswatch.com/4/lux/bootstrap.min.css">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">

<title>Registration Page</title>
</head>
<style>
body {
	background-image: URL("/Test_DBMS/img/img14.jpg");
	background-repeat: no-repeat;
	background-size: cover;
}

table {
	border-collapse: separate;
	border-spacing: 50px;
}

div.absolute {
	position: absolute;
	width: 100%;
	bottom: 10px;
	align-self: center;
	background-image: inherit;
}
</style>
<body>
	<%
	RequestDispatcher dis = request.getRequestDispatcher("/com/error.jsp");
	dis.include(request, response);
	%>

	<form action="/Test_DBMS/register" method="post">
		<center>
			<br> <br>
			<div>
				<h2 style="color: white;">Registration Page</h2>
			</div>
			<br>
			<div>
				<table>
					<tr>
						<th>First Name:</th>
						<td><input type="text" name="uFname"
							title="Must contain Alphabets only" required></td>
					</tr>
					<tr>
						<th>Last Name:</th>
						<td><input type="text" name="uLname"
							title="Must contain Alphabets only" required></td>
					</tr>
					<tr>
						<th>Address:</th>
						<td><input type="text" name="uAddress"
							title="Must contain Alphabets only" required></td>
					</tr>
					<tr>
						<th>Phone Number:</th>
						<td><input type="text" name="uPhone"
							title="Must contain Alphabets only" required></td>
					</tr>

					<tr>
						<th>Select Membership:</th>
						<td><select name="SAB" >
								<option value="Silver">Silver</option>
								<option value="Gold">Gold</option>
								<option value="Platinum">Platinum</option>
						</select></td>

						<tr>
						<th>Email:</th>
						<td><input type="text" name="uid" required></td>
					</tr>
					<tr>
						<th>Password:</th>
						<td><input type="password" name="upass"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
							required></td>
					</tr>
					<tr>
						<th>Confirm Your Password:</th>
						<td><input type="password" name="rePass"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
							required></td>
					</tr>
					<tr>
						<th>Security Question:</th>
						<td><input type="text" name="uSecurityQ" required></td>
						
					</tr>
					<tr>
						<th>security Answer:</th>
						<td><input type="text" name="uSecurityA" required></td>
					</tr>


				</table>
				<br> <br>
				<button class="btn btn-secondary" type="submit">Next</button>

				<button class="btn btn-secondary" type="reset">Clear</button>
				<br> <br> <a href="/Test_DBMS/com/login.jsp"
					style="color: inherit;">Login Page</a><br> <br> <a
					href="/Test_DBMS/com/forgotPassword.jsp" style="color: inherit;">Forgot
					Password</a><br> <br>
		
		</center>
	</form>
	<div align="center">
		<%
		ServletContext context = getServletContext();
		%>
		<%=context.getInitParameter("footer")%>
	</div>
</body>
</html>