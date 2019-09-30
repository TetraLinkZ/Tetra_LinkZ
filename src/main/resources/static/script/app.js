let StompClient = null;

function connect() {
	let socket = new SockJS('/tetra');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/game/play', function(message) {
			showMessage(message.body)
			receiveSeperator();
		});
	});
}

function disconnect() {
	//
}

function getAvatar() {
	stompClient.send("/app/message/avatar", {}, JSON.stringify({
		'avatar' : $("#userAvatar").val()
	}))
}

function showAvatar(message) {
//	console.log("in show avatar", message);
	$("#chat-box").prepend(message);
}

function getMessage() {
	stompClient.send("/app/message", {}, JSON.stringify({
		'content' : $("#message").val(),
		'username' : $("#username").val()
	}));
}

function showMessage(message) {
	// console.log("message");
	//$("form").trigger("reset");
	$("#chat-box").prepend("<div class=\"message-box\ style:\"vertical-align:middle\"><p>" + message + "</p></div>");
}

function seperator() {
	$("#chat-box").prepend("<div></div>");
}

var receiveSeperator = (function(){
	var executed = true;
	return ()=>{
		if(!executed){
			executed = true;
			seperator();
		} else {
			executed = false;
		}
	}
})();
var sendSeperator = (function(){
	var executed = false;
	return ()=>{
		if(!executed){
			executed = true;
			seperator();
		} else {
			executed = false;
		}
	}
})();



$(function() {
	connect();
	$("#message-box").on("submit", function(e) {
		e.preventDefault();
//		$.when(getMessage()).then(getAvatar())
//		getMessage();
		
		$.ajax({
			url: getMessage(),
			success: ()=>{
				$.ajax({
					url: getAvatar(),
					success: ()=>{
						// console.log("yay")
					}
				})
			}
		})
	});


});
