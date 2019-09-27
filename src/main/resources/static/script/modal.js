$(function() {
	$("#open-modal").click(function() {
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

	$("#pull-gacha").submit(
			function(e) {

				e.preventDefault();
				$.ajax({
					url : "/gacha",
					method : "post",
					success : function(data) {
						let url = data;
						if (url.length < 3) {
							$("#error").html("Try Again!");
						} else {
							$("#modal-title").html("Congrats!");
							$("#gacha-pic").attr("src", url);
							$("#stat").load(location.href + " #stat>*", "");
							$("#modal-credits").load(
									location.href + " #modal-credits*", "");
						}

					},
					error : function(err) {
					}

				});
			});
});
