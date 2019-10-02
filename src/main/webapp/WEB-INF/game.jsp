<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
    <script src="//code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
    <script src="/script/app.js"></script>
    
    <script src="/script/credits.js"></script>
    <script src="/script/game.js" ></script>
    
    <link rel="stylesheet" href="/css/style.css" />
    <!-- TEMP STYLING -->
    
<style>
	#game-board {
		display: flex;
		flex-direction: column;
		background-size: 200px 200px;
		background-color: #999;
	}
	.row: {
		display: flex;
		flex-direction: row;
	}
	.column {
		height: 50px;
		min-width: 50px;
		margin: 0 8px;
		background-color: #777;
		
		dsiplay: flex;
		flex-direction: row;
		
		border-radius: 50%;
		box-shadow: inset 0px -3px #888;
	}
	
	.p_one_token {
	/* background-color: red; */
		
		background-image: url(${match.players.get(0).token.url});
		background-size: contain;
		box-shadow: inset 0px -1px 1px #888;
	}
	
	.p_two_token {
	/* background-color: yellow; */
		background-image: url(${match.players.get(1).token.url});
		background-size: contain;
		box-shadow: inset 0px -1px 1px #888;
	}
	
	
</style>

<script>
	console.log("hello, ${match.players.get(1).token.url}");
	console.log("hello, ${match.players.get(0).token.url}");

	console.log("hello, ${playerOne.name}");
</script>
<title>TETRACHAT</title>
</head>
<header class="container-fluid">
			<div class="row">
				<div class="col-sm-8">
					<h1 class="header-title">Tetra LinkZ</h1>
					<h2>${playerOne.name}</h2>
					

					
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
<body id="game-jsp"> 
<c:set var="userId" value="${user.id}" />  

<main>
	<div id="game-div">
		<div id="game-board" data-current-board="${match.getBoard()}" data-current-player="${match.getCurrentPlayer()}">
		</div>
	</div>

	<div>
		<form action="/game/play/clear" method="POST">
			<input type="hidden" name="_method" value="PUT"/>
			<input type="hidden" name="match" value="2"/>
			<input type="submit" value="Clear Board"/>
		</form>
	</div>
	<div id="chat">
	<div id="chatting-as">
		<p>Chatting as: <c:out value="${user.name}" /></p>
		<form id="creditz" action="" >
			<input type="hidden" name="userId" value="${user.id}"/>	
			<input type="submit" id="blockCredit" value=""/>
			<div id="game-coin"></div>
		</form>	
	</div>
	<form action="" id="message-box" autocomplete="off">
	<input type="hidden" name="avatar" id="userAvatar" value="${user.avatar.url}"/>
	<input type="hidden" name="username" id="username" value="${user.name}"/>
	<label for="message">Enter Message: </label>
	<input type="text" name ="message" id="message"/>
	<input type="hidden" name="userId id="${user.id}" value="${user.id}"/>
	<input type="submit" value="Send" id ="send" class="btn text-left"/>
	</form>
	<div id="chat-box"></div>
	</div>
</main>

</body>
</html>