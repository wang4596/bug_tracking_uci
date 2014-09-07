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
</script>
</head>
<body>
<form id="resetPasword" name="resetPasword" method="post" action="ForgetPassServlet">
	Please enter your email address: <input id = "emailID" type="text" name="emailID" /><br/><br/>
	<input type="submit" value="submit" onclick="validateField();return false"/>	<input type ="reset" value="Clear"><br />
</form>
</body>
</html>