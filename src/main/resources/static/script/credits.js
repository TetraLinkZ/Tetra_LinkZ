

$(document).ready(function() {

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
})

})



