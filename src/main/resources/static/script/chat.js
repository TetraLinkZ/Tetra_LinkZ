$(function(){
	
	
	$("#send-msg").submit(function(e){
		e.preventDefault();
		let newMessage = $(this);
		let friendId = $("#friend_id").val();
		console.log(friendId);
		$.ajax({
			url:/chat/+friendId,
			method : "post",
			data: newMessage,
			success:function(data){
				console.log("success!");
			}, error: function(err){
				console.log(err);
			}
			
			
		});
		
		
	});
})




