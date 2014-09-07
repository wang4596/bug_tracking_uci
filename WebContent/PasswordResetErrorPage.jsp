 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Reset Error</title>
</head>
<body>
<% 
	if ((request.getAttribute("ERROR_MSG") != null)) {
		String error = request.getAttribute("ERROR_MSG").toString();
		%>
		<script>
		alert("<%=error%>");
		</script>
		<div>
		<%="Error occured while reseting your password: "%><br />
		<%="Error messge: "+ error%>
		</div>
		<br /><a href='LoginPage.jsp'>Log in</a><br />
		<%
	}
%>
</body>
</html>