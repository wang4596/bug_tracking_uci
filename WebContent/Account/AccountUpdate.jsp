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
		<a href='/BugTracker/HomePage.jsp'>Home Page</a><br />	
		<a href='/BugTracker/Logout.jsp'>Log out</a><br />
		
	<table>
		<tr>
			<td>
				<form id="updateAccountForm" name="updateAccountForm" method="post" action="UpdateUserServlet">
					<h2>Update Account Details:</h2>
					<label>First Name:* </label><input id = "firstname" type="text" value="<%=currentUser.getFirstName()%>" name="firstname" /><br/>
					<label>Last Name:* </label><input id = "lastname" type="text" value="<%=currentUser.getLastName()%>" name="lastname" /><br/>
					<label id="emailIDLabel">Email: <%=currentUser.getEmailID()%> </label><br/>
					<INPUT TYPE="HIDDEN" NAME="emailIDHidden" VALUE="<%=currentUser.getEmailID()%>" />
					<label >Role:* </label>
	            		<select id="roleSelect" name="roleSelect">
	            			<option value="Help Desk">Help Desk</option>
	            			<option value="Developer">Developer</option>
	        			</select><br />
					<input type="submit" value="Update Account" onclick="validateUpdateAccountForm();return false"/>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<form action="UpdateUserPasswordServlet" id="updatePasswordForm" name="updatePasswordForm" method="post">
					<h2>Update password:</h2>
					<INPUT TYPE="HIDDEN" NAME="emailID" VALUE="<%=currentUser.getEmailID()%>" />
					<label>Current Password:* </label><input id = "oldPassword" type="password" name="oldPassword" /><br/><br />
					<label>New Password:* </label><input id = "newPassword1" type="password" name="newPassword1" /><br/><br/>
					<label>Re-enter Password:* </label><input id = "newPassword2" type="password" name="newPassword2" /><br/><br/>
					<input type="submit" value="Update Password" onclick="validatePassword();return false"/>
				</form>
			</td>
		</tr>
	</table>
</div>
</body>
</html>