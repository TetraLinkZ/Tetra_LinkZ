<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/style.css" />
</head>
<body>
	<header>
		<h1>
			Welcome,
			<c:out value="${userInfo.name}" />
		</h1>
		<a href="/logout">Logout?</a>
	</header>
	<main>
	<div class="items">
		<h1>Avatars</h1>
		<div id="avatars">
			<!-- obtained avatars -->
		</div>
	</div>
	<div class="items">
		<h1>Tokens</h1>
		<div id="tokens">
			<!-- obtained tokens -->
		</div>
	</div>
	</main>
</body>
</html>