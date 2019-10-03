$(function() {
	isOpen = false;
	$("#open-modal").click(function() {
		isOpen = true;
		$("#modal-container").fadeIn("fast");
		$("modal-container").css("display", "block");
		$("#modal").slideDown("slow");
		$("#modal").css("display", "flex");
		$("#modal").css("z-index", "99999");
	});

	$("#modal button").click(function() {
		isOpen = false;
		$("#modal-container").fadeOut("fast");
		$("#modal").slideUp("slow");
	});
	$("body").keydown(function(e) {
		if (e.keyCode == 27 && isOpen == true) {
			isOpen = false;
			$("#modal-container").fadeOut("fast");
			$("#modal").slideUp("slow");
			$("#modal-avatar-container").fadeOut("fast");
			$("#modal-avatar").slideUp("slow");
		}
	})

	$("#pull-gacha")
			.submit(
					function(e) {
						e.preventDefault();
						let credits = $("#credits").val();
						if (credits < 100) {
							$("#error").html("Not Enough Credits!");
						} else {
							$
									.ajax({
										url : "/gacha",
										method : "post",
										success : function(data) {
											let url = data;
											if (url.length < 3) {
												$("#error").html(
														"Not Enough Credits!");
											} else {
												$("#modal-title").html(
														"Congrats!");
												$("#gacha-pic")
														.attr("src", url);
												$("#stat").load(
														location.href
																+ " #stat>*",
														"");
												$("#modal-credits")
														.load(
																location.href
																		+ " #modal-credits*",
																"");
												$("#gacha-pic").css("display",
														"none");
												$("#gacha-pic").slideDown(
														"fast", function() {

														});
											}

										},
										error : function(err) {
										}

									});
						}
					});
	$("#dash-avitar").click(function() {
		console.log("clicked");
		isOpen = true;
		$("#modal-avatar-container").fadeIn("fast");
		$("modal-avatar-container").css("display", "block");
		$("#modal-avatar").slideDown("slow");
		$("#modal-avatar").css("display", "flex");
		$("#moda-avatarl").css("z-index", "99999");
	});
	$("#modal-avatar button").click(function() {
		isOpen = false;
		$("#modal-avatar-container").fadeOut("fast");
	});
});
