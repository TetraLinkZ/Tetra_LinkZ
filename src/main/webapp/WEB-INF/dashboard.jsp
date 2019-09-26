<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/script/modal.js"></script>
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
		<button id="open-modal">Open Box</button>
	</div>
	<main id="dash-main">
	<div id="avatar" class="bodypart">
	<h2>Profile</h2>
		<img src="${userInfo.avatar.url}" alt="" style="width: 200px;" />
		<img src="${userInfo.token.url}" alt="" style="width: 50px;" />
		<form action="/items">
			<input type="submit" value="Change" class="dash-button"/>
		</form>
	</div>
	<div id="stat" class="bodypart">
	<h2>Stats</h2>
	<p>Games Played: </p>
	<p>Win Percentage:</p>
		<form action="">
			<input type="submit" value="View Rank" class="dash-button"/>
		</form>
	</div>
	<div id="friends" class="bodypart">
		<h2>Friends</h2>
		<div class="search-container">
			<form action="">
				<input type="text" placeholder="Search.." name="search">
				<button type="submit" class="dash-button">Search</button>
			</form>
		</div>
	</div>
	</main>
	<div id="play">
		<form action="">
			<input type="submit" value="Ranked" class="play-button"/>
		</form>
		<form action="">
			<input type="submit" value="Casual" class="play-button"/>
		</form>
	</div>
	<div id="modal">
	<button>X</button>
	<h2>Whats Inside?</h2>
	<img src="https://media3.giphy.com/media/8TCWcOcocBO3UuxuDt/source.gif" alt="treasure" />
	<p>Credits: ${userInfo.credits}</p>
	<form action="">
	<input type="hidden" value="${userInfo.id}" />
	<input id="open-box"type="submit" value="OPEN" />
	</form>
	</div>
	<footer> </footer>
</body>
</html>