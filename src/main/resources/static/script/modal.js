$(function() {
	$("#open-modal").click(function() {
		console.log("clicked");
		$("#modal").slideDown("slow", function() {
		});
		$("#modal").css("display", "flex");
		$("#modal").css("z-index", "99999");

		$("#background-wrapper").css("opacity", ".2");
	});
	$("#modal button").click(function() {
		$("#modal").slideUp("slow", function() {
			$("#background-wrapper").css("opacity", "1");		

		});
	});

	
	$("#pull-gacha").submit(function(e){
		e.preventDefault();
		$.ajax({
			url:"/gacha",
			method:"post",
			success:function(data){
			let url = data;
			console.log(url);
				if(url.length < 3){
					$("#error").html("Try Again!");
				}else{
					$("#modal-title").html("Congrats!");
					$("#gacha-pic").attr("src", url);
					$("#stat").load();
				}
				
			}, error:function(err){
				console.log(err)
			}
		
		});
	});


	$("#dash-avitar").click(function() {
		console.log("clicked");
		$("#modal-avatar").slideDown("slow", function() {
		});
		$("#modal-avatar").css("display", "flex");
		$("#modal-avatar").css("z-index", "99999");
	});
	$("#modal-avatar button").click(function() {
		$("#modal-avatar").slideUp("slow", function() {
			$("#background-wrapper").css("opacity", "1");		

		});
	});
});
	
