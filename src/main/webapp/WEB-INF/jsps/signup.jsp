<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h6>${msg}</h6>
	<form action="/save" method="post">
		<input type="text" name="firstName"/>
		<input type="text" name="lastName"/>
		<input type="text" name="email"/>
		<input type="text" name="mobileno"/>
		<input type="password" name="password"/>
		<input type="submit" value="SAVE"/>
	</form>
</body>
</html>