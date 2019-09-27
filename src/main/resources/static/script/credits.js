

$(document).ready(function() {
	$("#game-coin").hide();
	$("#creditz").on("submit", function(e) {
	  e.preventDefault();
	  var actionUrl = $(this).attr("action");
	  var body = $(this).serialize();
	  
	  $.ajax({
	      url: "/api/credits",
	      method: "POST",
	      data: body,
	      success: function(data) {
	        console.log("cool");
	      },
	      dataType: "json"
	  });
	  
	  return false;
	});
	
	//effects:
	$("#creditz").on("submit", function(){
		$("#blockCredit").effect( "bounce", "slow" );
		$("#game-coin").toggle();
		$( "#game-coin" ).toggle( "bounce", { times: 1 }, "fast" )
	 });

})



