<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<%@page import="com.db.dao.UserDAO"%>
<%@page import="com.beans.UserBean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<script type="text/javascript">

</script>
<%	
	
    if ((session.getAttribute("emailID") == null)) {
%>
	<p>You are not logged in</p>
	<a href="LoginPage.jsp">Please Login</a>
<%} else {
	
	UserBean currentUser = UserDAO.getUser(session.getAttribute("emailID").toString());
	
%>
	<p>Welcome <%=currentUser.getFirstName()%>, <%=currentUser.getLastName()%></p>
	<a href='Logout.jsp'>Log out</a><br />
	<a href='createTicket.jsp'>Create Ticket</a>
<%
    }
%>

</body>
</html>