
<%@page import="java.lang.ProcessBuilder.Redirect"%>

<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
</head>
<body>
<% 
if(session != null)
{
         session.invalidate();
}
response.sendRedirect("/Test_DBMS/com/login.jsp");
%>
	<%
	ServletContext context = getServletContext();
	%>
</body>
</html>