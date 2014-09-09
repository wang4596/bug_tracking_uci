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
		<table align = "center" width="70%" border="1">
		<tr>
			<td><a href='/BugTracker/HomePage.jsp'>Home Page</a></td>
			<td><a href='/BugTracker/Account/AccountUpdate.jsp'>Update Account Details</a></td>
			<td><a href='/BugTracker/Logout.jsp'>Log out</a><br /></td>
			</tr>
		</table>
		<div>
		<h4 align = "center"><%="Update status: "+ msg%></h4>
		</tr>
		</div>
		<table width="70%" border="1" cellpadding="10" cellspacing="10" align="center" >
			<h4 align="center">Account Details:</h4>
			<tr><td align="left"><label>First Name: </td><td><%=currentUser.getFirstName()%></label></td></tr>
			<tr><td align="left"><label>Last Name: </td><td><%=currentUser.getLastName()%></label></td></tr>
			<tr><td align="left"><label id="emailIDLabel">Email: </td><td><%=currentUser.getEmailID()%></label></td></tr>
			<tr><td align="left"><label> Role: </td><td><%=currentUser.getRole()%></label></td></tr>
		</table>
		<%
	}
%>
</body>
</html>