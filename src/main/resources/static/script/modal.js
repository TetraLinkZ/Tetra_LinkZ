$(function() {
	$("#open-modal").click(function() {
		console.log("clicked");
		$("#modal").slideDown("slow", function() {
		});
		$("#modal").css("display", "flex");
		$("#dash-main").css("opacity", ".2");
	});
	$("#modal button").click(function() {
		$("#modal").slideUp("slow", function() {
			$("#dash-main").css("opacity", "1");		

		});
	});

});
