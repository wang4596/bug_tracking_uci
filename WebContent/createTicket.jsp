<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Defect Request</title>
<script>
function validateTicketRequestForm(){
	
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
<form id="ticketform" name="ticketform" method="post" action="createTicketServlet">
<input type="submit" value="Save" onclick="validateTicketRequestForm();return false">
<input type="button" name="cancelbtn" value="Cancel" onClick="">
<table width="75%" border="1" cellpadding="10" cellspacing="10" align="center" >
<h1>New Defect Request</h1>
<tr>
<td>Defect Number</td>
<td></td></tr>
<tr>
<td>Project</td>
<td>
<select id="project" name="project">
   <option value="0"></option>
   <option value="1">Project 1</option>
   <option value="2">Project 2</option>
   <option value="3">Project 3</option>
   <option value="4">Project 4</option>
</select>
</td></tr>
<tr>
<td>Status</td>
<td><select id="status" name="status">
   <option value="1">Open</option>
   <option value="2">Closed</option>
   <option value="3">Duplicate</option>
</select>
</td></tr>
<tr>
<td>Priority</td>
<td><select id="priority" name="priority">
   <option value="1">Medium</option>
   <option value="2">High</option>
   <option value="3">Low</option>
   <option value="4">Critical</option>
</select></td></tr>
<tr>
<td>Summary</td>
<td><textarea id="summary" name="summary"></textarea></td>
</tr>
<tr>
<td>Description</td>
<td><textarea id="description" name="description"></textarea></td></tr>
</table>
</form>
</body>
</html>