 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="com.db.dao.UserDAO"%>
<%@page import="com.beans.UserBean"%>

<title>Account Update Successful</title>
</head>
<body>
<%	UserBean currentUser = null;
    if ((session.getAttribute("emailID") == null)) {
%>
	<p>You are not logged in</p>
	<a href="LoginPage.jsp">Please Login</a>
<%} else {
	currentUser = UserDAO.getUser(session.getAttribute("emailID").toString());
}
	if ((request.getAttribute("UPDATE_MSG") != null)) {
		String msg = request.getAttribute("UPDATE_MSG").toString();
		%>
		<div>
		<h2><%="Update status: "+ msg%></h2>
		</div>
		<a href='/BugTracker/HomePage.jsp'>Home Page</a><br />
		<a href='/BugTracker/Account/AccountUpdate.jsp'>Update Account Details</a><br />
		<a href='/BugTracker/Logout.jsp'>Log out</a><br />
		<table>
			<tr>
				<td><h2>Account Details:</h2></td>
			</tr>
			<tr>
				<td><label>First Name: <%=currentUser.getFirstName()%></label></td>
			</tr>
			<tr>
			<td><label>Last Name: <%=currentUser.getLastName()%></label></td>
			</tr>
			<tr>
			<td><label id="emailIDLabel">Email: <%=currentUser.getEmailID()%></label></td>
			</tr>
			<tr>
			<td><label> Role: <%=currentUser.getRole()%></label></td>
			</tr>
		</table>
		<%
	}
%>
</body>
</html>