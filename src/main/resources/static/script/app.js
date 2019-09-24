let StompClient = null;


function connect(){
	let socket = new SockJS('/tetra');
	stompClient = Stomp.over(socket);
	 stompClient.connect({}, function (frame) {
		 	console.log("connecth")
		 	// setConnected(true);
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/game/play', function (message) {
	        	console.log("test");
	        	console.log(message.body);
	            showMessage(message.body);
	        });
	    });
}

function disconnect(){
//
	}
function getMessage(){
	stompClient.send("/app/message", {}, JSON.stringify({'content': $("#message").val()}));
}
function showMessage(message){
	console.log("test");
	$("#chat-box").append("<p>"+ message +"</p>");
}
$(function (){
	connect();	

	$("#message-box").on("submit", function (e){
		e.preventDefault();
		getMessage();
		//connect();			
	});
/*	$("#send").click(function(){
	});*/
	//$("#send").click(function (){connect();});
	
});
