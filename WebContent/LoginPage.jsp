 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 	
		Team D Bug Tracker Project
		8/28/2014  	Saurabh Pandit 		Created File
		9/7/2014	Melissa Stratton	Changed Team Lead role to Developer
-->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<script>
function validateRegistrationForm(){
	var firstName = document.getElementById("firstname");
	var lastName = document.getElementById("lastname");
	var emailID = document.getElementById("emailID");
	var password = document.getElementById("newPassword");
	var role = document.getElementById("roleSelect");
	if(firstName.value.length<=0 || lastName.value.length<=0 || emailID.value.length<=0 || password.value.length <= 0 || role.value == "null"){
		alert("please enter all the information");
		return false;
	}else{
		//alert("else");
		document.regForm.submit();
	}
}

function validateLogin(){
	var emailID = document.getElementById("emailIDLogin");
	var password = document.getElementById("password");
	if(emailID.value.length<=0 || password.value.length <= 0){
		alert("please enter all the information");
		return false;
	}else{
		//alert("else");
		document.loginForm.submit();
	}
	
}

</script>

</head>
<body>
<div>
	<table>
		<tr>
			<td>
				<form name="loginForm" method="post" action="LoginServlet">
					<h2>Login:</h2>
					Email: <input id="emailIDLogin" type="text" name="emailIDLogin" /><br/>
					Password: <input id="password" type="password" name="password" /><br/>
					<input type="submit" value="submit" onclick="validateLogin();return false"/>	<input type ="reset" value="Clear Form"><br />
					<a href="/BugTracker/ForgetPassword.jsp">forget password?</a>
				</form>
			</td>
			
			<td>
				<form id="regForm" name="regform" method="post" action="RegisterUserServlet">
					<h2>Create User:</h2>
					First Name:* <input id = "firstname" type="text" name="firstname" /><br/>
					Last Name:* <input id = "lastname" type="text" name="lastname" /><br/>
					Email:* <input id = "emailID" type="text" name="emailID" /><br/>
					Password:* <input id = "newPassword" type="password" name="newPassword" /><br/>
					<label >Role:* </label>
	            		<select id="roleSelect" name="roleSelect">
	            			<option value="Help Desk">Help Desk</option>
	            			<option value="Developer">Developer</option>
	        			</select><br />
					Submit: <input type="submit" value="submit" onclick="validateRegistrationForm();return false"/>	<input type ="reset" value="Clear Form">
				</form>
			</td>
		</tr>
	</table>
</div>
<% 
	if ((request.getAttribute("ERROR_MSG") != null)) {
		String error = request.getAttribute("ERROR_MSG").toString();
		%>
		<div>
			<h4>Error: <%=error%></h4>
		</div>
		
		<%
	}

%>
</body>
</html>