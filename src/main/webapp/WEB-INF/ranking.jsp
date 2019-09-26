<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<header class="container-fluid auto">
		<div class="row">
			<div class="col-sm-8">
				<h1 class="header-title">Tetra LinkZ</h1>
			</div>
			<div class="col-sm-2">
				<h2>
					Welcome,
					<c:out value="${userInfo.name}" />
				</h2>
				<a href="/dashboard" class="btn" role="button">Profile</a> <a
					href="/logout" class="btn" role="button">Logout</a>
				<div></div>
			</div>

		</div>
	</header>

	<main>
	<h2>Top Players</h2>
	<ol>
		<!-- top players go here -->
	</ol>
	</main>
</body>
</html>