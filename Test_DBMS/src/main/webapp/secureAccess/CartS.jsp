<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<%
List<String> success=(List<String>)request.getAttribute("CartS");
if(success!=null){
Iterator<String> it=success.iterator();
out.print("<font color='green'><ul>");
while(it.hasNext()){
	out.print("<li>"+it.next()+"</li>");
}
out.print("</ul></font>");
success=null;
}

%>
</body>
</html>