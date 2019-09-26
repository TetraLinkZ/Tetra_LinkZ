<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
<link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/script/app.js"></script>
    <script src="/script/game.js"></script>
    
    <link rel="stylesheet" href="/css/style.css" />
<title>Insert title here</title>
</head>
<body id="game-jsp">
<sec:csrfMetaTags />

<header>
	<div id="show-players"></div>
<div id = "game-info">
<h1>Tetra LinkZ</h1>
<div >
<!-- timer img -->
<div id="timer"><!-- TIMER --></div>
<button id="give-up">Give Up</button>
</div>
</div>
<!-- TEST -->
<form method="POST">
	<input type="submit" value="submit" id="test" />
</form>
</header>
<main>
	<div id="game-board">
	</div>
	
	<div id="chat">
	<form action="" id="message-box">
	<input type="hidden" name="username" id="username" value="${user.name}"/>
	<label for="message">Enter Message: </label>
	<input type="text" name ="message" id="message"/>
	<input type="submit" value="Send" id = "send"/>
	</form>
	<div id="chat-box"></div>
	</div>
</main>

</body>
</html>