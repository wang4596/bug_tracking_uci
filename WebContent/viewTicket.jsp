<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.db.retryable.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Map.Entry" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Defect Request</title>
<script>
function validateTicketRequestForm(){
/*	 
	var idx = document.getElementById("project").selectedIndex;
	if(idx == 0){
		alert("Please select a project");
		return false;
	}
	
	var project = document.getElementById("project").options[idx].text;
		
	idx = document.getElementById("status").selectedIndex;
	var project = document.getElementById("status").options[idx].text;
	
	idx = document.getElementById("priority").selectedIndex;
	var project = document.getElementById("priority").options[idx].text;
*/	
	var summary = document.getElementById("summary").value;	
	if(summary == ""){
		alert("Please enter summary");
		return false;
	}
	
	var description = document.getElementById("description");
	if(description == ""){
		alert("Please enter description");
		return false;
	}
	
	document.ticketForm.submit();
	 
}
</script>
</head>
<body>
<table align = "center" width="70%" border="1">
		<tr>			
			<td><a href="/BugTracker/HomePage.jsp"><img src="/BugTracker/resources/logo_2.jpg" width="185" height="64" alt="Bug Tracker logo" /></a></td>
			<td align ='center'><a href="OpenTicketsServlet">View All Open Tickets</a></td>
			<td align ='center'><a href='AssignedTicketsServlet'>View Your Assigned Tickets</a></td>
			<td align ='center'><a href='/BugTracker/Account/AccountUpdate.jsp'>Update Account Details</a></td>
			<td align ='center'><a href='Logout.jsp'>Log out</a></td>
		</tr>
	</table>
<form id="ticketform" name="ticketform" method="post" action="updateTicketServlet">
<table width="75%" border="0" cellpadding="10" cellspacing="10" align="center" >
<tr><td>
<input type="submit" value="Save" onclick="validateTicketRequestForm();return false">
<input type="button" name="cancelbtn" value="Cancel" onclick="window.location.href='OpenTicketsServlet'">
</td></tr>
</table>
<table width="75%" border="1" cellpadding="10" cellspacing="10" align="center" >
<center><h1>View Defect Request</h1></center>
<tr>
<td>Defect Number</td>
<td><%=request.getAttribute("id")%></td></tr>
<tr>
<td>Project</td>
<td>
<select id="project" name="project">
<%
String projAttr = request.getAttribute("project").toString();

ArrayList<String> projects = new ArrayList<String>();
GetProjects g =  new GetProjects();
try {
	projects = g.listAllProjects();
	int i=1;
	for(String p:projects) {
		if(p.equals(projAttr)){
			out.println("<option value='"+i+"' selected>"+p+"</option>");
		}else{
			out.println("<option value='"+i+"'>"+p+"</option>");
		}
		i++;
    }

} catch (Exception e) {
	e.printStackTrace();
} 

%>
</select>
</td></tr>
<tr>
<td>Status</td>
<td><select id="status" name="status">
<%
String status = request.getAttribute("status").toString();
String s[] = {"Open", "Closed", "Duplicate"}; 

for (int i=0; i<s.length; i++){
	 if(s[i].equals(status)){
    	 out.println("<option value='"+s[i]+"' selected>"+s[i]+"</option>");
     }
     else{
     	out.println("<option value='"+s[i]+"'>"+s[i]+"</option>");
     }
}
%>
</select>
</td></tr>
<tr>
<td>Priority</td>
<td><select id="priority" name="priority">

<%
String priority = request.getAttribute("priority").toString();
String p[] = {"Medium", "High", "Low", "Critical"}; 

for (int i=0; i<p.length; i++){
	 if(p[i].equals(priority)){
    	 out.println("<option value='"+p[i]+"' selected>"+p[i]+"</option>");
     }
     else{
     	out.println("<option value='"+p[i]+"'>"+p[i]+"</option>");
     }
}
%>
</select></td></tr>
<tr>
<tr>
<td>Assignee</td>
<td><select id="assignee" name="assignee">
 <%
 String assignAttr = request.getAttribute("assignee").toString();
 HashMap<String, String> assignee = new HashMap<String, String>();
	
	GetAssignee a =  new GetAssignee();
	
	try {
		assignee = a.listAllAssignee();
		Set<Entry<String, String>> set = assignee.entrySet();
	    Iterator<Entry<String, String>> i = set.iterator();
	    while(i.hasNext()) {
	         Entry<String, String> entry = i.next();
	         if(entry.getValue().equals(assignAttr)){
	        	 out.println("<option value='"+entry.getKey()+"' selected>"+entry.getValue()+"</option>");
	         }
	         else{
	         	out.println("<option value='"+entry.getKey()+"'>"+ entry.getValue()+"</option>");
	         }
	    }	

	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	
%>
</select></td></tr>
<tr>
<td>Client Email</td>
<td><input type="text" id="clientEmail" name="clientEmail" value="<%=request.getAttribute("clientEmail") %>"></td></tr>
<tr>
<td>Summary</td>
<td><textarea id="summary" name="summary"><%=request.getAttribute("summary")%></textarea></td>
</tr>
<tr>
<td>Description</td>
<td><textarea id="description" name="description"><%=request.getAttribute("description")%></textarea></td></tr>
</table>
<input type="hidden" id="ticketid" name="ticketid" value="<%=request.getAttribute("id")%>">
</form>
</body>
</html>