$(document).ready(function() {
	
	let currentBoardString = $("#game-board").data('current-board');
	
	
	let occupyCheck = function(boardIdx){
		let currentBoardString = $("#game-board").data('current-board');

    	if(currentBoardString.charAt(boardIdx) == "1") {
    		return "p_one_token";
    	} else if (currentBoardString.charAt(boardIdx) == "2") {
    		return "p_two_token";
    	} else {
    		return "empty";
    	}
    }
	
	const drawGame = () => {
        let output = '';
        let boardIdx = 0;
        
        
        for (let row = 0; row < 7; row++) {
          output += '<div class="row row'+ row +' empty">';
          for (col = 0; col < 7; col++) {
            output += "<div class='column column" + col + " " + occupyCheck(boardIdx) + "' data-column='" + col + "' data-occupy='" + currentBoardString.charAt(boardIdx) + "'></div>";
            boardIdx++;
          }
          output += '</div>';
        }
        $("#game-board").append(output);
      }

      drawGame();
      console.log("hello, welcome to the game!");

      // TEST CONTROLS
      
      const board = $("#game-board");
      let playerTurn = $("#game-board").data("current-player");
            
	  $("#game-board").on("click", ".column", function(e) {

      console.log("clicked board")
      console.log("Current player is...", playerTurn)
      let column = $(this).data('column');
      let payload = {
          board_data: $("#game-board").attr("data-current-board"), //forrealzies
          player: playerTurn,
          i: 6,
          j: column
        }
        console.log("KEVINLABS PAYLOAD: " + payload.player);

        // PROMISE utilization:
        let connectFourWinCheck = $.ajax({
      	  url: "http://kevinalbs.com/connect4/back-end/index.php/hasWon",
  	      method: "GET",
  	      data: payload
        })
        
        let gameAPImove;
        let movePayload = {
          match: 2,
          row: payload.i, 
          column: payload.j,
          user: 2,
          player: payload.player,
        }

        connectFourWinCheck.done((checkResult)=>{
        	console.log("COLUMN: " + column);
        	console.log("game board", currentBoardString);
        	console.log("win check API call result: ", checkResult);
        	
        	movePayload.win = checkResult;
          gameAPImove = $.ajax({
          url: "/game/play/move",
          method: "POST",
          data: movePayload
      	  });
          gameAPImove.done((result)=>{
            console.log(movePayload);
            
            currentBoardString = result;
            
            $("#game-board").attr("data-current-board", result);

            $.ajax({
              url: "/game/play/playerTurn",
              method: "GET"
            })
            .done((playerChange)=>{
            	console.log("AJAX response is..." + playerChange);
            	console.log("before:", $("#game-board").data("current-player"))
              $("#game-board").attr("data-current-player", playerChange);
            	console.log($("#game-board").data("current-player"))
              $("#game-board").empty();
              let output = '';
              let boardIdx = 0;
                  
              let occupyCheck = function(boardIdx, newBoard){
              let currentBoardString = newBoard;

                  if(currentBoardString.charAt(boardIdx) == "1") {
                    return "p_one_token";
                  } else if (currentBoardString.charAt(boardIdx) == "2") {
                    return "p_two_token";
                  } else {
                    return "empty";
                  }
                }
                  
                for (let row = 0; row < 7; row++) {
                  output += '<div class="row row'+ row +' empty">';
                  for (col = 0; col < 7; col++) {
                    output += "<div class='column column" + col + " " + occupyCheck(boardIdx, result) + "' data-column='" + col + "' data-occupy='" + currentBoardString.charAt(boardIdx) + "'></div>";
                    boardIdx++;
                  }
                  output += '</div>';
                }
                $("#game-board").append(output);
            })

            

              
              

        	  
        	  
          }).fail((err)=>{
        	  console.log(movePayload);
        	  console.error(err);
        	  console.log(err.responseJSON.error + " : " + err.responseJSON.message);
          })
          
        })
  	
        
        
         // END OF SCOPE
        console.log("hello from POST ajax")
        return false;
      })
      
      setInterval(function(){
    	  
    	

        $.ajax({
          url: "/game/play/board",
          method: "GET"
        }).done((result)=>{

          $("#game-board").attr("data-current-board", result);
          console.log("Hello from AJAX interval", result);
          $("#game-board").empty();
            let output = '';
            let boardIdx = 0;
            
          let occupyCheck = function(boardIdx, newBoard){
          let currentBoardString = newBoard;
  
            if(currentBoardString.charAt(boardIdx) == "1") {
              return "p_one_token";
            } else if (currentBoardString.charAt(boardIdx) == "2") {
              return "p_two_token";
            } else {
              return "empty";
            }
          }
            
            for (let row = 0; row < 7; row++) {
              output += '<div class="row row'+ row +' empty">';
              for (col = 0; col < 7; col++) {
                output += "<div class='column column" + col + " " + occupyCheck(boardIdx, result) + "' data-column='" + col + "' data-occupy='" + currentBoardString.charAt(boardIdx) + "'></div>";
                boardIdx++;
              }
              output += '</div>';
            }
  //          console.log("output: within fxn: " + output);

            // console.log($("#game-board").data("current-board"));
            $("#game-board").append(output);
        })

    	  
      }, 3000);
	// END SCRIPT
})
