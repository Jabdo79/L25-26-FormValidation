<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Area</title>
</head>
<body>
	<%
		String loggedIn = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loggedIn") && cookie.getValue().equals("true"))
					loggedIn = cookie.getValue();
			}
		}
		if (loggedIn == null)
			response.sendRedirect("createLogin.html");
	%>
	<h1>Welcome to the Member Area!</h1>
	<h3>${cookie.counter.value}</h3>
	<br>
	<a href="1.html">Member Link 1</a>
	<br>
	<a href="2.html">Member Link 2</a>
	<br>
	<a href="3.html">Member Link 3</a>
	<br>
</body>
</html>