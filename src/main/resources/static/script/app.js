let StompClient = null;

function connect() {
	let socket = new SockJS('/tetra');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/game/play', function(message) {
			showMessage(message.body);
		});
	});
}

function disconnect() {
	//
}
function getMessage() {
	stompClient.send("/app/message", {}, JSON.stringify({
		'content' : $("#message").val(),
		'username' : $("#username").val()

	}));
}
function showMessage(message) {
	console.log("test");
	$("#chat-box").append("<p>" + message + "</p>");
}
/*function drawBoard() {
	let board = [];
	let row = [];
	for (let i = 0; i < 7; i++) {
		row[i] = 6;
		board[i] = [];
		for (var j = 0; j < 7; j++) {
			board[i][j] = 0;
		}
	}

	let str = "";
	for (let i = 0; i <= board.length; i++) {
		for (let j = 0; j <= board.length - 1; j++) {
			str += board[i][j];
			console.log(str);
		}
	}

	console.log("string" + str);
}*/
$(function() {
	connect();
	$("#message-box").on("submit", function(e) {
		e.preventDefault();
		getMessage();
	});
	drawBoard();

});
