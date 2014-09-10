<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 	
		Team D Bug Tracker Project
		9/6/2014	Melissa Stratton 		Created File
-->
		 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<%@page import="com.db.dao.UserDAO"%>
<%@page import="com.beans.UserBean"%>
<%@page import="com.beans.TicketBean"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View All Open Tickets Page</title>
</head>
<body>
<script type="text/javascript">

</script>
<%	
	
    if ((session.getAttribute("emailID") == null)) {
%>
		<p>You are not logged in</p>
		<a href="LoginPage.jsp">Please Login</a>
<%
	} else {
	
		UserBean currentUser = UserDAO.getUser(session.getAttribute("emailID").toString());
	 
%>
		<table align = "center" width="70%" border="1">
		<tr>			
			<td><a href="/BugTracker/HomePage.jsp"><img src="/BugTracker/resources/logo_2.jpg" width="185" height="64" alt="Bug Tracker logo" /></a></td>
			<td align ='center'><a href='createTicket.jsp'>Create Ticket</a></td>
			<td align ='center'><a href='AssignedTicketsServlet'>View Your Assigned Tickets</a></td>
			<td align ='center'><a href='/BugTracker/Account/AccountUpdate.jsp'>Update Account Details</a></td>
			<td align ='center'><a href='/BugTracker/Logout.jsp'>Log Out</a></td>
		</tr>
	</table>
<%		
		ArrayList<TicketBean> rows = new ArrayList<TicketBean>(); 
		rows = (ArrayList<TicketBean>)request.getAttribute("tickets");  

    	if (rows.size() > 0) { 
%>
			<h1>Open Tickets</h1>
				<table border="1" width="100%">
				<!-- column headers -->
					<tr>
						<TH>Ticket ID</TH>
						<TH>Summary</TH>
						<TH>Status</TH>
						<TH>Project</TH>
						<TH>Priority</TH>
						<TH>Assignee</TH> 
					</tr>
				<!-- column data -->
<%				for (int i = 0; i < rows.size(); i++) {
					out.println("<tr><td><a href='ViewTicketServlet?ticketId=" + ((TicketBean)rows.get(i)).getId() + "'>" + ((TicketBean)rows.get(i)).getId() + "</a></td>");
					out.println("<td>" + ((TicketBean)rows.get(i)).getSummary() + "</td>");
					out.println("<td>" + ((TicketBean)rows.get(i)).getStatus() + "</td>");
					out.println("<td>" + ((TicketBean)rows.get(i)).getProject() + "</td>");
					out.println("<td>" + ((TicketBean)rows.get(i)).getPriority() + "</td>");
					out.println("<td>" + ((TicketBean)rows.get(i)).getAssignee() + "</td></tr>");
				} 
%>
				</table>
<%
		} else {
%>
			<h1>There are not any Open Tickets.</h1>
<%
		}
	}
%>

</body>
</html>