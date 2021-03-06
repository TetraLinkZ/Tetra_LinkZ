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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
		<header class="container-fluid" >
			<div class="row">
				<div class="col-sm-8">
					<h1 class="header-title">Tetra LinkZ</h1>
				</div>
				<div class="col-sm-2">
					<h2>
						Welcome,
						<c:out value="${userInfo.name}" />
					</h2>
					<a href="/dashboard" class="btn">Profile</a> <a href="/logout"
						class="btn">Logout</a>
					<div></div>
				</div>

			</div>
		</header>
		<div id="dash-gacha" class="row m-auto">
			<button id="open-modal">Open Box</button>
		</div>

		<div id="dash-main" class="row m-auto">
			<div id="avatar" class="bodypart col-sm-3 text-center">
				<h2 class="text-center">Current Items</h2>
				<hr />
				<h3>Avatar</h3>
				<div class="row m-auto">
					<img id="dash-avitar" class="col-sm-6" src="${userInfo.avatar.url}"
						alt="" />
				</div>
				<h3>Token</h3>
				<div class="row m-auto">
					<img class="col-sm-4" id="dash-token" src="${userInfo.token.url}"
						alt="" />
				</div>
				<div class="row profile-btns m-auto">
					<a href="/items" class="btn">Change</a> <a href="/shop" class="btn">Shop</a>
				</div>
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
					Friend Code:
					<c:out value="${userInfo.friendCode}" />
				</p>
				<p>
					User Since:
					<fmt:formatDate type="date" value="${userInfo.createdAt}" />
				</p>

				<%-- 	<form action="/ranking">
						<input type="submit" value="View Rank" class="btn" role="button" />
					</form>  --%>
			</div>
			<div id="friends" class="bodypart col-sm-3 text-center ">
				<h2>Friends</h2>
				<hr />
				<div class="container-fluid">
				<div class="row">

					<form action="/addFriend" class="search-container" method="post">
						<input type="text" placeholder="Enter Friend Code" name="add" />
						<button type="submit" class="btn">Add</button>
					</form>
				</div>
				<c:forEach items="${friendList}" var="friend">
					<div class="row w-80 m-auto">
						<p class="friend col-sm-3 p-0">
							<c:out value="${friend.name}" />
						</p>


						<form action="/showProfile" method="post" class="col-sm-4 p-0">
							<input type="hidden" name="friendId" value="${friend.id}" /> <input
								type="submit" value="View" class="btn w-100" />
						</form>
						<a href="/chat/${friend.id}" class="btn col-sm-4 chat-link">Chat</a>
					</div>
				</c:forEach>
				<div class="row">
				<form action="/game/play" class="col-sm-12 text-center enter-chat m-auto">
					<input type="submit" value="Enter Chat" class="btn" id="chat-btn" />
				</form>
				</div>
		

				</div>
		</div>
		<div class="container-fluid">
			<div id="play" class="row">
				<%--
				<form action="/game/play" class="col-sm-3 text-right">
					<input type="submit" value="Ranked" class="btn" />
				</form> --%>
				<%-- 			<form action="/game/play" class="col-sm-12 text-center">
					<input type="submit" value="Enter Chat" class="btn" id="chat-btn" />
				</form> --%>
			</div>

		</div>
		<!-- END OF CONTAINER -->
		<footer> </footer>
	</div>
	<!-- END OF BACKGROUND WRAPPER -->
	<div id="modal-container" class="container-fluid m-0 p-0">
	<div id="modal" class=" m-auto">
		<button>x</button>
		<h2 id="modal-title">Whats Inside?</h2>
		<img
			src="https://media3.giphy.com/media/8TCWcOcocBO3UuxuDt/source.gif"
			alt="treasure" id="gacha-pic" />
		<p id="modal-credits">Credits: ${userInfo.credits}</p>
		<form action="/gacha" method="post" id="pull-gacha">
			<input type="hidden" name="credits" value="${userInfo.credits}" /> <input
				id="open-box" type="submit" value="OPEN" />
		</form>
		<p id="error"></p>
	</div>

	</div>
		<div id="modal-avatar-container" class="p-0 m-0 container-fluid">
			<div id="modal-avatar">
				<img src="${userInfo.avatar.url}" alt="avatar" id="modal-avatar-pic" />
				<h2 id="modal-title">
					<c:out value="${userInfo.avatar.name}" />
				</h2>
				<button>x</button>
			</div>
		</div>
</body>
</html>