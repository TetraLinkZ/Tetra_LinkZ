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
	<div id="background-wrapper">
		<header class="container-fluid">
			<div class="row">
				<div class="col-sm-8">
					<h1 class="header-title">Tetra LinkZ</h1>
				</div>
				<div class="col-sm-2">
					<h2>
						<c:out value="${userInfo.name}" /> 's Profile
					</h2>
					<a href="/dashboard" class="btn">Back to Dashboard</a> 
					<div></div>
				</div>
			</div>
		</header>
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
			</div>
			<div id="friends" class="bodypart col-sm-3 text-center">
				<h2>Friends</h2>
				<hr />
				<div class="row">
					<c:forEach items="${friendList}" var="friend">
						<p class="friend">
							<c:out value="${friend.name}" />
						<p>
					</c:forEach>
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
</body>
</html>