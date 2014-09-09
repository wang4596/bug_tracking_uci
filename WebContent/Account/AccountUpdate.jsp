<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 	
		Team D Bug Tracker Project
		9/9/2014  	Saurabh Pandit 		Created File
-->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page import="com.db.dao.UserDAO"%>
<%@page import="com.beans.UserBean"%>
<title>Update Account Details</title>
<script>
function validateUpdateAccountForm(){
	var firstName = document.getElementById("firstname");
	var lastName = document.getElementById("lastname");
	var role = document.getElementById("roleSelect");
	if(firstName.value.length<=0 || lastName.value.length<=0 || role.value == "null"){
		alert("please enter all the information");
		return false;
	}else{
		document.updateAccountForm.submit();
	}
}

function validatePassword(){
	var oldPassword = document.getElementById("oldPassword");
	var newPassword1 = document.getElementById("newPassword1");
	var newPassword2 = document.getElementById("newPassword2");
	
	if(oldPassword.value.length<=0 || newPassword1.value.length<=0 || newPassword2.value.length<=0){
		alert("password fields cannot be empty");
		return false;
	}else{
		if(newPassword1.value != newPassword2.value){
			alert("New passwords do not match...");
			return false;
		}
		document.updatePasswordForm.submit();
	}
}
</script>
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
%>
<div>
	<table align = "center" width="70%" border="1">
		<tr>
		<td><a href='/BugTracker/HomePage.jsp'>Home Page</a></td>
		<td><a href='/BugTracker/Logout.jsp'>Log out</a></td>
		</tr>
	</table>
		
		
	<table width="70%" style="border: 1px solid black;"  cellpadding="5" cellspacing="5" align="center" >
			<td>
				<form id="updateAccountForm" name="updateAccountForm" method="post" action="UpdateUserServlet">
					<h4>Update Account Details:</h4>
					<tr><td align="left"><label>First Name:* </label></td><td align="left"><input id = "firstname" type="text" value="<%=currentUser.getFirstName()%>" name="firstname" /><br/></td></tr>
					<tr><td align="left"><label>Last Name:* </label></td><td align="left"><input id = "lastname" type="text" value="<%=currentUser.getLastName()%>" name="lastname" /><br/></td></tr>
					<tr><td align="left"><label id="emailIDLabel">Email: </td><td align="left"><%=currentUser.getEmailID()%> </label></td></tr>
					<INPUT TYPE="HIDDEN" NAME="emailIDHidden" VALUE="<%=currentUser.getEmailID()%>" />
					<tr><td align="left"><label >Role:* </label></td><td align="left">
	            		<select id="roleSelect" name="roleSelect">
	            			<option <%if(currentUser.getRole()!=null && "Help Desk".equalsIgnoreCase(currentUser.getRole().toLowerCase())){%> selected="selected" <%}%> value="Help Desk">Help Desk</option>
	            			<option <%if(currentUser.getRole()!=null && "Developer".equalsIgnoreCase(currentUser.getRole().toLowerCase())){%> selected="selected" <%}%> value="Developer">Developer</option>
	        			</select><br /></td></tr>
					<tr><td align="left"><input type="submit" value="Update Account" onclick="validateUpdateAccountForm();return false"/></td></tr>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<form action="UpdateUserPasswordServlet" id="updatePasswordForm" name="updatePasswordForm" method="post">
					<h4>Update password:</h4>
					<INPUT TYPE="HIDDEN" NAME="emailID" VALUE="<%=currentUser.getEmailID()%>" />
					<tr><td align="left"><label>Current Password:* </label></td><td align="left"><input id = "oldPassword" type="password" name="oldPassword" /></td></tr>
					<tr><td align="left"><label>New Password:* </label></td><td align="left"><input id = "newPassword1" type="password" name="newPassword1" /></td></tr>
					<tr><td align="left"><label>Re-enter Password:* </label></td><td align="left"><input id = "newPassword2" type="password" name="newPassword2" /></td></tr>
					<tr><td align="left"><input type="submit" value="Update Password" onclick="validatePassword();return false"/></td></tr>
				</form>
			</td>
		</tr>
	</table>
</div>
</body>
</html>