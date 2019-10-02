<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/style.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/script/chat.js"></script>
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
<style>
#chat {
margin:1em auto !important;
padding:1em 2em !important;
	width: 70% !important;
	background-color:rgba(0, 0, 0, 0.5); !important;
	border-radius:10px;
	height:100%;

	
	
}
</style>
</head>
<body>
	<header class="container-fluid">
		<div class="row">
			<div class="col-sm-8">
		<h1 class="header-title">Tetra LinkZ</h1>
			</div>
			<div class="col-sm-2">
				<h2>
					Welcome,
					<c:out value="${user.name}" />
				</h2>
				<a href="/dashboard" class="btn">Profile</a> <a href="/logout"
					class="btn">Logout</a>
				<div></div>
			</div>

		</div>
	</header>
	<h1 class="text-center">
		Chat With
		<c:out value="${friend.name}" />
	</h1>
	<form action="/chat/${friend.id}" method="post" id="send-msg"
		class="text-center">
		<input type="text" name="message" /> <input type="hidden"
			name="user_id" value="${user.id}" /> <input type="hidden"
			id="friend_id" name="friend_id" value="${friend.id}" /> <input
			type="submit" value="Send" class="btn" />
	</form>
	<div id="chat-container" class="w-100 m-0">
	<div id="chat" class="m-auto">
		<c:forEach items="${messages}" var="message">
	<hr />
		<div class="message">
			<p>
				<c:out value="${message.user.name}:" />
				<c:out value="${message.message}" />
				</p>
				Sent At:
<fmt:formatDate type="time" value="${message.createdAt}" />

		</div>
			</c:forEach>
	</div>
	</div>

</body>
</html>