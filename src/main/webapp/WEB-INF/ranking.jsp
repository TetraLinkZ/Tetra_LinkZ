<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css" />
<meta charset="utf-8">
<title>Insert title here</title>
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
	<h2>Top Players</h2>
	<ol>
		<!-- top players go here -->
	</ol>
	</main>
</body>
</html>