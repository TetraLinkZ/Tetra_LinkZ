$(document).ready(function() {
	let currentBoardString = $("#game-board").data('current-board');
	
	let occupyCheck = function(boardIdx){
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
        let occupy = occupyCheck(boardIdx);
        
        
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
            
	  board.find(".column").click(function() {

		let column = $(this).data('column');
        let payload = {
//          board_data: "0002000001102000222100012220002111000122100012110", //example
          board_data: currentBoardString, //forrealzies
          player: 2,
          i: 6,
          j: column
        }

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
        	  $("#game-board").attr("data-current-board", result);
        	  $("#game-board").empty();
        	  drawGame();
        	  $("#game-board").load("#game-board");
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
      

	// END SCRIPT
})
