<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css" />
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<header>
		<h1>Tetra LinkZ</h1>
		<div>
			<h1>
				Welcome,
				<c:out value="${userInfo.name}" />
			</h1>
			<a href="/logout">Logout?</a>
		</div>
	</header>
	<div id="dash-gacha">
		<form action="">
			<input type="submit" value="Open Box" />
		</form>
	</div>
	<main id="dash-main">
	<div id="avatar" class="bodypart">
	<h2>Profile</h2>
		<img src="${userInfo.avatar.url}" alt="" style="width: 200px;" />
		<form action="">
			<input type="submit" value="Change" />
		</form>
	</div>
	<div id="stat" class="bodypart">
	<h2>Stats</h2>
		<form action="">
			<input type="submit" value="View Rank" />
		</form>
	</div>
	<div id="friends" class="bodypart">
		<h2>Friends</h2>
		<div class="search-container">
			<form action="">
				<input type="text" placeholder="Search.." name="search">
				<button type="submit">Search</button>
			</form>
		</div>
	</div>
	</main>
	<div id="play">
		<form action="">
			<input type="submit" value="Ranked" />
		</form>
		<form action="">
			<input type="submit" value="Casual" />
		</form>
	</div>
	<footer> </footer>
</body>
</html>