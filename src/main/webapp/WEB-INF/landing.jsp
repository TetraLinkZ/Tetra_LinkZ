<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Tetra LinkZ</title>
</head>
<body>
	
	<main id="landing-main">
	<div id="registration-box">
		<h2>Join Now!</h2>
		<form:form action="/users/register" modelAttribute="newUser"
			id="registration-form">
			<form:label path="name">Username: </form:label>
			<form:input path="name" />
			<form:errors path='name' />

			<form:label path="email">Email: </form:label>
			<form:input path="email" />
			<form:errors path='email' />

			<form:label path="password">Password: </form:label>
			<form:input type="password" path="password" />
			<form:errors path='password' />

			<form:label path="passwordConfirm">Re-enter Password:</form:label>
			<form:input type="password" path="passwordConfirm" />
			<form:errors path="passwordConfirm" />
			<input type="submit" value="Register" />
		</form:form>
	</div>
	</main>
</body>
</html>