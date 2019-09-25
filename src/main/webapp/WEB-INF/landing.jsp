<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css" />
<meta charset="UTF-8">
<title>Tetra LinkZ</title>
</head>
<body>
	<header>
		<h1>Tetra LinkZ</h1>
		<form action="/users/login" method="POST" id="login-form">
		<p>
			<label for="email">Email: </label> <input type="email" name="email"
				id="" /> 
		</p>
			<p>
				<label for="password">Password: </label> <input type="password"
					name="password" id="" />
			</p>
			<input type="submit"
				value="Login" />
		</form>
	</header>
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