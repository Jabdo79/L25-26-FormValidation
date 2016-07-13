<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- Spring Form Tag Library -->    
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.error{
color: red;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
<h1>Sign Up</h1>
	<form:form action="submitNewUser.html" method="POST">
		<table>
			<tbody>
				<tr>
					<td><form:input path="username" placeholder="Enter username"></form:input></td>
					<td>(10 Characters Max)</td>
					<td class="error">${username}${error}</td>
				</tr>
				<tr>
					<td><form:input path="password" placeholder="Enter password"></form:input></td>
					<td>(32 Characters Max)</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit"></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>