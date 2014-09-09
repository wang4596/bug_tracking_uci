 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Reset Successful</title>
</head>
<body>
<% 
	if ((request.getAttribute("SUCCESS_MSG") != null)) {
		String msg = request.getAttribute("SUCCESS_MSG").toString();
		%>
		<div>
		<p><%="Password reset update: "+ msg%></p>
		</div>
		<br /><a href='LoginPage.jsp'>Log in</a><br />
		<%
	}
%>
</body>
</html>