 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 	
		Team D Bug Tracker Project
		8/28/2014  	Saurabh Pandit 		Created File
		9/7/2014	Melissa Stratton	Changed Team Lead role to Developer
		9/8/2014	Saurabh Pandit 		Added email validation
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
		if (emailcheck(emailID.value)==false){
			document.getElementById("emailIDLabel").style.color = "red";
			document.getElementById("emailIDLabel").style.fontWeight="900";
			emailID.value=""
			emailID.focus();
			return false;
		}
		document.regForm.submit();
	}
}

function validateLogin(){
	var emailID = document.getElementById("emailIDLogin");
	var password = document.getElementById("password");
	if(emailID.value.length<=0 || password.value.length <= 0){
		alert("please enter all the information");
		document.getElementById("emailIDLoginLabel").style.color = "red";
		document.getElementById("emailIDLoginLabel").style.fontWeight="900";		
		return false;
	}else{
		//alert("else");
		document.loginForm.submit();
	}
	
}

function emailcheck(str) {

	var at="@"
	var dot="."
	var lat=str.indexOf(at)
	var lstr=str.length
	var ldot=str.indexOf(dot)
	if (str.indexOf(at)==-1){
	alert("Invalid E-mail ID")
	return false
	}

	if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
	alert("Invalid E-mail ID")
	return false
	}

	if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
	alert("Invalid E-mail ID")
	return false
	}

	if (str.indexOf(at,(lat+1))!=-1){
	alert("Invalid E-mail ID")
	return false
	}

	if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
	alert("Invalid E-mail ID")
	return false
	}

	if (str.indexOf(dot,(lat+2))==-1){
	alert("Invalid E-mail ID")
	return false
	}

	if (str.indexOf(" ")!=-1){
	alert("Invalid E-mail ID")
	return false
	}
	return true 
}

</script>

</head>
<body>
	<table align = "left" width="70%" border="1">
		<tr>
			<td><a href="/BugTracker/LoginPage.jsp"><img src="/BugTracker/resources/logo_2.jpg" width="185" height="64" alt="Bug Tracker logo" /></a></td>
		</tr>
	</table>
<div>
	<table width="35%" style="border: 1px solid black;"  cellpadding="5" cellspacing="5" align="left" >
			<td>
				<form name="loginForm" method="post" action="LoginServlet">
					<h2>Login:</h2>
					<tr>
						<td align="left"><label id="emailIDLoginLabel">Email:* </label></td><td align="left"><input id="emailIDLogin" type="text" name="emailIDLogin" /></td>
					</tr>
						<td align="left"><label>Password:* </label></td><td align="left"><input id="password" type="password" name="password" /></td>
					<tr>
						<td align="left"></td><td><input type="submit" value="submit" onclick="validateLogin();return false"/>	<input type ="reset" value="Clear Form"><br /></td>
					</tr>
					<tr>
						<td><a href="/BugTracker/ForgetPassword.jsp">forget password?</a></td>
					</tr>
				</form>
			</td>
	</table>
	<br />
	<table width="35%" style="border: 1px solid black;"  cellpadding="5" cellspacing="5" align="left" >
			<td>
				<form id="regForm" name="regform" method="post" action="RegisterUserServlet">
					<h2>Create User:</h2>
					<tr><td align="left"><label>First Name:* </label></td><td><input id = "firstname" type="text" name="firstname" /><br/></td></tr>
					<tr><td align="left"><label>Last Name:* </label></td><td><input id = "lastname" type="text" name="lastname" /><br/></td></tr>
					<tr><td align="left"><label id="emailIDLabel">Email:* </label></td><td><input id = "emailID" type="text" name="emailID" /><br/></td></tr>
					<tr><td align="left"><label>Password:* </label></td><td><input id = "newPassword" type="password" name="newPassword" /><br/></td></tr>
					<tr><td align="left"><label >Role:* </label></td><td>
	            		<select id="roleSelect" name="roleSelect">
	            			<option value="Help Desk">Help Desk</option>
	            			<option value="team Lead">Developer</option>
	        			</select><br /></td></tr>
					<tr><td  align="left"></td><td><input type="submit" value="submit" onclick="validateRegistrationForm();return false"/>	<input type ="reset" value="Clear Form"></td></tr>
				</form>
			</td>
		
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