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
	alert("valid E-mail ID")
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
<form id="resetPasword" name="resetPasword" method="post" action="ForgetPassServlet">
	<label id="emailIDLabel">Please enter your email address:* </label><input id = "emailID" type="text" name="emailID" /><br/><br/>
	<input type="submit" value="submit" onclick="ValidateEmail();return false"/>	<input type ="reset" value="Clear"><br />
</form>
</body>
</html>