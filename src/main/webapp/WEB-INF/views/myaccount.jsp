<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<link href="resources/css/style.css" type="text/css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>

	<div align="center" style="padding-top: 5%"><h1 id="header">Congrats! You are
		logged in! ${loggedInUser.email}</h1></div>
<div align="center" style="padding-top: 5%"><h3 id="header">Your selected tours:</h1></div>
<div align="center" style="padding-top: 2%">
		<mytag:myTourList coll="${myTours}" />
	</div>
	<div align="center" style="padding-top: 5%">
		<form action="order" method="GET">
			<input type="submit" value="MakeOrder" />
		</form>
		</div>
	<div align="center" style="padding-top: 10%">
		<form action="logout" method="GET">
			<input type="submit" value="Logout" />
		</form>
	</div>
</body>
</html>
