<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/script/modal.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
	<div id="background-wrapper">
		<header class="container-fluid">
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
			<div id="dash-gacha" class="row">
				<button id="open-modal">Open Box</button>
			</div>

		<div id="dash-main" class="row">
			<div id="avatar" class="bodypart col-sm-3 text-center">
				<h2 class="text-center">Current Items</h2>
				<hr />
				<h3>Avatar</h3>
				<div class="row">
					<img id="dash-avitar" class="col-sm-6" src="${userInfo.avatar.url}"
						alt="" />
				</div>
				<h3>Token</h3>
				<div class="row">
					<img class="col-sm-4" id="dash-token" src="${userInfo.token.url}"
						alt="" />
				</div>
				<form action="/items" class="row">
					<input type="submit" value="Change" class="btn" role="button" />
				</form>
			</div>
			<div id="stat" class="bodypart col-sm-3 text-center">
				<h2>Stats</h2>
				<hr />
				<p>
					Games Played:
					<c:out value="${userInfo.gamesPlayed }" />
				</p>

				<p>Win Percentage: 100%</p>
				<p>
					Credits:
					<c:out value="${userInfo.credits}" />
				</p>
				<p>
					Ranked Points:
					<c:out value="${userInfo.rankedPoints}" />
				</p>
				<p>
					Boxes Opened:
					<c:out value="${userInfo.boxesBought }" />
				</p>
				<p>
					User Since:
					<fmt:formatDate type="date" value="${userInfo.createdAt}" />
				</p>
				
				<%-- 	<form action="/ranking">
						<input type="submit" value="View Rank" class="btn" role="button" />
					</form>  --%>
			</div>
			<div id="friends" class="bodypart col-sm-3 text-center">
				<h2>Friends</h2>
				<hr />
				<div class="search-container">
					<form action="">
						<input type="text" placeholder="Search.." name="search">
						<button type="submit" class="btn" role="button">Search</button>
					</form>
					<div class="row">
						<p class="col-sm-12">No Friends! :(</p>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid">


		


			<div id="play" class="row">
				<form action="/game/play" class="col-sm-6 text-right">
					<input type="submit" value="Ranked" class="btn"  />
				</form>
				<form action="/game/play" class="col-sm-6 text-left">
					<input type="submit" value="Casual" class="btn"  />
				</form>
			</div>

		</div>
		<!-- END OF CONTAINER -->
		<footer> </footer>
	</div>
	<div id="modal">
		<button>X</button>
		<h2>Whats Inside?</h2>
		<img
			src="https://media3.giphy.com/media/8TCWcOcocBO3UuxuDt/source.gif"
			alt="treasure" />
		<p>Credits: ${userInfo.credits}</p>
		<form action="/gacha" method="post" id="pull-gacha">
			<input id="open-box" type="submit" value="OPEN" />
		</form>
	</div>
</body>
</html>