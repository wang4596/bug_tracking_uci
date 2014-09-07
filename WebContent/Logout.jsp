 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout Page</title>
</head>
<body>
<%
    if ((session.getAttribute("emailID") == null)) {
%>
You are not logged in<br/>
<a href="LoginPage.jsp">Please Login</a>
<%} else {
	
	session.removeAttribute("emailID");
    session.removeAttribute("firstName");
    session.removeAttribute("lastName");
    session.invalidate();
	
%>
       <p>Logout was done successfully.</p>
		<a href='LoginPage.jsp'>Log in</a><br />
<%
    }
%>

</body>
</html>