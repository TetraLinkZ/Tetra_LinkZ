(function() {
    // Load the script
    var script = document.createElement("SCRIPT");
    script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js';
    script.type = 'text/javascript';
    script.onload = function() {
        var $ = window.jQuery;
        const drawGame = () => {
            let output = '';
            
            for (let row = 0; row < 7; row++) {
              output += '<div class="row row'+row+' empty">';
              for (col = 0; col < 7; col++) {
                output += "<div class='column column" + col + " empty'></div>";
              }
              output += '</div>';
            }
            $("#game-board").append(output);
          }

          drawGame();
          console.log("hello, welcome to the game!");

          // TEST CONTROLS

          $(".column").click(function() {
            // ".column"
            let payload = {
              board_data: "0002000001102000222100012220002111000122100012110",
              player: 2,
              i: 3,
              j: 3
            }

            $.ajax({
                url: "http://kevinalbs.com/connect4/back-end/index.php/hasWon",
                method: "GET",
                data: payload,
                success: function(data) {
                  console.log("player win with the move? :" + data);
                  payload.win = data;
                  $.ajax({
                    url: "/game/play/move",
                    method: "POST",
                    headers: {"X-CSRF-TOKEN": $("meta[name='_csrf']").attr("content")},
                    data: {
                      match: 2,
                      row: payload.i,
                      column: payload.j,
                      user: 2,
                      player: payload.player,
                      // win: payload.win
                      win: false
                    },
                    success: function(data) {
                      console.log("current board: " + data);
                    },
                    dataType: "json"
                  });
                },
                dataType: "json"
            });
            console.log("hello")
            return false;
          })
    };
    document.getElementsByTagName("head")[0].appendChild(script);
})();

