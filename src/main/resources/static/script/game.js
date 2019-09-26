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
              output += '<div class="row row'+row+'">';
              for (col = 0; col < 7; col++) {
                output += "<div class='column column" + col + "'>" + 0 + "</div>";
              }
              output += '</div>';
            }
            $("#game-board").append(output)
            console.log(output);
          }

          drawGame();
          alert("hllo");
          console.log("hello");
    };
    document.getElementsByTagName("head")[0].appendChild(script);
})();

