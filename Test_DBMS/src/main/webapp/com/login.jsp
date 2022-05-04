<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>



 
 
 
 
 
 
 <style>

textarea {
	color: black;
}
</style>
 <%
RequestDispatcher dis1=request.getRequestDispatcher("/com/RegSuccess.jsp");
dis1.include(request, response);
%>
 <%
RequestDispatcher dis=request.getRequestDispatcher("/com/error.jsp");
dis.include(request, response);
%>
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.min.css">
<link rel="stylesheet" href="https://bootswatch.com/4/lux/bootstrap.css">
</head>
<body>
<center>
<br><br><br><br>

<form action="/Test_DBMS/verify" method="post">

<h1>Login Page</h1>
<br><br><br><br>
<table>
<tr><th>
<label>User ID :</label></th>
<td>   <input class="form-control form-control-sm" name="uid" type="text"><br></td></tr>
<tr><th><label>Password :</label></th>
<td> <input class="form-control form-control-sm" name="upass" type="password"></td></tr></table><br><br>
<button type="submit" class="btn btn-primary">Login</button>
 &nbsp; &nbsp;
&nbsp;<button type="reset" class="btn btn-primary">Clear</button>
</form><br>


New User <a href="/Test_DBMS/com/registration.jsp">Register here</a><br><br>

Forgot Password <a href="/Test_DBMS/com/forgotPassword.jsp">Click here</a><br><br>
<%
ServletContext context=getServletContext();
%>

<%= context.getInitParameter("footer") %>
</center>
</body>

</html>