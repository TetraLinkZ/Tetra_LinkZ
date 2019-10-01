$(function() {
	friendId = $("#friend_id").val();

	$("#send-msg").submit(function(e) {
		e.preventDefault();
		let newMessage = $(this).serialize();
		$.ajax({
			url : /chat/ + friendId,
			method : "post",
			data : newMessage,
			success : function() {
				console.log("success!");
				$("body").load(location.href + "#chat<*", "");
			},
			error : function(err) {
				console.log(err);
			}
		});
	});
	function refreshChat() {
		$.ajax({
			url : /chat/ + friendId,
			method : "get",
			success : function(data) {
				let chat = $(data).find("#chat");
				$("#chat-container").empty();
				$("#chat-container").html(chat);
			},
			error : function(err) {
				console.log(err);
			}
		})
	}

	setInterval(function() {
		refreshChat();
	}, 1000)

})