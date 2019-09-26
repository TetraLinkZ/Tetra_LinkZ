<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <!-- <script src="/script/game.js"></script> -->
    
    <link rel="stylesheet" href="/css/style.css" />
    <!-- TEMP STYLING -->
    <style>
    
    	header {
    	/* margin-left: 20px; */
    		width: 100%;
    	}
    	#give-up {
    		margin-top: -10px;
    		float: right;
    	}
	    #game-board {
	    	display: none;
	    }
    	#chat { 
    		width: 100%;
    	}
    	#message-box {
    		display: flex;
    		justify-content: center;
    	}
    	.userAvatar {
    		width: 150px;
    		height:150px;
    		border-radius: 10px;
    		background-size: 500px 500px;
    		background-position: 50% 40%;
    	}
   		.message-box:nth-child(3n+3) {
   			/* Text box */
   			background-image: linear-gradient(#c0539b, #c0539b, #9319e5);
   			box-shadow: 0 16px 10px -17px rgba(0, 0, 0, 0.5);
   			border-radius: 5px;
   			padding: 10px;
   			display: inline-block;
   			
		    border-radius: 5px;
		    box-shadow: 2px 2px 2px #19041f;
		    margin: 0 20px;
		    width:  275px;
		}
		
		.message-box:nth-child(3n+3)::after {
		    content: "\00a0";
		    display: inline-block;

		}
 		.message-box:nth-child(3n+2) {
 			/* Avatar */
   			display: inline-block;
		}
		.message-box:nth-child(3n+1) {
			/*Seperator div*/
		}
    </style>
<title>TETRACHAT</title>
</head>
<body id="game-jsp">
<c:set var="userId" value="${user.id}" />  


<header>
	<div id="show-players"></div>
<div id = "game-info">
<h1>Tetra LinkZ</h1>
<div >
<!-- timer img -->
<div id="timer"><!-- TIMER --></div>
<a href="/dashboard"><button id="give-up">Give Up</button></a>
</div>
</div>
</header>
<main>
	<div id="game-board">
	</div>
	
	<div id="chat">
	<p>Chatting as: <c:out value="${user.name}" /> </p>
	<form action="" id="message-box" autocomplete="off">
	<input type="hidden" name="avatar" id="userAvatar" value="${user.avatar.url}"/>
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