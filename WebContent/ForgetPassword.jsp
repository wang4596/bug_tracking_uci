 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Reset</title>
<script>
function validateField(){
	var emailID = document.getElementById("emailID");
	
	if(emailID.value.length<=0){
		alert("Please enter the email id.");
		
		return false;
	}else{
		document.resetPasword.submit();
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

function ValidateEmail(){
	var emailID=document.getElementById("emailID");
	if ((emailID.value==null)||(emailID.value=="")){
		alert("Please Enter your Email Address");
		document.getElementById("emailIDLabel").style.color = "red";
		document.getElementById("emailIDLabel").style.fontWeight="900";
		emailID.focus();
		return false;
	}
	if (emailcheck(emailID.value)==false){
		emailID.value=""
		emailID.focus();
		return false;
	}
	document.resetPasword.submit();
	//return true;
	}
</script>
</head>
<body>
	
	<table align = "center" width="70%" border="1">
		<tr>
		<td><a href="/BugTracker/LoginPage.jsp"><img src="/BugTracker/resources/logo_2.jpg" width="185" height="64" alt="Bug Tracker logo" /></a></td>
		<td align="right"><a href="LoginPage.jsp">Login</a></td>
		</tr>
	</table>
	<h2 align="center">Reset Password:</h2>
	<table width="40%" style="border: 1px solid black;"  cellpadding="5" cellspacing="5" align="center" >
			<td>
				<form id="resetPasword" name="resetPasword" method="post" action="ForgetPassServlet">
					<tr>
						<td align="left"><label id="emailIDLabel">Please enter your email address:* </label></td><td><input id = "emailID" type="text" name="emailID" /><br/><br/></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" value="Reset password" onclick="ValidateEmail();return false"/>	<input type ="reset" value="Clear Form"><br /></td>
					</tr>
				</form>
			</td>
	</table>
</body>
</html>