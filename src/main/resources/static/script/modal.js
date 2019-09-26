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

});
$.ajax({
	url:"/gacha",
	method:"post",
	success:function(){
		console.log("test");
	}
});
